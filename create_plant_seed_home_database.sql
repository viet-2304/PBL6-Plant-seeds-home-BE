drop database plant_seeds_home;

Create database plant_seeds_home;

use plant_seeds_home;
Create table roles(
role_id varchar(255) not null,
  role_name varchar(255),
  primary key(role_id)
  );
Create table users (
user_id varchar(255) not null,
email varchar(255),
phone_number varchar(255),
address varchar(255),
user_name varchar(255) not null,
password varchar(255) not null,
role_id varchar(255) not null,
primary key(user_id),
foreign key(role_id) references Roles(role_id)
);

Create table shops (
  shop_id varchar(255) not null,
  shop_name varchar(255) not null,
  address varchar(255) not null,
  phone_number varchar(255) not null,
  email varchar(255),
  facebook_address varchar(255),
  user_id varchar(255) not null,
  primary key(shop_id),
  foreign key(user_id) references Users(user_id)
);

Create table images_avatar(
  avatar_id varchar(255) not null,
  avatar_URL varchar(255) not null,
  user_id varchar(255),
  shop_id varchar(255),
  primary key(avatar_id),
  foreign key(user_id) references Users(user_id),
  foreign key(shop_id) references Shops(shop_id)
);

 Create table product_Type (
  product_type_id varchar(255) not null,
  name varchar(255),
  primary key (product_type_id)
);

Create table products (
product_id varchar(255) not null,
  product_name varchar(255) not null,
  description varchar(255) not null,
  EXP datetime not null,
  MFG datetime not null,
  manufacturer varchar(255) not null,
  price int not null,
  rating int,
  number_of_product int not null,
  shop_id varchar(255),
  product_type varchar(255),
  primary key (product_id),
  foreign key (shop_id) references Shops(shop_id),
  foreign key (product_type) references Product_Type(product_type_id)
 );

Create table carts (
  STT varchar(255) not null,
  number_of_product int,
  user_id varchar(255) not null,
  product_id varchar(255) not null,
  primary key(STT),
  foreign key (user_id) references Users(user_id),
  foreign key (product_id) references Products(product_id)
);

Create Table comments (
 comment_id varchar(255) not null,
  description varchar(255) not null,
  comment_time datetime,
  product_id varchar(255) not null,
  user_id varchar(255),
  primary key (comment_id),
  foreign key(product_id) references Products(product_id),
  foreign key(user_id) references Users(user_id)
  );
  
  Create table images_product (
image_id varchar(255) not null,
  image_url varchar(255) not null,
  product_id varchar(255),
  comment_id varchar(255),
  primary key (image_id),
  foreign key(product_id) references Products(product_id),
  foreign key(comment_id) references Comments(comment_id)
);
  
  Create table payment_method(
   payment_method_id varchar(255) not null,
  payment_method_name varchar(255),
  primary key (payment_method_id)
  );
  
  Create table order_status (
   status_id varchar(255) not null,
  status_name varchar(255),
  primary key (status_id)
  );
  
  Create Table orders(
  order_id varchar(255) not null,
  number int,
  total int,
  create_date datetime,
  update_date datetime,
  paymenthMethod varchar(255),
  orderStatus varchar(255),
  primary key(order_id),
  foreign key (paymenthMethod) references Payment_Method(payment_method_id),
  foreign key (orderStatus) references Order_Status(status_id)
  );
   
  
  Create Table order_details (
  order_details_id varchar(255) not null,
  order_id varchar(255) not null,
   user_id varchar(255) not null,
  product_id varchar(255) not null,
  primary key (order_details_id),
  foreign key (order_id) references Orders(order_id),
  foreign key(user_id) references Users(user_id),
  foreign key (product_id) references Products(product_id)
  );

use plant_seeds_home;
insert into roles values('1', 'user');
insert into roles values('2', 'admin');
insert into roles values('3', 'root');

insert into product_type values ('1', 'hat giong hoa');
insert into product_type values ('2', 'cay non');
insert into product_type values ('3', 'hat giong cay luong thuc');


use plant_seeds_home;
insert into shops values ('45494b5652ac11edbdc30242ac120002','Thu Suong', 'Gia Lai','342719265', 'suong@gmail.com', null,'ff8081818403fcb2018403fccddd0000');
insert into shops values ('968209c252ac11edbdc30242ac120002','Si Viet', 'Binh Dinh','175394237', 'viet@gmail.com', null, 'ff8081818403fcb2018403fe38ac0002');


use plant_seeds_home;
insert into product_Type values ('5e41c0e652ae11edbdc30242ac120002', 'rau');
insert into product_Type values ('75d100dc52ae11edbdc30242ac120002', 'củ');
insert into product_Type values ('8202ea1e52ae11edbdc30242ac120002', 'quả');
insert into product_Type values ('8db1ed1a52ae11edbdc30242ac120002', 'hoa');

use plant_seeds_home;
insert into products values ('b2dc130e-52ae-11ed-bdc3-0242ac120002','HẠT GIỐNG XÀ LÁCH CHỊU MƯA RADO 359', 'là giống xà lách phát triển mạnh, kháng bệnh tốt, độ đồng đều cao, khả năng thích nghi rộng. Cây lớn, dạng lá to tròn, hơi dúng, dày, màu vàng đẹp, ăn ngon và hợp thị hiếu người tiêu dùng. Đặc biệt, cây có thể trồng được quanh năm, đặc biệt giống xà lách này có khả năng chống chịu tốt hơn các giống khác khi trồng vào mùa mưa', 12/05/2024, 12/05/2022, 'RẠNG ĐÔNG',9000, 4.5, 20, '45494b5652ac11edbdc30242ac120002','5e41c0e652ae11edbdc30242ac120002')

  
  

 
