CREATE TABLE lancamento(

codigo BIGINT NOT NULL AUTO_INCREMENT,
descricao VARCHAR(60) NOT NULL,
data_vencimento DATE NOT NULL,
data_pagamento DATE,
valor DECIMAL(10, 2) NOT NULL,
observacao VARCHAR(100),
tipo VARCHAR(30) NOT NULL,
codigo_categoria BIGINT NOT NULL,
codigo_pessoa BIGINT NOT NULL,

PRIMARY KEY (codigo)

)ENGINE=INNODB DEFAULT CHARSET=utf8;


ALTER TABLE lancamento
ADD CONSTRAINT fk_lancamento_categoria
FOREIGN KEY(codigo_categoria)
REFERENCES categoria(codigo);

ALTER TABLE lancamento
ADD CONSTRAINT fk_lancamento_pessoa
FOREIGN KEY(codigo_pessoa)
REFERENCES pessoa(codigo);