
CREATE SCHEMA IF NOT EXISTS marketplace;

DROP TABLE IF EXISTS marketplace."Order" CASCADE;

CREATE TABLE marketplace."Order" (
       "ID" UUID DEFAULT public.uuid_generate_v4() NOT NULL UNIQUE PRIMARY KEY,
       "OrderNumber" varchar(20) NOT NULL,
       "CustomerId" UUID NOT NULL,
       "TotalPrice"  DECIMAL,
       "CreatorId" character varying(255),
       "SysInsertTS" timestamp without time zone NOT NULL,
       "SysInsertPrg" character varying(50),
       "SysUpdateTS" timestamp without time zone,
       "SysUpdatePrg" character varying(50),
      CONSTRAINT customer_fkey FOREIGN KEY ("CustomerId") REFERENCES protostellar."Customer"("ID")
);

DROP TABLE IF EXISTS marketplace."OrderLine" CASCADE;

CREATE TABLE marketplace."OrderLine" (
       "ID" UUID DEFAULT public.uuid_generate_v4() NOT NULL UNIQUE PRIMARY KEY,
       "OrderId" UUID NOT NULL,
       "ProductId" UUID NOT NULL,
       "ArticleId" UUID NOT NULL,
       "PricePerArticle"  DECIMAL,
       "Quantity" INTEGER,
       "TotalPrice" DECIMAL,
       "SysInsertTS" timestamp without time zone NOT NULL,
       "SysUpdateTS" timestamp without time zone,
     CONSTRAINT order_fkey FOREIGN KEY ("OrderId") REFERENCES marketplace."Order"("ID"),
     CONSTRAINT product_fkey FOREIGN KEY ("ProductId") REFERENCES protostellar."Product"("ID"),
     CONSTRAINT article_fkey FOREIGN KEY ("ArticleId") REFERENCES protostellar."Article"("ID")
);
