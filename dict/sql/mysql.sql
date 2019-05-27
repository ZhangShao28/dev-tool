drop table if exists DEV_DICT_VALUE;
drop table if exists DEV_DICT;
/*==============================================================*/
/* Name:<数据字典> Table: DEV_DICT                               */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow -d cs DEV_DICT > DEV_DICT.sql
*/
drop table if exists DEV_DICT;
create table DEV_DICT (
  DICT_ID                         varchar(36)         character set utf8 collate utf8_bin  not null comment '数据字典编号',
  DICT_KEY                        varchar(64)         character set utf8 collate utf8_bin  not null comment '数据字典标识',
  DICT_NAME                       varchar(64)         character set utf8 collate utf8_bin      null comment '数据字典名称',
  NOTES                           varchar(255)        character set utf8 collate utf8_bin      null comment '说明',
  WEIGHT_ORDER                    integer                                                      null comment '排序权重',

  VERSION                         integer                                                  not null comment '版本号',
  CREATE_ID                       varchar(36)         character set utf8 collate utf8_bin  not null comment '创建人编号',
  CREATE_ACC                      varchar(64)         character set utf8 collate utf8_bin  not null comment '创建人账号',
  CREATE_NAME                     varchar(64)         character set utf8 collate utf8_bin  not null comment '创建人名称',
  MODIFY_ID                       varchar(36)         character set utf8 collate utf8_bin      null comment '修改人编号',
  MODIFY_ACC                      varchar(64)         character set utf8 collate utf8_bin      null comment '修改人账号',
  MODIFY_NAME                     varchar(64)         character set utf8 collate utf8_bin      null comment '修改人名称',
  primary key (DICT_ID),
  unique key (DICT_KEY)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '数据字典';

/*==============================================================*/
/* Name:<数据字典值> Table: DEV_DICT_VALUE                       */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow -d cs DEV_DICT_VALUE > DEV_DICT_VALUE.sql
*/
drop table if exists DEV_DICT_VALUE;
create table DEV_DICT_VALUE (
  DICT_VALUE_ID                   varchar(36)         character set utf8 collate utf8_bin  not null comment '数据字典值编号',
  CODE                            varchar(64)         character set utf8 collate utf8_bin  not null comment '代码',
  DISPLAY_NAME                    varchar(64)         character set utf8 collate utf8_bin  not null comment '显示名称',
  WEIGHT_ORDER                    integer                                                      null comment '排序权重',
  DESCRIPTION                     varchar(255)        character set utf8 collate utf8_bin      null comment '描述',

  DICT_ID                         varchar(36)         character set utf8 collate utf8_bin  not null comment '数据字典编号',

  VERSION                         integer                                                  not null comment '版本号',
  CREATE_ID                       varchar(36)         character set utf8 collate utf8_bin  not null comment '创建人编号',
  CREATE_ACC                      varchar(64)         character set utf8 collate utf8_bin  not null comment '创建人账号',
  CREATE_NAME                     varchar(64)         character set utf8 collate utf8_bin  not null comment '创建人名称',
  MODIFY_ID                       varchar(36)         character set utf8 collate utf8_bin      null comment '修改人编号',
  MODIFY_ACC                      varchar(64)         character set utf8 collate utf8_bin      null comment '修改人账号',
  MODIFY_NAME                     varchar(64)         character set utf8 collate utf8_bin      null comment '修改人名称',
  primary key (DICT_VALUE_ID),
  unique key (CODE, DICT_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '数据字典值';
alter table DEV_DICT_VALUE add constraint FK_DEV_DICT_VALUE_DICT_ID foreign key (DICT_ID) references DEV_DICT(DICT_ID);
