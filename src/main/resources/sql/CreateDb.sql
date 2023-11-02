DROP database logistics;

CREATE DATABASE IF NOT EXISTS logistics CHARACTER SET utf8 COLLATE utf8_general_ci;
USE logistics;

CREATE TABLE logistics.authentication_info (
id INT,
login VARCHAR(40) NOT NULL,
password VARCHAR(40) NOT NULL,
PRIMARY KEY id_pk (id),
UNIQUE KEY login_uk (login)
);

CREATE TABLE logistics.trucks (
number VARCHAR(7) NOT NULL,
model VARCHAR(30) NOT NULL,
capacity DECIMAL(3,1) NOT NULL,
total_operating_time INT,
technical_condition ENUM('OK','NOK') DEFAULT 'OK',
busy ENUM('YES','NO') DEFAULT 'NO',
current_city VARCHAR(30) NOT NULL,
current_state VARCHAR(30) NOT NULL,
PRIMARY KEY number_pk (number)
);

CREATE TABLE logistics.customers (
customer_id INT NOT NULL AUTO_INCREMENT,
customer_authentication_id INT NOT NULL UNIQUE,
customer_name VARCHAR(50) NOT NULL,
phone VARCHAR(25) NOT NULL,
email VARCHAR(50) NOT NULL,
PRIMARY KEY customer_id_pk (customer_id),
FOREIGN KEY authentication_customer_fk (customer_authentication_id) REFERENCES logistics.authentication_info(id),
UNIQUE KEY phone_uk (phone),
UNIQUE KEY email_uk (email)
)
AUTO_INCREMENT = 5001;

CREATE TABLE logistics.orders (
order_id INT NOT NULL AUTO_INCREMENT,
order_customer_id INT,
category VARCHAR(40) NOT NULL,
weight DECIMAL(3,1) NOT NULL,
status ENUM('NEW','EXPECT_DRIVERS_CONFIRMATION','CONFIRMED','DECLINED_BY_DRIVERS','ON_ROAD','COMPLETED') DEFAULT 'NEW',
start_date_time DATETIME,
limit_date_time DATETIME NOT NULL,
assigned_truck_number VARCHAR(7),
driver_comment VARCHAR(120),
PRIMARY KEY order_id_pk (order_id),
FOREIGN KEY truck_number_fk (assigned_truck_number) REFERENCES logistics.trucks(number),
FOREIGN KEY order_customer_id_fk (order_customer_id) REFERENCES logistics.customers(customer_id)
)
AUTO_INCREMENT = 12001;

CREATE TABLE logistics.cargos (
cargo_id INT NOT NULL AUTO_INCREMENT,
order_for_cargo_id INT,
cargo_name VARCHAR(40) NOT NULL,
weight DECIMAL(3,1) NOT NULL,
start_city VARCHAR(30) NOT NULL,
start_state VARCHAR(30) NOT NULL,
start_address VARCHAR(50) NOT NULL,
loaded ENUM('YES','NO') DEFAULT 'NO',
final_city VARCHAR(30) NOT NULL,
final_state VARCHAR(30) NOT NULL,
final_address VARCHAR(50) NOT NULL,
unloaded ENUM('YES','NO') DEFAULT 'NO',
expected_completion_date_time DATETIME,
real_completion_date_time DATETIME,
PRIMARY KEY cargo_id_pk (cargo_id),
FOREIGN KEY order_id_fk (order_for_cargo_id) REFERENCES logistics.orders(order_id)
)
AUTO_INCREMENT = 1001;

CREATE TABLE logistics.drivers (
personal_number INT NOT NULL AUTO_INCREMENT,
driver_authentication_id INT NOT NULL UNIQUE,
name VARCHAR(35) NOT NULL,
surname VARCHAR(35) NOT NULL,
work_experience INT,
working_hours_in_current_month INT,
status ENUM('REST','DRIVING') DEFAULT 'REST',
busy ENUM('YES','NO') DEFAULT 'NO',
current_city VARCHAR(30) NOT NULL,
current_state VARCHAR(30) NOT NULL,
current_truck_number VARCHAR(7),
current_order_id INT,
order_acceptance ENUM('YES','NO'),
CONSTRAINT chk_working_hours CHECK (working_hours_in_current_month BETWEEN 0 AND 176),
PRIMARY KEY personal_number_pk (personal_number),
FOREIGN KEY authentication_driver_fk (driver_authentication_id) REFERENCES logistics.authentication_info(id),
FOREIGN KEY current_truck_number_fk (current_truck_number) REFERENCES logistics.trucks(number),
FOREIGN KEY current_order_id_fk (current_order_id) REFERENCES logistics.orders(order_id)
)
AUTO_INCREMENT = 153001;

CREATE TABLE logistics.logisticians (
personal_number INT NOT NULL AUTO_INCREMENT,
logistician_authentication_id INT NOT NULL UNIQUE,
name VARCHAR(35) NOT NULL,
surname VARCHAR(35) NOT NULL,
PRIMARY KEY personal_number_pk (personal_number),
FOREIGN KEY authentication_logistician_fk (logistician_authentication_id) REFERENCES logistics.authentication_info(id)
)
AUTO_INCREMENT = 1201;