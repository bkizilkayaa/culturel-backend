CREATE TABLE `zip_codes` (
                             `id` bigint NOT NULL,
                             `name` varchar(255) DEFAULT NULL,
                             `value` varchar(255) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
