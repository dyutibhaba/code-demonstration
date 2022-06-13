CREATE SCHEMA IF NOT EXISTS transport_order;

ALTER TABLE IF EXISTS protostellar."Order" SET SCHEMA transport_order;

ALTER TABLE IF EXISTS protostellar."OrderItem" SET SCHEMA transport_order;

ALTER TABLE IF EXISTS protostellar."OrderItemDangerousGoods" SET SCHEMA transport_order;

ALTER TABLE IF EXISTS protostellar."OrderItemGoods" SET SCHEMA transport_order;

ALTER TABLE IF EXISTS protostellar."OrderItemSeals" SET SCHEMA transport_order;

ALTER TABLE IF EXISTS protostellar."OrderItemVoyageSegment" SET SCHEMA transport_order;
ALTER TABLE transport_order."OrderItemVoyageSegment" DROP COLUMN IF EXISTS "OceanCarrierPickupReference";

ALTER TABLE IF EXISTS protostellar."OrderVoyageSegment" SET SCHEMA transport_order;

ALTER TABLE IF EXISTS protostellar."OrderVoyageSegmentTrain" SET SCHEMA transport_order;

ALTER TABLE IF EXISTS protostellar."OrderVoyageSegmentTrainWagons" SET SCHEMA transport_order;

ALTER TABLE IF EXISTS protostellar."VoyageOrderSegmentRejects" SET SCHEMA transport_order;

ALTER TABLE IF EXISTS protostellar."VoyageOrderSegment_LoadFromMail" SET SCHEMA transport_order;

ALTER TABLE IF EXISTS protostellar."VoyageOrderSegment_LoadHistory" SET SCHEMA transport_order;

ALTER TABLE IF EXISTS protostellar."VoyageOrder_Hist" SET SCHEMA transport_order;
