package gradle.cucumber;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class VendingMachineStep {

    private VendingMachine vm = new VendingMachine();

    @When("{int}円を入金")
    public void 円を入金(Integer money) {
        vm.charge(money);
    }

    @Then("{int}円が入金されている")
    public void 円が入金されている(Integer money) {
        assertEquals(vm.currentCharge(), money);
    }

}

