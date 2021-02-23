INSERT INTO funcionarios(id_funcionario,nombre,apellido,run,telefono,email,cargo) VALUES (1, 'Diego', 'Orrego', '111111111-1', 123456789, 'diego@mail.com', 'vendedor');
INSERT INTO funcionarios(id_funcionario,nombre,apellido,run,telefono,email,cargo) VALUES (2, 'Carolina', 'Pereira', '222222222-2', 234567891, 'carolina@mail.com', 'vendedor');
INSERT INTO funcionarios(id_funcionario,nombre,apellido,run,telefono,email,cargo) VALUES (3, 'Francisca', 'Bustos', '333333333-3', 345678912, 'francisca@mail.com', 'vendedor');
INSERT INTO funcionarios(id_funcionario,nombre,apellido,run,telefono,email,cargo) VALUES (4, 'Ramón', 'Elgueta', '444444444-1', 456789123, 'ramon@mail.com', 'vendedor');


INSERT INTO proveedores(id_proveedor,nombre,rut,telefono,email,direccion) VALUES (10, 'Andrés Orellana', '555555555-5', 567891234, 'andres@mail.com', 'Los Gladiolos 123');
INSERT INTO proveedores(id_proveedor,nombre,rut,telefono,email,direccion) VALUES (11, 'Belen Peña', '666666666-6', 678912345, 'belen@mail.com', 'Los Lirios 234');
INSERT INTO proveedores(id_proveedor,nombre,rut,telefono,email,direccion) VALUES (12, 'César Ortiz', '777777777-7', 789123456, 'cesar@mail.com', 'Los Claveles 345');
INSERT INTO proveedores(id_proveedor,nombre,rut,telefono,email,direccion) VALUES (13, 'Karen Jara', '888888888-8', 891234567, 'karen@mail.com', 'Las Rosas 456');
INSERT INTO proveedores(id_proveedor,nombre,rut,telefono,email,direccion) VALUES (14, 'Cristobal Parra', '999999999-9', 912345678, 'cristobal@mail.com', 'Los Tulipanes 567');
INSERT INTO proveedores(id_proveedor,nombre,rut,telefono,email,direccion) VALUES (15, 'Carla Fuentealba', '112233445-5', 987654321, 'carla@mail.com', 'Las Orquídeas 678');


INSERT INTO productos(id_producto,nombre,descripcion,marca,precio,stock) VALUES (111111, 'Tierra con abono', 'Tierra con abono para jardín 35 litros saco', 'Armony', 3250, 50);
INSERT INTO productos(id_producto,nombre,descripcion,marca,precio,stock) VALUES (222222, 'Fertilizante', 'Fertilizante para plantas y árboles 1 kg bolsa', 'Best Garden', 3590, 30);
INSERT INTO productos(id_producto,nombre,descripcion,marca,precio,stock) VALUES (333333, 'Tierra de hoja', 'Tierra de hoja para jardín 80 litros saco', 'Anasac', 5990, 60);
INSERT INTO productos(id_producto,nombre,descripcion,marca,precio,stock) VALUES (444444, 'Fertilizante Líquido', 'Fertilizante líquido para plantas y flores 250ml frasco', 'Best Garden', 3290, 25);
INSERT INTO productos(id_producto,nombre,descripcion,marca,precio,stock) VALUES (555555, 'Enraizante', 'Enraizante inductor del crecimiento 250ml frasco', 'Ergo', 3630, 20);
INSERT INTO productos(id_producto,nombre,descripcion,marca,precio,stock) VALUES (666666, 'Shampoo Fungicida', 'Fungicida para plantas 1 litro spray', 'Ergo', 3600, 45);
INSERT INTO productos(id_producto,nombre,descripcion,marca,precio,stock) VALUES (777777, 'Insecticida', 'Insecticida concentrado para jardín 100 ml emulsión', 'Best Garden', 15490, 35);
INSERT INTO productos(id_producto,nombre,descripcion,marca,precio,stock) VALUES (888888, 'Insecticida en polvo', 'Insectivida en polvo para plagas restreras 40 gr', 'Anasac', 4890, 15);
INSERT INTO productos(id_producto,nombre,descripcion,marca,precio,stock) VALUES (999999, 'Tierra de hoja', 'Tierra de hoja 40 litros reforzada', 'Comercializadora VH', 6290, 23);


INSERT INTO vales(id_vale,id_funcionario,fecha,codigo_producto,total) VALUES (100, 3, '2020-12-15', 444444, 3290);
INSERT INTO vales(id_vale,id_funcionario,fecha,codigo_producto,total) VALUES (101, 2, '2020-12-15', 888888, 4890);
INSERT INTO vales(id_vale,id_funcionario,fecha,codigo_producto,total) VALUES (102, 1, '2020-12-16', 222222, 3590);
INSERT INTO vales(id_vale,id_funcionario,fecha,codigo_producto,total) VALUES (103, 3, '2020-12-16', 666666, 3600);
INSERT INTO vales(id_vale,id_funcionario,fecha,codigo_producto,total) VALUES (104, 4, '2020-12-17', 111111, 3250);
INSERT INTO vales(id_vale,id_funcionario,fecha,codigo_producto,total) VALUES (105, 1, '2020-12-17', 333333, 5990);
INSERT INTO vales(id_vale,id_funcionario,fecha,codigo_producto,total) VALUES (106, 3, '2020-12-18', 555555, 3630);
INSERT INTO vales(id_vale,id_funcionario,fecha,codigo_producto,total) VALUES (107, 2, '2020-12-18', 999999, 6290);
INSERT INTO vales(id_vale,id_funcionario,fecha,codigo_producto,total) VALUES (108, 4, '2020-12-19', 777777, 15490);