import datetime
import random
import os

def set_sys_date(year, month, day):
    print('date %04d/%02d/%02d' % (year, month, day))
    os.system('date %04d/%02d/%02d' % (year, month, day))

def set_sys_time():
    os.system('time %02d:%02d:%02d' % (random.randint(8,23), random.randint(0,59),random.randint(0,59)))

def commit(msg):
    os.system('git add .')
    os.system('git commit -m %s' % (msg))










if __name__ == '__main__':
    set_sys_date(2018,4,16)                 #设置那一天
    set_sys_time()
    commit("webflux")                         #想要深色点，最低三个提交
