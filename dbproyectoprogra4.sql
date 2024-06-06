-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-06-2024 a las 17:26:49
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbproyectoprogra4`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apartado`
--

CREATE TABLE `apartado` (
  `id_Apartado` int(11) NOT NULL,
  `idCliente_Apartado` int(11) DEFAULT NULL,
  `idProducto_Apartado` int(11) DEFAULT NULL,
  `fechaInicio_Apartado` datetime DEFAULT NULL,
  `fechaFinal_Apartado` datetime DEFAULT NULL,
  `abono_Apartado` decimal(10,2) DEFAULT NULL,
  `estado_Apartado` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_Cliente` int(11) NOT NULL,
  `idUsuario_Cliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id_Empleado` int(11) NOT NULL,
  `idUsuario_Empleado` int(11) DEFAULT NULL,
  `idEmpleado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id_Empleado`, `idUsuario_Empleado`, `idEmpleado`) VALUES
(1, 2, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `envio`
--

CREATE TABLE `envio` (
  `id_Envio` int(11) NOT NULL,
  `codigo_envio` varchar(25) DEFAULT NULL,
  `idPedido_Envio` int(11) DEFAULT NULL,
  `idCliente_Envio` int(11) DEFAULT NULL,
  `fechaEnvio_Envio` datetime DEFAULT NULL,
  `observacion_Envio` varchar(255) DEFAULT NULL,
  `direccionEnvio_Envio` varchar(255) DEFAULT NULL,
  `estadoEnvio_Envio` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturacion`
--

CREATE TABLE `facturacion` (
  `id_Facturacion` int(11) NOT NULL,
  `codigoFactura_Facturacion` varchar(20) DEFAULT NULL,
  `fechaFactura_Facturacion` date DEFAULT NULL,
  `precioTotal_Facturacion` double DEFAULT NULL,
  `descuento_Facturacion` double DEFAULT NULL,
  `metodoPago_Facturacion` varchar(50) DEFAULT NULL,
  `observacion_Facturacion` varchar(255) DEFAULT NULL,
  `impuesto_Facturacion` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `facturacion`
--

INSERT INTO `facturacion` (`id_Facturacion`, `codigoFactura_Facturacion`, `fechaFactura_Facturacion`, `precioTotal_Facturacion`, `descuento_Facturacion`, `metodoPago_Facturacion`, `observacion_Facturacion`, `impuesto_Facturacion`) VALUES
(1, '2021', '2024-06-05', 25000, 25, 'efectivo', 'Pago con efectivo', 13);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `oferta`
--

CREATE TABLE `oferta` (
  `id_Oferta` int(11) NOT NULL,
  `codigoOferta` varchar(100) NOT NULL,
  `codigoProducto` varchar(100) DEFAULT NULL,
  `descuentoOferta_Oferta` int(3) DEFAULT NULL,
  `fechaInicioOferta_Oferta` datetime DEFAULT NULL,
  `fechaFinOferta_Oferta` datetime DEFAULT NULL,
  `estadoOferta_Oferta` tinyint(1) DEFAULT NULL,
  `tipoOferta_Oferta` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordendecompra`
--

CREATE TABLE `ordendecompra` (
  `id_OrdenDeCompra` int(11) NOT NULL,
  `idPedido_OrdenDeCompra` int(11) NOT NULL,
  `idProveedor_OrdenDeCompra` int(11) DEFAULT NULL,
  `fechaOrden_OrdenDeCompra` datetime DEFAULT NULL,
  `fechaEntrega_OrdenDeCompra` datetime DEFAULT NULL,
  `estadoOrden_OrdenDeCompra` varchar(50) DEFAULT NULL,
  `numeroReferencia_OrdenDeCompra` varchar(50) DEFAULT NULL,
  `numeroReferencia` varchar(255) NOT NULL,
  `estadoOrden` varchar(255) DEFAULT NULL,
  `fechaEntrega` date DEFAULT NULL,
  `fechaOrden` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ordendecompra`
--

INSERT INTO `ordendecompra` (`id_OrdenDeCompra`, `idPedido_OrdenDeCompra`, `idProveedor_OrdenDeCompra`, `fechaOrden_OrdenDeCompra`, `fechaEntrega_OrdenDeCompra`, `estadoOrden_OrdenDeCompra`, `numeroReferencia_OrdenDeCompra`, `numeroReferencia`, `estadoOrden`, `fechaEntrega`, `fechaOrden`) VALUES
(2, 1, 1, '2024-06-05 00:00:00', '2024-06-29 00:00:00', 'Pendiente', '001', '', NULL, NULL, NULL),
(3, 1, 1, '2024-06-07 00:00:00', '2024-06-07 00:00:00', 'Pendiente', '002', '', NULL, NULL, NULL),
(5, 1, 1, '2024-06-28 00:00:00', '2024-06-29 00:00:00', 'Completo', '6743', '', NULL, NULL, NULL),
(6, 1, 1, '2024-06-28 00:00:00', '2024-06-29 00:00:00', 'Completo', '6743', '', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id_Pedido` int(11) NOT NULL,
  `idEmpleado_Pedido` int(11) DEFAULT NULL,
  `codigo_Pedido` varchar(50) DEFAULT NULL,
  `estadoPedido_Pedido` varchar(50) DEFAULT NULL,
  `fechaPedido__Pedido` datetime DEFAULT NULL,
  `direccionEnvioPedido_Pedido` varchar(255) DEFAULT NULL,
  `provinciaEnvioPedido_pedido` varchar(255) DEFAULT NULL,
  `cantonEnvio_Pedido` varchar(255) DEFAULT NULL,
  `idProducto_Pedido` int(11) DEFAULT NULL,
  `cantidadProductos_pedido` int(50) DEFAULT NULL,
  `idFactura_Pedido` int(11) DEFAULT NULL,
  `Provincia` varchar(255) DEFAULT NULL,
  `cantidad` int(11) NOT NULL,
  `canton` varchar(255) DEFAULT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `direccion_pedido` varchar(255) DEFAULT NULL,
  `estado_pedido` varchar(255) DEFAULT NULL,
  `factura` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `id_empleado` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id_Pedido`, `idEmpleado_Pedido`, `codigo_Pedido`, `estadoPedido_Pedido`, `fechaPedido__Pedido`, `direccionEnvioPedido_Pedido`, `provinciaEnvioPedido_pedido`, `cantonEnvio_Pedido`, `idProducto_Pedido`, `cantidadProductos_pedido`, `idFactura_Pedido`, `Provincia`, `cantidad`, `canton`, `codigo`, `direccion_pedido`, `estado_pedido`, `factura`, `fecha`, `id_empleado`, `id_producto`) VALUES
(1, 1, '0001', 'Pendiente', '2024-06-05 00:00:00', 'La Guaria, 300 metros oeste del sementerio', 'Heredia', 'Sarapiqui', 1, 25, 1, NULL, 0, NULL, NULL, NULL, NULL, 0, NULL, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_Producto` int(11) NOT NULL,
  `nombre_Producto` varchar(255) DEFAULT NULL,
  `descripcion_Producto` text DEFAULT NULL,
  `precio_Producto` double DEFAULT NULL,
  `categoria_Producto` varchar(100) DEFAULT NULL,
  `calificacion_Producto` int(11) DEFAULT NULL,
  `disponibilidad_Producto` tinyint(1) DEFAULT NULL,
  `stock_Producto` int(255) DEFAULT NULL,
  `codProducto` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_Producto`, `nombre_Producto`, `descripcion_Producto`, `precio_Producto`, `categoria_Producto`, `calificacion_Producto`, `disponibilidad_Producto`, `stock_Producto`, `codProducto`) VALUES
(1, 'Laptop', 'Laptop de alta gama con procesador Intel i7', 1200, 'Electrónica', 5, 1, 10, 'LP-001'),
(2, 'Smartphone', 'Teléfono inteligente con pantalla AMOLED', 699.99, 'Electrónica', 4, 1, 25, 'SP-002'),
(3, 'Cafetera', 'Cafetera automática con varias configuraciones', 150, 'Hogar', 4, 1, 15, 'CF-003'),
(4, 'Televisor', 'Televisor 4K UHD de 55 pulgadas', 800, 'Electrónica', 5, 1, 5, 'TV-004'),
(5, 'Auriculares', 'Auriculares inalámbricos con cancelación de ruido', 199.99, 'Electrónica', 4, 1, 50, 'AU-005'),
(6, 'Mochila', 'Mochila resistente al agua con compartimento para laptop', 49.99, 'Accesorios', 3, 1, 30, 'MC-006'),
(7, 'Reloj Inteligente', 'Reloj inteligente con monitor de actividad física', 129.99, 'Electrónica', 4, 1, 20, 'RI-007'),
(8, 'Libro', 'Novela de misterio y suspenso', 14.99, 'Libros', 5, 1, 100, 'LB-008'),
(9, 'Mouse', 'Mouse inalámbrico ergonómico', 25, 'Accesorios', 3, 1, 40, 'MO-009'),
(10, 'Cámara', 'Cámara digital con lente intercambiable', 599.99, 'Electrónica', 5, 1, 8, 'CM-010'),
(11, 'Piña', 'Una fruta muy rica', 25000, 'Frutas', 16, 1, 50, '0001'),
(12, 'Manzana', '', 3000, 'Frutas', 16, 1, 50, '0001');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id_Proveedor` int(11) NOT NULL,
  `nombreProveedor_Proveedor` varchar(100) DEFAULT NULL,
  `telefonoProveedor_Proveedor` varchar(20) DEFAULT NULL,
  `descripcionProveedor_Proveedor` text DEFAULT NULL,
  `correo_Proveedor` varchar(100) DEFAULT NULL,
  `direccionProveedor_Proveedor` varchar(255) DEFAULT NULL,
  `categoriaServicio_Proveedor` varchar(100) DEFAULT NULL,
  `informacionAdicional_Proveedor` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id_Proveedor`, `nombreProveedor_Proveedor`, `telefonoProveedor_Proveedor`, `descripcionProveedor_Proveedor`, `correo_Proveedor`, `direccionProveedor_Proveedor`, `categoriaServicio_Proveedor`, `informacionAdicional_Proveedor`) VALUES
(1, 'Adam Acuña', '63133860', 'Proveedor de productos tecnológicos', 'adam.acuna.gonzalez@est.una.ac.cr', 'Sarapiqui, Horquetas.', 'Tecnología', 'Da un 50% de descuento si se realizan compras mayores a 1000000 de colones');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_Usuario` int(11) NOT NULL,
  `nombre_Usuario` varchar(255) DEFAULT NULL,
  `apellidos_Usuario` varchar(255) DEFAULT NULL,
  `email_Usuario` varchar(255) DEFAULT NULL,
  `password_Usuario` varchar(255) DEFAULT NULL,
  `cedula_Usuario` varchar(20) DEFAULT NULL,
  `telefono_Usuario` varchar(20) DEFAULT NULL,
  `credencial_Usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_Usuario`, `nombre_Usuario`, `apellidos_Usuario`, `email_Usuario`, `password_Usuario`, `cedula_Usuario`, `telefono_Usuario`, `credencial_Usuario`) VALUES
(2, 'Juan', 'Perez', 'juan@gmail.com', '1234', '123456789', '23145698', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `apartado`
--
ALTER TABLE `apartado`
  ADD PRIMARY KEY (`id_Apartado`),
  ADD KEY `FK_Apartado_Cliente` (`idCliente_Apartado`),
  ADD KEY `FK_Apartado_Producto` (`idProducto_Apartado`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_Cliente`),
  ADD KEY `FK_Cliente_Usuario` (`idUsuario_Cliente`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id_Empleado`),
  ADD KEY `FK_Empleado_Usuario` (`idUsuario_Empleado`);

--
-- Indices de la tabla `envio`
--
ALTER TABLE `envio`
  ADD PRIMARY KEY (`id_Envio`),
  ADD KEY `FK_Envio_Pedido` (`idPedido_Envio`),
  ADD KEY `FK_Envio_Cliente` (`idCliente_Envio`);

--
-- Indices de la tabla `facturacion`
--
ALTER TABLE `facturacion`
  ADD PRIMARY KEY (`id_Facturacion`);

--
-- Indices de la tabla `oferta`
--
ALTER TABLE `oferta`
  ADD PRIMARY KEY (`id_Oferta`);

--
-- Indices de la tabla `ordendecompra`
--
ALTER TABLE `ordendecompra`
  ADD PRIMARY KEY (`id_OrdenDeCompra`),
  ADD KEY `FK_PROVEEDOR` (`idProveedor_OrdenDeCompra`),
  ADD KEY `FK_PEDIDO` (`idPedido_OrdenDeCompra`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_Pedido`),
  ADD KEY `FK_Pedido_Empleado` (`idEmpleado_Pedido`),
  ADD KEY `FK_Pedido_Producto` (`idProducto_Pedido`),
  ADD KEY `FK_Pedido_Facturacion` (`idFactura_Pedido`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_Producto`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id_Proveedor`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_Usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `apartado`
--
ALTER TABLE `apartado`
  MODIFY `id_Apartado` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_Cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id_Empleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `envio`
--
ALTER TABLE `envio`
  MODIFY `id_Envio` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `facturacion`
--
ALTER TABLE `facturacion`
  MODIFY `id_Facturacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `oferta`
--
ALTER TABLE `oferta`
  MODIFY `id_Oferta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `ordendecompra`
--
ALTER TABLE `ordendecompra`
  MODIFY `id_OrdenDeCompra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_Pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_Producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id_Proveedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_Usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `apartado`
--
ALTER TABLE `apartado`
  ADD CONSTRAINT `FK_Apartado_Cliente` FOREIGN KEY (`idCliente_Apartado`) REFERENCES `cliente` (`id_Cliente`),
  ADD CONSTRAINT `FK_Apartado_Producto` FOREIGN KEY (`idProducto_Apartado`) REFERENCES `producto` (`id_Producto`);

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `FK_Cliente_Usuario` FOREIGN KEY (`idUsuario_Cliente`) REFERENCES `usuario` (`id_Usuario`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `FK_Empleado_Usuario` FOREIGN KEY (`idUsuario_Empleado`) REFERENCES `usuario` (`id_Usuario`);

--
-- Filtros para la tabla `envio`
--
ALTER TABLE `envio`
  ADD CONSTRAINT `FK_Envio_Cliente` FOREIGN KEY (`idCliente_Envio`) REFERENCES `cliente` (`id_Cliente`),
  ADD CONSTRAINT `FK_Envio_Pedido` FOREIGN KEY (`idPedido_Envio`) REFERENCES `pedido` (`id_Pedido`);

--
-- Filtros para la tabla `ordendecompra`
--
ALTER TABLE `ordendecompra`
  ADD CONSTRAINT `ordendecompra_ibfk_1` FOREIGN KEY (`idProveedor_OrdenDeCompra`) REFERENCES `proveedor` (`id_Proveedor`),
  ADD CONSTRAINT `ordendecompra_ibfk_2` FOREIGN KEY (`idPedido_OrdenDeCompra`) REFERENCES `pedido` (`id_Pedido`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK_Pedido_Empleado` FOREIGN KEY (`idEmpleado_Pedido`) REFERENCES `empleado` (`id_Empleado`),
  ADD CONSTRAINT `FK_Pedido_Facturacion` FOREIGN KEY (`idFactura_Pedido`) REFERENCES `facturacion` (`id_Facturacion`),
  ADD CONSTRAINT `FK_Pedido_Producto` FOREIGN KEY (`idProducto_Pedido`) REFERENCES `producto` (`id_Producto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
