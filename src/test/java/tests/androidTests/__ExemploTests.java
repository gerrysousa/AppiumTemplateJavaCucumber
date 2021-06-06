package tests.androidTests;

import base.BaseTests;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.*;

public class __ExemploTests extends BaseTests {



    @Test
    @DisplayName("teste de login valido")
    public void Test_LoginSucesso() {
        __ExampleLoginSteps exampleLoginSteps = new __ExampleLoginSteps();
        __ExampleHomeSteps exampleHomeSteps = new __ExampleHomeSteps();

        String username = "admin";
        String password = "password";
        String expectedMessage = "You are logged on as admin";
        exampleLoginSteps.i_access_login_page();
        exampleLoginSteps.i_fill_in_username_and_password_and_click_login_button(username, password);
        exampleHomeSteps.i_should_see_the_welcome_message(expectedMessage);
    }

    @Test
    @DisplayName("teste de login invalido")
    public void Test_LoginInvalido() {
        __ExampleLoginSteps exampleLoginSteps = new __ExampleLoginSteps();
        __ExampleHomeSteps exampleHomeSteps = new __ExampleHomeSteps();

        String username = "teste123";
        String password = "teste123";
        String expectedMessage = "You gave me the wrong username and password";

        exampleLoginSteps.i_access_login_page();
        exampleLoginSteps.i_fill_in_username_and_password_and_click_login_button(username, password);
        exampleHomeSteps.i_should_see_the_welcome_message(expectedMessage);
    }

//
//
//    @DisplayName("A negative value for year is not supported by the leap year computation.")
//    @Tag("search")
//    @Test
//    public void Test_AcessarFixtures() {
//        homePage = new HomePage();
//        menuPage = new MenuPage();
//        fixturesPage = new FixturesPage();
//
//        homePage.clicarBtnMenu();
//        menuPage.clicarBtnFixturesPage();
//
//        String textTitulo = "Fixtures";
//        String teste1 = fixturesPage.obterTextoTitulo();
//        Assert.assertEquals(teste1, textTitulo);
//    }
//
//    @Test
//    @DisplayName( "Teste para acessar Fixtures e obter status do NFC")
//    public void Test_AcessarFixturesObterStatusNFC() {
//        homePage = new HomePage();
//        menuPage = new MenuPage();
//        fixturesPage = new FixturesPage();
//
//        homePage.clicarBtnMenu();
//        menuPage.clicarBtnFixturesPage();
//
//        String textTitulo = "Fixtures";
//        String teste1 = fixturesPage.obterTextoTitulo();
//        Assert.assertEquals(teste1, textTitulo);
//
//        String statusNFC = "false";
//        String teste2 = fixturesPage.obterStatusNFC();
//        Assert.assertEquals(teste2, statusNFC);
//    }
//
//    @Test
//    @DisplayName("Teste para acessar Fixtures e obter status do GPS")
//    public void Test_AcessarFixturesObterStatusGPS() {
//        homePage = new HomePage();
//        menuPage = new MenuPage();
//        fixturesPage = new FixturesPage();
//
//        homePage.clicarBtnMenu();
//        menuPage.clicarBtnFixturesPage();
//
//        String textTitulo = "Fixtures";
//        String teste1 = fixturesPage.obterTextoTitulo();
//        Assert.assertEquals(teste1, textTitulo);
//
//        String statusGPS = "false";
//        String teste2 = fixturesPage.obterStatusGPS();
//        Assert.assertEquals(teste2, statusGPS);
//    }
//
//    @Test
//    @DisplayName( "Teste para acessar Fixtures e obter status do Bluetooth")
//    public void Test_AcessarFixturesObterStatusBluetooth() {
//        homePage = new HomePage();
//        menuPage = new MenuPage();
//        fixturesPage = new FixturesPage();
//
//        homePage.clicarBtnMenu();
//        menuPage.clicarBtnFixturesPage();
//
//        String textTitulo = "Fixtures";
//        String teste1 = fixturesPage.obterTextoTitulo();
//        Assert.assertEquals(teste1, textTitulo);
//
//        String statusBluetooth = "false";
//        String teste2 = fixturesPage.obterStatusBluetooth();
//        Assert.assertEquals(teste2, statusBluetooth);
//    }
//
//    @Test
//    @DisplayName("Teste para acessar Fixtures e obter status do Wi-Fi")
//    public void Test_AcessarFixturesObterStatusWiFi() {
//        homePage = new HomePage();
//        menuPage = new MenuPage();
//        fixturesPage = new FixturesPage();
//
//        homePage.clicarBtnMenu();
//        menuPage.clicarBtnFixturesPage();
//
//        String textTitulo = "Fixtures";
//        String teste1 = fixturesPage.obterTextoTitulo();
//        Assert.assertEquals(teste1, textTitulo);
//
//        String statusWiFi = "false";
//        String teste2 = fixturesPage.obterStatusWiFi();
//        Assert.assertEquals(teste2, statusWiFi);
//    }
}
