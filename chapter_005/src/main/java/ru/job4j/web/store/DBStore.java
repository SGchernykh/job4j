package ru.job4j.web.store;

/**
 * DBStore.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.web.model.Role;
import ru.job4j.web.model.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBStore implements Store {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBStore.class);
    Settings settings = new Settings();
    private static final Store INSTANCE = new DBStore();
    private  BasicDataSource dataSource;

    /**
     * DBCP Connection Pooling, setting.
     * @return
     */
    private  BasicDataSource getDataSource() {
        if (dataSource == null) {
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl(settings.getValue("ServletUrl"));
            ds.setUsername(settings.getValue("userName"));
            ds.setPassword(settings.getValue("password"));
            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);
            dataSource = ds;
        }
        return dataSource;
    }

    /**
     * Constructor.
     */
    private DBStore() {
        try {
            this.connectToDb();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.getDataSource();
    }
    /**
     * Connects to database.
     */
    private void connectToDb() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
     * Check create data base.
     * @return true if not data base.
     */
    private boolean checkDB() {
        boolean result = true;
        try (Connection conn = DriverManager.getConnection(settings.getValue("url"), settings.getValue("userName"), settings.getValue("password"));
                PreparedStatement statement = conn.prepareStatement(settings.getValue("checkDB"))) {
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
        try (Connection conn = DriverManager.getConnection(settings.getValue("url"), settings.getValue("userName"), settings.getValue("password"));
                PreparedStatement statement = conn.prepareStatement(settings.getValue("createDB"))) {
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
        boolean result = true;
        try (Connection conn = DriverManager.getConnection(settings.getValue("ServletUrl"), settings.getValue("userName"), settings.getValue("password"));
                PreparedStatement statement = conn.prepareStatement(settings.getValue("checkTable"))) {
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
    private void createTable() throws SQLException {
        Connection conn = DriverManager.getConnection(settings.getValue("ServletUrl"), settings.getValue("userName"), settings.getValue("password"));
        try (Statement statement = conn.createStatement()) {
            conn.setAutoCommit(false);
            statement.execute(settings.getValue("createTableRole"));
            statement.execute(settings.getValue("createTableUser"));
            statement.executeUpdate(settings.getValue("roleAdmin"));
            statement.executeUpdate(settings.getValue("roleUser"));
            statement.executeUpdate(settings.getValue("userAdmin"));
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            conn.rollback();
            conn.close();
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Dispatch pattern.
     */
    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Users user) {
        try (Connection conn = this.dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(settings.getValue("add"))) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            ps.setInt(6, user.getRole().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(Users user) {
        try (Connection conn = this.dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(settings.getValue("updateAdmin"))) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setInt(5, user.getRole().getId());
            ps.setInt(6, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = this.dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(settings.getValue("delete"))) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Users> findAll() {
        ArrayList<Users> users = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection();
                Statement statement = conn.createStatement()) {
            try (ResultSet rs = statement.executeQuery(settings.getValue("getAll"))) {
                while (rs.next()) {
                    Users user = new Users(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("password"), rs.getString("email"), rs.getTimestamp("create_date"), new Role(Integer.parseInt(rs.getString("role_id")), rs.getString("role")));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public Users findById(int id) {
        Users user = null;
        try (Connection conn = this.dataSource.getConnection();
                PreparedStatement statement = conn.prepareStatement(settings.getValue("findById"))) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    user = new Users(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    public Users getUserByLoginAndPassword(final String login, final String password) {
        Users user = null;
        try (Connection con = this.dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(settings.getValue("selectUserWithLoginAndPass"))) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    user = new Users(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("password"), rs.getString("email"), rs.getTimestamp("create_date"), new Role(Integer.parseInt(rs.getString("role_id")), rs.getString("role")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public List<Role> roleAll() {
        List<Role> roles = new ArrayList<>();
        try (Connection con = this.dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(settings.getValue("roleAll"))) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    roles.add(new Role(resultSet.getInt("id"), resultSet.getString("role")));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return roles;
    }
}
