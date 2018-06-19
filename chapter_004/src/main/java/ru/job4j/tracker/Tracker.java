package ru.job4j.tracker;
/**
 * Tracker.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

import java.util.Properties;

public class Tracker implements AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Tracker.class);
    private final Settings settings = new Settings();
    private Connection conn = null;
    private PreparedStatement st = null;
    private ResultSet rs = null;
    private final Properties prs = new Properties();
    private final String propertiesFile = "config.properties";

    /**
     * Connects to database.
     */
    public void connectToDb() {
        this.loadProperties();
        try {
            this.conn = DriverManager.getConnection(prs.getProperty("url"), prs.getProperty("userName"), prs.getProperty("password"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        if (this.checkDB()) {
            this.createDB();
            this.createTable();
        } else {
            if (this.checkTable()) {
                this.createTable();
            }
        }
    }

    /**
     * Add item.
     * @param item Item.
     * @return id item.
     */
    public int addItem(final Item item) {
        String sql = settings.getValue("addItem");
        int generateKeys = 0;
        try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setTimestamp(3, new Timestamp(item.getCreated().getTime()));
            generateKeys = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        Item addedTask = new Item(generateKeys, item.getName(), item.getDescription(), item.getCreated().getTime());
        LOGGER.info(String.format("%s added to task table.", addedTask));
        return generateKeys;
    }

    /**
     * Replace.
     * @param item item.
     * @return true if success.
     */
    public boolean replace(Item item) {
        String sql = settings.getValue("replace");
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setInt(3, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info(String.format("%s updated.", item));
        return true;
    }

    /**
     * Метод реализаущий удаление заявки из хранилища.
     * @param id идентификатор заявки.
     */
    public boolean delete(final int id) {
        String sql = settings.getValue("delete");
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info(String.format("Item with ID %s was removed from the table.", id));
        return true;
    }

    /**
     * Метод реализаущий получение списка всех заявок.
     */
    public ArrayList<Item> getAll() {
        String sql = settings.getValue("getAll");
        ArrayList<Item> items = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            try (ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    Item item = new Item(rs.getInt("id"), rs.getString("name"),
                            rs.getString("description"), rs.getTimestamp("create_date").getTime());
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return items;
    }

    /**
     * Find By Name.
     * @param name name.
     * @return List item.
     */
    public ArrayList<Item> findByName(String name) {
        String sql = settings.getValue("findByName");
        ArrayList<Item> items = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    items.add(new Item(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
                            rs.getTimestamp("create_date").getTime()));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return items;
    }

    /**
     * find By Id.
     * @param id id.
     * @return item.
     */
    public Item findById(final int id) {
        String sql = settings.getValue("findById");
        Item item = null;
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    item = new Item(rs.getInt("id"), rs.getString("name"),
                            rs.getString("description"), rs.getTimestamp("create_date").getTime());
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info(String.format("%s returned from the item table.", item));
        return item;
    }

    /**
     * Check create data base.
     * @return true if not data base.
     */
    private boolean checkDB() {
        boolean result = true;
        try (PreparedStatement statement = conn.prepareStatement(settings.getValue("checkDB"))) {
            if (statement.executeQuery().next()) {
                result = false;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Create Data base.
     */
    private void createDB() {
        try (PreparedStatement statement = conn.prepareStatement(settings.getValue("createDB"))) {
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Check create table.
     * @return true if not table.
     */
    private boolean checkTable() {
        try {
            conn = DriverManager.getConnection(prs.getProperty("trackerUrl"), prs.getProperty("userName"), prs.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean result = true;
        try (PreparedStatement statement = conn.prepareStatement(settings.getValue("checkTable"))) {
            if (statement.executeQuery().next()) {
                result = false;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Create table.
     */
    private void createTable() {
        try {
            conn = DriverManager.getConnection(prs.getProperty("trackerUrl"), prs.getProperty("userName"), prs.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement statement = conn.prepareStatement(settings.getValue("createTable"))) {
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Loads properties.
     */
    private void loadProperties() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(propertiesFile)) {
            this.prs.load(in);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void close() throws Exception {
        this.conn.close();
    }
}