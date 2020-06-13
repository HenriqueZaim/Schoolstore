INSERT INTO tb_precificacao (pre_percentual, pre_ativo, pre_dataHoraCriacao)
VALUES
(0.04, b'1', NOW()),
(0.08, b'1', NOW()),
(0.1, b'1', NOW()),
(0.15, b'1', NOW()),
(0.2, b'1', NOW());



INSERT INTO tb_imagem (ima_nome, ima_descricao, ima_caminho, ima_ativo, ima_dataHoraCriacao) VALUES
("admin","admin","./img/admin.jpg", b'1', NOW());

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
("Proteção de mesa", "Descrição da imagem..", "./img/mesaprotecao.jpg", b'1', NOW()),
("23057010806333.png", "Imagem 23057010806333.png", "./img/23057010806333.png", b'1', NOW());

INSERT INTO `tb_usuario` (`usu_id`, `usu_ativo`, `usu_dataHoraCriacao`, `usu_email`, `usu_senha`, `usu_admin`, `usu_ima_id`) VALUES
(1, 1, '2020-01-01 09:31:04', 'admin@admin.com', 'qwer1234', 1, 1),
(2, 1, '2020-01-01 09:33:43', 'henrique@gmail.com', 'qwer1234', 0, 20);

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
(120, 1),
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
(50, NOW(), 1219.3, 1, 1),
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



INSERT INTO `tb_carrinho` (`car_id`, `car_subTotal`, `car_validade`, `car_ativo`, `car_dataHoraCriacao`) VALUES
(1, '48.070', '2020-06-20 10:28:50', 1, '2020-01-01 09:33:43');

INSERT INTO `tb_cliente` (`cli_id`, `cli_nome`, `cli_numeroTelefone`, `cli_numeroDocumento`, `cli_ativo`, `cli_dataHoraCriacao`, `cli_usu_id`, `cli_car_id`) VALUES
(1, 'Henrique', '12341234123', '23057010806333', 1, '2020-01-01 09:33:43', 2, 1);

INSERT INTO `tb_cartaoCredito` (`ccr_id`, `ccr_ativo`, `ccr_dataHoraCriacao`, `ccr_numero`, `ccr_codigo`, `ccr_nomeImpresso`, `ccr_favorito`, `ccr_cli_id`) VALUES
(1, 1, '2020-01-01 09:33:43', '1234123412341234', '234', 'qwerqwerqwer', 1, 1);

INSERT INTO `tb_endereco` (`end_id`, `end_nome`, `end_logradouro`, `end_bairro`, `end_cep`, `end_numero`, `end_complemento`, `end_referencia`, `end_favorito`, `end_ativo`, `end_dataHoraCriacao`, `end_cid_id`, `end_cli_id`) VALUES
(1, 'qwerqwer', 'qwerqwer', 'qweqwe', '12312-312', 123, 'qweqwe', 'qweqwerqw', 1, 1, '2020-01-01 09:33:43', 101, 1);

INSERT INTO tb_cupom (cup_valor, cup_usu_id, cup_ativo, cup_dataHoraCriacao,cup_dataHoraVencimento, cup_cupomPromocional) VALUES
(10, 2, b'1', NOW(), NULL, b'0'),
(20, 2, b'1', NOW(), NULL, b'0'),
(30, 2, b'1', NOW(), NULL, b'0'),
(50, 2, b'1', NOW(), NULL, b'0'),
(100, 2, b'1', NOW(), NULL, b'0');

INSERT INTO tb_formaPagamento (fpag_id, fpag_valorTotal) VALUES
(1, 48.070);

INSERT INTO tb_pagamentoCartao (pca_id, pca_valorTotalCartao, pca_ccr_id, pca_fpag_id) VALUES
(1, 48.070, 1, 1);

INSERT INTO `tb_frete` (`fre_id`, `fre_valor`, `fre_previsaoEmDias`, `fre_end_id`, `fre_ativo`, `fre_dataHoraCriacao`) VALUES
(1, '20.000', 8, 1, 1, '2020-01-10 09:34:57');

INSERT INTO `tb_pedido` (`ped_id`, `ped_valor`, `ped_statusPedido`, `ped_ativo`, `ped_dataHoraCriacao`, `ped_dataHoraAtualizacao`, `ped_fpag_id`, `ped_fre_id`, `ped_cli_id`) VALUES

(1, '48.070', 'ENTREGUE', 1, '2020-01-10 09:34:57', '2020-01-07 09:34:57',1, 1, 1),
(2, '48.070', 'ENTREGUE', 1, '2020-01-10 09:34:57', '2020-01-15 09:34:57',1, 1, 1),
(3, '48.070', 'ENTREGUE', 1, '2020-01-10 09:34:57', '2020-01-15 09:34:57',1, 1, 1),
(4, '48.070', 'ENTREGUE', 1, '2020-01-10 09:34:57', '2020-01-15 09:34:57',1, 1, 1),
(5, '48.070', 'ENTREGUE', 1, '2020-01-10 09:34:57', '2020-01-16 09:34:57',1, 1, 1),
(6, '48.070', 'ENTREGUE', 1, '2020-01-10 09:34:57', '2020-01-17 09:34:57',1, 1, 1),
(7, '48.070', 'ENTREGUE', 1, '2020-01-10 09:34:57', '2020-01-17 09:34:57',1, 1, 1),
(8, '48.070', 'ENTREGUE', 1, '2020-01-10 09:34:57', '2020-01-18 09:34:57',1, 1, 1),
(9, '48.070', 'REPROVADO', 1, '2020-01-10 09:34:57', '2020-01-19 09:34:57',1, 1, 1),
(10, '48.070', 'REPROVADO', 1, '2020-01-10 09:34:57', '2020-01-19 09:34:57',1, 1, 1),
(11, '48.070', 'REPROVADO', 1, '2020-01-10 09:34:57', '2020-01-20 09:34:57',1, 1, 1),
(12, '48.070', 'REPROVADO', 1, '2020-01-10 09:34:57', '2020-01-23 09:34:57',1, 1, 1),
(13, '48.070', 'ATUALIZADO', 0, '2020-01-10 09:34:57', '2020-01-23 09:34:57',1, 1, 1),
(14, '48.070', 'ATUALIZADO', 0, '2020-01-10 09:34:57', '2020-01-24 09:34:57',1, 1, 1),
(15, '48.070', 'ATUALIZADO', 0, '2020-01-10 09:34:57', '2020-01-25 09:34:57',1, 1, 1),

(16, '48.070', 'ENTREGUE', 1, '2020-02-10 09:34:57', '2020-02-15 09:34:57',1, 1, 1),
(17, '48.070', 'ENTREGUE', 1, '2020-02-10 09:34:57', '2020-02-15 09:34:57',1, 1, 1),
(18, '48.070', 'ENTREGUE', 1, '2020-02-10 09:34:57', '2020-02-15 09:34:57',1, 1, 1),
(19, '48.070', 'ENTREGUE', 1, '2020-02-10 09:34:57', '2020-02-16 09:34:57',1, 1, 1),
(20, '48.070', 'ENTREGUE', 1, '2020-02-10 09:34:57', '2020-02-17 09:34:57',1, 1, 1),
(21, '48.070', 'ENTREGUE', 1, '2020-02-10 09:34:57', '2020-02-17 09:34:57',1, 1, 1),
(22, '48.070', 'ENTREGUE', 1, '2020-02-10 09:34:57', '2020-02-18 09:34:57',1, 1, 1),
(23, '48.070', 'REPROVADO', 1, '2020-02-10 09:34:57', '2020-02-19 09:34:57',1, 1, 1),
(24, '48.070', 'REPROVADO', 1, '2020-02-10 09:34:57', '2020-02-19 09:34:57',1, 1, 1),
(25, '48.070', 'REPROVADO', 1, '2020-02-10 09:34:57', '2020-02-20 09:34:57',1, 1, 1),
(26, '48.070', 'REPROVADO', 1, '2020-02-10 09:34:57', '2020-02-23 09:34:57',1, 1, 1),
(27, '48.070', 'ATUALIZADO', 0, '2020-02-10 09:34:57', '2020-02-23 09:34:57',1, 1, 1),
(28, '48.070', 'ATUALIZADO', 0, '2020-02-10 09:34:57', '2020-02-24 09:34:57',1, 1, 1),
(29, '48.070', 'ATUALIZADO', 0, '2020-02-10 09:34:57', '2020-02-25 09:34:57',1, 1, 1),
(30, '48.070', 'ATUALIZADO', 0, '2020-02-10 09:34:57', '2020-02-11 09:34:57',1, 1, 1),
(31, '48.070', 'ATUALIZADO', 0, '2020-02-10 09:34:57', '2020-02-15 09:34:57',1, 1, 1),
(32, '48.070', 'REPROVADO', 1, '2020-02-10 09:34:57', '2020-02-15 09:34:57',1, 1, 1),
(33, '48.070', 'REPROVADO', 1, '2020-02-10 09:34:57', '2020-02-15 09:34:57',1, 1, 1),

(34, '48.070', 'ENTREGUE', 1, '2020-03-10 09:34:57', '2020-03-16 09:34:57',1, 1, 1),
(35, '48.070', 'ENTREGUE', 1, '2020-03-10 09:34:57', '2020-03-17 09:34:57',1, 1, 1),
(36, '48.070', 'ENTREGUE', 1, '2020-03-10 09:34:57', '2020-03-17 09:34:57',1, 1, 1),
(37, '48.070', 'ENTREGUE', 1, '2020-03-10 09:34:57', '2020-03-18 09:34:57',1, 1, 1),
(38, '48.070', 'REPROVADO', 1, '2020-03-10 09:34:57', '2020-03-19 09:34:57',1, 1, 1),
(39, '48.070', 'REPROVADO', 1, '2020-03-10 09:34:57', '2020-03-19 09:34:57',1, 1, 1),
(40, '48.070', 'REPROVADO', 1, '2020-03-10 09:34:57', '2020-03-20 09:34:57',1, 1, 1),
(41, '48.070', 'REPROVADO', 1, '2020-03-10 09:34:57', '2020-03-23 09:34:57',1, 1, 1),
(42, '48.070', 'ATUALIZADO', 0, '2020-03-10 09:34:57', '2020-03-23 09:34:57',1, 1, 1),
(43, '48.070', 'ATUALIZADO', 0, '2020-03-10 09:34:57', '2020-03-24 09:34:57',1, 1, 1),
(44, '48.070', 'ATUALIZADO', 0, '2020-03-10 09:34:57', '2020-03-25 09:34:57',1, 1, 1),
(45, '48.070', 'ATUALIZADO', 0, '2020-03-10 09:34:57', '2020-03-23 09:34:57',1, 1, 1),
(46, '48.070', 'ATUALIZADO', 0, '2020-03-10 09:34:57', '2020-03-24 09:34:57',1, 1, 1),
(47, '48.070', 'ATUALIZADO', 0, '2020-03-10 09:34:57', '2020-03-25 09:34:57',1, 1, 1),
(48, '48.070', 'ATUALIZADO', 0, '2020-03-10 09:34:57', '2020-03-23 09:34:57',1, 1, 1),
(49, '48.070', 'ATUALIZADO', 0, '2020-03-10 09:34:57', '2020-03-24 09:34:57',1, 1, 1),
(50, '48.070', 'ATUALIZADO', 0, '2020-03-10 09:34:57', '2020-03-25 09:34:57',1, 1, 1),
(51, '48.070', 'ATUALIZADO', 0, '2020-03-10 09:34:57', '2020-03-23 09:34:57',1, 1, 1),
(52, '48.070', 'ATUALIZADO', 0, '2020-03-10 09:34:57', '2020-03-24 09:34:57',1, 1, 1),
(53, '48.070', 'ATUALIZADO', 0, '2020-03-10 09:34:57', '2020-03-25 09:34:57',1, 1, 1),
(54, '48.070', 'ATUALIZADO', 0, '2020-03-10 09:34:57', '2020-03-23 09:34:57',1, 1, 1),

(55, '48.070', 'ENTREGUE', 1, '2020-04-10 09:34:57', '2020-04-24 09:34:57',1, 1, 1),
(56, '48.070', 'ENTREGUE', 1, '2020-04-10 09:34:57', '2020-04-24 09:34:57',1, 1, 1),
(57, '48.070', 'ENTREGUE', 1, '2020-04-10 09:34:57', '2020-04-25 09:34:57',1, 1, 1),
(58, '48.070', 'ENTREGUE', 1, '2020-04-10 09:34:57', '2020-04-23 09:34:57',1, 1, 1),
(59, '48.070', 'ENTREGUE', 1, '2020-04-10 09:34:57', '2020-04-24 09:34:57',1, 1, 1),
(60, '48.070', 'ENTREGUE', 1, '2020-04-10 09:34:57', '2020-04-23 09:34:57',1, 1, 1),
(61, '48.070', 'ENTREGUE', 1, '2020-04-10 09:34:57', '2020-04-24 09:34:57',1, 1, 1),
(62, '48.070', 'ENTREGUE', 1, '2020-04-10 09:34:57', '2020-04-25 09:34:57',1, 1, 1),
(63, '48.070', 'ENTREGUE', 1, '2020-04-10 09:34:57', '2020-04-23 09:34:57',1, 1, 1),
(64, '48.070', 'ATUALIZADO', 0, '2020-04-10 09:34:57', '2020-04-24 09:34:57',1, 1, 1),
(65, '48.070', 'ATUALIZADO', 0, '2020-04-10 09:34:57', '2020-04-25 09:34:57',1, 1, 1),
(66, '48.070', 'ATUALIZADO', 0, '2020-04-10 09:34:57', '2020-04-23 09:34:57',1, 1, 1),

(67, '48.070', 'ENTREGUE', 1, '2020-05-10 09:34:57', '2020-05-24 09:34:57',1, 1, 1),
(68, '48.070', 'ENTREGUE', 1, '2020-05-10 09:34:57', '2020-05-25 09:34:57',1, 1, 1),
(69, '48.070', 'ENTREGUE', 1, '2020-05-10 09:34:57', '2020-05-23 09:34:57',1, 1, 1),
(70, '48.070', 'ENTREGUE', 1, '2020-05-10 09:34:57', '2020-05-24 09:34:57',1, 1, 1),
(71, '48.070', 'ENTREGUE', 1, '2020-05-10 09:34:57', '2020-05-25 09:34:57',1, 1, 1),
(72, '48.070', 'ENTREGUE', 1, '2020-05-10 09:34:57', '2020-05-23 09:34:57',1, 1, 1),
(73, '48.070', 'ENTREGUE', 1, '2020-05-10 09:34:57', '2020-05-24 09:34:57',1, 1, 1),
(74, '48.070', 'REPROVADO', 1, '2020-05-10 09:34:57', '2020-05-24 09:34:57',1, 1, 1),
(75, '48.070', 'REPROVADO', 1, '2020-05-10 09:34:57', '2020-05-25 09:34:57',1, 1, 1),
(76, '48.070', 'REPROVADO', 1, '2020-05-10 09:34:57', '2020-05-25 09:34:57',1, 1, 1),
(77, '48.070', 'REPROVADO', 1, '2020-05-10 09:34:57', '2020-05-23 09:34:57',1, 1, 1),
(78, '48.070', 'REPROVADO', 1, '2020-05-10 09:34:57', '2020-05-24 09:34:57',1, 1, 1),

(79, '48.070', 'ENTREGUE', 1, '2020-06-10 09:34:57', '2020-06-23 09:34:57',1, 1, 1),
(80, '48.070', 'ENTREGUE', 1, '2020-06-10 09:34:57', '2020-06-24 09:34:57',1, 1, 1),
(81, '48.070', 'ATUALIZADO', 0, '2020-06-10 09:34:57', '2020-06-25 09:34:57',1, 1, 1);


INSERT INTO `tb_itemPedido` (`iped_id`, `iped_quantidade`, `iped_pro_id`, `iped_ped_id`) VALUES
(1, 1, 1, 1),
(2, 1, 1, 2),
(3, 1, 1, 3),
(4, 1, 1, 4),
(5, 1, 1, 5),
(6, 1, 1, 6),
(7, 1, 1, 7),
(8, 1, 1, 8),
(9, 1, 1, 9),
(10, 1, 1, 10),
(11, 1, 1, 11),
(12, 1, 1, 12),
(13, 1, 1, 13),
(14, 1, 1, 14),
(15, 1, 1, 15),
(16, 1, 1, 16),
(17, 1, 1, 17),
(18, 1, 1, 18),
(19, 1, 1, 19),
(20, 1, 1, 20),
(21, 1, 1, 21),
(22, 1, 1, 22),
(23, 1, 1, 23),
(24, 1, 1, 24),
(25, 1, 1, 25),
(26, 1, 1, 26),
(27, 1, 1, 27),
(28, 1, 1, 28),
(29, 1, 1, 29),
(30, 1, 1, 30),
(31, 1, 1, 31),
(32, 1, 1, 32),
(33, 1, 1, 33),
(34, 1, 1, 34),
(35, 1, 1, 35),
(36, 1, 1, 36),
(37, 1, 1, 37),
(38, 1, 1, 38),
(39, 1, 1, 39),
(40, 1, 1, 40),
(41, 1, 1, 41),
(42, 1, 1, 42),
(43, 1, 1, 43),
(44, 1, 1, 44),
(45, 1, 1, 45),
(46, 1, 1, 46),
(47, 1, 1, 47),
(48, 1, 1, 48),
(49, 1, 1, 49),
(50, 1, 1, 50),
(51, 1, 1, 51),
(52, 1, 1, 52),
(53, 1, 1, 53),
(54, 1, 1, 54),
(55, 1, 1, 55),
(56, 1, 1, 56),
(57, 1, 1, 57),
(58, 1, 1, 58),
(59, 1, 1, 59),
(60, 1, 1, 60),
(61, 1, 1, 61),
(62, 1, 1, 62),
(63, 1, 1, 63),
(64, 1, 1, 64),
(65, 1, 1, 65),
(66, 1, 1, 66),
(67, 1, 1, 67),
(68, 1, 1, 68),
(69, 1, 1, 69),
(70, 1, 1, 70),
(71, 1, 1, 71),
(72, 1, 1, 72),
(73, 1, 1, 73),
(74, 1, 1, 74),
(75, 1, 1, 75),
(76, 1, 1, 76),
(77, 1, 1, 77),
(78, 1, 1, 78),
(79, 1, 1, 79),
(80, 1, 1, 80),
(81, 1, 1, 81);

