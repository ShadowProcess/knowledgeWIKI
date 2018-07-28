use spring;
create table book
(
    isbn      varchar(50) primary key,
    book_name varchar(100),
    price     int
);

create table book_stock
(
    isbn  varchar(50) primary key,
    stock int,
    check ( stock > 0 ) -- 对于mysql，这个可以加，但是没有效果
);

create table account
(
    username varchar(50) primary key,
    balance  int,
    check ( balance > 0 )
);