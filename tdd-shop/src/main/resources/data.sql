insert into  PRODUCT (id,title,price) VALUES (1,'testProductTitle',115.37);

insert into  CATEGORY (id,title) VALUES (1,'testCategoryTitle');

--alter table PRODUCT add CATEGORY_ID NUMBER;

alter table PRODUCT 
add constraint FK_CATEGORY
foreign key (CATEGORY_ID) 
references CATEGORY (ID); 


insert into  PRODUCT (id,title,price,category_id) VALUES (2,'testProductTitle2',10.15,1);

