CREATE DATABASE nutrition_store;
USE nutrition_store;

CREATE TABLE roles (
  id INT,
  name VARCHAR(15),
  PRIMARY KEY(id));

INSERT INTO roles (id, name) VALUES (0, 'Гость');
INSERT INTO roles (id, name) VALUES (1, 'Администратор');
INSERT INTO roles (id, name) VALUES (2, 'Маркетолог');
INSERT INTO roles (id, name) VALUES (3, 'Покупатель');

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
  VALUES (1, 'Admin', 'Admin', 'JaS3MecSfP8f23L0DfTeuBV+AvtCpVcC8ybqb9XVjME=', 'admin@sportpit.by', 'admin@sportpit.by', '2017-12-01', 1);
INSERT INTO users (id, first_name, email, password, phone, address, registration_date, role_id)
  VALUES (2, 'Marketer', 'Marketer', 'SOEXTRf5PxsZi4fm4QD5pxiTuMpWF5YUZ4+Ll1YUMes=', 'marketer@sportpit.by', 'marketer@sportpit.by', '2017-12-01', 2);

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
  image_url VARCHAR(100) DEFAULT '/images/default.png',
  PRIMARY KEY(id),
  FOREIGN KEY(category_id) REFERENCES categories(id)) AUTO_INCREMENT=1000;
 
 CREATE TABLE deliveries (
  id INT AUTO_INCREMENT,
  name VARCHAR(30) UNIQUE NOT NULL,
  cost DOUBLE NOT NULL,
  PRIMARY KEY(id));

INSERT INTO deliveries (name, cost) VALUES ('Самовывоз', 0.0);
INSERT INTO deliveries (name, cost) VALUES ('Стандартная доставка', 3.50);
INSERT INTO deliveries (name, cost) VALUES ('Экспресс доставка', 5.00);

CREATE TABLE statuses (
  id INT AUTO_INCREMENT,
  name VARCHAR(15) UNIQUE NOT NULL,
  PRIMARY KEY(id));
  
INSERT INTO statuses (name) VALUES ('Открыт');
INSERT INTO statuses (name) VALUES ('В обработке');
INSERT INTO statuses (name) VALUES ('Доставлен');
INSERT INTO statuses (name) VALUES ('Отменен');
  
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

CREATE TABLE pages (
  id INT AUTO_INCREMENT,
  url VARCHAR(50),
  PRIMARY KEY (id));

CREATE TABLE roles_pages (
  role_id INT,
  page_id INT,
  FOREIGN KEY (role_id) REFERENCES roles(id),
  FOREIGN KEY (page_id) REFERENCES pages(id));

INSERT INTO pages (url) VALUES ('/');
INSERT INTO pages (url) VALUES ('/admin');
INSERT INTO pages (url) VALUES ('/cart');
INSERT INTO pages (url) VALUES ('/download-order');
INSERT INTO pages (url) VALUES ('/login');
INSERT INTO pages (url) VALUES ('/log-out');
INSERT INTO pages (url) VALUES ('/marketer');
INSERT INTO pages (url) VALUES ('/my-account');
INSERT INTO pages (url) VALUES ('/order');
INSERT INTO pages (url) VALUES ('/order-placed');
INSERT INTO pages (url) VALUES ('/orders-list');
INSERT INTO pages (url) VALUES ('/products-list');
INSERT INTO pages (url) VALUES ('/registration');
INSERT INTO pages (url) VALUES ('/update-order');
INSERT INTO pages (url) VALUES ('/update-password');
INSERT INTO pages (url) VALUES ('/update-product');
INSERT INTO pages (url) VALUES ('/update-profile');
INSERT INTO pages (url) VALUES ('/update-user');
INSERT INTO pages (url) VALUES ('/user');
INSERT INTO pages (url) VALUES ('/users-list');



INSERT INTO roles_pages (role_id, page_id) VALUES (0, 0);

INSERT INTO roles_pages (role_id, page_id) VALUES (1, 4);

INSERT INTO roles_pages (role_id, page_id) VALUES (2, 2);

INSERT INTO roles_pages (role_id, page_id) VALUES (3, 2);

#DROP DATABASE nutrition_store;