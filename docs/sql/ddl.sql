-- Generated 2026-03-17 21:44:58-0600 for database version 1

CREATE TABLE IF NOT EXISTS `fossil`
(
    `fossil_id`      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `image_uri`      TEXT                              NOT NULL,
    `latin_name`     TEXT,
    `geological_era` TEXT,
    `origin_key`     INTEGER                           NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_fossil_origin_key` ON `fossil` (`origin_key`);

CREATE TABLE IF NOT EXISTS `collected_fossil`
(
    `collected_fossil_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `fossil_stats_id`     INTEGER,
    `collecting_user`     INTEGER                           NOT NULL,
    `is_favorite`         INTEGER                           NOT NULL,
    `date_time_collected` INTEGER                           NOT NULL,
    FOREIGN KEY (`fossil_stats_id`) REFERENCES `fossil` (`fossil_id`) ON UPDATE NO ACTION ON DELETE CASCADE,
    FOREIGN KEY (`collecting_user`) REFERENCES `user_profile` (`user_profile_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_collected_fossil_fossil_stats_id` ON `collected_fossil` (`fossil_stats_id`);

CREATE INDEX IF NOT EXISTS `index_collected_fossil_collecting_user` ON `collected_fossil` (`collecting_user`);

CREATE TABLE IF NOT EXISTS `user_profile`
(
    `user_profile_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `scanner_items`   INTEGER                           NOT NULL
);

CREATE TABLE IF NOT EXISTS `dig_site_grid`
(
    `dig_site_grid_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `player_id`        INTEGER                           NOT NULL,
    `height`           INTEGER                           NOT NULL,
    `width`            INTEGER                           NOT NULL,
    `start_time`       INTEGER                           NOT NULL,
    FOREIGN KEY (`player_id`) REFERENCES `user_profile` (`user_profile_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_dig_site_grid_player_id` ON `dig_site_grid` (`player_id`);

CREATE TABLE IF NOT EXISTS `dig_site_square`
(
    `dig_site_square_id`     INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `belonging_grid_id`      INTEGER                           NOT NULL,
    `x_coordinate`           INTEGER                           NOT NULL,
    `y_coordinate`           INTEGER                           NOT NULL,
    `has_fossil`             INTEGER                           NOT NULL,
    `moore_neighbor_fossils` INTEGER                           NOT NULL,
    `state`                  INTEGER                           NOT NULL,
    `last_modified`          INTEGER                           NOT NULL,
    FOREIGN KEY (`belonging_grid_id`) REFERENCES `dig_site_grid` (`dig_site_grid_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_dig_site_square_belonging_grid_id` ON `dig_site_square` (`belonging_grid_id`);

CREATE UNIQUE INDEX IF NOT EXISTS `index_dig_site_square_belonging_grid_id_x_coordinate_y_coordinate` ON `dig_site_square` (`belonging_grid_id`, `x_coordinate`, `y_coordinate`);