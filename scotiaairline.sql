SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Table structure for table `Flight`
--

CREATE TABLE `Flight` (
  `FlightID` varchar(25) NOT NULL,
  `Departure` varchar(25) NOT NULL,
  `Arrival` varchar(25) NOT NULL,
  `Rows` int(2) NOT NULL,
  `Columns` int(2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

INSERT INTO `Flight` (`FlightID`,`Departure`,`Arrival`,`Rows`,`Columns`) VALUES
('SA123','GLA','LON','2','15'),
('SA124','LON','GLA','2','15'),
('SA234','GLA','ABR','3','20'),
('SA235','ABR','GLA','3','20'),
('SA345','GLA','SYY','2','20'),
('SA346','SYY','GLA','2','20');


--
-- Table structure for table `Passenger`
--

CREATE TABLE `Passenger` (
  `SeatNo` varchar(3) NOT NULL,
  `PassengerName` varchar(50) NOT NULL,
  `Type` varchar(25) NOT NULL,
  `Information` varchar(25) NOT NULL,
  `FlightID` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Seat`
--

CREATE TABLE `Seat` (
  `SeatNo` varchar(3) NOT NULL,
  `Status` varchar(25) NOT NULL,
  `Takings` int(50) NOT NULL,
  `FlightID` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for table `Passenger`
--
ALTER TABLE `Passenger`
  ADD PRIMARY KEY (`SeatNo`,`FlightID`);
--
ALTER TABLE `Flight`
  ADD PRIMARY KEY (`FlightID`);

--
-- Indexes for table `Seat`
--
ALTER TABLE `Seat`
  ADD PRIMARY KEY (`SeatNo`,`FlightID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
