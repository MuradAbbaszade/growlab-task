CREATE TABLE `role_attribute` (
                                  `id` integer PRIMARY KEY,
                                  `role_id` integer,
                                  `attribute_id` integer,
                                  FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
                                  FOREIGN KEY (`attribute_id`) REFERENCES `attribute` (`id`)
);