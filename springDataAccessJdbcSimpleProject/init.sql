DROP TABLE IF EXISTS singer;
DROP TABLE IF EXISTS album;

CREATE TABLE singer
(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(250),
    last_name VARCHAR(250),
    birth_date DATE   
);

CREATE TABLE album
(
    id SERIAL PRIMARY KEY,
    singer_id INTEGER REFERENCES singer (id),
    title VARCHAR(250),
    release_date DATE
);

INSERT INTO singer(id ,first_name, last_name, birth_date) 
    VALUES(10 ,'Michael', 'Jackson', '1958-08-29');
INSERT INTO album(singer_id, title, release_date)
    VALUES(10, 'Thriller', '1982-11-30');