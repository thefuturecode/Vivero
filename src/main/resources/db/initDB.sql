DROP TABLE funcionario IF EXISTS;
DROP TABLE proveedor IF EXISTS;
DROP TABLE vale IF EXISTS;
DROP TABLE producto IF EXISTS;

CREATE TABLE funcionario (
    idFuncionario   	INTEGER IDENTITY PRIMARY KEY,
    nombre	 			VARCHAR(50),
    apellido	 		VARCHAR(50),
    rut	 		    	VARCHAR(9),
    telefono			VARCHAR(11),
    email				VARCHAR(50),
    cargo				VARCHAR(20)
);

CREATE TABLE proveedor (
    idProveedor   		INTEGER IDENTITY PRIMARY KEY,
    nombre	 			VARCHAR(50),
    rut	 		    	VARCHAR(9),
    telefono			VARCHAR(11),
    email				VARCHAR(50),
    direccion			VARCHAR(50)
);

CREATE TABLE producto (
    codigoProducto		INTEGER IDENTITY PRIMARY KEY,
    nombre				VARCHAR(50),
    descripcion	 		VARCHAR(100),
    marca				VARCHAR(20),
    precio				INTEGER,
    stock				INTEGER
);

CREATE TABLE vale (
    idVale		   		INTEGER IDENTITY PRIMARY KEY,
    idFuncionario		VARCHAR(50),
    fecha	 			DATE
    codigoProducto		VARCHAR(11),
    total				INTEGER
);
