create table message (
                        id bigint not null,
                        user_from bigint,
                        user_to bigint,
                        message varchar(2048) not null,
                        date datetime,
                        checked bit,
                        primary key (id)
);

alter table message
    add constraint message_userFrom_fk
        foreign key (user_from) references user (id);

alter table message
    add constraint message_userTo_fk
        foreign key (user_to) references user (id);

