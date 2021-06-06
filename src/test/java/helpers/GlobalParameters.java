package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class GlobalParameters {

    public static final String PKG_QA = "com.abinbev.hive.brazil.qa";
    public static final String PKG_UAT = "com.abinbev.hive.brazil.uat";

    private String BROWSER_DEFAULT;
    private int TIMEOUT_DEFAULT;
    private String SELENIUM_HUB;
    private String URL_DEFAULT;
    private String REPORT_NAME;
    private String GET_SCREENSHOT_FOR_EACH_STEP;
    private String DOWNLOAD_DEFAULT_PATH;
    private String REPORT_PATH;
    private String REPORT_BY_EXECUTION;
    private String DEVICE_TYPE;
    private String ENV;
    private String PATH_PROJECT = System.getProperty("user.dir");
    private String DEVICE_FARM;

    private String APPIUM_AUTOMATION_NAME;
    private String APPIUM_IP_ADDRESS;
    private int APPIUM_PORT;
    private String APPIUM_VERSION;
    private String AppiumServer;

    private String ANDROID_DEVICE_NAME;
    private String ANDROID_UDID;
    private String ANDROID_PLATFORM_NAME;
    private String ANDROID_PLATFORM_VERSION;
    private String ANDROID_APP_PACKAGE;
    private String ANDROID_APP_ACTIVITY;
    private String ANDROID_APP_PATH;
    private String ANDROID_APP_BROWSER_NAME;
    private String ANDROID_APP_NO_RESET;
    private String ANDROID_APP_FULL_RESET;
    private String ANDROID_APP_ORIENTATION;
    private String ANDROID_AUTO_GRANT_PERMISSIONS;

    private String IOS_UDID;
    private String IOS_PLATFORM_NAME;
    private String IOS_PLATFORM_VERSION;
    private String IOS_REPORT_FORMAT;
    private String IOS_TEST_NAME;
    private String IOS_NO_RESET;
    private String IOS_FULL_RESET;
    private String IOS_SEND_KEY_STRATEGY;
    private String IOS_AUTOMATION_NAME;
    private String IOS_DEVICE_NAME;
    private String IOS_APP_PATH;
    private String IOS_BUNDLE_ID;

    private String TEST_OBJECT_API_KEY;
    private String TEST_OBJECT_URL;

    private String BROWSERSTACK_USER;
    private String BROWSERSTACK_KEY ;
    private String BROWSERSTACK_APP ;
    private String BROWSERSTACK_DEVICE;
    private String BROWSERSTACK_OS_VERSION;
    private String BROWSERSTACK_PROJECT ;
    private String BROWSERSTACK_BUILD ;
    private String BROWSERSTACK_NAME;
    private String BROWSERSTACK_URL;


    private Properties properties;

    public static final GlobalParameters params = new GlobalParameters();

    public GlobalParameters() {
        properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(PATH_PROJECT + "/src/test/resources/globalParameters.properties");
            properties.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BROWSER_DEFAULT = getProperty("browser.default");
        ENV = getProperty("env");
        TIMEOUT_DEFAULT = Integer.parseInt(getProperty("timeout.default"));
        SELENIUM_HUB = getProperty("selenium.hub");
        URL_DEFAULT = getProperty("url.default");
        REPORT_NAME = getProperty("report.name");
        GET_SCREENSHOT_FOR_EACH_STEP = getProperty("get.screenshot.for.each.step");
        DOWNLOAD_DEFAULT_PATH = getProperty("download.defaul.path");
        REPORT_PATH = getProperty("report.path");
        DEVICE_TYPE = getProperty("device.type");
        REPORT_BY_EXECUTION = getProperty("report.separate.by.execution");
        DEVICE_FARM=getProperty("device.farm");

        AppiumServer = getProperty("appium.server");
        APPIUM_IP_ADDRESS = getProperty("appium.ip.address");
        APPIUM_PORT = Integer.parseInt(getProperty("appium.port"));
        APPIUM_AUTOMATION_NAME = getProperty("appium.automation.name");
        APPIUM_VERSION = getProperty("appium.version");

        ANDROID_DEVICE_NAME = getProperty("android.device.name");
        ANDROID_UDID = getProperty("android.udid");
        ANDROID_PLATFORM_NAME = getProperty("android.platform.name");
        ANDROID_PLATFORM_VERSION = getProperty("android.platform.version");
        ANDROID_APP_PACKAGE = getProperty("android.app.package");
        ANDROID_APP_ACTIVITY = getProperty("android.app.activity");
        ANDROID_APP_PATH = PATH_PROJECT + "/src/test/resources/app/android/" + getProperty("android.app");
        ANDROID_APP_BROWSER_NAME = getProperty("android.browser.name");
        ANDROID_APP_NO_RESET = getProperty("android.no.reset");
        ANDROID_APP_FULL_RESET = getProperty("android.full.reset");
        ANDROID_APP_ORIENTATION = getProperty("android.orientation");
        ANDROID_AUTO_GRANT_PERMISSIONS = getProperty("android.auto.grant.permissions");

        IOS_UDID = getProperty("ios.udid");
        IOS_PLATFORM_NAME = getProperty("ios.platform.name");
        IOS_PLATFORM_VERSION = getProperty("ios.platform.version");
        IOS_BUNDLE_ID = getProperty("ios.bundle.id");
        IOS_REPORT_FORMAT = getProperty("ios.report.format");
        IOS_TEST_NAME = getProperty("ios.test.name");
        IOS_NO_RESET = getProperty("ios.no.reset");
        IOS_FULL_RESET = getProperty("ios.full.reset");
        IOS_SEND_KEY_STRATEGY = getProperty("ios.send.key.strategy");
        IOS_AUTOMATION_NAME = getProperty("ios.automation.name");
        IOS_DEVICE_NAME = getProperty("ios.device.name");
        IOS_APP_PATH = PATH_PROJECT + "/src/test/resources/app/ios/" + getProperty("ios.app");

        TEST_OBJECT_API_KEY = getProperty("test.object.api.key");
        TEST_OBJECT_URL = getProperty("test.object.url");

        BROWSERSTACK_USER = getProperty("browserstack.user");
        BROWSERSTACK_KEY = getProperty("browserstack.key");
        BROWSERSTACK_APP = getProperty("browserstack.app");
        BROWSERSTACK_DEVICE = getProperty("browserstack.device");
        BROWSERSTACK_OS_VERSION = getProperty("browserstack.os_version");
        BROWSERSTACK_PROJECT = getProperty("browserstack.project");
        BROWSERSTACK_BUILD = getProperty("browserstack.build");
        BROWSERSTACK_NAME = getProperty("browserstack.name");
        BROWSERSTACK_URL=getProperty("browserstack.url");
    }

    public String getBROWSER_DEFAULT() {
        return BROWSER_DEFAULT;
    }

    public int getTIMEOUT_DEFAULT() {
        return TIMEOUT_DEFAULT;
    }

    public String getSELENIUM_HUB() {
        return SELENIUM_HUB;
    }

    public String getURL_DEFAULT() {
        return URL_DEFAULT;
    }

    public String getREPORT_NAME() {
        return REPORT_NAME;
    }

    public String getGET_SCREENSHOT_FOR_EACH_STEP() {
        return GET_SCREENSHOT_FOR_EACH_STEP;
    }

    public String getDOWNLOAD_DEFAULT_PATH() {
        return DOWNLOAD_DEFAULT_PATH;
    }

    public String getREPORT_PATH() {
        return REPORT_PATH;
    }

    public String getREPORT_BY_EXECUTION() {
        return REPORT_BY_EXECUTION;
    }

    public String getDEVICE_TYPE() {
        return DEVICE_TYPE;
    }

    public String getENV() {
        return ENV;
    }

    public String getPATH_PROJECT() {
        return PATH_PROJECT;
    }

    public String getDEVICE_FARM() {
        return DEVICE_FARM;
    }

    public String getAPPIUM_AUTOMATION_NAME() {
        return APPIUM_AUTOMATION_NAME;
    }

    public String getAPPIUM_IP_ADDRESS() {
        return APPIUM_IP_ADDRESS;
    }

    public int getAPPIUM_PORT() {
        return APPIUM_PORT;
    }

    public String getAPPIUM_VERSION() {
        return APPIUM_VERSION;
    }

    public String getAppiumServer() {
        return AppiumServer;
    }

    public String getANDROID_DEVICE_NAME() {
        return ANDROID_DEVICE_NAME;
    }

    public String getANDROID_UDID() {
        return ANDROID_UDID;
    }

    public String getANDROID_PLATFORM_NAME() {
        return ANDROID_PLATFORM_NAME;
    }

    public String getANDROID_PLATFORM_VERSION() {
        return ANDROID_PLATFORM_VERSION;
    }

    public String getANDROID_APP_PACKAGE() {
        return ANDROID_APP_PACKAGE;
    }

    public String getANDROID_APP_ACTIVITY() {
        return ANDROID_APP_ACTIVITY;
    }

    public String getANDROID_APP_PATH() {
        return ANDROID_APP_PATH;
    }

    public String getANDROID_APP_BROWSER_NAME() {
        return ANDROID_APP_BROWSER_NAME;
    }

    public String getANDROID_APP_NO_RESET() {
        return ANDROID_APP_NO_RESET;
    }

    public String getANDROID_APP_FULL_RESET() {
        return ANDROID_APP_FULL_RESET;
    }

    public String getANDROID_APP_ORIENTATION() {
        return ANDROID_APP_ORIENTATION;
    }

    public String getANDROID_AUTO_GRANT_PERMISSIONS() {
        return ANDROID_AUTO_GRANT_PERMISSIONS;
    }

    public String getIOS_UDID() {
        return IOS_UDID;
    }

    public String getIOS_PLATFORM_NAME() {
        return IOS_PLATFORM_NAME;
    }

    public String getIOS_PLATFORM_VERSION() {
        return IOS_PLATFORM_VERSION;
    }

    public String getIOS_REPORT_FORMAT() {
        return IOS_REPORT_FORMAT;
    }

    public String getIOS_TEST_NAME() {
        return IOS_TEST_NAME;
    }

    public String getIOS_NO_RESET() {
        return IOS_NO_RESET;
    }

    public String getIOS_FULL_RESET() {
        return IOS_FULL_RESET;
    }

    public String getIOS_SEND_KEY_STRATEGY() {
        return IOS_SEND_KEY_STRATEGY;
    }

    public String getIOS_AUTOMATION_NAME() {
        return IOS_AUTOMATION_NAME;
    }

    public String getIOS_DEVICE_NAME() {
        return IOS_DEVICE_NAME;
    }

    public String getIOS_APP_PATH() {
        return IOS_APP_PATH;
    }

    public String getIOS_BUNDLE_ID() {
        return IOS_BUNDLE_ID;
    }

    public String getTEST_OBJECT_API_KEY() {
        return TEST_OBJECT_API_KEY;
    }

    public String getTEST_OBJECT_URL() {
        return TEST_OBJECT_URL;
    }

    public String getBROWSERSTACK_USER() {
        return BROWSERSTACK_USER;
    }

    public String getBROWSERSTACK_KEY() {
        return BROWSERSTACK_KEY;
    }

    public String getBROWSERSTACK_APP() {
        return BROWSERSTACK_APP;
    }

    public String getBROWSERSTACK_DEVICE() {
        return BROWSERSTACK_DEVICE;
    }

    public String getBROWSERSTACK_OS_VERSION() {
        return BROWSERSTACK_OS_VERSION;
    }

    public String getBROWSERSTACK_PROJECT() {
        return BROWSERSTACK_PROJECT;
    }

    public String getBROWSERSTACK_BUILD() {
        return BROWSERSTACK_BUILD;
    }

    public String getBROWSERSTACK_NAME() {
        return BROWSERSTACK_NAME;
    }

    public String getBROWSERSTACK_URL() {
        return BROWSERSTACK_URL;
    }

    private String getProperty(String propertyKey) {
        Objects.requireNonNull(propertyKey);
        String environmentKey = propertyKey.toUpperCase().replaceAll("\\.", "_");
        String fromEnvironment = System.getenv(environmentKey);
        return (fromEnvironment == null) ? properties.getProperty(propertyKey) : fromEnvironment;
    }
}
