package selenium.event;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EventTest {

    static final int REPEAT_TIME = 50;

    private static class Click {
        private String url;
        private String[] elements;

        public Click(String url, String... elements) {
            this.url = url;
            this.elements = elements;
        }
    }

    static Click[] clicks = {
            new Click("https://sparrow.im/kr/service/source-code/", "html/body/div[4]/section[2]/div/div[2]/div/div[1]/div/div/a"),
            new Click("https://www.fasoo.com/?lang=kr", "/html/body/div[2]/section[1]/div/div/div/div/div/div/div/div[1]/div[8]/a/div/div[3]", "/html/body/main/div/div[1]/section[2]/div/div/div/div/div/nav[1]/ul/li[2]/a"),
            new Click("https://sparrow.im/kr/company/summary/", "/html/body/div[3]/section[1]/div/div/div/div[3]/div/div/div[1]", "/html/body/div[3]/section[1]/div/div/div/div[3]/div/div/div[1]/div[2]/div/div[2]/div/ul/li[2]/div/a/div/div"),
    };


    public static void main(String[] args) {
        long startTime = System.nanoTime();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement element;
        By by;
        for(int i = 0; i < REPEAT_TIME; ++i) {
            driver.manage().deleteAllCookies();

            for(Click click : clicks) {
                driver.get(click.url);
                for(String el : click.elements) {
                    by = By.xpath(el);
                    wait.until(ExpectedConditions.presenceOfElementLocated(by));
                    element = driver.findElement(by);
                    element.click();
                }
            }

//            System.out.println("page " + (i + 1) + " completed : " + (int) ((System.nanoTime() - startTime) / 1_000_000) + "ms");
        }

        driver.quit();

        long endTime = System.nanoTime();
        int elapsedTime = (int) ((endTime - startTime) / 1_000_000);
        System.out.println("걸린 시간: " + elapsedTime + "ms");
    }
}
