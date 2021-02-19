from bs4 import BeautifulSoup
from selenium import webdriver
import requests
import csv
import pandas as pd

path = 'CtgrWiki_Tier1_1.csv'
file_result = open(path, 'w', newline='', encoding='utf-8-sig')
writer = csv.writer(file_result)
writer.writerow(['title'])

headers = {'User-Agent': 'Custom'}
response = requests.get('https://ko.wikipedia.org/wiki/%EB%B9%84%EB%94%94%EC%98%A4_%EA%B2%8C%EC%9E%84_%EC%9E%A5%EB%A5%B4', headers = headers)
html = response.text
soup = BeautifulSoup(html, 'html.parser')

print(response)

for tag in soup.select('div[class="navbox"]'):
    result = tag.text
    for trash in ['']:
        result = result.replace(trash, "")
        result = result.split("\n")
    for item in result:
        print("------------------------------\n")
        print(item)
        writer.writerow([item])

file_result.close()