-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 24, 2022 at 06:38 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `carrent`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_admin`
--

CREATE TABLE `tbl_admin` (
  `admin_id` int(11) NOT NULL,
  `username` varchar(100) CHARACTER SET latin1 NOT NULL,
  `password` varchar(100) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_admin`
--

INSERT INTO `tbl_admin` (`admin_id`, `username`, `password`) VALUES
(1, 'admin', '552e6a97297c53e592208cf97fbb3b60');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_booking`
--

CREATE TABLE `tbl_booking` (
  `b_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `v_id` int(11) NOT NULL,
  `d_id` int(11) DEFAULT NULL,
  `booking_no` bigint(12) NOT NULL,
  `from_date` varchar(20) CHARACTER SET latin1 NOT NULL,
  `to_date` varchar(20) CHARACTER SET latin1 NOT NULL,
  `message` varchar(120) CHARACTER SET latin1 DEFAULT NULL,
  `b_status` int(11) DEFAULT NULL,
  `booking_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `action_date` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_booking`
--

INSERT INTO `tbl_booking` (`b_id`, `u_id`, `v_id`, `d_id`, `booking_no`, `from_date`, `to_date`, `message`, `b_status`, `booking_date`, `action_date`) VALUES
(1, 1, 2, 2, 201320421, '2022-02-25', '2022-02-27', 'where is your branch?', 0, '2022-02-23 07:53:56', '2022-02-24 04:29:20'),
(2, 2, 4, 0, 523102435, '2022-02-24', '2022-02-25', NULL, 0, '2022-02-23 08:02:53', NULL),
(3, 3, 1, 2, 123456789, '2022-02-24', '2022-02-27', 'cost ?', 0, '2022-02-23 08:04:47', NULL),
(4, 4, 21, 8, 320235261, '2022-02-25', '2022-02-26', NULL, 0, '2022-02-24 04:31:49', NULL),
(5, 5, 22, 0, 652301245, '2022-02-28', '2022-02-29', NULL, 0, '2022-02-24 04:31:49', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_brand`
--

CREATE TABLE `tbl_brand` (
  `brand_id` int(11) NOT NULL,
  `brand_name` varchar(120) CHARACTER SET latin1 NOT NULL,
  `listing_date` timestamp NULL DEFAULT current_timestamp(),
  `Updation_date` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_brand`
--

INSERT INTO `tbl_brand` (`brand_id`, `brand_name`, `listing_date`, `Updation_date`) VALUES
(1, 'Maruti', '2022-02-21 05:41:00', NULL),
(2, 'BMW', '2022-02-21 05:41:16', NULL),
(3, 'Audi', '2022-02-21 05:41:28', NULL),
(4, 'Nissan', '2022-02-21 05:42:03', NULL),
(15, 'Toyota', '2022-02-24 03:53:53', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_contactusinfo`
--

CREATE TABLE `tbl_contactusinfo` (
  `id` int(11) NOT NULL,
  `address` tinytext CHARACTER SET latin1 NOT NULL,
  `email_id` varchar(30) CHARACTER SET latin1 NOT NULL,
  `contact_no` char(11) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_contactusinfo`
--

INSERT INTO `tbl_contactusinfo` (`id`, `address`, `email_id`, `contact_no`) VALUES
(1, 'J&K Block, Laxmi Nagar, Mota Varachha, Surat.', 'heelghevariya@gmail.com', '8460601539');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_driver`
--

CREATE TABLE `tbl_driver` (
  `d_id` int(11) NOT NULL,
  `fullname` varchar(50) CHARACTER SET latin1 NOT NULL,
  `emailid` varchar(50) CHARACTER SET latin1 NOT NULL,
  `mobile_no` char(10) CHARACTER SET latin1 NOT NULL,
  `photo` varchar(120) CHARACTER SET latin1 NOT NULL,
  `aadhaarcard_no` char(12) CHARACTER SET latin1 NOT NULL,
  `license_no` varchar(20) CHARACTER SET latin1 NOT NULL,
  `experience` int(10) NOT NULL,
  `driver_fees` int(10) NOT NULL,
  `occupied_date` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `joining_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updation_date` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_driver`
--

INSERT INTO `tbl_driver` (`d_id`, `fullname`, `emailid`, `mobile_no`, `photo`, `aadhaarcard_no`, `license_no`, `experience`, `driver_fees`, `occupied_date`, `status`, `joining_date`, `updation_date`) VALUES
(1, 'Mukesh Prajapati', 'vaibhavradadiya0@gmail.com', '6253467589', 'c333273fcfc3198e93df380c0cfc0437.jpg', '665665686465', 'GJ0523265515155', 3, 500, '0', 1, '2022-02-21 05:54:24', '2022-02-22 17:30:26'),
(2, 'Rajesh Rana', 'heelghevariya@gmail.com', '8465326575', 'acceptedphoto1.png', '685943754555', 'GJ0526486526464', 4, 500, '2022-02-27', 1, '2022-02-21 05:55:22', '2022-02-23 07:55:35'),
(7, 'Ajay Patel', 'ghevariyaheel1@gmail.com', '8653456821', '8360f6e8e6167d545b0c34de7490cc1e--passport.jpg', '685685685446', 'MH0352316521212', 2, 500, NULL, NULL, '2022-02-24 03:48:43', NULL),
(8, 'Sanjay Chavda', 'sem6project1@gmail.com', '9756425316', 'FB_IMG_1605666747087.jpg', '601425023137', 'GJ1256243265626', 3, 500, NULL, 1, '2022-02-24 03:50:15', '2022-02-24 05:07:43'),
(9, 'Piyush Gohel', 'rutvik3398@gmail.com', '6853410052', 'Passport-Size-Pic.jpg', '800652322323', 'GJ3356853865685', 1, 500, NULL, NULL, '2022-02-24 03:51:48', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_inquiry`
--

CREATE TABLE `tbl_inquiry` (
  `id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET latin1 NOT NULL,
  `email_id` varchar(50) CHARACTER SET latin1 NOT NULL,
  `contact_no` char(11) CHARACTER SET latin1 DEFAULT NULL,
  `message` longtext CHARACTER SET latin1 NOT NULL,
  `inquiry_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_inquiry`
--

INSERT INTO `tbl_inquiry` (`id`, `name`, `email_id`, `contact_no`, `message`, `inquiry_date`, `status`) VALUES
(1, 'heel', 'heelghevariya@gmail.com', '8460601539', 'where is the other branch?', '2022-02-07 05:56:44', 0),
(2, 'vaibhav', 'ghevariyaheel1@gmail.com', '992542153', 'cost?', '2022-02-07 08:00:34', 0),
(3, 'om', 'vaibhavradadiya0@gmail.com', NULL, 'How much driver service cost?', '2022-02-24 05:02:09', 0),
(4, 'Rutvik', 'rutvik3398@gmail.com', '6523532656', 'Is kyc mandatory?', '2022-02-24 05:02:09', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_kyc`
--

CREATE TABLE `tbl_kyc` (
  `k_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `full_name` varchar(50) CHARACTER SET latin1 NOT NULL,
  `aadhaarcard_no` char(12) CHARACTER SET latin1 NOT NULL,
  `aadhaarimg1` varchar(120) CHARACTER SET latin1 NOT NULL,
  `aadhaarimg2` varchar(120) CHARACTER SET latin1 NOT NULL,
  `pancard_no` char(10) CHARACTER SET latin1 NOT NULL,
  `pancardimg1` varchar(120) CHARACTER SET latin1 NOT NULL,
  `license_no` varchar(20) CHARACTER SET latin1 NOT NULL,
  `licenseimg1` varchar(120) CHARACTER SET latin1 NOT NULL,
  `licenseimg2` varchar(120) CHARACTER SET latin1 NOT NULL,
  `status` int(11) DEFAULT NULL,
  `kycrequest_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `Updation_date` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_kyc`
--

INSERT INTO `tbl_kyc` (`k_id`, `u_id`, `full_name`, `aadhaarcard_no`, `aadhaarimg1`, `aadhaarimg2`, `pancard_no`, `pancardimg1`, `license_no`, `licenseimg1`, `licenseimg2`, `status`, `kycrequest_date`, `Updation_date`) VALUES
(3, 1, 'Jonil Shingala', '652032562350', 'shot.jpg', '2.jpg', 'AWM5234162', '1e138f5aa1d54cea5c2938a20fc1aa85.jpg', 'GJ0553241625327', '201-permanent-licence-e0c0.jpg', '681ae540-abcc-4ca0-919e-7d0cba472f38.jpg', 0, '2022-02-24 04:07:39', NULL),
(4, 2, 'Heel Ghevariya', '685042301226', 'R.jfif', '2 (1).jpg', 'AMG1503262', 'images.jpg', 'GJ0352362120253', 'images (1).jpg', 'dl.jpg', 0, '2022-02-24 04:07:39', NULL),
(5, 3, 'Vaibhav Radadiya', '603202530232', 'product-jpeg-500x500.jpeg', '2.jpg', 'ASA5232012', '2f9a62d3f6be9f0ded94bdb576a43d25.jpg', 'GJ0522352623521', 'Untitled-design-30.jpg', 'OIP.jfif', 0, '2022-02-24 04:11:31', NULL),
(6, 4, 'Rutvik Ajagiya', '865025320265', 'f99b3efadece9bde10e251760cc71b1c.jpg', '2 (1).jpg', 'ASD5230212', 'new-pan-card-500x500.jpg', 'GJ0352362120253', 'bihar.jpg', 'Delhi license.jpg', 0, '2022-02-24 04:11:31', NULL),
(7, 5, 'Ravi Mulani', '602102302102', 'thumb.jfif', '2.jpg', 'AVD3240042', '2f9a62d3f6be9f0ded94bdb576a43d25.jpg', 'GJ0523400023412', 'IMG_20190204_231755-300x194.jpg', 'dl.jpg', 0, '2022-02-24 04:13:06', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_pages`
--

CREATE TABLE `tbl_pages` (
  `page_id` int(11) NOT NULL,
  `page_name` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `type` varchar(30) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `detail` longtext CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_pages`
--

INSERT INTO `tbl_pages` (`page_id`, `page_name`, `type`, `detail`) VALUES
(1, 'Terms and Conditions', 'terms', '(1)<span style=\"text-decoration-line: underline;\"> ACCEPTANCE OF TERMS\r\n</span>\r\nWelcome to Yahoo! India. 1Yahoo Web Services India Private Limited Yahoo\", \"we\" or \"us\" as the case may be) provides the Service (defined below) to you, subject to the following Terms of Service (\"TOS\"), which may be updated by us from time to time without notice to you. You can review the most current version of the TOS at any time at: http://in.docs.yahoo.com/info/terms/. In addition, when using particular Yahoo services or third party services, you and Yahoo shall be subject to any posted guidelines or rules applicable to such services which may be posted from time to time. All such guidelines or rules, which maybe subject to change, are hereby incorporated by reference into the TOS. In most cases the guides and rules are specific to a particular part of the Service and will assist you in applying the TOS to that part, but to the extent of any inconsistency between the TOS and any guide or rule, the TOS will prevail. We may also offer other services from time to time that are governed by different Terms of Services, in which case the TOS do not apply to such other services if and to the extent expressly excluded by such different Terms of Services. Yahoo also may offer other services from time to time that are governed by different Terms of Services. These TOS do not apply to such other services that are governed by different Terms of Service.\r\n\r\nWelcome to Yahoo! India. Yahoo Web Services India Private Limited Yahoo\", \"we\" or \"us\" as the case may be) provides the Service (defined below) to you, subject to the following Terms of Service (\"TOS\"), which may be updated by us from time to time without notice to you. You can review the most current version of the TOS at any time at: http://in.docs.yahoo.com/info/terms/. In addition, when using particular Yahoo services or third party services, you and Yahoo shall be subject to any posted guidelines or rules applicable to such services which may be posted from time to time. All such guidelines or rules, which maybe subject to change, are hereby incorporated by reference into the TOS. In most cases the guides and rules are specific to a particular part of the Service and will assist you in applying the TOS to that part, but to the extent of any inconsistency between the TOS and any guide or rule, the TOS will prevail. We may also offer other services from time to time that are governed by different Terms of Services, in which case the TOS do not apply to such other services if and to the extent expressly excluded by such different Terms of Services. Yahoo also may offer other services from time to time that are governed by different Terms of Services. These TOS do not apply to such other services that are governed by different Terms of Service.\r\n\r\nWelcome to Yahoo! India. Yahoo Web Services India Private Limited Yahoo\", \"we\" or \"us\" as the case may be) provides the Service (defined below) to you, subject to the following Terms of Service (\"TOS\"), which may be updated by us from time to time without notice to you. You can review the most current version of the TOS at any time at: http://in.docs.yahoo.com/info/terms/. In addition, when using particular Yahoo services or third party services, you and Yahoo shall be subject to any posted guidelines or rules applicable to such services which may be posted from time to time. All such guidelines or rules, which maybe subject to change, are hereby incorporated by reference into the TOS. In most cases the guides and rules are specific to a particular part of the Service and will assist you in applying the TOS to that part, but to the extent of any inconsistency between the TOS and any guide or rule, the TOS will prevail. We may also offer other services from time to time that are governed by different Terms of Services, in which case the TOS do not apply to such other services if and to the extent expressly excluded by such different Terms of Services. Yahoo also may offer other services from time to time that are governed by different Terms of Services. These TOS do not apply to such other services that are governed by different Terms of Service.\r\n										\r\n										'),
(2, 'Privacy Policy', 'privacy', 'At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat'),
(3, 'About Us ', 'aboutus', 'We offer a varied fleet of cars, ranging from the compact. All our vehicles have air conditioning,  power steering, electric windows. All our vehicles are bought and maintained at official dealerships only. Automatic transmission cars are available in every booking class. As we are not affiliated with any specific automaker, we are able to provide a variety of vehicle makes and models for customers to rent.\r\nur mission is to be recognised as the global leader in Car Rental for companies and the public and private sector by partnering with our clients to provide the best and most efficient Cab Rental solutions and to achieve service excellence.'),
(4, 'FAQs', 'faqs', 'Address');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_payment`
--

CREATE TABLE `tbl_payment` (
  `p_id` int(11) NOT NULL,
  `booking_id` int(11) NOT NULL,
  `transaction_id` bigint(12) NOT NULL,
  `amount_paid` int(11) DEFAULT NULL,
  `p_status` int(11) DEFAULT NULL,
  `payment_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_payment`
--

INSERT INTO `tbl_payment` (`p_id`, `booking_id`, `transaction_id`, `amount_paid`, `p_status`, `payment_date`) VALUES
(1, 1, 253625865, NULL, 0, '2022-02-21 06:13:00'),
(2, 2, 312457512, 16000, 1, '2022-02-21 06:16:12'),
(3, 3, 362523625, 16000, 1, '2022-02-23 08:45:35'),
(4, 4, 352652352, 0, 0, '2022-02-24 04:56:20'),
(5, 5, 663252123, 30000, 1, '2022-02-24 04:56:20');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_reviewrating`
--

CREATE TABLE `tbl_reviewrating` (
  `r_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `review` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `posting_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_reviewrating`
--

INSERT INTO `tbl_reviewrating` (`r_id`, `u_id`, `review`, `rating`, `status`, `posting_date`) VALUES
(1, 1, 'Good Service.', 4, 0, '2022-02-21 06:01:31'),
(2, 2, NULL, 2, 0, '2022-02-21 06:08:49'),
(3, 3, 'Poor Managment.', 1, 0, '2022-02-24 05:03:38'),
(4, 4, NULL, 5, 0, '2022-02-24 05:03:55'),
(5, 5, 'Value for money.', 4, 0, '2022-02-24 05:05:28');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_subscribers`
--

CREATE TABLE `tbl_subscribers` (
  `sid` int(11) NOT NULL,
  `subscriber_email` varchar(30) CHARACTER SET latin1 NOT NULL,
  `joining_date` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_subscribers`
--

INSERT INTO `tbl_subscribers` (`sid`, `subscriber_email`, `joining_date`) VALUES
(1, 'ghevariyaheel1@gmail.com', '2022-02-06 13:23:47'),
(8, 'heelghevariya@gmail.com', '2022-02-24 05:06:01'),
(9, 'sem6project1@gmail.com', '2022-02-24 05:06:01'),
(10, 'rutvik3398@gmail.com', '2022-02-24 05:06:42'),
(11, 'vaibhavradadiya0@gmail.com', '2022-02-24 05:06:42');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(30) CHARACTER SET latin1 NOT NULL,
  `email_id` varchar(50) CHARACTER SET latin1 NOT NULL,
  `password` varchar(50) CHARACTER SET latin1 NOT NULL,
  `mobile_no` varchar(12) CHARACTER SET latin1 NOT NULL,
  `dob` varchar(50) CHARACTER SET latin1 NOT NULL,
  `address` varchar(255) CHARACTER SET latin1 NOT NULL,
  `country` varchar(10) CHARACTER SET latin1 NOT NULL,
  `state` varchar(10) CHARACTER SET latin1 NOT NULL,
  `city` varchar(10) CHARACTER SET latin1 NOT NULL,
  `isbooked` int(11) DEFAULT NULL,
  `register_date` timestamp NULL DEFAULT current_timestamp(),
  `updation_date` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`user_id`, `user_name`, `email_id`, `password`, `mobile_no`, `dob`, `address`, `country`, `state`, `city`, `isbooked`, `register_date`, `updation_date`) VALUES
(1, 'Jonil', 'heelghevariya@gmail.com', '67c7557a715a7087455fc575050d2564', '8460601539', '03-02-2002', 'a-7,rajhans,surat.', 'India', 'Gujarat', 'Surat', NULL, '2022-02-23 07:45:23', NULL),
(2, 'Heel', 'ghevariyaheel1@gmail.com', '8460601539', '6583564253', '21-04-2002', '35,ashwin soc-3,umiaroad,surat.', 'India', 'Gujarat', 'Surat', 0, '2022-02-23 08:01:49', NULL),
(3, 'Vaibhav', 'vaibhavradadiya0@gmail.com', '12451245', '8656835626', '21-04-2002', 'a-12,shangrila,kapodara,surat.', 'India', 'Gujarat', 'Surat', NULL, '2022-02-23 08:04:11', NULL),
(4, 'Rutvik', 'rutvik3398@gmail.com', '26535d323', '6586567549', '21-02-2001', '67-B,victoria soc,udhna', 'India', 'Gujarat', 'Surat', NULL, '2022-02-24 03:40:40', NULL),
(5, 'Ravi', 'sem6project1@gmail.com', '6856865686', '9856754213', '15-05-2003', '32,bajrang nagar soc-2,varachha,surat.', 'India', 'Gujarat', 'Surat', NULL, '2022-02-24 03:40:40', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_vehicles`
--

CREATE TABLE `tbl_vehicles` (
  `vehicle_id` int(11) NOT NULL,
  `brand_id` int(11) NOT NULL,
  `vehicle_title` varchar(50) CHARACTER SET latin1 NOT NULL,
  `vehicle_no` varchar(20) CHARACTER SET latin1 NOT NULL,
  `vehicle_detail` longtext CHARACTER SET latin1 DEFAULT NULL,
  `priceperday` int(11) NOT NULL,
  `fuel_type` varchar(20) CHARACTER SET latin1 NOT NULL,
  `model_year` int(6) NOT NULL,
  `seating_capacity` int(11) NOT NULL,
  `vimage1` varchar(120) CHARACTER SET latin1 NOT NULL,
  `vimage2` varchar(120) CHARACTER SET latin1 NOT NULL,
  `vimage3` varchar(120) CHARACTER SET latin1 NOT NULL,
  `vimage4` varchar(120) CHARACTER SET latin1 DEFAULT NULL,
  `vimage5` varchar(120) CHARACTER SET latin1 DEFAULT NULL,
  `Airconditioner` int(11) DEFAULT NULL,
  `Childdoorlock` int(11) DEFAULT NULL,
  `Brakeassiste` int(11) DEFAULT NULL,
  `Driverairbage` int(11) DEFAULT NULL,
  `Passengerairbage` int(11) DEFAULT NULL,
  `Powerwindow` int(11) DEFAULT NULL,
  `Smartgps` int(11) DEFAULT NULL,
  `LEDdisplay` int(11) DEFAULT NULL,
  `Airfreshner` int(11) DEFAULT NULL,
  `Auxcable` int(11) DEFAULT NULL,
  `Dashcam` int(11) DEFAULT NULL,
  `Posting_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `Updation_date` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_vehicles`
--

INSERT INTO `tbl_vehicles` (`vehicle_id`, `brand_id`, `vehicle_title`, `vehicle_no`, `vehicle_detail`, `priceperday`, `fuel_type`, `model_year`, `seating_capacity`, `vimage1`, `vimage2`, `vimage3`, `vimage4`, `vimage5`, `Airconditioner`, `Childdoorlock`, `Brakeassiste`, `Driverairbage`, `Passengerairbage`, `Powerwindow`, `Smartgps`, `LEDdisplay`, `Airfreshner`, `Auxcable`, `Dashcam`, `Posting_date`, `Updation_date`) VALUES
(1, 1, 'Maruti Suzuki Wagon ', 'GJ05KK2272', 'Maruti Wagon R Latest Updates\r\n\r\nMaruti Suzuki has launched the BS6 Wagon R S-CNG in India. The LXI CNG and LXI (O) CNG variants now cost Rs 5.25 lakh and Rs 5.32 lakh respectively, up by Rs 19,000. Maruti claims a fuel economy of 32.52km per kg. The C', 5000, 'Petrol', 2019, 5, 'rear-3-4-left-589823254_930x620.jpg', 'steering-close-up-1288209207_930x620.jpg', 'rear-3-4-right-520328200_930x620.jpg', 'tail-lamp-1666712219_930x620.jpg', 'boot-with-standard-luggage-202327489_930x620.jpg', 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, '2022-02-21 05:45:22', '2022-02-24 03:54:55'),
(2, 2, 'BMW 5 Series', 'GJ05AS2463', 'BMW 5 Series price starts at ? 55.4 Lakh and goes upto ? 68.39 Lakh. The price of Petrol version for 5 Series ranges between ? 55.4 Lakh - ? 60.89 Lakh and the price of Diesel version for 5 Series ranges between ? 60.89 Lakh - ? 68.39 Lakh.', 8000, 'Petrol', 2018, 5, 'BMW-5-Series-Exterior-102006.jpg', 'BMW-5-Series-Interior-102021.jpg', 'BMW-5-Series-Exterior-102005.jpg', 'BMW-5-Series-Interior-102022.jpg', 'BMW-5-Series-New-Exterior-89729.jpg', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, '2022-02-21 05:48:24', NULL),
(4, 4, 'Nissan Kicks', 'MH03VD5682', 'Latest Update: Nissan has launched the Kicks 2020 with a new turbocharged petrol engine. You can read more about it here.\r\n\r\nNissan Kicks Price and Variants: The Kicks is available in four variants: XL, XV, XV Premium, and XV Premium(O).', 15000, 'Petrol', 2020, 5, 'front-left-side-47.jpg', 'kicksmodelimage.jpg', 'download.jpg', '', '', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, '2022-02-21 05:52:44', '2022-02-22 17:40:31'),
(21, 3, 'Audi Q8', 'MH11FF2051', 'As per ARAI, the mileage of Q8 is 0 kmpl. Real mileage of the vehicle varies depending upon the driving habits. City and highway mileage figures also vary depending upon the road conditions.', 18000, 'Petrol', 2017, 5, '1audiq8.jpg', '1920x1080_MTC_XL_framed_Audi-Odessa-Armaturen_Spiegelung_CC_v05.jpg', 'audi1.jpg', 'audi-q8-front-view4.jpeg', '', 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, '2022-02-24 03:57:06', NULL),
(22, 15, 'Toyota Fortuner', 'DH03GH5023', 'Toyota Fortuner Features: It is a premium seven-seater SUV loaded with features such as LED projector headlamps with LED DRLs, LED fog lamp, and power-adjustable and foldable ORVMs. Inside, the Fortuner offers features such as power-adjustable driver', 17500, 'Petrol', 2020, 5, '2015_Toyota_Fortuner_(New_Zealand).jpg', 'toyota-fortuner-legender-rear-quarters-6e57.jpg', 'download (1).jpg', '', '', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, '2022-02-24 03:58:49', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_admin`
--
ALTER TABLE `tbl_admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `tbl_booking`
--
ALTER TABLE `tbl_booking`
  ADD PRIMARY KEY (`b_id`);

--
-- Indexes for table `tbl_brand`
--
ALTER TABLE `tbl_brand`
  ADD PRIMARY KEY (`brand_id`);

--
-- Indexes for table `tbl_contactusinfo`
--
ALTER TABLE `tbl_contactusinfo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_driver`
--
ALTER TABLE `tbl_driver`
  ADD PRIMARY KEY (`d_id`);

--
-- Indexes for table `tbl_inquiry`
--
ALTER TABLE `tbl_inquiry`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_kyc`
--
ALTER TABLE `tbl_kyc`
  ADD PRIMARY KEY (`k_id`);

--
-- Indexes for table `tbl_pages`
--
ALTER TABLE `tbl_pages`
  ADD PRIMARY KEY (`page_id`);

--
-- Indexes for table `tbl_payment`
--
ALTER TABLE `tbl_payment`
  ADD PRIMARY KEY (`p_id`);

--
-- Indexes for table `tbl_reviewrating`
--
ALTER TABLE `tbl_reviewrating`
  ADD PRIMARY KEY (`r_id`);

--
-- Indexes for table `tbl_subscribers`
--
ALTER TABLE `tbl_subscribers`
  ADD PRIMARY KEY (`sid`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `tbl_vehicles`
--
ALTER TABLE `tbl_vehicles`
  ADD PRIMARY KEY (`vehicle_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_admin`
--
ALTER TABLE `tbl_admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_booking`
--
ALTER TABLE `tbl_booking`
  MODIFY `b_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tbl_brand`
--
ALTER TABLE `tbl_brand`
  MODIFY `brand_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `tbl_contactusinfo`
--
ALTER TABLE `tbl_contactusinfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_driver`
--
ALTER TABLE `tbl_driver`
  MODIFY `d_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tbl_inquiry`
--
ALTER TABLE `tbl_inquiry`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_kyc`
--
ALTER TABLE `tbl_kyc`
  MODIFY `k_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tbl_pages`
--
ALTER TABLE `tbl_pages`
  MODIFY `page_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_payment`
--
ALTER TABLE `tbl_payment`
  MODIFY `p_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tbl_reviewrating`
--
ALTER TABLE `tbl_reviewrating`
  MODIFY `r_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tbl_subscribers`
--
ALTER TABLE `tbl_subscribers`
  MODIFY `sid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tbl_vehicles`
--
ALTER TABLE `tbl_vehicles`
  MODIFY `vehicle_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
