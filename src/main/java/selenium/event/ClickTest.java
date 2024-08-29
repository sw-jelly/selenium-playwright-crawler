package selenium.event;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClickTest {
    static final int REPEAT_TIME = 20;

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        String url = "https://www.fasoo.com/newsletter";
        String baseElement = "/html/body/div[2]/section[3]/div/div/div/div[2]/div/div/article";

        WebElement element;
        By by;
        driver.get(url);
        int articleNum = 5;
        for(int i = 0; i < REPEAT_TIME; ++i) {
            for(int j = 1; j <= articleNum; ++j) {
                by = By.xpath(baseElement + getArticleIdx(j ));
                wait.until(ExpectedConditions.elementToBeClickable(by));
                element = driver.findElement(by);
                element.click();
                element.click();
            }
        }

        driver.quit();

        long endTime = System.nanoTime();
        int elapsedTime = (int) ((endTime - startTime) / 1_000_000);
        System.out.println("걸린 시간: " + elapsedTime + "ms");
    }

    private static String getArticleIdx(int j) {
        return "[" + j + "]/div/section[1]";
    }
}
