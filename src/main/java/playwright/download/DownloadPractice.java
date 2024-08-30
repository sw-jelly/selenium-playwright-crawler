package playwright.download;

import com.microsoft.playwright.*;

public class DownloadPractice {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create();
             Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
             BrowserContext context = browser.newContext()) {

            Page page = context.newPage();
            page.onDownload(download -> {
                System.out.println("다운로드 발생!");
                download.cancel();
            });
            page.navigate("https://discord.com/download");
            page.click("xpath=/html/body/div[1]/div[2]/div/div[1]/div[2]/a");
        }
    }
}
