insert into retrovito.user(id, username, password, active)
    values (1, 'a', '1', true);

insert into retrovito.user_role(user_id, roles)
    values (1, 'USER'), (1, 'ADMIN');