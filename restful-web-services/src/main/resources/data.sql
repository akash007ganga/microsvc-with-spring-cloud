insert into users (Id, name, birth_date) values (10001, 'AB', CURRENT_TIMESTAMP());
insert into users (Id, name, birth_date) values (10002, 'jill', CURRENT_TIMESTAMP());
insert into users (Id, name, birth_date) values (10003, 'jam', CURRENT_TIMESTAMP());

insert into post (Id, description, user_id) values (5001, 'My First Post', 10001);
insert into post (Id, description, user_id) values (5002, 'My Second Post', 10001);
