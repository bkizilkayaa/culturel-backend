CREATE TABLE `authors` (
                           `id` bigint NOT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `role` int DEFAULT NULL,
                           `create_date` datetime(6) DEFAULT NULL,
                           `modified_date` datetime(6) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
