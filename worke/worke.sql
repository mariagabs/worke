-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 28, 2021 at 03:19 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `worke`
--

-- --------------------------------------------------------

--
-- Table structure for table `exercicio`
--

CREATE TABLE `exercicio` (
  `Id` int(11) NOT NULL,
  `Nome` varchar(200) NOT NULL,
  `Descricao` varchar(200) NOT NULL,
  `Imagem` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `exercicio`
--

INSERT INTO `exercicio` (`Id`, `Nome`, `Descricao`, `Imagem`) VALUES
(1, 'Alongamento na parte superior', 'Alongamento na parte superior', 'AlongamentoSuperior'),
(2, 'Alongamento na parte inferior', 'Alongamento na parte inferior', 'AlongamentoInferior'),
(3, 'Meditação', 'Meditação', 'Meditacao'),
(4, 'Sequência de abdominal', 'Sequência de abdominal', 'SequenciaAbdominal'),
(5, 'Alongamento inferior', 'Alongamento inferior', 'AlongamentoEquilibrio'),
(6, 'Sequência de agachamentos', 'Sequência de agachamentos', 'SequenciaAgachamentos'),
(7, 'Sequência de flexões', 'Sequência de flexões', 'SequenciaFlexoes'),
(8, 'Alongamento das pernas', 'Alongamento das pernas', 'AlongamentoPernas'),
(9, 'Alongamento corporal', 'Alongamento corporal', 'AlongamentoCorporal'),
(11, 'Polichinelo', 'Polichinelo', 'Polichinelo'),
(12, 'Equilíbrio e meditação', 'Equilíbrio e meditação', 'EquilibrioMeditacao'),
(13, 'Equilíbrio e foco', 'Equilíbrio e foco', 'EquilibrioFoco'),
(14, 'Aquecimento corporal', 'Aquecimento corporal', 'AquecimentoCorporal'),
(15, 'Alongamento corporal sentado', 'Alongamento corporal sentado', 'AlongamentoSentado'),
(16, 'Alongamento', 'Alongamento', 'Alongamento'),
(17, 'Alongamento com apoio', 'Alongamento com apoio', 'AlongamentoApoio');

-- --------------------------------------------------------

--
-- Table structure for table `exercicio_escolhido`
--

CREATE TABLE `exercicio_escolhido` (
  `Id` int(11) NOT NULL,
  `ExercicioId` int(11) NOT NULL,
  `DataExecucao` date DEFAULT NULL,
  `RotinaId` int(11) NOT NULL,
  `Duracao` int(11) NOT NULL,
  `QntRealizado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `exercicio_escolhido`
--

INSERT INTO `exercicio_escolhido` (`Id`, `ExercicioId`, `DataExecucao`, `RotinaId`, `Duracao`, `QntRealizado`) VALUES
(1, 5, NULL, 1, 1, 0),
(2, 9, NULL, 3, 1, 0),
(3, 11, NULL, 3, 1, 0),
(4, 12, NULL, 3, 1, 0),
(9, 9, NULL, 5, 5, 0),
(10, 11, NULL, 5, 5, 0),
(11, 12, NULL, 5, 5, 0),
(12, 1, NULL, 6, 5, 0),
(13, 2, NULL, 6, 5, 0),
(14, 15, NULL, 6, 5, 0),
(15, 15, NULL, 7, 5, 0),
(16, 6, NULL, 7, 5, 0),
(17, 2, NULL, 7, 5, 0),
(18, 1, NULL, 8, 5, 0),
(19, 4, NULL, 8, 5, 0),
(20, 8, NULL, 8, 5, 0),
(21, 3, NULL, 9, 5, 0),
(22, 11, NULL, 9, 5, 0),
(23, 16, NULL, 9, 5, 0),
(24, 3, NULL, 10, 5, 0),
(25, 11, NULL, 10, 5, 0),
(26, 16, NULL, 10, 5, 0),
(27, 12, NULL, 10, 5, 0),
(28, 3, NULL, 11, 5, 0),
(29, 11, NULL, 11, 5, 0),
(30, 16, NULL, 11, 5, 0),
(31, 12, NULL, 11, 5, 0),
(32, 3, NULL, 12, 5, 0),
(33, 11, NULL, 12, 5, 0),
(34, 16, NULL, 12, 5, 0),
(35, 12, NULL, 12, 5, 0),
(36, 3, NULL, 13, 5, 0),
(37, 11, NULL, 13, 5, 0),
(38, 16, NULL, 13, 5, 0),
(39, 12, NULL, 13, 5, 0),
(40, 3, NULL, 14, 5, 0),
(41, 11, NULL, 14, 5, 0),
(42, 16, NULL, 14, 5, 0),
(43, 12, NULL, 14, 5, 0),
(44, 3, NULL, 15, 5, 0),
(45, 11, NULL, 15, 5, 0),
(46, 16, NULL, 15, 5, 0),
(47, 12, NULL, 15, 5, 0),
(48, 13, NULL, 15, 5, 0),
(49, 3, NULL, 16, 5, 0),
(50, 11, NULL, 16, 5, 0),
(51, 16, NULL, 16, 5, 0),
(52, 12, NULL, 16, 5, 0),
(53, 13, NULL, 16, 5, 0),
(54, 14, NULL, 16, 5, 0),
(55, 3, NULL, 17, 5, 0),
(56, 11, NULL, 17, 5, 0),
(57, 16, NULL, 17, 5, 0),
(58, 12, NULL, 17, 5, 0),
(59, 13, NULL, 17, 5, 0),
(60, 3, NULL, 18, 5, 0),
(61, 3, NULL, 19, 5, 0),
(62, 12, NULL, 19, 5, 0),
(63, 13, NULL, 19, 5, 0),
(64, 3, NULL, 20, 5, 0),
(65, 12, NULL, 20, 5, 0),
(66, 13, NULL, 20, 5, 0),
(67, 14, NULL, 20, 5, 0),
(68, 3, NULL, 21, 5, 0),
(69, 12, NULL, 21, 5, 0),
(70, 13, NULL, 21, 5, 0),
(71, 14, NULL, 21, 5, 0),
(72, 1, NULL, 21, 5, 0),
(73, 2, NULL, 21, 5, 0),
(74, 11, NULL, 21, 5, 0),
(75, 9, NULL, 21, 5, 0),
(76, 4, NULL, 21, 5, 0),
(77, 5, NULL, 21, 5, 0),
(78, 15, NULL, 21, 5, 0),
(79, 16, NULL, 21, 5, 0),
(80, 7, NULL, 21, 5, 0),
(81, 6, NULL, 21, 5, 0),
(82, 8, NULL, 21, 5, 0),
(83, 17, NULL, 21, 5, 0),
(84, 12, NULL, 22, 5, 0),
(85, 9, NULL, 22, 5, 0),
(86, 12, NULL, 23, 5, 0),
(87, 9, NULL, 23, 5, 0),
(88, 12, NULL, 24, 5, 0),
(89, 9, NULL, 24, 5, 0),
(90, 12, NULL, 25, 5, 0),
(91, 9, NULL, 25, 5, 0),
(92, 13, NULL, 25, 5, 0),
(93, 12, NULL, 26, 5, 0),
(94, 9, NULL, 26, 5, 0),
(95, 12, NULL, 27, 5, 0),
(96, 9, NULL, 27, 5, 0),
(97, 1, NULL, 27, 5, 0),
(98, 11, NULL, 27, 5, 0),
(99, 2, NULL, 27, 5, 0),
(100, 4, NULL, 27, 5, 0),
(101, 13, NULL, 27, 5, 0),
(102, 3, NULL, 27, 5, 0),
(103, 5, NULL, 27, 5, 0),
(104, 14, NULL, 27, 5, 0),
(105, 15, NULL, 27, 5, 0),
(106, 6, NULL, 27, 5, 0),
(107, 7, NULL, 27, 5, 0),
(108, 16, NULL, 27, 5, 0),
(109, 8, NULL, 27, 5, 0),
(110, 17, NULL, 27, 5, 0),
(111, 9, NULL, 28, 5, 0),
(112, 11, NULL, 28, 5, 0),
(113, 9, NULL, 29, 5, 0),
(114, 11, NULL, 29, 5, 0),
(115, 2, NULL, 29, 5, 0),
(116, 9, NULL, 30, 5, 0),
(117, 11, NULL, 30, 5, 0),
(118, 2, NULL, 30, 5, 0),
(119, 1, NULL, 31, 5, 0),
(120, 15, NULL, 31, 5, 0),
(121, 6, NULL, 31, 5, 0),
(122, 8, NULL, 31, 5, 0),
(123, 17, NULL, 31, 5, 0),
(124, 1, NULL, 32, 5, 0),
(125, 6, NULL, 32, 5, 0),
(126, 8, NULL, 32, 5, 0),
(127, 17, NULL, 32, 5, 0),
(128, 1, NULL, 33, 5, 0),
(129, 6, NULL, 33, 5, 0),
(130, 8, NULL, 33, 5, 0),
(131, 17, NULL, 33, 5, 0),
(132, 15, NULL, 33, 5, 0),
(133, 7, NULL, 33, 5, 0),
(134, 1, NULL, 34, 3, 0),
(135, 6, NULL, 34, 3, 0),
(136, 8, NULL, 34, 3, 0),
(137, 17, NULL, 34, 3, 0),
(138, 15, NULL, 34, 3, 0),
(139, 7, NULL, 34, 3, 0),
(140, 1, NULL, 35, 3, 0),
(141, 6, NULL, 35, 3, 0),
(142, 8, NULL, 35, 3, 0),
(143, 17, NULL, 35, 3, 0),
(144, 15, NULL, 35, 3, 0),
(145, 7, NULL, 35, 3, 0),
(176, 1, NULL, 43, 3, 0),
(177, 7, NULL, 43, 3, 0),
(178, 8, NULL, 43, 3, 0),
(179, 17, NULL, 43, 3, 0),
(180, 1, NULL, 44, 1, 0),
(181, 7, '2021-05-18', 44, 1, 0),
(182, 8, NULL, 44, 1, 0),
(183, 17, '2021-05-18', 44, 1, 0),
(184, 1, NULL, 45, 2, 0),
(185, 7, NULL, 45, 2, 0),
(186, 8, NULL, 45, 2, 0),
(187, 17, NULL, 45, 2, 0),
(188, 1, NULL, 46, 2, 0),
(189, 7, NULL, 46, 2, 0),
(190, 8, NULL, 46, 2, 0),
(191, 17, NULL, 46, 2, 0),
(192, 1, NULL, 47, 2, 0),
(193, 7, NULL, 47, 2, 0),
(194, 8, NULL, 47, 2, 0),
(195, 17, NULL, 47, 2, 0),
(196, 1, NULL, 48, 2, 0),
(197, 7, NULL, 48, 2, 0),
(198, 8, NULL, 48, 2, 0),
(199, 17, NULL, 48, 2, 0),
(200, 1, NULL, 49, 2, 0),
(201, 7, NULL, 49, 2, 0),
(202, 8, NULL, 49, 2, 0),
(203, 17, NULL, 49, 2, 0),
(204, 2, NULL, 49, 2, 0),
(205, 1, '2021-05-21', 50, 2, 6),
(206, 7, '2021-05-21', 50, 2, 2),
(207, 8, '2021-05-20', 50, 2, 2),
(208, 17, '2021-05-21', 50, 2, 2),
(209, 6, NULL, 52, 6, 0),
(210, 15, NULL, 52, 6, 0),
(211, 17, NULL, 52, 6, 0),
(212, 8, NULL, 52, 6, 0),
(213, 6, NULL, 53, 4, 0),
(214, 15, NULL, 53, 4, 0),
(215, 17, NULL, 53, 4, 0),
(216, 8, NULL, 53, 4, 0),
(217, 6, NULL, 54, 4, 0),
(218, 15, NULL, 54, 4, 0),
(219, 17, NULL, 54, 4, 0),
(220, 8, NULL, 54, 4, 0),
(221, 6, NULL, 55, 2, 0),
(222, 15, NULL, 55, 2, 0),
(223, 17, NULL, 55, 2, 0),
(224, 8, NULL, 55, 2, 0),
(225, 6, NULL, 56, 2, 0),
(226, 15, NULL, 56, 2, 0),
(227, 17, NULL, 56, 2, 0),
(228, 8, '2021-05-21', 56, 2, 1),
(229, 3, '2021-05-21', 56, 2, 1),
(236, 3, '2021-05-22', 59, 1, 11),
(237, 2, '2021-05-22', 60, 1, 3),
(343, 3, '2021-05-23', 95, 1, 2),
(344, 3, NULL, 96, 1, 0),
(345, 17, NULL, 96, 1, 0),
(346, 12, NULL, 96, 1, 0),
(347, 6, NULL, 97, 1, 0),
(348, 3, NULL, 98, 1, 0),
(349, 12, NULL, 98, 1, 0),
(350, 17, NULL, 98, 1, 0),
(351, 7, NULL, 98, 1, 0),
(352, 3, NULL, 99, 1, 0),
(353, 12, '2021-05-26', 99, 1, 1),
(354, 17, NULL, 99, 1, 0),
(355, 7, '2021-05-25', 99, 1, 1),
(356, 16, NULL, 99, 1, 0),
(357, 4, NULL, 99, 1, 0),
(358, 5, NULL, 99, 1, 0),
(359, 3, NULL, 100, 1, 0),
(360, 4, NULL, 100, 1, 0),
(361, 5, NULL, 100, 1, 0),
(362, 7, NULL, 100, 1, 0),
(363, 12, NULL, 100, 1, 0),
(364, 16, NULL, 100, 1, 0),
(365, 17, NULL, 100, 1, 0),
(366, 8, NULL, 102, 2, 0),
(367, 5, NULL, 102, 2, 0),
(368, 4, '2021-05-26', 102, 2, 1),
(369, 3, NULL, 103, 3, 0),
(370, 4, NULL, 103, 3, 0),
(371, 5, NULL, 103, 3, 0),
(372, 7, '2021-05-27', 103, 3, 2),
(373, 12, NULL, 103, 3, 0),
(374, 16, NULL, 103, 3, 0),
(375, 17, NULL, 103, 3, 0),
(376, 3, '2021-05-27', 104, 1, 1),
(377, 4, NULL, 104, 1, 0),
(378, 5, NULL, 104, 1, 0),
(379, 7, NULL, 104, 1, 0),
(380, 12, NULL, 104, 1, 0),
(381, 16, NULL, 104, 1, 0),
(382, 17, NULL, 104, 1, 0),
(383, 3, '2021-05-27', 105, 1, 1),
(384, 4, NULL, 105, 1, 0),
(385, 5, NULL, 105, 1, 0),
(386, 7, NULL, 105, 1, 0),
(387, 12, NULL, 105, 1, 0),
(388, 16, NULL, 105, 1, 0),
(389, 17, NULL, 105, 1, 0),
(390, 3, '2021-05-27', 106, 1, 4),
(391, 4, NULL, 106, 1, 0),
(392, 5, NULL, 106, 1, 0),
(393, 7, NULL, 106, 1, 0),
(394, 12, NULL, 106, 1, 0),
(395, 16, NULL, 106, 1, 0),
(396, 17, NULL, 106, 1, 0),
(397, 11, NULL, 108, 1, 0),
(398, 12, NULL, 108, 1, 0),
(399, 13, NULL, 108, 1, 0),
(400, 3, NULL, 108, 1, 0),
(401, 11, NULL, 109, 1, 0),
(402, 12, NULL, 109, 1, 0),
(403, 13, NULL, 109, 1, 0),
(404, 3, NULL, 109, 1, 0),
(405, 11, NULL, 110, 1, 0),
(406, 12, NULL, 110, 1, 0),
(407, 13, '2021-05-27', 110, 1, 1),
(408, 3, '2021-05-27', 110, 1, 1),
(409, 3, '2021-05-27', 111, 1, 1),
(410, 4, NULL, 111, 1, 0),
(411, 5, NULL, 111, 1, 0),
(412, 7, NULL, 111, 1, 0),
(413, 12, NULL, 111, 1, 0),
(414, 16, NULL, 111, 1, 0),
(415, 17, NULL, 111, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `instrucao`
--

CREATE TABLE `instrucao` (
  `Id` int(11) NOT NULL,
  `Sequencia` int(11) NOT NULL,
  `ExercicioId` int(11) NOT NULL,
  `Descricao` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `instrucao`
--

INSERT INTO `instrucao` (`Id`, `Sequencia`, `ExercicioId`, `Descricao`) VALUES
(1, 1, 1, 'Inspire'),
(2, 2, 1, 'Expire'),
(3, 3, 1, 'Mantenha-se focado no exercício'),
(4, 4, 1, 'Afaste as pernas '),
(5, 5, 1, 'Coloque uma mão na cintura'),
(6, 6, 1, 'Passe a outra por cima da cabeça '),
(7, 1, 2, 'Inspire'),
(8, 2, 2, 'Expire'),
(9, 3, 2, 'Mantenha-se focado no exercício'),
(10, 4, 2, 'Afaste as pernas'),
(11, 5, 2, 'Coloque ambas as mãos na cintura'),
(12, 6, 2, 'Movimente seu tronco'),
(13, 1, 3, 'Inspire'),
(14, 2, 3, 'Expire'),
(15, 3, 3, 'Mantenha-se focado'),
(16, 4, 3, 'Relaxe'),
(17, 5, 3, 'Feche os olhos e concentre-se na respiração'),
(22, 1, 4, 'Abra espaço em seu ambiente'),
(23, 2, 4, 'Deite-se'),
(24, 3, 4, 'Flexione as pernas'),
(25, 4, 4, 'Inicie o abdominal'),
(26, 5, 4, 'Mantenha-se focado no exercício'),
(27, 1, 5, 'Abra espaço em seu ambiente'),
(28, 2, 5, 'Apoie-se no joelho e nos braços'),
(29, 3, 5, 'De forma que você fique virado para baixo'),
(30, 4, 5, 'Erga uma das pernas'),
(31, 5, 5, 'Mantenha-se focado'),
(32, 6, 5, 'Alterne as pernas'),
(33, 1, 6, 'Afaste levemente as pernas'),
(34, 2, 6, 'Levante seus braços'),
(35, 3, 6, 'Flexione o joelho'),
(36, 4, 6, 'Repita os movimentos'),
(37, 5, 6, 'Mantenha-se focado no exercício'),
(38, 1, 7, 'Abra espaço em seu ambiente'),
(39, 2, 7, 'Deite-se de barriga para baixo'),
(40, 3, 7, 'Apoie-se no braços'),
(41, 4, 7, 'Erga o corpo'),
(42, 5, 7, 'Repita o movimento'),
(43, 6, 7, 'Mantenha-se focado no exercício'),
(44, 1, 8, 'Abra espaço em seu ambiente'),
(45, 2, 8, 'Deite-se de barriga para cima'),
(46, 3, 8, 'Erga as pernas a 90º'),
(47, 4, 8, 'Inspire'),
(48, 5, 8, 'Expire'),
(49, 6, 8, 'Mantenha-se focado no exercício'),
(50, 1, 9, 'Inspire'),
(51, 2, 9, 'Expire'),
(52, 3, 9, 'Mantenha-se focado no exercício'),
(53, 4, 9, 'Erga ambos os braços'),
(54, 5, 9, 'Posicione uma perna na frente da outra'),
(55, 6, 9, 'Flexione a perna que está na frente'),
(56, 7, 9, 'Repita o movimento'),
(57, 1, 11, 'Inspire'),
(58, 2, 11, 'Expire'),
(59, 3, 11, 'Mantenha-se focado no exercício'),
(60, 4, 11, 'Erga os braços juntos para cima'),
(61, 5, 11, 'Ao pular, abra as pernas e os braços'),
(62, 6, 11, 'Repita o movimento'),
(63, 1, 12, 'Inspire'),
(64, 2, 12, 'Expire'),
(65, 3, 12, 'Mantenha-se focado'),
(66, 4, 12, 'Abra os braços'),
(67, 5, 12, 'Coloque um de seus pés no joelho da outra perna'),
(68, 6, 12, 'Foque em um ponto'),
(69, 7, 12, 'Teste seu equilíbrio'),
(70, 1, 13, 'Inspire'),
(71, 2, 13, 'Expire'),
(72, 3, 13, 'Mantenha-se focado'),
(73, 4, 13, 'Olhe para a palma de uma das mãos'),
(74, 5, 13, 'Mantenha o outro braço rente ao corpo'),
(75, 6, 13, 'Erga e flexione uma das pernas'),
(76, 7, 13, 'Foque na palma de sua mão'),
(77, 8, 13, 'Teste seu foco'),
(78, 1, 14, 'Inspire'),
(79, 2, 14, 'Expire'),
(80, 3, 14, 'Mantenha-se focado no exercício'),
(81, 4, 14, 'Afaste as pernas'),
(82, 5, 14, 'Tente alcançar seu pé com uma das mãos'),
(83, 1, 15, 'Inspire'),
(84, 2, 15, 'Expire'),
(85, 3, 15, 'Mantenha-se focado no exercício'),
(86, 4, 15, 'Abra espaço em seu ambiente'),
(87, 5, 15, 'Sente-se com as pernas esticadas para frente'),
(88, 6, 15, 'Tente alcançar os pés com as suas mãos'),
(89, 1, 16, 'Inspire'),
(90, 2, 16, 'Expire'),
(91, 3, 16, 'Mantenha-se focado no exercício'),
(92, 4, 16, 'Abra espaço em seu ambiente'),
(93, 5, 16, 'Deite-se de barriga para baixo'),
(94, 6, 16, 'Apoie-se nas mãos e nos pés'),
(95, 7, 16, 'De forma que seu quadril fique para cima'),
(96, 1, 17, 'Inspire'),
(97, 2, 17, 'Expire'),
(98, 3, 17, 'Mantenha-se focado no exercício'),
(99, 4, 17, 'Apoie uma mão na cadeira de trabalho'),
(100, 5, 17, 'Erga uma das pernas');

-- --------------------------------------------------------

--
-- Table structure for table `premio`
--

CREATE TABLE `premio` (
  `Id` int(11) NOT NULL,
  `Descricao` varchar(200) NOT NULL,
  `Finalizado` int(11) DEFAULT NULL,
  `UsuarioId` int(11) DEFAULT NULL,
  `DataFinal` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `premio`
--

INSERT INTO `premio` (`Id`, `Descricao`, `Finalizado`, `UsuarioId`, `DataFinal`) VALUES
(7, 'Chocolate', 1, 9, '2021-05-25'),
(10, 'MacBook', 1, 9, '2021-05-13'),
(11, 'iPhone 10.000', 1, 9, '2021-05-10'),
(12, 'Aruba', 1, 9, '2021-05-19'),
(13, 'Apple Watch', 1, 9, '2021-04-28'),
(14, 'Chocolate Lindt', 1, 9, '2021-05-23'),
(15, 'Vale compras C&A', 1, 9, '2021-05-15'),
(16, 'Galaxy Z Flip Ultravioleta', 1, 9, '2021-04-30'),
(29, 'MacBook Air', 0, NULL, NULL),
(30, 'MacBook Air', 1, 9, '2021-05-27');

-- --------------------------------------------------------

--
-- Table structure for table `rotina_exercicios`
--

CREATE TABLE `rotina_exercicios` (
  `Id` int(11) NOT NULL,
  `QuantidadeExercicios` int(11) NOT NULL,
  `DuracaoTotalExercicios` int(11) NOT NULL,
  `UsuarioId` int(11) DEFAULT NULL,
  `DataCriacao` datetime DEFAULT NULL,
  `QntExerciciosDisponivel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rotina_exercicios`
--

INSERT INTO `rotina_exercicios` (`Id`, `QuantidadeExercicios`, `DuracaoTotalExercicios`, `UsuarioId`, `DataCriacao`, `QntExerciciosDisponivel`) VALUES
(1, 1, 0, 9, '2021-05-14 00:00:00', 0),
(3, 3, 0, 9, '2021-05-14 21:14:00', 0),
(5, 3, 0, 9, NULL, 0),
(6, 3, 0, 9, NULL, 0),
(7, 3, 0, 9, '2021-05-15 00:14:29', 0),
(8, 3, 0, 9, '2021-05-15 00:17:14', 0),
(9, 3, 0, 9, '2021-05-15 00:20:26', 0),
(10, 4, 0, 9, '2021-05-15 00:24:31', 0),
(11, 4, 0, 9, '2021-05-15 01:34:18', 0),
(12, 4, 0, 9, '2021-05-15 01:35:46', 0),
(13, 4, 0, 9, '2021-05-15 01:35:51', 0),
(14, 4, 0, 9, '2021-05-15 01:35:56', 0),
(15, 5, 0, 9, '2021-05-15 01:49:40', 0),
(16, 6, 0, 9, '2021-05-15 01:49:54', 0),
(17, 5, 0, 9, '2021-05-15 01:55:59', 0),
(18, 6, 0, 9, '2021-05-15 01:57:04', 0),
(19, 3, 0, 9, '2021-05-15 01:59:30', 0),
(20, 4, 0, 9, '2021-05-15 01:59:47', 0),
(21, 16, 0, 9, '2021-05-15 02:01:42', 0),
(22, 2, 0, 9, '2021-05-15 02:05:07', 0),
(23, 2, 0, 9, '2021-05-15 02:05:10', 0),
(24, 2, 0, 9, '2021-05-15 02:06:03', 0),
(25, 3, 0, 9, '2021-05-15 02:09:41', 0),
(26, 2, 0, 9, '2021-05-15 02:09:45', 0),
(27, 16, 0, 9, '2021-05-15 02:09:54', 0),
(28, 2, 0, 9, '2021-05-15 02:10:00', 0),
(29, 3, 0, 9, '2021-05-15 02:10:54', 0),
(30, 3, 0, 9, '2021-05-15 02:11:37', 0),
(31, 5, 0, 9, '2021-05-15 12:40:57', 0),
(32, 4, 0, 9, '2021-05-15 12:41:44', 0),
(33, 6, 0, 9, '2021-05-15 12:42:25', 0),
(34, 6, 0, 9, '2021-05-15 12:42:37', 0),
(35, 6, 0, 9, '2021-05-15 12:44:36', 0),
(43, 4, 0, 9, '2021-05-15 16:34:08', 0),
(44, 4, 2, 9, '2021-05-18 20:17:59', 0),
(45, 4, 0, 9, '2021-05-18 22:47:32', 0),
(46, 4, 0, 9, '2021-05-18 22:48:33', 0),
(47, 4, 0, 9, '2021-05-18 22:49:15', 0),
(48, 4, 0, 9, '2021-05-18 22:49:44', 0),
(49, 5, 0, 9, '2021-05-18 22:50:05', 0),
(50, 4, 28, 9, '2021-05-18 22:50:16', 0),
(51, 4, 0, 9, '2021-05-21 20:44:46', 0),
(52, 4, 0, 9, '2021-05-21 20:47:46', 0),
(53, 4, 0, 9, '2021-05-21 20:48:02', 0),
(54, 4, 0, 9, '2021-05-21 20:48:06', 0),
(55, 4, 0, 9, '2021-05-21 20:48:14', 0),
(56, 5, 4, 9, '2021-05-21 20:48:17', 0),
(59, 1, 16, 9, '2021-05-21 23:54:52', 0),
(60, 1, 3, 2, '2021-05-22 01:08:51', 0),
(95, 1, 2, 9, '2021-05-22 23:57:16', 14),
(96, 3, 0, 9, '2021-05-23 18:32:19', 16),
(97, 1, 0, 48, '2021-05-24 22:52:15', 1),
(98, 4, 0, 9, '2021-05-25 23:48:54', 16),
(99, 7, 2, 9, '2021-05-25 23:49:10', 14),
(100, 7, 0, 9, '2021-05-26 00:35:17', 16),
(101, 0, 0, 52, '2021-05-26 22:39:44', 0),
(102, 3, 2, 52, '2021-05-26 22:40:05', -1),
(103, 7, 2, 9, '2021-05-27 00:13:54', 14),
(104, 7, 1, 9, '2021-05-27 00:17:44', 13),
(105, 7, 1, 9, '2021-05-27 00:20:56', 12),
(106, 7, 4, 9, '2021-05-27 00:24:30', 0),
(107, 0, 0, 54, '2021-05-27 18:31:17', 0),
(108, 4, 0, 54, '2021-05-27 18:35:19', 16),
(109, 4, 0, 54, '2021-05-27 18:35:30', 16),
(110, 4, 2, 54, '2021-05-27 18:35:56', 8),
(111, 7, 1, 9, '2021-05-27 18:44:17', 15);

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `Id` int(11) NOT NULL,
  `Nome` varchar(200) NOT NULL,
  `Login` varchar(80) NOT NULL,
  `Senha` varchar(20) NOT NULL,
  `AdmEmpresa` tinyint(1) NOT NULL,
  `FraseMotivacional` varchar(200) DEFAULT NULL,
  `Lembrete` varchar(200) DEFAULT NULL,
  `PossuiPremio` tinyint(1) NOT NULL,
  `PremioId` int(11) DEFAULT NULL,
  `QuantidadeFuncionarios` int(11) DEFAULT NULL,
  `Ranking` int(11) DEFAULT NULL,
  `DuracaoExercicio` double DEFAULT NULL,
  `IntervaloExercicio` time DEFAULT NULL,
  `HorarioInicio` varchar(200) DEFAULT NULL,
  `HorarioTermino` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`Id`, `Nome`, `Login`, `Senha`, `AdmEmpresa`, `FraseMotivacional`, `Lembrete`, `PossuiPremio`, `PremioId`, `QuantidadeFuncionarios`, `Ranking`, `DuracaoExercicio`, `IntervaloExercicio`, `HorarioInicio`, `HorarioTermino`) VALUES
(1, 'Worke', 'admin@worke.com', 'worke123', 1, 'Você é nosso maior projeto!', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'Raphael Konichi de Moraes', 'raphaelkonichi@gmail.com', 'raphael', 0, NULL, 'Estudar', 0, NULL, NULL, NULL, 1, '03:00:00', '09:00', '16:00'),
(9, 'Laura Bento', 'laurinha@gmail.com', 'laura', 0, NULL, 'Bela, Sagwa, Cheetos, Kira, Jorginho, Ella', 0, NULL, NULL, NULL, 1, '00:30:00', '08:00', '17:00'),
(48, 'teste', 'teste', 'teste', 0, NULL, NULL, 0, NULL, NULL, NULL, 1, '01:00:00', '08:00', '10:00'),
(52, 'Isabelle Okuma', 'isaokuma@gmail.com', 'isa', 0, NULL, 'Sou a isa', 0, NULL, NULL, NULL, 2, '02:00:00', '09:00', '16:00'),
(53, 'teste 2', 'teste@gmail.com', 'Trocar123*', 0, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(54, 'Maria Gabriela', 'maria@hotmail.com', 'maria', 0, NULL, NULL, 0, NULL, NULL, NULL, 1, '00:30:00', '08:00', '14:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `exercicio`
--
ALTER TABLE `exercicio`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `exercicio_escolhido`
--
ALTER TABLE `exercicio_escolhido`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_exercicioId` (`ExercicioId`),
  ADD KEY `fk_rotinaId` (`RotinaId`);

--
-- Indexes for table `instrucao`
--
ALTER TABLE `instrucao`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `ExercicioId` (`ExercicioId`);

--
-- Indexes for table `premio`
--
ALTER TABLE `premio`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `rotina_exercicios`
--
ALTER TABLE `rotina_exercicios`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_usuarioId` (`UsuarioId`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Login` (`Id`),
  ADD KEY `PremioId` (`PremioId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `exercicio`
--
ALTER TABLE `exercicio`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `exercicio_escolhido`
--
ALTER TABLE `exercicio_escolhido`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=417;

--
-- AUTO_INCREMENT for table `instrucao`
--
ALTER TABLE `instrucao`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT for table `premio`
--
ALTER TABLE `premio`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `rotina_exercicios`
--
ALTER TABLE `rotina_exercicios`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=114;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `exercicio_escolhido`
--
ALTER TABLE `exercicio_escolhido`
  ADD CONSTRAINT `fk_exercicioId` FOREIGN KEY (`ExercicioId`) REFERENCES `exercicio` (`Id`),
  ADD CONSTRAINT `fk_rotinaId` FOREIGN KEY (`RotinaId`) REFERENCES `rotina_exercicios` (`Id`);

--
-- Constraints for table `instrucao`
--
ALTER TABLE `instrucao`
  ADD CONSTRAINT `instrucao_ibfk_1` FOREIGN KEY (`ExercicioId`) REFERENCES `exercicio` (`Id`);

--
-- Constraints for table `rotina_exercicios`
--
ALTER TABLE `rotina_exercicios`
  ADD CONSTRAINT `fk_usuarioId` FOREIGN KEY (`UsuarioId`) REFERENCES `usuario` (`Id`);

--
-- Constraints for table `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`PremioId`) REFERENCES `premio` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
