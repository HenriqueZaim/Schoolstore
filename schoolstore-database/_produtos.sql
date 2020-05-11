INSERT INTO tb_precificacao (pre_percentual, pre_ativo, pre_dataHoraCriacao)
VALUES
(0.04, b'1', NOW()),
(0.08, b'1', NOW()),
(0.1, b'1', NOW()),
(0.15, b'1', NOW()),
(0.2, b'1', NOW());

INSERT INTO tb_imagem (ima_nome, ima_descricao, ima_caminho, ima_ativo, ima_dataHoraCriacao) VALUES
("admin","admin","./img/admin.jpg", b'1', NOW());

INSERT INTO tb_usuario (usu_email, usu_senha, usu_admin, usu_ima_id, usu_ativo, usu_dataHoraCriacao) VALUES
("admin@admin.com", "qwer1234", b'1', 1, b'1', NOW());

INSERT INTO tb_imagem (ima_nome, ima_descricao, ima_caminho, ima_ativo, ima_dataHoraCriacao)
VALUES
("Cadeira escolar", "Descrição da imagem..", "./img/cadeiraescolar.jpg", b'1', NOW()),
("Mesa para escritório", "Descrição da imagem..", "./img/mesaescritorio.jpg", b'1', NOW()),
("Mesa escolar", "Descrição da imagem..", "./img/mesaescolar.jpg", b'1', NOW()),
("Cadeira para escritório", "Descrição da imagem..", "./img/cadeiraescritorio.jpg", b'1', NOW()),
("Quadro-negro", "Descrição da imagem..", "./img/quadronegro.jpg", b'1', NOW()),
("Abajur", "Descrição da imagem..", "./img/abajur.jpg", b'1', NOW()),
("Fichário", "Descrição da imagem..", "./img/fichario.jpg", b'1', NOW()),
("Cadeira com rodinha", "Descrição da imagem..", "./img/cadeirarodinha.jpg", b'1', NOW()),
("Mesa ajustável", "Descrição da imagem..", "./img/mesaajustavel.jpg", b'1', NOW()),
("Quadro de anotação", "Descrição da imagem..", "./img/quadroanotacao.jpg", b'1', NOW()),
("Kit escolar", "Descrição da imagem..", "./img/kitescolar.jpg", b'1', NOW()),
("Iluminária", "Descrição da imagem..", "./img/iluminaria.jpg", b'1', NOW()),
("Mesa alta", "Descrição da imagem..", "./img/mesaalta.jpg", b'1', NOW()),
("Cadeira de chefe", "Descrição da imagem..", "./img/cadeirachefe.jpg", b'1', NOW()),
("Organizador de documentos", "Descrição da imagem..", "./img/organizador.jpg", b'1', NOW()),
("Escrivaninha ajustável", "Descrição da imagem..", "./img/escrivaninha.jpg", b'1', NOW()),
("Compactador de pastas", "Descrição da imagem..", "./img/compactador.jpg", b'1', NOW()),
("Proteção de mesa", "Descrição da imagem..", "./img/mesaprotecao.jpg", b'1', NOW());

INSERT INTO tb_produto (pro_nome, pro_preco, pro_descricao, pro_ima_id, pro_pre_id, pro_ativo, pro_dataHoraCriacao)
VALUES 
("Cadeira escolar", 25.99, "Cadeira ajustada e desenvolvida para fins escolares ", 2, 2, b'1', NOW()),
("Mesa para escritório", 230.99, "Mesa para escritório feita sob medida", 3, 5, b'1', NOW()),
("Mesa escolar", 89.90, "Mesa para fins escolares", 4, 4, b'1', NOW()),
("Cadeira para escritório", 85.99, "Cadeira simples para seu escritório", 5, 4, b'1', NOW()),
("Quadro-negro", 45.99, "Descrição sobre um produto", 6, 3, b'1', NOW()),
("Abajur", 8.99, "Descrição sobre um produto", 7, 1, b'1', NOW()),
("Fichário", 15.99, "Descrição sobre um produto", 8, 2, b'1', NOW()),
("Cadeira com rodinha", 110.99, "Descrição sobre um produto", 9, 5, b'1', NOW()),
("Mesa ajustável", 278.99, "Descrição sobre um produto", 10, 5, b'1', NOW()),
("Quadro de anotação", 15.99, "Descrição sobre um produto", 11, 2, b'1', NOW()),
("Kit escolar", 75.99, "Descrição sobre um produto", 12, 4, b'1', NOW()),
("Iluminária", 20.99, "Descrição sobre um produto", 13, 2, b'1', NOW()),
("Mesa alta", 150.99, "Descrição sobre um produto", 14, 5, b'1', NOW()),
("Cadeira de chefe", 344.99, "Descrição sobre um produto", 15, 5, b'1', NOW()),
("Organizador de documentos", 15.50, "Descrição sobre um produto", 16, 2, b'1', NOW()),
("Escrivaninha ajustável", 310, "Descrição sobre um produto", 17, 5, b'1', NOW()),
("Compactador de pastas", 11.99, "Descrição sobre um produto", 18, 2, b'1', NOW()),
("Proteção de mesa", 15.99, "Descrição sobre um produto", 19, 2, b'1', NOW());

INSERT INTO tb_estoque (sto_quantidadeTotal, sto_pro_id)
VALUES 
(70, 1),
(25, 2),
(40, 3),
(30, 4),
(50, 5),
(67, 6),
(43, 7),
(30, 8),
(15, 9),
(61, 10),
(36, 11),
(75, 12),
(5, 13),
(8, 14),
(11, 15),
(18, 16),
(62, 17),
(64, 18);

INSERT INTO tb_fornecedor (for_nome, for_cnpj, for_ativo, for_dataCriacao)
VALUES 
("Industria A","1234123443", b'1', NOW()),
("Industria B","1234123443", b'1', NOW()),
("Industria C","1234123443", b'1', NOW());

INSERT INTO tb_itemEstoque (isto_quantidade, isto_dataEntrada, isto_valor, isto_for_id, isto_sto_id)
VALUES 
(70, NOW(), 1819.3, 1, 1),
(25, NOW(), 5774.75, 2, 2),
(40, NOW(), 3596, 3, 3),
(30, NOW(), 2579.7, 1, 4),
(50, NOW(), 2299.5, 2, 5),
(67, NOW(), 602.33, 3, 6),
(43, NOW(), 687.57, 3, 7),
(30, NOW(), 3329.7, 3, 8),
(15, NOW(), 4184.85, 2, 9),
(61, NOW(), 975.39, 3, 10),
(36, NOW(), 2735.64, 2, 11),
(75, NOW(), 1574.25, 1, 12),
(5, NOW(),  754.95, 1, 13),
(8, NOW(),  2759.92, 3, 14),
(11, NOW(), 170.5, 3, 15),
(6, NOW(), 1860, 1, 16),
(62, NOW(), 743.38, 2, 17),
(64, NOW(), 1023.36, 1, 18);


INSERT INTO tb_categoria (cat_nome, cat_pro_id, cat_ativo, cat_dataHoraCriacao)
VALUES
("Escolar", 1, b'1', NOW()),
("Escolar", 3, b'1', NOW()),
("Escolar", 5, b'1', NOW()),
("Escolar", 6, b'1', NOW()),
("Escolar", 11, b'1', NOW()),
("Escolar", 15, b'1', NOW()),
("Escolar", 17, b'1', NOW()),
("Escolar", 18, b'1', NOW()),
("Mesa", 2, b'1', NOW()),
("Mesa", 3, b'1', NOW()),
("Mesa", 9, b'1', NOW()),
("Mesa", 13, b'1', NOW()),
("Mesa", 18, b'1', NOW()),
("Cadeira", 1, b'1', NOW()),
("Cadeira", 4, b'1', NOW()),
("Cadeira", 8, b'1', NOW()),
("Cadeira", 14, b'1', NOW()),
("Escritório", 2, b'1', NOW()),
("Escritório", 4, b'1', NOW()),
("Escritório", 6, b'1', NOW()),
("Escritório", 7, b'1', NOW()),
("Escritório", 8, b'1', NOW()),
("Escritório", 9, b'1', NOW()),
("Escritório", 10, b'1', NOW()),
("Escritório", 12, b'1', NOW()),
("Escritório", 13, b'1', NOW()),
("Escritório", 14, b'1', NOW()),
("Escritório", 15, b'1', NOW()),
("Escritório", 16, b'1', NOW()),
("Escritório", 17, b'1', NOW()),
("Escritório", 18, b'1', NOW()),
("Acessório", 5, b'1', NOW()),
("Acessório", 6, b'1', NOW()),
("Acessório", 7, b'1', NOW()),
("Acessório", 10, b'1', NOW()),
("Acessório", 12, b'1', NOW()),
("Acessório", 15, b'1', NOW()),
("Acessório", 17, b'1', NOW()),
("Acessório", 18, b'1', NOW());



