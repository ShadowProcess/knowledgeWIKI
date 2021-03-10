alter table `t_flow_wx_bind` add column `channel` varchar(20) default '' comment '渠道';
alter table `t_flow_wx_bind` add column `ctdev` bit(1) default 0 comment '是否电信手机号';