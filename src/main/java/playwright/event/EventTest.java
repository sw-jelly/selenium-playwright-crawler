package playwright.event;

import com.microsoft.playwright.*;


public class EventTest {

    static final int REPEAT_TIME = 50;
    static final String XPATH = "xpath=";

    private static class Click {
        private String url;
        private String[] elements;

        public Click(String url, String... elements) {
            this.url = url;
            this.elements = elements;
        }
    }

    static Click[] clicks = {
            new Click("https://sparrow.im/kr/service/source-code/", "/html/body/div[4]/section[2]/div/div[2]/div/div[1]/div/div/a"),
            new Click("https://www.fasoo.com/?lang=kr", "/html/body/div[2]/section[1]/div/div/div/div/div/div/div/div[1]/div[8]/a/div/div[3]", "/html/body/main/div/div[1]/section[2]/div/div/div/div/div/nav[1]/ul/li[2]/a"),
            new Click("https://sparrow.im/kr/company/summary/", "/html/body/div[3]/section[1]/div/div/div/div[3]/div/div/div[1]", "/html/body/div[3]/section[1]/div/div/div/div[3]/div/div/div[1]/div[2]/div/div[2]/div/ul/li[2]/div/a/div/div"),
    };

    public static void main(String[] args) {

        long startTime = System.nanoTime();

        try(Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            BrowserContext context = browser.newContext()) {
            context.clearCookies();

            Page page = context.newPage();

            for(int i = 0; i < REPEAT_TIME; ++i) {
                for(Click click : clicks) {
                    page.navigate(click.url);
                    for(String element : click.elements) {
                        page.click(XPATH + element);
                    }
                }
//                System.out.println("page " + (i + 1) + " completed : " + (int) ((System.nanoTime() - startTime) / 1_000_000) + "ms");
            }
        }

        long endTime = System.nanoTime();
        int elapsedTime = (int) ((endTime - startTime) / 1_000_000);
        System.out.println("걸린 시간: " + elapsedTime + "ms");
    }
}
