package pages;

import base.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSBy;

public class __ExemploHomePage  extends BasePage {
  //Mapeamento
  @iOSBy(accessibility= "Login")
  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Login Page']")
  private MobileElement btnLoginPage;

  @iOSBy(accessibility= "Inputs")
  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Input Controls']")
  private MobileElement btnInputControls;

  @iOSBy(accessibility= "Nested")
  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Nested Views']")
  private MobileElement btnNestedViews;

  @iOSBy(accessibility= "Web")
  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Local Web View']")
  private MobileElement btnLocalWebView;

  //@iOSBy(accessibility= "Username Input Field")
  @AndroidFindBy(id = "com.amazonaws.devicefarm.android.referenceapp:id/drawerList")
  private MobileElement menuLista;

  @iOSBy(accessibility= "Alerts")
  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Alerts']")
  private MobileElement btnAlertsDialogsPage;

  @iOSBy(accessibility= "Native")
  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Native Components']")
  private MobileElement btnNativeComponentsPage;

  //@iOSBy(accessibility= "Username Input Field")
  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Fixtures']")
  private MobileElement btnFixturesPage;

  @iOSBy(accessibility= "HTTP")
  @AndroidFindBy(xpath = "xxxxxxxx")
  private MobileElement btnHttp;

  @iOSBy(accessibility= "Web")
  @AndroidFindBy(xpath = "xxxxxxxx")
  private MobileElement btnWebView;




  @iOSBy(xpath = "(//XCUIElementTypeStaticText)[1]")
  @AndroidFindBy(accessibility = "Alt Message")
  private MobileElement message;

  @iOSBy(xpath= "//XCUIElementTypeButton[@name='More']")
  @AndroidFindBy(xpath = "//android.widget.ImageButton")
  private MobileElement btnMenu;

  //Fim Mapeamento
  public void clicarBtnMenu()
  {
    click(btnMenu);
  }

  public String getMessageText()
  {
    return message.getText();
  }

  //Ações
  public void clicarBtnLoginPage()
  {
    click(btnLoginPage);
  }
  public void clicarInputControls()
  {
    click(btnInputControls);
  }

  public void clicarBtnNestedViews()
  {
    click(btnNestedViews);
  }

  public void clicarBtnLocalWebViewPage() {
    excutarScrowDownMenuLista();
    click(btnLocalWebView);
  }

  public void excutarScrowDownMenuLista() {
    scrollElement(menuLista,0.8, 0.3);
  }

  public void clicarBtnAlertsDialogsPage() {
    click(btnAlertsDialogsPage);
  }

  public void clicarBtnNativeComponentsPage() {
    click(btnNativeComponentsPage);
  }

  public void clicarBtnFixturesPage() {
    excutarScrowDownMenuLista();
    click(btnFixturesPage);
  }

  public void clicarBtnHttp() {
    click(btnHttp);
  }

  public void clicarBtnWebViewPage() {
    click(btnWebView);
  }

  ///====================================== Fixture Page
  @iOSBy(xpath= "//android.widget.TextView[@text='Fixtures]")
  @AndroidFindBy(xpath= "//android.widget.TextView[@text='Fixtures']")
  private MobileElement lblTitulo;

  @iOSBy(id= "com.amazonaws.devicefarm.android.referenceapp:id/nfc")
  @AndroidFindBy(id= "com.amazonaws.devicefarm.android.referenceapp:id/nfc")
  private MobileElement lblStatusNFC;

  @iOSBy(id= "com.amazonaws.devicefarm.android.referenceapp:id/gps")
  @AndroidFindBy(id= "com.amazonaws.devicefarm.android.referenceapp:id/gps")
  private MobileElement lblStatusGPS;

  // @iOSBy(id= "com.amazonaws.devicefarm.android.referenceapp:id/gps")
  @AndroidFindBy(id= "com.amazonaws.devicefarm.android.referenceapp:id/bluetooth")
  private MobileElement lblStatusBluetooth;

  // @iOSBy(id= "com.amazonaws.devicefarm.android.referenceapp:id/gps")
  @AndroidFindBy(id= "com.amazonaws.devicefarm.android.referenceapp:id/wifi")
  private MobileElement lblStatusWiFi;

  //Ações
  public String obterTextoTitulo() {
    String text = getText(lblTitulo);
    return text;
  }

  public String obterStatusNFC() {
    String text = getText(lblStatusNFC);
    return text;
  }

  public String obterStatusGPS() {
    String text = getText(lblStatusGPS);
    return text;
  }

  public String obterStatusBluetooth() {
    String text = getText(lblStatusBluetooth);
    return text;
  }

  public String obterStatusWiFi() {
    String text = getText(lblStatusWiFi);
    return text;
  }

}
