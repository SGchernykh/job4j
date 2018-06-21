package ru.job4j.parser;

/**
 * DataBaseJOB.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Set;

public class DataBaseJOB implements AutoCloseable {
    private static final Logger LOGGER = Logger.getLogger(ParserJOB.class);
    private final Settings settings;
    private Connection conn = null;

    /**
     * Constructor.
     * @param propertiesFile name properties Files.
     */
    public DataBaseJOB(String propertiesFile) {
        this.settings = new Settings(propertiesFile);
        connectToDb();
    }

    /**
     * Add vacancy in Data Base.
     * A unique job is with different authors and description.
     * @param set Set Vacancy.
     * @throws SQLException
     */
    public void addVacancy(final Set<Vacancy> set) throws SQLException {
        conn.setAutoCommit(false);
        try (PreparedStatement st = this.conn.prepareStatement(this.settings.getValue("insert"))) {
            for (Vacancy vacancy : set) {
                st.setString(1, vacancy.getTitle());
                st.setString(2, vacancy.getDescription());
                st.setString(3, vacancy.getAuthor());
                st.setTimestamp(4, vacancy.getCreateDate());
                st.executeUpdate();
                LOGGER.info(vacancy);
            }
            this.conn.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            this.conn.rollback();
        }
    }

    /**
     * Get Last Vacancy Date.
     * @return LastVacancyDate
     */
    public Timestamp getLastVacancyDate() {
        Timestamp lastVacancyDate = null;
        try (Statement st = this.conn.createStatement()) {
            try (ResultSet rs = st.executeQuery(this.settings.getValue("maxData"))) {
                if (rs != null) {
                    while (rs.next()) {
                        lastVacancyDate = rs.getTimestamp("max");
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return lastVacancyDate;
    }

    /**
     * Connect to data base.
     */
    public void connectToDb() {
        try {
            this.conn = DriverManager.getConnection(this.settings.getValue("url"), this.settings.getValue("userName"), this.settings.getValue("password"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        if (this.checkDB()) {
            this.createDB();
            this.createTable();
            LOGGER.info("Created database and tables!");
        } else {
            if (this.checkTable()) {
                this.createTable();
                LOGGER.info("Created tables!");
            }
        }
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
            conn = DriverManager.getConnection(this.settings.getValue("jobUrl"), this.settings.getValue("userName"), this.settings.getValue("password"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
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
            conn = DriverManager.getConnection(this.settings.getValue("jobUrl"), this.settings.getValue("userName"), this.settings.getValue("password"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        try (PreparedStatement statement = conn.prepareStatement(settings.getValue("createTable"))) {
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void close() throws Exception {
        this.conn.close();
    }
}
