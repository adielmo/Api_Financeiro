CREATE TABLE usuario(
codigo BIGINT NOT NULL AUTO_INCREMENT, 
nome VARCHAR(50) NOT NULL, 
email VARCHAR(50)NOT NULL, 
senha VARCHAR(150)NOT NULL, 

PRIMARY KEY(codigo)

)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE permissao(
codigo BIGINT NOT NULL AUTO_INCREMENT, 
descricao VARCHAR(50)NOT NULL, 

PRIMARY KEY(codigo)

)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao(
codigo_usuario BIGINT(20) NOT NULL, 
codigo_permissao BIGINT(20) NOT NULL, 

PRIMARY KEY (codigo_usuario, codigo_permissao)

)ENGINE=INNODB DEFAULT CHARSET=utf8;

ALTER TABLE usuario_permissao 
ADD CONSTRAINT fk_usuario_permissao_usuario 
FOREIGN KEY(codigo_usuario) 
REFERENCES usuario(codigo); 
 
ALTER TABLE usuario_permissao 
ADD CONSTRAINT fk_usuario_permissao_permissao 
FOREIGN KEY(codigo_permissao) 
REFERENCES permissao(codigo); 