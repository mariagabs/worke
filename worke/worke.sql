-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 03, 2021 at 12:16 AM
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
  `Nome` varchar(50) NOT NULL,
  `Descricao` varchar(255) NOT NULL,
  `Imagem` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `instrucao`
--

CREATE TABLE `instrucao` (
  `Id` int(11) NOT NULL,
  `Sequencia` int(11) NOT NULL,
  `ExercicioId` int(11) NOT NULL,
  `Descricao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE `log` (
  `Tabela` varchar(255) NOT NULL,
  `Data` date NOT NULL,
  `Metodo` varchar(255) NOT NULL,
  `Descricao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `premio`
--

CREATE TABLE `premio` (
  `Id` int(11) NOT NULL,
  `Descricao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `Id` int(11) NOT NULL,
  `Login` varchar(80) NOT NULL,
  `Senha` varchar(20) NOT NULL,
  `Nome` varchar(80) NOT NULL,
  `AdmEmpresa` tinyint(1) NOT NULL,
  `Lembrete` varchar(255) DEFAULT NULL,
  `Ranking` int(11) DEFAULT NULL,
  `FraseMotivacional` varchar(255) DEFAULT NULL,
  `PossuiPremio` tinyint(1) NOT NULL,
  `QuantidadeFuncionarios` int(11) DEFAULT NULL,
  `PremioId` int(11) DEFAULT NULL,
  `DuracaoExercicio` double DEFAULT NULL,
  `IntervaloExercicio` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`Id`, `Login`, `Senha`, `Nome`, `AdmEmpresa`, `Lembrete`, `Ranking`, `FraseMotivacional`, `PossuiPremio`, `QuantidadeFuncionarios`, `PremioId`, `DuracaoExercicio`, `IntervaloExercicio`) VALUES
(1, 'raphaelkonichi@gmail.com', 'gilmar123', 'Raphael', 1, 'Vamos todos morrer um dia.', NULL, NULL, 0, 10, NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `exercicio`
--
ALTER TABLE `exercicio`
  ADD PRIMARY KEY (`Id`);

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
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Login` (`Login`),
  ADD KEY `PremioId` (`PremioId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `exercicio`
--
ALTER TABLE `exercicio`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `instrucao`
--
ALTER TABLE `instrucao`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `premio`
--
ALTER TABLE `premio`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `instrucao`
--
ALTER TABLE `instrucao`
  ADD CONSTRAINT `instrucao_ibfk_1` FOREIGN KEY (`ExercicioId`) REFERENCES `exercicio` (`Id`);

--
-- Constraints for table `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`PremioId`) REFERENCES `premio` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
