use lesProject;

CREATE TABLE tb_precificacao
(
  pre_id INT NOT NULL AUTO_INCREMENT,
  pre_percentual DECIMAL(3,2) NOT NULL,
  pre_ativo BOOLEAN NOT NULL,
  pre_dataHoraCriacao DATETIME NOT NULL,
  PRIMARY KEY (pre_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_produto
(
  pro_id INT NOT NULL AUTO_INCREMENT,
  pro_nome VARCHAR(100) NOT NULL,
  pro_preco DECIMAL(4,2) NOT NULL,
  pro_descricao VARCHAR(400) NOT NULL,
  pro_ativo BOOLEAN NOT NULL,
  pro_dataHoraCriacao DATETIME NOT NULL,
  pro_ima_id INT NOT NULL,
  pro_pre_id INT NOT NULL, 
  PRIMARY KEY (pro_id),
  FOREIGN KEY(pro_ima_id) REFERENCES tb_imagem(ima_id),
  FOREIGN KEY(pro_pre_id) REFERENCES tb_precificacao(pre_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_categoria
(
  cat_id INT NOT NULL AUTO_INCREMENT,
  cat_nome VARCHAR(100) NOT NULL,
  cat_ativo BOOLEAN NOT NULL,
  cat_dataHoraCriacao DATETIME NOT NULL,
  cat_pro_id INT NOT NULL, 
  PRIMARY KEY (cat_id),
  FOREIGN KEY(cat_pro_id) REFERENCES tb_produto(pro_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_inativacao
(
  ina_id INT NOT NULL AUTO_INCREMENT,
  ina_ativo BOOLEAN NOT NULL,
  ina_dataCriacao DATETIME NOT NULL,
  ina_descricao VARCHAR(400) NOT NULL,
  ina_statusInativacao VARCHAR(20) NOT NULL, 
  ina_pro_id INT NOT NULL,
  PRIMARY KEY (ina_id),
  FOREIGN KEY(ina_pro_id) REFERENCES tb_produto(pro_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_ativacao 
(
  ati_id INT NOT NULL AUTO_INCREMENT,
  ati_ativo BOOLEAN NOT NULL,
  ati_dataCriacao DATETIME NOT NULL,
  ati_descricao VARCHAR(400) NOT NULL,
  ati_statusAtivacao VARCHAR(20) NOT NULL, 
  ati_pro_id INT NOT NULL,
  PRIMARY KEY (ati_id),
  FOREIGN KEY(ati_pro_id) REFERENCES tb_produto(pro_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
