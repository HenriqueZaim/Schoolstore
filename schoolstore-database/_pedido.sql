use lesProject;

CREATE TABLE tb_frete
( 
  fre_id INT NOT NULL AUTO_INCREMENT,
  fre_valor DECIMAL(6,2),
  fre_previsaoEmDias INT NOT NULL,
  fre_end_id INT NOT NULL,
  PRIMARY KEY (fre_id),
  FOREIGN KEY(fre_end_id) REFERENCES tb_endereco(end_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_pedido
(
  ped_id INT NOT NULL AUTO_INCREMENT,
  ped_valor DECIMAL(6,2) NOT NULL,
  ped_statusPedido VARCHAR(20) NOT NULL,
  ped_ativo BOOLEAN NOT NULL,
  ped_dataHoraCriacao DATETIME NOT NULL,
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
  iped_quantidade INT NOT NULL,
  iped_pro_id INT NOT NULL,
  iped_ped_id INT NOT NULL,
  PRIMARY KEY (iped_id),
  FOREIGN KEY(iped_pro_id) REFERENCES tb_produto(pro_id),
  FOREIGN KEY(iped_ped_id) REFERENCES tb_pedido(ped_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;