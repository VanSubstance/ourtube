from bs4 import BeautifulSoup
from selenium import webdriver
import requests
import csv
import pandas as pd

path = 'channelIds.csv'
file_result = open(path, 'r', newline='', encoding='utf-8-sig')
baseUrl = 'http://222.232.15.205:8082/ytb/video/'

ctgrs = list(file_result)
i = 0
for ctgr in ctgrs:
    print(i, "----------")
    ctgr = ctgr.replace("게임", "")
    ctgr = ctgr.replace("\r", "")
    ctgr = ctgr.replace("\n", "")
    print(ctgr)
    if i >= 0:
        driver = webdriver.Chrome('chromedriver')
        driver.get(baseUrl + ctgr)
        driver.close()
    i += 1
    print("-------------")

file_result.close()