create table category(id int AUTO_INCREMENT,
name VARCHAR(50), 
description VARCHAR(50),
image_url VARCHAR(50),
is_active BOOLEAN,
CONSTRAINT pk_category_id PRIMARY KEY (id));



create table user_detail(id int AUTO_INCREMENT,
first_name varchar(50),
last_name varchar(50),
role varchar(50),
enabled boolean,
password varchar(50),
email varchar(50),
contact_number varchar(15),
constraint pk_user_id primary key(id)
);

create table product( 
id int AUTO_INCREMENT,
code varchar(50),
name varchar(50),
brand varchar(50),
description varchar(255),
unit_price decimal(10,2),
quantity int,
is_active boolean,
category_id int,
supplier_id int,
purchases int default 0,
views int  default 0,
constraint pk_product_id primary key(id),
constraint fk_product_category_id foreign key(category_id) references category(id),
constraint fk_product_supplier_id foreign key(supplier_id) references user_detail(id)
);

create table address(
id int AUTO_INCREMENT,
user_id int,
address_line_one varchar(100),
address_line_two varchar(100),
city varchar(20),
state varchar(20),
country varchar(20),
postal_code varchar(20),
is_billing boolean,
is_shipping boolean,
constraint fk_address_user_id foreign key(user_id) references user_detail(id),
constraint pk_address_id primary key(id)
);

create table cart(
id int AUTO_INCREMENT,
user_id int,
grand_total decimal(10,2),
cart_line int,
constraint fk_cart_user_id foreign key(user_id) references user_detail(id),
constraint pk_cart_user_id primary key(id)
);

create table cart_line(
id int AUTO_INCREMENT,
cart_id int,
total decimal(10,2),
product_id int,
product_count int,
buying_price decimal(10,2),
is_available boolean,
constraint fk_cartline_product_id foreign key(product_id) references product (id),
constraint pk_cartline_id primary key(id)
);

create table order_detail(
id int AUTO_INCREMENT,
user_id int,
order_total decimal(10,2),
order_count int,
shipping_id int,
billing_id int,
order_date date,
constraint fk_order_detail_user_id foreign key(user_id) references user_detail(id),
constraint fk_order_detail_shipping_id foreign key(shipping_id) references address(id),
constraint fk_order_detail_billing_id foreign key(billing_id) references address(id),
constraint pk_order_detail_id primary key(id)
);

create table order_item(
id int AUTO_INCREMENT,
order_id int,
total decimal(10,2),
product_id int,
product_count int,
buying_price decimal(10,2),
constraint fk_order_item_product_id foreign key(product_id) references product(id),
constraint fk_order_item_order_id foreign key(order_id) references order_detail(id),
constraint pk_order_item_id primary key(id)
);


INSERT INTO category (name, description,image_url,is_active) VALUES ('Laptop', 'This is description for Laptop category!', 'CAT_1.png', true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Television', 'This is description for Television category!', 'CAT_2.png', true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Mobile', 'This is description for Mobile category!', 'CAT_3.png', true);
 
INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Vikram', 'Kumar', 'ADMIN', true, 'PGXv0mKyEvLnKP7SW', 'vk@gmail.com', '8888888888');
INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Ravi', 'Sundar', 'SUPPLIER', true, 'JR1Of5n95myFsu3hgUnm6', 'rs@gmail.com', '9999999999');
INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Ravichandra', 'Sharma', 'SUPPLIER', true, 'RlmNtL5xtgD6bcVNOK', 'rsh@gmail.com', '7777777777');
INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Tejasvi', 'Arya', 'USER', true, 'PpH1xVSdbie1k6Ni2jfjwwminq', 'ta@gmail.com', '7777777777');


INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDABC123DEFX', 'iPhone 5s', 'apple', 'This is one of the best phone available in the market right now!', 18000, 5, true, 3, 2, 0, 0 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDDEF123DEFX', 'Samsung s7', 'samsung', 'A smart phone by samsung!', 32000, 2, true, 3, 3, 0, 0 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDPQR123WGTX', 'Google Pixel', 'google', 'This is one of the best android smart phone available in the market right now!', 57000, 5, true, 3, 2, 0, 0 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDMNO123PQRX', ' Macbook Pro', 'apple', 'This is one of the best laptops available in the market right now!', 54000, 3, true, 1, 2, 0, 0 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDABCXYZDEFX', 'Dell Latitude E6510', 'dell', 'This is one of the best laptop series from dell that can be used!', 48000, 5, true, 1, 3, 0, 0 );

INSERT INTO address( user_id, address_line_one, address_line_two, city, state, country, postal_code, is_billing, is_shipping) 
VALUES (4, '102 Sabarmati Society, Mahatma Gandhi Road', 'Near Salt Lake, Gandhi Nagar', 'Ahmedabad', 'Gujarat', 'India', '111111', true, false );
 
INSERT INTO cart (user_id, grand_total, cart_lines) VALUES (4, 0, 0);
