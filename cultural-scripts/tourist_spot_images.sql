CREATE TABLE `tourist_spot_images` (
                                       `ID` bigint NOT NULL AUTO_INCREMENT,
                                       `tourist_spot_id` bigint DEFAULT NULL,
                                       `image_id` bigint NOT NULL,
                                       PRIMARY KEY (`ID`),
                                       KEY `FK843anshrfbmhvl829s2im1jhp` (`tourist_spot_id`),
                                       KEY `FKtrv9fetiex7a17jynk2lniqtr` (`image_id`),
                                       CONSTRAINT `FK843anshrfbmhvl829s2im1jhp` FOREIGN KEY (`tourist_spot_id`) REFERENCES `tourist_spot` (`id`),
                                       CONSTRAINT `FKtrv9fetiex7a17jynk2lniqtr` FOREIGN KEY (`image_id`) REFERENCES `file_data` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
