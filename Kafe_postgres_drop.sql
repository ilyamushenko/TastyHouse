ALTER TABLE "staff" DROP CONSTRAINT IF EXISTS "fkslcqhfm3vyktb99sf1819mtly";

ALTER TABLE "orders" DROP CONSTRAINT IF EXISTS "fk3j4cberxjut2arquh1ox3jf06";

ALTER TABLE "orders" DROP CONSTRAINT IF EXISTS "fk4ery255787xl56k025fyxrqe9";

ALTER TABLE "dishes" DROP CONSTRAINT IF EXISTS "fk5c13px7nbqy2yx8n5f212rgs7";

ALTER TABLE "dishes_from_order" DROP CONSTRAINT IF EXISTS "fk77p12o4qhyq9xxbpg0kybobjs";

ALTER TABLE "dishes_from_order" DROP CONSTRAINT IF EXISTS "fkpj5bhnfamlhqb72qbjk4wlvjn";

DROP TABLE IF EXISTS "staff";

DROP TABLE IF EXISTS "orders";

DROP TABLE IF EXISTS "dishes";

DROP TABLE IF EXISTS "dishes_from_order";

DROP TABLE IF EXISTS "statuses";

DROP TABLE IF EXISTS "type_dish";

DROP TABLE IF EXISTS "role_staff";

