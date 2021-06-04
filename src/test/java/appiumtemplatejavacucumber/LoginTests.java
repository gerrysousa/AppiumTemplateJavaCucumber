package appiumtemplatejavacucumber;

import org.junit.jupiter.api.Test;


public class LoginTests {
  StepDefinitions steps = new StepDefinitions();

  @Test
  public void Login_successfully(){
      steps.today_is_Sunday();
      steps.i_ask_whether_it_s_Friday_yet();
      steps.i_should_be_told("Nope");
  }

}