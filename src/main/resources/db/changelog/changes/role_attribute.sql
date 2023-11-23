CREATE TABLE `role_attribute` (
                                  `id` integer PRIMARY KEY,
                                  `role_id` integer,
                                  `attribute_id` integer,
                                  FOREIGN KEY (`id`) REFERENCES `role_attribute` (`role_id`),
                                  FOREIGN KEY (`id`) REFERENCES `role_attribute` (`attribute_id`);
);