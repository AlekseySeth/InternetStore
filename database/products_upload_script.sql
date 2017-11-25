USE nutrition_store;

INSERT INTO categories(name, description) VALUES ('Protein', 'Protein description');
INSERT INTO categories(id, name, parent_id) VALUES (10, 'Biotech', 1);

INSERT INTO products(name, description, price, qty, category_id) VALUES ('Protein Power Biotech USA 1000', 'Some description for Protein Power Biotech', 25.99, 10, 10);
INSERT INTO products(name, description, price, qty, category_id) VALUES ('Iso Whey Zero Biotech USA 908', 'Some description for Iso Whey Zero Biotech', 45.99, 10, 10);
INSERT INTO products(name, description, price, qty, category_id) VALUES ('Biotech Iso Whey Zero lact free 500g', 'Some description for Iso Whey Zero lact free', 24.99, 10, 10);
