use les-project

CREATE TABLE usuarios
( 
 usu_id int NOT NULL AUTO_INCREMENT,
 usu_ativo boolean NOT NULL,
 usu_dataHoraCriacao DATETIME NOT NULL,
 usu_nome varchar (100) NOT NULL,
 usu_email varchar(100) NOT NULL,
 usu_senha varchar(100) NOT NULL,
 usu_numeroTelefone varchar(15) NOT NULL,
 usu_dataNascimento Date,
 usu_numeroDocumento varchar(30) NOT NULL,
 usu_genero varchar(10),
 PRIMARY KEY (usu_id)
);

/*
CREATE TABLE clientes
( 
 cli_id int NOT NULL AUTO_INCREMENT,
 cli_nome varchar(100),
 cli_usu_id int,
 PRIMARY KEY (cli_id),
 FOREIGN KEY(cli_usu_id) REFERENCES usuarios(usu_id)
);*/


INSERT INTO usuarios (usu_email, usu_senha, usu_status, usu_dataHoraCriacao) VALUES ("qerqwer","qwerqewr",true,NOW())

