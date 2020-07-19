CREATE TABLE "ferramenta_tag"
(
 "ferramenta_id" integer NOT NULL,
 "tag_id"        integer NOT NULL,
 CONSTRAINT "PK_fer_tag" PRIMARY KEY ( "ferramenta_id", "tag_id" ),
 CONSTRAINT "FK_fer_tag_ferramenta" FOREIGN KEY ( "ferramenta_id" ) REFERENCES "ferramenta" ( "ferramenta_id" ),
 CONSTRAINT "FK_fer_tag_tag" FOREIGN KEY ( "tag_id" ) REFERENCES "tag" ( "tag_id" )
);

CREATE INDEX "fkIdx_fer_tag_ferramenta" ON "ferramenta_tag"
(
 "ferramenta_id"
);

CREATE INDEX "fkIdx_fer_tag_tag" ON "ferramenta_tag"
(
 "tag_id"
);
