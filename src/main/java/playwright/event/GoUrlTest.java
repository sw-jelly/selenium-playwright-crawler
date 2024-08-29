package playwright.event;

import com.microsoft.playwright.*;

public class GoUrlTest {
    static final int REPEAT_TIME = 1000;

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        String baseUrl = "https://www.acmicpc.net/problem/";

        try(Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            BrowserContext context = browser.newContext()) {

            Page page = context.newPage();

            for(int i = 0; i < REPEAT_TIME; ++i) {
                page.navigate(baseUrl + (1000 + i));
            }
        }

        long endTime = System.nanoTime();
        int elapsedTime = (int) ((endTime - startTime) / 1_000_000);
        System.out.println("걸린 시간: " + elapsedTime + "ms");
    }
}
