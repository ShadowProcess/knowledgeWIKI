```
/**
* 单向映射关系和双向映射关系的区别？
*
* 总结可见：
* 双向关联和单向关联在数据表层是没有区别的，只是在业务上单向关联只可以通过ProductInfo对象找到对应的ProductType对象；而双向
* 关联关系不仅可以通过ProductInfo对象找到对应的ProductType对象，还可以通过ProductType对象找到对应的ProductInfo对象
*/
```

问题：
```
JPA 在双向映射时，会相互包含对方的实例，相互引用，造成递归迭代，堆栈溢出（java.lang.StackOverflowError）。
```

分析:
```
在后端向前端传递的时候会将数据序列化，转为json，这时会出现循环引用造成堆栈溢出
```

解决方案：
```
1、将其配置为单向关联，可以较好的解决问题；

2.解决方法就是在转换json时忽略循环字段。首先确定项目使用的json包是哪一个（jackjson 或 fastjson），寻找相应的注解忽略某一字段。
jackson包对应的相关注解：  @JsonIgnoreProperties、@JsonIgnore
fastjson包对应的相关注解： @JSONField(serialize = false)

注意: 在使用注解时一定要注意引入的包是否正确，如果和自己使用的json包不对应的话，注解是不生效的。
```

