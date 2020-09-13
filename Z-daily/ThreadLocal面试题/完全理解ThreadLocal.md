Thread类中有一个ThreadLocalMap成员

ThreadLocalMap:
- key指向你声明的ThreadLocal
- value你设置的值

key是弱引用
value是强引用