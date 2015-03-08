/*
 *  Zemian Deng 2014
 */

package zemian.service.util;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;
import org.junit.Test;

/**
 *
 * @author zedeng
 */
public class JdbcTemplateTest {
    
    // We do not want to enable this unless we have a database setup first.
    //@Test
    public void testMySql() {
        JdbcTemplate.withJdbc("jdbc:mysql://localhost", "root", "root123", new JdbcTemplate.JdbcAction() {
            @Override
            public void doWork(JdbcTemplate jdbc) {
                Object result = jdbc.queryObject("select version()");
                System.out.println("MySQL Server Version: " + result);
            }
        });
    }
    
    @Test
    public void testH2() {
        JdbcTemplate.withJdbc("jdbc:h2:~/java-ee6-examples-db", "", "", new JdbcTemplate.JdbcAction() {
            @Override
            public void doWork(JdbcTemplate jdbc) {
                Object result = jdbc.queryObject("select 1 + 1");
                System.out.println("1 + 1: " + result);
                
                int updateResult = jdbc.update("create table testdata(id int primary key, data varchar(1024))");
                System.out.println("create table: updateResult=" + updateResult);
                
                jdbc.update("insert into testdata(id, data) values(?,?)", 1, "test1");
                jdbc.update("insert into testdata(id, data) values(?,?)", 2, "test2");
                jdbc.update("insert into testdata(id, data) values(?,?)", 3, "test3");
                
                System.out.println("count: " + jdbc.queryObject("select count(*) from testdata"));
                
                jdbc.update("update testdata set data = ? where id = ?", "foo3", 3);
                List<Object[]> queryResult = jdbc.queryRowList("select * from testdata");
                System.out.println("Query all: ");
                for (Object[] row : queryResult)
                    System.out.println("row: " + Utils.toList(row));
                
                System.out.println("Query row: " + Utils.toList(jdbc.queryRow("select * from testdata where id=1")));
                System.out.println("Query object: " + jdbc.queryObject("select data from testdata where id=?", 1));
                
                jdbc.withPreparedStmt("insert into testdata(id, data) values(?, ?)", Utils.emptyArray(), new JdbcTemplate.PreparedStmtAction() {
                    @Override
                    public void doWork(PreparedStatement stmt) throws Exception {    
                        for (int i = 0; i < 1000; i++) {
                            stmt.setInt(1, 1000 + i);
                            stmt.setString(2, UUID.randomUUID().toString());
                            stmt.addBatch();
                            if (i % 25 == 24) {
                                stmt.executeBatch();
                                System.out.println("Insert by batch. row=" + i);
                            }
                        }
                        // ensure last batch is executed.
                        stmt.executeBatch();
                    }
                });
                System.out.println("count after batch insert: " + jdbc.queryObject("select count(*) from testdata"));
                
                updateResult = jdbc.update("drop table testdata");
                System.out.println("drop table: updateResult=" + updateResult);
            }
        });
    }
    
}
