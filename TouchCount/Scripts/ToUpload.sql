DROP DATABASE IF EXISTS AuditDatabase;

CREATE DATABASE AuditDatabase;

USE AuditDatabase;

Create Table users (
	user_id int(11) not null auto_increment,
    user_name varchar(20) not null,
    `password` varchar(20) not null,
    enabled tinyint(1) not null,
    primary key (user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

Create table audits (
	audit_id int(11) not null auto_increment,
    loc_name varchar(60) null,
    user_id int(11) not null,
    primary key (audit_id),
    foreign key (user_id) references users(user_id)
    
)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

Create Table entries (
	entry_id int(11) not null auto_increment,
    audit_id int(11) not null,
    map_number varchar(20) not null,
    room_name varchar(40) null,
    floor_number varchar(20) not null,
    base_code varchar(30) not null,
    ext_code varchar(100) not null,
    quantity int(11) not null,
    comments varchar(100) null,
    fixture_height varchar(60)  null,
    ceiling_height varchar(60) null,
    room_type varchar(20) not null,
    primary key (entry_id), 
    foreign key (audit_id) references audits(audit_id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

insert into users (user_id, user_name, password, enabled) values (1, "admin", "admin", 1);
insert into users (user_id, user_name, password, enabled) values (2, "user", "user", 1);

