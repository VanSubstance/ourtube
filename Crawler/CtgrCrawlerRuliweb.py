from bs4 import BeautifulSoup
from selenium import webdriver
import requests
import csv
import pandas as pd

path = 'CtgrRuliweb.csv'
file_result = open(path, 'w', newline='', encoding='utf-8-sig')
writer = csv.writer(file_result)
writer.writerow(['title', 'url', 'ctgr'])

headers = {'User-Agent': 'Custom'}
url = 'https://bbs.ruliweb.com/game/search?platform=&genre=all&service=&ordering=ranking_a&search_key=&page='

for i in range(1, 1507):
    response = requests.get(url + str(i), headers = headers)
    html = response.text
    soup = BeautifulSoup(html, 'html.parser')
    print(response)

    for items in soup.select('div[class="card"]'):
        title = str(items.select('strong[class="name"]'))
        img = str(items.select('img[class="game_image"]'))
        ctgrs = str(items.select('span[class="key"]'))
        if title != "[]":
            title = title[22: title.find("</strong>")]
            img = img[img.find("src=") + 5 : img.find("\" width")]
            ctgrs = ctgrs[ctgrs.find("장르: ") + 4 :]
            ctgrs = ctgrs[: ctgrs.find('</span>, <span class="key">')]
            ctgrs = ctgrs.replace(" ", "")
            ctgrs = ctgrs.split(",")
            print("title: ", title)
            print()
            print("img: ", img)
            print()
            print("ctgr: ", ctgrs)
            print()
            for ctgr in ctgrs:
                newRow = [title, img, ctgr]
                writer.writerow(newRow)

file_result.close()