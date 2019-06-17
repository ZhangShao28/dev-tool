drop table if exists DEV_SESSION;
drop table if exists DEV_LOGIN_LOG;
drop table if exists DEV_ACCOUNT_GRP_NEXUS;
drop table if exists DEV_GRP;
drop table if exists DEV_ACCOUNT;
/*==============================================================*/
/* Name:<账号> Table: DEV_ACCOUNT                               */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow -d cs DEV_ACCOUNT > DEV_ACCOUNT.sql
*/
drop table if exists DEV_ACCOUNT;
create table DEV_ACCOUNT (
  ACCOUNT_ID                      varchar(36)         character set utf8 collate utf8_bin  not null comment '账号编号',
  ACCOUNT                         varchar(64)         character set utf8 collate utf8_bin  not null comment '账号',
  PASSWORD                        varchar(64)         character set utf8 collate utf8_bin  not null comment '密码',
  MOBILE_PHONE_NUMBER             varchar(24)         character set utf8 collate utf8_bin      null comment '手机号',
  NEXUS_TYPE                      varchar(32)         character set utf8 collate utf8_bin      null comment '关联类型',
  NEXUS_OBJECT_ID                 varchar(36)         character set utf8 collate utf8_bin      null comment '对象编号',

  VERSIONS                        integer                                                  not null comment '版本号',
  CREATE_ID                       varchar(36)         character set utf8 collate utf8_bin  not null comment '创建编号',
  CREATE_TIMES                    bigint                                                   not null comment '创建时间',
  MODIFY_ID                       varchar(36)         character set utf8 collate utf8_bin      null comment '修改编号',
  MODIFY_TIMES                    bigint                                                       null comment '修改时间',
  primary key (ACCOUNT_ID),
  unique key (ACCOUNT)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '账号';

/*==============================================================*/
/* Name:<组> Table: DEV_GRP                                     */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow -d cs DEV_GRP > DEV_GRP.sql
*/
drop table if exists DEV_GRP;
create table DEV_GRP (
  GRP_ID                          varchar(36)         character set utf8 collate utf8_bin  not null comment '组编号',
  GRP_KEY                         varchar(64)         character set utf8 collate utf8_bin  not null comment '组标识',
  GRP_NAME                        varchar(64)         character set utf8 collate utf8_bin      null comment '组名称',
  NOTES                           varchar(255)        character set utf8 collate utf8_bin      null comment '说明',

  VERSION                         integer                                                  not null comment '版本号',
  CREATE_ID                       varchar(36)         character set utf8 collate utf8_bin  not null comment '创建人编号',
  CREATE_ACC                      varchar(64)         character set utf8 collate utf8_bin  not null comment '创建人账号',
  CREATE_NAME                     varchar(64)         character set utf8 collate utf8_bin  not null comment '创建人名称',
  MODIFY_ID                       varchar(36)         character set utf8 collate utf8_bin      null comment '修改人编号',
  MODIFY_ACC                      varchar(64)         character set utf8 collate utf8_bin      null comment '修改人账号',
  MODIFY_NAME                     varchar(64)         character set utf8 collate utf8_bin      null comment '修改人名称',
  primary key (GRP_ID),
  unique key (GRP_KEY)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '组';

/*==============================================================*/
/* Name:<账号分组> Table: DEV_ACCOUNT_GRP_NEXUS                  */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow -d cs DEV_ACCOUNT_GRP_NEXUS > DEV_ACCOUNT_GRP_NEXUS.sql
*/
drop table if exists DEV_ACCOUNT_GRP_NEXUS;
create table DEV_ACCOUNT_GRP_NEXUS (
  NEXUS_ID                        varchar(36)         character set utf8 collate utf8_bin  not null comment '账号分组编号',
  ACCOUNT_ID                      varchar(36)         character set utf8 collate utf8_bin  not null comment '账号编号',
  GRP_ID                          varchar(36)         character set utf8 collate utf8_bin  not null comment '组编号',
  primary key (NEXUS_ID),
  unique key (ACCOUNT_ID, GRP_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '账号分组';
alter table DEV_ACCOUNT_GRP_NEXUS add constraint FK_DEV_ACC_GRP_NEXUS_ACC_ID foreign key (ACCOUNT_ID) references DEV_ACCOUNT(ACCOUNT_ID);
alter table DEV_ACCOUNT_GRP_NEXUS add constraint FK_DEV_ACC_GRP_NEXUS_GRP_ID foreign key (GRP_ID) references DEV_GRP(GRP_ID);

/*==============================================================*/
/* Name:<登录日志> Table: DEV_LOGIN_LOG                          */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow -d cs DEV_LOGIN_LOG > DEV_LOGIN_LOG.sql
*/
drop table if exists DEV_LOGIN_LOG;
create table DEV_LOGIN_LOG (
  LOG_ID                          varchar(36)         character set utf8 collate utf8_bin  not null comment '登录日志编号',
  LOGIN_KEY                       varchar(64)         character set utf8 collate utf8_bin  not null comment '登录标识',
  ACCOUNT_ID                      varchar(36)         character set utf8 collate utf8_bin  not null comment '账号编号',
  ACCOUNT                         varchar(64)         character set utf8 collate utf8_bin  not null comment '账号',
  MOBILE_PHONE_NUMBER             varchar(24)         character set utf8 collate utf8_bin      null comment '手机号',
  LOGIN_TIMES                     bigint(20)                                               not null comment '登录时间',
  primary key (LOG_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '登录日志';

/*==============================================================*/
/* Name:<会话> Table: DEV_SESSION                               */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow -d cs DEV_SESSION > DEV_SESSION.sql
*/
drop table if exists DEV_SESSION;
create table DEV_SESSION (
  SESSION_ID                      varchar(36)         character set utf8 collate utf8_bin  not null comment '会话编号',
  ACCOUNT_ID                      varchar(36)         character set utf8 collate utf8_bin  not null comment '账号编号',
  LOGIN_KEY                       varchar(64)         character set utf8 collate utf8_bin  not null comment '登录标识',
  CURRENT_TIMES                   bigint(20)                                               not null comment '当前时间',
  AES_KEY                         varchar(255)        character set utf8 collate utf8_bin  not null comment 'AES密钥',
  primary key (SESSION_ID),
  unique key (ACCOUNT_ID, LOGIN_KEY)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '会话';
alter table DEV_SESSION add constraint FK_DEV_SESSION_ACCOUNT_ID foreign key (ACCOUNT_ID) references DEV_ACCOUNT(ACCOUNT_ID);
