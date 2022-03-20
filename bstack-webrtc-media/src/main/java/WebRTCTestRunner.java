import java.io.File;
import java.net.URL;
import java.util.HashMap;

import com.browserstack.local.Local;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebRTCTestRunner implements Runnable {

    public static final String HUB_URL = "https://hub-cloud.browserstack.com/wd/hub";
    WebDriver driver = null;
    AndroidDriver<WebElement> androidDriver = null;
    String appRTCURL = "https://webrtc.github.io/samples/src/content/devices/input-output/";
    String deviceType = null;
    String userSelection = null;
    MutableCapabilities options = null;

    public WebRTCTestRunner(final MutableCapabilities options, final String deviceType, final String userSelection) {
        this.options = options;
        this.deviceType = deviceType;
        this.userSelection = userSelection;
    }

    public final void run() {
        Local bsLocal = new Local();

        try {

            // Creating Remote WebDriver based on the capabilites defined in
            // WebRTCConf.java.

            if (userSelection.equalsIgnoreCase("1.3") || userSelection.equalsIgnoreCase("3.3")) {

                HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
                bsLocalArgs.put("key", System.getenv("BROWSERSTACK_ACCESS_KEY"));
                bsLocalArgs.put("f", Config.getLocalFolderPath());
                bsLocal.start(bsLocalArgs);
            }
            if (deviceType.equalsIgnoreCase("browser")) {
                driver = new RemoteWebDriver(new URL(HUB_URL), options);
                driver.manage().window().maximize();

                if (userSelection.equalsIgnoreCase("1.3") || userSelection.equalsIgnoreCase("3.3")) {

                    driver.get("http://" + System.getenv("BROWSERSTACK_USERNAME") +
                            ".browserstack.com/"
                            + Config.getCustomVideoFile());
                    driver.get("http://" + System.getenv("BROWSERSTACK_USERNAME") +
                            ".browserstack.com/"
                            + Config.getCustomAudioFile());

                    ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
                }
                driver.get(appRTCURL);
                Thread.sleep(5000);

            } else {
                androidDriver = new AndroidDriver<WebElement>(new URL(HUB_URL), options);
                if (userSelection.equalsIgnoreCase("5.3")) {
                    System.out.println("/data/local/tmp/" + Config.getCustomVideoFile());
                    System.out.println(Config.getLocalFolderPath()
                            + "/" + Config.getCustomVideoFile());

                    androidDriver.pushFile("/data/local/tmp/" + Config.getCustomVideoFile(),
                            new File(Config.getLocalFolderPath()
                                    + "/" + Config.getCustomVideoFile()));

                }
                androidDriver.get(appRTCURL);
                Thread.sleep(5000);
            }
            System.out.println("Test successfully executed!");
        } catch (Exception ex) {
            System.out.println("Test execution failed!");
            ex.printStackTrace();
        } finally {
            try {
                bsLocal.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (driver != null) {

                driver.quit();
            }
            if (androidDriver != null) {

                androidDriver.quit();
            }
        }
    }
}