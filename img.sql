-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 04-Out-2016 às 03:54
-- Versão do servidor: 10.1.16-MariaDB
-- PHP Version: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tbd`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `img`
--

CREATE TABLE `img` (
  `id` int(11) NOT NULL,
  `nome` varchar(1000) NOT NULL,
  `url` varchar(100) NOT NULL,
  `extensao` varchar(20) NOT NULL,
  `conteudo` longblob NOT NULL,
  `tamanho` bigint(20) NOT NULL,
  `propriedades` longtext NOT NULL,
  `altura` varchar(100) NOT NULL,
  `largura` varchar(100) NOT NULL,
  `criacao` varchar(50) NOT NULL,
  `iso` varchar(200) NOT NULL,
  `abertura` varchar(200) NOT NULL,
  `velocidade` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `img`
--
ALTER TABLE `img`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `img`
--
ALTER TABLE `img`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
