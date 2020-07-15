create table PLACE (
        id varchar(255) not null,
        place numeric,
        game_id varchar(255),
        player_id varchar(255),
        primary key (id)
    );

alter table PLACE
       add constraint FK_PLACE_GAME
       foreign key (game_id)
       references game;
alter table PLACE
       add constraint FK_PLACE_PLAYER
       foreign key (player_id)
       references player;
