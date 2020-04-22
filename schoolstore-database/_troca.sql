use lesProject;

CREATE TABLE tb_troca
(
  tro_id INT NOT NULL AUTO_INCREMENT,
  tro_dataHoraCriacao DATETIME NOT NULL,
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
  itro_quantidade INT NOT NULL,
  itro_pro_id INT NOT NULL,
  itro_tro_id INT NOT NULL,
  PRIMARY KEY (itro_id),
  FOREIGN KEY(itro_pro_id) REFERENCES tb_produto(pro_id),
  FOREIGN KEY(itro_tro_id) REFERENCES tb_troca(tro_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;