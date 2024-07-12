# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class GetAcItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    problem_id = scrapy.Field()
    problem_name = scrapy.Field()
    problem_rate = scrapy.Field()
    url = scrapy.Field()


    contest_name = scrapy.Field()
    begin_time = scrapy.Field()
    length = scrapy.Field()
    rating = scrapy.Field()
    full_url = scrapy.Field()


