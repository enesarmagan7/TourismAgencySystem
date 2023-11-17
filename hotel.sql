-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 17 Kas 2023, 18:51:51
-- Sunucu sürümü: 8.0.31
-- PHP Sürümü: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `hotel`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hotel`
--

CREATE TABLE `hotel` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `star` enum('*****','****','***','') COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_property` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `hotel`
--

INSERT INTO `hotel` (`id`, `name`, `star`, `hotel_property`, `address`, `phone`, `email`) VALUES
(1, 'Swiss', '*****', 'İnternet\nÜcretsiz WiFi', 'İstanbul/Avcılar', '05343434333', 'swis@hotel.com'),
(3, 'Hilton', '****', 'Fitness Center', 'Ankara', '01261232112', 'hilton@hotel.com'),
(5, 'İzmir Hotel', '****', 'Ücretsiz Wifi', 'İzmir Merkez No:7', '0324324', 'izmirhotel@gmail.com');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `reservation_info`
--

CREATE TABLE `reservation_info` (
  `id` int NOT NULL,
  `room_id` int DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `note` text COLLATE utf8mb4_general_ci,
  `checkin_date` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `checkout_date` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `adult_count` int DEFAULT NULL,
  `child_count` int DEFAULT NULL,
  `total_price` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `reservation_info`
--

INSERT INTO `reservation_info` (`id`, `room_id`, `name`, `phone`, `email`, `note`, `checkin_date`, `checkout_date`, `adult_count`, `child_count`, `total_price`) VALUES
(22, 4, 'Enes', 'aycan', 'enes34.point@gmail.com', 'Sabah erken geleceğim', '22/12/2023', '25/12/2023', 2, 1, 4500);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `room`
--

CREATE TABLE `room` (
  `id` int NOT NULL,
  `room_type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `season_id` int DEFAULT NULL,
  `adult_price` int DEFAULT NULL,
  `child_price` int DEFAULT NULL,
  `hotel_type_id` int DEFAULT NULL,
  `hotel_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `room`
--

INSERT INTO `room` (`id`, `room_type`, `stock`, `season_id`, `adult_price`, `child_price`, `hotel_type_id`, `hotel_id`) VALUES
(3, 'Suit', 45, 1, 400, 100, 1, 1),
(4, 'Double', 24, 1, 700, 100, 1, 1),
(6, 'Double', 1, 1, 300, 100, 1, 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `room_properties`
--

CREATE TABLE `room_properties` (
  `id` int NOT NULL,
  `room_property` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `room_id` int DEFAULT NULL,
  `bed` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `area` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `room_properties`
--

INSERT INTO `room_properties` (`id`, `room_property`, `room_id`, `bed`, `area`) VALUES
(2, 'televizyon,Oyun Konsolu', 6, '2', 45),
(3, '\ntelevizyon', 3, '3', 50),
(4, '\ntelevizyon', 4, '4', 45);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `season`
--

CREATE TABLE `season` (
  `id` int NOT NULL,
  `season_start` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `season_end` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `season`
--

INSERT INTO `season` (`id`, `season_start`, `season_end`, `hotel_id`) VALUES
(1, '21/12/2023', '21/05/2024', 1),
(3, '10/12/2023', '06/06/2024', 3),
(5, '02/01/2024', '05/05/2024', 3),
(6, '29/12/2023', '07/12/2024', 1),
(7, '23/12/2023', '12/01/2024', 5),
(8, '', '', 5);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `type_hotel`
--

CREATE TABLE `type_hotel` (
  `id` int NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `type_hotel`
--

INSERT INTO `type_hotel` (`id`, `type`, `hotel_id`) VALUES
(1, 'Herşey Dahil', 1),
(2, 'Tam Pansiyon', 1),
(3, 'Oda Kahvaltı', 3),
(6, 'Tam Pansiyon', 5),
(7, 'Yarım Pansiyon', 5);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `uname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `pass` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `type` enum('employee','admin') COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `user`
--

INSERT INTO `user` (`id`, `name`, `uname`, `pass`, `type`) VALUES
(1, 'aa', 'aa', 'aa', 'admin'),
(2, 'bb', 'bb', 'bb', 'employee');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `reservation_info`
--
ALTER TABLE `reservation_info`
  ADD PRIMARY KEY (`id`),
  ADD KEY `room_id` (`room_id`);

--
-- Tablo için indeksler `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`),
  ADD KEY `season_id` (`season_id`),
  ADD KEY `hotel_type_id` (`hotel_type_id`),
  ADD KEY `hotel_id` (`hotel_id`);

--
-- Tablo için indeksler `room_properties`
--
ALTER TABLE `room_properties`
  ADD PRIMARY KEY (`id`),
  ADD KEY `room_id` (`room_id`);

--
-- Tablo için indeksler `season`
--
ALTER TABLE `season`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_season_hotel` (`hotel_id`);

--
-- Tablo için indeksler `type_hotel`
--
ALTER TABLE `type_hotel`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_type_hotel_hotel` (`hotel_id`);

--
-- Tablo için indeksler `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `reservation_info`
--
ALTER TABLE `reservation_info`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Tablo için AUTO_INCREMENT değeri `room`
--
ALTER TABLE `room`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Tablo için AUTO_INCREMENT değeri `room_properties`
--
ALTER TABLE `room_properties`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Tablo için AUTO_INCREMENT değeri `season`
--
ALTER TABLE `season`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Tablo için AUTO_INCREMENT değeri `type_hotel`
--
ALTER TABLE `type_hotel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Tablo için AUTO_INCREMENT değeri `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `reservation_info`
--
ALTER TABLE `reservation_info`
  ADD CONSTRAINT `reservation_info_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`);

--
-- Tablo kısıtlamaları `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `room_ibfk_1` FOREIGN KEY (`season_id`) REFERENCES `season` (`id`),
  ADD CONSTRAINT `room_ibfk_2` FOREIGN KEY (`hotel_type_id`) REFERENCES `type_hotel` (`id`),
  ADD CONSTRAINT `room_ibfk_3` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`);

--
-- Tablo kısıtlamaları `room_properties`
--
ALTER TABLE `room_properties`
  ADD CONSTRAINT `room_properties_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`);

--
-- Tablo kısıtlamaları `season`
--
ALTER TABLE `season`
  ADD CONSTRAINT `FK_season_hotel` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`);

--
-- Tablo kısıtlamaları `type_hotel`
--
ALTER TABLE `type_hotel`
  ADD CONSTRAINT `FK_type_hotel_hotel` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
