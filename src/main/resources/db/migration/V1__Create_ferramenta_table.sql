CREATE TABLE "ferramenta"
(
	 "ferramenta_id" serial NOT NULL,
	 "nome"          varchar(200) NOT NULL,
	 "url"           varchar(200) NOT NULL,
	 "descricao"     text NOT NULL,
	 CONSTRAINT "PK_ferramenta" PRIMARY KEY ( "ferramenta_id" )
);
