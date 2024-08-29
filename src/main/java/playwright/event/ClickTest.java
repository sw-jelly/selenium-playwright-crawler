package playwright.event;

import com.microsoft.playwright.*;

public class ClickTest {
    static final int REPEAT_TIME = 20;
    static final String XPATH = "xpath=";

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        String url = "https://www.fasoo.com/newsletter";
        String baseElement = "/html/body/div[2]/section[3]/div/div/div/div[2]/div/div/article";

        try(Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            BrowserContext context = browser.newContext()) {

            Page page = context.newPage();
            page.navigate(url);

            int articleNum = 5;
            String path;
            for(int i = 0; i < REPEAT_TIME; ++i) {
                for(int j = 1; j <= articleNum; ++j) {
                    path = XPATH + baseElement + getArticleIdx(j);
                    page.click(path);
                    page.click(path);
                }
            }
        }

        long endTime = System.nanoTime();
        int elapsedTime = (int) ((endTime - startTime) / 1_000_000);
        System.out.println("걸린 시간: " + elapsedTime + "ms");
    }

    private static String getArticleIdx(int j) {
        return "[" + j + "]/div/section[1]";
    }
}
