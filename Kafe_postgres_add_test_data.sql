/* Вставка тестовых данных */
TRUNCATE TABLE type_payment RESTART IDENTITY CASCADE;
TRUNCATE TABLE order_status RESTART IDENTITY CASCADE;
TRUNCATE TABLE role_staff RESTART IDENTITY CASCADE;
TRUNCATE TABLE staff RESTART IDENTITY CASCADE;
TRUNCATE TABLE orders RESTART IDENTITY CASCADE;
TRUNCATE TABLE type_dish RESTART IDENTITY CASCADE;
TRUNCATE TABLE dishes RESTART IDENTITY CASCADE;
TRUNCATE TABLE dishes_from_order RESTART IDENTITY CASCADE;
TRUNCATE TABLE dishes_and_staff RESTART IDENTITY CASCADE;
TRUNCATE TABLE restaurant_table RESTART IDENTITY CASCADE;
TRUNCATE TABLE table_status RESTART IDENTITY CASCADE;
TRUNCATE TABLE dish_status RESTART IDENTITY CASCADE;

/* Вставка статусов столиков */
INSERT INTO table_status (title) VALUES ('reserved'); /* 1 */
INSERT INTO table_status (title) VALUES ('no_one_here'); /* 2 */
INSERT INTO table_status (title) VALUES ('Занят, но не принят'); /* 3 */
INSERT INTO table_status (title) VALUES ('Принят'); /* 4 */
INSERT INTO table_status (title) VALUES ('in_process_of_cooking'); /* 5 */
INSERT INTO table_status (title) VALUES ('dish_is_ready'); /* 6 */
INSERT INTO table_status (title) VALUES ('Ждет оплаты'); /* 7 */
INSERT INTO table_status (title) VALUES ('Оплачен'); /* 8 */

/* Вставка статусов блюд */
INSERT INTO dish_status (title) VALUES ('В ожидании');
INSERT INTO dish_status (title) VALUES ('Готовится');
INSERT INTO dish_status (title) VALUES ('Готово');

/* Вставка ресторанных столиков */
INSERT INTO restaurant_table (table_status_id) VALUES (2);
INSERT INTO restaurant_table (table_status_id) VALUES (5);
INSERT INTO restaurant_table (table_status_id) VALUES (6);
INSERT INTO restaurant_table (table_status_id) VALUES (2);
INSERT INTO restaurant_table (table_status_id) VALUES (5);
INSERT INTO restaurant_table (table_status_id) VALUES (6);
INSERT INTO restaurant_table (table_status_id) VALUES (2);

/* Вставка типов оплаты */
INSERT INTO type_payment (title) VALUES ('По карте');
INSERT INTO type_payment (title) VALUES ('Наличными');

/* Вставка статусов заказов */
INSERT INTO order_status (title) VALUES ('reserved'); /* 1 */
INSERT INTO order_status (title) VALUES ('no_one_here'); /* 2 */
INSERT INTO order_status (title) VALUES ('Занят, но не принят'); /* 3 */
INSERT INTO order_status (title) VALUES ('Принят'); /* 4 */
INSERT INTO order_status (title) VALUES ('in_process_of_cooking'); /* 5 */
INSERT INTO order_status (title) VALUES ('dish_is_ready'); /* 6 */
INSERT INTO order_status (title) VALUES ('Ждет оплаты'); /* 7 */
INSERT INTO order_status (title) VALUES ('Оплачен'); /* 8 */

/* Вставка роли персонала */
INSERT INTO role_staff (title) VALUES ('Официант'); /* 1 */
INSERT INTO role_staff (title) VALUES ('Повар'); /* 2 */

/* Вставка персонала */
INSERT INTO staff (last_name, first_name, phone, email, password, login, role_staff_id) VALUES ('Федоров', 'Василий', '8-910-249-53-46', 'vasya@tastyhouse.com', 'qwerty', 'vasya_fedorov', 1);
INSERT INTO staff (last_name, first_name, phone, email, password, login, role_staff_id) VALUES ('Иванова', 'Юлия', '8-950-959-64-75', 'ivanova@tastyhouse.com', 'qwerty', 'ivanova', 2);

/* Вставка заказов */
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2000-03-24 13:25:00', 'На месте', 5, 1, 1);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2006-02-02 12:20:00', 'На месте', 5, 2, 1);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2009-12-23 11:43:00', 'На месте', 6, 1, 1);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2003-05-15 20:53:00', 'На месте', 2, 2, 2);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2002-06-24 15:34:00', 'На месте', 5, 1, 2);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 6, 2, 2);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 2, 1, 3);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 5, 2, 3);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 6, 1, 3);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 2, 2, 3);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 5, 1, 4);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 6, 2, 4);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 2, 1, 5);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 5, 2, 6);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 6, 1, 6);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 2, 2, 6);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 5, 1, 7);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 6, 2, 7);
INSERT INTO orders (date_orders, type, order_status_id, type_payment_id, restaurant_table_id) VALUES (TIMESTAMP '2018-07-23 18:15:00', 'На месте', 2, 1, 7);


/* Вставка типов блюд */
INSERT INTO type_dish (title) VALUES ('Салаты'); /* 1 */
INSERT INTO type_dish (title) VALUES ('Закуски'); /* 2 */
INSERT INTO type_dish (title) VALUES ('Горячее'); /* 3 */
INSERT INTO type_dish (title) VALUES ('Десерт'); /* 4 */
INSERT INTO type_dish (title) VALUES ('Рыба и морепродукты'); /* 5 */

/* Вставка блюд */
/* Ингредиенты, рецепт, масса, время - поставил кавычки как заглушку, можете вставлять туда что вам нужно */ 

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, img_url, type_dish_id) VALUES (
'Цезарь с курицей', 250.9,
'Чеснок луковица-2 г <br>
Салат Романо-35 г <br>
Помидоры-45 г <br>
Сыр, пармезан-15 г <br>
Куриная грудка-70 г <br>
Хлеб белый-20 г <br>
Масло подсолнечное-5 г <br>
Соль поваренная пищевая-1 г <br>
Соус для салата "Цезарь"-25 г',
'1. Белый хлеб очищаем от корочки и нарезаем кубиками с гранью в 1 см. <br>
2. Оливковое масло наливаем на сковороду (чтобы еле-еле покрывало дно). <br>
3. Очищаем чеснок, разрезаем его на несколько пластинок и обжариваем слегка в масле. <br>
4. Обжариваем в масле хлебные кубики - наши будущие крутоны - до приятной золотинки. <br>
5. Нарезаем филе, желательно кубиками такого же размера, т.е. в 1 см. <br>
6. Курицу обжариваем, солим, перчим. <br>
7. На крупной терке натираем “Пармезан”.',
 '200 г.', TIME '00:40:00', '/img/cezar.jpg', 1);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, img_url, type_dish_id) VALUES (
'Салат с креветками', 200,
'Очищенные вареные креветки 150 г <br>
Помидоры 1 штука <br>
Оливковое масло по вкусу <br>
Огурцы 0,5 штуки <br>
Соль по вкусу',
 '1.Помидоры и огурцы нарежьте кубиками. <br>
2. Все, вместе с креветками, смешайте. Добавьте соль по вкусу и заправьте оливковым маслом.',
 '250 г.', TIME '00:50:00', '/img/krevetki.jpg', 1);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, img_url, type_dish_id) VALUES (
'Салат с красной рыбой', 150,
'Слабосоленая семга 50 г <br>
Сметана по вкусу <br>
Перепелиное яйцо 3 штуки <br>
Укроп по вкусу <br>
Зеленый лук по вкусу <br>
Огурцы 1 штука',
'1. Семгу нарезать кусочками. <br>
2. Яйца отварить и нарезать небольшими кубиками. <br>
3. Свежие огурцы очистить и нарезать кубиками. <br>
4. Смешайте все ингредиенты и заправьте сметаной. <br>
5. Добавьте соль, укроп и зеленый лук.',
'200', TIME '00:25:00', '/img/salat-s-krasnoj-ryboj.jpg', 1);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, img_url, type_dish_id) VALUES (
'Бутерброды с красной икрой и сыром', 100,
'Батон 2 куска <br>
Икра красная 20 г <br>
Зелень укропа  5 г <br>
Масло сливочное 20 г <br>
Сыр пармезан 40 г',
'1.Сливочное масло заранее достать из холодильника и дать ему оттаять около 15 минут. <br>
2.Намазать ровным слоем масла каждый ломтик хлеба.<br>
3.Поверх масла на каждый ломтик уложить по ложечке красной икры.<br>
4.Украсить каждый бутерброд веточкой укропа.',
'100 г', TIME '00:10:00', '/img/buterbrody-s-krasnoj-ikroj-i-syrom.jpg', 2);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, img_url, type_dish_id) VALUES (
'Новогодний десерт "Радость обезьянки"', 200,
'Сливочное мороженое 200 гр.<br>
Молоко	200 мл.<br>
Бананы	2 шт.<br>
Лимонный сок<br>
Шоколадная стружка',
'1. Бананы очистить, мороженое нарезать кубиками. <br>
2. Сложить в чашу блендера и взбить мороженое с бананами. <br>
3. Добавить сок лимона и молоко.<br>
4. Разлить по бокалам.<br>
5. Присыпать шоколадом и убрать в холод до подачи.',
'250 мл', TIME '00:10:00', '/img/novogodnij-desert-radost-obezyanki.jpg', 4);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, img_url, type_dish_id) VALUES (
'Тирамису', 300,
'Яйцо куриное 0,5 штуки <br>
Сыр маскарпоне 50 г <br>
Печенье савоярди 7,5 штуки <br>
Сахарная пудра 1 столовая ложка <br>
Крепкий кофе 2,5 столовые ложки <br>
Кофейный ликер 2 чайные ложки <br>
Какао-порошок 2 столовые ложки',
'1. Выложить маскарпоне в широкую миску и интенсивно взбить лопаткой. <br>
2. Отделить белки от желтков. В одной миске взбить желтки и сахарную пудру и получившуюся массу постепенно добавить к маскарпоне. <br>
3. Отдельно взбить белки и затем — по одной ложке — аккуратно вмешать в смесь желтков и маскарпоне. <br>
4. Смешать охлажденный крепкий кофе с четырьмя столовыми ложками рома в широкой тарелке. <br>
5. Аккуратно обмакнуть в смесь кофе и рома треть или половину савоярди. <br>
6. Пропитанное печенье уложить на дно формы, сверху залить третью крема из маскарпоне. <br>
7. Окунуть вторую порцию печенья в кофейную смесь и уложить на крем. Сверху выложить второй слой крема из маскарпоне. <br>
8. Затем — оставшееся пропитанное печенье.',
'200 г', TIME '00:15:00', '/img/tiramisu.jpg', 4);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, img_url, type_dish_id) VALUES ('Курица с картошкой «Праздничная»', 200,
'Курица 0,4 кг  <br>
Картофель 1,5 штуки <br>
Чеснок 1,5 зубчика <br>
Соль по вкусу <br>
Перец черный молотый по вкусу <br>
Майонез по вкусу',
'1. Смешать майонез и измельченный чеснок. <br>
2. Курицу промыть и натереть солью, перцем и майонезом внутри и снаружи. <br>
3. Картофель очистить, нарезать кружочками и отварить в подсоленной воде до полуготовности. <br>
4. В глубокий противень выложить курицу, по краям разложить картошку. <br>
5. Запекать в духовке при 200 градусах до готовности курицы.',
'350 г', TIME '01:00:00', '/img/kurica-s-kartoshkoj-prazdnichnaya.jpg', 3);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, img_url, type_dish_id) VALUES (
'Мини-пиццы', 100,
'Чиабатта 0,5 штуки <br>
Сыр 100 г <br>
Бекон 100 г <br>
Укроп по вкусу  <br>
Зеленый лук по вкусу <br>
Сметана 20%-ная 2 столовые ложки <br>
Яйцо куриное 1,5 штуки <br>
Красный сладкий перец 0,5 штуки',
'1. Мелко нарезать болгарский перец и бекон, перемешать в глубокой посуде. <br>
2. Сыр натереть на средней терке или мелко нарезать ломтиками. <br>
3. Мелко порубить зелень. <br>
4. Все нарезанные ингредиенты смешать в глубокой посуде. <br>
5. Разбить в получившуюся смесь 3 яйца и тщательно перемешать до однородности яиц.  <br>
6. Добавить сметану и еще раз тщательно перемешать. <br>
7. Чиабатту нарезать кусочками толщиной 1–1,5 см, и выложить на противень (на бумагу для выпекания). <br>
8. На каждый кусок хлеба выложить приготовленную смесь, разделить поровну между всеми кусками. <br>
9. Выпекать при температуре 200–220 градусов 10–15 минут. ',
'400 г', TIME '00:30:00', '/img/mini-piccy.jpg', 3);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, img_url, type_dish_id) VALUES (
'Минтай в духовке', 150,
'Лук репчатый 0,5 штуки <br>
Морковь 0,5 штуки <br>
Пшеничная мука 1,5 чайные ложки <br>
Растительное масло 1,5 чайные ложки <br>
Сметана 20%-ная 1 чайная ложка <br>
Майонез 1,5 чайные ложки <br>
Специи по вкусу <br>
Минтай 0,1 кг',
'1. Лук нарезать полукольцами, обжарить на растительном масле. Выложить на противень ровным слоем. <br>
2. Морковь натереть на крупной терке, обжарить на растительном масле. Выложить на лук. <br>
3. Филе минтая нарезать большими кусочками и обвалять в муке, обжарить с каждой стороны и выложить на морковь. <br>
4. Смешать майонез, сметану и специи и обмазать сверху минтай. <br>
5. Запекать в духовке при 180 градусах 20 минут.',
'250 г', TIME '00:40:00', '/img/mintaj-v-duhovke.jpg', 5);

INSERT INTO "dishes" (name, price, ingredient, recipe, mass, preparing_time, img_url, type_dish_id) VALUES (
'Грибной суп из шампиньонов', 200,
'Шампиньоны 100 г <br>
Картофель 1 штука <br>
Лук репчатый 0,5 штуки <br>
Сливки 20%-ные 5 столовых ложек <br>
Соль по вкусу <br>
Перец черный молотый по вкусу',
'1. Поставить вариться 4–5 средних картофелин. <br>
2. Лук мелко нарезать, грибы нарезать дольками. Обжарить лук на сливочном масле до прозрачности, потом добавить грибы. <br>
3. Добавить грибы с луком в картофель, добавить сливки, соль, перец и измельчить блендером. <br>
4. Подавать с гренками.',
'200 г', TIME '00:30:00', '/img/gribnoj-sup-iz-shampinonov.jpg', 5);

/* Вставка для связующей таблицы между блюдами и персоналом */
INSERT INTO dishes_and_staff (dishes_id, staff_id) VALUES (1, 1);
INSERT INTO dishes_and_staff (dishes_id, staff_id) VALUES (2, 1);
INSERT INTO dishes_and_staff (dishes_id, staff_id) VALUES (3, 1);
INSERT INTO dishes_and_staff (dishes_id, staff_id) VALUES (4, 1);
INSERT INTO dishes_and_staff (dishes_id, staff_id) VALUES (5, 1);
INSERT INTO dishes_and_staff (dishes_id, staff_id) VALUES (6, 2);
INSERT INTO dishes_and_staff (dishes_id, staff_id) VALUES (7, 2);
INSERT INTO dishes_and_staff (dishes_id, staff_id) VALUES (8, 2);
INSERT INTO dishes_and_staff (dishes_id, staff_id) VALUES (9, 2);
INSERT INTO dishes_and_staff (dishes_id, staff_id) VALUES (10, 2);

/* Вставка для таблицы связи блюдо-заказ */
/* NULL здесь как заглушка - вставляйте что считаете нужным */
INSERT INTO dishes_from_order (real_time, dish_status_id, orders_id, dishes_id) VALUES (TIME '01:00:00', 1, 1, 1);
INSERT INTO dishes_from_order (real_time, dish_status_id, orders_id, dishes_id) VALUES (TIME '00:30:00', 2, 1, 2);
INSERT INTO dishes_from_order (real_time, dish_status_id, orders_id, dishes_id) VALUES (TIME '00:50:00', 3, 1, 3);
INSERT INTO dishes_from_order (real_time, dish_status_id, orders_id, dishes_id) VALUES (TIME '01:20:00', 1, 2, 4);
INSERT INTO dishes_from_order (real_time, dish_status_id, orders_id, dishes_id) VALUES (TIME '01:00:00', 2, 2, 5);
INSERT INTO dishes_from_order (real_time, dish_status_id, orders_id, dishes_id) VALUES (TIME '02:00:00', 1, 2, 6);
INSERT INTO dishes_from_order (real_time, dish_status_id, orders_id, dishes_id) VALUES (TIME '01:40:00', 1, 3, 7);
INSERT INTO dishes_from_order (real_time, dish_status_id, orders_id, dishes_id) VALUES (TIME '00:40:00', 2, 3, 8);
INSERT INTO dishes_from_order (real_time, dish_status_id, orders_id, dishes_id) VALUES (TIME '02:00:00', 2, 3, 9);
INSERT INTO dishes_from_order (real_time, dish_status_id, orders_id, dishes_id) VALUES (TIME '01:10:00', 3, 3, 10);