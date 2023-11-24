CREATE TABLE `user` (
                        `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                        `username` varchar(256),
                        `email` varchar(256),
                        `mobile` varchar(256),
                        `name` varchar(256),
                        `surname` varchar(256),
                        `status` varchar(256),
                        `created_by` BIGINT,
                        `create_time` datetime,
                        `password` varchar(256),
                        `last_update_time` datetime,
                        `auth_status` varchar(256),
                        FOREIGN KEY (`created_by`) REFERENCES `user` (`id`)
);