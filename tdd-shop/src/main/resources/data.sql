insert into  CATEGORY (id,title) VALUES (1,'testCategoryTitle');
insert into  CATEGORY (id,title) VALUES (2,'testCategoryTitle2');
insert into  CATEGORY (id,title) VALUES (3,'testCategoryTitle3');

insert into  PRODUCT (id,title,price,category_id) VALUES (101,'testProductTitle',115.37,1);
insert into  PRODUCT (id,title,price,category_id) VALUES (102,'testProductTitle2',70.10,1);
insert into  PRODUCT (id,title,price,category_id) VALUES (103,'testProductTitle3',20.90,3);



alter table PRODUCT 
add constraint FK_CATEGORY
foreign key (CATEGORY_ID) 
references CATEGORY (ID); 


insert into  PRODUCT (id,title,price,category_id) values (2,'testProductTitle2',10.15,1);

alter table MERGE_CATEGORY_CAMPAIGN
add constraint FK_MERGE_CAMPAING
foreign key (CAMPAING_ID)
references CAMPAIGN(ID);

alter table MERGE_CATEGORY_CAMPAIGN
add constraint FK_MERGE_CATEGORY
foreign key (CATEGORY_ID)
references CATEGORY(ID);

insert into CAMPAIGN(id,title,discount_amount,discount_type) values (1,'kampanyaYuzde20',20,'RATE');

insert into CAMPAIGN(id,title,discount_amount,discount_type) values (2,'kampanyaYuzde50',50,'RATE');

insert into CAMPAIGN(id,title,discount_amount,discount_type) values (3,'kampanya5TL',5,'AMOUNT');

