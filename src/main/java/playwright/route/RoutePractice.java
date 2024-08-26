package playwright.route;

import java.io.*;
import com.microsoft.playwright.*;

public class RoutePractice {
    public static void main(String[] args) throws IOException {
        try (Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext()) {

            Page page = context.newPage();
            context.route("**/*.{png,jpg,jpeg,gif,svg,PNG,JPG,JPEG,GIF,SVG}*", Route::abort);
            page.navigate("https://www.naver.com");

            System.out.println("Press Enter to exit...");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            reader.readLine();
        }
    }
}
