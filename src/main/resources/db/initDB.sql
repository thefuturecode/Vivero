DROP TABLE funcionario IF EXISTS;
DROP TABLE proveedor IF EXISTS;
DROP TABLE vale IF EXISTS;
DROP TABLE producto IF EXISTS;

CREATE TABLE funcionarios (
    id_funcionario   	INTEGER IDENTITY PRIMARY KEY,
    nombre	 			VARCHAR(50),
    apellido	 		VARCHAR(50),
    run	 		    	VARCHAR(15),
    telefono			VARCHAR(11),
    email				VARCHAR(50),
    estado				VARCHAR(20),
    cargo				VARCHAR(20)
);

CREATE TABLE proveedores (
    id_proveedor   		INTEGER IDENTITY PRIMARY KEY,
    nombre	 			VARCHAR(50),
    rut	 		    	VARCHAR(15),
    telefono			VARCHAR(11),
    email				VARCHAR(50),
    direccion			VARCHAR(50)
);

CREATE TABLE productos (
    id_producto		INTEGER IDENTITY PRIMARY KEY,
    nombre				VARCHAR(50),
    descripcion	 		VARCHAR(100),
    marca				VARCHAR(20),
    precio				INTEGER,
    stock				INTEGER
);

CREATE TABLE vales (
    id_vale		   		INTEGER IDENTITY PRIMARY KEY,
    id_funcionario		VARCHAR(50),
    fecha	 			VARCHAR(41),
    codigo_producto		VARCHAR(11),
    total				INTEGER
);
