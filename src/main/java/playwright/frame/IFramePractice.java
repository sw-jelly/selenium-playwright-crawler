package playwright.frame;

import com.microsoft.playwright.*;

public class IFramePractice {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://www.tcpschool.com/examples/tryit/tryhtml.php?filename=html_ref_tag_iframe_01");
            String src = "https://brand.nongshim.com/all_product/index";

            Frame frame = page.frame("iframeResult");
            frame.navigate(src);

            Locator elements = frame.locator("a, button, input[type='button'], input[type='submit']");

            for (Locator element : elements.all()) {
                if(!frame.url().equals(src)) {
                    System.out.println("현재 url : " + frame.url());
                    frame.navigate(src);
                }

                try {
                    element.click(new Locator.ClickOptions().setTimeout(1000).setForce(true));
                    System.out.println("클릭 성공! " + element.textContent());
                } catch(PlaywrightException e) {
                    System.out.println("클릭 실패 " + element.textContent());
                }
            }

            browser.close();
        }
    }
}
