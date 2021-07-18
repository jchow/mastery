DROP TABLE IF EXISTS products;

CREATE TABLE products (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  category VARCHAR(250) NOT NULL,
  sub_category VARCHAR(250) NOT NULL,
  quantity INT NOT NULL
);