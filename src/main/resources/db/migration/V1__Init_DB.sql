create table advert (
    id bigint not null,
    active bit,
    city varchar(255) not null,
    company varchar(255) not null,
    cost bigint not null,
    date datetime,
    description varchar(2048),
    has_client bit,
    picture varchar(255),
    tittle varchar(255) not null,
    type varchar(255) not null,
    author bigint,
    primary key (id)
);

create table hibernate_sequence (next_val bigint);
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

create table user (
    id bigint not null,
    active bit,
    blocked bit,
    city varchar(255) not null,
    date datetime,
    email varchar(255) not null,
    fname varchar(255),
    name varchar(255) not null,
    password varchar(255) not null,
    phone varchar(255) not null,
    picture varchar(255),
    rating integer,
    sname varchar(255),
    username varchar(255) not null,
    primary key (id)
);

create table user_role (
    user_id bigint not null,
    roles varchar(255)
);

alter table advert
    add constraint advert_user_fk
    foreign key (author) references user (id);

alter table user_role
    add constraint user_role_user_fk
    foreign key (user_id) references user (id);




insert into user(id, username, password, active, blocked, city, email, name, phone, rating)
    values (1, 'admin', '123', true, false, 'Саратов', 'wopizoli@gmail.com', 'Илья', '+79873556990', 0);

insert into user_role(user_id, roles)
    values (1, 'USER'), (1, 'ADMIN');