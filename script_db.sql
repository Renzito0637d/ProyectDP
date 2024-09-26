-- DDL
-- DROP DATABASE ProyectoTambo;
-- Crear la base de datos
CREATE DATABASE ProyectoTambo;
USE ProyectoTambo;

-- --------------------------------------------------------------
-- Crear tabla Cliente
CREATE TABLE Cliente(
	codigo_cliente int primary key AUTO_INCREMENT,
	nombres varchar(30) not null,
	apellidos varchar(30) not null,
	email varchar(80) null, 
	telefono varchar(10) null, 
	usuario varchar(80) not null,   -- unique
	clave varchar(30) not null,
	direccion varchar(80) null
);
-- Llave única: usuario
ALTER TABLE Cliente
ADD CONSTRAINT uniq_usuario_cliente
UNIQUE (usuario);

-- --------------------------------------------------------------
-- Crear tabla Departamento
CREATE TABLE Departamento(
	codigo_departamento int primary key AUTO_INCREMENT,
	nombre varchar(30) not null,
	email varchar(80) null, 
	telefono varchar(10) null
);


-- --------------------------------------------------------------
-- Crear tabla Empleado
CREATE TABLE Empleado(
	codigo_empleado int primary key AUTO_INCREMENT,
	nombres varchar(30) not null,
	apellidos varchar(30) not null,
	email varchar(80) null, 
	telefono varchar(10) null, 
	usuario varchar(80) not null,      -- unique
	clave varchar(30) not null,
	codigo_departamento int not null   -- FK
);
-- Llave única: usuario
ALTER TABLE Empleado
ADD CONSTRAINT uniq_usuario_empleado
UNIQUE (usuario);
-- Llave foránea
ALTER TABLE Empleado
ADD CONSTRAINT fk_empleado_departamento
FOREIGN KEY (codigo_departamento) REFERENCES Departamento(codigo_departamento);

-- --------------------------------------------------------------
-- Crear tabla Solicitud
CREATE TABLE Solicitud(
	id_solicitud int primary key AUTO_INCREMENT,
	tipo_solicitud varchar(10) not null,	
	fecha_ingreso date not null,
	estado_actual int not null,
	codigo_cliente int not null,                  -- FK
	codigo_departamento_evaluador int not null    -- FK	
);
-- Llave foránea
ALTER TABLE Solicitud
ADD CONSTRAINT fk_solicitud_cliente
FOREIGN KEY (codigo_cliente) REFERENCES Cliente(codigo_cliente);
-- Llave foránea
ALTER TABLE Solicitud
ADD CONSTRAINT fk_solicitud_departamento
FOREIGN KEY (codigo_departamento_evaluador) REFERENCES Departamento(codigo_departamento);


-- --------------------------------------------------------------
-- Crear tabla Motivo
CREATE TABLE Motivo(
	id_motivo int primary key AUTO_INCREMENT,
	categoria varchar(50) not null,
	descripcion varchar(300) null,
	id_solicitud int not null              -- FK, unique
);
-- Llave foránea
ALTER TABLE Motivo
ADD CONSTRAINT fk_motivo_solicitud
FOREIGN KEY (id_solicitud) REFERENCES Solicitud(id_solicitud);
-- Relacion de uno a uno
ALTER TABLE Motivo
ADD CONSTRAINT uniq_solicitud_motivo
UNIQUE (id_solicitud);


-- --------------------------------------------------------------
-- Crear tabla Articulo
CREATE TABLE Articulo(
	codigo_producto int primary key AUTO_INCREMENT,
	nombre varchar(50) not null,
	categoria varchar(20) not null,
	precio decimal(8,2) not null,
	stock int not null
);


-- --------------------------------------------------------------
-- Crear tabla CompraReclamada
CREATE TABLE CompraReclamada(
	numero_compra int primary key AUTO_INCREMENT,
	canal_compra varchar(25) not null,
	fecha_compra date not null,
	direccion varchar(80) null,
	monto_reclamado decimal(8,2) not null,
	nombre_servicio varchar(30) null,
	codigo_producto int null,              -- FK
	id_solicitud int not null              -- FK, unique
);
-- Llave foránea
ALTER TABLE CompraReclamada
ADD CONSTRAINT fk_compra_articulo
FOREIGN KEY (codigo_producto) REFERENCES Articulo(codigo_producto);
-- Llave foránea
ALTER TABLE CompraReclamada
ADD CONSTRAINT fk_compra_solicitud
FOREIGN KEY (id_solicitud) REFERENCES Solicitud(id_solicitud);
-- Relacion de uno a uno
ALTER TABLE CompraReclamada
ADD CONSTRAINT uniq_solicitud_compra
UNIQUE (id_solicitud);


-- --------------------------------------------------------------
-- Crear tabla Evaluacion
CREATE TABLE Evaluacion(
	numero_evaluacion int primary key AUTO_INCREMENT,
	fecha_hora datetime not null,
	estado int not null,
	descripcion varchar(300) null,
	id_solicitud int not null,     -- FK
	codigo_empleado int null      -- FK
);
-- Llave foránea
ALTER TABLE Evaluacion
ADD CONSTRAINT fk_evaluacion_solicitud
FOREIGN KEY (id_solicitud) REFERENCES Solicitud(id_solicitud);
-- Llave foránea
ALTER TABLE Evaluacion
ADD CONSTRAINT fk_evaluacion_empleado
FOREIGN KEY (codigo_empleado) REFERENCES Empleado(codigo_empleado);


-- --------------------------------------------------------------









-- DML
-- Inserciones Tabla Cliente
INSERT INTO Cliente (nombres, apellidos, email, telefono, usuario, clave, direccion)
VALUES
('Ana', 'Fuentes', 'ana.fuentes@gmail.com', '984568777', 'ana.fuentes.1', '123', 'Calle Las Flores 75'),
('Luis', 'Ramirez', 'luis.ramirez@yahoo.com', '987654321', 'luis.ramirez.2', 'abc', 'Avenida Arequipa 1234'),
('Maria', 'Lopez', 'maria.lopez@hotmail.com', '912345678', 'maria.lopez.3', 'xyz', 'Calle Pizarro 567'),
('Carlos', 'Sanchez', 'carlos.sanchez@gmail.com', '923456789', 'carlos.sanchez.4', 'password', 'Jiron Lampa 789'),
('Mario', 'Gonzales', 'mario.gonzales@gmail.com', '934567890', 'mario.gonzales.5', '12345', 'Avenida Brasil 456');


-- Inserciones Tabla Departamento
INSERT INTO Departamento (nombre, email, telefono)
VALUES
('Atención al Cliente', 'atencion@tambo.com.pe', '945678901'),
('Calidad', 'calidad@tambo.com.pe', '978901234'),
('Ventas', 'ventas@tambo.com.pe', '956789012'),
('Gerencia', 'gerencia@tambo.com.pe', '989012345'),
('Contabilidad y Finanzas', 'contabilidadyfinanzas@tambo.com.pe', '989098125');

-- Inserciones Tabla Empleado
INSERT INTO Empleado (nombres, apellidos, email, telefono, usuario, clave, codigo_departamento)
VALUES
('Jose', 'Perez', 'jose.perez@tambo.com.pe', '991234567', 'jose.perez.1', '1234', 1),
('Sandra', 'Torres', 'sandra.torres@tambo.com.pe', '992345678', 'sandra.torres.2', 'abcd', 2),
('Miguel', 'Castro', 'miguel.castro@tambo.com.pe', '993456789', 'miguel.castro.3', 'efgh', 3),
('Lucia', 'Mendoza', 'lucia.mendoza@tambo.com.pe', '994567890', 'lucia.mendoza.4', 'admin', 4),
('Jorge', 'Diaz', 'jorge.diaz@tambo.com.pe', '995678901', 'jorge.diaz.5', 'ijkl', 5);

-- Inserciones Tabla Solicitud
INSERT INTO Solicitud (tipo_solicitud, fecha_ingreso, estado_actual, codigo_cliente, codigo_departamento_evaluador)
VALUES
('RECLAMO', '2024-03-29', 3, 2, 4),
('QUEJA', '2024-04-25', 3, 3, 4),
('QUEJA', '2024-04-29', 3, 1, 4),
('RECLAMO', '2024-05-03', 2, 4, 3),
('RECLAMO', '2024-05-03', 3, 1, 4),
('RECLAMO', '2024-05-05', 2, 1, 2),
('QUEJA', '2024-05-06', 2, 3, 3),
('RECLAMO', '2024-05-06', 3, 5, 4),
('RECLAMO', '2024-05-07', 2, 1, 2),
('RECLAMO', '2024-05-14', 1, 5, 1);

-- Inserciones Tabla Motivo
INSERT INTO Motivo (categoria, descripcion, id_solicitud)
VALUES
('Producto en mal estado', 'El producto llegó dañado.', 1),
('Mal servicio', 'El empleado fue grosero.', 2),
('Mal servicio', 'El pedido tardó mucho en llegar.', 3),
('Producto no corresponde al pedido', 'Enviaron un producto equivocado.', 4),
('Producto en mal estado', 'El producto está malogrado.', 5),
('Producto no corresponde al pedido', 'El producto no es de la talla que pedí.', 6),
('Mal servicio', 'Llevo esperando mucho tiempo y el pedido no llega.', 7),
('Problemas en el pago', 'El cajero me cobró de más.', 8),
('Producto en mal estado', 'El producto ha pasado la fecha de vencimiento.', 9),
('Problemas en el pago', 'Se me cobró un monto incorrecto.', 10);

-- Inserciones Tabla Artículo
INSERT INTO Articulo (nombre, categoria, precio, stock)
VALUES
('Arroz', 'Alimentos', 5.50, 100),
('Aceite', 'Alimentos', 12.90, 50),
('Galletas', 'Snacks', 3.20, 200),
('Jabón', 'Limpieza', 2.50, 150),
('Shampoo', 'Higiene', 15.00, 80),
('Camisa', 'Ropa', 54.00, 25);

-- Inserciones Tabla CompraReclamada
INSERT INTO CompraReclamada (canal_compra, fecha_compra, direccion, monto_reclamado, nombre_servicio, codigo_producto, id_solicitud)
VALUES
('Tienda Virtual (Delivery)', '2024-03-29', 'Jiron Lima 123', 22.00, null, 1, 1),
('Tienda Física', '2024-04-24', 'Avenida Grau 456', 0.0, 'Atención al Cliente', null, 2),
('Tienda Virtual (Delivery)', '2024-04-27', 'Calle Larco 789', 0.0, 'Delivery', null, 3),
('Tienda Virtual (Delivery)', '2024-05-03', 'Avenida Pardo 101', 20.00, null, 4, 4),
('Tienda Física', '2024-05-03', 'Jiron Union 202', 39.00, null, 2, 5),
('Tienda Virtual (Delivery)', '2024-05-04', 'Jiron Lima 123', 54.00, null, 6, 6),
('Tienda Virtual (Delivery)', '2024-05-04', 'Avenida Grau 456', 0.0, 'Delivery', null, 7),
('Tienda Física', '2024-05-06', 'Calle Larco 789', 12.00, 'Operación en caja', null, 8),
('Tienda Física', '2024-05-07', 'Avenida Pardo 101', 10.00, null, 3, 9),
('Tienda Virtual (Delivery)', '2024-05-14', 'Jiron Union 202', 20.0, 'Pago online', null, 10);


-- Inserciones Tabla Evaluacion
INSERT INTO Evaluacion (fecha_hora, estado, descripcion, id_solicitud, codigo_empleado)
VALUES
('2024-03-29 10:00:00', 1, 'Enviado por el cliente', 1, null),
('2024-03-29 15:00:00', 2, 'Solicitud aceptada. Enviada al área de Calidad', 1, 1),
('2024-04-01 11:39:00', 2, 'Evaluación en proceso.... Enviada a Gerencia', 1, 2),
('2024-04-02 09:14:00', 3, 'Evaluación finalizada', 1, 4),
('2024-04-25 09:35:00', 1, 'Enviado por el cliente', 2, null),
('2024-04-25 19:50:00', 3, 'Solicitud desestimada debido a (...). Vuelva a realizar otro trámite', 2, 1),
('2024-04-29 11:42:00', 1, 'Enviado por el cliente', 3, null),
('2024-04-29 19:00:00', 2, 'Solicitud aceptada. Enviada al área de Ventas', 3, 1),
('2024-04-30 15:39:00', 2, 'Evaluación en proceso.... Enviada a Gerencia', 3, 3),
('2024-05-02 12:14:00', 3, 'Evaluación finalizada', 3, 4),
('2024-05-03 12:14:00', 1, 'Enviado por el cliente', 4, null),
('2024-05-04 16:00:00', 2, 'Solicitud aceptada. Enviada al área de Ventas', 4, 1),
('2024-05-03 13:26:00', 1, 'Enviado por el cliente', 5, null),
('2024-05-03 21:00:00', 2, 'Solicitud aceptada. Enviada al área de Calidad', 5, 1),
('2024-05-04 11:39:00', 2, 'Evaluación en proceso.... Enviada a Gerencia', 5, 2),
('2024-05-05 09:14:00', 3, 'Evaluación finalizada', 5, 4),
('2024-05-05 14:47:00', 1, 'Enviado por el cliente', 6, null),
('2024-05-06 16:00:00', 2, 'Solicitud aceptada. Enviada al área de Calidad', 6, 1),
('2024-05-06 15:51:00', 1, 'Enviado por el cliente', 7, null),
('2024-05-06 18:00:00', 2, 'Solicitud aceptada. Enviada al área de Ventas', 7, 1),
('2024-05-06 16:28:00', 1, 'Enviado por el cliente', 8, null),
('2024-05-07 15:34:00', 2, 'Solicitud aceptada. Enviada al área de Contabilidad y Finanzas', 8, 1),
('2024-05-07 18:39:00', 2, 'Evaluación en proceso.... Enviada a Gerencia', 8, 5),
('2024-05-09 09:14:00', 3, 'Evaluación finalizada', 8, 4),
('2024-05-07 14:19:00', 1, 'Enviado por el cliente', 9, null),
('2024-05-08 11:00:00', 2, 'Solicitud aceptada. Enviada al área de Calidad', 9, 1),
('2024-05-14 13:32:00', 1, 'Enviado por el cliente', 10, null);