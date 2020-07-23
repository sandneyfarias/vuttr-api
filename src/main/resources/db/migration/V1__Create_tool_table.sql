CREATE TABLE "tool"
(
	 "tool_id" serial NOT NULL,
	 "title"          varchar(200) NOT NULL,
	 "link"           varchar(200) NOT NULL,
	 "description"     text NOT NULL,
	 CONSTRAINT "PK_ferramenta" PRIMARY KEY ( "tool_id" )
);
