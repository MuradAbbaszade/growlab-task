CREATE TABLE `role` (
                        `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                        `role` varchar(256),
                        `status` varchar(256),
                        `created_by` BIGINT,
                        `create_time` datetime,
                        FOREIGN KEY (`created_by`) REFERENCES `user` (`id`)
);