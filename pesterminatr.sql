-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 07, 2019 at 11:06 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.1.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pesterminatr`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `admin_name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `admin_name`, `username`, `password`, `user_type`) VALUES
(1, 'Tudechristian', 'admintude', 'admintude', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `calendar`
--

CREATE TABLE `calendar` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `start` datetime NOT NULL,
  `end` date NOT NULL,
  `create_by` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `calendar`
--

INSERT INTO `calendar` (`id`, `title`, `description`, `start`, `end`, `create_by`) VALUES
(1, 'sample', 'sample', '0000-00-00 00:00:00', '2018-12-28', 2),
(2, 'test', 'test', '0000-00-00 00:00:00', '2018-12-28', 2),
(3, 'sample', 'sample', '0000-00-00 00:00:00', '0000-00-00', 2),
(4, 'sample', 'sample', '0000-00-00 00:00:00', '0000-00-00', 2),
(5, 'sample', 'sample', '0000-00-00 00:00:00', '2018-12-21', 2),
(6, 'sample', 'sample', '0000-00-00 00:00:00', '0000-00-00', 2),
(7, 'sample', 'sample', '0000-00-00 00:00:00', '2018-12-28', 2),
(8, 'sample', 'sample', '0000-00-00 00:00:00', '2018-12-28', 2),
(9, 'sample', 'sample', '0000-00-00 00:00:00', '0000-00-00', 2);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `client_id` int(11) NOT NULL,
  `client_name` varchar(255) NOT NULL,
  `client_lastname` varchar(255) NOT NULL,
  `client_address` varchar(255) NOT NULL,
  `client_contact` varchar(15) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `user_type` varchar(255) NOT NULL DEFAULT 'Client',
  `status` varchar(255) NOT NULL DEFAULT 'Active',
  `notification` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`client_id`, `client_name`, `client_lastname`, `client_address`, `client_contact`, `username`, `password`, `user_type`, `status`, `notification`) VALUES
(35, 'anthony peter', 'dayondon', 'cebu city', '09334445566', 'anthony', 'dayondon', 'Client', 'Active', 0),
(36, 'fhage', 'tigley', 'cebu city', '09335336647', 'fhage123', 'fhage123', 'Client', 'Active', 0),
(37, 'thelmas', 'miral', 'cebu city', '09121253347', 'thelma', 'thelma123', 'Client', 'Active', 0),
(38, 'Christian', 'Tude', 'Brgy Pasil Cebu City', '09225052112', 'tudechristian3', 'janielletude123', 'Client', 'Active', 1);

-- --------------------------------------------------------

--
-- Table structure for table `clients_log`
--

CREATE TABLE `clients_log` (
  `logs_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `action` varchar(255) NOT NULL,
  `time_in` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clients_log`
--

INSERT INTO `clients_log` (`logs_id`, `client_id`, `action`, `time_in`) VALUES
(147, 35, 'Online', '2019-02-15 14:25:09'),
(148, 37, 'Online', '2019-02-15 23:52:38'),
(149, 36, 'Online', '2019-02-16 01:24:23');

-- --------------------------------------------------------

--
-- Table structure for table `client_commercial`
--

CREATE TABLE `client_commercial` (
  `commercial_client_id` int(11) NOT NULL,
  `c_name` varchar(255) NOT NULL,
  `c_last` varchar(255) NOT NULL,
  `c_address` varchar(255) NOT NULL,
  `c_contact` varchar(12) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_type` varchar(255) NOT NULL DEFAULT 'Commercial',
  `Status` varchar(255) NOT NULL DEFAULT 'Active'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client_commercial`
--

INSERT INTO `client_commercial` (`commercial_client_id`, `c_name`, `c_last`, `c_address`, `c_contact`, `username`, `password`, `user_type`, `Status`) VALUES
(1, 'SM', '', 'SRP Cebu City', '09335336647', 'smcity', 'smcity', 'Commercial', 'Active'),
(2, 'Glojam', '', 'Panganiban Street', '234321', 'glojam', 'glojam', 'Commercial', 'Active'),
(3, 'ocslending', '', 'C.padilla Street', '1234', 'ocslending', 'ocslending', 'Commercial', 'Active'),
(6, 'Buenavista', 'Residence', 'mabolo st cebu city', '1234567', 'buenavista', 'residence', 'Commercial', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL,
  `comment` varchar(255) NOT NULL,
  `client_id` varchar(255) NOT NULL,
  `pestcontrol_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `notification` int(11) NOT NULL DEFAULT '1',
  `soft_delete` int(11) NOT NULL DEFAULT '1',
  `rating` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`comment_id`, `comment`, `client_id`, `pestcontrol_id`, `created_at`, `notification`, `soft_delete`, `rating`) VALUES
(13, 'love it!', 'anthony', 8, '2019-02-16 00:00:26', 1, 0, 0),
(14, '', 'fhage', 9, '2019-03-30 05:03:24', 1, 1, 0),
(15, 'ok ra inyong service', 'fhage', 9, '2019-03-30 05:30:59', 1, 1, 0),
(16, 'sample comment', 'fhage', 9, '2019-03-30 05:34:51', 1, 1, 0),
(17, 'ok ra', 'fhage', 9, '2019-03-30 05:38:26', 1, 1, 5),
(18, 'commercial comment', '', 9, '2019-03-30 06:41:04', 1, 1, 4),
(19, 'comment ra', 'SM', 9, '2019-03-30 06:43:21', 1, 1, 5),
(20, 'sample comment ra', 'fhage', 9, '2019-04-06 00:20:30', 1, 1, 5),
(21, 'comment sample', 'SM', 9, '2019-04-06 00:23:32', 1, 1, 5),
(22, 'comment', 'fhage', 9, '2019-04-06 01:01:13', 1, 1, 5),
(23, 'fhage comment', 'fhage', 9, '2019-04-08 23:56:02', 1, 1, 5),
(24, 'sample comment diri', 'fhage', 9, '2019-04-23 18:45:54', 1, 1, 5),
(25, 'sample comment ra', 'fhage', 9, '2019-04-27 23:32:53', 1, 1, 5),
(26, 'Char2 Lang', '', 9, '2019-05-10 10:00:19', 1, 1, 4),
(27, 'Hahahahah', 'fhage', 9, '2019-05-10 10:04:08', 1, 1, 4.5),
(28, 'Hahahahah', '', 9, '2019-05-10 10:05:06', 1, 1, 4.5),
(29, 'hahahahahah', 'SM', 11, '2019-05-10 10:15:06', 1, 1, 5),
(30, 'ok ra kayo inyong service', 'Buenavista', 9, '2019-05-19 14:47:34', 1, 1, 5),
(31, 'sample', 'fhage', 14, '2019-05-23 00:28:51', 1, 1, 5);

-- --------------------------------------------------------

--
-- Table structure for table `commercial`
--

CREATE TABLE `commercial` (
  `commercial_id` int(11) NOT NULL,
  `problem` varchar(255) NOT NULL,
  `company_name` varchar(255) NOT NULL,
  `company_address` varchar(255) NOT NULL,
  `pestcontrol_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `meter_id` int(11) NOT NULL,
  `client_id` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `price` decimal(9,2) NOT NULL,
  `type` varchar(255) NOT NULL DEFAULT 'commerical',
  `confirm` int(11) NOT NULL DEFAULT '1',
  `status` varchar(255) NOT NULL DEFAULT 'Pending',
  `provider` varchar(255) NOT NULL,
  `company_last` varchar(255) NOT NULL,
  `notification` int(11) NOT NULL DEFAULT '1',
  `soft_delete` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `commercial`
--

INSERT INTO `commercial` (`commercial_id`, `problem`, `company_name`, `company_address`, `pestcontrol_id`, `service_id`, `meter_id`, `client_id`, `date`, `time`, `price`, `type`, `confirm`, `status`, `provider`, `company_last`, `notification`, `soft_delete`) VALUES
(1, 'Spider Control ', 'Buenavista', 'mabolo st cebu city', 9, 200, 1501, 'Buenavista', 'Jun 7, 2019', '12:05PM', '1000.00', 'commerical', 0, 'Completed', 'Rentokill', 'Residence', 0, 1),
(2, 'Coackroach Control ', 'Buenavista', 'mabolo st cebu city', 9, 200, 501, 'Buenavista', 'Jun 4, 2019', '12:05PM', '500.00', 'commerical', 0, 'Completed', 'Rentokill', 'Residence', 0, 1),
(3, 'Ant Control ', 'Buenavista', 'mabolo st cebu city', 9, 400, 1, 'Buenavista', 'May 30, 2019', '12:15PM', '500.00', 'commerical', 0, 'Canceled', 'Rentokill', 'Residence', 0, 1),
(4, 'Mice Control ', 'Buenavista', 'mabolo st cebu city', 9, 200, 2001, 'Buenavista', 'Jun 4, 2019', '12:05PM', '800.00', 'commerical', 0, 'Completed', 'Rentokill', 'Residence', 0, 1),
(5, 'Coackroach Control ', 'Buenavista', 'mabolo st cebu city', 9, 200, 3001, 'Buenavista', 'Jun 7, 2019', '12:05PM', '1500.00', 'commerical', 0, 'Completed', 'Rentokill', 'Residence', 0, 1),
(6, 'Wood bug', 'Buenavista', 'mabolo st cebu city', 9, 200, 4001, 'Buenavista', 'Jul 27, 2019', '09:48AM', '900.00', 'commerical', 0, 'Canceled', 'Rentokill', 'Residence', 0, 1),
(7, 'Ant Control ', 'Buenavista', 'mabolo st cebu city', 9, 400, 1, 'Buenavista', 'May 27, 2019', '03:00PM', '500.00', 'commerical', 0, 'Canceled', 'Rentokill', 'Residence', 0, 1),
(8, 'Spider Control ', 'Buenavista', 'mabolo st cebu city', 9, 200, 1, 'Buenavista', 'Jun 7, 2019', '12:05PM', '300.00', 'commerical', 0, 'Canceled', 'Rentokill', 'Residence', 0, 1),
(9, 'Ant Control ', 'Buenavista', 'mabolo st cebu city', 9, 400, 1, 'Buenavista', 'Jun 7, 2019', '12:05PM', '500.00', 'commerical', 0, 'Canceled', 'Rentokill', 'Residence', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `pestcontrol`
--

CREATE TABLE `pestcontrol` (
  `pestcontrol_id` int(11) NOT NULL,
  `pestcontrol_name` varchar(255) NOT NULL,
  `pestcontrol_address` varchar(255) NOT NULL,
  `pestcontrol_contact` varchar(50) NOT NULL,
  `pestcontrol_detail` varchar(500) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `certificate1` varchar(255) NOT NULL,
  `certificate2` varchar(255) NOT NULL,
  `logo` varchar(255) NOT NULL,
  `path_image` varchar(255) NOT NULL,
  `user_type` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL DEFAULT 'Active',
  `notification` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pestcontrol`
--

INSERT INTO `pestcontrol` (`pestcontrol_id`, `pestcontrol_name`, `pestcontrol_address`, `pestcontrol_contact`, `pestcontrol_detail`, `username`, `password`, `certificate1`, `certificate2`, `logo`, `path_image`, `user_type`, `status`, `notification`) VALUES
(8, 'No Ka Oi Inc.', 'Cebu City', '09331212321', 'We give you service guarantee.', 'nokaoi', 'nokaoi', 'certificate1.jpg', 'certificate2.jpg', '', 'http://10.0.2.2/project/upload/', 'pestcontrol', 'Deactivate', 0),
(9, 'Rentokill', 'Cebu City', '0933221212', 'rentokill', 'rentokill', 'rentokill', 'certificate1.jpg', 'certificate2.jpg', 'rentokill.png', 'http://192.168.43.118/project/upload/rentokill.png', 'pestcontrol', 'Active', 0),
(10, 'sm seaside cebu', 'cebu city', '123456789', 'sm seaside', 'smseaside', 'smseaside', 'certificate1.jpg', 'certificate2.jpg', '', 'http://10.0.2.2/project/upload/user.png', 'pestcontrol', 'Deactivate', 0),
(11, 'Mapecon', '1091 B H Cortes, Mandaue City, Cebu', '344 0794', 'MAPECON offers the best environment-friendly solutions for your pest problems. You can trust MAPECON\'s practice of Christian principles and ethics in business, 56 years of experience, 38 award-winning patents, and 100% indigenous Filipino products to help make your environment pest free. Our 44 branches are here to serve you nationwide', 'mapecon', 'mapecon', 'certificate 1.jpg', 'certificate 2.jpg', 'mapecon.png', 'http://192.168.43.118/project/upload/mapecon.png', 'pestcontrol', 'Active', 0),
(12, 'Technologist pest control', '6A, Rahmann Street, Zapatera', '516 3707', 'We\'re proud to be one of the experts in pest control companies in the Philippines – and the biggest in the Visayas Region. We love our jobs and we take our \"eliminate them\" mission seriously. We offer premier customer service through highly trained, friendly technicians who are responsive and proactive. Then we go beyond these services to guarantee our work 100 percent. When you choose Technologist Pest Control Cebu, you\'re going with the best in pest control.', 'technologist', 'technologist', 'certificate1.jpg', 'certificate2.jpg', 'technologistpestcontrol.png', 'http://192.168.43.118/project/upload/technologistpestcontrol.png', 'pestcontrol', 'Active', 0),
(13, 'JCN Jansen Pest Control', 'D. Jakosalem St, Cebu City, 6000 Cebu', '0916 221 2629', 'JCN Jansen Pest Control, a pest control service provider operating by virtue of applicable Philippine laws is offering you our specialized services in the field of Pest Management & Termite Control using the most effective yet environmentally sound methods', 'jcnjansen', 'jcnjansen', 'certificate1.jpg', 'certificate2.jpg', 'jcn.png', 'http://192.168.43.118/project/upload/jcn.png', 'pestcontrol', 'Active', 0),
(14, 'Bug Assassin Pest Control Cebu', 'Blk. 2 Lot 6 Villa Alessandra Subd., Ylaya, Cebu City, 6000 Cebu', '032 520 7099', 'Bug Assassins Pest Control was founded in March of 2012 and incorporated in October of 2014, becoming Bug Assassins Pest Control , a Philippine company. Through continued hard work of its employees and officers Bug Assassins Pest Control continues to post good growth through excellent service, sales and acquisitions. Our goal is to continue to grow profitably and employ more quality employees within our community.  Our commitment is not only to our customers but also to our employees. We desire ', 'bugpest', 'bugpest', 'certificate1.jpg', 'certificate2.jpg', 'buglogo.jpg', 'http://192.168.43.118/project/upload/buglogo.jpg', 'pestcontrol', 'Active', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pestcontrol_logs`
--

CREATE TABLE `pestcontrol_logs` (
  `logs_id` int(11) NOT NULL,
  `pestcontrol_id` int(11) NOT NULL,
  `action` varchar(255) NOT NULL,
  `time_in` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reply_comment`
--

CREATE TABLE `reply_comment` (
  `reply_id` int(11) NOT NULL,
  `reply` varchar(255) NOT NULL,
  `comment_id` int(11) NOT NULL,
  `pestcontrol_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reply_comment`
--

INSERT INTO `reply_comment` (`reply_id`, `reply`, `comment_id`, `pestcontrol_id`) VALUES
(8, 'thank you!', 13, 'No Ka Oi');

-- --------------------------------------------------------

--
-- Table structure for table `residential`
--

CREATE TABLE `residential` (
  `residential_id` int(11) NOT NULL,
  `problem` varchar(255) NOT NULL,
  `pestcontrol_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `meter_id` int(11) NOT NULL,
  `client_id` varchar(255) NOT NULL,
  `residential_address` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `price` decimal(9,2) NOT NULL,
  `type` varchar(255) NOT NULL DEFAULT 'residential',
  `notification` int(11) NOT NULL DEFAULT '1',
  `confirm` int(11) NOT NULL DEFAULT '1',
  `status` varchar(255) NOT NULL DEFAULT 'Pending',
  `provider` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `soft_delete` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `residential`
--

INSERT INTO `residential` (`residential_id`, `problem`, `pestcontrol_id`, `service_id`, `meter_id`, `client_id`, `residential_address`, `date`, `time`, `price`, `type`, `notification`, `confirm`, `status`, `provider`, `lastname`, `soft_delete`) VALUES
(1, 'Mice Control ', 9, 200, 1501, 'fhage', 'cebu city', 'Jun 7, 2019', '12:05PM', '700.00', 'residential', 0, 0, 'Completed', 'Rentokill', 'tigley', 1),
(2, 'Coackroach Control ', 9, 200, 2001, 'fhage', 'cebu city', 'Jun 7, 2019', '12:05PM', '800.00', 'residential', 0, 0, 'Completed', 'Rentokill', 'tigley', 1),
(3, 'Bed Bug ', 9, 300, 3001, 'fhage', 'cebu city', 'Jun 21, 2019', '08:07AM', '900.00', 'residential', 0, 0, 'Canceled', 'Rentokill', 'tigley', 1),
(4, 'Coackroach Control ', 9, 200, 1, 'fhage', 'cebu city', 'Jun 4, 2019', '12:05PM', '300.00', 'residential', 0, 0, 'Completed', 'Rentokill', 'tigley', 1),
(5, 'Wood bug', 9, 200, 1, 'fhage', 'cebu city', 'May 27, 2019', '10:47PM', '300.00', 'residential', 0, 0, 'Cancel', 'Rentokill', 'tigley', 1),
(6, 'Mice Control ', 9, 200, 1, 'fhage', 'cebu city', 'May 30, 2019', '10:50PM', '300.00', 'residential', 0, 1, 'Re-Schedule', 'Rentokill', 'tigley', 1),
(7, 'Coackroach Control', 14, 200, 1, 'fhage', 'cebu city', 'Jun 25, 2019', '12:20PM', '300.00', 'residential', 0, 1, 'Re-Schedule', 'Bug Assassin Pest Control Cebu', 'tigley', 1),
(8, 'Mice Control ', 9, 200, 1, 'fhage', 'cebu city', 'Jun 7, 2019', '12:05PM', '300.00', 'residential', 0, 0, 'Cancel', 'Rentokill', 'tigley', 1),
(9, 'General Pest Control ', 9, 500, 1501, 'fhage', 'cebu city', 'Jun 7, 2019', '12:05PM', '900.00', 'residential', 0, 0, 'Completed', 'Rentokill', 'tigley', 1),
(10, 'Coackroach Control ', 9, 200, 1, 'fhage', 'cebu city', 'Jun 7, 2019', '12:05PM', '300.00', 'residential', 0, 0, 'Completed', 'Rentokill', 'tigley', 1);

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `schedule_id` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `pestcontrol_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`schedule_id`, `date`, `time`, `pestcontrol_id`) VALUES
(1, 'May 27, 2019', '10:47PM', 9),
(2, 'May 30, 2019', '10:50PM', 9),
(3, 'Jun 1, 2019', '11:05PM', 9),
(4, 'Jun 4, 2019', '12:05PM', 9),
(5, 'Jun 7, 2019', '12:05PM', 9),
(6, 'Jun 8, 2019', '12:15PM', 13),
(7, 'Jun 15, 2019', '12:15PM', 13),
(8, 'Jul 7, 2019', '12:17PM', 13),
(9, 'Jun 19, 2019', '12:20PM', 11),
(10, 'Jun 25, 2019', '12:20PM', 11),
(12, 'Jun 27, 2019', '12:20PM', 11),
(13, 'Jun 25, 2019', '12:20PM', 14),
(14, 'Jun 15, 2019', '01:20PM', 14),
(15, 'Jun 20, 2019', '01:25PM', 14),
(16, 'Jun 5, 2019', '12:25PM', 12),
(17, 'Jun 27, 2019', '01:30PM', 12),
(18, 'Jun 17, 2019', '01:25PM', 12);

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `service_id` int(11) NOT NULL,
  `service_name` varchar(255) NOT NULL,
  `service_detail` varchar(255) NOT NULL,
  `service_type` varchar(255) NOT NULL,
  `service_price` varchar(255) NOT NULL,
  `logo` varchar(255) NOT NULL,
  `path_image` varchar(255) NOT NULL,
  `pestcontrol_id` int(11) NOT NULL,
  `soft_delete` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`service_id`, `service_name`, `service_detail`, `service_type`, `service_price`, `logo`, `path_image`, `pestcontrol_id`, `soft_delete`) VALUES
(18, 'Termite Control', 'Keep termite away!', 'Termite Pest Control Sevice', '500', 'termite.JPG', 'http://192.168.43.118/project/upload/termite.JPG', 8, 1),
(19, 'Bed Bug ', 'Keep bed bug away!', 'General Pest Control Service', '800', 'bedbug.JPG', 'http://192.168.43.118/project/upload/bedbug.JPG', 8, 0),
(20, 'Ant Control', 'Ant Control', 'General Pest Control Service', '200', 'woodbug.JPG', 'http://192.168.43.118/project/upload/woodbug.JPG', 8, 1),
(21, 'Termite Control ', 'These treatments prevent termites that are already inside the structure from getting to the soil to get the moisture needed to survive', 'General Pest Control Service', '400', 'termite.JPG', 'http://192.168.43.118/project/upload/bug.png', 9, 0),
(22, 'Bed Bug ', 'Reduce the number of bed bugs to reduce bites. Thorough vacuuming can get rid of some of your bed bugs. Carefully vacuum rugs, floors, upholstered furniture, bed frames, under beds, around bed legs, and all cracks and crevices around the room', 'General Pest Control Service', '300', 'bedbug.JPG', 'http://192.168.43.118/project/upload/bug2.png', 9, 1),
(23, 'Wood bug', 'wood louse especially a wood louse capable of curling itself into a ball', 'General Pest Control Service', '200', 'woodbug.JPG', 'http://192.168.43.118/project/upload/woodbug.JPG', 9, 1),
(24, 'Bed Bug Service', 'Bed bugs in the Philippines are kind of a rare sight as there have only been several cases of bed bugs infesting households. Well, either way, getting rid of them is still a definite priority.', 'General Pest Control Service', '400', 'bug2.png', 'http://192.168.43.118/project/upload/bug2.png', 11, 1),
(25, 'Wood bug ', 'Wood louse especially a wood louse capable of curling itself into a ball', 'General Pest Control Service', '200', 'woodbug.JPG', 'http://192.168.43.118/project/upload/woodbug.JPG', 11, 1),
(26, 'Spider Control', 'Spider Control', 'General Pest Control Service', '300', 'spiders.png', 'http://192.168.43.118/project/upload/spiders.png', 9, 0),
(27, 'Coackroach Control', 'Coackroach control', 'General Pest Control Service', '200', 'cock.png', 'http://192.168.43.118/project/upload/cock.png', 9, 0),
(28, 'General Pest Control', 'General Pest Control', 'General Pest Control Service', '500', '4pics.png', 'http://192.168.43.118/project/upload/4pics.png', 9, 0),
(29, 'Coackroach Control ', 'presence in business environment poses a real threat of food contamination, damage to stored items and business reputation. Additionally, the health hazards associated with this disease-spreading pest are a real concern for homeowners too', 'General Pest Control Service', '200', 'cock.png', 'http://192.168.43.118/project/upload/cock.png', 9, 1),
(30, 'Mice Control ', 'They can multiply quickly and will do significant damage to your home, business and furniture through their gnawing activities. They will also contaminate food and other materials through the droppings they leave behind. Their droppings have even been imp', 'General Pest Control Service', '200', 'mice.png', 'http://192.168.43.118/project/upload/mice.png', 9, 1),
(31, 'Ant Control ', 'are a social insect that lives in colonies. Treatment plans should include killing the entire colony. Just spraying an ant with a typical ant spray, particularly a repellent spray will only kill a few at best and scatter the colony. Ants may enter your ho', 'General Pest Control Service', '400', 'ants.png', 'http://192.168.43.118/project/upload/ants.png', 9, 1),
(32, 'Spider Control ', 'Use an insecticide aerosol like PT221L Aerosol with a crack and crevice tip to spray along baseboards, window and door frames, corners, pipes and other areas where spiders may crawl', 'General Pest Control Service', '200', 'spiders.png', 'http://192.168.43.118/project/upload/spiders.png', 9, 1),
(33, 'General Pest Control ', 'General Pest Control. Premier Pest Control provides services for many different pests including ants, spiders, wood bug, mice', 'General Pest Control Service', '500', '4pics.png', 'http://192.168.43.118/project/upload/4pics.png', 9, 1),
(34, 'Coackroach Control', 'presence in business environment poses a real threat of food contamination, damage to stored items and business reputation. Additionally, the health hazards associated with this disease-spreading pest are a real concern for homeowners too', 'General Pest Control Service', '200', 'cock.png', 'http://192.168.43.118/project/upload/cock.png', 11, 1),
(35, 'Mice Control', 'They can multiply quickly and will do significant damage to your home, business and furniture through their gnawing activities. They will also contaminate food and other materials through the droppings they leave behind. Their droppings have been imp', 'General Pest Control Service', '300', 'mice.png', 'http://192.168.43.118/project/upload/mice.png', 11, 1),
(36, 'Ant Control', 'Are a social insect that lives in colonies. Treatment plans should include killing the entire colony. Just spraying an ant with a typical ant spray, particularly a repellent spray will only kill a few at best and scatter the colony', 'General Pest Control Service', '300', 'ants.png', 'http://192.168.43.118/project/upload/ants.png', 11, 1),
(37, 'Spider Control', 'Use an insecticide aerosol like PT221L Aerosol with a crack and crevice tip to spray along baseboards, windows and door frames, corners, pipes and other areas where spiders may crawl', 'General Pest Control Service', '300', 'spiders.png', 'http://192.168.43.118/project/upload/spiders.png', 11, 1),
(38, 'General Pest Control', 'General Pest Control, Premier Pest Control provides service for many different pest including ants, spiders, wood bug, mice', 'General Pest Control Service', '500', '4pics.png', 'http://192.168.43.118/project/upload/4pics.png', 11, 1),
(39, 'Bed Bug', 'Bed bugs in the Philippines are kind of a rare sight as there have only been several cases of bed bugs infesting households. Well, either way, getting rid of them is still a definite priority.', 'General Pest Control Service', '200', 'bug2.png', 'http://192.168.43.118/project/upload/bug2.png', 12, 1),
(40, 'Wood bug', 'Wood louse especially a wood louse capable of curling itself into a ball', 'General Pest Control Service', '300', 'woodbug.JPG', 'http://192.168.43.118/project/upload/woodbug.JPG', 12, 1),
(41, 'Coackroach Control', 'presence in business environment poses a real threat of food contamination, damage to stored items and business reputation. Additionally, the health hazards associated with this disease-spreading pest are a real concern for homeowners too', 'General Pest Control Service', '300', 'cock.png', 'http://192.168.43.118/project/upload/cock.png', 12, 1),
(42, 'Ant Control', ' Are a social insect that lives in colonies. Treatment plans should include killing the entire colony. Just spraying an ant with a typical ant spray, particularly a repellent spray will only kill a few at best and scatter the colony', 'General Pest Control Service', '200', 'ants.png', 'http://192.168.43.118/project/upload/ants.png', 12, 1),
(43, 'Spider Control', 'Use an insecticide aerosol like PT221L Aerosol with a crack and crevice tip to spray along baseboards, windows and door frames, corners, pipes and other areas where spiders may crawl', 'General Pest Control Service', '300', 'spiders.png', 'http://192.168.43.118/project/upload/spiders.png', 12, 1),
(44, 'General Pest Control', 'General Pest Control, Premier Pest Control provides service for many different pest including ants, spiders, wood bug, mice', 'General Pest Control Service', '500', '4pics.png', 'http://192.168.43.118/project/upload/4pics.png', 12, 1),
(45, 'Bed Bug', 'Bed bugs in the Philippines are kind of a rare sight as there have only been several cases of bed bugs infesting households. Well, either way, getting rid of them is still a definite priority.', 'General Pest Control Service', '400', 'bug2.png', 'http://192.168.43.118/project/upload/bug2.png', 13, 1),
(46, 'Wood bug', 'Wood louse especially a wood louse capable of curling itself into a ball', 'General Pest Control Service', '300', 'woodbug.JPG', 'http://192.168.43.118/project/upload/woodbug.JPG', 13, 1),
(47, 'Coackroach Control', 'presence in business environment poses a real threat of food contamination, damage to stored items and business reputation. Additionally, the health hazards associated with this disease-spreading pest are a real concern for homeowners too', 'General Pest Control Service', '300', 'cock.png', 'http://192.168.43.118/project/upload/cock.png', 13, 1),
(48, 'Mice Control', 'They can multiply quickly and will do significant damage to your home, business and furniture through their gnawing activities. They will also contaminate food and other materials through the droppings they leave behind. Their droppings have been imp', 'General Pest Control Service', '300', 'mice.png', 'http://192.168.43.118/project/upload/mice.png', 13, 1),
(49, 'Ant Control', 'Are a social insect that lives in colonies. Treatment plans should include killing the entire colony. Just spraying an ant with a typical ant spray, particularly a repellent spray will only kill a few at best and scatter the colony', 'General Pest Control Service', '350', 'cock.png', 'http://192.168.43.118/project/upload/cock.png', 13, 1),
(50, 'Spider Control', 'Use an insecticide aerosol like PT221L Aerosol with a crack and crevice tip to spray along baseboards, windows and door frames, corners, pipes and other areas where spiders may crawl', 'General Pest Control Service', '250', 'spiders.png', 'http://192.168.43.118/project/upload/spiders.png', 13, 1),
(51, 'General Pest Control', 'General Pest Control, Premier Pest Control provides service for many different pest including ants, spiders, wood bug, mice', 'General Pest Control Service', '500', '4pics.png', 'http://192.168.43.118/project/upload/4pics.png', 13, 1),
(52, 'Termite Controls', 'For most homeowners, their home is their biggest investment. For that reason, they get very upset when they think termites might be attacking their home.', 'General Pest Control Service', '300', 'bug.png', 'http://192.168.43.118/project/upload/bug.png', 14, 1),
(53, 'Coackroach Control', 'Few bugs are as disgusting to see alive or dead as a cockroach. If you are seeing signs of them in or around your home or office, ', 'General Pest Control Service', '200', 'cock.png', 'http://192.168.43.118/project/upload/cock.png', 14, 1);

-- --------------------------------------------------------

--
-- Table structure for table `squaremeter`
--

CREATE TABLE `squaremeter` (
  `id` int(11) NOT NULL,
  `square` varchar(255) NOT NULL,
  `price` decimal(9,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `squaremeter`
--

INSERT INTO `squaremeter` (`id`, `square`, `price`) VALUES
(1, '1-500 Sq.ft', '100.00'),
(2, '501-1000 Sq.ft', '200.00'),
(3, '1001-1500 Sq.ft', '300.00'),
(4, '1501-2000 Sq.ft', '400.00'),
(5, '2001-3000 Sq.ft', '500.00'),
(6, '3001-4000 Sq.ft', '600.00'),
(7, '4001-5000 Sq.m', '700.00'),
(8, '5001-10000 Sq.ft', '800.00'),
(9, '10001-20000 Sq.ft', '900.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `calendar`
--
ALTER TABLE `calendar`
  ADD PRIMARY KEY (`id`),
  ADD KEY `create_by` (`create_by`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`client_id`);

--
-- Indexes for table `clients_log`
--
ALTER TABLE `clients_log`
  ADD PRIMARY KEY (`logs_id`),
  ADD KEY `client_id` (`client_id`);

--
-- Indexes for table `client_commercial`
--
ALTER TABLE `client_commercial`
  ADD PRIMARY KEY (`commercial_client_id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `client_id` (`client_id`),
  ADD KEY `pestcontrol_id` (`pestcontrol_id`);

--
-- Indexes for table `commercial`
--
ALTER TABLE `commercial`
  ADD PRIMARY KEY (`commercial_id`),
  ADD KEY `pestcontrol_id` (`pestcontrol_id`),
  ADD KEY `service_id` (`service_id`),
  ADD KEY `meter_id` (`meter_id`),
  ADD KEY `client_id` (`client_id`);

--
-- Indexes for table `pestcontrol`
--
ALTER TABLE `pestcontrol`
  ADD PRIMARY KEY (`pestcontrol_id`);

--
-- Indexes for table `pestcontrol_logs`
--
ALTER TABLE `pestcontrol_logs`
  ADD PRIMARY KEY (`logs_id`),
  ADD KEY `pestcontrol_id` (`pestcontrol_id`);

--
-- Indexes for table `reply_comment`
--
ALTER TABLE `reply_comment`
  ADD PRIMARY KEY (`reply_id`),
  ADD KEY `comment_id` (`comment_id`),
  ADD KEY `pestcontrol_id` (`pestcontrol_id`);

--
-- Indexes for table `residential`
--
ALTER TABLE `residential`
  ADD PRIMARY KEY (`residential_id`),
  ADD KEY `pestcontrol_id` (`pestcontrol_id`),
  ADD KEY `service_id` (`service_id`),
  ADD KEY `meter_id` (`meter_id`),
  ADD KEY `client_id` (`client_id`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`schedule_id`),
  ADD KEY `pestcontrol_id` (`pestcontrol_id`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`service_id`),
  ADD KEY `pestcontrol_id` (`pestcontrol_id`);

--
-- Indexes for table `squaremeter`
--
ALTER TABLE `squaremeter`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `calendar`
--
ALTER TABLE `calendar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `clients_log`
--
ALTER TABLE `clients_log`
  MODIFY `logs_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=150;

--
-- AUTO_INCREMENT for table `client_commercial`
--
ALTER TABLE `client_commercial`
  MODIFY `commercial_client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `commercial`
--
ALTER TABLE `commercial`
  MODIFY `commercial_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `pestcontrol`
--
ALTER TABLE `pestcontrol`
  MODIFY `pestcontrol_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `pestcontrol_logs`
--
ALTER TABLE `pestcontrol_logs`
  MODIFY `logs_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reply_comment`
--
ALTER TABLE `reply_comment`
  MODIFY `reply_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `residential`
--
ALTER TABLE `residential`
  MODIFY `residential_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `schedule_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `squaremeter`
--
ALTER TABLE `squaremeter`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `clients_log`
--
ALTER TABLE `clients_log`
  ADD CONSTRAINT `clients_log_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
