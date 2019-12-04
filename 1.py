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
    set_sys_date(2019,12,4)
    #set_sys_date(2019,12,9)
    #set_sys_date(2020,1,1)
    #set_sys_date(2020,1,5)
    #set_sys_date(2020,1,9)
    #set_sys_date(2020,1,15)
    #set_sys_date(2020,1,18)
    #set_sys_date(2020,1,25)
    #set_sys_date(2020,2,1)
    #set_sys_date(2020,2,5)
    #set_sys_date(2020,2,8)
    #set_sys_date(2020,2,16)
    #set_sys_date(2020,2,20)
    #set_sys_date(2020,2,25)
    #set_sys_date(2020,2,26)
    #set_sys_date(2020,2,28)
    #set_sys_date(2020,3,5)
    #set_sys_date(2020,3,7)
    #set_sys_date(2020,3,10)
    #set_sys_date(2020,3,15)
    #set_sys_date(2020,3,17)
    #set_sys_date(2020,3,20)
    #set_sys_date(2020,3,26)
    #set_sys_date(2020,4,1)
    #set_sys_date(2020,4,3)
    #set_sys_date(2020,4,8)
    #set_sys_date(2020,4,16)
    #set_sys_date(2020,4,20)
    #set_sys_date(2020,4,25)
    #set_sys_date(2020,4,29)
    #set_sys_date(2020,5,14)
    #set_sys_date(2020,5,19)
    #set_sys_date(2020,5,22)
    #set_sys_date(2020,5,26)
    #set_sys_date(2020,5,29)
    #set_sys_date(2020,6,7)
    #set_sys_date(2020,6,13)
    #set_sys_date(2020,6,17)
    #set_sys_date(2020,6,21)
    #set_sys_date(2020,6,26)
    #set_sys_date(2020,6,29)
    #set_sys_date(2020,7,3)
    #set_sys_date(2020,7,10)
    #set_ys_date(2020,7,15)
    set_sys_time()
    commit("课件")                         #想要深色点，最低三个提交
