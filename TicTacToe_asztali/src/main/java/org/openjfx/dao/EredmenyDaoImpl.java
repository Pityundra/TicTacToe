package org.openjfx.dao;

import java.sql.*;

public class EredmenyDaoImpl implements EredmenyDAO {
    private final static String DB_STRING = DBConfig.DB_CONN_STR;
    private static final String CREATE_RESULTS = "CREATE TABLE IF NOT EXISTS Results (" +
            "match_id integer PRIMARY KEY AUTOINCREMENT," +
            "turns text NOT NULL);";
    private static final String INSERT_RESULT = "INSERT INTO Results (turns) VALUES " +
            "(?);";

    public void initializeTables() {
        try (Connection conn = DriverManager.getConnection(DB_STRING); Statement st = conn.createStatement()) {
            st.executeUpdate(CREATE_RESULTS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public EredmenyDaoImpl() {
        initializeTables();
    }

    @Override
    public boolean addResult(String result) {
        try (Connection conn = DriverManager.getConnection(DB_STRING); PreparedStatement st = conn.prepareStatement(INSERT_RESULT)) {
            st.setString(1, result);
            int res = st.executeUpdate();
            if (res == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
