CREATE TABLE "tool_tag"
(
 "tool_id" integer NOT NULL,
 "tag_id"        integer NOT NULL,
 CONSTRAINT "PK_tool_tag" PRIMARY KEY ( "tool_id", "tag_id" ),
 CONSTRAINT "FK_tool_tag_tool" FOREIGN KEY ( "tool_id" ) REFERENCES "tool" ( "tool_id" ),
 CONSTRAINT "FK_tool_tag_tag" FOREIGN KEY ( "tag_id" ) REFERENCES "tag" ( "tag_id" )
);

CREATE INDEX "fkIdx_tool_tag_tool" ON "tool_tag"
(
 "tool_id"
);

CREATE INDEX "fkIdx_tool_tag_tag" ON "tool_tag"
(
 "tag_id"
);
