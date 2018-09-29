package com.lyyco.rays.service.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Author liyangyang
 * 2018/9/29
 */
public class PhantomJsDemo {
    public static void main(String...args){
        DesiredCapabilities dcaps = new DesiredCapabilities();
        //ssl支持
        dcaps.setCapability("acceptSslCerts",true);
        //截屏
        dcaps.setCapability("takesScreenshot",true);
        //css搜索支持
        dcaps.setCapability("cssSelectorsEnabled",true);
        //js支持
        dcaps.setJavascriptEnabled(true);
        //驱动支持
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"D:\\Code\\phantomjs\\bin\\phantomjs.exe");
        //创建无界面浏览器对象
        PhantomJSDriver driver = new PhantomJSDriver(dcaps);
//        // 让浏览器访问 携程
//        driver.get("http://flights.ctrip.com/actualtime/");
//        // 获取 网页的 title
//        System.out.println(" Page title is: " +driver.getTitle());
//        // 通过 id 找到 input 的 DOM
//        WebElement element = driver.findElement(By.id("WZ_flightNo"));
//        // 输入关键字
//        element.sendKeys("MU578");
//        /*// 提交 input 所在的 form
//        element.submit();*/
//        // 点击查询按钮 WZ_btn_search
//        WebElement queryElement = driver.findElement(By.id("WZ_btn_search"));
//        queryElement.click();
//        // 通过判断 title 内容等待搜索页面加载完毕，间隔秒
//       /* new WebDriverWait(driver, 30).until(new ExpectedCondition() {
//            @Override
//            public Object apply(Object input) {
//                return ((WebDriver)input).getTitle().toLowerCase().startsWith("东鹏瓷砖");
//            }
//        });*/
//        // 显示搜索结果页面的 title
//        System.out.println(" Page title is: " +driver.getTitle());
//        Object result = driver.executePhantomJS("var url = 'http://flights.ctrip.com/actualtime/';\n" +
//                "var page = require('webpage').create();\n" +
//                "\n" +
//                "page.onResourceRequested = function(request){\n" +
//                "\tconsole.log('Request:' + JSON.stringify(request,undefined,4));\n" +
//                "};\n" +
//                "page.onResourceReceived = function(response){console.log('Response:' + JSON.stringify(response,undefined,4));};\n" +
//                "page.open(url);");
//        System.out.println(result);

        Object resultKey = driver.executePhantomJS("var condition = '{\"SearchKey\":\"0AE28C64B79DFB643C1966E4A02DCAFD153D9F2265ACB91942259FA65DCA15A92263CFECF5546269\"}';\n" +
                "(function(u,r,k,t){var E44=1,r44E=1;E44=E44*=eval(function(p,a,c,k,e,d){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\\\b'+e(c)+'\\\\b','g'),k[c]);return p;}('1(0.2(4)*3)',5,5,'Math|parseInt|cos|0xa|5'.split('|'),0,{}));E44=E44+=eval(function(p,a,c,k,e,d){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\\\b'+e(c)+'\\\\b','g'),k[c]);return p;}('1(0.2(4)*3)',5,5,'Math|parseInt|exp|0xa|5'.split('|'),0,{}));if(E44<0)E44=-E44; while(E44>30)E44=E44%10;r44E=r44E-=eval(function(p,a,c,k,e,d){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\\\b'+e(c)+'\\\\b','g'),k[c]);return p;}('1(0.2(4)*3)',5,5,'Math|parseInt|sin|0xa|4'.split('|'),0,{}));if(r44E<0)r44E=-r44E; while(r44E>30)r44E=r44E%10;(function(r, w, y, zz, x) {eval(function(p,a,c,k,e,d){e=function(c){return(c<a?\"\":e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\\\b'+e(c)+'\\\\b','g'),k[c]);return p;}('m 5$=[\\'\\',\\'b\\',\\'f\\',\\'e\\',\\'h\\'],l,7;g(6[5$[1]]){l=7.9(5$[0]);c=l.8(d,a);l.8(i,j,c);6[5$[4]]=6[5$[1]](6[5$[2]]=6[5$[2]][5$[3]](7,l.k(5$[0])))}',23,23,'|||||_|w|r|splice|split|0x1|simpleLoader||y|replace|condition|if|flightLoader|x|0x0|join||var'.split('|'),0,{}))})(eval(function(p,a,c,k,e,d){e=function(c){return(c<a?\"\":e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\\\b'+e(c)+'\\\\b','g'),k[c]);return p;}('(1.0(/\"3\":\"(.+?)\"/)||[\\'\\']).2();',4,4,'match|condition|pop|SearchKey'.split('|'),0,{})), window, r44E, eval(function(p,a,c,k,e,d){e=function(c){return c.toString(36)};if(!''.replace(/^/,String)){while(c--){d[c.toString(a)]=k[c]||c.toString(a)}k=[function(e){return d[e]}];e=function(){return'\\\\w+'};c=1};while(c--){if(k[c]){p=p.replace(new RegExp('\\\\b'+e(c)+'\\\\b','g'),k[c])}}return p}('!0(a){a||($=n=i=h=e)}(0(a,b,c){f g[6.7(2*a+b,2*a+c,2*a-1,2*a-3,2*a+9,2*a+5,2*a+c,2*a+c-1)][6.7(2*a+4,a+m,l,j)]}(k,8,d,9,5));',24,24,'function||||||String|fromCharCode||16||||11|null|return|this|simpleLoader|window|102|50|101|64|module'.split('|'),0,{})), E44)})();\n" +
                "console.log(condition);");
        System.out.println("SSSS" +resultKey);
    }
}
