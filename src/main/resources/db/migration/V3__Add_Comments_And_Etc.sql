create table comment(
                         id bigint not null,
                         user_from bigint,
                         advert bigint,
                         message varchar(2048) not null,
                         date datetime,
                         edited bit,
                         primary key (id)
);

alter table comment
    add constraint comment_userFrom_fk
        foreign key (user_from) references user (id);

alter table comment
    add constraint comment_advert_fk
        foreign key (advert) references advert (id);


create table review(
                        id bigint not null,
                        user_from bigint,
                        user_to bigint,
                        message varchar(2048) not null,
                        date datetime,
                        mark bit,
                        edited bit,
                        primary key (id)
);

alter table review
    add constraint review_userFrom_fk
        foreign key (user_from) references user (id);

alter table review
    add constraint review_userTo_fk
        foreign key (user_to) references user (id);


create table deal(
                       id bigint not null,
                       advert bigint,
                       date datetime,
                       primary key (id)
);

alter table deal
    add constraint deal_advert_fk
        foreign key (advert) references advert (id);