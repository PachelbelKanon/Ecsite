USE ecsite;

CREATE TABLE IF NOT EXISTS user (
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_name VARCHAR(25) NOT NULL,
  password VARCHAR(25) NOT NULL,
  full_name VARCHAR(50) NOT NULL,
  is_admin TINYINT(1) NOT NULL DEFAULT 0
);
ecsite;
CREATE TABLE IF NOT EXISTS goods (
 id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
 goods_name VARCHAR(255) NOT NULL,
 price INT(11) DEFAULT 0,
 updated_at TIMESTAMP NOT NULL DEFAULT now() ON UPDATE now()
);

CREATE TABLE IF NOT EXISTS purchase (
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_id INT(11) NOT NULL,
  goods_id INT(11) NOT NULL,
  goods_name VARCHAR(255) NOT NULL,
  item_count INT(11) DEFAULT 0,
  total INT(11) DEFAULT 0,
  created_at DATETIME NOT NULL
);
