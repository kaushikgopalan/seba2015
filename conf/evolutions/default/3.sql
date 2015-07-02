--!Ups--
SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists notification;

SET REFERENTIAL_INTEGRITY TRUE;

create table notification(

  id                        integer not null,
  title                     varchar(255),
  text                      varchar(1000),
  creating_date             timestamp,
  help_id                   integer,
  sender_login              varchar(255),
  receiver_login            varchar(255),
  constraint pk_message primary key (id)
);

create sequence notification_seq;

alter table notification add constraint fk_notification_help_8 foreign key (help_id) references help (id) on delete restrict on update restrict;
create index ix_notification_help_8 on notification (help_id);
alter table notification add constraint fk_notification_sender_9 foreign key (sender_login) references user (login) on delete restrict on update restrict;
create index ix_notification_sender_9 on notification (sender_login);
alter table notification add constraint fk_notification_receiver_10 foreign key (receiver_login) references user (login) on delete restrict on update restrict;
create index ix_notification_receiver_10 on notification (receiver_login);

--!Downs--


--SET REFERENTIAL_INTEGRITY FALSE;

--drop table if exists notification;

--SET REFERENTIAL_INTEGRITY TRUE;

--drop sequence if exists notification_seq;