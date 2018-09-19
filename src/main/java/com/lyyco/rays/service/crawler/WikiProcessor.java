package com.lyyco.rays.service.crawler;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.management.JMException;

/**
 * Author liyangyang
 * 2018/9/13
 */
public class WikiProcessor implements PageProcessor{

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    @Override
    public void process(Page page) {
        page.putField("IATA",
                page.getUrl()
                        .regex("https://en\\.wikipedia\\.org/wiki/List_of_airports_by_IATA_code/(\\w+)/.*")
                        .toString());
        page.putField("IATACODE",
                page.getHtml()
                        .xpath("//table[@class='wikitable sortable jquery-tablesorter']/tbody/tr/td"));
        //从页面发现后续的URL地址进行抓取
//        page.addTargetRequests(page.getHtml().links().regex("https://en\\.wikipedia\\.org/wiki/List_of_airports_by_IATA_code/(\\w+)/.*")));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String...args) throws JMException {
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
