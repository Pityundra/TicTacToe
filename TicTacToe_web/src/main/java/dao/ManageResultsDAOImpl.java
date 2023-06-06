package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManageResultsDAOImpl implements ManageResultsDAO {

    private final static String DB_STRING = DBConfig.DB_CONN_STR;
    private static final String INSERT_RESULT = "INSERT INTO Results (turns) VALUES " +
            "(?);";

    public ManageResultsDAOImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
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
