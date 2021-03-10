create table  t_flow_td_record(
    id bigint not null auto_increment,
    mobile_num bigint null,
    offer_id varchar(128) null,
    offer_inst_id varchar(128) null,
    offer_name varchar(128) null,
    cust_order_nbr varchar(128) null,
    date_created datetime null,
    primary key (id)
)
