--- Created by Ebean DDL
--- To stop Ebean DDL generation, remove this comment and start using Evolutions

--- !Ups

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists notification;

drop table if exists category;

drop table if exists help;

drop table if exists note;

drop table if exists user;

drop table if exists user_category;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists notification_seq;

drop sequence if exists category_seq;

drop sequence if exists help_seq;

drop sequence if exists note_seq;

drop sequence if exists user_seq;


create table notification (
  id                        integer not null DEFAULT 1,
  title                     varchar(40),
  text                      varchar(255),
  creating_date             timestamp,
  constraint pk_notification primary key (id))
;

create table category (
  id                        integer not null DEFAULT 1,
  name                      varchar(255),
  parent_id                 integer,
  constraint pk_category primary key (id))
;

create table help (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  latitude                  bigint,
  longitude                 bigint,
  creating_date             timestamp,
  closing_date              timestamp,
  category_id               integer,
  owner_login               varchar(255),
  helpie_login              varchar(255),
  done                      boolean,
  constraint pk_help primary key (id))
;

create table note (
  id                        integer not null,
  rank                      integer,
  description               varchar(255),
  date                      timestamp,
  help_id                   integer,
  owner_login               varchar(255),
  helpie_login              varchar(255),
  constraint pk_note primary key (id))
;

create table user (
  login                     varchar(255) not null,
  hash_pass                 varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  description               varchar(255),
  latitude                  bigint,
  longitude                 bigint,
  area                      integer,
  plan                      integer,
  count_of_jobs_per_month   integer,
  confirmation_token        varchar(255),
  validated                 boolean,
  constraint pk_user primary key (login))
;


create table user_category (
  user_login                     varchar(255) not null,
  category_id                    integer not null,
  constraint pk_user_category primary key (user_login, category_id))
;

create sequence notification_seq;

create sequence category_seq;

create sequence help_seq;

create sequence note_seq;

create sequence user_seq;

alter table category add constraint fk_category_parent_1 foreign key (parent_id) references category (id) on delete restrict on update restrict;
create index ix_category_parent_1 on category (parent_id);
alter table help add constraint fk_help_category_2 foreign key (category_id) references category (id) on delete restrict on update restrict;
create index ix_help_category_2 on help (category_id);
alter table help add constraint fk_help_owner_3 foreign key (owner_login) references user (login) on delete restrict on update restrict;
create index ix_help_owner_3 on help (owner_login);
alter table help add constraint fk_help_helpie_4 foreign key (helpie_login) references user (login) on delete restrict on update restrict;
create index ix_help_helpie_4 on help (helpie_login);
alter table note add constraint fk_note_help_5 foreign key (help_id) references help (id) on delete restrict on update restrict;
create index ix_note_help_5 on note (help_id);
alter table note add constraint fk_note_owner_6 foreign key (owner_login) references user (login) on delete restrict on update restrict;
create index ix_note_owner_6 on note (owner_login);
alter table note add constraint fk_note_helpie_7 foreign key (helpie_login) references user (login) on delete restrict on update restrict;
create index ix_note_helpie_7 on note (helpie_login);



alter table user_category add constraint fk_user_category_user_01 foreign key (user_login) references user (login) on delete restrict on update restrict;

alter table user_category add constraint fk_user_category_category_02 foreign key (category_id) references category (id) on delete restrict on update restrict;
