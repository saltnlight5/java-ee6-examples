= Glassfish

== MySQL setup.
1. Copy mysql-connector-java-5.1.30-bin.jar into $GF/domains/domain1/lib
2. Goto GF Admin Console and create the following
	JDBC Connection Pool: "jpa-example-pool" with MySQL DataSource
            When adding database properties, ensure you remove URL and Url keys and add the following
            (it might be more clean if you remove all default values and insert only these)
                ServerName=localhost
                DatabaseName=test
                User=root
                Password=root123
	JDBC Resource: "jdbc/jpa-example"
        