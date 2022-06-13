ALTER TABLE protostellar."Goods"
  ADD COLUMN IF NOT EXISTS "CustomsTariffID" int8 NULL;


ALTER TABLE protostellar."Goods" DROP CONSTRAINT IF EXISTS goods_fk;
ALTER TABLE protostellar."Goods"
  ADD CONSTRAINT goods_fk FOREIGN KEY ("CustomsTariffID") REFERENCES protostellar."CustomsTariff" ("ID") ON DELETE RESTRICT ON UPDATE RESTRICT;
