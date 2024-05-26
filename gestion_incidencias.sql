-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-05-2024 a las 14:42:49
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestion_incidencias`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE `estado` (
  `id_estado` int(11) NOT NULL,
  `descripcion` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `incidencias`
--

CREATE TABLE `incidencias` (
  `fecha_registro` date NOT NULL,
  `hora_registro` time NOT NULL,
  `numero_registro` int(11) NOT NULL,
  `puesto` int(11) NOT NULL,
  `id_estado` int(11) NOT NULL,
  `problema` varchar(200) NOT NULL,
  `fecha_eliminacion` date DEFAULT NULL,
  `causa_eliminacion` varchar(200) NOT NULL,
  `fecha_resolucion` date DEFAULT NULL,
  `resolucion` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `incidencias`
--

INSERT INTO `incidencias` (`fecha_registro`, `hora_registro`, `numero_registro`, `puesto`, `id_estado`, `problema`, `fecha_eliminacion`, `causa_eliminacion`, `fecha_resolucion`, `resolucion`) VALUES
('2024-05-25', '19:11:44', 3, 3, 1, 'djhbcf', NULL, '', NULL, 'hola'),
('2024-05-25', '19:11:50', 4, 3, 3, 's', NULL, '', NULL, 'drfgvb'),
('2024-05-25', '19:16:36', 5, 5, 1, 'r', NULL, '', NULL, 'drevg'),
('2024-05-25', '20:04:20', 6, 2, 2, 'hola', NULL, 'porque', NULL, 'porque'),
('2024-05-26', '11:41:06', 7, 6, 1, 'xdcf', NULL, '', NULL, ''),
('2024-05-26', '13:04:56', 8, 5, 1, 'fdfv', NULL, 'cf', NULL, 'g'),
('2024-05-26', '14:12:43', 9, 5, 2, 'dg', NULL, 'g', NULL, '');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `incidencias`
--
ALTER TABLE `incidencias`
  ADD PRIMARY KEY (`numero_registro`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `incidencias`
--
ALTER TABLE `incidencias`
  MODIFY `numero_registro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
