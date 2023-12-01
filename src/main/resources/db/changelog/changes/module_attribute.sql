CREATE TABLE `module_attribute` (
                             `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                             `module_id` BIGINT,
                             `attribute_id` BIGINT,
                             FOREIGN KEY (`attribute_id`) REFERENCES `attribute` (`id`),
                             FOREIGN KEY (`module_id`) REFERENCES `module` (`id`)
);