INSERT INTO categories (name, description)
VALUES ('ELECTRONICS', 'Tech products'),
       ('BOOKS', 'Books and magazines'),
       ('CLOTHING', 'Men and women clothing'),
       ('HOME', 'Home and kitchen'),
       ('SPORTS', 'Sports and outdoors');

INSERT INTO products (name, description, price, stock_quantity, category_id)
VALUES ('Laptop', 'Gaming laptop', 1200.00, 10, (SELECT id FROM categories WHERE name = 'ELECTRONICS')),
       ('Smartphone', '6.5 inch smartphone', 799.00, 25, (SELECT id FROM categories WHERE name = 'ELECTRONICS')),
       ('Headphones', 'Wireless headphones', 149.90, 40, (SELECT id FROM categories WHERE name = 'ELECTRONICS')),
       ('Clean Code', 'Robert C. Martin', 35.00, 30, (SELECT id FROM categories WHERE name = 'BOOKS')),
       ('Spring in Action', 'Spring framework guide', 45.50, 20, (SELECT id FROM categories WHERE name = 'BOOKS')),
       ('T-Shirt', 'Cotton t-shirt', 15.00, 100, (SELECT id FROM categories WHERE name = 'CLOTHING')),
       ('Jeans', 'Slim fit jeans', 49.99, 60, (SELECT id FROM categories WHERE name = 'CLOTHING')),
       ('Coffee Maker', 'Automatic coffee machine', 89.00, 15, (SELECT id FROM categories WHERE name = 'HOME')),
       ('Football', 'Size 5 football', 25.00, 50, (SELECT id FROM categories WHERE name = 'SPORTS'));