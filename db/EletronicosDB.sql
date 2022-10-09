CREATE DATABASE EletronicosDB;
USE EletronicosDB;

CREATE TABLE TableCarrinho (
	idCarrinho INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
	idProduto INTEGER NOT NULL,
	codigoCarrinho INTEGER NOT NULL,
	quantidade INTEGER NOT NULL,
	total FLOAT NOT NULL
);

CREATE TABLE GroupCarrinho (
    idGroupCarrinho INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    codigoCarrinho INTEGER NOT NULL
);

CREATE TABLE TableCliente (
	idCliente INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
	nomeCliente VARCHAR(30) NOT NULL,
	CPF INTEGER NOT NULL,
	bandeiraCartao VARCHAR(20),
	numeroCartao INTEGER,
	nomeNoCartao VARCHAR(20),
	validade VARCHAR(8),
	seguranca VARCHAR(3)
);

CREATE TABLE TableFuncionario (
	idFuncionario INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
	cargoFuncionario VARCHAR(30),
	setor VARCHAR(30)
);

CREATE TABLE TablePedidos (
	idPedido INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
	idCliente  VARCHAR(30) NOT NULL,
	idFuncionario  VARCHAR(30) NOT NULL,
	codigoCarrinho INTEGER NOT NULL,
	previsaoDeEntrega datetime,
	formaDePagamento VARCHAR(30) NOT NULL,
	codigoDaTransportadora INTEGER NOT NULL,
	frete FLOAT NOT NULL
);

CREATE TABLE TableProdutos (
	idProduto INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
	nomeDoProduto VARCHAR(50) NOT NULL,
	quantidadeDePecas INTEGER(30) NOT NULL,
	precoUnitario FLOAT NOT NULL,
	descricao VARCHAR(50) NOT NULL
);

CREATE TABLE TableUsuario (
	idUsuario INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
	nomeUsuario VARCHAR(30) NOT NULL,
	CEP INTEGER(8) NOT NULL,
	ruaEnderecoNumero VARCHAR(50),
	cidadeEndereco VARCHAR(25),
	estadoEndereco CHAR(2),
	telefoneUsuario CHAR(20),
	tipoUsuario CHAR(2),
	login VARCHAR(50),
	senha VARCHAR(20)
);

ALTER TABLE `TableCarrinho` ADD CONSTRAINT `fk_idProduto` FOREIGN KEY ( `idProduto` ) REFERENCES `TableProdutos` ( `idProduto` );
ALTER TABLE `TablePedidos` ADD CONSTRAINT `fk_idCarrinho` FOREIGN KEY ( `codigoCarrinho` ) REFERENCES `TableCarrinho` ( `codigoCarrinho` );
ALTER TABLE `TableCarrinho` ADD CONSTRAINT `fk_codigoCarrinho` FOREIGN KEY ( `codigoCarrinho` ) REFERENCES `GroupCarrinho` ( `codigoCarrinho` );

USE EletronicosDB;
SHOW TABLES;
