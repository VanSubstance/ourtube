from bs4 import BeautifulSoup
from selenium import webdriver
import requests
import csv
import pandas as pd

path = 'CtgrNamu_Tier1_1.csv'
file_result = open(path, 'w', newline='', encoding='utf-8-sig')
writer = csv.writer(file_result)
writer.writerow(['title'])

headers = {'User-Agent': 'Custom'}
response = requests.get('https://namu.wiki/w/%EB%B6%84%EB%A5%98:%EA%B2%8C%EC%9E%84%20%EC%9E%A5%EB%A5%B4', headers = headers)
html = response.text
soup = BeautifulSoup(html, 'html.parser')

print(response)

for tag in soup.select('a'):
    result = tag.text
    trash = ['문서', '최근', '갱신중', '더 보기']
    if not trash[0] in result and not trash[1] in result and not trash[2] in result and not trash[3] in result and len(result) != 0:
        if result[0] == " ":
            result = result[1:]
        print("---------------\n")
        print(result)
        writer.writerow([result])
    # for trash in ['']:
    #     result = result.replace(trash, "")
    #     result = result.split("\n")
    # for item in result:
    #     print("------------------------------\n")
    #     print(item)

file_result.close()