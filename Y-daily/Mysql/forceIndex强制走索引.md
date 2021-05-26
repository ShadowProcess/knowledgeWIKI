#怎么强制走索引  force index(xxx_xxx)
explain select user_name,user_age from user1 force index(idx_user_age) where user_age < 50;
explain select user_name,user_age from user1 force index(idx_user_age) where user_age > 20;