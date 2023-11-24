CREATE TABLE `log` (
                       `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                       `event_time` datetime,
                       `user_id` BIGINT,
                       `operation_id` varchar(256),
                       `old_value` varchar(256),
                       `new_value` varchar(256),
                       `accepted_by` BIGINT,
                       `accepted_time` datetime,
                       FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                       FOREIGN KEY (`accepted_by`) REFERENCES `user` (`id`)

);