= JPA data and service development guideline

- Define POJO entity models in "data" package.
    * These should be a plain Java object that map to your database table.
    * Use JPA annotation to map the ORM layer.
- Define DAO classes in "dao" package.
    * These classes should deal with POJO entity and related queries together to
      fetch, modify and persist entities. Use the base JpaDao class to support your own
      implementation. 
    * A single DAO should deal with one entity as its focus.
    * You might not need to define one to one DAO to entity if all you need is
      simple create/modify and delete operation. In this case just use the JpaDao
      directly.
    * Do not add business logic into DAO level.
- Define SERVICE classes in "service" package.
    * These should be the business logic service that access the  DAO layer. You
      might need to combine several DAO along with other application services to provide
      the full business functionality.
    * Do not use JPA EntityManager directly in business layer.

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
        