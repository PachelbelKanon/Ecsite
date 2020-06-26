USE ecsite;

INSERT INTO user(user_name, password, full_name) VALUES('taro', 'taropw', 'Taro Yamada');
INSERT INTO user(user_name, password, full_name) VALUES('jiro', 'jiropw', 'Jiro Tanaka');
INSERT INTO user(user_name, password, full_name) VALUES('ichiko', 'ichikopw', 'Ichiko Hayashi');
INSERT INTO user(user_name, password, full_name, is_admin) VALUES('admin', 'admin', '管理者 admin', 1);

INSERT INTO goods(goods_name, price) VALUES('Tシャツ', 1000);
INSERT INTO goods(goods_name, price) VALUES('ジャケット', 10000);
INSERT INTO goods(goods_name, price) VALUES('スニーカー', 5000);
