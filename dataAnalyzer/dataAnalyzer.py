import requests
import json
import matplotlib.pyplot as plt
import pandas as pd
from pandas import json_normalize
import numpy as np
from scipy import stats

res = requests.get('http://222.232.15.205:8082/data/test')
data = res.json()
df = json_normalize(data)
print(df)
print(df.columns)

def detectOutliner(dataset):
    q1, q3 = np.percentile(dataset, [25, 75])
    iqr = q3 - q1
    upper_bound = q3 + (iqr * 1.5)
    
    return np.where((dataset > upper_bound))[0]

for col in ['numExistedVid', 'numNewVid', 'avgAccuView', 'avgNewView', 'avgNewLike', 'avgNewDislike', 'avgNewComment']:
    print("변수: ", col)
    df_part = df[col]

    print(df['title'][detectOutliner(df_part)])
    print()
    
# plt.figure()
# plt.title("Average Accumulated ViewCount -> Box Plot")
# plt.boxplot(avgAccuView)

# plt.figure()
# plt.title("Avg New ViewCount -> Box Plot")
# plt.boxplot(avgNewView)

# plt.figure()
# plt.title("Avg New LikeCount -> Box Plot")
# plt.boxplot(avgNewLike)

# plt.figure()
# plt.title("Avg New Like/Dislike -> Box Plot")
# plt.boxplot(avgRatio)

# plt.figure()
# plt.title("Avg avgNewCount -> BoxPlot")
# plt.boxplot(avgNewComment)