create table envs (
	id int primary key auto_increment
	, createdon datetime not null default current_timestamp
	, name varchar (256) not null
	, props text null
);
insert into envs(id, name, props)
	values(1, 'zemian_labtop_pc'
		, '{"hostname": "ZEDENG-US"
			, "os": "Windows 7 Service Pack 1 64 bits"
			, "processor": "Intel Core i5-3340M @ 2.70GHz"
			, "memory": "8GB"}');

create table tests (
	id int primary key auto_increment
	, createdon datetime not null default current_timestamp
	, envid int not null
	, name varchar (256) not null
	, description text null
	
	, foreign key (envid) references envs(id)
);
insert into tests(envid, name, description) 
	values(1, 'hello_test', 'Just a test.');

create table testruns (
	id int primary key auto_increment
	, testid int not null
	, purpose varchar(256) not null
	, tester varchar(256) not null
	, startedon datetime null
	, stoppedon datetime null
	, status varchar(32) null
	, request text null
	, response text null
	
	, foreign key (testid) references tests(id)
);
insert into testruns(testid, tester, startedon, stoppedon, status) 
	values
	(1, 'sample_test', 'zemian.deng@oracle.com', now() + 0, now() + 1, 'PASSED')
	, (1, 'sample_test', 'zemian.deng@oracle.com', now() + 1, now() + 2, 'PASSED')
	, (1, 'sample_test', 'zemian.deng@oracle.com', now() + 2, now() + 1, 'FAILED')
	, (1, 'sample_test', 'zemian.deng@oracle.com', now() + 3, now() + 2, 'PASSED')
	, (1, 'sample_test', 'zemian.deng@oracle.com', now() + 4, now() + 1, 'PASSED');
	