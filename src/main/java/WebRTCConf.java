import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariOptions;

public class WebRTCConf {

    public static Thread roomCreationThread = null;
    public static Thread roomJoiningThread = null;

    public static int getRoomID() {
        Random random = new Random();
        int roomId = random.nextInt(500000);
        roomId += 100000;

        return roomId;
    }

    public static MutableCapabilities getFireFoxConfiguration() {

        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("media.navigator.streams.fake", true);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(firefoxProfile);
        options.setCapability("media.navigator.streams.fake", true);
        options.setCapability("browser", "Firefox");
        options.setCapability("browser_version", "latest");
        options.setCapability("os", "Windows");
        options.setCapability("os_version", "10");
        options.setCapability("build", "WebRTC Dummy Video Call Build - Chrome-FireFox");
        options.setCapability("name", "WebRTC Room Joining - FireFox Browser");
        options.setCapability("browserstack.idleTimeout", 300);
        options.setCapability("browserstack.user", System.getenv("BROWSERSTACK_USERNAME"));
        options.setCapability("browserstack.key", System.getenv("BROWSERSTACK_ACCESS_KEY"));

        return options;
    }

    public static MutableCapabilities getChromeConfiguration() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-device-for-media-stream", "--use-fake-ui-for-media-stream");
        options.setCapability("browser", "Chrome");
        options.setCapability("browser_version", "latest");
        options.setCapability("os", "Windows");
        options.setCapability("os_version", "10");
        options.setCapability("build", "WebRTC Dummy Video Call Build - Chrome-FireFox");
        options.setCapability("name", "WebRTC Room Creation - Chrome Browser");
        options.setCapability("browserstack.idleTimeout", 300);
        options.setCapability("browserstack.user", System.getenv("BROWSERSTACK_USERNAME"));
        options.setCapability("browserstack.key", System.getenv("BROWSERSTACK_ACCESS_KEY"));

        return options;
    }

    public static MutableCapabilities getEdgeConfiguration() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-device-for-media-stream", "--use-fake-ui-for-media-stream");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
        options.setCapability("browser", "Edge");
        options.setCapability("browser_version", "latest");
        options.setCapability("os", "Windows");
        options.setCapability("os_version", "10");
        options.setCapability("build", "WebRTC Dummy Video Call Build - Edge-Safari");
        options.setCapability("name", "WebRTC Room Creation - Edge Browser");
        options.setCapability("browserstack.idleTimeout", 300);
        options.setCapability("browserstack.user", System.getenv("BROWSERSTACK_USERNAME"));
        options.setCapability("browserstack.key", System.getenv("BROWSERSTACK_ACCESS_KEY"));

        return options;
    }

    public static MutableCapabilities getSafariConfiguration() {

        SafariOptions options = new SafariOptions();
        options.setUseTechnologyPreview(true);

        options.setCapability("browser", "Safari");
        options.setCapability("browser_version", "latest");
        options.setCapability("os", "OS X");
        options.setCapability("os_version", "Big Sur");
        options.setCapability("build", "WebRTC Dummy Video Call Build - Edge-Safari");
        options.setCapability("name", "WebRTC Room Joining - Safari Browser");
        options.setCapability("browserstack.idleTimeout", 300);
        options.setCapability("browserstack.user", System.getenv("BROWSERSTACK_USERNAME"));
        options.setCapability("browserstack.key", System.getenv("BROWSERSTACK_ACCESS_KEY"));

        return options;
    }

    public static MutableCapabilities getiOSConfiguration() {

        SafariOptions options = new SafariOptions();
        options.setUseTechnologyPreview(true);

        options.setCapability("device", "iPhone 12");
        options.setCapability("real_mobile", "true");
        options.setCapability("os_version", "14");
        options.setCapability("autoAcceptAlerts", "true");
        options.setCapability("build", "WebRTC Dummy Video Call Build - Android-iOS");
        options.setCapability("name", "WebRTC Room Joining - iOS");
        options.setCapability("browserstack.idleTimeout", 300);
        options.setCapability("browserstack.user", System.getenv("BROWSERSTACK_USERNAME"));
        options.setCapability("browserstack.key", System.getenv("BROWSERSTACK_ACCESS_KEY"));

        return options;
    }

    public static MutableCapabilities getAndroidConfiguration() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--use-fake-device-for-media-stream", "--use-fake-ui-for-media-stream");

        options.setCapability("os_version", "11.0");
        options.setCapability("device", "Samsung Galaxy S21");
        options.setCapability("real_mobile", "true");
        options.setCapability("autoGrantPermissions", "true");
        options.setCapability("build", "WebRTC Dummy Video Call Build - Android-iOS");
        options.setCapability("name", "WebRTC Room Creation - Android");
        options.setCapability("browserstack.idleTimeout", 300);
        options.setCapability("browserstack.user", System.getenv("BROWSERSTACK_USERNAME"));
        options.setCapability("browserstack.key", System.getenv("BROWSERSTACK_ACCESS_KEY"));

        return options;
    }

    public static void createRoom(final MutableCapabilities config, final int roomId, final boolean flag,
            final int waitingTime, final int userSelection) {

        roomCreationThread = new Thread(
                new WebRTCTestRunner(config, String.valueOf(roomId), flag, waitingTime, userSelection));
        roomCreationThread.start();

    }

    public static void joinRoom(final MutableCapabilities config, final int roomId, final boolean flag,
            final int waitingTime, final int userSelection) {

        roomJoiningThread = new Thread(
                new WebRTCTestRunner(config, String.valueOf(roomId), flag, waitingTime, userSelection));
        roomJoiningThread.start();
    }

    public static void main(final String[] args) {
        // Disabling Selenium messages.
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        Scanner sc = new Scanner(System.in);

        try {
            // Generating Random new RoomID
            int roomId = getRoomID();

            // Getting User's Selection
            System.out.println("Please Select a Browser Combination to initiate the WebRTC test on BrowserStack : ");
            System.out.println("1 : Press 1 to initiate WebRTC test on Google Chrome and Firefox Browser Combination ");
            System.out.println("2 : Press 2 to initiate WebRTC test on Edge and Safari Browser Combination ");
            System.out.println("3 : Press 3 to initiate WebRTC test on Android and iOS Combination");
            int userSelection = sc.nextInt();

            switch (userSelection) {
            case 1:
                System.out.println("Your test would execute on Chrome-Firefox Browser Combination");
                // Creating capabilities for Chrome Browser.
                MutableCapabilities chromeConfiguration = getChromeConfiguration();
                // Creating capabilities for FireFox Browser.
                MutableCapabilities fireFoxConfiguration = getFireFoxConfiguration();

                // Creating the WebRTC Room on Chrome Browser.
                System.out.println("Creating a Room on Chrome Browser");
                createRoom(chromeConfiguration, roomId, false, 30000, userSelection);

                // Waiting for other user to join the above created WebRTC room.
                Thread.sleep(15000);

                // Joining the above created Room on FireFox Browser.
                System.out.println("Joining the Room on Firefox Browser");
                joinRoom(fireFoxConfiguration, roomId, true, 20000, userSelection);

                break;

            case 2:
                System.out.println("Your test would execute on Edge-Safari Browser Combination");
                // Creating capabilities for Edge Browser.
                MutableCapabilities edgeConfiguration = getEdgeConfiguration();
                // Creating capabilities for Safari Browser.
                MutableCapabilities safariConfiguration = getSafariConfiguration();

                // Creating the WebRTC Room on Edge Browser.
                createRoom(edgeConfiguration, roomId, false, 30000, userSelection);

                // Waiting for other user to join the above created WebRTC room.
                Thread.sleep(15000);

                // Joining the above created Room on Safari Browser.
                System.out.println("Joining the Room on Safari Browser");
                joinRoom(safariConfiguration, roomId, true, 20000, userSelection);
                break; // optional

            case 3:
                System.out.println("Your test would execute on Android-iOS Combination");
                // Creating capabilities for Android Mobile Browser.
                MutableCapabilities AndroidConfiguration = getAndroidConfiguration();
                // Creating capabilities for iOS Mobile Browser.
                MutableCapabilities iOSConfiguration = getiOSConfiguration();

                // Creating the WebRTC Room on Android Mobile Browser.
                System.out.println("Creating a Room on Android Mobile Browser");
                createRoom(AndroidConfiguration, roomId, false, 30000, userSelection);

                // Waiting for other user to join the above created WebRTC room.
                Thread.sleep(15000);

                // Joining the above created Room on iOS Mobile Browser.
                System.out.println("Joining the Room on iOS Mobile Browser");
                joinRoom(iOSConfiguration, roomId, true, 20000, userSelection);
                break;

            default:
                System.out.println("Please select a valid Option");
                break;
            }

            // Wait for threads to finish execution.
            roomCreationThread.join();
            roomJoiningThread.join();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            System.out.println("Test successfully executed!");
            sc.close();
        }
    }
}
