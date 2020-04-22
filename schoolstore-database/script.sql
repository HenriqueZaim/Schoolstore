CREATE DATABASE lesProject;

use lesProject;

CREATE TABLE tb_estado
(
 est_id INT NOT NULL AUTO_INCREMENT,
 est_nome VARCHAR(100) NOT NULL,
 est_sigla CHAR(2) NOT NULL,
 est_ativo BOOLEAN NOT NULL,
 est_dataHoraCriacao DATETIME NOT NULL,
 PRIMARY KEY (est_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_cidade
(
 cid_id INT NOT NULL AUTO_INCREMENT,
 cid_nome VARCHAR(100) NOT NULL,
 cid_ativo BOOLEAN NOT NULL,
 cid_dataHoraCriacao DATETIME NOT NULL,
 cid_est_id INT NOT NULL,
 PRIMARY KEY (cid_id),
 FOREIGN KEY(cid_est_id) REFERENCES tb_estado(est_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_imagem
( 
 ima_id INT NOT NULL AUTO_INCREMENT,
 ima_ativo BOOLEAN NOT NULL,
 ima_dataHoraCriacao DATETIME NOT NULL,
 ima_nome VARCHAR(100) NOT NULL,
 ima_descricao VARCHAR(100) NOT NULL,
 PRIMARY KEY (ima_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_usuario
( 
 usu_id INT NOT NULL AUTO_INCREMENT,
 usu_ativo BOOLEAN NOT NULL,
 usu_dataHoraCriacao DATETIME NOT NULL,
 usu_email VARCHAR(100) NOT NULL,
 usu_senha VARCHAR(100) NOT NULL,
 usu_ima_id INT NOT NULL,
 PRIMARY KEY (usu_id),
 FOREIGN KEY(usu_ima_id) REFERENCES tb_imagem(ima_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_cliente
( 
 cli_id INT NOT NULL AUTO_INCREMENT,
 cli_nome VARCHAR(100) NOT NULL,
 cli_numeroTelefone VARCHAR(20) NOT NULL,
 cli_numeroDocumento VARCHAR(25) NOT NULL,
 cli_ativo BOOLEAN NOT NULL,
 cli_dataHoraCriacao DATETIME NOT NULL,
 cli_usu_id INT NOT NULL,
 cli_car_id INT DEFAULT NULL,
 cli_cre_id INT DEFAULT NULL,
 PRIMARY KEY (cli_id),
 FOREIGN KEY(cli_usu_id) REFERENCES tb_usuario(usu_id),
 FOREIGN KEY(cli_car_id) REFERENCES tb_carrinho(car_id),
 FOREIGN KEY(cli_ccr_id) REFERENCES tb_cartaoCredito(ccr_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_endereco
( 
 end_id INT NOT NULL AUTO_INCREMENT,
 end_nome VARCHAR(100) NOT NULL,
 end_logradouro VARCHAR(100) NOT NULL,
 end_bairro VARCHAR(100) NOT NULL,
 end_cep VARCHAR(10) NOT NULL,
 end_numero INT(4) NOT NULL,
 end_complemento VARCHAR(100),
 end_referencia VARCHAR(100),
 end_favorito BOOLEAN NOT NULL,
 end_ativo BOOLEAN NOT NULL,
 end_dataHoraCriacao DATETIME NOT NULL,
 end_cid_id INT NOT NULL,
 end_cli_id INT NOT NULL,
 PRIMARY KEY (end_id),
 FOREIGN KEY(end_cid_id) REFERENCES tb_cidade(cid_id),
 FOREIGN KEY(end_cli_id) REFERENCES tb_cliente(cli_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_carrinho
(
  car_id INT NOT NULL AUTO_INCREMENT,
  car_subTotal DECIMAL(4,2) NOT NULL,
  car_validade DATETIME NOT NULL,
  car_ativo BOOLEAN NOT NULL,
  car_dataHoraCriacao DATETIME NOT NULL,
  PRIMARY KEY (car_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_itemCarrinho
(
  icar_id INT NOT NULL AUTO_INCREMENT,
  icar_quantidade INT NOT NULL,
  icar_car_id INT NOT NULL,
  icar_pro_id INT NOT NULL,
  PRIMARY KEY (icar_id),
  FOREIGN KEY(icar_pro_id) REFERENCES tb_produto(pro_id),
  FOREIGN KEY(icar_car_id) REFERENCES tb_carrinho(car_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;




