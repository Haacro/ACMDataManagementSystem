import requests
import re
import pandas as pd
from lxml import etree
import pymysql
from config import mysql_local
import numpy as np
class cfInfo:
    def __init__(self):
        self.conn = pymysql.connect(**mysql_local)
        self.cursor = self.conn.cursor()
    def get_cf(self,url):
        headers_cf = {
            "Use-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36"
        }

        res_cf = requests.get(url, headers=headers_cf)
        # print(res_cf.text)
        return res_cf

    def parse_data(self,res):
        html = etree.HTML(res.text)

        contestName_tags = html.xpath("//tr/td[1]/text()")
        cl_contestName = [tag.strip() for tag in contestName_tags if tag.strip()]
        #print(cl_contestName)

        contestId = re.findall('data-contestId="(.*?)"', res.text)
        #print(contestId)

        contestTime = re.findall('<span class="format-date" data-locale="en">(.*?)</span>',res.text)
        #print(contestTime)

        length = html.xpath('//tr/td[4]/text()')
        cl_length = [tag.strip() for tag in length if tag.strip()]
        #print(cl_length)

        return cl_contestName,contestId,contestTime,cl_length

    def save_excel(slef,cl_contestName, contestId, contestTime, length):
        # 确定最短列表的长度
        min_length = min(len(cl_contestName), len(contestId), len(length))

        # 创建一个空列表，用来存放时间信息，长度与比赛名称相同
        contestTime_filled = [np.nan] * len(cl_contestName)

        # 将有时间信息的比赛时间赋值给对应位置
        for i in range(min_length):
            contestTime_filled[i] = contestTime[i]

        # 创建 DataFrame
        df = pd.DataFrame({
            "比赛id": contestId[:min_length],
            "比赛名称": cl_contestName[:min_length],
            "比赛时间": contestTime_filled,
            "持续时间": length[:min_length]
        })

        # 将 DataFrame 写入 Excel 文件
        df.to_excel("contest.xlsx", index=False)

    def save_db(self,cl_contestName, contestId, contestTime, length):
        data_list = list(zip(contestId, cl_contestName, contestTime, length))
        sql = "INSERT INTO cf_contest(contest_id, contest_name, begin_time, length) VALUES(%s,%s,%s,%s)"
        self.cursor.executemany(sql,data_list)
        self.conn.commit()
        print("数据存储成功")
        #print(data_list)
    def main(self):
        cl_contestName_list = []
        contestId_list = []
        contestTime_list = []
        length_list = []
        for num in range(1,20):
            page_url = f"https://codeforces.com/contests/page/{num}"
            #print(page_url)

            res = self.get_cf(page_url)

            cl_contestName, contestId,contestTime,length = self.parse_data(res)
            cl_contestName_list.extend(cl_contestName)
            contestId_list.extend(contestId)
            contestTime_list.extend(contestTime)
            length_list.extend(length)
        #print(len(cl_contestName_list), len(contestId_list), len(contestTime_list), len(length_list))

        self.save_db(cl_contestName_list,contestId_list,contestTime_list,length_list)


if __name__=='__main__':
    fi = cfInfo()


    fi.main()
