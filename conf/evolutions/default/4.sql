--!Ups--


alter table help add column is_deleted boolean default false not null;
alter table user add column is_deleted boolean default false not null;
alter table notification add column is_deleted boolean default false not null;
alter table note add column is_deleted boolean default false not null;


--!Downs--
