create table PRODUTO(
  COD_PRODUTO int not null AUTO_INCREMENT,
  DES_PRODUTO varchar(30),
  STA_STATUS varchar(1),
  PRIMARY KEY ( COD_PRODUTO )
);

create table PRODUTO_COSIF (
  COD_PRODUTO int not null,
  COD_COSIF int not null,
  COD_CLASSIFICACAO varchar(6),
  STA_STATUS varchar(1),  PRIMARY KEY ( COD_PRODUTO, COD_COSIF )
);
