package steps;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.*;
import pages.__ExemploHomePage;

public class __ExampleHomeSteps {
  __ExemploHomePage exemploHomePage = new __ExemploHomePage();

  @Then("I should see the welcome message {string}")
  public void i_should_see_the_welcome_message(String expectedAnswer)
  {
    String text = exemploHomePage.getMessageText();
    assertEquals(expectedAnswer, text);
  }

}
