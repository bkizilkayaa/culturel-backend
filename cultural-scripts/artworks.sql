CREATE TABLE `artworks` (
                            `id` bigint NOT NULL,
                            `title` varchar(255) DEFAULT NULL,
                            `description` varchar(255) DEFAULT NULL,
                            `content` text,
                            `create_date` datetime(6) DEFAULT NULL,
                            `modified_date` datetime(6) DEFAULT NULL,
                            `parent_id` bigint DEFAULT NULL,
                            `zip_code_id` bigint DEFAULT NULL,
                            `author_id` bigint DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FKsymltulh3egae34bsvc023uik` (`zip_code_id`),
                            CONSTRAINT `FKsymltulh3egae34bsvc023uik` FOREIGN KEY (`zip_code_id`) REFERENCES `zip_codes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
