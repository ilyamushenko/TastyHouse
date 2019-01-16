/* Вставка тестовых данных */

/* Вставка статусов */
INSERT INTO "statuses" (title) VALUES ('Зарезервирован'); /* 1 */
INSERT INTO "statuses" (title) VALUES ('Свободен'); /* 2 */
INSERT INTO "statuses" (title) VALUES ('Занят, но не принят'); /* 3 */
INSERT INTO "statuses" (title) VALUES ('Принят'); /* 4 */
INSERT INTO "statuses" (title) VALUES ('Готовится'); /* 5 */
INSERT INTO "statuses" (title) VALUES ('Одно из блюд готово'); /* 6 */
INSERT INTO "statuses" (title) VALUES ('Ждет оплаты'); /* 7 */
INSERT INTO "statuses" (title) VALUES ('Оплачен'); /* 8 */

/* Вставка роли персонала */
INSERT INTO "role_staff" (title) VALUES ('Официант'); /* 1 */

/* Вставка персонала */
INSERT INTO "staff" (last_name, first_name, phone, email, password, login) VALUES ('Пупкин', 'Василий', '8-800-555-35-35', 'vasya@tastyhouse.com', 'qwerty', 'vasya_pupkin');

/* Вставка заказов */
INSERT INTO "orders" (table_number, date_orders, type) VALUES (1, TIMESTAMP '2000-03-24 13:25:00', 'На месте');
INSERT INTO "orders" (table_number, date_orders, type) VALUES (2, TIMESTAMP '2006-02-02 12:20:00', 'На месте');
INSERT INTO "orders" (table_number, date_orders, type) VALUES (3, TIMESTAMP '2009-12-23 11:43:00', 'На месте');
INSERT INTO "orders" (table_number, date_orders, type) VALUES (4, TIMESTAMP '2003-05-15 20:53:00', 'На месте');
INSERT INTO "orders" (table_number, date_orders, type) VALUES (5, TIMESTAMP '2002-06-24 15:34:00', 'На месте');
INSERT INTO "orders" (table_number, date_orders, type) VALUES (6, TIMESTAMP '2005-07-23 13:54:00', 'На месте');
INSERT INTO "orders" (table_number, date_orders, type) VALUES (7, TIMESTAMP '2005-07-23 13:54:00', 'На месте');
INSERT INTO "orders" (table_number, date_orders, type) VALUES (8, TIMESTAMP '2005-07-23 13:54:00', 'На месте');
INSERT INTO "orders" (table_number, date_orders, type) VALUES (9, TIMESTAMP '2005-07-23 13:54:00', 'На месте');
INSERT INTO "orders" (table_number, date_orders, type) VALUES (10, TIMESTAMP '2005-07-23 13:54:00', 'На месте');
INSERT INTO "orders" (table_number, date_orders, type) VALUES (11, TIMESTAMP '2005-07-23 13:54:00', 'На месте');
INSERT INTO "orders" (table_number, date_orders, type) VALUES (12, TIMESTAMP '2005-07-23 13:54:00', 'На месте');
INSERT INTO "orders" (table_number, date_orders, type) VALUES (13, TIMESTAMP '2005-07-23 13:54:00', 'На месте');
INSERT INTO "orders" (table_number, date_orders, type) VALUES (14, TIMESTAMP '2005-07-23 13:54:00', 'На месте');
INSERT INTO "orders" (table_number, date_orders, type) VALUES (15, TIMESTAMP '2005-07-23 13:54:00', 'На месте');
INSERT INTO "orders" (table_number, date_orders, type) VALUES (16, TIMESTAMP '2005-07-23 13:54:00', 'На месте');
INSERT INTO "orders" (table_number, date_orders, type) VALUES (17, TIMESTAMP '2005-07-23 13:54:00', 'На месте');
INSERT INTO "orders" (table_number, date_orders, type) VALUES (18, TIMESTAMP '2005-07-23 13:54:00', 'На месте');

/* Вставка типов блюд */
INSERT INTO "type_dish" (title) VALUES ('Салаты'); /* 1 */
INSERT INTO "type_dish" (title) VALUES ('Закуски'); /* 2 */

/* Вставка блюд */
/* Ингредиенты, рецепт, масса, время - поставил кавычки как заглушку, можете вставлять туда что вам нужно */
INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time) VALUES ('Цезарь с курицей', 250.9, '', '', '', '40');
INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time) VALUES ('Салат с креветками', 200, '', '', '', '50');
INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time) VALUES ('Салат из авокадо с семгой', 100, '', '', '', '30');
INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time) VALUES ('Волованы с салатом из тунца', 350.71, '', '', '', '20');
INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time) VALUES ('Свекольная закуска с сельдью', 280.7, '', '', '', '10');
INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time) VALUES ('Маринованный сыр фета с оливками', 345.8, '', '', '', '15');

/* Вставка для таблицы связи блюдо-заказ */
/* NULL здесь как заглушка - вставляйте что считаете нужным */
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'В ожидании');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'Готовится');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'Готово');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'В ожидании');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'Готовится');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'В ожидании');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'В ожидании');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'Готовится');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'Готовится');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'Готово');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'Готово');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'В ожидании');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'Готовится');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'Готовится');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'Готовится');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'Готовится');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'В ожидании');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'В ожидании');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'В ожидании');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'Готовится');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'Готово');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'В ожидании');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'В ожидании');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'Готовится');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'Готово');
INSERT INTO "dishes_from_order" (real_time, status) VALUES (NULL, 'Готово');