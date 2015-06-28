--!Ups--

create table message(

  id                        integer not null,
  description               varchar(255),
  creating_date             timestamp,
  help_id                   integer,
  sender_login              varchar(255),
  receiver_login            varchar(255),
  constraint pk_message primary key (id)
);

create sequence message_seq;

alter table message add constraint fk_message_help_8 foreign key (help_id) references help (id) on delete restrict on update restrict;
create index ix_message_help_8 on message (help_id);
alter table message add constraint fk_message_sender_9 foreign key (sender_login) references user (login) on delete restrict on update restrict;
create index ix_message_sender_9 on message (sender_login);
alter table message add constraint fk_message_receiver_10 foreign key (receiver_login) references user (login) on delete restrict on update restrict;
create index ix_message_receiver_10 on message (receiver_login);

--!Downs--


SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists message;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists message_seq;