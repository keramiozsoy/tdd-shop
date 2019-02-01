insert into  PRODUCT (id,title,price) VALUES (1,'testProductTitle',115.37);

insert into  CATEGORY (id,title) VALUES (1,'testCategoryTitle');

--alter table PRODUCT add CATEGORY_ID NUMBER;

alter table PRODUCT 
add constraint FK_CATEGORY
foreign key (CATEGORY_ID) 
references CATEGORY (ID); 


insert into  PRODUCT (id,title,price,category_id) values (2,'testProductTitle2',10.15,1);

alter table CATEGORY
add constraint FK_CAMPAING
foreign key (CAMPAING_ID)
references CAMPAIGN(ID);

insert into CAMPAIGN(id,title,discount_amount,discount_type) values (1,'kampanya1',20,'RATE');

insert into CAMPAIGN(id,title,discount_amount,discount_type) values (2,'kampanya2',50,'RATE');

insert into CAMPAIGN(id,title,discount_amount,discount_type) values (3,'kampanya4',5,'AMOUNT');

