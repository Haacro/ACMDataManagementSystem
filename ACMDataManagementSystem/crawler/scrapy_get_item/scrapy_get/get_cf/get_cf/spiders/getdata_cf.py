import scrapy
import re
from scrapy import Request
from scrapy_get.get_cf.get_cf.items import GetCfItem
from collections import OrderedDict

class GetdataCfSpider(scrapy.Spider):
    name = "getdata_cf"
    allowed_domains = ["codeforces.com"]
    start_urls = [f"https://codeforces.com/contests/page/{num}" for num in range(2, 20)]

    def parse(self, response):
        text = response.text


        contest_id = re.findall('data-contestId="(.*?)"', text)
        contest_name = response.xpath('//tr/td[1]/text()').getall()
        contest_name_cleaned = [name.strip() for name in contest_name if name.strip()]

        length = response.xpath('//tr/td[4]/text()').getall()
        length_cleaned = [time.strip() for time in length if time.strip()]

        begin_time = response.xpath('//td/span[@class="format-date"]/text()').get()
        cf_info = {"contest_id": contest_id}
        for box in response.xpath('//tr/td[1]'):
            url_one = box.xpath('./a/@href').get()
            #print(url_one)
            if url_one:
                full_url = 'https://codeforces.com' + url_one
                #print(full_url)

                yield Request(url=full_url, callback=self.parse_two)

    def parse_two(self, response):
        text = response.text
        contest_id = re.findall('/contest/(.*?)/problem', text)
        unique_contest_ids = list(OrderedDict.fromkeys(contest_id))

        problem_id = re.findall('/problem/(.*?)">', text)
        unique_problem_ids = list(OrderedDict.fromkeys(problem_id))

        problem_names = response.xpath('//tr/td[2]/div/div[1]/a/text()').getall()

        boxes = response.xpath('//tr/td[2]')
        for i, box in enumerate(boxes):
            url_two = box.xpath('./div/div[1]/a/@href').get()
            if url_two:
                full_url_two = 'https://codeforces.com' + url_two
                cf_info = {
                    "contest_id": unique_contest_ids[i % len(unique_contest_ids)],
                    "problem_id": unique_problem_ids[i % len(unique_problem_ids)],
                    "problem_name": problem_names[i],
                    "url": full_url_two
                }
                yield Request(url=full_url_two, callback=self.parse_three, meta={"cf_info": cf_info})


    def parse_three(self,response):
        text = response.text
        cf_info = response.meta['cf_info']

        problem_tag = response.xpath('//div[4]/div[2]/div/span/text()').getall()
        problem_tag_clean1 = [name.strip() for name in problem_tag if name.strip()]
        problem_tag_clean2 = [tag for tag in problem_tag_clean1 if tag != 'No tag edit access']

        rating = None
        for tag in problem_tag_clean2:
            if tag.startswith('*'):
                rating_str = tag[1:]
                if rating_str.isdigit():
                    rating = int(rating_str)
                break
        #print(problem_tag_clean2)
        #print(rating)
        problem_name = re.findall('<div class="header"><div class="title">(.*?)</div>', text)
        #print(problem_name)

        if problem_name:
            cf_info['problem_name'] = problem_name[0].strip()

        cf_info['tags'] = problem_tag_clean2
        cf_info['rating'] = rating
        item = GetCfItem()
        item.update(cf_info)
        #print("Parsed info:", cf_info)

        yield item
