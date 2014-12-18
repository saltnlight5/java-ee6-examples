CREATE TABLE useraccount (
    username VARCHAR(63) NOT NULL PRIMARY KEY,
    encryptedpassword VARCHAR(127) NOT NULL,
    firstname VARCHAR(127) NULL,
    lastname VARCHAR(127) NULL
);
