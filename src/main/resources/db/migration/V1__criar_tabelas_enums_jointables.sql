CREATE TABLE usuario (
                         id BIGSERIAL NOT NULL,
                         nome varchar(50) DEFAULT NULL,
                         email varchar(30) NOT NULL,
                         senha varchar(100) NOT NULL,
                         PRIMARY KEY (id)
);



CREATE TABLE livro (
                       id BIGSERIAL NOT NULL,
                       titulo VARCHAR(100) NOT NULL,
                       descricao TEXT NOT NULL,
                       autor VARCHAR(100) NOT NULL,
                       usuario_id BIGINT,
                       imagem_url VARCHAR(255) NOT NULL,
                       PRIMARY KEY (id),
                       FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);
CREATE TABLE usuario_id_livro_id (
                                     usuario_id BIGINT NOT NULL,
                                     livro_id BIGINT NOT NULL,
                                     PRIMARY KEY (usuario_id, livro_id),
                                     FOREIGN KEY (usuario_id) REFERENCES usuario(id),
                                     FOREIGN KEY (livro_id) REFERENCES livro(id)
);
CREATE TABLE livro_generos (
                               livro_id BIGINT NOT NULL,
                               genero VARCHAR(50) NOT NULL,
                               FOREIGN KEY (livro_id) REFERENCES Livro(id)
);

CREATE TABLE livro_estados (
                               livro_id BIGINT NOT NULL,
                               estados VARCHAR(50) NOT NULL,
                               FOREIGN KEY (livro_id) REFERENCES Livro(id)
);

CREATE TABLE endereco (
                          id BIGSERIAL NOT NULL,
                          rua VARCHAR(255) NOT NULL,
                          cidade VARCHAR(255) NOT NULL,
                          estado VARCHAR(255) NOT NULL,
                          cep VARCHAR(20) NOT NULL,
                          numero VARCHAR(20) NOT NULL,
                          complemento VARCHAR(255),
                          usuario_id BIGINT,
                          PRIMARY KEY (id),
                          FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);

CREATE TABLE usuario_id_endereco_id (
                                     usuario_id BIGINT NOT NULL,
                                     endereco_id BIGINT NOT NULL,
                                     PRIMARY KEY (usuario_id, endereco_id),
                                     FOREIGN KEY (usuario_id) REFERENCES usuario(id),
                                     FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);
