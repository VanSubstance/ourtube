from bs4 import BeautifulSoup
from selenium import webdriver
import requests
import csv
import pandas as pd

path = 'advCtgr.csv'
file_result = open(path, 'r', newline='')
baseUrl = 'http://222.232.15.205:8082/patch/ctgr/'

ctgrs = list(file_result)
i = 0
for ctgr in ctgrs:
    ctgr = ctgr.replace("\r", "")
    ctgr = ctgr.replace("\n", "")
    ctgr = ctgr.replace(" =", "")
    if ctgr != "":
        print(i, "----------")
        print(ctgr)
        if i >= 14:
            driver = webdriver.Chrome('chromedriver')
            driver.get(baseUrl + ctgr)
            driver.close()
        i += 1
        print("-------------")

file_result.close()