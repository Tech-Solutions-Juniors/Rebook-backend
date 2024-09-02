CREATE TABLE login (
                       id BIGSERIAL NOT NULL,
                       email VARCHAR(50) NOT NULL UNIQUE,
                       senha VARCHAR(100) NOT NULL,
                       PRIMARY KEY (id)
);


CREATE TABLE usuario (
                         id BIGSERIAL NOT NULL,
                         login_id BIGINT NOT NULL,
                         nome VARCHAR(50) NOT NULL,
                         PRIMARY KEY (id),
                         FOREIGN KEY (login_id) REFERENCES login(id)
);





CREATE TABLE livro (
                       id BIGSERIAL NOT NULL,
                       titulo VARCHAR(100) NOT NULL,
                       descricao TEXT NOT NULL,
                       autor VARCHAR(100) NOT NULL,
                       usuario_id BIGINT NOT NULL,
                       imagem_url VARCHAR(255) NOT NULL DEFAULT 'url_placeholder',
                       preco DECIMAL(19,2) DEFAULT NULL,
                       PRIMARY KEY (id),
                       FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE livro_imagens (
                               livro_id BIGINT NOT NULL,
                               imagem_url VARCHAR(255),
                               FOREIGN KEY (livro_id) REFERENCES livro(id)
);

CREATE TABLE livro_generos (
                               livro_id BIGINT NOT NULL,
                               genero VARCHAR(50) NOT NULL,
                               FOREIGN KEY (livro_id) REFERENCES livro(id)
);


CREATE TABLE livro_estados (
                               livro_id BIGINT NOT NULL,
                               estados VARCHAR(50) NOT NULL,
                               FOREIGN KEY (livro_id) REFERENCES livro(id)
);


CREATE TABLE endereco (
                          id BIGSERIAL NOT NULL,
                          rua VARCHAR(255) NOT NULL,
                          cidade VARCHAR(255) NOT NULL,
                          estado VARCHAR(255) NOT NULL,
                          cep VARCHAR(20) NOT NULL,
                          numero VARCHAR(20) NOT NULL,
                          complemento VARCHAR(255),
                          usuario_id BIGINT NOT NULL,
                          PRIMARY KEY (id),
                          FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);


