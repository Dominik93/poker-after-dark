create table profit (
        id varchar(255) not null,
        lp numeric,
        date date,
        profit float8,
        player_id varchar(255),
        game_id varchar(255),
        primary key (id)
    );
alter table profit
      add constraint FK_PROFIT_PLAYER
      foreign key (player_id)
      references player;
alter table profit
      add constraint FK_PROFIT_GAME
      foreign key (game_id)
      references game;