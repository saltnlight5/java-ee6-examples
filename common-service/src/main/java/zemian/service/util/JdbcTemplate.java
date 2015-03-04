/*
 *  Zemian Deng 2014
 */
package zemian.service.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcTemplate {

    public interface JdbcAction {
        public void doWork(JdbcTemplate jdbc) throws Exception;
    }

    public interface PreparedStmtAction {
        public void doWork(PreparedStatement stmt) throws Exception;
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

    public Connection getConnnection() {
        return conn;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void withPreparedStmt(String sql, Object[] params, PreparedStmtAction action) {
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (params != null && params.length > 0) {
                int i = 1;
                for (Object param : params) {
                    stmt.setObject(i++, param);
                }
            }
            action.doWork(stmt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }        
    }

    public int update(String sql, Object ... params) {
        final Integer[] resultWrapper = new Integer[1];
        withPreparedStmt(sql, params, new PreparedStmtAction() {
            @Override
            public void doWork(PreparedStatement stmt) throws SQLException {
                Integer result = stmt.executeUpdate();
                resultWrapper[0] = result;
            }
        });
        return resultWrapper[0];
    }

    public List<Object[]> queryRowList(String sql, Object ... params) {
        final List<Object[]> result = new ArrayList<>();
        withPreparedStmt(sql, params, new PreparedStmtAction() {
            @Override
            public void doWork(PreparedStatement stmt) throws SQLException {
                try (ResultSet rs = stmt.executeQuery()) {
                    int colCount = rs.getMetaData().getColumnCount();
                    while (rs.next()) {
                        Object[] row = new Object[colCount];
                        for (int i = 1; i <= colCount; i++) {
                            row[i - 1] = rs.getObject(i);
                        }
                        result.add(row);
                    }
                }
            }
        });
        return result;
    }

    public Object[] queryRow(String sql, Object ... params) {
        final Object[][] result = new Object[1][];
        withPreparedStmt(sql, params, new PreparedStmtAction() {
            @Override
            public void doWork(PreparedStatement stmt) throws SQLException {
                try (ResultSet rs = stmt.executeQuery()) {
                    int colCount = rs.getMetaData().getColumnCount();
                    if (rs.next()) {
                        result[0] = new Object[colCount];
                        for (int i = 1; i <= colCount; i++) {
                            result[0][i - 1] = rs.getObject(i);
                        }
                    }
                }
            }
        });
        if (result[0] == null || result[0].length == 0) {
            throw new RuntimeException("No single row found.");
        }
        return result[0];
    }

    public Object queryObject(String sql, final Object ... params) {
        final Object[] result = new Object[1];
        withPreparedStmt(sql, params, new PreparedStmtAction() {
            @Override
            public void doWork(PreparedStatement stmt) throws SQLException {
                try (ResultSet rs = stmt.executeQuery()) {
                    int colCount = rs.getMetaData().getColumnCount();
                    if (rs.next() && colCount > 0) {
                        result[0] = rs.getObject(1);
                    }
                }
            }
        });
        if (result[0] == null) {
            throw new RuntimeException("No single result found.");
        }
        return result[0];
    }

    public void print(ResultSet rs) {
        try {
            int colCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= colCount; i++) {
                Object col = rs.getObject(i);
                System.out.print(col);
                System.out.print(' ');
            }
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to print ResultSet", e);
        }
    }

    /**
     * Perform a one time JdbcTemplate action with a new Connection and then
     * close it.
     */
    public static void withJdbc(String url, String user, String password, JdbcAction action) {
        JdbcTemplate jdbc = new JdbcTemplate(url, user, password);
        try {
            action.doWork(jdbc);
        } catch (Exception e) {
            throw new RuntimeException("Failed to perfom Jdbc action.", e);
        } finally {
            jdbc.close();
        }
    }
}
