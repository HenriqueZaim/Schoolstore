use lesProject;

CREATE TABLE tb_pagamentoCupom
(
  pcu_id INT NOT NULL AUTO_INCREMENT,
  pcu_valorTotalCupom DECIMAL(4,2) NOT NULL,
  PRIMARY KEY (pcu_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_cupom
(
  cup_id INT NOT NULL AUTO_INCREMENT,
  cup_valor DECIMAL(4,2) NOT NULL,
  cup_ativo BOOLEAN NOT NULL,
  cup_dataHoraCriacao DATETIME NOT NULL,
  cup_pcu_id INT NOT NULL,
  PRIMARY KEY (cup_id),
  FOREIGN KEY(cup_pcu_id) REFERENCES tb_pagamentoCupom(pcu_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_cupomPromocional
(
  cpr_id INT NOT NULL AUTO_INCREMENT,
  cpr_valor DECIMAL(4,2) NOT NULL,
  cpr_validade DATETIME NOT NULL,
  cpr_ativo BOOLEAN NOT NULL,
  cpr_dataHoraCriacao DATETIME NOT NULL,
  cpr_pcu_id INT NOT NULL,
  PRIMARY KEY (cpr_id),
  FOREIGN KEY(cpr_pcu_id) REFERENCES tb_pagamentoCupom(cpr_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_cupomTroca
(
  ctr_id INT NOT NULL AUTO_INCREMENT,
  ctr_valor DECIMAL(4,2) NOT NULL,
  ctr_ativo BOOLEAN NOT NULL,
  ctr_dataHoraCriacao DATETIME NOT NULL,
  ctr_pcu_id INT NOT NULL,
  ctr_usu_id INT NOT NULL,
  PRIMARY KEY (ctr_id),
  FOREIGN KEY(ctr_pcu_id) REFERENCES tb_pagamentoCupom(ctr_id),
  FOREIGN KEY(ctr_usu_id) REFERENCES tb_usuario(su_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_cartaoCredito
(
  ccr_id INT NOT NULL AUTO_INCREMENT,
  ccr_ativo BOOLEAN NOT NULL,
  ccr_dataHoraCriacao DATETIME NOT NULL,
  ccr_numero VARCHAR(16) NOT NULL,
  ccr_codigo VARCHAR(3) NOT NULL,
  ccr_nomeImpresso VARCHAR(100) NOT NULL,
  ccr_favorito BOOLEAN NOT NULL,
  ccr_cli_id INT NOT NULL,
  PRIMARY KEY (ccr_id),
  FOREIGN KEY(ccr_cli_id) REFERENCES tb_cliente(cli_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_pagamentoCartao
(
  pca_id INT NOT NULL AUTO_INCREMENT,
  pca_valorTotalCartao DECIMAL(4,2) NOT NULL,
  pca_ccr_id INT NOT NULL,
  PRIMARY KEY (pca_id),
  FOREIGN KEY(pca_ccr_id) REFERENCES tb_cartaoCredito(ccr_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_formaPagamento
(
  fpag_id INT NOT NULL AUTO_INCREMENT,
  fpag_valorTotal DECIMAL(4,2) NOT NULL,
  fpag_pcu_id INT DEFAULT NULL,
  fpag_pca_id INT DEFAULT NULL,
  PRIMARY KEY (fpag_id),
  FOREIGN KEY(fpag_pcu_id) REFERENCES tb_pagamentoCupom(pcu_id),
  FOREIGN KEY(fpag_pca_id) REFERENCES tb_pagamentoCartao(pca_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;