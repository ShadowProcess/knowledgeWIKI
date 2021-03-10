create table t_5g_orders_lucky(
    id bigint not null auto_increment,
    mobile varchar(11) not null,
    Lottery_date date not null,
    date_created datetime not null,
    last_updated datetime not null,
    primary key (id)
);