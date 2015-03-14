import zemian.service.util.*
JdbcTemplate.withJdbc("jdbc:mysql://localhost/test", "root", "root123") { jdbc ->
	println('MySQL Server Version: ' + jdbc.queryObject('select version()'))
}