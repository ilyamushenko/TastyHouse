CREATE EXTENSION IF NOT EXISTS pgcrypto;

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
INSERT INTO dish_status (title)
VALUES ('Отнесено');
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
INSERT INTO role_staff (staff_login, title)
VALUES (pgp_sym_encrypt('vasya_fedorov', 'secretKey'), 'WAITER');
INSERT INTO role_staff (staff_login, title)
VALUES (pgp_sym_encrypt('ivanova', 'secretKey'), 'COOK');
INSERT INTO role_staff (staff_login, title)
VALUES (pgp_sym_encrypt('admin', 'secretKey'), 'ADMIN');
INSERT INTO role_staff (staff_login, title)
VALUES (pgp_sym_encrypt('guest', 'secretKey'), 'GUEST');

/* Вставка персонала */
INSERT INTO staff (last_name, first_name, phone, email, password, login, staff_login, enabled)
VALUES (pgp_sym_encrypt('Федоров', 'secretKey'), pgp_sym_encrypt('Василий', 'secretKey'), pgp_sym_encrypt('8-910-249-53-46', 'secretKey'), pgp_sym_encrypt('vasya@tastyhouse.com', 'secretKey'), pgp_sym_encrypt('qwerty', 'secretKey'), pgp_sym_encrypt('vasya_fedorov', 'secretKey'), 1, true);
INSERT INTO staff (last_name, first_name, phone, email, password, login, staff_login, enabled)
VALUES (pgp_sym_encrypt('Иванова', 'secretKey'), pgp_sym_encrypt('Юлия', 'secretKey'), pgp_sym_encrypt('8-950-959-64-75', 'secretKey'), pgp_sym_encrypt('ivanova@tastyhouse.com', 'secretKey'), pgp_sym_encrypt('qwerty', 'secretKey'), pgp_sym_encrypt('ivanova', 'secretKey'), 2, true);
INSERT INTO staff (last_name, first_name, phone, email, password, login, staff_login, enabled)
VALUES (pgp_sym_encrypt('Кушнеренко', 'secretKey'), pgp_sym_encrypt('Виктор', 'secretKey'), pgp_sym_encrypt('8-950-959-64-30', 'secretKey'), pgp_sym_encrypt('vitya@tastyhouse.com', 'secretKey'), pgp_sym_encrypt('qwerty', 'secretKey'), pgp_sym_encrypt('admin', 'secretKey'), 3, true);
INSERT INTO staff (last_name, first_name, phone, email, password, login, staff_login, enabled)
VALUES (pgp_sym_encrypt('Петров', 'secretKey'), pgp_sym_encrypt('Игорь', 'secretKey'), pgp_sym_encrypt('8-950-959-64-22', 'secretKey'), pgp_sym_encrypt('guest@tastyhouse.com', 'secretKey'), pgp_sym_encrypt('qwerty', 'secretKey'), pgp_sym_encrypt('guest', 'secretKey'), 4, true);

/* Вставка заказов */
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2019-05-17 16:20:00', 'На месте', 1, 1, 2);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2019-05-17 16:00:00', 'На месте', 2, 2, 2);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2019-05-17 16:10:00', 'На месте', 1, 1, 3);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2019-05-17 16:15:00', 'На месте', 2, 2, 3);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2019-05-17 16:10:00', 'На месте', 1, 1, 4);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2019-05-17 16:13:00', 'На месте', 2, 2, 4);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2019-05-17 16:24:00', 'На месте', 1, 1, 6);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2019-05-17 16:15:00', 'На месте', 2, 2, 6);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2019-05-17 16:22:00', 'На месте', 1, 1, 7);
INSERT INTO restaurant_order (date_orders, type, order_status_id, type_payment_id, restaurant_table_id)
VALUES (TIMESTAMP '2019-05-17 16:12:00', 'На месте', 2, 2, 7);

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
VALUES ('"Pasta carbonara"',
        'Невероятно вкусная паста не оставит равнодушным вас, даже если вы никогда не были в Италии.',
        350,
        '1. Спагетти варить 7-10 минут в кипящей подсоленной воде и откинуть на дуршлаг. <br>
	2. В сковороде разогрейте оливковое масло, положите чеснок и слегка подрумяньте. <br>
	3. Ветчину/бекон мелко нарежьте, добавьте к чесноку и обжаривайте 5 минут. <br>
	4. Сыр пармезан натрите на мелкой терке. Желтки взбить со сливками, немного подсолить. <br>
	5. Спагетти переложить в сотейник с чесноком и ветчиной/беконом. <br>
	6. Добавить взбитые желтки и тёртый сыр, перемешать. Держать на огне 3 минуты. <br>
	7. Посыпать молотым перцем, украсить зеленью и подавать на стол.',
        '200 г', TIME '00:25:00', '/img/pasta-carbonara.jpg', 3, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Стейк из лосося',
	'Аппетитный стейк лосося на гриле, нежнейшего вкуса с тонкой пряной корочкой – блюдо любимое многими, именно поэтому его популярность настолько велика. На вкус филе достаточно сочное, внешний вид позволяет отнести представленное блюдо к универсальным угощениям, способным не только украсить праздничный стол во время торжественного мероприятия, но также насытить человеческий организм ценной энергией и восполнить витаминный состав.',
        800,
        '1. Имбирь очистить и тонко нарезать соломкой, соединить с остальными ингредиентами. <br>
	2. Лосось промыть, просушить и замариновать в маринаде. Оставить минут на 10. <br>
	3. Выложить на гриль и обжарить с двух сторон или запечь в духовом шкафу при 180 градусах 10-15 минут.',
        '250 г', TIME '00:50:00', '/img/steak-is-lososia.jpg', 3, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Котлеты куриные с пюре',
        'Легкое и невероятно полезное блюдо — аккуратные котлетки из сочного куриного мяса. Их восхитительный вкус стал еще более нежным благодаря добавлению в фарш натуральных сливок. Пряная петрушка и золотистый репчатый лук добавляют блюду пикантности и тонкий аппетитный аромат. Мясо птицы богато легкоусвояемым белком и витаминами, необходимым для полноценного развития организм',
        250,
        '1. Хлебушек замочить в молоке так, что бы он все впитал и стал мягким. Ничего, если молока немного останется. Прокручиваем филе в мясорубке или в блендере, там же хлеб и лук, добавляем яйцо, солим. <br>
	2. Сливочное масло отправляем в морозилку на 10 минут. Достаём и натираем на крупной тёрке в фарш, перемешиваем. <br>
	3. Лепим котлетки, формируем их и обваливаем в муке. Обжариваем на растительном масле до румяной корочки с двух сторон.',
        '200 г', TIME '00:25:00', '/img/kotleti-kurinie.jpg', 3, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Грибное ризотто в сливочном соусе',
        'Традиционное итальянское блюдо с нежным сливочным вкусом и изумительным ароматом.',
        450,
        '1. Сушеные грибы залить холодной водой и оставить на час. Затем добавить остальные ингредиенты и варить 30-40 минут на среднем огне. Либо, не замачивая заране, сразу варить вместе с овощами, но уже дольше, час-полтора. <br> 
	2. Процедить бульон и перейти к приготовлению ризотто. Овощи из бульона нам не понадобятся, а грибы уже по вашему желанию. Я добавляю часть из них в готовое ризотто. <br>
	3. В глубокой кастрюле/сотейнике нагрето оливковое масло и обжарить мелконарезанный лук до прозрачности. Не передержать, чтобы ничего не подгорело. <br> 
	4. Добавить рис, перемешать с луком и дать немного обжариться, буквально пол минутки на низком огне. <br>
	5. Прибавляем огонь и вливаем вино, хорошо выпариваем его. <br>
	6. Вливаем бульон так, чтобы он покрывал рис на 1 см и добавляем листики тимьяна. Огонь держим средний. Как только рис впитает бульон, добавляем ещё и так, пока рис не сварится. В классическом варианте рис для ризотто должен быть «аль-денте», то есть немного не доварен. Но вы можете сделать по своему вкусу. <br> 
	7. Когда мы влили последнюю часть бульона (или воды) начинаем обжаривать начинку - шампиньоны. Моем, сушим и нарезкам вдоль (как на фото) Обжариваем на оливковом масле с ложкой сливочного до золотистого цвета. Туда же я добавила немного отварных сушеных грибов, мелко нарезав. <br>
	8. Добавляем начинку в ризотто (или только часть начину , а остальную оставляем чуть для украшения сверху). Вливаем сливки и быстро мешаем. На этом этапе жидкости в ризотто должно быть чуть больше, чем перед подачей. Он быстро ее впитает. <br>
	9. Снимаем ризотто с огня и добавляем оставшееся сливочное масло. Хорошо перемешиваем. Вмешиваем мелко нарезанную зелень, солим, перчим по вкусу и пробуем.',
        '200 г', TIME '00:25:00', '/img/gribnoe-rizotto-v-slivochnom-souse.jpg', 3, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Бутерброды с красной рыбой',
	'Бутерброды с красной рыбой – это классическая закуска.',
        350,
        '1.Сливочное масло заранее достать из холодильника и дать ему оттаять около 15 минут. <br>
        2.Намазать ровным слоем масла каждый ломтик хлеба. <br>
        3.Поверх масла на каждый ломтик уложить по ломтику красной рыбы. <br>
        4.Украсить каждый бутерброд веточкой укропа.',
        '150 г', TIME '00:10:00', '/img/buterbrody-s-ribkoi.jpg', 2, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Сырники',
	'Сырники (творожники) — главное блюдо завтрака, чудно настраивающего на прекрасный день, любимое детское блюдо, угощение на десерт, лакомство на перекус среди дня.',
        200,
        '1. Замешиваем все ингредиенты вместе с мукой. <br>
	2. Разогреваем в сковороде растительное/ кокосовое масло. Чуть больше, чем обычно. Сковорода не должна быть сухой. <br>
	3. Смачиваем столовую ложку в стакане воды и выкладываем каждый сырник. <br>
	4. Накрываем крышкой и жарим на низком огне, через пару минут проверяем и переворачиваемым на другу сторону.',
        '150 г', TIME '00:10:00', '/img/sirniki.jpg', 2, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Австралийский чизкейк',
	'Довольно необычный чизкейк, со сливочным кремом, джемом и глазурью с насыщенным шоколадным вкусом. Этот чизкейк не выпекается, он относится к «холодным» тортам, которые, можно сказать, доходят до готовности в холодильнике. Необычен австралийский чизкейк в первую очередь своим сливочно-творожным кремом.',
	 200,
        '1. Взбить миксером жирный творог, сахарную пудру и размягченное масло до образования однородной массы, добавить ванилин и тонкой струйкой влить растворенный в горячей воде желатин. Взбить, выложить массу в форму и поставить в холодильник на 4–5 часов для застывания. <br>
	2. Для приготовления коржа растолочь овсяное печенье и смешать с растопленной на водяной бане шоколадкой. Выстелить тарелку для торта промасленной пергаментной бумагой и сформовать лепешку. Поставить в холодильник на 1 час. <br>
	3. Форму с застывшей творожной массой опустить на несколько секунд в горячую воду и осторожно выложить содержимое на подготовленный корж. Смазать сверху густым фруктовым джемом и обмазать весь торт чуть теплым, растопленным на водяной бане шоколадом. Поставить в холодильник на ночь.',
        '250 г', TIME '00:10:00', '/img/australian-cheesecake.jpg', 4, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Шоколадные блинчики',
	'Тонкие шоколадные блины, подаются с вареньем из смородины и ягодами.',
	 150,
        '1. Соединить все ингредиенты. Масло в тесто. <br>
	2. Сковороду хорошо прогрейте и добавьте масло, на котором будете обжаривать блины.<br>
	3. Обжарить каждый блинчик, выложить красиво на тёплую тарелку .',
        '200 г', TIME '00:15:00', '/img/shokoladnie-blinchiki.jpg', 4, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Салат с авокадо',
	'Салат из авокадо – прекрасная полезная трапеза. Во-первых, регулярное употребление этого плода улучшает не только здоровье, но и внешность. Во-вторых, авокадо препятствует старению организма. ',
	 350,
        '1. Маринад для куриного филе, он же соус (часть маринада оставляем и разбавляем водой 1:1) : 1столовая ложка мёда, 1 столовая ложка зернистой горчицы, 2 столовых ложки оливкового масла, шепотка соли. Оставляем курочку в маринаде примерно на 1 час. <br>
	2. Обжариваем цельное куриное филе на гриле до готовности. Чтобы все прожарилось равномерно, не берите слишком толстые кусочки или разрежьте их вдоль. <br>
	3. Выкладываем салат.',
        '200 г', TIME '00:15:00', '/img/salad-s-avokado.jpg', 1, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Печеночный салат',
	'Печень в этом салате совсем по-новому проявляет свой вкус и в сочетании с овощами и специями создает невероятное по вкусу блюдо.',
	 300,
        '1. Куриную печень (свежую, важно) промыть, просушить, и отрезать лишнее (я отрезаю то, что мне не нравится) а именно: жилки и неприглядные кусочки. <br>⠀⠀ ⠀⠀⠀ ⠀ 
	2. Обжарить печень на оливковом с двух сторон до готовности, посолить. <br> ⠀⠀ ⠀⠀⠀ ⠀ 
	3. В это время вы успеете: порвать листья салата и выложить с рукколлой в блюдо, хорошо перемешать с заправкой (наша любимая: три столовых ложки оливкового, одна ложка лимонного сока, 1 чайная ложка горчицы, 1 чайная ложка мёда и 1/2 чайная ложка сахара, перемешать и по желанию добавить несколько капель бальзамика. <br> ⠀⠀ ⠀⠀⠀ ⠀ 
	4. Добавить половинки черри, очищенные и тонко нарезанные дольки апельсина для этого тщательно очищаем от кожуры и белой плёнки, можно срезать ножом. <br>
	5. Выкладываем печень, сверху красный лук и подаём, пока салат ещё теплый. Если хотите подать тёплым к столу- сразу после обжарки переложите печенку в фольгу - и отложите, там она будет тёплой ещё минут 15-20 (только не забудьте)',
        '200 г', TIME '00:15:00', '/img/pechenochnii-salad.jpg', 1, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Крем-суп из шампиньонов',
	'Аппетитный крем-суп пекрасно сочетает в себе грибы и петрушку, подается в хлебной тарелке.',
	 190,
        '1. Нарезать луковицу, обжарить ее в сливочном масле. <br>
	2. Грибы нарезать пластинами, отправить к луку. <br>
	3. В сотейнике растопить сливочное масло, засыпать 2 ст. л. муки. <br>
	4. Влить бульон, довести до кипения, засыпать туда грибы и лук, добавить смесь масла с мукой, влить сливки.',
        '350 г', TIME '01:00:00', '/img/krem-soup.jpg', 6, 'available');

INSERT INTO "dish" (name, description, price, recipe, mass, preparing_time, img_url, type_dish_id, status)
VALUES ('Суп-лапша с яйцом',
        'Куриный суп с лапшой и яйцом - отличное первое блюдо для разнообразия вашего повседневного обеденного стола. Суп очень вкусный, насыщенный и ароматный.',
        150,
        '1. Хлеб замочить в молоке так, что бы он все впитал и стал мягким. Ничего, если молока немного останется. Прокручиваем филе в мясорубке или в блендере, там же хлеб и лук, добавляем яйцо, солим. <br>
	2. Сливочное масло отправляем в морозилку на 10 минут. Достаём и натираем на крупной тёрке в фарш, перемешиваем. <br>
	3. Лепим котлетки, формируем их и обваливаем в муке. Обжариваем на растительном масле до румяной корочки с двух сторон.',
        '400 г', TIME '00:30:00', '/img/soup-lapsha-s-yaicom.jpg', 6, 'available');

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
VALUES (6, 1);
INSERT INTO dishes_and_staff (dish_id, staff_id)
VALUES (7, 1);
INSERT INTO dishes_and_staff (dish_id, staff_id)
VALUES (8, 1);
INSERT INTO dishes_and_staff (dish_id, staff_id)
VALUES (9, 1);
INSERT INTO dishes_and_staff (dish_id, staff_id)
VALUES (10, 1);
INSERT INTO dishes_and_staff (dish_id, staff_id)
VALUES (11, 1);
INSERT INTO dishes_and_staff (dish_id, staff_id)
VALUES (12, 1);

/* Вставка для таблицы связи блюдо-заказ */
/* NULL здесь как заглушка - вставляйте что считаете нужным */
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 1, 1, 1);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 1, 1, 2);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 3, 1, 3);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 1, 2, 4);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 3, 2, 5);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 3, 2, 6);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 3, 3, 7);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '00:30:00', null, null, 3, 3, 8);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '00:50:00', null, null, 3, 4, 9);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:20:00', null, null, 2, 4, 10);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 4, 5, 11);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '02:00:00', null, null, 4, 5, 12);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 4, 5, 1);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 4, 6, 2);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '02:00:00', null, null, 4, 6, 3);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 4, 6, 4);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '02:00:00', null, null, 1, 7, 5);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 1, 7, 6);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '02:00:00', null, null, 3, 8, 7);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 3, 8, 8);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '02:00:00', null, null, 3, 9, 9);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 3, 9, 10);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '02:00:00', null, null, 2, 10, 11);
INSERT INTO dishes_from_order (real_time, begin_cooking_time, end_cooking_time, dish_status_id, order_id, dish_id)
VALUES (TIME '01:00:00', null, null, 3, 10, 12);

/* Вставка для таблицы ингредиенты */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Спагетти', 3000, 'Лапша', 'гр', 2); /* 1 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Оливковое масло', 2000, 'Бакалея', 'мл', 4); /* 2 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Чеснок', 300, 'Овощ', 'гр', 1); /* 3 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Ветчина', 4000, 'Мясо', 'гр', 5); /* 4 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Яйцо', 100, 'Молочная продукция', 'шт', 10); /* 5 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Пармезан', 3000, 'Сыр', 'гр', 3); /* 6 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Сливки 10%', 4000, 'Молочная продукция', 'мл', 4); /* 7 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Соль поваренная пищевая', 2000, 'Специи', 'гр', 1); /* 8 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Перец черный', 1000, 'Специи', 'гр', 1); /* 9 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Соевый соус', 500, 'Бакалея', 'мл', 2); /* 10 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Имбирь', 600, 'Специи', 'гр', 2); /* 11 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Мед', 500, 'Сладкое', 'гр', 3); /* 12 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Лосось', 5000, 'Рыба и морепродукты', 'гр', 7); /* 13 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Куриная грудка', 5000, 'Мясо', 'гр', 5); /* 14 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Лук репчатый', 500, 'Овощ', 'гр', 2); /* 15 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Масло сливочное', 2000, 'Молочная продукция', 'гр', 2); /* 16 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Хлеб белый', 2000, 'Хлеб', 'гр', 2); /* 17 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Молоко', 3000, 'Молочная продукция', 'мл', 2); /* 18 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Пшеничная мука', 5000, 'Бакалея', 'гр', 2); /* 19 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Сушеные грибы', 3000, 'Грибы', 'гр', 3); /* 20 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Луковица', 50, 'Овощ', 'шт', 2); /* 21 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Морковь', 5000, 'Овощ', 'гр', 2); /* 22 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Рис', 3000, 'Злаковые', 'гр', 3); /* 23 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Шампиньоны', 1000, 'Грибы', 'гр', 4); /* 24 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Тимьян', 1000, 'Специи', 'гр', 3); /* 25 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Белое сухое вино', 4000, 'Бакалея', 'мл', 5); /* 26 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Подсолнечное масло', 2000, 'Бакалея', 'мл', 2); /* 27 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Красная рыба', 2000, 'Рыба и морепродукты', 'гр', 6); /* 28 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Творог', 3000, 'Молочная продукция', 'гр', 3); /* 29 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Сахар', 2000, 'Специи', 'гр', 2); /* 30 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Желатин', 2000, 'Белок', 'гр', 1); /* 31 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Ванилин', 2500, 'Специи', 'гр', 1); /* 32 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Сахарная пудра', 500, 'Бакалея', 'гр', 1); /* 33 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Овсяное печенье', 1000, 'Бакалея', 'гр', 3); /* 34 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Шоколад', 2000, 'Сладкое', 'гр', 3); /* 35 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Джем', 2000, 'Бакалея', 'гр', 2); /* 36 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Растительное масло', 2000, 'Бакалея', 'мл', 2); /* 37 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Салат Романо', 1500, 'Зелень', 'гр', 3); /* 38 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Помидор Черри', 2000, 'Овощ', 'гр', 4); /* 39 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Авокадо', 2000, 'Фрукт', 'гр', 4); /* 40 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Бекон', 5000, 'Мясо', 'гр', 5); /* 41 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Красный лук', 2000, 'Овощ', 'гр', 2); /* 42 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Горчица', 2000, 'Специи', 'гр', 3); /* 43 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Куриная печень', 5000, 'Мясо', 'гр', 4); /* 44 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Лимон', 100, 'Цитрус', 'шт', 3); /* 45 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Апельсин', 100, 'Цитрус', 'шт', 3); /* 46 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Лук-порей', 1000, 'Овощ', 'гр', 3); /* 47 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Сельдерей', 2000, 'Овощ', 'гр', 4); /* 48 */
INSERT INTO ingredient (name, quantity_in_stock, type, unit, price)
VALUES ('Лавровый лист', 2500, 'Специи', 'гр', 1); /* 49 */

/* Вставка для таблицы связи блюдо-ингредиент */
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (100, 1, 1);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (12, 1, 2);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (2, 1, 3);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (80, 1, 4);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (1, 1, 5);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (25, 1, 6);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 1, 7);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (10, 1, 8);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (2, 1, 9);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (50, 2, 10);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (10, 2, 11);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (15, 2, 12);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (300, 2, 13);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (400, 3, 14);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (50, 3, 15);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (1, 3, 5);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (50, 3, 16);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (50, 3, 17);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (100, 3, 18);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 3, 8);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (100, 3, 19);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (30, 4, 20);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (2, 4, 21);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (100, 4, 22);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (100, 4, 20);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (50, 4, 25);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (100, 4, 26);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (30, 4, 16);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (100, 4, 7);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (50, 4, 2);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (50, 4, 27);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (50, 5, 27);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (150, 5, 28);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (200, 6, 29);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (1, 6, 5);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (15, 6, 30);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (15, 6, 19);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (15, 6, 8);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (400, 7, 29);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (200, 7, 16);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (50, 7, 31);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 7, 32);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 7, 33);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (50, 7, 34);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (200, 7, 35);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (50, 7, 36);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (1, 8, 5);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (100, 8, 19);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (100, 8, 36);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 8, 30);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (15, 8, 8);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (15, 8, 37);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (150, 8, 35);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (100, 9, 38);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (150, 9, 39);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (75, 9, 40);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (100, 9, 41);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (50, 9, 42);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (100, 9, 43);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (300, 10, 44);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (1, 10, 45);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (15, 10, 43);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (1, 10, 46);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (1, 11, 21);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (30, 11, 16);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (50, 11, 24);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (100, 11, 19);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (2, 12, 22);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 12, 15);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (15, 12, 47);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 12, 48);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 12, 8);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 12, 9);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (50, 12, 49);
INSERT INTO food_ingredients (quantity, dish_id, ingredient_id)
VALUES (20, 12, 25);



