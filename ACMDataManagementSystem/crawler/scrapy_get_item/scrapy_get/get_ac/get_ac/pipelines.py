
from itemadapter import ItemAdapter
from get_cf.settings import mysql_local
import pymysql

class GetAcPipeline:
    def open_spider(self, spider):
        print("启动！！！")
        self.conn = pymysql.connect(**mysql_local)
        self.cursor = self.conn.cursor()

    def process_item(self, item, spider):
        #print(item)
        sql = "INSERT INTO ac_problem(problem_id, problem_name, problem_rate, url) VALUES(%s, %s, %s, %s)"
        problem_id = item['problem_id']
        problem_name = item['problem_name']
        problem_rate = item['problem_rate']
        url = item['url']
        data = (problem_id,problem_name,problem_rate,url)
        #sql = "INSERT INTO ac_contest(contest_name, begin_time, length,rating , url) VALUES(%s, %s, %s, %s, %s)"
        #contest_name = item["contest_name"]
        #begin_time = item["begin_time"]
        #length = item["length"]
        #rating = item["rating"]
        #full_url = item['full_url']
        #data = ( contest_name, begin_time, length, rating,full_url)

        try:
            self.cursor.execute(sql, data)
            self.conn.commit()
            print("数据成功存储到数据库中！！！")  # 每爬取一个数据输出一次成功
        except pymysql.Error as e:
            print(f"存储数据时发生错误：{e}")  # 如有错误发生，提示出错误信息
            self.conn.rollback()

        return item

    def close_spider(self, spider):
        print("结束！！！")
        self.cursor.close()
        self.conn.close()