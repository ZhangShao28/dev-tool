drop table if exists PRO_SESSION;
drop table if exists PRO_LOGIN_LOG;
drop table if exists PRO_ACCOUNT_GRP_NEXUS;
drop table if exists PRO_USR_GRP_NEXUS;
drop table if exists PRO_GRP;
drop table if exists PRO_ACCOUNT;
drop table if exists PRO_USR;
/*==============================================================*/
/* Name:<用户> Table: PRO_USR                                   */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow -d cs PRO_USR > PRO_USR.sql
*/
drop table if exists PRO_USR;
create table PRO_USR (
  USR_ID                          varchar(36)         character set utf8 collate utf8_bin  not null comment '用户编号',
  USR_KEY                         varchar(64)         character set utf8 collate utf8_bin  not null comment '用户标识',
  USR_NAME                        varchar(64)         character set utf8 collate utf8_bin      null comment '用户名称',
  GENDER                          varchar(1)          character set utf8 collate utf8_bin      null comment '性别',
  PERMISSION_KEY                  varchar(64)         character set utf8 collate utf8_bin      null comment '权限标识',
  REGISTER_TIMES                  bigint(20)                                                   null comment '注册时间',
  DESCRIPTION                     varchar(255)        character set utf8 collate utf8_bin      null comment '描述',
  primary key (USR_ID),
  unique key (USR_KEY)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '用户';

/*==============================================================*/
/* Name:<账号> Table: PRO_ACCOUNT                               */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow -d cs PRO_ACCOUNT > PRO_ACCOUNT.sql
*/
drop table if exists PRO_ACCOUNT;
create table PRO_ACCOUNT (
  ACCOUNT_ID                      varchar(36)         character set utf8 collate utf8_bin  not null comment '账号编号',
  ACCOUNT                         varchar(64)         character set utf8 collate utf8_bin  not null comment '账号',
  PASSWORD                        varchar(64)         character set utf8 collate utf8_bin  not null comment '密码',
  NICK_NAME                       varchar(64)         character set utf8 collate utf8_bin      null comment '昵称',
  MOBILE_PHONE_NUMBER             varchar(24)         character set utf8 collate utf8_bin      null comment '手机号',
  AVATAR_URL                      varchar(2048)       character set utf8 collate utf8_bin      null comment '头像url',
  REGISTER_TIMES                  bigint(20)                                                   null comment '注册时间',
  PERMISSION_TYPE                 varchar(1)          character set utf8 collate utf8_bin      null comment '权限类型',
  PERMISSION_KEY                  varchar(64)         character set utf8 collate utf8_bin      null comment '权限标识',

  USR_ID                          varchar(36)         character set utf8 collate utf8_bin      null comment '用户编号',
  primary key (ACCOUNT_ID),
  unique key (ACCOUNT)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '账号';
alter table PRO_ACCOUNT add constraint FK_PRO_ACCOUNT_USR_ID foreign key (USR_ID) references PRO_USR(USR_ID);

/*==============================================================*/
/* Name:<组> Table: PRO_GRP                                     */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow -d cs PRO_GRP > PRO_GRP.sql
*/
drop table if exists PRO_GRP;
create table PRO_GRP (
  GRP_ID                          varchar(36)         character set utf8 collate utf8_bin  not null comment '组编号',
  GRP_KEY                         varchar(64)         character set utf8 collate utf8_bin  not null comment '组标识',
  GRP_TYPE                        varchar(24)         character set utf8 collate utf8_bin  not null comment '组类型',
  GRP_NAME                        varchar(64)         character set utf8 collate utf8_bin      null comment '组名称',
  PERMISSION_KEY                  varchar(64)         character set utf8 collate utf8_bin      null comment '权限标识',
  DESCRIPTION                     varchar(255)        character set utf8 collate utf8_bin      null comment '描述',
  primary key (GRP_ID),
  unique key (GRP_KEY)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '组';

/*==============================================================*/
/* Name:<用户分组> Table: PRO_USR_GRP_NEXUS                      */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow -d cs PRO_USR_GRP_NEXUS > PRO_USR_GRP_NEXUS.sql
*/
drop table if exists PRO_USR_GRP_NEXUS;
create table PRO_USR_GRP_NEXUS (
  NEXUS_ID                        varchar(36)         character set utf8 collate utf8_bin  not null comment '用户分组编号',
  USR_ID                          varchar(36)         character set utf8 collate utf8_bin  not null comment '用户编号',
  GRP_ID                          varchar(36)         character set utf8 collate utf8_bin  not null comment '组编号',
  primary key (NEXUS_ID),
  unique key (USR_ID, GRP_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '用户分组';
alter table PRO_USR_GRP_NEXUS add constraint FK_PRO_USR_GRP_NEXUS_USR_ID foreign key (USR_ID) references PRO_USR(USR_ID);
alter table PRO_USR_GRP_NEXUS add constraint FK_PRO_USR_GRP_NEXUS_GRP_ID foreign key (GRP_ID) references PRO_GRP(GRP_ID);

/*==============================================================*/
/* Name:<账号分组> Table: PRO_ACCOUNT_GRP_NEXUS                  */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow -d cs PRO_ACCOUNT_GRP_NEXUS > PRO_ACCOUNT_GRP_NEXUS.sql
*/
drop table if exists PRO_ACCOUNT_GRP_NEXUS;
create table PRO_ACCOUNT_GRP_NEXUS (
  NEXUS_ID                        varchar(36)         character set utf8 collate utf8_bin  not null comment '账号分组编号',
  ACCOUNT_ID                      varchar(36)         character set utf8 collate utf8_bin  not null comment '账号编号',
  GRP_ID                          varchar(36)         character set utf8 collate utf8_bin  not null comment '组编号',
  primary key (NEXUS_ID),
  unique key (ACCOUNT_ID, GRP_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '账号分组';
alter table PRO_ACCOUNT_GRP_NEXUS add constraint FK_PRO_ACC_GRP_NEXUS_ACC_ID foreign key (ACCOUNT_ID) references PRO_ACCOUNT(ACCOUNT_ID);
alter table PRO_ACCOUNT_GRP_NEXUS add constraint FK_PRO_ACC_GRP_NEXUS_GRP_ID foreign key (GRP_ID) references PRO_GRP(GRP_ID);

/*==============================================================*/
/* Name:<登录日志> Table: PRO_LOGIN_LOG                          */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow -d cs PRO_LOGIN_LOG > PRO_LOGIN_LOG.sql
*/
drop table if exists PRO_LOGIN_LOG;
create table PRO_LOGIN_LOG (
  LOG_ID                          varchar(36)         character set utf8 collate utf8_bin  not null comment '登录日志编号',
  LOGIN_KEY                       varchar(64)         character set utf8 collate utf8_bin  not null comment '登录标识',
  ACCOUNT_ID                      varchar(36)         character set utf8 collate utf8_bin  not null comment '账号编号',
  ACCOUNT                         varchar(64)         character set utf8 collate utf8_bin  not null comment '账号',
  NICK_NAME                       varchar(64)         character set utf8 collate utf8_bin      null comment '昵称',
  MOBILE_PHONE_NUMBER             varchar(24)         character set utf8 collate utf8_bin      null comment '手机号',
  LOGIN_TIMES                     bigint(20)                                               not null comment '登录时间',
  primary key (LOG_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '登录日志';

/*==============================================================*/
/* Name:<会话> Table: PRO_SESSION                               */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow -d cs PRO_SESSION > PRO_SESSION.sql
*/
drop table if exists PRO_SESSION;
create table PRO_SESSION (
  SESSION_ID                      varchar(36)         character set utf8 collate utf8_bin  not null comment '会话编号',
  ACCOUNT_ID                      varchar(36)         character set utf8 collate utf8_bin  not null comment '账号编号',
  LOGIN_KEY                       varchar(64)         character set utf8 collate utf8_bin  not null comment '登录标识',
  CURRENT_TIMES                   bigint(20)                                               not null comment '当前时间',
  primary key (SESSION_ID),
  unique key (ACCOUNT_ID, LOGIN_KEY)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '会话';
alter table PRO_SESSION add constraint FK_PRO_SESSION_ACCOUNT_ID foreign key (ACCOUNT_ID) references PRO_ACCOUNT(ACCOUNT_ID);
