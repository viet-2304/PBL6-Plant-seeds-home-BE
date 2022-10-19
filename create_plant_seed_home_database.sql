drop database plant_seeds_home;

Create database plant_seeds_home;

use plant_seeds_home;
Create table Roles(
role_id varchar(255) not null,
  role_name varchar(255),
  primary key(role_id)
  );
Create table Users (
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

Create table Shops (
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

Create table Images_Avatar(
  avatar_id varchar(255) not null,
  avatar_URL varchar(255) not null,
  user_id varchar(255),
  shop_id varchar(255),
  primary key(avatar_id),
  foreign key(user_id) references Users(user_id),
  foreign key(shop_id) references Shops(shop_id)
);

 Create table Product_Type (
  product_type_id varchar(255) not null,
  name varchar(255),
  primary key (product_type_id)
);

Create table Products (
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

Create table Carts (
  STT varchar(255) not null,
  number_of_product int,
  user_id varchar(255) not null,
  product_id varchar(255) not null,
  primary key(STT),
  foreign key (user_id) references Users(user_id),
  foreign key (product_id) references Products(product_id)
);

Create Table Comments (
 comment_id varchar(255) not null,
  description varchar(255) not null,
  comment_time datetime,
  product_id varchar(255) not null,
  user_id varchar(255),
  primary key (comment_id),
  foreign key(product_id) references Products(product_id),
  foreign key(user_id) references Users(user_id)
  );
  
  Create table Images_Product (
image_id varchar(255) not null,
  image_url varchar(255) not null,
  product_id varchar(255),
  comment_id varchar(255),
  primary key (image_id),
  foreign key(product_id) references Products(product_id),
  foreign key(comment_id) references Comments(comment_id)
);
  
  Create table Payment_Method(
   payment_method_id varchar(255) not null,
  payment_method_name varchar(255),
  primary key (payment_method_id)
  );
  
  Create table Order_Status (
   status_id varchar(255) not null,
  status_name varchar(255),
  primary key (status_id)
  );
  
  Create Table Orders(
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
   
  
  Create Table Order_Details (
  order_details_id varchar(255) not null,
  order_id varchar(255) not null,
   user_id varchar(255) not null,
  product_id varchar(255) not null,
  primary key (order_details_id),
  foreign key (order_id) references Orders(order_id),
  foreign key(user_id) references Users(user_id),
  foreign key (product_id) references Products(product_id)
  );
  
  
  
  

 
