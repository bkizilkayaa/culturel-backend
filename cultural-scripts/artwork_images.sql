CREATE TABLE `artwork_images` (
                                  `ID` bigint NOT NULL AUTO_INCREMENT,
                                  `artwork_id` bigint DEFAULT NULL,
                                  `image_id` bigint NOT NULL,
                                  PRIMARY KEY (`ID`),
                                  KEY `FK2kvrjpg0cp5y0fad9snxstqqp` (`artwork_id`),
                                  KEY `FK9kwbs0kribnt0iwnj71wgqtm6` (`image_id`),
                                  CONSTRAINT `FK2kvrjpg0cp5y0fad9snxstqqp` FOREIGN KEY (`artwork_id`) REFERENCES `artworks` (`id`),
                                  CONSTRAINT `FK9kwbs0kribnt0iwnj71wgqtm6` FOREIGN KEY (`image_id`) REFERENCES `file_data` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
