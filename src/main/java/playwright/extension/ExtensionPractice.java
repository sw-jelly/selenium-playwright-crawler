package playwright.extension;

import com.microsoft.playwright.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ExtensionPractice {
    public static void main(String[] args) throws IOException {

        String extensionPath = "path\\of\\your\\algo-plus\\dist";

        try (Playwright playwright = Playwright.create();
             Browser browser = playwright.chromium().launch(new BrowserType
                     .LaunchOptions()
                     .setHeadless(false)
                     .setArgs(List.of(
                             "--disable-extensions-except=" + extensionPath,
                             "--load-extension=" + extensionPath
                     ))
             );
             BrowserContext context = browser.newContext()) {

            Page page = context.newPage();
            page.navigate("https://www.acmicpc.net/problem/1010");
            System.out.println("Page loaded with extension.");

            // 로그인 및 시크릿 모드에서 확장 허용 수행 후 엔터
            System.out.println("Press Enter to continue...");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            reader.readLine();

            page.navigate("https://www.acmicpc.net/submit/1010?solve=true");
            System.out.println("Press Enter to exit...");
            reader.readLine();
        }
    }
}
