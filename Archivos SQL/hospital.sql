-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-12-2025 a las 10:44:40
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hospital`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `doctor`
--

CREATE TABLE `doctor` (
  `hospital_cod` int(2) DEFAULT NULL,
  `doctor_no` int(3) NOT NULL DEFAULT 0,
  `apellido` varchar(4) DEFAULT NULL,
  `especialidad` varchar(4) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `doctor`
--

INSERT INTO `doctor` (`hospital_cod`, `doctor_no`, `apellido`, `especialidad`) VALUES
(13, 435, 'López A.', 'Cardiología'),
(18, 585, 'Miller G.', 'Ginecología'),
(18, 982, 'Cajal R.', 'Cardiiología'),
(22, 453, 'Galo D.', 'Pediatría'),
(22, 398, 'Best D.', 'Urología'),
(22, 386, 'Cabeza D.', 'Psiquiatría'),
(45, 607, 'Nino P.', 'Pediatría'),
(45, 522, 'Adams C.', 'Neurología');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enfermo`
--

CREATE TABLE `enfermo` (
  `inscripcion` int(5) DEFAULT NULL,
  `apellido` varchar(3) DEFAULT NULL,
  `direccion` varchar(5) DEFAULT NULL,
  `fecha_nac` date DEFAULT NULL,
  `s` char(0) DEFAULT NULL,
  `nss` int(9) NOT NULL DEFAULT 0,
  `hospital_cod` int(2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `enfermo`
--

INSERT INTO `enfermo` (`inscripcion`, `apellido`, `direccion`, `fecha_nac`, `s`, `nss`, `hospital_cod`) VALUES
(10995, 'Laguía M.', 'Goya 20', '0000-00-00', 'M', 280862482, 13),
(18004, 'Serrano V.', 'Alcala 12', '0000-00-00', 'F', 284991452, 18),
(14024, 'Fernández M', 'Recoletos 50', '0000-00-00', 'F', 321790059, 18),
(36658, 'Domin S.', 'Mayor 71', '0000-00-00', 'M', 160654471, 22),
(38702, 'Neal R.', 'Orense 11', '0000-00-00', 'F', 380010217, 45),
(39217, 'Cervantes M.', 'Peron 38', '0000-00-00', 'M', 440294390, 22),
(59076, 'Miller B.', 'López de Hoyos 2', '0000-00-00', 'F', 311969044, 45),
(63827, 'Ruiz P.', 'Esquerdo 103', '0000-00-00', 'M', 100973253, 18),
(64823, 'Fraser A.', 'Soto 3', '0000-00-00', 'F', 285201776, 13),
(74835, 'Benitez E.', 'Argentina 5', '0000-00-00', 'M', 154811767, 22);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hospital`
--

CREATE TABLE `hospital` (
  `hospital_cod` int(2) NOT NULL DEFAULT 0,
  `nombre` varchar(3) DEFAULT NULL,
  `direccion` varchar(5) DEFAULT NULL,
  `telefono` varchar(2) DEFAULT NULL,
  `num_cama` int(4) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hospital`
--

INSERT INTO `hospital` (`hospital_cod`, `nombre`, `direccion`, `telefono`, `num_cama`) VALUES
(13, 'Provincial', 'O Donell 50', '964-4264', 502),
(18, 'General', 'Atocha s/n', '595-3111', 987),
(22, 'La Paz', 'Castellana 1000', '923-5411', 412),
(45, 'San Carlos', 'Ciudad Universitaria', '597-1500', 845);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plantilla`
--

CREATE TABLE `plantilla` (
  `hospital_cod` int(2) DEFAULT NULL,
  `sala_cod` int(2) DEFAULT NULL,
  `empleado_no` int(4) NOT NULL DEFAULT 0,
  `apellido` varchar(4) DEFAULT NULL,
  `funcion` varchar(2) DEFAULT NULL,
  `turno` char(0) DEFAULT NULL,
  `salario` int(6) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `plantilla`
--

INSERT INTO `plantilla` (`hospital_cod`, `sala_cod`, `empleado_no`, `apellido`, `funcion`, `turno`, `salario`) VALUES
(13, 6, 3754, 'Díaz B.', 'Enfermera', 'T', 226200),
(13, 6, 3106, 'Hernández J.', 'Enfermero', 'T', 275000),
(18, 4, 6357, 'Karplus W.', 'Interno', 'T', 337900),
(22, 6, 1009, 'Higueras D.', 'Enfermera', 'T', 200500),
(22, 6, 8422, 'Bocina G.', 'Enfermero', 'M', 183800),
(22, 2, 9901, 'Nuñez C.', 'Interno', 'M', 221000),
(22, 1, 6065, 'Rivera G.', 'Enfermera', 'N', 162600),
(22, 1, 7379, 'Carlos R.', 'Enfermera', 'T', 211900),
(45, 4, 1280, 'AMIGO R.', 'INTERNO', 'N', 221000),
(45, 1, 8526, 'FRANK H.', 'ENFERMERA', 'T', 252200);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala`
--

CREATE TABLE `sala` (
  `hospital_cod` int(2) NOT NULL DEFAULT 0,
  `sala_cod` int(2) NOT NULL DEFAULT 0,
  `nombre` varchar(5) DEFAULT NULL,
  `num_cama` int(4) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sala`
--

INSERT INTO `sala` (`hospital_cod`, `sala_cod`, `nombre`, `num_cama`) VALUES
(13, 3, 'Cuidados intensivos', 21),
(13, 6, 'Psiquiátrico', 67),
(18, 3, 'Cuidados intensivos', 10),
(18, 4, 'Cardiología', 53),
(22, 1, 'Recuperación', 10),
(22, 6, 'Psiquiátrico', 118),
(22, 2, 'Maternidad', 34),
(45, 4, 'Cardiología', 55),
(45, 1, 'Recuperación', 13),
(45, 2, 'Maternidad', 24);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`doctor_no`);

--
-- Indices de la tabla `enfermo`
--
ALTER TABLE `enfermo`
  ADD PRIMARY KEY (`nss`);

--
-- Indices de la tabla `hospital`
--
ALTER TABLE `hospital`
  ADD PRIMARY KEY (`hospital_cod`);

--
-- Indices de la tabla `plantilla`
--
ALTER TABLE `plantilla`
  ADD PRIMARY KEY (`empleado_no`);

--
-- Indices de la tabla `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`hospital_cod`,`sala_cod`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
