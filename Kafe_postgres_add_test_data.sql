/* Вставка тестовых данных */

/* Вставка статусов */
INSERT INTO "Statuses" (title) VALUES ('Зарезервирован'); /* 1 */
INSERT INTO "Statuses" (title) VALUES ('Свободен'); /* 2 */
INSERT INTO "Statuses" (title) VALUES ('Занят, но не принят'); /* 3 */
INSERT INTO "Statuses" (title) VALUES ('Принят'); /* 4 */
INSERT INTO "Statuses" (title) VALUES ('Готовится'); /* 5 */
INSERT INTO "Statuses" (title) VALUES ('Одно из блюд готово'); /* 6 */
INSERT INTO "Statuses" (title) VALUES ('Ждет оплаты'); /* 7 */
INSERT INTO "Statuses" (title) VALUES ('Оплачен'); /* 8 */

/* Вставка роли персонала */
INSERT INTO "Role_staff" (title) VALUES ('Официант'); /* 1 */

/* Вставка персонала */
INSERT INTO "Staff" (last_name, first_name, phone, email, role, password, login) VALUES ('Пупкин', 'Василий', '8-800-555-35-35', 'vasya@tastyhouse.com', 1, 'qwerty', 'vasya_pupkin');

/* Вставка заказов */
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (1, 1, TIMESTAMP '2000-03-24 13:25:00', 3, 'На месте');
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (2, 1, TIMESTAMP '2006-02-02 12:20:00', 4, 'На месте');
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (3, 1, TIMESTAMP '2009-12-23 11:43:00', 5, 'На месте');
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (4, 1, TIMESTAMP '2003-05-15 20:53:00', 6, 'На месте');
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (5, 1, TIMESTAMP '2002-06-24 15:34:00', 3, 'На месте');
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (6, 1, TIMESTAMP '2005-07-23 13:54:00', 4, 'На месте');
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (7, 1, TIMESTAMP '2005-07-23 13:54:00', 5, 'На месте');
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (8, 1, TIMESTAMP '2005-07-23 13:54:00', 6, 'На месте');
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (9, 1, TIMESTAMP '2005-07-23 13:54:00', 3, 'На месте');
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (10, 1, TIMESTAMP '2005-07-23 13:54:00', 4, 'На месте');
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (11, 1, TIMESTAMP '2005-07-23 13:54:00', 5, 'На месте');
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (12, 1, TIMESTAMP '2005-07-23 13:54:00', 6, 'На месте');
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (13, 1, TIMESTAMP '2005-07-23 13:54:00', 3, 'На месте');
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (14, 1, TIMESTAMP '2005-07-23 13:54:00', 4, 'На месте');
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (15, 1, TIMESTAMP '2005-07-23 13:54:00', 5, 'На месте');
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (16, 1, TIMESTAMP '2005-07-23 13:54:00', 6, 'На месте');
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (17, 1, TIMESTAMP '2005-07-23 13:54:00', 3, 'На месте');
INSERT INTO "Orders" (table_number, user_id, date_orders, status, type) VALUES (18, 1, TIMESTAMP '2005-07-23 13:54:00', 4, 'На месте');

/* Вставка типов блюд */
INSERT INTO "TypeDish" (title) VALUES ('Салаты'); /* 1 */
INSERT INTO "TypeDish" (title) VALUES ('Закуски'); /* 2 */

/* Вставка блюд */
/* Ингредиенты, рецепт, масса, время - поставил кавычки как заглушку, можете вставлять туда что вам нужно */
INSERT INTO "Dishes" (name, price, ingredient, recipe, mass, type_dish, preparing_time) VALUES ('Цезарь с курицей', 250.9, '', '', '', 1, '');
INSERT INTO "Dishes" (name, price, ingredient, recipe, mass, type_dish, preparing_time) VALUES ('Салат с креветками', 200, '', '', '', 1, '');
INSERT INTO "Dishes" (name, price, ingredient, recipe, mass, type_dish, preparing_time) VALUES ('Салат из авокадо с семгой', 100, '', '', '', 1, '');
INSERT INTO "Dishes" (name, price, ingredient, recipe, mass, type_dish, preparing_time) VALUES ('Волованы с салатом из тунца', 350.71, '', '', '', 2, '');
INSERT INTO "Dishes" (name, price, ingredient, recipe, mass, type_dish, preparing_time) VALUES ('Свекольная закуска с сельдью', 280.7, '', '', '', 2, '');
INSERT INTO "Dishes" (name, price, ingredient, recipe, mass, type_dish, preparing_time) VALUES ('Маринованный сыр фета с оливками', 345.8, '', '', '', 2, '');

/* Вставка для таблицы связи блюдо-заказ */
/* NULL здесь как заглушка - вставляйте что считаете нужным */
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (3, 1, NULL, 'В ожидании');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (3, 2, NULL, 'Готовится');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (4, 1, NULL, 'Готово');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (4, 2, NULL, 'В ожидании');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (4, 3, NULL, 'Готовится');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (7, 1, NULL, 'В ожидании');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (7, 2, NULL, 'В ожидании');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (7, 3, NULL, 'Готовится');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (7, 4, NULL, 'Готовится');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (8, 1, NULL, 'Готово');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (8, 2, NULL, 'Готово');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (8, 3, NULL, 'В ожидании');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (8, 4, NULL, 'Готовится');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (8, 5, NULL, 'Готовится');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (11, 1, NULL, 'Готовится');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (11, 2, NULL, 'Готовится');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (11, 3, NULL, 'В ожидании');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (11, 4, NULL, 'В ожидании');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (11, 5, NULL, 'В ожидании');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (11, 6, NULL, 'Готовится');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (12, 1, NULL, 'Готово');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (15, 1, NULL, 'В ожидании');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (15, 2, NULL, 'В ожидании');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (16, 1, NULL, 'Готовится');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (16, 2, NULL, 'Готово');
INSERT INTO "DishesFromOrder" (order_id, dish_id, real_time, status) VALUES (16, 3, NULL, 'Готово');