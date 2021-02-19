from bs4 import BeautifulSoup
from selenium import webdriver
import requests
import csv
import pandas as pd

path = 'CtgrDC_Tier2_2.csv'
file_result = open(path, 'w', newline='', encoding='utf-8-sig')
writer = csv.writer(file_result)
writer.writerow(['title'])

headers = {'User-Agent': 'Custom'}
response = requests.get('https://gall.dcinside.com/m', headers = headers)
html = response.text
soup = BeautifulSoup(html, 'html.parser')

print(response)

for tag in soup.select('a[target="_parent"]'):
    result = tag.text
    for trash in [" ", "\n", "\t", "\r", "1", "2", "3", "4", "5", "6", '7', '8', '9', '0', "(", ")", '더보기', 'NEW', "Q&A", "q&a", "갤"]:
        result = result.replace(trash, "")
    print("--------------------")
    print(result)
    writer.writerow([result])

file_result.close()