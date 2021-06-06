package base;

import static helpers.DriverFactory.getDriver;
import static helpers.GlobalParameters.params;

import com.aventstack.extentreports.MediaEntityBuilder;
import helpers.DriverFactory;
import helpers.ExtentReportsUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.time.Duration;
import java.util.HashMap;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private static WebDriverWait wait;
    protected AppiumDriver<MobileElement> driver;
    protected JavascriptExecutor javaScriptExecutor = null;

    public BasePage() {
        this.driver = getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(params.getTIMEOUT_DEFAULT())), this);
        wait = new WebDriverWait (driver, params.getTIMEOUT_DEFAULT());
    }

    protected void waitElement(MobileElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitUntilElementToBeClickable(MobileElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitUntilElementToBeVisibilityOf(MobileElement element, int time){
        WebDriverWait waitTime = new WebDriverWait(driver, time);
        waitTime.until(ExpectedConditions.visibilityOf(element));
    }

    protected void click(MobileElement element){
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        WebDriverException possibleWebDriverException = null;
        StopWatch timeOut = new StopWatch();
        timeOut.start();
        while (timeOut.getTime() <= params.getTIMEOUT_DEFAULT())//TODO: Check if need add a new condition to avoid loop
        {
            try
            {
                waitElement(element);
                element.click();
                timeOut.stop();
                return;
            }
            catch (StaleElementReferenceException e) {
                continue;
            }
            catch (WebDriverException e)
            {
                possibleWebDriverException = e;
                if (e.getMessage().contains("Other element would receive the click")) {
                    continue;
                }
                throw e;
            }
        }
        try {
            throw new Exception(possibleWebDriverException);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clickBy(By by) {
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        getDriver().findElement(by).click();
    }

    protected void clickByText(String text) {
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"' Click text: "+text);
        clickBy(By.xpath("//*[@text='"+text+"']"));
    }

    protected void sendKeys(MobileElement element, String text){
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'. Write the text '"+text+"'.");
        waitElement(element);
        element.sendKeys(text);
    }

    protected void sendKeysWithoutElementToBeVisible(MobileElement element, String text){
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        element.sendKeys(text);
        ExtentReportsUtils.addTestInfoSimple("Write the text "+text+" in the element: "+element);
    }

    protected void clearElement(MobileElement element){
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        ExtentReportsUtils.addTestInfoSimple("Clear element text: "+element);
        waitElement(element);
        element.clear();
    }

    protected void clearElementBeforeSendKeys(MobileElement element, String text){
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        ExtentReportsUtils.addTestInfoSimple("Clear element text "+element+" and type the text: "+text);
        waitElement(element);
        element.clear();
        element.sendKeys(text);
    }

    protected void clearElementBeforeSendKeys_CtrlAandDelele(MobileElement element, String text){
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        ExtentReportsUtils.addTestInfoSimple("Clear element text "+element+" and type the text: "+text);
        waitElement(element);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
    }

    protected String getText(MobileElement element){
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        ExtentReportsUtils.addTestInfoSimple("Get element text: "+element);
        waitElement(element);
        String text = element.getText();
        ExtentReportsUtils.addTestInfoSimple("Text "+element+" = "+text);
        return text;
    }

    protected String getValue(MobileElement element){
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        waitElement(element);
        String text = element.getAttribute("value");
        ExtentReportsUtils.addTestInfoSimple("Text "+element+" = "+text);
        return text;
    }

    protected String getElementDescription(MobileElement element){
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        waitElement(element);
        String text = element.getAttribute("contentDescription");
        ExtentReportsUtils.addTestInfoSimple("Text "+element+" = "+text);
        return text;
    }

    protected boolean returnIfElementIsDisplayed(MobileElement element){
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        waitElement(element);
        boolean result = element.isDisplayed();
        ExtentReportsUtils.addTestInfoSimple("Element " +element+" is displayed = "+result);
        return result;
    }

    protected boolean returnIfElementIsEnable(MobileElement element){
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        waitElement(element);
        boolean result = element.isEnabled();
        ExtentReportsUtils.addTestInfoSimple("Element " +element+" is enabled = "+result);
        return result;
    }

    protected boolean returnIfElementIsSelected(MobileElement element){
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        waitElement(element);
        boolean result = element.isSelected();
        ExtentReportsUtils.addTestInfoSimple("Element " +element+" is selected = "+result);
        return result;
    }

    protected void scrollUsingTouchAction_ByElements(MobileElement startElement, MobileElement endElement, int seconds) {
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        TouchAction actions = new TouchAction(driver);
        actions.press(PointOption.point(startElement.getLocation().x,startElement.getLocation().y))
            .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
            .moveTo(PointOption.point(endElement.getLocation().x,endElement.getLocation().y)).release().perform();
    }

    protected void scrollUsingTouchAction(int startX,int startY, int endX, int endY, int seconds) {
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        TouchAction actions = new TouchAction(driver);
        actions.press(PointOption.point(startX,startY))
            .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
            .moveTo(PointOption.point(endX,endY)).release().perform();
    }

    protected void longPress(MobileElement element) {
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        waitElement(element);
        TouchActions action = new TouchActions(driver);
        action.longPress(element);
        action.perform();
    }

    protected void scrolling(String direction){
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", direction);
        js.executeScript("mobile: scroll", scrollObject);
    }

    protected void tap(MobileElement element){
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        waitElement(element);
        TouchActions action = new TouchActions(driver);
        action.singleTap(element);
        action.perform();
    }

    protected void doubleTap(MobileElement element){
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        waitElement(element);
        TouchActions action = new TouchActions(driver);
        action.doubleTap(element);
        action.perform();
    }

    public void scroll(double initialPosition, double finalPosition) {
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        Dimension size =  getDriver().manage().window().getSize();

        int x = size.width/2;

        int startY =(int) (size.height* initialPosition);
        int endY =(int) (size.height* finalPosition);

        TouchAction actions = new TouchAction(driver);
        actions.press(PointOption.point(x,startY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
            .moveTo(PointOption.point(x,endY)).release().perform();
    }

    public void swipe(double initialPosition, double finalPosition) {
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        Dimension size =  getDriver().manage().window().getSize();

        int y = size.height/2;

        int start_x =(int) (size.width* initialPosition);
        int end_x =(int) (size.width* finalPosition);

        TouchAction actions = new TouchAction(driver);
        actions.press(PointOption.point(start_x,y))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
            .moveTo(PointOption.point(end_x,y)).release().perform();
    }

    public void scrollDown() {
        scroll(0.3, 0.8);
    }

    public void scrollUp() {
        scroll(0.8, 0.3);
    }

    public void swipeLeft() {
        swipe(0.1, 0.9);
    }

    public void swipeRight() {
        swipe(0.9, 0.1);
    }

    public void takeAScreenShot() {
        try
        {
            String temp = helpers.ExtentReportsUtils.takeScreenshot(getDriver());
            ExtentReportsUtils.TEST.info("Take a screenshot!", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        }
        catch (Exception e)
        {

        }
    }

    public boolean verifyIfTextIsDisplayedOnScreen(String text) {
        boolean exist = getDriver().getPageSource().contains(text);
        ExtentReportsUtils.addTestInfoSimple("Verify if text '"+text+"' is displayed on screen: Result: "+exist);

        return exist;
    }

    public void scrollElement(MobileElement element, double initialPosition, double finalPosition) {
        int x= element.getLocation().x + (element.getSize().width / 2);

        int start_y =(int) (element.getSize().height* initialPosition);
        int end_y =(int) (element.getSize().height* finalPosition);

        TouchAction actions = new TouchAction(getDriver());
        actions.press(PointOption.point(x,start_y))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
            .moveTo(PointOption.point(x,end_y)).release().perform();
    }

    public void swipeElement(MobileElement element, double initialPosition, double finalPosition) {
        Dimension size = element.getSize();
        int y= element.getLocation().y + (element.getSize().height / 2);

        int start_x =(int) (element.getSize().width* initialPosition);
        int end_x =(int) (element.getSize().width* finalPosition);

        TouchAction actions = new TouchAction(getDriver());
        actions.press(PointOption.point(start_x, y))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
            .moveTo(PointOption.point(end_x, y)).release().perform();

    }

    public void scrollbyCoordinates(int xInitial,int yInitial,int xFinal,int yFinal) {
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        ExtentReportsUtils.addTestInfoSimple("Scroll action with coordinates: xInitial="+xInitial+", yInitial="+yInitial+", xFinal="+xFinal+", yFinal="+yFinal+".");
        TouchAction actions = new TouchAction(getDriver());
        actions.tap(PointOption.point(xInitial, yInitial))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
            .moveTo(PointOption.point(xFinal, yFinal)).release()
            .perform();
    }

    public void scrollDownIOS() {
        scrollbyCoordinates(200,700,200,100);
    }

    public void scrollUpIOS() {
        scrollbyCoordinates(200,100, 200,700);
    }

    public void swipeLeftIOS() {
        scrollbyCoordinates(70,450,350,450);
    }

    public void swipeRightIOS() {
        scrollbyCoordinates(350,450,70,450);
    }

    protected void longPressByTouchAction(MobileElement element) {
        String methodOfOrigin = Thread.currentThread().getStackTrace()[2].getMethodName();
        ExtentReportsUtils.addTestInfoSimple("Act: '" + methodOfOrigin+"'");
        waitElement(element);
        TouchAction actions = new TouchAction(getDriver());
        actions.longPress(PointOption.point(element.getCenter().getX(),element.getCenter().getY()))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).perform();
    }

    public boolean verifyIfTextIsDisplayedOnElement(MobileElement element ,String text) {
        boolean exist = element.getText().contains(text);
        ExtentReportsUtils.addTestInfoSimple("Verify if text '"+text+"' is displayed on element: Result: "+exist);
        return exist;
    }
}



