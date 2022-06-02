drop table likes;
drop table films;
drop table mpa;
create table mpa
(
id int primary key ,
name char(10)
);

create table films
(
    id int auto_increment primary key ,
    name varchar(200) not null,
    description varchar(200) not null,
    release_date datetime not null,
    duration int not null,
    mpa_id int,
    foreign key(mpa_id) references mpa(id)
);

create table likes
(
    film_id int not null,
    user_id int not null,
    foreign key (film_id) references films(id),
--    todo:
--    foreign key (user_id) references users(id),
--    constraint LIKES_FILM_USER_UNIQUE unique (film_id, user_id)
    primary key  (film_id, user_id)
);
