import scrapy
from scrapy_get.get_ac.get_ac.items import GetAcItem

class GetdataAcSpider(scrapy.Spider):
    name = "getdata_ac"
    allowed_domains = ["https://atcoder.jp/contests"]
    start_urls = [f"https://atcoder.jp/contests/archive?page={num}" for num in range(1,14)]

    def parse(self, response):
        #print(response.text)
        #print(response.status)

        item = GetAcItem()

        for box in response.xpath('//tbody/tr'):
            contest_name = box.xpath('.//td[2]/a/text()').getall()

            begin_time = box.xpath('.//td[1]/a/time/text()').getall()

            length = box.xpath('.//td[3]/text()').getall()

            rating = box.xpath('.//td[4]/text()').getall()
            data_list = list(zip(contest_name, begin_time, length, rating))
            #print(data_list)
            item['data_list'] = data_list
            yield item



