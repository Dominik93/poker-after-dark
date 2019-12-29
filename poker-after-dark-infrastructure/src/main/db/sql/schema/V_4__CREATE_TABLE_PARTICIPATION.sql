create table participation (
        id varchar(255) not null,
        earnings float8,
        game_id varchar(255),
        player_id varchar(255),
        primary key (id)
    );
alter table participation
       add constraint FK_PARTICIPATION_GAME
       foreign key (game_id)
       references game;
alter table participation
       add constraint FK_PARTICIPATION_PLAYER
       foreign key (player_id)
       references player;
