CREATE DATABASE nutrition_store;
USE nutrition_store;

CREATE TABLE roles (
  id INT,
  name VARCHAR(10),
  PRIMARY KEY(id));

INSERT INTO roles (id, name) VALUES (1, 'Admin');
INSERT INTO roles (id, name) VALUES (2, 'Marketer');
INSERT INTO roles (id, name) VALUES (3, 'Customer');
# INSERT INTO roles (id, name) VALUES (4, 'VIP');

CREATE TABLE users (
  id INT AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50),
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(50) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  address VARCHAR(100) NOT NULL,
  registration_date TIMESTAMP,
  role_id INT,
  #ref_code VARCHAR(8) UNIQUE,
  PRIMARY KEY(id),
  FOREIGN KEY (role_id) REFERENCES roles(id)) AUTO_INCREMENT=1000;

INSERT INTO users (id, first_name, email, password, phone, address, registration_date, role_id)
  VALUES (1, 'Admin', 'Admin', 'JaS3MecSfP8f23L0DfTeuBV+AvtCpVcC8ybqb9XVjME=', 'admin@sportpit.by', 'admin@sportpit.by', 2017-12-01, 1);
INSERT INTO users (id, first_name, email, password, phone, address, registration_date, role_id)
  VALUES (2, 'Marketer', 'Marketer', 'SOEXTRf5PxsZi4fm4QD5pxiTuMpWF5YUZ4+Ll1YUMes=', 'marketer@sportpit.by', 'marketer@sportpit.by', 2017-12-01, 2);

CREATE TABLE categories (
  id INT AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  description TEXT,
  parent_id INT,
  PRIMARY KEY(id),
  FOREIGN KEY(parent_id) REFERENCES categories(id));

CREATE TABLE products (
  id INT AUTO_INCREMENT,
  name VARCHAR(100) UNICODE NOT NULL,
  description TEXT,
  price DOUBLE NOT NULL,
  qty INT NOT NULL,
  category_id INT,
  image_url VARCHAR(100) DEFAULT '/images/default.gif',
  PRIMARY KEY(id),
  FOREIGN KEY(category_id) REFERENCES categories(id)) AUTO_INCREMENT=1000;
 
 CREATE TABLE deliveries (
  id INT AUTO_INCREMENT,
  name VARCHAR(30) UNIQUE NOT NULL,
  cost DOUBLE NOT NULL,
  PRIMARY KEY(id));

INSERT INTO deliveries (name, cost) VALUES ('Local Pickup', 0.0);
INSERT INTO deliveries (name, cost) VALUES ('Standard', 3.50);
INSERT INTO deliveries (name, cost) VALUES ('Premium', 5.00);

CREATE TABLE statuses (
  id INT AUTO_INCREMENT,
  name VARCHAR(15) UNIQUE NOT NULL,
  PRIMARY KEY(id));
  
INSERT INTO statuses (name) VALUES ('Open');
INSERT INTO statuses (name) VALUES ('In Progress');
INSERT INTO statuses (name) VALUES ('Completed');
INSERT INTO statuses (name) VALUES ('Closed');
  
CREATE TABLE orders (
  id INT AUTO_INCREMENT,
  status_id INT NOT NULL DEFAULT 1,
  total_price DOUBLE NOT NULL,
  delivery_id INT NOT NULL,
  open_date TIMESTAMP,
  close_date TIMESTAMP,
  PRIMARY KEY(id),
  FOREIGN KEY(delivery_id) REFERENCES deliveries(id),
  FOREIGN KEY(status_id) REFERENCES statuses(id)) AUTO_INCREMENT=1000;
 
 CREATE TABLE orders_products (
  order_id INT,
  product_id INT,
  product_qty INT NOT NULL,
  PRIMARY KEY(order_id, product_id),
  FOREIGN KEY(order_id) REFERENCES orders(id),
  FOREIGN KEY(product_id) REFERENCES products(id));

CREATE TABLE users_orders (
  user_id INT,
  order_id INT,
  PRIMARY KEY(user_id, order_id),
  FOREIGN KEY(user_id) REFERENCES users(id),
  FOREIGN KEY(order_id) REFERENCES orders(id));

#DROP DATABASE nutrition_store;