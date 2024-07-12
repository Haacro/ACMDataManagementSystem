import pymysql
from get_cf.settings import mysql_local


class GetCfPipeline:
    def open_spider(self, spider):
        print("启动！！！")
        self.conn = pymysql.connect(**mysql_local)
        self.cursor = self.conn.cursor()

    def process_item(self, item, spider):
        sql = "INSERT INTO cf_problem(contest_id, problem_id, problem_name, problem_rate, url, tag) VALUES(%s, %s, %s, %s, %s, %s)"

        contest_id = item["contest_id"]
        problem_id = item["problem_id"]
        problem_name = item["problem_name"]
        problem_rate = item["rating"]
        url = item["url"]
        tags = ','.join(item["tags"])

        data = (contest_id, problem_id, problem_name, problem_rate, url, tags)

        try:
            self.cursor.execute(sql, data)
            self.conn.commit()
            print("数据成功存储到数据库中！！！")#每爬取一个数据输出一次成功
        except pymysql.Error as e:
            print(f"存储数据时发生错误：{e}")#如有错误发生，提示出错误信息
            self.conn.rollback()

        return item

    def close_spider(self, spider):
        print("结束！！！")
        self.cursor.close()
        self.conn.close()
