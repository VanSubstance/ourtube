from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import requests
import time

import cx_Oracle
cx_Oracle.init_oracle_client(lib_dir=r"C:\instantclient_19_10")

url = 'https://www.youtube.com/gaming/games'
driver = webdriver.Chrome('chromedriver')
driver.get(url)
SCROLL_PAUSE_SEC = 1
body = driver.find_element_by_css_selector('body')

dsn = cx_Oracle.makedsn("222.232.15.205",1521,"orcl")
db = cx_Oracle.connect("rootweb2020","1234",dsn)

for i in range(10):
    # 끝까지 스크롤 다운
    body.send_keys(Keys.END)
    # 1초 대기
    time.sleep(SCROLL_PAUSE_SEC)


html = driver.page_source
soup = BeautifulSoup(html, 'html.parser')
i = 1
for tag in soup.select('div[id = "game"]'):
    cursor = db.cursor()
    print(i, "-------------------------------------------------------------------------------")
    lines = str(tag).splitlines()
    link = 'https://www.youtube.com/' + lines[0].split('href="')[1].replace('">', '') + '/about'
    title = lines[2].split('title">')[1].split('</yt')[0]
    print(lines[4])
    views = lines[4].split('전 세계에서 </span><span class="style-scope yt-formatted-string" dir="auto">')[1].split('</span><span class="style-scope yt-for')[0]
    if views.__contains__("천"):
        views = float(views[0: len(views) - 1]) * 1000
    elif views.__contains__("만"):
        views = float(views[0: len(views) - 1]) * 10000
    elif len(views) == 0:
        views = 0
    else:
        views = float(views[0: len(views) - 1])
    views = int(views)
    driverDetail = webdriver.Chrome('chromedriver')
    driverDetail.get(link)
    soupDetail = BeautifulSoup(driverDetail.page_source, 'html.parser')
    thumbnail = str(soupDetail.select('img[id = "img"]')).split(' src="')[1].split('"/>, <img')[0]
    description = str(soupDetail.select('yt-formatted-string[id="description"]')).split('split-lines="">')[1].split('</yt-form')[0]
    topics = []
    for topic in soupDetail.select('span[class="style-scope ytd-badge-supported-renderer"]'):
        topics.append(topic.text)
    title = title.replace("'", "\"")
    description = description.replace("'", "\"")
    insertGame = "insert into game (title, description, thumbnail) values ('" + title + "', '" + description + "', '" + thumbnail + "')"
    print(insertGame)
    try:
        cursor.execute(insertGame)
    except cx_Oracle.IntegrityError:
        print("이미 존재")
    else:
        print("잘됨")
    db.commit()
    for topic in topics:
        insertTopic = "insert into topictotal (topic) values ('" + topic + "')"
        print(insertTopic)
        try:
            cursor.execute(insertTopic)
        except cx_Oracle.IntegrityError:
            print("이미 존재")
        else:
            print("잘됨")
        db.commit()

    for topic in topics:
        insertGameTopic = "insert into gametopic (title, topic) values ('" + title + "', '" + topic + "')"
        print(insertGameTopic)
        try:
            cursor.execute(insertGameTopic)
        except cx_Oracle.IntegrityError:
            print("이미 존재")
        else:
            print("잘됨")
        db.commit()
    driverDetail.close()
    cursor.close()
    print("-------------------------------------------------------------------------------\n")
    i += 1


driver.close()