package selenium.click;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ClickPractice {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.naver.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "setTimeout(function() {\n" +
                        "    var xPathRes = document.evaluate(\"/html/body/div[2]/div[2]/div/div[1]/div[4]/div[1]/div/ul/li[3]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null);\n" +
                        "    if (xPathRes.singleNodeValue) {\n" +
                        "        console.log('Element found:', xPathRes.singleNodeValue);\n" +
                        "        xPathRes.singleNodeValue.click();\n" +
                        "    } else {\n" +
                        "        console.log('Element not found');\n" +
                        "    }\n" +
                        "}, 3000);"
        );

        By by2 = By.xpath("/html/body/div[2]/div[2]/div/div[1]/div[4]/div[2]/ul[1]/li[2]");
        wait.until(ExpectedConditions.presenceOfElementLocated(by2));
        WebElement element2 = driver.findElement(by2);
        element2.click();
    }
}
