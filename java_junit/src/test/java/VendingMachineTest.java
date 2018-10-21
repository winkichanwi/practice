import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class VendingMachineTest {

    @Test
    void chargeValidMoney() {
        for (Integer charge: Arrays.asList(10,50,500,1000)) {
            assertThat(new VendingMachine().charge(charge).currentCharge(), is(charge));
        }
    }

    @Test
    void chargeInvalidMoney() {
        for (Integer charge : Arrays.asList(1,5,5000,10000)) {
            assertThat(new VendingMachine().charge(charge).currentCharge(), is(0));
        }
    }

    @Test
    void multiCharge() {
        Integer charge = new VendingMachine().charge(100).charge(1000).currentCharge();
        assertThat(charge, is(1100));
    }

    @Test
    void resetCharge() {
        VendingMachine vm = new VendingMachine().charge(100).charge(1000);
        assertThat(vm.resetCharge(), is(1100));
        assertThat(vm.currentCharge(), is(0));
    }

    @Test
    void has5CokeInit() {
        VendingMachine vm= new VendingMachine();
        assertThat(vm.drinks.size(), is(5));
        vm.drinks.forEach((drink)-> {
            assertThat(drink.get(0), is("coke"));
            assertThat(drink.get(1), is("120"));
        });

    }

    @Test
    void addDrink() {
        VendingMachine vm = new VendingMachine().addDrink("water", "100");
        assertThat(vm.drinks.size(), is(6));
        assertThat(vm.drinks.get(5).get(0), is("water"));
        assertThat(vm.drinks.get(5).get(1), is("100"));
    }

    @Test
    void inventory() {
        VendingMachine vendingMachine = new VendingMachine();
        assertThat(vendingMachine.inventory(), is("coke 120yen: 5"));
        vendingMachine.addDrink("coke", "120");
        assertThat(vendingMachine.inventory(), is("coke 120yen: 6"));
        vendingMachine.addDrink("water", "100");
        assertThat(vendingMachine.inventory(), is("coke 120yen: 6\nwater 100yen: 1"));
    }


    @Nested
    public class canBuy {
        private VendingMachine vendingMachine;
        @BeforeEach
        void before() {
            vendingMachine = new VendingMachine();
        }
        @Test
        void lessChargeEnoughInventory() {
            vendingMachine.addDrink("tea", "150");
            vendingMachine.charge(100).charge(10);
            assertThat(vendingMachine.canBy("tea"), is(false));
            assertThat(vendingMachine.canBy("coke"), is(false));
        }
        @Test
        void enoughChargeAndInventory() {
            vendingMachine.charge(100).charge(10).charge(10);
            assertThat(vendingMachine.canBy("coke"), is(true));
        }
        @Test
        void lessInventoryEnoughtCharge() {
            vendingMachine.charge(100).charge(50);
            assertThat(vendingMachine.canBy("tea"), is(false));
        }
    }

    @Nested
    public class buy {

        private VendingMachine vm;

        @BeforeEach
        void before() {
            vm = new VendingMachine();
            vm.addDrink("tea", "150");
            vm.charge(500);
        }
        @Test
        void updateCharge(){
            List<String[]> patterns = Arrays.asList(
                    new String[]{"coke", "380"},
                    new String[]{"tea",  "230"}
            );
            for (String[] pattern : patterns) {
                vm.buy(pattern[0]);
                assertThat(vm.currentCharge(), is(Integer.valueOf(pattern[1])));
            }
        }
        @Test
        void updateInventory() {
            vm.buy("tea");
            assertThat(vm.inventory(), is("coke 120yen: 5"));
        }
        @Test
        void cantBuyWhenNoInventory() {
            vm.buy("tea");
            vm.buy("tea");
            assertThat(vm.currentCharge(), is(350));
            assertThat(vm.inventory(), is("coke 120yen: 5"));
        }
    }

}


