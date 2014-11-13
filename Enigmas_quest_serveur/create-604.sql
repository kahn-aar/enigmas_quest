drop table video;
drop table photo;
drop table questionReponse;
drop table combat;
drop table question;
drop table player;
drop table positions;


create table positions(
	id integer AUTO_INCREMENT,
	latitude float,
	longitude float,
	primary key (id)
);

create table player(
	login varchar(20),
	points integer DEFAULT 0,
	quetesRealisees integer DEFAULT 0,
	positionId integer,
	salt varchar(50),
	primary key (login),
	foreign key (positionId) references positions(id)
);

create table question(
	numero integer AUTO_INCREMENT,
	question varchar(500),
	reponse varchar(500),
	possible1 varchar(500),
	possible2 varchar(500),
	possible3 varchar(500),
	possible4 varchar(500),
	positionId integer,
	primary key (numero),
	foreign key (positionId) references positions(id)
);

create table combat(
	numero integer AUTO_INCREMENT,
	player1 varchar(20),
	player2 varchar(20),
	question integer,
	primary key (numero),
	foreign key (player1) references player(login),
	foreign key (player2) references player(login),
	foreign key (question) references question(numero)
);

create table questionReponse(
	login varchar(20),
	numeroQ integer,
	vraiOuFaux boolean,
	primary key (login, numeroQ),
	foreign key (login) references player(login),
	foreign key (numeroQ) references question(numero)
);

create table photo(
	numero integer AUTO_INCREMENT,
	positionId integer,
	theme varchar(50),
	url varchar(200),
	primary key (numero),
	foreign key (positionId) references positions(id)
);

create table video(
	numero integer AUTO_INCREMENT,
	positionId integer,
	theme varchar(50),
	url varchar(200),
	primary key (numero),
	foreign key (positionId) references positions(id)
);


--INSERT FIXTURE
insert into player (login) values ('bob');
