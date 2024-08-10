CREATE TABLE Livro (
                       id BIGSERIAL NOT NULL,
                       titulo VARCHAR(100) NOT NULL,
                       descricao TEXT NOT NULL,
                       autor VARCHAR(100) NOT NULL,
                       usuario_id BIGINT,
                       imagem_url VARCHAR(255) NOT NULL,
                       PRIMARY KEY (id),
                       FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);

CREATE TABLE Livro_Generos (
                               livro_id BIGINT NOT NULL,
                               genero VARCHAR(50) NOT NULL,
                               FOREIGN KEY (livro_id) REFERENCES Livro(id)
);

CREATE TABLE Livro_Estados (
                               livro_id BIGINT NOT NULL,
                               estados VARCHAR(50) NOT NULL,
                               FOREIGN KEY (livro_id) REFERENCES Livro(id)
);
