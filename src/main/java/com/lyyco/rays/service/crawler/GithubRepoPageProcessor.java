package com.lyyco.rays.service.crawler;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import javax.management.JMException;

/**
 * github爬虫
 * com.lyyco.rays.service.crawler
 *
 * @Author liyangyang
 * 2018/3/23
 */
public class GithubRepoPageProcessor implements PageProcessor{
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site
            .me()
            .setCharset("utf-8")
//            .setDomain("github.com")//设置域名，需设置域名后，addCookie才可生效
//            .addCookie("dotcomt_user","YangyangLi0528")
//            .addHeader("Referer","https://github.com")

            .setRetryTimes(3)
            .setSleepTime(1000)
            .setTimeOut(10000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        /*
        部分二：定义如何抽取页面信息，并保存下来
        用于获取所有满足"(https:/ /github\.com/\w+/\w+)"这个正则表达式的链接，
        page.addTargetRequests()则将这些链接加入到待抓取的队列中去。
         */
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-])").all());
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='public']/strong/a/text()").toString());
        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }
        // 部分三：从页面发现后续的url地址来抓取
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws JMException {
//        Spider.create(new GithubRepoPageProcessor())
//                .addPipeline(new JsonFilePipeline("D:\\usr\\webMagic"))
//                //从"https://github.com/YangyangLi0528"开始抓
//                .addUrl("https://github.com/YangyangLi0528")
//                .thread(4).run();

        //为项目添加监控
        Spider oschinaSpider = Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://github.com/YangyangLi0528");
        SpiderMonitor.instance().register(oschinaSpider);

        //配置代理
//        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
////        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
////                new Proxy("47.88.220.219",50001,"username","password")
////        ));
//        oschinaSpider.setDownloader(httpClientDownloader);


        oschinaSpider.thread(4).start();
    }
}

