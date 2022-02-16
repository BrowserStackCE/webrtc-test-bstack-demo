import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebRTCTestRunner implements Runnable {

    // public static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    // public static final String ACCESS_KEY =
    // System.getenv("BROWSERSTACK_ACCESS_KEY");

    public static final String HUB_URL = "https://hub-cloud.browserstack.com/wd/hub";
    WebDriver driver = null;
    String appRTCURL = "https://localhost/?wshpp=localhost:8089&wstls=true";
    String roomId = null;
    boolean joinExisting = false;
    int userSelection = 0;
    long duration = 15000;
    MutableCapabilities options = null;

    public WebRTCTestRunner(final MutableCapabilities options, final String roomId, final boolean joinExisting,
            final long duration, final int userSelection) {
        this.options = options;
        this.roomId = roomId;
        this.joinExisting = joinExisting;
        this.duration = duration;
        this.userSelection = userSelection;
    }

    // test
    public final void run() {

        try {
            // Creating Remote WebDriver based on the capabilites defined in
            // WebRTCConf.java.
            driver = new RemoteWebDriver(new URL(HUB_URL), options);

        //    driver.get("http://samiran11.browserstack.com/sample_960x400_ocean_with_audio.mjpeg");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e1) {
                
                e1.printStackTrace();
            }
            ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());

            // Creating new WebRTC Room with generated roomID
            if (!joinExisting) {

                if (userSelection != 3) {
                    driver.manage().window().maximize();
                }

                // driver.get("https://appr.tc/");
                driver.get(appRTCURL);
                WebElement roomIdElement = driver.findElement(By.id("room-id-input"));
                roomIdElement.clear();
                roomIdElement.sendKeys(roomId);
                WebElement joinElement = driver.findElement(By.id("join-button"));
                joinElement.click();

            }
            // Joining the WebRTC Room with generated roomID
            else {
                // if (userSelection != 3) {
                //     driver.manage().window().maximize();
                // }

                // driver.get(appRTCURL + "r/" + roomId);

                // WebElement joinElement = driver.findElement(By.id("confirm-join-button"));
                // WebDriverWait wait = new WebDriverWait(driver, 10);
                // wait.until(ExpectedConditions.elementToBeClickable(joinElement));
                // joinElement.click();

                if (userSelection != 3) {
                    driver.manage().window().maximize();
                }

                // driver.get("https://appr.tc/");
                driver.get(appRTCURL);
                WebElement roomIdElement = driver.findElement(By.id("room-id-input"));
                roomIdElement.clear();
                roomIdElement.sendKeys(roomId);
                WebElement joinElement = driver.findElement(By.id("join-button"));
                joinElement.click();

            }

            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
