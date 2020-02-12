-- PLAYERS
INSERT INTO player(
	id, name, number_of_plays, live_winnings)
	VALUES ('PLAYER_1', 'PLAYER_NAME_1', 5, 0);

INSERT INTO player(
	id, name, number_of_plays, live_winnings)
	VALUES ('PLAYER_2', 'PLAYER_NAME_2', 4, 30);

INSERT INTO player(
	id, name, number_of_plays, live_winnings)
	VALUES ('PLAYER_3', 'PLAYER_NAME_3', 5, -110);

INSERT INTO player(
	id, name, number_of_plays, live_winnings)
	VALUES ('PLAYER_4', 'PLAYER_NAME_4', 0, 0);

INSERT INTO player(
	id, name, number_of_plays, live_winnings)
	VALUES ('PLAYER_5', 'PLAYER_NAME_5', 2, 80);

-- GAMES
INSERT INTO public.game(
	id, date, pot, player_id)
	VALUES ('GAME_1', '2018-02-01', 60, 'PLAYER_1');

INSERT INTO public.game(
	id, date, pot, player_id)
	VALUES ('GAME_2', '2018-08-01', 30, 'PLAYER_2');
	
INSERT INTO public.game(
	id, date, pot, player_id)
	VALUES ('GAME_3', '2018-09-22', 100, 'PLAYER_1');
	
INSERT INTO public.game(
	id, date, pot, player_id)
	VALUES ('GAME_4', '2019-01-01', 120, 'PLAYER_3');
	
INSERT INTO public.game(
	id, date, pot, player_id)
	VALUES ('GAME_5', '2019-08-30', 60, 'PLAYER_1');


-- GAME_1
INSERT INTO public.participation(
  	id, earnings, game_id, player_id)
   	VALUES ('PLAYER_1_GAME_1', 20, 'GAME_1', 'PLAYER_1');

INSERT INTO public.participation(
  	id, earnings, game_id, player_id)
   	VALUES ('PLAYER_2_GAME_1', 10, 'GAME_1', 'PLAYER_2');

INSERT INTO public.participation(
  	id, earnings, game_id, player_id)
   	VALUES ('PLAYER_3_GAME_1', -30, 'GAME_1', 'PLAYER_3');

-- GAME_2
INSERT INTO public.participation(
  	id, earnings, game_id, player_id)
   	VALUES ('PLAYER_1_GAME_2', 0, 'GAME_2', 'PLAYER_1');

INSERT INTO public.participation(
  	id, earnings, game_id, player_id)
   	VALUES ('PLAYER_2_GAME_2', -10, 'GAME_2', 'PLAYER_2');

INSERT INTO public.participation(
  	id, earnings, game_id, player_id)
   	VALUES ('PLAYER_3_GAME_2', 10, 'GAME_2', 'PLAYER_3');


-- GAME_3
INSERT INTO public.participation(
  	id, earnings, game_id, player_id)
   	VALUES ('PLAYER_1_GAME_3', -10, 'GAME_3', 'PLAYER_1');

INSERT INTO public.participation(
  	id, earnings, game_id, player_id)
   	VALUES ('PLAYER_2_GAME_3', 20, 'GAME_3', 'PLAYER_2');

INSERT INTO public.participation(
  	id, earnings, game_id, player_id)
   	VALUES ('PLAYER_3_GAME_3', -10, 'GAME_3', 'PLAYER_3');

-- GAME_4
INSERT INTO public.participation(
  	id, earnings, game_id, player_id)
   	VALUES ('PLAYER_1_GAME_4', 0, 'GAME_4', 'PLAYER_1');

INSERT INTO public.participation(
  	id, earnings, game_id, player_id)
   	VALUES ('PLAYER_3_GAME_4', -60, 'GAME_4', 'PLAYER_3');

INSERT INTO public.participation(
  	id, earnings, game_id, player_id)
   	VALUES ('PLAYER_5_GAME_4', 60, 'GAME_4', 'PLAYER_5');

-- GAME_5
INSERT INTO public.participation(
  	id, earnings, game_id, player_id)
   	VALUES ('PLAYER_1_GAME_5', -10, 'GAME_5', 'PLAYER_1');

INSERT INTO public.participation(
  	id, earnings, game_id, player_id)
   	VALUES ('PLAYER_2_GAME_5', 10, 'GAME_5', 'PLAYER_2');

INSERT INTO public.participation(
  	id, earnings, game_id, player_id)
   	VALUES ('PLAYER_3_GAME_5', -20, 'GAME_5', 'PLAYER_3');

INSERT INTO public.participation(
  	id, earnings, game_id, player_id)
   	VALUES ('PLAYER_5_GAME_5', 20, 'GAME_5', 'PLAYER_5');

-- GAME_1
INSERT INTO public.profit(
	id, lp, date, profit, player_id, game_id)
	VALUES ('GAME_1_PLAYER_1', 1, '2018-02-01', 20, 'PLAYER_1', 'GAME_1');

INSERT INTO public.profit(
	id, lp, date, profit, player_id, game_id)
	VALUES ('GAME_1_PLAYER_2', 1, '2018-02-01', 10, 'PLAYER_2', 'GAME_1');

INSERT INTO public.profit(
	id, lp, date, profit, player_id, game_id)
	VALUES ('GAME_1_PLAYER_3', 1, '2018-02-01', -30, 'PLAYER_3', 'GAME_1');

-- GAME_2
INSERT INTO public.profit(
	id, lp, date, profit, player_id, game_id)
	VALUES ('GAME_2_PLAYER_1', 2, '2018-08-01', 20, 'PLAYER_1', 'GAME_2');

INSERT INTO public.profit(
	id, lp, date, profit, player_id, game_id)
	VALUES ('GAME_2_PLAYER_2', 2, '2018-08-01', 0, 'PLAYER_2', 'GAME_2');

INSERT INTO public.profit(
	id, lp, date, profit, player_id, game_id)
	VALUES ('GAME_2_PLAYER_3', 2, '2018-08-01', -20, 'PLAYER_3', 'GAME_2');

-- GAME_3
INSERT INTO public.profit(
	id, lp, date, profit, player_id, game_id)
	VALUES ('GAME_3_PLAYER_1', 3, '2018-09-22', 10, 'PLAYER_1', 'GAME_3');

INSERT INTO public.profit(
	id, lp, date, profit, player_id, game_id)
	VALUES ('GAME_3_PLAYER_2', 3, '2018-09-22', 20, 'PLAYER_2', 'GAME_3');

INSERT INTO public.profit(
	id, lp, date, profit, player_id, game_id)
	VALUES ('GAME_3_PLAYER_3', 3, '2018-09-22', -30, 'PLAYER_3', 'GAME_3');


-- GAME_4
INSERT INTO public.profit(
	id, lp, date, profit, player_id, game_id)
	VALUES ('GAME_4_PLAYER_1', 4, '2019-01-01', 10, 'PLAYER_1', 'GAME_4');

INSERT INTO public.profit(
	id, lp, date, profit, player_id, game_id)
	VALUES ('GAME_4_PLAYER_3', 4, '2019-01-01', -90, 'PLAYER_3', 'GAME_4');

INSERT INTO public.profit(
	id, lp, date, profit, player_id, game_id)
	VALUES ('GAME_4_PLAYER_5', 4, '2019-01-01', 60, 'PLAYER_5', 'GAME_4');


-- GAME_5
INSERT INTO public.profit(
	id, lp, date, profit, player_id, game_id)
	VALUES ('GAME_5_PLAYER_1', 5, '2019-08-30', 0, 'PLAYER_1', 'GAME_5');

INSERT INTO public.profit(
	id, lp, date, profit, player_id, game_id)
	VALUES ('GAME_5_PLAYER_2', 5, '2019-08-30', 30, 'PLAYER_2', 'GAME_5');

INSERT INTO public.profit(
	id, lp, date, profit, player_id, game_id)
	VALUES ('GAME_5_PLAYER_3', 5, '2019-08-30', -110, 'PLAYER_3', 'GAME_5');

INSERT INTO public.profit(
	id, lp, date, profit, player_id, game_id)
	VALUES ('GAME_5_PLAYER_5', 5, '2019-08-30', 80, 'PLAYER_5', 'GAME_5');
