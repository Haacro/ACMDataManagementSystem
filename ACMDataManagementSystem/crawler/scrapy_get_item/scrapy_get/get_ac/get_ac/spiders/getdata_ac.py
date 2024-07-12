import scrapy
from scrapy_get.get_ac.get_ac.items import GetAcItem
import re
from collections import OrderedDict
from scrapy import Request



class GetdataAcSpider(scrapy.Spider):
    name = "getdata_ac"
    allowed_domains = ["atcoder.jp"]
    start_urls = [f"https://atcoder.jp/contests/archive?page={num}" for num in range(1,14)]

    def parse(self, response):
        for box in response.xpath('//tbody/tr'):
            contest_name = box.xpath('.//td[2]/a/text()').getall()
            begin_time = box.xpath('.//td[1]/a/time/text()').getall()
            length = box.xpath('.//td[3]/text()').getall()
            rating = box.xpath('.//td[4]/text()').getall()

            url = box.xpath('.//td[2]/a').attrib['href']
            if url:
                full_url = 'https://atcoder.jp' + url
                ac_info1 = {
                    'contest_name': contest_name,
                    'begin_time': begin_time,
                    'length': length,
                    'rating': rating,
                    'full_url': full_url

                }
                #item = GetAcItem()
                #item.update(ac_info1)

                #yield item
                yield scrapy.Request(url=full_url, callback=self.parse_two)

    def parse_two(self, response):
        tasks_url = response.url + '/tasks'

        for box in response.xpath('//table/tbody/tr'):
            problem_rate_text = box.xpath('.//td[2]/text()').get()

            # Extract integer from problem_rate_text or set to 0 if not an integer
            try:
                problem_rate = int(problem_rate_text)
            except (ValueError, TypeError):
                problem_rate = 0

            ac_info = {
                "problem_rate": problem_rate
            }

            yield scrapy.Request(url=tasks_url, callback=self.parse_three, meta={'ac_info': ac_info})

    def parse_three(self, response):
        ac_info = response.meta.get('ac_info', {})
        for box in response.xpath('//tbody/tr'):
            problem_id = box.xpath('.//td[1]/a/text()').get()
            problem_name = box.xpath('.//td[2]/a/text()').get()
            url_two = box.xpath('.//td[1]/a/@href').get()

            if problem_id and problem_name and url_two:
                full_url_two = 'https://atcoder.jp' + url_two
                ac_info.update({
                    "problem_id": problem_id.strip(),
                    "problem_name": problem_name.strip(),
                    "url": full_url_two
                })
                item = GetAcItem()
                item.update(ac_info)
                #print(ac_info)

                yield item









