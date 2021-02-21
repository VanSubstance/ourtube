from bs4 import BeautifulSoup
from selenium import webdriver
import requests
import csv
import pandas as pd

path = 'ctgrsHavingChannel.csv'
file_result = open(path, 'r', newline='')
baseUrl = 'http://222.232.15.205:8082/basic/addCommentsByCtgr/'

videos = list(file_result)
i = 0
for video in videos:
    video = video.replace("\r", "")
    video = video.replace("\n", "")
    if video != "":
        print(i, "----------")
        print(video)
        if i >= 87:
            driver = webdriver.Chrome('chromedriver')
            driver.get(baseUrl + video)
            driver.close()
        i += 1
        print("-------------")

file_result.close()