CREATE USER 'sakila'@'localhost' IDENTIFIED BY 'sakila123';
GRANT ALL ON sakila.* TO 'sakila'@'localhost';
FLUSH privileges;
