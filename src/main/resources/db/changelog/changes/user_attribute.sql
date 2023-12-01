CREATE TABLE `user_attribute` (
                             `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                             `attribute_id` BIGINT,
                             `user_id` BIGINT,
                             FOREIGN KEY (`attribute_id`) REFERENCES `attribute` (`id`),
                             FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);