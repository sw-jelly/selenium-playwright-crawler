package selenium.event;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GoUrlTest {
    static final int REPEAT_TIME = 1000;

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);

        String baseUrl = "https://www.acmicpc.net/problem/";

        for(int i = 0; i < REPEAT_TIME; ++i) {
            driver.get(baseUrl + (1000 + i));
        }

        driver.quit();

        long endTime = System.nanoTime();
        int elapsedTime = (int) ((endTime - startTime) / 1_000_000);
        System.out.println("걸린 시간: " + elapsedTime + "ms");
    }
}
