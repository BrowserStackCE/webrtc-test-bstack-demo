import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariOptions;

public class WebRTCConf {

    public static Thread roomCreationThread = null;

    public static MutableCapabilities getCommonCaps() {
        MutableCapabilities options = new MutableCapabilities();

        options.setCapability("browserstack.idleTimeout", 300);
        options.setCapability("browserstack.user", System.getenv("BROWSERSTACK_USERNAME"));
        options.setCapability("browserstack.key", System.getenv("BROWSERSTACK_ACCESS_KEY"));
        options.setCapability("browserstack.debug", true);

        return options;
    }

    public static MutableCapabilities getFireFoxConfiguration(final String userSelection) {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(firefoxProfile);
        options.addPreference("media.navigator.streams.fake", true);
        options.addPreference("media.autoplay.default", 0);
        options.addPreference("media.navigator.permission.disabled", true);
        options.setCapability("browser", "Firefox");
        options.setCapability("browser_version", "latest");
        options.setCapability("os", "Windows");
        options.setCapability("os_version", "10");
        options.setCapability("build", "WebRTC Testing - FireFox");
        options.setCapability("name", "WebRTC with Fake Media");
        MutableCapabilities commonCaps = getCommonCaps();
        options.merge(commonCaps);

        return options;
    }

    public static MutableCapabilities getChromeConfiguration(final String userSelection) throws Exception {
        ChromeOptions options = new ChromeOptions();

        if (userSelection.equalsIgnoreCase("1.1")) {
            options.addArguments(
                    "--use-fake-device-for-media-stream", "--use-fake-ui-for-media-stream");
            options.setCapability("name", "WebRTC with Fake media");
        } else if (userSelection.equalsIgnoreCase("1.2")) {
            options.addArguments(
                    "--use-fake-device-for-media-stream", "--use-fake-ui-for-media-stream",
                    "--use-file-for-fake-video-capture=C:\\Users\\hello\\Documents\\video\\sample_mjpeg.mjpeg",
                    "--use-file-for-fake-audio-capture=C:\\Users\\hello\\Documents\\audio\\250Hz_44100Hz_16bit_05sec.wav");
            options.setCapability("name", "WebRTC with Pre-uploaded media");
        } else if (userSelection.equalsIgnoreCase("1.3")) {

            options.addArguments(
                    "--use-fake-device-for-media-stream", "--use-fake-ui-for-media-stream",
                    "--use-file-for-fake-video-capture=C:\\Users\\hello\\Downloads\\" +
                            Config.getCustomVideoFile(),
                    "--use-file-for-fake-audio-capture=C:\\Users\\hello\\Downloads\\" +
                            Config.getCustomAudioFile());
            options.setCapability("browserstack.local", "true");
            options.setCapability("name", "WebRTC with Custom media");
        }

        options.setCapability("browser", "Chrome");
        options.setCapability("browser_version", "latest");
        options.setCapability("os", "Windows");
        options.setCapability("os_version", "10");

        options.setCapability("build", "WebRTC Testing - Chrome");

        MutableCapabilities commonCaps = getCommonCaps();
        options.merge(commonCaps);

        return options;
    }

    public static MutableCapabilities getEdgeConfiguration(final String userSelection) throws Exception {

        ChromeOptions options = new ChromeOptions();

        if (userSelection.equalsIgnoreCase("3.1")) {
            options.addArguments(
                    "--use-fake-device-for-media-stream", "--use-fake-ui-for-media-stream");
            options.setCapability("name", "WebRTC with Fake media");
        } else if (userSelection.equalsIgnoreCase("3.2")) {
            options.addArguments(
                    "--use-fake-device-for-media-stream", "--use-fake-ui-for-media-stream",
                    "--use-file-for-fake-video-capture=C:\\Users\\hello\\Documents\\video\\sample_mjpeg.mjpeg",
                    "--use-file-for-fake-audio-capture=C:\\Users\\hello\\Documents\\audio\\250Hz_44100Hz_16bit_05sec.wav");
            options.setCapability("name", "WebRTC with Pre-uploaded media");
        } else if (userSelection.equalsIgnoreCase("3.3")) {
            options.addArguments(
                    "--use-fake-device-for-media-stream", "--use-fake-ui-for-media-stream",
                    "--use-file-for-fake-video-capture=C:\\Users\\hello\\Downloads\\" + Config.getCustomVideoFile(),
                    "--use-file-for-fake-audio-capture=C:\\Users\\hello\\Downloads\\" + Config.getCustomAudioFile());
            options.setCapability("name", "WebRTC with Custom media");
            options.setCapability("browserstack.local", "true");

        }
        options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
        options.setCapability("browser", "Edge");
        options.setCapability("browser_version", "latest");
        options.setCapability("os", "Windows");
        options.setCapability("os_version", "10");
        options.setCapability("build", "WebRTC Testing - Edge");

        MutableCapabilities commonCaps = getCommonCaps();
        options.merge(commonCaps);

        return options;
    }

    public static MutableCapabilities getSafariConfiguration(final String userSelection) {

        SafariOptions options = new SafariOptions();
        options.setUseTechnologyPreview(true);
        options.setCapability("browser", "Safari");
        options.setCapability("browser_version", "latest");
        options.setCapability("os", "OS X");
        options.setCapability("os_version", "Big Sur");
        options.setCapability("build", "WebRTC Testing - Safari");
        options.setCapability("name", "WebRTC with Fake Media");
        MutableCapabilities commonCaps = getCommonCaps();
        options.merge(commonCaps);

        return options;
    }

    public static MutableCapabilities getiOSConfiguration(final String userSelection) {

        SafariOptions options = new SafariOptions();
        options.setUseTechnologyPreview(true);

        options.setCapability("device", "iPhone 12");
        options.setCapability("real_mobile", "true");
        options.setCapability("os_version", "14");
        options.setCapability("autoAcceptAlerts", "true");
        options.setCapability("build", "WebRTC Testing - iOS");
        options.setCapability("name", "WebRTC with Fake media");
        MutableCapabilities commonCaps = getCommonCaps();
        options.merge(commonCaps);

        return options;
    }

    public static MutableCapabilities getAndroidConfiguration(final String userSelection) throws Exception {

        ChromeOptions options = new ChromeOptions();

        if (userSelection.equalsIgnoreCase("5.1")) {
            options.addArguments(
                    "--use-fake-device-for-media-stream", "--use-fake-ui-for-media-stream");
            options.setCapability("name", "WebRTC with Fake media");
        } else if (userSelection.equalsIgnoreCase("5.3")) {
            options.addArguments(
                    "--use-fake-device-for-media-stream", "--use-fake-ui-for-media-stream",
                    "--use-file-for-fake-video-capture=/data/local/tmp/" + Config.getCustomVideoFile(),
                    "--use-file-for-fake-audio-capture=/data/local/tmp/" + Config.getCustomAudioFile());

            options.setCapability("name", "WebRTC with Custom media");
            options.setCapability("browserstack.local", "true");
        }

        options.setCapability("os_version", "11.0");
        options.setCapability("device", "Samsung Galaxy S21");
        options.setCapability("real_mobile", "true");
        options.setCapability("autoGrantPermissions", "true");
        options.setCapability("build", "WebRTC Testing - Android");

        MutableCapabilities commonCaps = getCommonCaps();
        options.merge(commonCaps);
        return options;
    }

    public static void createSession(final MutableCapabilities config, final String deviceType,
            final String userSelection) {
        roomCreationThread = new Thread(
                new WebRTCTestRunner(config, deviceType, userSelection));
        roomCreationThread.start();

    }

    public static void main(final String[] args) throws Exception {
        // Disabling Selenium messages.
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        Scanner sc = new Scanner(System.in);

        try {

            // Getting User's Selection
            System.out.println("Please Select a Browser to initiate the WebRTC test on BrowserStack : ");
            System.out.println("1 : Press 1 to initiate WebRTC test on Google Chrome Browser");
            System.out.println("2 : Press 2 to initiate WebRTC test on Firefox Browser");
            System.out.println("3 : Press 3 to initiate WebRTC test on Edge Browser");
            System.out.println("4 : Press 4 to initiate WebRTC test on Safari Browser");
            System.out.println("5 : Press 5 to initiate WebRTC test on Chrome Browser for Android ");
            System.out.println("6 : Press 6 to initiate WebRTC test on Safari Browser for iOS");
            String userSelection = sc.next();

            switch (userSelection) {
                case "1":
                    String[] selections = { "1.1", "1.2", "1.3" };

                    System.out.println("Executing tests on Chrome Browsers");

                    for (int i = 0; i < selections.length; i++) {
                        // Creating capabilities for Chrome Browser.
                        MutableCapabilities chromeConfiguration = getChromeConfiguration(selections[i]);

                        // Creating a session on Chrome Browser.
                        createSession(chromeConfiguration, "browser", selections[i]);
                    }

                    Thread.sleep(30000);

                    break;

                case "2":
                    System.out.println("Executing tests on Firefox Browser");
                    // Creating capabilities for FireFox Browser.
                    MutableCapabilities fireFoxConfiguration = getFireFoxConfiguration("2.1");

                    // Creating a session on Firefox Browser.
                    createSession(fireFoxConfiguration, "browser", "2.1");

                    Thread.sleep(30000);

                    break;

                case "3":

                    String[] EdgeSelections = { "3.1", "3.2", "3.3" };
                    System.out.println("Executing tests on Edge Browsers");

                    for (int i = 0; i < EdgeSelections.length; i++) {
                        // // Creating capabilities for Edge Browser.
                        MutableCapabilities edgeConfiguration = getEdgeConfiguration(EdgeSelections[i]);

                        // // Creating a session on Edge Browser.
                        createSession(edgeConfiguration, "browser", EdgeSelections[i]);
                    }

                    Thread.sleep(30000);
                    break;

                case "4":
                    System.out.println("Executing tests on Safari Browser");
                    // Creating capabilities for Safari Browser.
                    MutableCapabilities safariConfiguration = getSafariConfiguration(userSelection);
                    // // Creating a session on Safari Browser.
                    createSession(safariConfiguration, "browser", userSelection);

                    Thread.sleep(30000);
                    break;

                case "5":
                    String[] AndroidSelections = { "5.1", "5.3" };

                    System.out.println("Executing tests on Android Browser");
                    for (int i = 0; i < AndroidSelections.length; i++) {
                        // Creating capabilities for Android Mobile Browser.
                        MutableCapabilities AndroidConfiguration = getAndroidConfiguration(AndroidSelections[i]);

                        // Creating a session on Android Mobile Browser.
                        createSession(AndroidConfiguration, "android", AndroidSelections[i]);
                    }
                    Thread.sleep(30000);
                    break;

                case "6":
                    System.out.println("Executing tests on iOS Browser");
                    // Creating capabilities for iOS Mobile Browser.
                    MutableCapabilities iOSConfiguration = getiOSConfiguration(userSelection);
                    // // Creating a session on iOS Mobile Browser.
                    createSession(iOSConfiguration, "ios", userSelection);

                    Thread.sleep(30000);
                    break;

                default:
                    System.out.println("Please select a valid Option");
                    break;
            }

            // Wait for threads to finish execution.
            roomCreationThread.join();

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Test execution failed!");

        } finally {
            sc.close();
        }
    }
}
