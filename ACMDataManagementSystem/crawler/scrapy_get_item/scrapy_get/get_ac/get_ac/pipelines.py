# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


# useful for handling different item types with a single interface
from itemadapter import ItemAdapter
from get_cf.settings import mysql_local
import pymysql

class GetAcPipeline:
    def open_spider(self,spider):
        print("启动！！！")
        self.conn = pymysql.connect(**mysql_local)
        self.cursor = self.conn.cursor()

    def process_item(self, item, spider):
        #print("管道",item)
        data_list = item["data_list"]
        sql = "INSERT INTO ac_contest(contest_name, begin_time, length, rating) VALUES(%s,%s,%s,%s)"
        self.cursor.executemany(sql, data_list)
        self.conn.commit()
        print("数据成功存储到数据库中！！！")
        return item

    def close_spider(self,spider):
        print("结束！！！")
        self.cursor.close()
        self.conn.close()