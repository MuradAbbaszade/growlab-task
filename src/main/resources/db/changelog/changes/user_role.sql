CREATE TABLE `user_role` (
                        `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                        `user_id` BIGINT,
                        `role_id` BIGINT,
                        FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                        FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
);