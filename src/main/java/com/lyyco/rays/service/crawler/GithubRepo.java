package com.lyyco.rays.service.crawler;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * 使用注解编写爬虫
 * com.lyyco.rays.service.crawler
 * @Author liyangyang
 * 2018/3/26
 */
//最终要抓取的URL
@TargetUrl("https://github.com/\\w+/\\w+")
//为了发现最终URL而需要范访问的页面
@HelpUrl("https://github.com/\\w+")
public class GithubRepo {

    //作用于字段，表示使用这个抽取规则，将抽取到的结果保存到这个字段中---XPath表示的抽取规则
    @ExtractBy(value = "//h1[@class='entry-title public']/strong/a/text()",notNull = true)
//  @ExtractBy(value = "div.BlogContent",type = ExtractBy.Type.Css)//Css选择器表示的抽取规则
    private String name;
    //从URL中抽取；仅支持正则表达式作为抽取规则
    @ExtractByUrl("https://github\\.com/(\\w+)/.*")
    private String author;

    @ExtractBy("//div[@id='readme']/tidtText()")
    private String readme;

    public static void main(String...args){
        OOSpider.create(Site.me().setSleepTime(1000),
                new ConsolePageModelPipeline(),GithubRepo.class)
//                .addPageModel(PageModelPipeline pageModel,Class... pageModels)
                .addUrl("https://github.com/YangyangLi0528")
                .thread(4)
                .run();
    }
}
