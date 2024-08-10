CREATE TABLE ENDERECO (
                          id BIGSERIAL NOT NULL,
                          rua VARCHAR(255) NOT NULL,
                          cidade VARCHAR(255) NOT NULL,
                          estado VARCHAR(255) NOT NULL,
                          cep VARCHAR(20) NOT NULL,
                          numero VARCHAR(20) NOT NULL,
                          complemento VARCHAR(255)
);