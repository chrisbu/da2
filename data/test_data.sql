insert into customer_orders values (1, 'Chris');
insert into customer_orders values (2, 'Bob');
insert into customer_orders values (3, 'Tim');


insert into order_item (order_item_id, product_name, order_Id) values (1, 'Widget', 1);
insert into order_item (order_item_id, product_name, order_Id) values (2, 'Fangle', 1);

insert into order_item (order_item_id, product_name, order_Id) values (3, 'Foobar', 2);
insert into order_item (order_item_id, product_name, order_Id) values (4, 'Widget', 2);

insert into order_item (order_item_id, product_name, order_Id) values (5, 'Widget', 3);
insert into order_item (order_item_id, product_name, order_Id) values (6, 'Dongle', 3);
insert into order_item (order_item_id, product_name, order_Id) values (7, 'Foobar', 3);