package com.lyyco.rays.service.crawler;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 机票相关爬取
 * Author liyangyang
 * 2018/9/13
 */
public class AirPortProcessor implements PageProcessor{
    //部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site
            .me()
            .setCharset("utf-8")
            .setDomain("https://flights.ctrip.com/process/FlightStatus/FindByFlightNoWithJson?flightNo=MU578&date=20180911&searchKey=0AE28CB6479DFB64922C12D8A190B772C648C01913C7D635D2046A982EAA973A2AD1332E3E74952D")//设置域名，需设置域名后，addCookie才可生效
            .addCookie("dotcomt_user","YangyangLi0528")
            .addHeader("referer","https://flights.ctrip.com/actualtime/fno-MU578/t20180911")
            .addHeader("authority","flights.ctrip.com")
            .addHeader("method","GET")
            .addHeader("path","/process/FlightStatus/FindByFlightNoWithJson?flightNo=MU578&date=20180911&searchKey=0AE28CB6479DFB64922C12D8A190B772C648C01913C7D635D2046A982EAA973A2AD1332E3E74952D")
            .addHeader("scheme","https")
            .addHeader("accept","*/*")
            .addHeader("accept-encoding","gzip, deflate, br")
            .addHeader("content-type","application/x-www-form-urlencoded; charset=utf-8")
            .addHeader("cookie","_abtest_userid=d68246d1-7511-4640-9587-7deeb21536e4; _RSG=oVa9xcN21r6PfM5tR9jG1B; _RDG=280201bcac4fe32dd7202c5421eef2a47e; _RGUID=6d410979-bb26-47f2-b050-b496f7156a59; _ga=GA1.2.597947599.1509686614; DestinationType=1; GUID=09031139310933121327; MKT_Pagesource=PC; StartCity_SH=SHStartCity=2; _gid=GA1.2.1679141086.1536636080; Customer=HAL=ctrip_cn; ibu_wws_c=1539249523145%7Czh-cn; __utma=13090024.597947599.1509686614.1536657541.1536720519.68; __utmz=13090024.1536720519.68.53.utmcsr=ctrip.com|utmccn=(referral)|utmcmd=referral|utmcct=/; IntHotelCityID=6664split%25u963F%25u8BA9%25uFF0C%25u65B0%25u963F%25u57FA%25u5766%25u5927%25u533A%25uFF0C%25u6CD5%25u56FDsplitagensplit2018-10-06split2018-10-12splitFsplitsplit2split2split1; _jzqco=%7C%7C%7C%7C%7C1.48361213.1509686613998.1536720530385.1536720534452.1536720530385.1536720534452.0.0.0.417.417; __zpspc=9.90.1536720518.1536720534.5%231%7C%7C%7C%7C%7C%23; _RF1=47.75.103.97; _bfi=p1%3D151001%26p2%3D600005579%26v1%3D637%26v2%3D636; appFloatCnt=13; manualclose=1; _bfa=1.1509592011975.3e5j1u.1.1536720515209.1536803940719.105.638.229014; _bfs=1.3")
            .addHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36")
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setTimeOut(8000);
    @Override
    public void process(Page page) {
        /*
        部分二：定义如何抽取页面信息，并保存下来
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
}
