-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 23-10-2023 a las 16:00:04
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+02:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `apr`
--

CREATE DATABASE 'apr';
USE 'apr';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `usuario` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `contrasena` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fecha_nacimiento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`usuario`, `contrasena`, `fecha_nacimiento`) VALUES
('admin', 'admin', '1986-10-01'),
('Usuario1', 'Contraseña1', '1990-01-01'),
('Usuario10', 'Contraseña10', '1990-10-10'),
('Usuario11', 'Contraseña11', '1990-11-11'),
('Usuario12', 'Contraseña12', '1990-12-12'),
('Usuario13', 'Contraseña13', '1990-01-13'),
('Usuario14', 'Contraseña14', '1990-02-14'),
('Usuario15', 'Contraseña15', '1990-03-15'),
('Usuario16', 'Contraseña16', '1990-04-16'),
('Usuario17', 'Contraseña17', '1990-05-17'),
('Usuario18', 'Contraseña18', '1990-06-18'),
('Usuario19', 'Contraseña19', '1990-07-19'),
('Usuario2', 'Contraseña2', '1990-02-02'),
('Usuario20', 'Contraseña20', '1990-08-20'),
('Usuario21', 'Contraseña21', '1990-09-21'),
('Usuario22', 'Contraseña22', '1990-10-22'),
('Usuario23', 'Contraseña23', '1990-11-23'),
('Usuario24', 'Contraseña24', '1990-12-24'),
('Usuario25', 'Contraseña25', '1990-01-25'),
('Usuario26', 'Contraseña26', '1990-02-26'),
('Usuario27', 'Contraseña27', '1990-03-27'),
('Usuario28', 'Contraseña28', '1990-04-28'),
('Usuario29', 'Contraseña29', '1990-05-29'),
('Usuario3', 'Contraseña3', '1990-03-03'),
('Usuario30', 'Contraseña30', '1990-06-30'),
('Usuario31', 'Contraseña31', '1990-07-31'),
('Usuario32', 'Contraseña32', '1990-08-01'),
('Usuario33', 'Contraseña33', '1990-09-02'),
('Usuario34', 'Contraseña34', '1990-10-03'),
('Usuario35', 'Contraseña35', '1990-11-04'),
('Usuario36', 'Contraseña36', '1990-12-05'),
('Usuario37', 'Contraseña37', '1990-01-06'),
('Usuario38', 'Contraseña38', '1990-02-07'),
('Usuario39', 'Contraseña39', '1990-03-08'),
('Usuario4', 'Contraseña4', '1990-04-04'),
('Usuario40', 'Contraseña40', '1990-04-09'),
('Usuario41', 'Contraseña41', '1990-05-10'),
('Usuario42', 'Contraseña42', '1990-06-11'),
('Usuario43', 'Contraseña43', '1990-07-12'),
('Usuario44', 'Contraseña44', '1990-08-13'),
('Usuario45', 'Contraseña45', '1990-09-14'),
('Usuario46', 'Contraseña46', '1990-10-15'),
('Usuario47', 'Contraseña47', '1990-11-16'),
('Usuario48', 'Contraseña48', '1990-12-17'),
('Usuario49', 'Contraseña49', '1990-01-18'),
('Usuario5', 'Contraseña5', '1990-05-05'),
('Usuario50', 'Contraseña50', '1990-02-19'),
('Usuario6', 'Contraseña6', '1990-06-06'),
('Usuario7', 'Contraseña7', '1990-07-07'),
('Usuario8', 'Contraseña8', '1990-08-08'),
('Usuario9', 'Contraseña9', '1990-09-09');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
