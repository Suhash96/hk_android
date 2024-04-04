-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 04, 2024 at 10:29 AM
-- Server version: 10.5.20-MariaDB
-- PHP Version: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id21896700_house`
--

-- --------------------------------------------------------

--
-- Table structure for table `AssignWork`
--

CREATE TABLE `AssignWork` (
  `job_id` int(11) NOT NULL,
  `week` varchar(50) DEFAULT NULL,
  `building` varchar(100) DEFAULT NULL,
  `floor` varchar(100) DEFAULT NULL,
  `room_type` varchar(100) DEFAULT NULL,
  `assigned_supervisors` varchar(250) DEFAULT NULL,
  `from_date` date DEFAULT curdate(),
  `to_date` date DEFAULT (curdate() + interval 5 day),
  `status` varchar(50) DEFAULT 'Upcoming'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `AssignWork`
--

INSERT INTO `AssignWork` (`job_id`, `week`, `building`, `floor`, `room_type`, `assigned_supervisors`, `from_date`, `to_date`, `status`) VALUES
(1, 'Week1', 'Building1', 'Floor1', 'Type1', 'Supervisor1, Supervisor2', '2024-02-12', '2024-02-12', 'Upcoming'),
(2, 'Week2', 'Building1', 'Floor1', 'Type1', 'Supervisor1, Supervisor2', '2024-02-12', '2024-02-12', 'Upcoming'),
(3, 'Week1', 'ExampleBuilding', '0', 'ClassRoom', 'hari', '2024-02-14', '2024-02-19', 'Upcoming'),
(4, 'Week1', 'ExampleBuilding', '0', 'ClassRoom', 'John, hari', '2024-02-11', '2024-02-16', 'Upcoming'),
(5, 'Week1', 'Ssc', '0', 'ClassRoom', 'John, teja, hari', '2024-02-11', '2024-02-16', 'Upcoming'),
(6, 'Week1', 'Scand', '2', 'Staff Room', 'suraj', '2024-02-11', '2024-02-16', 'Upcoming'),
(7, 'Week1', 'Scand', '2', 'Staff Room', 'suraj', '2024-02-11', '2024-02-16', 'Upcoming'),
(8, 'Week1', 'Scand', '2', 'Staff Room', 'kamal, suraj', '2024-02-11', '2024-02-16', 'Upcoming'),
(9, 'Week1', 'Scand', '2', 'Staff Room', 'kamal, suraj', '2024-02-11', '2024-02-16', 'Upcoming'),
(10, 'Week1', 'Scand', '2', 'Staff Room', 'kamal, suraj', '2024-02-11', '2024-02-16', 'Upcoming'),
(11, 'Week1', 'Scand', '2', 'Staff Room', 'teja, kamal, suraj', '2024-02-11', '2024-02-16', 'Upcoming'),
(12, 'Week2', 'Building1', 'Floor1', 'Type1', 'Supervisor1, Supervisor2', '2024-02-12', '2024-02-12', 'Upcoming'),
(13, 'Week2', 'Building1', 'Floor1', 'Type1', 'Supervisor1, Supervisor2', '2024-02-12', '2024-02-12', 'Upcoming'),
(14, 'Week1', 'ExampleBuilding', '0', 'ClassRoom', 'John', '2024-02-11', '2024-02-16', 'Upcoming'),
(15, 'Week2', 'Building1', 'Floor1', 'Type1', 'Supervisor1, Supervisor2', '2024-02-12', '2024-02-12', 'Upcoming'),
(16, 'Week1', 'ExampleBuilding', '0', 'ClassRoom', 'John', '2024-02-11', '2024-02-16', 'Upcoming'),
(17, 'Week1', 'ExampleBuilding', '0', 'ClassRoom', 'teja', '2024-02-11', '2024-02-16', 'Upcoming'),
(18, 'Week1', 'ExampleBuilding', '0', 'ClassRoom', 'hari', '2024-02-14', '2024-02-19', 'Upcoming'),
(19, 'Week1', 'ExampleBuilding', '0', 'ClassRoom', 'teja', '2024-02-11', '2024-02-16', 'Upcoming'),
(20, 'Week1', 'ExampleBuilding', '0', 'ClassRoom', 'teja', '2024-02-11', '2024-02-16', 'Upcoming'),
(21, NULL, NULL, NULL, NULL, 'Supervisor1, Supervisor2', '2024-02-12', '2024-02-12', 'Upcoming'),
(22, NULL, NULL, NULL, NULL, 'hari', '2024-02-14', '2024-02-19', 'Upcoming'),
(23, NULL, NULL, NULL, NULL, 'hari', '2024-02-14', '2024-02-19', 'Upcoming'),
(24, 'Week1', 'Ssc', '2', 'Staff Room', 'teja', '2024-02-11', '2024-02-16', 'Upcoming'),
(25, 'Week1', 'Ssc', '1', 'ClassRoom', 'Suhash Tatapudi', '2024-02-12', '2024-02-17', 'Upcoming'),
(26, 'Week1', 'Ssc', '1', 'ClassRoom', 'Suhash Tatapudi', '2024-02-12', '2024-02-17', 'Upcoming'),
(27, 'Week1', 'Ssc', '1', 'ClassRoom', 'Suhash Tatapudi', '2024-02-12', '2024-02-17', 'Upcoming'),
(28, 'Week1', 'Ssc', '1', 'ClassRoom', 'Suhash Tatapudi', '2024-02-12', '2024-02-17', 'Upcoming'),
(29, 'Week1', 'Ssc', '1', 'Class Room', 'suraj', '2024-02-12', '2024-02-17', 'Upcoming'),
(30, 'Week1', 'Ssc', '0', 'ClassRoom', 'teja', '2024-02-13', '2024-02-18', 'Upcoming'),
(31, 'Week1', 'ExampleBuilding', '0', 'ClassRoom', 'teja', '2024-02-13', '2024-02-18', 'Upcoming'),
(32, 'Week1', 'Ssc', '1', 'ClassRoom', 'jyo', '2024-02-19', '2024-02-24', 'Upcoming'),
(33, 'Week1', 'Ssc', '1', 'ClassRoom', 'jyo', '2024-02-19', '2024-02-24', 'Upcoming'),
(34, 'Week1', 'ExampleBuilding', '1', 'ClassRoom', 'vignan', '2024-03-19', '2024-03-24', 'Upcoming'),
(35, 'Week1', 'Ssc', '1', 'Staff Room', 'teja, Suhash Tatapudi', '2024-04-04', '2024-04-09', 'Upcoming');

-- --------------------------------------------------------

--
-- Table structure for table `AssignWorkWorkers`
--

CREATE TABLE `AssignWorkWorkers` (
  `job_id` int(11) NOT NULL,
  `week` varchar(50) DEFAULT NULL,
  `building` varchar(100) DEFAULT NULL,
  `floor` varchar(100) DEFAULT NULL,
  `room_type` varchar(100) DEFAULT NULL,
  `assigned_workers` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `AssignWorkWorkers`
--

INSERT INTO `AssignWorkWorkers` (`job_id`, `week`, `building`, `floor`, `room_type`, `assigned_workers`) VALUES
(1, 'Week2', 'Building1', 'Floor1', 'Type1', 'Supervisor1,Supervisor2'),
(2, 'Week2', 'Building1', 'Floor1', 'Type1', 'Supervisor1,Supervisor2'),
(3, 'Week2', 'Building1', 'Floor1', 'Type1', 'Supervisor1,Supervisor2'),
(4, 'Week2', 'Building1', 'Floor1', 'Type1', 'Supervisor1,Supervisor2'),
(5, 'Week2', 'Building1', 'Floor1', 'Type1', 'Supervisor1,Supervisor2'),
(6, 'Week1', 'ExampleBuilding', '2', 'ClassRoom', 'What is the , What is the other '),
(7, 'Week1', 'ExampleBuilding', '0', 'ClassRoom', 'What is the other '),
(8, 'Week1', 'Ssc', '0', 'ClassRoom', 'Ravi'),
(9, 'Week1', 'ExampleBuilding', '0', 'ClassRoom', 'Ravi, Raju, Krishna'),
(10, 'Week1', 'Ssc', '1', 'ClassRoom', 'Ravi, Raju, Krishna'),
(11, 'Week1', 'Ssc', '1', 'ClassRoom', 'Krishna, Raju '),
(12, 'Week1', 'Ssc', '1', 'ClassRoom', 'Ravi, Raju');

-- --------------------------------------------------------

--
-- Table structure for table `DetailsManager`
--

CREATE TABLE `DetailsManager` (
  `bio_id` varchar(255) NOT NULL,
  `name` text DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `experience` int(11) DEFAULT NULL,
  `doj` text DEFAULT NULL,
  `qualification` varchar(255) DEFAULT NULL,
  `phone_no` varchar(10) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `DetailsManager`
--

INSERT INTO `DetailsManager` (`bio_id`, `name`, `age`, `experience`, `doj`, `qualification`, `phone_no`, `password`) VALUES
('123', 'suraj', 3, 4, '0', 'Btech', '1234567890', '123'),
('125', 'ravi', 3, 4, '3467', 'Btech', '1234567890', '123'),
('192011264', 'Rakesh ', 21, 5, '19', 'BE', '0987654321', 'raki123'),
('46', 'Sarath ', 54, 5, '2024', 'Btech', '1234557890', '124456');

-- --------------------------------------------------------

--
-- Table structure for table `DetailsStoreKeeper`
--

CREATE TABLE `DetailsStoreKeeper` (
  `bio_id` varchar(255) NOT NULL,
  `name` text DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `experience` int(11) DEFAULT NULL,
  `doj` text DEFAULT NULL,
  `qualification` varchar(255) DEFAULT NULL,
  `phone_no` varchar(10) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `DetailsStoreKeeper`
--

INSERT INTO `DetailsStoreKeeper` (`bio_id`, `name`, `age`, `experience`, `doj`, `qualification`, `phone_no`, `password`) VALUES
('123', 'John', 30, 5, '2023', 'Bachelor', '1234567890', '123'),
('124', 'Raj', 25, 3, '20220115', 'Master', '9876543210', '123'),
('125', 'Kiran', 28, 6, '20220120', 'PhD', '4567890123', '123'),
('126', 'Suresh', 35, 10, '20201001', 'Bachelor', '7890123456', '123');

-- --------------------------------------------------------

--
-- Table structure for table `DetailsSupervisor`
--

CREATE TABLE `DetailsSupervisor` (
  `bio_id` varchar(255) NOT NULL,
  `name` text DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `experience` int(11) DEFAULT NULL,
  `doj` text DEFAULT NULL,
  `qualification` varchar(255) DEFAULT NULL,
  `phone_no` varchar(10) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `DetailsSupervisor`
--

INSERT INTO `DetailsSupervisor` (`bio_id`, `name`, `age`, `experience`, `doj`, `qualification`, `phone_no`, `password`) VALUES
('1', 'teja', 30, 5, '20240207', 'Bachelor', '1234567890', 'pass123'),
('123', 'Arun', 25, 3, '2023', 'Btech ', '1234567890', '123'),
('1920', 'vignan', 20, 4, '11', 'BE', '7702056967', 'jack123'),
('192011251', 'Suhash Tatapudi', 20, 2, '12', 'BE', '8106620459', '123'),
('4317', 'kamal', 30, 5, '20240207', 'Bachelor', '1234567890', 'pass123'),
('4348', 'suraj', 30, 5, '20240207', 'Bachelor', '1234567890', 'pass123'),
('456', 'John', 30, 5, '20240207', 'Bachelor', '1234567890', '456');

-- --------------------------------------------------------

--
-- Table structure for table `DetailsWorker`
--

CREATE TABLE `DetailsWorker` (
  `bio_id` varchar(255) NOT NULL,
  `name` text DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `experience` int(11) DEFAULT NULL,
  `doj` text DEFAULT NULL,
  `qualification` varchar(255) DEFAULT NULL,
  `phone_no` varchar(10) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `DetailsWorker`
--

INSERT INTO `DetailsWorker` (`bio_id`, `name`, `age`, `experience`, `doj`, `qualification`, `phone_no`, `password`) VALUES
('123', 'Ravi', 3, 4, '3588', 'Btech', '1234566678', '123'),
('124', 'Raju', 3, 4, '3588', 'Btech', '1234566678', '123'),
('125', 'Krishna', 3, 4, '3588', 'Btech', '1234566678', '123'),
('126', 'Ram', 3, 4, '3588', 'Btech', '1234566678', '123'),
('192011250', 'Raju ', 23, 3, '19', 'BE', '0987654321', 'raju123');

-- --------------------------------------------------------

--
-- Table structure for table `ratings`
--

CREATE TABLE `ratings` (
  `id` int(11) NOT NULL,
  `supervisor_name` varchar(255) NOT NULL,
  `rating` float NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `ratings`
--

INSERT INTO `ratings` (`id`, `supervisor_name`, `rating`, `created_at`) VALUES
(1, 'hari', 5, '2024-02-08 06:03:47'),
(2, 'John', 5, '2024-02-08 06:05:03'),
(3, 'teja', 5, '2024-02-08 06:05:03'),
(4, 'krishna', 4, '2024-02-08 06:08:27'),
(5, 'Hari', 4, '2024-02-08 06:08:50'),
(6, 'hari', 3, '2024-02-08 06:08:50'),
(7, 'John', 5, '2024-02-08 06:08:50'),
(8, 'hari', 5, '2024-02-08 06:08:50'),
(9, 'John', 3, '2024-02-08 06:08:50'),
(10, 'teja', 4, '2024-02-08 06:08:50'),
(11, 'John', 4, '2024-02-08 06:09:48'),
(12, 'Suhash Tatapudi', 5, '2024-02-19 13:16:16'),
(13, 'John', 5, '2024-02-23 08:53:51');

-- --------------------------------------------------------

--
-- Table structure for table `requestItem`
--

CREATE TABLE `requestItem` (
  `id` int(11) NOT NULL,
  `supervisorId` varchar(255) DEFAULT NULL,
  `item` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `request_acceptance` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `requestItem`
--

INSERT INTO `requestItem` (`id`, `supervisorId`, `item`, `count`, `request_acceptance`) VALUES
(2, '456', 'Mop', 1, 'Accepted'),
(3, '456', 'Bucket', 0, 'Accepted'),
(4, '456', 'Scrub Brush', 0, 'Accepted'),
(5, '456', 'Broom', 0, 'Accepted'),
(6, '456', 'Toilet Brush', 0, 'Accepted'),
(7, '456', 'Dust Pan', 0, 'Accepted'),
(8, '456', 'Gloves', 0, 'Accepted'),
(9, '456', 'Acid', 0, 'Accepted'),
(10, '456', 'Phenol', 0, 'Accepted'),
(11, '456', 'Vacuum', 0, 'Accepted'),
(12, '456', 'Vacuum', 1, 'Accepted'),
(13, '456', 'Gloves', 1, 'Accepted'),
(14, '456', 'Acid', 1, 'Accepted'),
(15, '456', 'Vacuum', 1, 'Accepted'),
(16, '456', 'Bucket', 1, 'Accepted'),
(17, 'null', 'Mop', 1, 'Accepted'),
(18, 'null', 'Bucket', 1, 'Accepted'),
(19, 'null', 'Scrub Brush', 1, 'Accepted'),
(20, 'null', 'Broom', 1, 'Accepted'),
(21, 'null', 'Toilet Brush', 1, 'Accepted'),
(22, 'null', 'Dust Pan', 1, 'Accepted'),
(23, 'null', 'Gloves', 1, 'Accepted'),
(24, 'null', 'Mop', 1, 'Accepted'),
(25, 'null', 'Bucket', 1, 'Accepted'),
(26, 'null', 'Scrub Brush', 1, 'Accepted'),
(27, 'null', 'Broom', 1, 'Accepted'),
(28, 'null', 'Mop', 5, 'Accepted'),
(29, 'null', 'Bucket', 3, 'Accepted'),
(30, 'null', 'Scrub Brush', 3, 'Accepted'),
(31, 'null', 'Mop', 5, 'Accepted'),
(32, 'null', 'Bucket', 3, 'Accepted'),
(33, 'null', 'Scrub Brush', 3, 'Accepted'),
(34, 'null', 'Mop', 1, 'Accepted'),
(35, 'null', 'Bucket', 1, 'Accepted'),
(36, 'null', 'Scrub Brush', 4, 'Accepted'),
(37, 'null', 'Broom', 1, 'Accepted'),
(38, 'null', 'Toilet Brush', 1, 'Accepted'),
(39, 'null', 'Mop', 1, 'Accepted'),
(40, 'null', 'Bucket', 1, 'Accepted'),
(41, 'null', 'Scrub Brush', 4, 'Accepted'),
(42, 'null', 'Broom', 1, 'Accepted'),
(43, 'null', 'Toilet Brush', 1, 'Accepted'),
(44, 'null', 'Mop', 1, 'Accepted'),
(45, 'null', 'Bucket', 1, 'Accepted'),
(46, 'null', 'Scrub Brush', 1, 'Accepted'),
(47, 'null', 'Broom', 1, 'Accepted'),
(48, 'null', 'Toilet Brush', 2, 'Accepted'),
(49, 'null', 'Dust Pan', 2, 'Accepted'),
(50, 'null', 'Gloves', 2, 'Accepted'),
(51, 'null', 'Mop', 2, 'Accepted'),
(52, 'null', 'Broom', 2, 'Accepted'),
(53, 'null', 'Gloves', 2, 'Accepted'),
(54, 'null', 'Mop', 1, 'Accepted'),
(55, 'null', 'Bucket', 1, 'Accepted'),
(56, 'null', 'Scrub Brush', 1, 'Accepted'),
(57, 'null', 'Mop', 2, 'Accepted'),
(58, 'null', 'Mop', 2, 'Accepted'),
(59, 'null', 'Mop', 2, 'Accepted'),
(60, 'null', 'Bucket', 1, 'Accepted'),
(61, 'null', 'Mop', 2, 'Accepted'),
(62, 'null', 'Bucket', 2, 'Accepted'),
(63, 'null', 'Mop', 1, 'Accepted'),
(64, 'null', 'Bucket', 1, 'Accepted'),
(65, 'null', 'Mop', 1, 'Accepted'),
(66, 'null', 'Bucket', 1, 'Accepted'),
(67, 'null', 'Vacuum', 3, NULL),
(68, 'null', 'Vacuum', 3, NULL),
(69, 'null', 'Mop', 2, NULL),
(70, 'null', 'Bucket', 2, NULL),
(71, 'null', 'Scrub Brush', 1, NULL),
(72, 'null', 'Mop', 1, NULL),
(73, 'null', 'Bucket', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `Rooms`
--

CREATE TABLE `Rooms` (
  `id` int(11) NOT NULL,
  `building_name` varchar(255) DEFAULT NULL,
  `floor_no` int(11) DEFAULT NULL,
  `room_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `Rooms`
--

INSERT INTO `Rooms` (`id`, `building_name`, `floor_no`, `room_type`) VALUES
(1, 'ExampleBuilding', 0, 'ClassRoom'),
(2, 'Ssc', 1, 'Staff Room'),
(3, 'Scand', 2, 'Class Room'),
(4, 'Build1', 1, 'Office Room'),
(5, 'Korfos', 203, 'Deep cleaning');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `AssignWork`
--
ALTER TABLE `AssignWork`
  ADD PRIMARY KEY (`job_id`);

--
-- Indexes for table `AssignWorkWorkers`
--
ALTER TABLE `AssignWorkWorkers`
  ADD PRIMARY KEY (`job_id`);

--
-- Indexes for table `DetailsManager`
--
ALTER TABLE `DetailsManager`
  ADD PRIMARY KEY (`bio_id`);

--
-- Indexes for table `DetailsSupervisor`
--
ALTER TABLE `DetailsSupervisor`
  ADD PRIMARY KEY (`bio_id`);

--
-- Indexes for table `DetailsWorker`
--
ALTER TABLE `DetailsWorker`
  ADD PRIMARY KEY (`bio_id`);

--
-- Indexes for table `ratings`
--
ALTER TABLE `ratings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `requestItem`
--
ALTER TABLE `requestItem`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Rooms`
--
ALTER TABLE `Rooms`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `AssignWork`
--
ALTER TABLE `AssignWork`
  MODIFY `job_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `AssignWorkWorkers`
--
ALTER TABLE `AssignWorkWorkers`
  MODIFY `job_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `ratings`
--
ALTER TABLE `ratings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `requestItem`
--
ALTER TABLE `requestItem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT for table `Rooms`
--
ALTER TABLE `Rooms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
