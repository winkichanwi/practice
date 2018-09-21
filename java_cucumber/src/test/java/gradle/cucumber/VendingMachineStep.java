package gradle.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

import static org.junit.Assert.assertEquals;

public class VendingMachineStep {

    private VendingMachine vm;

    @Given("自動販売機がある")
    public void 自動販売機がある() {
        vm = new VendingMachine();
    }

    @Given("{string}を{int}個追加する")
    public void を_個追加する(String name, Integer number) {
        vm.addDrink(name, number);
    }

    @When("{int}円を入金")
    public void 円を入金(Integer money) {
        vm.charge(money);
    }

    @When("以下のお金を入金")
    public void 以下のお金を入金(DataTable dataTable) {
        dataTable.asLists().forEach(data->{
            vm.charge(Integer.valueOf(data.get(0)));
        });
    }

    @Then("{int}円が入金されている")
    public void 円が入金されている(Integer money) {
        assertEquals(money, vm.currentCharge());
    }

    @When("お釣りを返却")
    public void お釣りを返却() {}

    @Then("{int}円が返却される")
    public void 円が返却される(int money) {
        assertEquals(money, vm.reset());
    }

    @When("{string}を購入")
    public void を購入(String name) {
        vm.buy(name);
    }

    @Then("{string}の在庫が{int}個")
    public void の在庫が_個(String name, int number) {
        assertEquals(number, vm.inventorySizeBy(name));
    }


}

