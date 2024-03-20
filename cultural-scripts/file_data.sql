CREATE TABLE `file_data` (
                             `id` bigint NOT NULL,
                             `name` varchar(255) DEFAULT NULL,
                             `type` varchar(255) DEFAULT NULL,
                             `create_date` datetime(6) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
