import datetime
import os

from green import set_sys_time, commit


def modify_other(path):
    file = open(path, 'r')
    flag = int(file.readline()) == 0
    file.close()
    file = open(path, 'w+')
    if flag:
        file.write('1')
    else:
        file.write('0')
        file.close()


def hard_commit(path, year, month, day, times):
    set_sys_time(year, month, day)
    while times > 0:
        modify_other(path)
        os.chdir(os.path.dirname(path))
        commit()
        times -= 1


def special_commit(path, date, times):
    hard_commit(path, date.year, date.month, date.day, times)


def read_etc(path):
    idxes = []
    file = open(path, 'r')
    while True:
        word = file.readline()
        if not word:
            break
        else:
            idxes.extend(word.split())
    intIdxes = []
    for idx in idxes:
        intIdxes.append(int(idx))
    return intIdxes


def love_commit(start_date, path, etc_path):
    words = read_etc(etc_path)
    for index in words:
        cur_date = start_date + datetime.timedelta(days=index)
        special_commit(path, cur_date, 4)


if __name__ == '__main__':
    love_commit(datetime.date(2019, 1, 1), 'D:\DevelopmentSoftWare\ideaWebWorkSpace\knowledgeWIKI\wings\zero.md', 'D:\DevelopmentSoftWare\ideaWebWorkSpace\knowledgeWIKI\wings\love')
##第一个参数为开始日期（小绿点表格左上），第二个参数为zero.md所在路劲，第三个为love文件所在路径
