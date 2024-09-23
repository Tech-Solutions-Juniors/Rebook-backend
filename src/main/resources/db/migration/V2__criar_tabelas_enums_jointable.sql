CREATE TABLE transacao (
    id BIGSERIAL PRIMARY KEY,
    livro_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    status VARCHAR(255),
    data TIMESTAMP,
    CONSTRAINT fk_transacao_livro FOREIGN KEY (livro_id) REFERENCES livro(id),
    CONSTRAINT fk_transacao_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE carrinho(

    id BIGINT PRIMARY KEY,
    livro_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,

    CONSTRAINT fk_carrinho_livro FOREIGN KEY (livro_id) REFERENCES livro(id),
    CONSTRAINT fk_carrinho_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);


ALTER TABLE livro
ADD COLUMN transacao_id INT,
ADD COLUMN carrinho_id INT;

ALTER TABLE livro
ADD CONSTRAINT fk_transacao FOREIGN KEY (transacao_id) REFERENCES transacao(id),
ADD CONSTRAINT fk_carrinho FOREIGN KEY (carrinho_id) REFERENCES carrinho(id);


CREATE TABLE pagamento(

    id BIGSERIAL PRIMARY KEY,
    valor FLOAT NOT NULL,
    formaPagamento VARCHAR(255),
    transacao_id BIGINT NOT NULL,
    status VARCHAR(255),
    data TIMESTAMP,
    dataConfirmacao TIMESTAMP,

    CONSTRAINT fk_pagamento_transacao FOREIGN KEY (transacao_id) REFERENCES transacao(id)
);

CREATE TABLE troca(

    id BIGSERIAL PRIMARY KEY,
    livro1_id BIGINT NOT NULL,
    livro2_id BIGINT NOT NULL,
    status VARCHAR(255),
    data TIMESTAMP,
    CONSTRAINT fk_troca_livro1 FOREIGN KEY (livro1_id) REFERENCES livro(id),
    CONSTRAINT fk_troca_livro2 FOREIGN KEY (livro2_id) REFERENCES livro(id)

);