 create table game (
        id varchar(255) not null,
        date date,
        pot float8,
        player_id varchar(255),
        primary key (id)
    );
alter table game
       add constraint FK_GAME_PLAYER
       foreign key (player_id)
       references player;