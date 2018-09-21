package gradle.cucumber;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private final Menu menu = new Menu();
    private int charge;
    public List<Drink> drinks = new ArrayList<Drink>();

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
        this.drinks.add(new Drink(name));
    }

    public void buy(String name) {
        charge = charge - menu.priceBy(name);
        drinks.remove(new Drink(name));
    }

    public int inventorySizeBy(String name) {
        return (int) drinks.stream().filter(drink->drink.name.equals(name)).count();
    }

    private class Drink {
        private final String name;

        public Drink(String name) {
            this.name = name;
        }

        public boolean equals(Object obj) {
            Drink drink = (Drink) obj;
            return drink.name.equals(name);
        }

    }
}
