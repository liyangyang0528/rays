package com.lyyco.rays.service.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

/**
 * chromeDriver是谷歌的浏览器驱动，用来适配Selenium,有图形页面存在，在调试爬虫下载运行的功能的时候会相对方便
 * @description copy from https://www.cnblogs.com/null-qige/p/7844381.html
 */
public class TestChromeDriver {
	private static ChromeDriverService service;
	
	public static WebDriver getChromeDriver() throws IOException {
		System.setProperty("webdriver.chrome.driver","C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
		// 创建一个 ChromeDriver 的接口，用于连接 Chrome（chromedriver.exe 的路径可以任意放置，只要在newFile（）的时候写入你放的路径即可）
		service =  new ChromeDriverService.Builder().usingDriverExecutable(new File("D:\\usr\\chromedriver.exe")).usingAnyFreePort().build();
        service.start();
        // 创建一个 Chrome 的浏览器实例
        return new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
	}
	public static void main(String[] args) throws IOException {
		WebDriver driver = TestChromeDriver.getChromeDriver();
		// 让浏览器访问 携程
        driver.get("http://flights.ctrip.com/actualtime/");
        // 用下面代码也可以实现
        //driver.navigate().to("http://www.baidu.com");
        // 获取 网页的 title
        System.out.println(" Page title is: " +driver.getTitle());
        // 通过 id 找到 input 的 DOM
        WebElement element = driver.findElement(By.id("WZ_flightNo"));
        // 输入关键字
        element.sendKeys("MU");
        /*// 提交 input 所在的 form
        element.submit();*/
        // 点击查询按钮 WZ_btn_search
        WebElement queryElement = driver.findElement(By.id("WZ_btn_search"));
        queryElement.click();
        // 通过判断 title 内容等待搜索页面加载完毕，间隔秒
       /* new WebDriverWait(driver, 30).until(new ExpectedCondition() {
            @Override
            public Object apply(Object input) {
                return ((WebDriver)input).getTitle().toLowerCase().startsWith("东鹏瓷砖");
            }
        });*/
        // 显示搜索结果页面的 title
        System.out.println(" Page title is: " +driver.getTitle());
        // 关闭浏览器
        driver.quit();
        // 关闭 ChromeDriver 接口
        service.stop();
	}

}
