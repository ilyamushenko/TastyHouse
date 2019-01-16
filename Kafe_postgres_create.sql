CREATE TABLE "Staff" (
	"id" serial NOT NULL,
	"last_name" varchar NOT NULL,
	"first_name" varchar NOT NULL,
	"phone" varchar NOT NULL,
	"email" varchar NOT NULL,
	"role" integer NOT NULL,
	"password" varchar NOT NULL,
	"login" varchar NOT NULL,
	CONSTRAINT Staff_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Orders" (
	"id" serial NOT NULL,
	"table_number" serial,
	"user_id" integer NOT NULL,
	"date_orders" timestamp NOT NULL,
	"status" integer NOT NULL,
	"type" varchar NOT NULL,
	CONSTRAINT Orders_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Dishes" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"price" FLOAT NOT NULL,
	"ingredient" varchar NOT NULL,
	"recipe" TEXT NOT NULL,
	"mass" varchar NOT NULL,
	"type_dish" integer NOT NULL,
	"preparing_time" varchar NOT NULL,
	CONSTRAINT Dishes_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "DishesFromOrder" (
	"id" serial NOT NULL,
	"order_id" integer NOT NULL,
	"dish_id" integer NOT NULL,
	"real_time" TIME,
	"status" varchar NOT NULL,
	CONSTRAINT DishesFromOrder_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Statuses" (
	"id" serial NOT NULL,
	"title" varchar NOT NULL,
	CONSTRAINT Statuses_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "TypeDish" (
	"id" serial NOT NULL,
	"title" varchar NOT NULL,
	CONSTRAINT TypeDish_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Role_staff" (
	"id" serial NOT NULL,
	"title" varchar NOT NULL,
	CONSTRAINT Role_staff_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "Staff" ADD CONSTRAINT "Staff_fk0" FOREIGN KEY ("role") REFERENCES "Role_staff"("id");

ALTER TABLE "Orders" ADD CONSTRAINT "Orders_fk0" FOREIGN KEY ("user_id") REFERENCES "Staff"("id");
ALTER TABLE "Orders" ADD CONSTRAINT "Orders_fk1" FOREIGN KEY ("status") REFERENCES "Statuses"("id");

ALTER TABLE "Dishes" ADD CONSTRAINT "Dishes_fk0" FOREIGN KEY ("type_dish") REFERENCES "TypeDish"("id");

ALTER TABLE "DishesFromOrder" ADD CONSTRAINT "DishesFromOrder_fk0" FOREIGN KEY ("order_id") REFERENCES "Orders"("id");
ALTER TABLE "DishesFromOrder" ADD CONSTRAINT "DishesFromOrder_fk1" FOREIGN KEY ("dish_id") REFERENCES "Dishes"("id");