CREATE DATABASE lesProject;

use lesProject;

CREATE TABLE estados
(
 est_id INT NOT NULL AUTO_INCREMENT,
 est_nome VARCHAR(100) NOT NULL,
 est_sigla CHAR(2) NOT NULL,
 est_ativo BOOLEAN NOT NULL,
 est_dataHoraCriacao DATETIME NOT NULL,
 PRIMARY KEY (est_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `estados` (`est_id`, `est_dataHoraCriacao`, `est_ativo`, `est_nome`, `est_sigla`) VALUES
(1, NOW(), b'1', 'Acre', 'AC'),
(2, NOW(), b'1', 'Alagoas', 'AL'),
(3, NOW(), b'1', 'Amapá', 'AP'),
(4, NOW(), b'1', 'Amazonas', 'AM'),
(5, NOW(), b'1', 'Bahia', 'BA'),
(6, NOW(), b'1', 'Ceará', 'CE'),
(7, NOW(), b'1', 'Distrito Federal', 'DF'),
(8, NOW(), b'1', 'Espírito Santo', 'ES'),
(9, NOW(), b'1', 'Goiás', 'GO'),
(10, NOW(), b'1', 'Maranhão', 'MA'),
(11, NOW(), b'1', 'Mato Grosso', 'MT'),
(12, NOW(), b'1', 'Mato Grosso do Sul', 'MS'),
(13, NOW(), b'1', 'Minhas Gerais', 'MG'),
(14, NOW(), b'1', 'Pará', 'PA'),
(15, NOW(), b'1', 'Paraíba', 'PB'),
(16, NOW(), b'1', 'Paraná', 'PN'),
(17, NOW(), b'1', 'Pernambuco', 'PE'),
(18, NOW(), b'1', 'Piauí', 'PI'),
(19, NOW(), b'1', 'Rio de Janeiro', 'RJ'),
(20, NOW(), b'1', 'Rio Grande do Norte', 'RN'),
(21, NOW(), b'1', 'Rio Grande do Sul', 'RS'),
(22, NOW(), b'1', 'Rondônia', 'RO'),
(23, NOW(), b'1', 'Rorâima', 'RR'),
(24, NOW(), b'1', 'Santa Catarina', 'SC'),
(25, NOW(), b'1', 'São Paulo', 'SP'),
(26, NOW(), b'1', 'Sergipe', 'SE'),
(27, NOW(), b'1', 'Tocantins', 'TO');

CREATE TABLE cidades
(
 cid_id INT NOT NULL AUTO_INCREMENT,
 cid_nome VARCHAR(100) NOT NULL,
 cid_ativo BOOLEAN NOT NULL,
 cid_dataHoraCriacao DATETIME NOT NULL,
 cid_est_id LONG NOT NULL,
 PRIMARY KEY (cid_id),
 FOREIGN KEY(cid_est_id) REFERENCES estados(est_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE imagens
( 
 ima_id INT NOT NULL AUTO_INCREMENT,
 ima_ativo BOOLEAN NOT NULL,
 ima_dataHoraCriacao DATETIME NOT NULL,
 ima_foto VARCHAR(100) NOT NULL,
 ima_descricao VARCHAR(100) NOT NULL,
 PRIMARY KEY (ima_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuarios
( 
 usu_id INT NOT NULL AUTO_INCREMENT,
 usu_ativo BOOLEAN NOT NULL,
 usu_dataHoraCriacao DATETIME NOT NULL,
 usu_email VARCHAR(100) NOT NULL,
 usu_senha VARCHAR(100) NOT NULL,
 usu_ima_id INT,
 PRIMARY KEY (usu_id),
 FOREIGN KEY(usu_ima_id) REFERENCES imagens(ima_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE enderecos
( 
 end_id INT NOT NULL AUTO_INCREMENT,
 end_logradouro VARCHAR(100) NOT NULL,
 end_bairro VARCHAR(100) NOT NULL,
 end_cep VARCHAR(9) NOT NULL,
 end_numero INT(4) NOT NULL,
 end_complemento VARCHAR(100),
 end_referencia VARCHAR(100),
 end_favorito BOOLEAN NOT NULL,
 end_ativo BOOLEAN NOT NULL,
 end_dataHoraCriacao DATETIME NOT NULL,
 end_cid_id INT NOT NULL,
 PRIMARY KEY (end_id),
 FOREIGN KEY(end_cid_id) REFERENCES cidades(cid_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE clientes
( 
 cli_id INT NOT NULL AUTO_INCREMENT,
 cli_nome VARCHAR(100) NOT NULL,
 cli_numeroTelefone VARCHAR(12) NOT NULL,
 cli_numeroDocumento VARCHAR(14) NOT NULL,
 cli_ativo BOOLEAN NOT NULL,
 cli_dataHoraCriacao DATETIME NOT NULL,
 cli_usu_id INT NOT NULL,
 cli_end_id INT NOT NULL,
 PRIMARY KEY (cli_id),
 FOREIGN KEY(cli_usu_id) REFERENCES usuarios(usu_id),
 FOREIGN KEY(cli_end_id) REFERENCES enderecos(end_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

