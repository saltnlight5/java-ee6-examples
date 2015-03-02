/*
 *  Zemian Deng 2014
 */
package zemian.service.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public interface Action {
        public void doWork(JdbcTemplate jdbc);
    }

    public interface ResultSetAction {

        /**
         * Return false to not continue to next result set.
         */
        public boolean doWork(ResultSet rs) throws SQLException;
    }

    private Connection conn;

    public JdbcTemplate(Connection conn) {
        this.conn = conn;
    }

    public JdbcTemplate(String url, String user, String password) {
        try {
            this.conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> query(String sql) {
        final List<Object[]> result = new ArrayList<Object[]>();
        query(sql, new ResultSetAction() {
            @Override
            public boolean doWork(ResultSet rs) throws SQLException {
                int colCount = rs.getMetaData().getColumnCount();
                Object[] row = new Object[colCount];
                for (int i = 1; i <= colCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                result.add(row);
                return true;
            }
        });
        return result;
    }

    public Object[] querySingleRow(String sql) {
        final List<Object[]> result = new ArrayList<Object[]>();
        query(sql, new ResultSetAction() {
            @Override
            public boolean doWork(ResultSet rs) throws SQLException {
                int colCount = rs.getMetaData().getColumnCount();
                Object[] row = new Object[colCount];
                for (int i = 1; i <= colCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                result.add(row);
                return false;
            }
        });
        if (result.size() == 0) {
            throw new RuntimeException("No single result found.");
        }
        return result.get(0);
    }

    public void query(String sql, ResultSetAction action) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                action.doWork(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void print(List<Object[]> rows) {
        for (Object[] row : rows) {
            print(row);
        }
    }

    public void print(Object[] row) {
        for (Object col : row) {
            System.out.print(col);
            System.out.print(' ');
        }
        System.out.println();
    }

    /**
     * Perform a one time JdbcTemplate action with a new Connection and then close
 it.
     */
    public static void withJdbcUtils(String url, String user, String password, Action action) {
        JdbcTemplate jdbc = new JdbcTemplate(url, user, password);
        try {
            action.doWork(jdbc);
        } finally {
            jdbc.close();
        }
    }
}
