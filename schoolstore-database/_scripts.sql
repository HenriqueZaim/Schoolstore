use lesProject;



-- INSERT INTO tb_estoque (sto_quantidadeTotal, sto_pro_id)
-- VALUES (60, 1);

-- INSERT INTO tb_estoque (sto_quantidadeTotal, sto_pro_id)
-- VALUES (40, 2);


-- INSERT INTO tb_fornecedor (for_nome, for_cnpj, for_ativo, for_dataCriacao)
-- VALUES ("Industria A","1234123443", 1, NOW());

-- INSERT INTO tb_fornecedor (for_nome, for_cnpj, for_ativo, for_dataCriacao)
-- VALUES ("Industria B","1234123443", 1, NOW());

-- INSERT INTO tb_fornecedor (for_nome, for_cnpj, for_ativo, for_dataCriacao)
-- VALUES ("Industria C", "1234123443", 1, NOW());


-- INSERT INTO tb_itemEstoque (isto_quantidade, isto_dataEntrada, isto_valor, isto_for_id, isto_sto_id)
-- VALUES (20, NOW(), 33.33, 1, 1);

-- INSERT INTO tb_itemEstoque (isto_quantidade, isto_dataEntrada, isto_valor, isto_for_id, isto_sto_id)
-- VALUES (20, NOW(), 33, 1, 1);

-- INSERT INTO tb_itemEstoque (isto_quantidade, isto_dataEntrada, isto_valor, isto_for_id, isto_sto_id)
-- VALUES (20, NOW(), 3333.33, 1, 1);

-- INSERT INTO tb_itemEstoque (isto_quantidade, isto_dataEntrada, isto_valor, isto_for_id, isto_sto_id)
-- VALUES (20, NOW(), 33, 2, 2);

-- INSERT INTO tb_itemEstoque (isto_quantidade, isto_dataEntrada, isto_valor, isto_for_id, isto_sto_id)
-- VALUES (20, NOW(), 66, 2, 2);

INSERT INTO tb_produto (pro_nome, pro_preco, pro_descricao, pro_ativo, pro_dataHoraCriacao)
VALUES ("prod1", 25, "descricao1", b'1', NOW());

INSERT INTO tb_produto (pro_nome, pro_preco, pro_descricao, pro_ativo, pro_dataHoraCriacao)
VALUES ("prod2", 25, "descricao3", b'1', NOW());

INSERT INTO tb_produto (pro_nome, pro_preco, pro_descricao, pro_ativo, pro_dataHoraCriacao)
VALUES ("prod3", 25, "descricao4", b'1', NOW());

INSERT INTO tb_produto (pro_nome, pro_preco, pro_descricao, pro_ativo, pro_dataHoraCriacao)
VALUES ("prod4", 25, "descricao5", b'1', NOW());

INSERT INTO tb_imagem (ima_nome, ima_descricao, ima_caminho, ima_ativo, ima_dataHoraCriacao) VALUES
("admin","admin","./img/admin.jpg", b'1', NOW());

INSERT INTO tb_usuario (usu_email, usu_senha, usu_admin, usu_ima_id, usu_ativo, usu_dataHoraCriacao) VALUES
("admin@admin.com", "qwer1234", b'1', 1, b'1', NOW());

INSERT INTO tb_usuario (usu_email, usu_senha, usu_admin, usu_ima_id, usu_ativo, usu_dataHoraCriacao) VALUES
("henrique@admin.com", "qwer1234", b'0', 1, b'1', NOW());

INSERT INTO tb_carrinho (car_subTotal, car_validade, car_ativo, car_dataHoraCriacao)
VALUES (125,NOW(),b'1',NOW());

INSERT INTO tb_cliente (cli_nome, cli_numeroTelefone, cli_numeroDocumento, cli_usu_id, cli_car_id, cli_ativo, cli_dataHoraCriacao) VALUES
("Henrique", "1234123", "23057010806", 2, 1, b'1', NOW());

INSERT INTO tb_itemCarrinho (icar_quantidade, icar_car_id, icar_pro_id)
VALUES (1, 1, 1),(2, 1, 2), (1,1,3), (1,1,4);
