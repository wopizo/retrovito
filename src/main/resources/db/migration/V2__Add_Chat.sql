create table message (
                        id bigint not null,
                        userFrom bigint,
                        userTo bigint,
                        message varchar(2048) not null,
                        date datetime,
                        checked bit,
                        primary key (id)
);

alter table message
    add constraint message_userFrom_fk
        foreign key (userFrom) references user (id);

alter table message
    add constraint message_userTo_fk
        foreign key (userTo) references user (id);