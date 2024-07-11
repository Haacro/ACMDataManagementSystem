# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class GetCfItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    #data_list = scrapy.Field()

    contest_id = scrapy.Field()
    problem_id = scrapy.Field()
    problem_name = scrapy.Field()
    url = scrapy.Field()
    tags = scrapy.Field()
    rating = scrapy.Field()
