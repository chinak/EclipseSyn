--数据库初始化脚本

--创建数据库
CREATE DATABASE seckill;
--使用数据库
use seckill;
--创建库存表

CREATE TABLE seckill(
seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
name varchar(120) NOT NULL COMMENT 'NAME',
number int NOT NULL COMMENT 'NUMBER',
start_time timestamp NOT NULL COMMENT 'START',
end_time timestamp NOT NULL COMMENT 'END',
create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'CREATE_TIME',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET = utf8 COMMENT='SECKILL_TABLE'


--init
insert into seckill(name,number,start_time,end_time)
values('1000元iphone6',100,'2016-11-01 00:00:00','2016-11-02 00:00:00'),
('100元iphone5',200,'2016-11-01 00:00:00','2016-11-02 00:00:00'),
('10元iphone4',300,'2016-11-01 00:00:00','2016-11-02 00:00:00'),
('1元iphone3',400,'2016-11-01 00:00:00','2016-11-02 00:00:00');

--successer
--login in 
create table success_killes(
seckill_id bigint NOT NULL COMMENT 'ID',
user_phone bigint NOT NULL COMMENT 'PHONE',
state tinyint NOT NULL DEFAULT -1 COMMENT '-1-NULL 0-SUCC 1-PAIED 2-DILIVERED',
create_time timestamp NOT NULL COMMENT 'CREATE_TIME',
PRIMARY KEY(seckill_id,user_phone),
key idx_create_time(create_time)

)ENGINE=InnoDB  DEFAULT CHARSET = utf8 COMMENT='killer_success';

--CONN MYSQL
mysql -uroot -p123456





