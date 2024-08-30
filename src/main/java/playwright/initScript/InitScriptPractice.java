package playwright.initScript;

import com.microsoft.playwright.*;

public class InitScriptPractice {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create();
             Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
             BrowserContext context = browser.newContext()) {

            context.addInitScript("window.print = () => console.log('print override 성공!');");
            context.onConsoleMessage(m -> System.out.println(m.text()));

            Page page1 = context.newPage();
            page1.navigate("https://www.allrecipes.com/recipe/150559/one-pan-taco-dinner/");

            Page page2 = context.waitForPage(() -> page1.click("xpath=/html/body/main/article/div[2]/div/div[2]/div[2]/div/div[3]"));
            page2.click("xpath=/html/body/article/div[2]/button");
        }
    }
}
