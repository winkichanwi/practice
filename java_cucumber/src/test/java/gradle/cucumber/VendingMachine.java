package gradle.cucumber;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private int charge;
    public List<String> drinks = new ArrayList<String>();

    public VendingMachine charge(Integer money) {
        if (canUseMoney(money)) {
            this.charge += money;
        }
        return this;
    }

    private boolean canUseMoney(Integer money) {
        return money == 10 || money == 50 || money == 100 || money == 500 || money == 1000;
    }

    public Integer currentCharge() {
        return charge;
    }

    public int reset() {
        int change = charge;
        charge = 0;
        return change;
    }

    public void addDrink(String name, Integer number) {
        this.drinks.add(name);
    }

    public void buy(String name) {
        if(name.equals("コーラ")) {
            charge = charge - 120;
        } else if(name.equals("お茶")) {
            charge = charge - 150;
        }
        this.drinks.remove(0);
        reduceInventoryBy(name);
    }

    private void reduceInventoryBy(String name) {
        drinks.remove(name);
    }

    public int inventorySizeBy(String name) {
        return (int) drinks.stream().filter(drink->drink.equals(name)).count();
    }
}
