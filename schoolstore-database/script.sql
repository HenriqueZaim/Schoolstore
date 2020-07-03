CREATE DATABASE lesProject;

use lesProject;

CREATE TABLE tb_formaPagamento
(
  fpag_id INT NOT NULL AUTO_INCREMENT,
  fpag_valorTotal DECIMAL(7,3) NOT NULL,
  PRIMARY KEY (fpag_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_fornecedor
(
  for_id INT NOT NULL AUTO_INCREMENT,
  for_cnpj VARCHAR(20) NOT NULL,
  for_nome VARCHAR(100) NOT NULL,
  for_ativo BOOLEAN NOT NULL,
  for_dataHoraCriacao DATETIME NOT NULL,
  for_dataHoraAtualizacao DATETIME DEFAULT NULL,
  PRIMARY KEY (for_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_estado
(
 est_id INT NOT NULL AUTO_INCREMENT,
 est_nome VARCHAR(100) NOT NULL,
 est_sigla CHAR(2) NOT NULL,
 est_ativo BOOLEAN NOT NULL,
 est_dataHoraCriacao DATETIME NOT NULL,
 est_dataHoraAtualizacao DATETIME DEFAULT NULL,
 PRIMARY KEY (est_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_precificacao
(
  pre_id INT NOT NULL AUTO_INCREMENT,
  pre_percentual DECIMAL(3,2) NOT NULL,
  pre_ativo BOOLEAN NOT NULL,
  pre_dataHoraCriacao DATETIME NOT NULL,
  pre_dataHoraAtualizacao DATETIME DEFAULT NULL,
  PRIMARY KEY (pre_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_carrinho
(
  car_id INT NOT NULL AUTO_INCREMENT,
  car_subTotal DECIMAL(7,3) DEFAULT NULL,
  car_validade DATETIME DEFAULT NULL,
  car_ativo BOOLEAN NOT NULL,
  car_dataHoraCriacao DATETIME NOT NULL,
  car_dataHoraAtualizacao DATETIME DEFAULT NULL,
  PRIMARY KEY (car_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_imagem
( 
 ima_id INT NOT NULL AUTO_INCREMENT,
 ima_ativo BOOLEAN NOT NULL,
 ima_dataHoraCriacao DATETIME NOT NULL,
 ima_dataHoraAtualizacao DATETIME DEFAULT NULL,
 ima_nome VARCHAR(100) NOT NULL,
 ima_descricao VARCHAR(100) NOT NULL,
 ima_caminho VARCHAR(255) NOT NULL,
 PRIMARY KEY (ima_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_cidade
(
 cid_id INT NOT NULL AUTO_INCREMENT,
 cid_nome VARCHAR(100) NOT NULL,
 cid_ativo BOOLEAN NOT NULL,
 cid_dataHoraCriacao DATETIME NOT NULL,
 cid_dataHoraAtualizacao DATETIME DEFAULT NULL,
 cid_est_id INT NOT NULL,
 PRIMARY KEY (cid_id),
 FOREIGN KEY(cid_est_id) REFERENCES tb_estado(est_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_usuario
( 
 usu_id INT NOT NULL AUTO_INCREMENT,
 usu_ativo BOOLEAN NOT NULL,
 usu_dataHoraCriacao DATETIME NOT NULL,
 usu_dataHoraAtualizacao DATETIME DEFAULT NULL,
 usu_email VARCHAR(100) NOT NULL,
 usu_senha VARCHAR(100) NOT NULL,
 usu_admin BOOLEAN NOT NULL,
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
 cli_dataHoraAtualizacao DATETIME DEFAULT NULL,
 cli_usu_id INT NOT NULL,
 cli_car_id INT NOT NULL,
 PRIMARY KEY (cli_id),
 FOREIGN KEY(cli_usu_id) REFERENCES tb_usuario(usu_id),
 FOREIGN KEY(cli_car_id) REFERENCES tb_carrinho(car_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_cartaoCredito
(
  ccr_id INT NOT NULL AUTO_INCREMENT,
  ccr_ativo BOOLEAN NOT NULL,
  ccr_dataHoraCriacao DATETIME NOT NULL,
  ccr_dataHoraAtualizacao DATETIME DEFAULT NULL,
  ccr_numero VARCHAR(16) NOT NULL,
  ccr_codigo VARCHAR(3) NOT NULL,
  ccr_nomeImpresso VARCHAR(100) NOT NULL,
  ccr_favorito BOOLEAN NOT NULL,
  ccr_cli_id INT NOT NULL,
  ccr_bandeiraCartao VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (ccr_id),
  FOREIGN KEY(ccr_cli_id) REFERENCES tb_cliente(cli_id)
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
 end_dataHoraAtualizacao DATETIME DEFAULT NULL,
 end_cid_id INT NOT NULL,
 end_cli_id INT NOT NULL,
 PRIMARY KEY (end_id),
 FOREIGN KEY(end_cid_id) REFERENCES tb_cidade(cid_id),
 FOREIGN KEY(end_cli_id) REFERENCES tb_cliente(cli_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_produto
(
  pro_id INT NOT NULL AUTO_INCREMENT,
  pro_nome VARCHAR(100) NOT NULL,
  pro_preco DECIMAL(7,3) NOT NULL,
  pro_descricao VARCHAR(400) NOT NULL,
  pro_ativo BOOLEAN NOT NULL,
  pro_dataHoraCriacao DATETIME NOT NULL,
  pro_dataHoraAtualizacao DATETIME DEFAULT NULL,
  pro_ima_id INT NOT NULL,
  pro_pre_id INT NOT NULL, 
  PRIMARY KEY (pro_id),
  FOREIGN KEY(pro_ima_id) REFERENCES tb_imagem(ima_id),
  FOREIGN KEY(pro_pre_id) REFERENCES tb_precificacao(pre_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_itemCarrinho
(
  icar_id INT NOT NULL AUTO_INCREMENT,
  icar_quantidade INT unsigned NOT NULL,
  icar_car_id INT NOT NULL,
  icar_pro_id INT NOT NULL,
  PRIMARY KEY (icar_id),
  FOREIGN KEY(icar_pro_id) REFERENCES tb_produto(pro_id),
  FOREIGN KEY(icar_car_id) REFERENCES tb_carrinho(car_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_categoria
(
  cat_id INT NOT NULL AUTO_INCREMENT,
  cat_nome VARCHAR(100) NOT NULL,
  cat_ativo BOOLEAN NOT NULL,
  cat_dataHoraCriacao DATETIME NOT NULL,
  cat_dataHoraAtualizacao DATETIME DEFAULT NULL,
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

CREATE TABLE tb_cupom
(
  cup_id INT NOT NULL AUTO_INCREMENT,
  cup_valor DECIMAL(7,3) NOT NULL,
  cup_ativo BOOLEAN NOT NULL,
  cup_dataHoraCriacao DATETIME NOT NULL,
  cup_dataHoraVencimento DATETIME DEFAULT NULL,
  cup_dataHoraAtualizacao DATETIME DEFAULT NULL,
  cup_cupomPromocional BOOLEAN DEFAULT false,
  cup_usu_id INT DEFAULT NULL,
  PRIMARY KEY (cup_id),
  FOREIGN KEY(cup_usu_id) REFERENCES tb_usuario(usu_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE tb_pagamentoCupom
(
  pcu_id INT NOT NULL AUTO_INCREMENT,
  pcu_valorTotalCupom DECIMAL(7,3) NOT NULL,
  pcu_cup_id INT NOT NULL,
  pcu_fpag_id INT NOT NULL,
  FOREIGN KEY(pcu_cup_id) REFERENCES tb_cupom(cup_id),
  FOREIGN KEY(pcu_fpag_id) REFERENCES tb_formaPagamento(fpag_id),
  PRIMARY KEY (pcu_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE tb_pagamentoCartao
(
  pca_id INT NOT NULL AUTO_INCREMENT,
  pca_valorTotalCartao DECIMAL(7,3) NOT NULL,
  pca_ccr_id INT NOT NULL,
  pca_fpag_id INT NOT NULL,
  PRIMARY KEY (pca_id),
  FOREIGN KEY(pca_ccr_id) REFERENCES tb_cartaoCredito(ccr_id),
  FOREIGN KEY(pca_fpag_id) REFERENCES tb_formaPagamento(fpag_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_estoque
(
  sto_id INT NOT NULL AUTO_INCREMENT,
  sto_quantidadeTotal INT(5) unsigned DEFAULT NULL,
  sto_pro_id INT NOT NULL,
  PRIMARY KEY (sto_id),
  FOREIGN KEY(sto_pro_id) REFERENCES tb_produto(pro_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_itemEstoque
(
  isto_id INT NOT NULL AUTO_INCREMENT,
  isto_quantidade INT(5) unsigned DEFAULT NULL,
  isto_dataEntrada DATE NOT NULL,
  isto_valor DECIMAL(7,3) NOT NULL,
  isto_for_id INT NOT NULL,
  isto_sto_id INT NOT NULL,
  PRIMARY KEY (isto_id),
  FOREIGN KEY(isto_sto_id) REFERENCES tb_estoque(sto_id),
  FOREIGN KEY(isto_for_id) REFERENCES tb_fornecedor(for_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_frete
( 
  fre_id INT NOT NULL AUTO_INCREMENT,
  fre_valor DECIMAL(7,3),
  fre_previsaoEmDias INT NOT NULL,
  fre_end_id INT NOT NULL,
  fre_ativo BOOLEAN NOT NULL,
  fre_dataHoraCriacao DATETIME NOT NULL,
  fre_dataHoraAtualizacao DATETIME DEFAULT NULL,
  PRIMARY KEY (fre_id),
  FOREIGN KEY(fre_end_id) REFERENCES tb_endereco(end_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_pedido
(
  ped_id INT NOT NULL AUTO_INCREMENT,
  ped_valor DECIMAL(7,3) NOT NULL,
  ped_statusPedido VARCHAR(20) NOT NULL,
  ped_ativo BOOLEAN NOT NULL,
  ped_dataHoraCriacao DATETIME NOT NULL,
  ped_dataHoraAtualizacao DATETIME DEFAULT NULL,
  ped_fpag_id INT NOT NULL,
  ped_fre_id INT NOT NULL,
  ped_cli_id INT NOT NULL,
  PRIMARY KEY (ped_id),
  FOREIGN KEY(ped_fpag_id) REFERENCES tb_formaPagamento(fpag_id),
  FOREIGN KEY(ped_fre_id) REFERENCES tb_frete(fre_id),
  FOREIGN KEY(ped_cli_id) REFERENCES tb_cliente(cli_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_itemPedido
(
  iped_id INT NOT NULL AUTO_INCREMENT, 
  iped_quantidade INT unsigned NOT NULL,
  iped_pro_id INT NOT NULL,
  iped_ped_id INT NOT NULL,
  PRIMARY KEY (iped_id),
  FOREIGN KEY(iped_pro_id) REFERENCES tb_produto(pro_id),
  FOREIGN KEY(iped_ped_id) REFERENCES tb_pedido(ped_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_troca
(
  tro_id INT NOT NULL AUTO_INCREMENT,
  tro_dataHoraCriacao DATETIME NOT NULL,
  tro_dataHoraAtualizacao DATETIME DEFAULT NULL,
  tro_ativo BOOLEAN NOT NULL,
  tro_ped_id INT NOT NULL,
  tro_cli_id INT NOT NULL,
  tro_statusTroca VARCHAR(20),
  PRIMARY KEY (tro_id),
  FOREIGN KEY(tro_ped_id) REFERENCES tb_pedido(ped_id),
  FOREIGN KEY(tro_cli_id) REFERENCES tb_cliente(cli_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_itemTroca
(
  itro_id INT NOT NULL AUTO_INCREMENT, 
  itro_quantidade INT unsigned NOT NULL,
  itro_pro_id INT NOT NULL,
  itro_tro_id INT NOT NULL,
  PRIMARY KEY (itro_id),
  FOREIGN KEY(itro_pro_id) REFERENCES tb_produto(pro_id),
  FOREIGN KEY(itro_tro_id) REFERENCES tb_troca(tro_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

