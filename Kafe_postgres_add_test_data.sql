/* Вставка тестовых данных */
TRUNCATE TABLE type_payment RESTART IDENTITY CASCADE;
TRUNCATE TABLE order_status RESTART IDENTITY CASCADE;
TRUNCATE TABLE role_staff RESTART IDENTITY CASCADE;
TRUNCATE TABLE staff RESTART IDENTITY CASCADE;
TRUNCATE TABLE restaurant_order RESTART IDENTITY CASCADE;
TRUNCATE TABLE type_dish RESTART IDENTITY CASCADE;
TRUNCATE TABLE dish RESTART IDENTITY CASCADE;
TRUNCATE TABLE dishes_from_order RESTART IDENTITY CASCADE;
TRUNCATE TABLE dishes_and_staff RESTART IDENTITY CASCADE;
TRUNCATE TABLE restaurant_table RESTART IDENTITY CASCADE;
TRUNCATE TABLE table_status RESTART IDENTITY CASCADE;
TRUNCATE TABLE dish_status RESTART IDENTITY CASCADE;
TRUNCATE TABLE ingredient RESTART IDENTITY CASCADE;
TRUNCATE TABLE food_ingredients RESTART IDENTITY CASCADE;

/* Вставка статусов столиков */
INSERT INTO table_status (title)
VALUES ('free'); /* Свободен */
INSERT INTO table_status (title)
VALUES ('busy_need_to_bring'); /* Занят, нужно отнести */
INSERT INTO table_status (title)
VALUES ('busy_need_attention'); /* Занят, требует внимания */
INSERT INTO table_status (title)
VALUES ('busy_no_need_attention');
/* Занят, не требует внимания */

/* Вставка статусов блюд */
INSERT INTO dish_status (title)
VALUES ('В ожидании'); /* В ожидании */
INSERT INTO dish_status (title)
VALUES ('Готовится'); /* Готовится */
INSERT INTO dish_status (title)
VALUES ('Готово');
/* Готово */

/* Вставка ресторанных столиков */
INSERT INTO restaurant_table (table_status_id)
VALUES (1);
INSERT INTO restaurant_table (table_status_id)
VALUES (2);
INSERT INTO restaurant_table (table_status_id)
VALUES (3);
INSERT INTO restaurant_table (table_status_id)
VALUES (4);
INSERT INTO restaurant_table (table_status_id)
VALUES (1);
INSERT INTO restaurant_table (table_status_id)
VALUES (2);
INSERT INTO restaurant_table (table_status_id)
VALUES (3);

/* Вставка типов оплаты */
INSERT INTO type_payment (title)
VALUES ('По карте');
INSERT INTO type_payment (title)
VALUES ('Наличными');

/* Вставка статусов заказов */
INSERT INTO order_status (title)
VALUES ('paid'); /* Оплачено */
INSERT INTO order_status (title)
VALUES ('not_paid');
/* Не оплачено */

/* Вставка роли персонала */
INSERT INTO role_staff (title)
VALUES ('Официант'); /* 1 */
INSERT INTO role_staff (title)
VALUES ('Повар');
INSERT INTO role_staff (title)
VALUES ('Администратор');
INSERT INTO role_staff (title)
VALUES ('Пользователь');
/* 2 */

/* Вставка персонала */
INSERT INTO staff (last_name, first_name, phone, email, password, login, role_staff_id)
VALUES ('Федоров', 'Василий', '8-910-249-53-46', 'vasya@tastyhouse.com', 'qwerty', 'vasya_fedorov', 1);
INSERT INTO staff (last_name, first_name, phone, email, password, login, role_staff_id)
VALUES ('Иванова', 'Юлия', '8-950-959-64-75', 'ivanova@tastyhouse.com', 'qwerty', 'ivanova', 2);

/* Вставка заказов */
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2019-03-01 13:25:00', 'На месте', 1, 1, 1);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2019-02-14 12:20:00', 'На месте', 2, 2, 1);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2019-02-10 11:43:00', 'На месте', 1, 1, 1);

INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 1, 1, 3);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 2, 2, 3);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 1, 1, 3);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 2, 2, 3);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 1, 1, 4);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 2, 2, 4);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 1, 1, 5);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 2, 2, 6);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 1, 1, 6);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2005-07-23 13:54:00', 'На месте', 2, 2, 6);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2019-02-13 13:54:00', 'На месте', 1, 1, 7);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2019-02-21 13:54:00', 'На месте', 2, 2, 7);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2018-07-23 18:15:00', 'На месте', 1, 1, 7);


/* Вставка типов блюд */
INSERT INTO type_dish (title)
VALUES ('Салаты'); /* 1 */
INSERT INTO type_dish (title)
VALUES ('Закуски'); /* 2 */
INSERT INTO type_dish (title)
VALUES ('Горячее'); /* 3 */
INSERT INTO type_dish (title)
VALUES ('Десерты'); /* 4 */
INSERT INTO type_dish (title)
VALUES ('Рыба и морепродукты'); /* 5 */
INSERT INTO type_dish (title)
VALUES ('Супы');
/* 6 */

/* Вставка блюд */
/* Ингредиенты, рецепт, масса, время - поставил кавычки как заглушку, можете вставлять туда что вам нужно */

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Цезарь с курицей',
        'Хрустящие листья салата, сочные помидоры, сыр пармезан и классический соус цезарь в сочетании с нежным куриным филе и хрустящими сухариками.',
        1250,
        '1. Белый хлеб очищаем от корочки и нарезаем кубиками с гранью в 1 см. <br>
        2. Оливковое масло наливаем на сковороду (чтобы еле-еле покрывало дно). <br>
        3. Очищаем чеснок, разрезаем его на несколько пластинок и обжариваем слегка в масле. <br>
        4. Обжариваем в масле хлебные кубики - наши будущие крутоны - до приятной золотинки. <br>
        5. Нарезаем филе, желательно кубиками такого же размера, т.е. в 1 см. <br>
        6. Курицу обжариваем, солим, перчим. <br>
        7. На крупной терке натираем “Пармезан”.',
        '200 г', TIME '00:40:00', '/img/cezar.jpg', 1, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Салат с креветками', 'Салат с креветками добавит свежести и яркости в ваш день. Салат лёгкий и освежающий. ',
        1500,
        '1.Помидоры и огурцы нарежьте кубиками. <br>
       2. Все, вместе с креветками, смешайте. Добавьте соль по вкусу и заправьте оливковым маслом.',
        '250 г', TIME '00:50:00', '/img/krevetki.jpg', 1, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Салат с красной рыбой',
        'Такое блюдо получается не только вкусным, но и очень легким, поэтому может использоваться в качестве диетического, несмотря на калорийность рыбы.',
        700,
        '1. Семгу нарезать кусочками. <br>
        2. Яйца отварить и нарезать небольшими кубиками. <br>
        3. Свежие огурцы очистить и нарезать кубиками. <br>
        4. Смешайте все ингредиенты и заправьте сметаной. <br>
        5. Добавьте соль, укроп и зеленый лук.',
        '200 г', TIME '00:25:00', '/img/salat-s-krasnoj-ryboj.jpg', 1, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Бутерброды с красной икрой и сыром', 'Бутерброды с красной икрой – это классическая праздничная закуска. ',
        520,
        '1.Сливочное масло заранее достать из холодильника и дать ему оттаять около 15 минут. <br>
        2.Намазать ровным слоем масла каждый ломтик хлеба.<br>
        3.Поверх масла на каждый ломтик уложить по ложечке красной икры.<br>
        4.Украсить каждый бутерброд веточкой укропа.',
        '100 г', TIME '00:10:00', '/img/buterbrody-s-krasnoj-ikroj-i-syrom.jpg', 2, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Десерт "Радость обезьянки"', 'Сладость из банана, мороженого и молоко порадует и впечатлит Вас.', 2000,
        '1. Бананы очистить, мороженое нарезать кубиками. <br>
        2. Сложить в чашу блендера и взбить мороженое с бананами. <br>
        3. Добавить сок лимона и молоко.<br>
        4. Разлить по бокалам.<br>
        5. Присыпать шоколадом и убрать в холод до подачи.',
        '250 г', TIME '00:10:00', '/img/novogodnij-desert-radost-obezyanki.jpg', 4, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Тирамису', 'Тирамису — итальянский творожный десерт на основе сыра маскарпоне. ', 1500,
        '1. Выложить маскарпоне в широкую миску и интенсивно взбить лопаткой. <br>
        2. Отделить белки от желтков. В одной миске взбить желтки и сахарную пудру и получившуюся массу постепенно добавить к маскарпоне. <br>
        3. Отдельно взбить белки и затем — по одной ложке — аккуратно вмешать в смесь желтков и маскарпоне. <br>
        4. Смешать охлажденный крепкий кофе с четырьмя столовыми ложками рома в широкой тарелке. <br>
        5. Аккуратно обмакнуть в смесь кофе и рома треть или половину савоярди. <br>
        6. Пропитанное печенье уложить на дно формы, сверху залить третью крема из маскарпоне. <br>
        7. Окунуть вторую порцию печенья в кофейную смесь и уложить на крем. Сверху выложить второй слой крема из маскарпоне. <br>
        8. Затем — оставшееся пропитанное печенье.',
        '200 г', TIME '00:15:00', '/img/tiramisu.jpg', 4, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Курица с картошкой «Праздничная»', 'Домашнее, всемилюбимое блюдо.', 3000,
        '1. Смешать майонез и измельченный чеснок. <br>
        2. Курицу промыть и натереть солью, перцем и майонезом внутри и снаружи. <br>
        3. Картофель очистить, нарезать кружочками и отварить в подсоленной воде до полуготовности. <br>
        4. В глубокий противень выложить курицу, по краям разложить картошку. <br>
        5. Запекать в духовке при 200 градусах до готовности курицы.',
        '350 г', TIME '01:00:00', '/img/kurica-s-kartoshkoj-prazdnichnaya.jpg', 3, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Мини-пиццы',
        'Большой пиццы для Вас много, тогда эти Мини-пиццы Вам идеально подойдут. Булочка с беконом и сочным перцом Вас удивят.',
        800,
        '1. Мелко нарезать болгарский перец и бекон, перемешать в глубокой посуде. <br>
        2. Сыр натереть на средней терке или мелко нарезать ломтиками. <br>
        3. Мелко порубить зелень. <br>
        4. Все нарезанные ингредиенты смешать в глубокой посуде. <br>
        5. Разбить в получившуюся смесь 3 яйца и тщательно перемешать до однородности яиц.  <br>
        6. Добавить сметану и еще раз тщательно перемешать. <br>
        7. Чиабатту нарезать кусочками толщиной 1–1,5 см, и выложить на противень (на бумагу для выпекания). <br>
        8. На каждый кусок хлеба выложить приготовленную смесь, разделить поровну между всеми кусками. <br>
        9. Выпекать при температуре 200–220 градусов 10–15 минут. ',
        '400 г', TIME '00:30:00', '/img/mini-piccy.jpg', 3, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Минтай в духовке', 'Вкусно, полезно, не дорого.', 150,
        '1. Лук нарезать полукольцами, обжарить на растительном масле. Выложить на противень ровным слоем. <br>
        2. Морковь натереть на крупной терке, обжарить на растительном масле. Выложить на лук. <br>
        3. Филе минтая нарезать большими кусочками и обвалять в муке, обжарить с каждой стороны и выложить на морковь. <br>
        4. Смешать майонез, сметану и специи и обмазать сверху минтай. <br>
        5. Запекать в духовке при 180 градусах 20 минут.',
        '250 г', TIME '00:40:00', '/img/mintaj-v-duhovke.jpg', 5, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Грибной крем-суп из шампиньонов', 'Аппетитныый грибной суп, подается в хлебной тарелке.', 200,
        '1. Поставить вариться 4–5 средних картофелин. <br>
        2. Лук мелко нарезать, грибы нарезать дольками. Обжарить лук на сливочном масле до прозрачности, потом добавить грибы. <br>
        3. Добавить грибы с луком в картофель, добавить сливки, соль, перец и измельчить блендером. <br>
        4. Подавать с гренками.',
        '200 г', TIME '00:30:00', '/img/gribnoj-sup-iz-shampinonov.jpg', 6, 'available');

/* Вставка для связующей таблицы между блюдами и персоналом */
INSERT INTO dishes_and_staff (dish_id, staff_id)
VALUES (1, 1);
INSERT INTO dishes_and_staff (dish_id, staff_id)
VALUES (2, 1);
INSERT INTO dishes_and_staff (dish_id, staff_id)
VALUES (3, 1);
INSERT INTO dishes_and_staff (dish_id, staff_id)
VALUES (4, 1);
INSERT INTO dishes_and_staff (dish_id, staff_id)
VALUES (5, 1);
INSERT INTO dishes_and_staff (dish_id, staff_id)
VALUES (6, 2);
INSERT INTO dishes_and_staff (dish_id, staff_id)
VALUES (7, 2);
INSERT INTO dishes_and_staff (dish_id, staff_id)
VALUES (8, 2);
INSERT INTO dishes_and_staff (dish_id, staff_id)
VALUES (9, 2);
INSERT INTO dishes_and_staff (dish_id, staff_id)
VALUES (10, 2);

/* Вставка для таблицы связи блюдо-заказ */
/* NULL здесь как заглушка - вставляйте что считаете нужным */
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 1, 1, 1);

INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 1, 1, 2);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 1, 1, 2);

INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 1, 1, 4);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 1, 1, 4);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 1, 1, 4);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 1, 1, 4);


INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '00:30:00', null, null, 2, 1, 2);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '00:50:00', null, null, 3, 1, 3);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:20:00', null, null, 1, 2, 4);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 2, 2, 5);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '02:00:00', null, null, 1, 2, 6);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:40:00', null, null, 1, 3, 7);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '00:40:00', null, null, 2, 3, 8);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '02:00:00', null, null, 2, 3, 9);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:10:00', null, null, 3, 3, 10);

/* Вставка для таблицы ингредиенты */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Чеснок', 300, 'Овощ', 'гр', 2);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Салат Романо', 150, 'Зелень', 'гр', 7);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Помидор', 2000, 'Овощ', 'гр', 2);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Пармезан', 1000, 'Сыр', 'гр', 3);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Куриная грудка', 5000, 'Мясо', 'гр', 4); /*5*/
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Хлеб белый', 1000, 'Хлеб', 'гр', 2);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Масло подсолнечное', 4000, 'Бакалея', 'мл', 4);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Соль поваренная пищевая', 2000, 'Специи', 'гр', 1); /*8*/
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Креветки', 2000, 'Рыба и морепродукты', 'гр', 5);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Оливковое масло', 2000, 'Бакалея', 'мл', 3);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Огурец', 2000, 'Овощ', 'гр', 2); /*11*/
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Сметана', 700, 'Молочная продукция', 'гр', 2);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Перепелиное яйцо', 20, 'Молочная продукция', 'шт', 20);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Укроп', 100, 'Зелень', 'гр', 1); /*14*/
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Зеленый лук', 100, 'Зелень', 'гр', 1);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Икра красная ', 1000, 'Рыба и морепродукты', 'гр', 8);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Масло сливочное', 1000, 'Молочная продукция', 'гр', 2); /*17*/
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Молоко ', 3000, 'Молочная продукция', 'мл', 2);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Банан', 2000, 'Фрукт', 'гр', 2);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Лимон', 1000, 'Цитрус', 'гр', 3); /*20*/
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Шоколадная стружка', 1000, 'Бакалея', 'гр', 4);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Яйцо куриное', 50, 'Молочная продукция', 'шт', 10);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Маскарпоне ', 1000, 'Сыр', 'гр', 5); /*23*/
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Печенье савоярди', 1000, 'Бакалея', 'гр', 7);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Сахарная пудра', 500, 'Бакалея', 'гр', 1);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Крепкий кофе', 500, 'Бакалея', 'гр', 2); /*26*/
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Кофейный лике', 500, 'Ликер', 'мл', 2);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Какао-порошок', 500, 'Бакалея', 'гр', 2);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Ноги куринные', 3000, 'Мясо', 'гр', 3); /*29*/
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Картофель', 10000, 'Овощ', 'гр', 2);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Перец черный', 500, 'Специи', 'гр', 1); /*31*/
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Майонез', '2000', 'Молочная продукция', 'гр', 2);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Бекон ', 500, 'Мясо', 'гр', 4);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Красный сладкий перец', 2000, 'Овощ', 'гр', 2); /*34*/
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Морковь ', 5000, 'Овощ', 'гр', 2);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Пшеничная мука', 5000, 'Бакалея', 'гр', 2);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Растительное масло', 2000, 'Бакалея', 'мл', 2); /*37*/
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Минтай ', 1000, 'Рыба', 'мл', 4);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Шампиньоны ', 1000, 'Овощ', 'гр', 3);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Лук репчатый', 500, 'Овощ', 'гр', 2); /*40*/
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Сливки 20%', 2000, 'Молочная продукция', 'мл', 4);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Слабосоленая семга', 2000, 'Рыба и морепродукты', 'гр', 5);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Батон', 700, 'Хлеб', 'гр', 2);
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Сливочное мороженое', 2000, 'Мороженое', 'гр', 4);
/*44*/

/* Вставка для таблицы связи блюдо-ингредиент */
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (2, 1, 1);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (35, 1, 2);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (45, 1, 3);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (15, 1, 4);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (70, 1, 5);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 1, 6);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (5, 1, 7);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (1, 1, 8);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (150, 2, 9);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (30, 2, 3);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 2, 10);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (40, 2, 11);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (1, 2, 8);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (50, 3, 42);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 3, 12);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (3, 3, 13);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (5, 3, 14);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (5, 3, 15);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (80, 3, 11);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 4, 43);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 4, 16);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (5, 4, 14);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 4, 17);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (40, 4, 4);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (200, 5, 44);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (200, 5, 18);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (250, 5, 19);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 5, 20);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (15, 5, 21);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (1, 6, 22);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (50, 6, 23);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (140, 6, 24);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (5, 6, 25);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (10, 6, 26);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (9, 6, 27);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (9, 6, 28);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (400, 7, 29);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (150, 7, 30);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (2, 7, 1);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (1, 7, 31);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (10, 7, 32);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (1, 7, 8);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (1, 8, 8);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (50, 8, 33);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 8, 4);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (15, 8, 12);