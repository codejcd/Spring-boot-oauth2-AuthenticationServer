CREATE TABLE `oauth_client_details` (
	`client_seq` INT(11) NOT NULL AUTO_INCREMENT,
	`client_id` VARCHAR(256) NOT NULL,
	`resource_ids` VARCHAR(256) NULL DEFAULT NULL,
	`client_secret` VARCHAR(256) NULL DEFAULT NULL,
	`scope` VARCHAR(256) NULL DEFAULT NULL,
	`authorized_grant_types` VARCHAR(256) NULL DEFAULT NULL,
	`web_server_redirect_uri` VARCHAR(256) NULL DEFAULT NULL,
	`authorities` VARCHAR(256) NULL DEFAULT NULL,
	`access_token_validity` INT(11) NULL DEFAULT NULL,
	`refresh_token_validity` INT(11) NULL DEFAULT NULL,
	`additional_information` VARCHAR(4096) NULL DEFAULT NULL,
	`autoapprove` VARCHAR(256) NULL DEFAULT NULL,
	PRIMARY KEY (`client_seq`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2
;

CREATE TABLE `oauth_access_token` (
	`token_id` VARCHAR(256) NULL DEFAULT NULL,
	`token` BLOB NULL,
	`token_seq` INT(11) NOT NULL AUTO_INCREMENT,
	`authentication_id` VARCHAR(128) NULL DEFAULT NULL,
	`user_name` VARCHAR(256) NULL,
	`client_id` VARCHAR(256) NULL,
	`authentication` BLOB NULL,
	`refresh_token` VARCHAR(256) NULL DEFAULT NULL,
	PRIMARY KEY (`seq`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=18
;

CREATE TABLE `user` (
	`user_seq` INT(11) NOT NULL AUTO_INCREMENT,
	`user_id` VARCHAR(100) NULL DEFAULT NULL,
	`password` VARCHAR(256) NOT NULL,
	`name` VARCHAR(100) NOT NULL,
	`status` VARCHAR(100) NOT NULL DEFAULT 'A',
	`reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`user_seq`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2
;


