CREATE TABLE Users (
                       id BIGSERIAL NOT NULL,
                       nome varchar(50) DEFAULT NULL,
                       email varchar(30) NOT NULL,
                       senha varchar(100) NOT NULL,
                       PRIMARY KEY (id)
);