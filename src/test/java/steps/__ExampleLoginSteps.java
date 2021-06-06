package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;


import static org.junit.Assert.*;

public class __ExampleLoginSteps {
  private __ExemploHomePage homePage= new __ExemploHomePage();
  private __ExemploLoginPage loginPage = new __ExemploLoginPage();

  @Given("I access login page")
  public void i_access_login_page()
  {
    homePage.clicarBtnMenu();
    homePage.clicarBtnLoginPage();
  }

  @When("I fill in username with {string} and password with {string} and click login button")
  public void i_fill_in_username_and_password_and_click_login_button(String username, String password)
  {
    loginPage.preencherNome(username);
    loginPage.preencherSenha(password);
    loginPage.clicarBtnLogin();
  }

}


/*
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

class IsItFriday {
  static String isItFriday(String today) {
    return "Nope";
  }
}

public class StepDefinitions {
  private String today;
  private String actualAnswer;

  @Given("today is Sunday")
  public void today_is_Sunday() {
    today = "Sunday";
  }

  @When("I ask whether it's Friday yet")
  public void i_ask_whether_it_s_Friday_yet() {
    actualAnswer = IsItFriday.isItFriday(today);
  }

  @Then("I should be told {string}")
  public void i_should_be_told(String expectedAnswer) {
    assertEquals(expectedAnswer, actualAnswer);
  }
 */