CREATE TABLE IF NOT EXISTS client(
  id SERIAL PRIMARY KEY,
    nui VARCHAR(13) UNIQUE,
    fullname VARCHAR(200),
    address VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS invoice(
  id SERIAL PRIMARY KEY,
    code VARCHAR(30) UNIQUE,
    create_at DATE,
    total INT,
    cliente_id SERIAL,
    FOREIGN KEY (cliente_id) REFERENCES client(id)
);

CREATE TABLE IF NOT EXISTS product(
  id SERIAL PRIMARY KEY,
    descripction VARCHAR(100),
    brand VARCHAR(30),
    price DECIMAL(10, 2),
    stock int
);

CREATE TABLE IF NOT EXISTS detail(
  id SERIAL PRIMARY KEY,
    quantity int,
    price DECIMAL(10,2),
    invoice_id SERIAL,
    product_id SERIAL,
    FOREIGN KEY (invoice_id) REFERENCES invoice(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);
