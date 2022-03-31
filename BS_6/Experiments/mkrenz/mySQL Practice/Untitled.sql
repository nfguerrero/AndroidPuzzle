Use mkrenzel;

create table users(
   userid INT NOT NULL AUTO_INCREMENT,
   username VARCHAR(40) NOT NULL UNIQUE,
   pass VARCHAR(40) NOT NULL,
   firstName VARCHAR(40) NOT NULL,
   lastName VARCHAR(40) NOT NULL,
   PRIMARY KEY (userid)
);
