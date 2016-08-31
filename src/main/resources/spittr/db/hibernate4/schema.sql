DROP TABLE if EXISTS spittle;
DROP TABLE if EXISTS spitter;

CREATE TABLE spitter (
  id IDENTITY ,
  username VARCHAR(25) NOT NULL,
  password VARCHAR(25) NOT NULL,
  fullname VARCHAR (100) NOT NULL ,
  email VARCHAR (50) NOT NULL ,
  updateByEmail boolean NOT NULL
);

CREATE TABLE spittle (
  id INTEGER IDENTITY PRIMARY KEY ,
  spitter INTEGER NOT NULL ,
  message VARCHAR (2000) NOT NULL ,
  postedTime DATETIME NOT NULL ,
  FOREIGN KEY (spitter) REFERENCES spitter(id)
);