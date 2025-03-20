INSERT INTO custom_user (id, username, password, roles)
VALUES ('1', 'admin', '$2a$12$jTnes1caIpsklaV73dMjhud.pUa.zVn7azAA/cgKrv.VI1UUij6Fu', 'USER,ADMIN');


INSERT INTO custom_user (id, username, password, roles)
VALUES ('2', 'admin2', '$2a$12$jTnes1caIpsklaV73dMjhud.pUa.zVn7azAA/cgKrv.VI1UUij6Fu', 'USER,ADMIN');

INSERT INTO custom_user (id, username, password, roles)
VALUES ('3', 'user3', '$2a$12$jTnes1caIpsklaV73dMjhud.pUa.zVn7azAA/cgKrv.VI1UUij6Fu', 'USER,ADMIN');


-- Insert Categories
INSERT INTO category (id, name) VALUES
                                    ('1', 'Electronics'),
                                    ('2', 'Clothing'),
                                    ('3', 'Home Appliances');

INSERT INTO product (id, name, description, price, quantity, brand) VALUES
                                                                        (1, 'Wireless Noise Cancelling Headphones', 'Over-ear headphones with active noise cancellation and Bluetooth connectivity', 12000, 6, 'Sony'),
                                                                        (2, 'Smartwatch with Fitness Tracker', 'Feature-rich smartwatch with heart rate monitor, GPS, and sleep tracking', 5000, 12, 'Amazfit'),
                                                                        (3, 'Gaming Laptop 15.6-inch', 'Powerful gaming laptop with high-refresh-rate display and dedicated GPU', 56000, 4, 'Asus'),
                                                                        (4, '4K Ultra HD Smart TV 55-inch', '55-inch Smart TV with 4K resolution, HDR, and voice assistant support', 42000, 7, 'Samsung'),
                                                                        (5, 'Men''s Cotton T-Shirt', 'Comfortable and breathable cotton t-shirt with a classic fit', 799, 10, 'Nike');

-- Insert Orders
INSERT INTO orders (id, quantity, order_date, total_price, user_id) VALUES
    ('1', 2, CURRENT_DATE, 24000, '1'),
    ('2', 1, CURRENT_DATE, 5000, '1');

-- Insert Ordered Products
INSERT INTO ordered_product (id, product_id, name, description, price, quantity, brand) VALUES
    ('1', '1', 'Wireless Noise Cancelling Headphones', 'Over-ear headphones with active noise cancellation and Bluetooth connectivity', 12000, 2, 'Sony'),
    ('2', '2', 'Smartwatch with Fitness Tracker', 'Feature-rich smartwatch with heart rate monitor, GPS, and sleep tracking', 5000, 1, 'Amazfit');
