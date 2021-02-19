from bs4 import BeautifulSoup
from selenium import webdriver
import requests
import csv
import pandas as pd

path = 'advCtgr.csv'
file_result = open(path, 'r', newline='', encoding='utf-8-sig')
baseUrl = 'http://222.232.15.205:8082/ytb/ctgr/'

headers = {'User-Agent': 'Custom'}
for ctgr in file_result:
    print(ctgr)
    # driver = webdriver.Chrome('chromedriver')
    # driver.get(baseUrl + ctgr)
    # driver.close

file_result.close()