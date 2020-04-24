use lesProject;

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

INSERT INTO tb_imagem (ima_nome, ima_descricao, ima_caminho, ima_ativo, ima_dataHoraCriacao) VALUES
("admin","admin","./img/admin.jpg", b'1', NOW());

INSERT INTO tb_usuario (usu_email, usu_senha, usu_admin, usu_ima_id, usu_ativo, usu_dataHoraCriacao) VALUES
("admin@admin.com", "qwer1234", b'1', 1, b'1', NOW());