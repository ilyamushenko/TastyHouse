﻿/* Вставка тестовых данных */



/* Вставка для таблицы связи блюдо-заказ */

/* NULL здесь как заглушка - вставляйте что считаете нужным */

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:00:00', 'В ожидании');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '00:30:00', 'Готовится');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '00:50:00', 'Готово');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:20:00', 'В ожидании');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:00:00', 'Готовится');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '02:00:00', 'В ожидании');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:40:00', 'В ожидании');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '00:40:00', 'Готовится');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '02:00:00', 'Готовится');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:10:00', 'Готово');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:15:00', 'Готово');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:20:00', 'В ожидании');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:30:00', 'Готовится');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '02:00:00', 'Готовится');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '02:30:00', 'Готовится');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:45:00', 'Готовится');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:50:00', 'В ожидании');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:55:00', 'В ожидании');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:25:00', 'В ожидании');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:15:00', 'Готовится');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:10:00', 'Готово');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:05:00', 'В ожидании');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:40:00', 'В ожидании');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:20:00', 'Готовится');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:10:00', 'Готово');

INSERT INTO "dishes_from_order" (real_time, status) VALUES (TIME '01:05:00', 'Готово');



/* Вставка статусов */

INSERT INTO "statuses" (title) VALUES ('reserved'); /* 1 */

INSERT INTO "statuses" (title) VALUES ('no_one_here'); /* 2 */

INSERT INTO "statuses" (title) VALUES ('Занят, но не принят'); /* 3 */

INSERT INTO "statuses" (title) VALUES ('Принят'); /* 4 */

INSERT INTO "statuses" (title) VALUES ('in_process_of_cooking'); /* 5 */

INSERT INTO "statuses" (title) VALUES ('dish_is_ready'); /* 6 */

INSERT INTO "statuses" (title) VALUES ('Ждет оплаты'); /* 7 */

INSERT INTO "statuses" (title) VALUES ('Оплачен'); /* 8 */



/* Вставка роли персонала */

INSERT INTO "role_staff" (title) VALUES ('Официант'); /* 1 */



/* Вставка персонала */

INSERT INTO "staff" (last_name, first_name, phone, email, password, login, role_staff_id) VALUES ('Федоров', 'Василий', '8-910-249-53-46', 'vasya@tastyhouse.com', 'qwerty', 'vasya_fedorov', 1);



/* Вставка заказов */

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (1, TIMESTAMP '2000-03-24 13:25:00', 'На месте', 1, 1, 5);

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (2, TIMESTAMP '2006-02-02 12:20:00', 'На месте', 2, 1, 5);

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (3, TIMESTAMP '2009-12-23 11:43:00', 'На месте', 3, 1, 6);

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (4, TIMESTAMP '2003-05-15 20:53:00', 'На месте', 4, 1, 2);

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (5, TIMESTAMP '2002-06-24 15:34:00', 'На месте', 5, 1, 5);

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (6, TIMESTAMP '2005-07-23 13:54:00', 'На месте', 6, 1, 6);

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (7, TIMESTAMP '2005-07-23 13:54:00', 'На месте', 7, 1, 2);

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (8, TIMESTAMP '2005-07-23 13:54:00', 'На месте', 8, 1, 5);

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (9, TIMESTAMP '2005-07-23 13:54:00', 'На месте', 9, 1, 6);

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (10, TIMESTAMP '2005-07-23 13:54:00', 'На месте', 10, 1, 2);

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (11, TIMESTAMP '2005-07-23 13:54:00', 'На месте', 11, 1, 5);

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (12, TIMESTAMP '2005-07-23 13:54:00', 'На месте', 12, 1, 6);

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (13, TIMESTAMP '2005-07-23 13:54:00', 'На месте', 13, 1, 2);

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (14, TIMESTAMP '2005-07-23 13:54:00', 'На месте', 14, 1, 5);

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (15, TIMESTAMP '2005-07-23 13:54:00', 'На месте', 15, 1, 6);

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (16, TIMESTAMP '2005-07-23 13:54:00', 'На месте', 16, 1, 2);

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (17, TIMESTAMP '2005-07-23 13:54:00', 'На месте', 17, 1, 5);

INSERT INTO "orders" (table_number, date_orders, type, dishes_from_order_id, staff_id, statuses_id) VALUES (18, TIMESTAMP '2005-07-23 13:54:00', 'На месте', 18, 1, 6);



/* Вставка типов блюд */

INSERT INTO "type_dish" (title) VALUES ('Салаты'); /* 1 */

INSERT INTO "type_dish" (title) VALUES ('Закуски'); /* 2 */

INSERT INTO "type_dish" (title) VALUES ('Горячее'); /* 3 */

INSERT INTO "type_dish" (title) VALUES ('Десерт'); /* 4 */

INSERT INTO "type_dish" (title) VALUES ('Рыба и морепродукты'); /* 5 */



/* Вставка блюд */

/* Ингредиенты, рецепт, масса, время - поставил кавычки как заглушку, можете вставлять туда что вам нужно */

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, dishes_from_order_id, type_dish_id) VALUES ('Цезарь с курицей', 250.9, '', '', '', TIME '00:40:00', 1, 1);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, dishes_from_order_id, type_dish_id) VALUES ('Салат с креветками', 200, '', '', '', TIME '00:50:00', 2, 1);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, dishes_from_order_id, type_dish_id) VALUES ('Салат с красной рыбой', 150, '', '', '', TIME '00:25:00', 3, 1);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, dishes_from_order_id, type_dish_id) VALUES ('Бутерброды с красной икрой и сыром', 100, '', '', '', TIME '00:10:00', 2, 2);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, dishes_from_order_id, type_dish_id) VALUES ('Новогодний десерт «Радость обезьянки»', 200, '', '', '', TIME '00:10:00', 2, 4);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, dishes_from_order_id, type_dish_id) VALUES ('Тирамису', 300, '', '', '', TIME '00:15:00', 3, 4);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, dishes_from_order_id, type_dish_id) VALUES ('Курица с картошкой «Праздничная»', 200, '', '', '', TIME '01:00:00', 1, 3);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, dishes_from_order_id, type_dish_id) VALUES ('Мини-пиццы', 100, '', '', '', TIME '00:30:00', 1, 3);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, dishes_from_order_id, type_dish_id) VALUES ('Минтай в духовке', 150, '', '', '', TIME '00:40:00', 3, 5);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, dishes_from_order_id, type_dish_id) VALUES ('Грибной суп из шампиньонов', 200, '', '', '', TIME '00:30:00', 2, 5);