
#1.查看MySQL的，auto_increment数值
```sql
select auto_increment from information_schema.tables where table_schema='数据库名' and table_name='表名';
```

#2.修改MySQL的，auto_increment数值
```sql
alter table '表名' auto_increment=数值;
```

