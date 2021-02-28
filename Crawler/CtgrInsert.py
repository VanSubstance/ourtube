import csv

path = 'CtgrInsert.sql'
file_write = open(path, 'w', encoding='utf-8-sig')

path = 'CtgrRuliweb.csv'
file_read= open(path, 'r', newline='', encoding='utf-8-sig')

file_write.write('set define off;\n')
data = file_read.readlines()
ctgrs = []
for line in data:
    line = line.replace('&lt;', '<')
    line = line.replace('&gt;', '>')
    line = line.replace('&amp;', '&')
    line = line.split(",")
    inp = 'insert into game (title, thumbnail) values (\''
    #%% 제목
    title = line[0]
    title = title.replace("\'", "\'\'")
    inp += title + "\',\'"
    #%% 썸네일
    inp += line[1] + "\');\n"
    file_write.write(inp)

    
for line in data:
    line = line.replace('&lt;', '<')
    line = line.replace('&gt;', '>')
    line = line.replace('&amp;', '&')
    line = line.split(",")
    inp = 'insert into gametopic (title, topic) values (\''
    #%% 제목
    title = line[0]
    title = title.replace("\'", "\'\'")
    inp += title + "\',\'"
    #%% 카테고리
    ctgr = line[2].replace('\'', '\'\'')
    ctgr = ctgr.replace("\r", "")
    ctgr = ctgr.replace("\n", "")
    inp += ctgr + '\');\n'
    file_write.write(inp)

file_read.close()
file_write.close()