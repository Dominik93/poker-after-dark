create table player (
        id varchar(255) not null,
        name varchar(255) not null,
        number_of_plays numeric default 0,
        live_winnings float8,
        max_earnings numeric default 0,
        min_earnings numeric default 0,
        primary key (id)
    );
alter table player add constraint UK_NAME unique (name);
