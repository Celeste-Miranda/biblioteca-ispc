-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 05-10-2022 a las 20:27:58
-- Versión del servidor: 10.3.32-MariaDB
-- Versión de PHP: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ande_biblioteca`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `books`
--

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `edit` varchar(255) NOT NULL,
  `lang` varchar(255) NOT NULL,
  `pages` varchar(255) NOT NULL,
  `description` varchar(900) NOT NULL,
  `ejemplares` varchar(255) NOT NULL,
  `stock` int(11) NOT NULL,
  `available` int(11) NOT NULL,
  `img` varchar(400) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `books`
--

INSERT INTO `books` (`id`, `title`, `date`, `author`, `category`, `edit`, `lang`, `pages`, `description`, `ejemplares`, `stock`, `available`, `img`, `created_at`) VALUES
(7, 'Biblia de la Programación 2', '1996', 'Linus Torvalds 2', 'Programación', 'GNU Books', 'Español', '666', 'Fundamentos de Linux hechos con mucha ira. ', '5', 5, 4, NULL, NULL),
(10, 'Biblia de la Programación', '1996', 'Linus Torvalds', 'Programación', 'GNU Books', 'Español', '666', 'Fundamentos de Linux hechos con mucha ira. ', '1', 1, 0, 'https://www.meme-arsenal.com/memes/13b192f6dc414221d388b05a15ce63a4.jpg', NULL),
(11, 'El señor de los anillos', '1954', 'J.R.R. Tolkien', 'Fantasía', 'Minotauro', 'Español', '1216', 'El señor de los anillos es una novela épica de fantasía escrita por el filólogo y académico británico J. R. R. Tolkien. Es la primera parte de la trilogía de El señor de los anillos, que consta de tres volúmenes: El señor de los anillos, Las dos torres y El retorno del rey. La novela narra la historia de un grupo de héroes que emprenden un viaje para destruir el Anillo Único, un artefacto de poder inimaginable que fue creado por el malvado Señor Oscuro Sauron con la intención de dominar a todos los pueblos de la Tierra Media.', '5', 3, 3, 'https://www.planetadelibros.com.ar/usuaris/libros/fotos/137/m_libros/portada_el-senor-de-los-anillos-i-la-comunidad-del-anillo_j-r-r-tolkien_202004261959.jpg', NULL),
(12, 'El Hobbit', '1937', 'J.R.R. Tolkien', 'Fantasía', 'Minotauro', 'Inglés', '320', 'El Hobbit es una novela fantástica escrita por el filólogo y académico británico J. R. R. Tolkien. Es la primera obra de la Tierra Media, el universo ficticio en el que se desarrollan las novelas El señor de los anillos y El Silmarillion. La novela narra las aventuras de Bilbo Bolsón, un hobbit que emprende un viaje junto a trece enanos para recuperar el tesoro que les fue robado por el dragón Smaug.', '5', 3, 3, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/2611/9780261102217.jpg', NULL),
(24, 'Libro Posta', '2022', 'Autor Posta', 'Posta', 'Editorial Posta', 'Inglés', '999', 'La posta ', '10', 10, 10, 'https://municipiosycomunas.com.ar/wp-content/uploads/2019/10/LA-POSTA-550x381.jpg', NULL),
(26, 'Rayuela', '1970', 'Cortazar', 'Novela', 'Mundial', 'Español', '999', 'Descripción de Rayuela ', '10', 10, 10, 'https://upload.wikimedia.org/wikipedia/commons/c/ca/Rayuela_JC.png', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(27);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lendings`
--

CREATE TABLE `lendings` (
  `id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `date_out` date NOT NULL,
  `date_return` date DEFAULT NULL,
  `return_estimate_date` date DEFAULT NULL,
  `user_app_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `lendings`
--

INSERT INTO `lendings` (`id`, `book_id`, `date_out`, `date_return`, `return_estimate_date`, `user_app_id`) VALUES
(44, 11, '2022-10-04', '2022-10-04', '2022-10-19', 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sanc_money` int(11) DEFAULT NULL,
  `sanctions` int(11) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `user_credential_id` bigint(20) NOT NULL,
  `dni` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `address`, `lastname`, `name`, `sanc_money`, `sanctions`, `tel`, `user_credential_id`, `dni`) VALUES
(1, 'calle falsa 123', 'miranda', 'celeste', NULL, NULL, '123123123', 1, '923123123'),
(9, 'Loco', 'Loco', 'Jason', 5000, 1, '2391923912', 9, '2939219311'),
(47, 's', 's', 's', NULL, NULL, '2', 47, '2'),
(48, 'a', 'a', 'aa', NULL, NULL, '2', 48, '2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_credentials`
--

CREATE TABLE `user_credentials` (
  `id` bigint(20) NOT NULL,
  `password` text DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `user_credentials`
--

INSERT INTO `user_credentials` (`id`, `password`, `role`, `username`) VALUES
(1, '$2a$10$WCdxVnTIK0Dz2pRuj8YKbeiv9LOYXuEWzV3tHVeqP6iyK3nsrEkPW', 'ADMINISTRADOR', 'celeste@hot.com'),
(9, '$2a$10$zebYgjJo7wnEFLfKnIGG6OYnHWgGUJZRmBKio.g3cASbQN9sUmunO', 'USUARIO', 'jasoncito@hot.com'),
(47, '$2a$10$h4TsPk00nLp5EXJfIDnEJ.mBnNZ9fjsp5AnoNwnB4uMvptMDP9Lzu', 'USUARIO', 'celest2e@hot.com'),
(48, '$2a$10$hdK5jAxVLRN.azD3P.hIveget71xDjehdGedZ4qfHPdpZLT8rfLqa', 'USUARIO', 'jasoncito2@hot.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `lendings`
--
ALTER TABLE `lendings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKisb7wu7w4af6fpilljihmgonx` (`book_id`),
  ADD KEY `FK74nfsmqwevk8ap644g08oxv3n` (`user_app_id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKly1ou9le2q07j0rs7615l0qx0` (`user_credential_id`);

--
-- Indices de la tabla `user_credentials`
--
ALTER TABLE `user_credentials`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `books`
--
ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `lendings`
--
ALTER TABLE `lendings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT de la tabla `user_credentials`
--
ALTER TABLE `user_credentials`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `lendings`
--
ALTER TABLE `lendings`
  ADD CONSTRAINT `FK74nfsmqwevk8ap644g08oxv3n` FOREIGN KEY (`user_app_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKisb7wu7w4af6fpilljihmgonx` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`);

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKly1ou9le2q07j0rs7615l0qx0` FOREIGN KEY (`user_credential_id`) REFERENCES `user_credentials` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
