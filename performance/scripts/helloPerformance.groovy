import zemian.service.util.*
JdbcTemplate.withJdbc("jdbc:mysql://localhost/test", "root", "root123") { jdbc ->
	jdbc.eachResultSet('select version()') { rs -> 
		println(rs) 
		return true
	}
			
}