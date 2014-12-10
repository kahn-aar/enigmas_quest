
insert into positions (latitude, longitude) values (2,2);
insert into positions (latitude, longitude) values (5,5);
insert into positions (latitude, longitude) values (3,2);
insert into positions (latitude, longitude) values (2,7);
insert into positions (latitude, longitude) values (45,2);
insert into positions (latitude, longitude) values (2,78);

insert into player (login, salt, positionId) values ('bob', 'bob', 1);
insert into player (login, salt, positionId) values ('toto', 'toto', 2);
insert into player (login, salt, positionId) values ('pizza', 'pizza', 3);

insert into question (question, reponse, possible1, possible2, possible3, possible4, positionId) values ("En quelle année fut fondée l'UdeS ?", "1954", "1945", "1964", "1934", "1954", 1);
insert into question (question, reponse, possible1, possible2, possible3, possible4, positionId) values ("Capital du Quebec ?", "Montréal", "Quebec", "Montréal", "Sherbrooke", "Toronto", 2);

insert into combat (player1, player2, question) values ("bob", "toto", 1);

insert into questionReponse values ("bob", 1, false);

insert into photo(positionId, theme, url) values (1, "Histoire", "http://www.google.com");

insert into video(positionId, theme, url) values (3, "Culture", "http://www.yahoo.com");