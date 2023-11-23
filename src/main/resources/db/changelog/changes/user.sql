CREATE TABLE `user` (
                        `id` integer PRIMARY KEY,
                        `username` varchar(255),
                        `email` varchar(255),
                        `mobile` varchar(255),
                        `name` varchar(255),
                        `surname` varchar(255),
                        `status` varchar(255),
                        `created_by` integer,
                        `create_time` datetime,
                        `password` varchar(255),
                        `last_update_time` datetime,
                        `auth_status` varchar(255),
                        `role_id` integer,
                        FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
                        FOREIGN KEY (`created_by`) REFERENCES `user` (`id`)
);