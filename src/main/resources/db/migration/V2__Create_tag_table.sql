CREATE TABLE "tag"
(
 "tag_id" serial NOT NULL,
 "nome"          varchar(200) NOT NULL,
 CONSTRAINT "PK_tag" PRIMARY KEY ( "tag_id" )
);
