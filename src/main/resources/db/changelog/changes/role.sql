CREATE TABLE `role` (
                        `id` integer PRIMARY KEY,
                        `role` varchar(255),
                        FOREIGN KEY (`id`) REFERENCES `role_attribute` (`role_id`),
);