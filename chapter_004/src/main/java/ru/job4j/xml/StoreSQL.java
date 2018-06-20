package ru.job4j.xml;

/**
 * StoreSQL.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreSQL {
    private Connection connection = null;
    private final Settings st = new Settings();

    /**
     * Constructor.
     */
    public StoreSQL() {
        try {
            this.connection = DriverManager.getConnection(st.getValue("url"));
            if (checkTable()) {
                createTable();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    /**
     * Check existence Table.
     * @return True if not created table.
     */
    private boolean checkTable() {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(st.getValue("checkTable"))) {
            if (statement.executeQuery().next()) {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Create table.
     */
    private void createTable() {
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(st.getValue("createTable"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deleted and Add element in table .
     * @param n Count element.
     * @throws SQLException
     */
    public void generate(final int n) throws SQLException {
        connection.setAutoCommit(false);
        try (Statement statement = connection.createStatement()) {
            statement.execute(st.getValue("allDelete"));
            try (PreparedStatement statement1 = connection.prepareStatement(st.getValue("addNumber"))) {
                for (int i = 1; i <= n; i++) {
                    statement1.setInt(1, i);
                    statement1.executeUpdate();
                }
            }
            this.connection.commit();
        } catch (SQLException  e) {
            this.connection.rollback();
            e.printStackTrace();
        }
    }

    /**
     * Get list element.
     * @return List element.
     * @throws SQLException
     */
    public List<Integer> getList() throws SQLException {
        connection.setAutoCommit(false);
        List<Integer> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(st.getValue("getTable"))) {
                while (rs.next()) {
                    list.add(rs.getInt("field"));
                }
            }
            this.connection.commit();
        } catch (SQLException  e) {
            this.connection.rollback();
            e.printStackTrace();
        }
        return list;
    }
}
