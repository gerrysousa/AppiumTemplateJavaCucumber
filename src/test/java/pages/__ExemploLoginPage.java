package pages;

import base.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSBy;

public class __ExemploLoginPage extends BasePage {
    @iOSBy(xpath= "//XCUIElementTypeTextField[@value='Username']")//XCUIElementTypeButton[@name='More']
    @AndroidFindBy(accessibility= "Username Input Field")
    private MobileElement usernameField;

    @iOSBy(xpath = "//XCUIElementTypeSecureTextField[@value='Password']")
    @AndroidFindBy(accessibility = "Password Input Field")
    private MobileElement passwordField;

    @iOSBy(accessibility = "Login")
    @AndroidFindBy(accessibility = "Login Button")
    private MobileElement loginBtn;



    public void preencherNome(String username)
    {
        sendKeys(usernameField,username);
    }
    public void preencherSenha(String password)
    {
        sendKeys(passwordField,password);
    }
    public void clicarBtnLogin()
    {
        click(loginBtn);
    }

}
