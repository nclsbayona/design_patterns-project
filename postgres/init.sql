-- Table creation

CREATE TABLE tblProvider ( 
    provider_id varchar primary key,
    provider_name varchar NOT NULL,
    provider_image varchar NOT NULL
);

CREATE TABLE tblOrder ( 
    order_id varchar primary key,
    client_id varchar NOT NULL,
    payment_registered BOOLEAN NOT NULL
);

CREATE TABLE tblProduct ( 
    product_id varchar primary key,
    product_name varchar NOT NULL, 
    product_price numeric(6, 0) NOT NULL,
    product_image varchar NOT NULL,
    provider_id varchar NOT NULL,
    CONSTRAINT fk_Product FOREIGN KEY(provider_id) REFERENCES tblProvider(provider_id)
);

CREATE TABLE tblMenu ( 
    menu_id varchar primary key,
    menu_name varchar NOT NULL,
    menu_image varchar NOT NULL,
    provider_id varchar NOT NULL,
    CONSTRAINT fk_Menu FOREIGN KEY(provider_id) REFERENCES tblProvider(provider_id)
);

CREATE TABLE tblProductForOrder ( 
    product_id varchar,
    order_id varchar, 
    CONSTRAINT fk_ProductForOrder_p FOREIGN KEY(product_id) REFERENCES tblProduct(product_id),
    CONSTRAINT fk_ProductForOrder_o FOREIGN KEY(order_id) REFERENCES tblOrder(order_id),
    PRIMARY KEY (product_id, order_id)
);

CREATE TABLE tblProductForMenu ( 
    product_id varchar,
    menu_id varchar, 
    CONSTRAINT fk_ProductForMenu_p FOREIGN KEY(product_id) REFERENCES tblProduct(product_id),
    CONSTRAINT fk_ProductForMenu_m FOREIGN KEY(menu_id) REFERENCES tblMenu(menu_id),
    PRIMARY KEY (product_id, menu_id)
);

CREATE TABLE tblMenuForOrder ( 
    menu_id varchar,
    order_id varchar, 
    CONSTRAINT fk_MenuForOrder_p FOREIGN KEY(menu_id) REFERENCES tblMenu(menu_id),
    CONSTRAINT fk_MenuForOrder_o FOREIGN KEY(order_id) REFERENCES tblOrder(order_id),
    PRIMARY KEY (menu_id, order_id)
);


-- Data insertion

INSERT INTO tblProvider (provider_id, provider_name, provider_image) VALUES ('Prov1', 'Presto', 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fecologycolombia.com%2Fwp-content%2Fuploads%2F2021%2F01%2Fpresto.png&f=1&nofb=1&ipt=4642608d3c848fe269823fbda445964ef6f60b3042106833fe0a40b998dea624&ipo=images');
INSERT INTO tblProvider (provider_id, provider_name, provider_image) VALUES ('Prov2', 'Administracion', 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcentrocomercialtulua.com%2Fwp-content%2Fuploads%2F2021%2F01%2Fcentro-comercial_Mesa-de-trabajo-1-510x510.jpg&f=1&nofb=1&ipt=10596a524729b9474fa55ee8d73b6e22c7b6da32cf7f42b7825914ae5ab79cec&ipo=images');


INSERT INTO tblProduct (product_id, product_name, product_price, product_image, provider_id) VALUES ('Prod1','Hamburguesa 1', 15500, 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fi0.wp.com%2Fprofundidad.net%2Fwp-content%2Fuploads%2F2014%2F05%2FSuperburger-9.jpg%3Ffit%3D1024%252C683%26ssl%3D1&f=1&nofb=1&ipt=779eee119108ca12416961999dbbf415a4f664003517fe871229f2d9bae96c45&ipo=images', 'Prov1');
INSERT INTO tblProduct (product_id, product_name, product_price, product_image, provider_id) VALUES ('Prod2','Hamburguesa 2', 10500, 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fimages.rappi.com%2Frestaurants_background%2F900012784-1558718863.png%3Fd%3D720x720&f=1&nofb=1&ipt=3473409162dcaea748c10bd98b2eb518794cdde02dbc96a4d6e34d1f2986f5fe&ipo=images', 'Prov1');


INSERT INTO tblMenu (menu_id, menu_name, menu_image, provider_id) VALUES ('Menu1','Combo Presto con papas', 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fmarketingdesigne.com%2Ffoodcart%2Fwp-content%2Fuploads%2F2018%2F10%2FPresto-Mall-Food-Cart-1802.jpg&f=1&nofb=1&ipt=c6ad4d048b561da71d2968a341ca2e9842b4884d75ab92582f2c8d5c57afd6fc&ipo=images', 'Prov1');
INSERT INTO tblMenu (menu_id, menu_name, menu_image, provider_id) VALUES ('Menu2','Administrando ando', 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fmarketingdesigne.com%2Ffoodcart%2Fwp-content%2Fuploads%2F2018%2F10%2FPresto-Mall-Food-Cart-1802.jpg&f=1&nofb=1&ipt=c6ad4d048b561da71d2968a341ca2e9842b4884d75ab92582f2c8d5c57afd6fc&ipo=images', 'Prov2');

INSERT INTO tblProductForMenu (product_id, menu_id) VALUES ('Prod1', 'Menu1');

