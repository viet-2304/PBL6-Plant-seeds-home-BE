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
use sql12538885;
-- Create table roles(
-- role_id varchar(255) not null,
--   role_name varchar(255),
--   primary key(role_id)
--   );
-- Create table users (
-- user_id varchar(255) not null,
-- email varchar(255),
-- phone_number varchar(255),
-- address varchar(255),
-- user_name varchar(255) not null,
-- password varchar(255) not null,
-- role_id varchar(255) not null,
-- primary key(user_id),
-- foreign key(role_id) references roles(role_id)
-- );
--
-- Create table shops (
--   shop_id varchar(255) not null,
--   shop_name varchar(255) not null,
--   address varchar(255) not null,
--   phone_number varchar(255) not null,
--   email varchar(255),
--   facebook_address varchar(255),
--   user_id varchar(255) not null,
--   primary key(shop_id),
--   foreign key(user_id) references users(user_id)
-- );

Create table images_avatar(
  avatar_id varchar(255) not null,
  avatar_URL varchar(255) not null,
  user_id varchar(255),
  shop_id varchar(255),
  primary key(avatar_id),
  foreign key(user_id) references Users(user_id),
  foreign key(shop_id) references Shops(shop_id)
);

 Create table product_type (
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

  foreign key (paymenthMethod) references payment_method(payment_method_id),
  foreign key (orderStatus) references order_status(status_id)

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

use sql12538885;
insert into shops values ('45494b5652ac11edbdc30242ac120002','Thu Suong', 'Gia Lai','342719265', 'suong@gmail.com', null,'2c93b01a84565313018456664ac20002');
insert into shops values ('968209c252ac11edbdc30242ac120002','Si Viet', 'Binh Dinh','175394237', 'viet@gmail.com', null, '2c93b01a8456531301845666101f0001');

use sql12538885;
insert into product_type values ('5e41c0e652ae11edbdc30242ac120002', 'rau');
insert into product_type values ('75d100dc52ae11edbdc30242ac120002', 'củ');
insert into product_type values ('8202ea1e52ae11edbdc30242ac120002', 'quả');
insert into product_type values ('8db1ed1a52ae11edbdc30242ac120002', 'hoa');

use sql12538885;
insert into products values ('b2dc130e52ae11edbdc30242ac120002','HẠT GIỐNG XÀ LÁCH CHỊU MƯA RADO 359', 'là giống xà lách phát triển mạnh, kháng bệnh tốt, độ đồng đều cao, khả năng thích nghi rộng. Cây lớn, dạng lá to tròn, hơi dúng, dày, màu vàng đẹp, ăn ngon và hợp thị hiếu người tiêu dùng. Đặc biệt, cây có thể trồng được quanh năm, đặc biệt giống xà lách này có khả năng chống chịu tốt hơn các giống khác khi trồng vào mùa mưa', 2024-05-12, 2022-05-12, 'RẠNG ĐÔNG',9000, 4.5, 20, '45494b5652ac11edbdc30242ac120002','5e41c0e652ae11edbdc30242ac120002');
insert into products values ('12035afbad514d4d84934387bdead8fa','HẠT GIỐNG BẮP CẢI F1', 'có xuất xứ Hàn Quốc, bắp to và có màu xanh hơi nhạt đặc trưng và ăn rất ngon. Hạt giống bắp cải có khả năng sinh trưởng mạnh, hạt nảy mầm đều và tỉ lệ nảy mầm rất cao.', 2024-03-17, 2022-03-17, 'PHÚ NÔNG',9000, 4.5, 20, '45494b5652ac11edbdc30242ac120002','5e41c0e652ae11edbdc30242ac120002');
insert into products values ('d8509097fe7444afb99f26533d16bbb8','HẠT GIỐNG BÍ NGỌN SIÊU ĐỌT', 'Hạt giống bí ngọn siêu đọt có tỉ lệ nảy mầm cao, sinh trưởng phát triển mạnh. Bí ngọn siêu đọt có thân ngọn mập, màu xanh mướt, đẹp và ăn rất ngọt, ngon. Thời gian bắt đầu thu hoạch từ 40 - 45 ngày sau gieo', 2024-05-15, 2022-05-15, 'PHÚ NÔNG',9000, 4.5, 20, '968209c252ac11edbdc30242ac120002','8202ea1e52ae11edbdc30242ac120002');
insert into products values ('9404453a796a436988a4b8ac0d6a4717','HẠT GIỐNG RAU NGÓT RADO 621', 'là giống rau ngót có tỉ lệ nảy mầm cao, sinh trưởng mạnh, đẻ nhiều nhánh, lá to mướt, thích hợp trồng được quanh năm. Rau ngót có vị ngọt thanh đặc trưng, thơm ngon bỗ dưỡng rất thích hợp cho bữa cơm gia đình nhà bạn.', 2024-05-12, 2022-05-12, 'RẠNG ĐÔNG',9000, 4.5, 20, '45494b5652ac11edbdc30242ac120002','5e41c0e652ae11edbdc30242ac120002');
insert into products values ('e74d6716635e4a91855c070190df5dd2','HẠT GIỐNG TẦN Ô LÁ TRÒN ( CÚC NẾP ) RADO 124', 'là giống tần ô sinh trưởng phát triển rất mạnh. Cây cao trung bình 25 - 30cm, đầu lá có dạng hơi tròn, màu xanh nhạt, chất lượng ăn ngon, thơm mùi dễ chịu, không có vị đắng, rất được ưa chuộng.', 20/6/2024, 20/06/2022, 'RẠNG ĐÔNG',9000, 4, 25, '968209c252ac11edbdc30242ac120002','8202ea1e52ae11edbdc30242ac120002');
insert into products values ('22ed2e3ec4c040f8b7d2c7d7ca4791c7','HẠT GIỐNG CẢI KALE TÍM ( CẢI XOĂN TÍM ) LAI F1 RADO 214', 'là giống cải Kale sinh trưởng phát triển mạnh. Lá cải có màu tím nổi bật, lá non có dạng xoăn, bó thành chùm, lá có vị ngọt nhẹ, cây có sức sống tốt, năng suất cao và thu hoạch chỉ sau khoảng 2 tháng trồng.', '2023-10-07', '2021-10-07', 'RẠNG ĐÔNG',14500, 4, 20, '45494b5652ac11edbdc30242ac120002','5e41c0e652ae11edbdc30242ac120002');
insert into products values ('300caea54ff7454b96e48b683bcb3772','HẠT GIỐNG BÍ ĐỎ HẠT ĐẬU LAI ( BÍ ĐỎ HỒ LÔ )', 'là loại hạt giống sinh trưởng tốt, phát triển mạnh, có thể trồng được quanh năm, và đặc biệt dễ đậu trái trong mùa mưa. Giống có năng suất cao, quả có hình hạt đậu, trọng lượng từ 1,2 – 1,5kg, thịt quả dày cơm, màu vàng cam đậm, vị béo bùi thơm ngon.', '2023-08-12', '2021-08-12', 'RẠNG ĐÔNG',9000, 5, 20, '968209c252ac11edbdc30242ac120002','8202ea1e52ae11edbdc30242ac120002');
insert into products values ('901725f418b246c283934fb0a125d333','HẠT GIỐNG BÍ ĐỎ ĂN NON', 'là loại sinh trưởng mạnh, lá nhỏ, chịu virus khá. Hoa cái được sinh ra lần đầu tiên trên các nút lá thứ 6 - 7 của thân cây chính, quả chắc, thịt dày, cây cho quả liên tục, rất nhiều quả, quả mềm có hình tròn, vỏ màu xanh có đốm trắng, tròn, cuốn dài, ăn rất ngon. Trái có đường kính 5cm - 6cm, nặng khoảng 450g - 500g thì nên thu hoạch,', '2023-12-11', '2021-12-11', 'RẠNG ĐÔNG',9000, 4, 10, '45494b5652ac11edbdc30242ac120002','8202ea1e52ae11edbdc30242ac120002');
insert into products values ('24e429dc8f4f467eac99ce5c3ad05476','HẠT GIỐNG BẮP NGỌT LAI ( BẮP MỸ ) F1', 'là loại hạt giống có tỷ lệ nảy mầm cao. Cây bắp sinh trưởng mạnh, kháng bệnh tốt, thân cứng, chống đỗ ngã tốt và cho năng suất cao. Quả bắp to, dài khoảng 20cm - 25cm, nặng trung bình 420g - 480g, vỏ bì rất kín có màu xanh, hạt đóng múp đầu, hạt vàng tươi đều, đẹp, ăn vị ngon ngọt.', '2023-10-01', '2021-10-01', 'RẠNG ĐÔNG',9000, 5, 15 , '45494b5652ac11edbdc30242ac120002','5e41c0e652ae11edbdc30242ac120002');

-- 410
-- insert into products values ('42d34885d77348c58dd5c7d52c91fc7d','HẠT GIỐNG RAU NGÓT RADO 621', 'là giống rau ngót có tỉ lệ nảy mầm cao, sinh trưởng mạnh, đẻ nhiều nhánh, lá to mướt, thích hợp trồng được quanh năm. Rau ngót có vị ngọt thanh đặc trưng, thơm ngon bỗ dưỡng rất thích hợp cho bữa cơm gia đình nhà bạn.', 2024-05-12, 2022-05-12, 'RẠNG ĐÔNG',9000, 4.5, 20, '45494b5652ac11edbdc30242ac120002','5e41c0e652ae11edbdc30242ac120002');
-- insert into products values ('9118643feb3845e0ae85182c83ce696c','HẠT GIỐNG RAU NGÓT RADO 621', 'là giống rau ngót có tỉ lệ nảy mầm cao, sinh trưởng mạnh, đẻ nhiều nhánh, lá to mướt, thích hợp trồng được quanh năm. Rau ngót có vị ngọt thanh đặc trưng, thơm ngon bỗ dưỡng rất thích hợp cho bữa cơm gia đình nhà bạn.', 2024-05-12, 2022-05-12, 'RẠNG ĐÔNG',9000, 4.5, 20, '45494b5652ac11edbdc30242ac120002','5e41c0e652ae11edbdc30242ac120002');
-- insert into products values ('f5282305d8044032bf6261fe5cc2c4c8','HẠT GIỐNG RAU NGÓT RADO 621', 'là giống rau ngót có tỉ lệ nảy mầm cao, sinh trưởng mạnh, đẻ nhiều nhánh, lá to mướt, thích hợp trồng được quanh năm. Rau ngót có vị ngọt thanh đặc trưng, thơm ngon bỗ dưỡng rất thích hợp cho bữa cơm gia đình nhà bạn.', 2024-05-12, 2022-05-12, 'RẠNG ĐÔNG',9000, 4.5, 20, '45494b5652ac11edbdc30242ac120002','5e41c0e652ae11edbdc30242ac120002');
-- insert into products values ('b1b540f9b4ca4f3ea3367f484a717185','HẠT GIỐNG RAU NGÓT RADO 621', 'là giống rau ngót có tỉ lệ nảy mầm cao, sinh trưởng mạnh, đẻ nhiều nhánh, lá to mướt, thích hợp trồng được quanh năm. Rau ngót có vị ngọt thanh đặc trưng, thơm ngon bỗ dưỡng rất thích hợp cho bữa cơm gia đình nhà bạn.', 2024-05-12, 2022-05-12, 'RẠNG ĐÔNG',9000, 4.5, 20, '45494b5652ac11edbdc30242ac120002','5e41c0e652ae11edbdc30242ac120002');
-- insert into products values ('b051c8481e7344f6bf0a22dad662cf69','HẠT GIỐNG RAU NGÓT RADO 621', 'là giống rau ngót có tỉ lệ nảy mầm cao, sinh trưởng mạnh, đẻ nhiều nhánh, lá to mướt, thích hợp trồng được quanh năm. Rau ngót có vị ngọt thanh đặc trưng, thơm ngon bỗ dưỡng rất thích hợp cho bữa cơm gia đình nhà bạn.', 2024-05-12, 2022-05-12, 'RẠNG ĐÔNG',9000, 4.5, 20, '45494b5652ac11edbdc30242ac120002','5e41c0e652ae11edbdc30242ac120002');
-- insert into products values ('cc6a859521e44acaae293b49e4624724','HẠT GIỐNG RAU NGÓT RADO 621', 'là giống rau ngót có tỉ lệ nảy mầm cao, sinh trưởng mạnh, đẻ nhiều nhánh, lá to mướt, thích hợp trồng được quanh năm. Rau ngót có vị ngọt thanh đặc trưng, thơm ngon bỗ dưỡng rất thích hợp cho bữa cơm gia đình nhà bạn.', 2024-05-12, 2022-05-12, 'RẠNG ĐÔNG',9000, 4.5, 20, '45494b5652ac11edbdc30242ac120002','5e41c0e652ae11edbdc30242ac120002');

  
  

 
