-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 17, 2016 at 08:47 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `seniors`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbldriver`
--

CREATE TABLE `tbldriver` (
  `intDriverID` int(11) NOT NULL,
  `intLoginID` int(11) NOT NULL,
  `strDriverName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `strAddress` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `dtmDOB` date NOT NULL,
  `strPhoneNo` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `strLicenceNo` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `strSchoolName` varchar(40) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbldriver`
--

INSERT INTO `tbldriver` (`intDriverID`, `intLoginID`, `strDriverName`, `strAddress`, `dtmDOB`, `strPhoneNo`, `strLicenceNo`, `strSchoolName`) VALUES
(16, 51, 'Akshat', '39800 Fremont Blvd', '1994-03-17', '9824188624', 'LIC798', 'Stevenson BLvd'),
(15, 49, 'Akshat Shah', 'Fremont Blvd', '1994-03-17', '9088423844', 'LIC123', 'Fremont Blvd');

-- --------------------------------------------------------

--
-- Table structure for table `tblkid`
--

CREATE TABLE `tblkid` (
  `intKidID` int(11) NOT NULL,
  `intParentID` int(11) NOT NULL,
  `strKidName` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `strSchoolName` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `dtmDOB` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbllogin`
--

CREATE TABLE `tbllogin` (
  `intLoginID` int(11) NOT NULL,
  `strMobileNo` varchar(20) DEFAULT NULL,
  `strPassword` varchar(100) DEFAULT NULL,
  `strUserType` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbllogin`
--

INSERT INTO `tbllogin` (`intLoginID`, `strMobileNo`, `strPassword`, `strUserType`) VALUES
(49, '9088423844', 'e10adc3949ba59abbe56e057f20f883e', 'Driver'),
(50, '5105853126', 'e10adc3949ba59abbe56e057f20f883e', 'Parent'),
(51, '9824188624', 'e10adc3949ba59abbe56e057f20f883e', 'Driver'),
(52, '9624768577', '65dd76425a838802398b6ef4ff918f28', 'Parent');

-- --------------------------------------------------------

--
-- Table structure for table `tblparent`
--

CREATE TABLE `tblparent` (
  `intParentID` int(11) NOT NULL,
  `intLoginID` int(11) NOT NULL,
  `strFullName` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `strPhoneNo` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `strAddress` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tblparent`
--

INSERT INTO `tblparent` (`intParentID`, `intLoginID`, `strFullName`, `strPhoneNo`, `strAddress`) VALUES
(9, 47, 'Akshat Shah', '5105853126', ''),
(12, 52, 'Palak', '9624768577', '');

-- --------------------------------------------------------

--
-- Table structure for table `tblsenior`
--

CREATE TABLE `tblsenior` (
  `intSeniorID` int(11) NOT NULL,
  `strSeniorName` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `strGender` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `intAge` int(11) NOT NULL,
  `strPickupStand` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `strLicenceNo` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `strMobileNo` varchar(12) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tblsenior`
--

INSERT INTO `tblsenior` (`intSeniorID`, `strSeniorName`, `strGender`, `intAge`, `strPickupStand`, `strLicenceNo`, `strMobileNo`) VALUES
(13, 'ABCD', 'Male', 65, 'Stevenson Blvd/Fremont Blvd', 'Fremont Blvd', '9624768577'),
(12, 'XYZ Person', 'Male', 65, 'Stevenson Blvd/Fremont Blvd', 'Fremont Blvd', '5105853126');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbldriver`
--
ALTER TABLE `tbldriver`
  ADD PRIMARY KEY (`intDriverID`);

--
-- Indexes for table `tblkid`
--
ALTER TABLE `tblkid`
  ADD PRIMARY KEY (`intKidID`);

--
-- Indexes for table `tbllogin`
--
ALTER TABLE `tbllogin`
  ADD PRIMARY KEY (`intLoginID`);

--
-- Indexes for table `tblparent`
--
ALTER TABLE `tblparent`
  ADD PRIMARY KEY (`intParentID`);

--
-- Indexes for table `tblsenior`
--
ALTER TABLE `tblsenior`
  ADD PRIMARY KEY (`intSeniorID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbldriver`
--
ALTER TABLE `tbldriver`
  MODIFY `intDriverID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `tblkid`
--
ALTER TABLE `tblkid`
  MODIFY `intKidID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbllogin`
--
ALTER TABLE `tbllogin`
  MODIFY `intLoginID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
--
-- AUTO_INCREMENT for table `tblparent`
--
ALTER TABLE `tblparent`
  MODIFY `intParentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `tblsenior`
--
ALTER TABLE `tblsenior`
  MODIFY `intSeniorID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
