package gradle.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class SampleStep {

    @Given("I run step given")
    public void i_run_step_given() {
        assertEquals(true, true);
    }

    @When("I run step when")
    public void i_run_step_when() {
        assertEquals(true, true);
    }

    @Then("I run step then")
    public void i_run_step_then() {
        assertEquals(true, true);
    }
}

