use lesProject;

CREATE TABLE tb_estoque
(
  sto_id INT NOT NULL AUTO_INCREMENT,
  sto_quantidadeTotal INT(5) DEFAULT NULL,
  sto_pro_id INT NOT NULL,
  PRIMARY KEY (sto_id),
  FOREIGN KEY(sto_pro_id) REFERENCES tb_produto(pro_id)
)

CREATE TABLE tb_fornecedor
(
  for_id INT NOT NULL AUTO_INCREMENT,
  for_cnpj VARCHAR(20) NOT NULL,
  for_nome VARCHAR(100) NOT NULL,
  for_ativo BOOLEAN NOT NULL,
  for_dataCriacao DATETIME NOT NULL,
  PRIMARY KEY (for_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_itemEstoque
(
  isto_id INT NOT NULL AUTO_INCREMENT,
  isto_quantidade INT(5) DEFAULT NULL,
  isto_dataEntrada DATE NOT NULL,
  isto_valor DECIMAL(4,2) NOT NULL,
  isto_for_id INT NOT NULL,
  isto_sto_id INT NOT NULL,
  PRIMARY KEY (isto_id),
  FOREIGN KEY(isto_sto_id) REFERENCES tb_estoque(sto_id),
  FOREIGN KEY(isto_for_id) REFERENCES tb_fornecedor(for_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO tb_estoque (sto_quantidadeTotal, sto_pro_id)
VALUES (60, 1);

INSERT INTO tb_estoque (sto_quantidadeTotal, sto_pro_id)
VALUES (40, 2);



INSERT INTO tb_fornecedor (for_nome, for_cnpj, for_ativo, for_dataCriacao)
VALUES ("Industria A","1234123443", 1, NOW());

INSERT INTO tb_fornecedor (for_nome, for_cnpj, for_ativo, for_dataCriacao)
VALUES ("Industria B","1234123443", 1, NOW());

INSERT INTO tb_fornecedor (for_nome, for_cnpj, for_ativo, for_dataCriacao)
VALUES ("Industria C", "1234123443", 1, NOW());



INSERT INTO tb_itemEstoque (isto_quantidade, isto_dataEntrada, isto_valor, isto_for_id, isto_est_id)
VALUES (20, NOW(), 33.33, 1, 1);

INSERT INTO tb_itemEstoque (isto_quantidade, isto_dataEntrada, isto_valor, isto_for_id, isto_est_id)
VALUES (20, NOW(), 33, 1, 1);

INSERT INTO tb_itemEstoque (isto_quantidade, isto_dataEntrada, isto_valor, isto_for_id, isto_est_id)
VALUES (20, NOW(), 3333.33, 1, 1);

INSERT INTO tb_itemEstoque (isto_quantidade, isto_dataEntrada, isto_valor, isto_for_id, isto_est_id)
VALUES (20, NOW(), 33, 2, 2);

INSERT INTO tb_itemEstoque (isto_quantidade, isto_dataEntrada, isto_valor, isto_for_id, isto_est_id)
VALUES (20, NOW(), 66, 2, 2);