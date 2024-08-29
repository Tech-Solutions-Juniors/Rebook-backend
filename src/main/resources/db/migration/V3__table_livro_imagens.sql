CREATE TABLE livro_imagens (
                               livro_id BIGINT NOT NULL,
                               imagem_url VARCHAR(255),
                               FOREIGN KEY (livro_id) REFERENCES livro(id)
);


