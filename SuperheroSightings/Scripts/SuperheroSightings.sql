create database SuperheroSightings_Test;

use SuperheroSightings_Test;

create table if not exists `locations`(
	`loc_id` int(11) not null auto_increment, 
    `loc_name` varchar(33) not null, 
    `loc_description` varchar(80) not null,
    `street` varchar(20) not null, 
    `city` varchar(20) not null, 
    `state` varchar(20) not null, 
    `zip` varchar(20) not null, 
    `latitude` varchar(10) not null, 
    `longitude` varchar(10) not null, 
    primary key (`loc_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


create table if not exists `heros` (
	`hero_id` int(11) not null auto_increment,
    `hero_name` varchar(40) not null, 
    `description` varchar(80) not null, 
    `super_power` varchar(33) not null, 
    primary key (`hero_id`)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

create table if not exists `organizations` (
	`org_id` int(11) not null auto_increment, 
    `org_name` varchar(40) not null, 
    `org_description` varchar(40) not null,
    `street` varchar(20) not null, 
    `city` varchar(20) not null, 
    `state` varchar(20) not null, 
    `zip` varchar(20) not null, 
    `president` varchar(33) not null, 
    `phone` varchar(20) not null,
    primary key (`org_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

create table if not exists `hero_organization_connections` (
	`hero_id` int(11) not null, 
    `org_id` int(11) not null, 
    key `hero_id` (`hero_id`), 
    key `org_id` (`org_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

create table if not exists `sightings` (
	`sighting_id` int(11) not null auto_increment, 
    `hero_id` int(11) not null, 
    `loc_id` int(11) not null,
    `sighting_date` datetime not null,
    primary key (`sighting_id`), 
    foreign key (`hero_id`) references `heros`(`hero_id`), 
    foreign key (`loc_id`) references `locations`(`loc_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

alter table `hero_organization_connections`
 add constraint `hero_organization_connections_ibfk_1` foreign key (`hero_id`) references `heros`
(`hero_id`) on delete no action,
 add constraint `hero_organization_connections_ibfk_2` foreign key (`org_id`) references `organizations`
(`org_id`) on delete no action;

CREATE TABLE `pictures` (
  `picture_id` int(11) NOT NULL AUTO_INCREMENT,
  `hero_id` int(11) null, 
  `title` varchar(45) not null,
  `file_name` varchar(68) not null,
  PRIMARY KEY (`picture_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1