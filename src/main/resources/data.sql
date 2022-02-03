INSERT INTO client(id,email,name,password,country,city)
VALUES ('1','gigi@example.com','gigi','$2a$10$ETXuYi449ENB3SPvgAY/O.jgi8mjyIgbUC0kT4XdDrQVPimxPaVEe','Romania','Bucharest');

INSERT INTO sensor(id,client_id)
VALUES ('1','1')