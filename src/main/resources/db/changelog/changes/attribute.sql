CREATE TABLE `attribute` (
                             `id` integer PRIMARY KEY,
                             `attribute` varchar(255),
                             FOREIGN KEY (`id`) REFERENCES `role_attribute` (`attribute_id`)
);