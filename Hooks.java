package hooks;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.*;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            try {
                // Create folder if not exists
                File folder = new File("FailedScreenshots");
                if (!folder.exists()) {
                    folder.mkdir();
                }

                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String fileName = scenario.getName().replace(" ", "_") + "_" + timestamp + ".png";

                FileUtils.copyFile(src, new File(folder, fileName));

                System.out.println("📸 Screenshot saved: " + fileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
