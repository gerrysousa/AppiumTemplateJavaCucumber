package helpers;

import static helpers.GlobalParameters.params;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;

public class DriverFactory {

    public static AppiumDriver<MobileElement> driver;
    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    public WebDriverWait wait;

    public DriverFactory() {
        builder = new AppiumServiceBuilder();
        builder.withIPAddress(params.getAPPIUM_IP_ADDRESS());
        builder.usingPort(params.getAPPIUM_PORT());
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        // If you need display only errors on terminal
        //builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("URL >>>"+service.getUrl());
        System.out.println("isRunning >>>"+service.isRunning());
        //System.out.println("isRunning >>>"+builder..());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }


    public static AppiumDriver<MobileElement> getDriver() {
        if (driver == null) {
            if ("remote".equals(params.getENV())) {
                DriverFactory.createDriverDeviceFarm(params.getDEVICE_TYPE());
            } else {
                DriverFactory.createDriver(params.getDEVICE_TYPE());
            }
        }
        return driver;

    }

    public void setDriver(AppiumDriver<MobileElement> driver) {
        DriverFactory.driver = driver;
    }

    public static AppiumDriver<MobileElement> createDriver(String deviceType) {
        try {
            if ("android".equals(deviceType)) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("platformName", params.getANDROID_PLATFORM_NAME());
                caps.setCapability("platformVersion", params.getANDROID_PLATFORM_VERSION());
                caps.setCapability("deviceName", params.getANDROID_DEVICE_NAME());
                caps.setCapability("app", params.getANDROID_APP_PATH());
                caps.setCapability("browserName", params.getANDROID_APP_BROWSER_NAME());
                caps.setCapability("udid", params.getANDROID_UDID());
                caps.setCapability("noReset", params.getANDROID_APP_NO_RESET());
                caps.setCapability("fullReset", params.getANDROID_APP_FULL_RESET());
                caps.setCapability("orientation", params.getANDROID_APP_ORIENTATION());
                caps.setCapability("automationName", params.getAPPIUM_AUTOMATION_NAME());
                caps.setCapability("autoGrantPermissions", params.getANDROID_AUTO_GRANT_PERMISSIONS());
                driver = new AndroidDriver<>(new URL(params.getAppiumServer()), caps);
                System.out.print("APPIUM driver Created!!!!!");
            } else if ("ios".equals(deviceType)) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("platformName", params.getIOS_PLATFORM_NAME());
                caps.setCapability("platformVersion", params.getIOS_PLATFORM_VERSION());
                caps.setCapability("deviceName", params.getIOS_DEVICE_NAME());
                caps.setCapability("automationName", params.getIOS_AUTOMATION_NAME());
                caps.setCapability("app", params.getIOS_APP_PATH());
                //caps.setCapability("bundleId", params.getIOSBundleId);
                //caps.setCapability("udid", params.getIOSUDID);
                //caps.setCapability(CapabilityType.BROWSER_NAME, "safari");
                //caps.setCapability("noReset", params.getIOSNoReset);
                //caps.setCapability("fullReset", params.getIOSFullReset);
                driver = new IOSDriver(new URL(params.getAppiumServer()), caps);
            }
        } catch (Exception e) {
            System.out.print("Alert! Could not create the APPIUM driver!!!!!");
        }
        return driver;
    }

    public static AppiumDriver<MobileElement> createDriverDeviceFarm(String deviceType) {
        try {
            if ("android".equals(deviceType)) {
                if ("saucelabs".equals(params.getDEVICE_FARM())) {
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setCapability("platformName", params.getANDROID_PLATFORM_NAME());
                    caps.setCapability("automationName", params.getAPPIUM_AUTOMATION_NAME());
                    caps.setCapability("testobject_api_key", params.getTEST_OBJECT_API_KEY());
                    caps.setCapability("appiumVersion", params.getAPPIUM_VERSION());
                    driver = new AndroidDriver(new URL(params.getTEST_OBJECT_URL()), caps);
                } else {
                    DesiredCapabilities caps = new DesiredCapabilities();
                    // Set your access credentials
                    caps.setCapability("browserstack.user", params.getBROWSERSTACK_USER());
                    caps.setCapability("browserstack.key", params.getBROWSERSTACK_KEY());
                    // Set URL of the application under test
                    caps.setCapability("app", params.getBROWSERSTACK_APP());
                    // Specify device and os_version for testing
                    caps.setCapability("device", params.getBROWSERSTACK_DEVICE());
                    caps.setCapability("os_version", params.getBROWSERSTACK_OS_VERSION());
                    // Set other BrowserStack capabilities
                    caps.setCapability("project", params.getBROWSERSTACK_PROJECT());
                    caps.setCapability("build", params.getBROWSERSTACK_BUILD());
                    caps.setCapability("name", params.getBROWSERSTACK_NAME());
                    // Initialise the remote Webdriver using BrowserStack remote URL
                    // and desired capabilities defined above
                    driver = new AndroidDriver(new URL(params.getBROWSERSTACK_URL()), caps);
                }
            } else if ("ios".equals(deviceType)) {

            }
            return driver;
        } catch (Exception e) {
            System.err.print("Alert! Could not create the APPIUM driver!!!!! Message: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static void killDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static void resetDriver() {
        if (driver != null) {
            driver.resetApp(); //it is not necessary because for each test Appium run a reset (noReset=false)
            driver.closeApp();
        }
    }


}
