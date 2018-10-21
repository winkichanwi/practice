import java.util.*;

public class VendingMachine {
    public List<List<String>> drinks = new ArrayList<>();
    private int charge;

    public VendingMachine() {
        drinks.add(Arrays.asList("coke", "120"));
        drinks.add(Arrays.asList("coke", "120"));
        drinks.add(Arrays.asList("coke", "120"));
        drinks.add(Arrays.asList("coke", "120"));
        drinks.add(Arrays.asList("coke", "120"));
    }

    public VendingMachine charge(int money) {
        if (money == 10 || money == 50 || money == 100 || money == 500 || money == 1000) {
            this.charge += money;
        }
        return this;
    }

    public Integer currentCharge() {
        return charge;
    }

    public Integer resetCharge() {
        Integer charge = this.charge;
        this.charge = 0;
        return charge;
    }

    public String inventory() {
        Map<String, List<String>> map = new HashMap<>();
        for (List<String> drink: drinks) {
            if(map.containsKey(drink.get(0))) {
                String size = map.get(drink.get(0)).get(1);
                int sizeInt = Integer.valueOf(size);
                sizeInt++;
                map.put(drink.get(0), Arrays.asList(drink.get(1), String.valueOf(sizeInt)));
            } else {
                map.put(drink.get(0), Arrays.asList(drink.get(1), "1"));
            }
        }
        List<String> result = new ArrayList<>();
        map.forEach((k, v) -> {
            result.add((k+" "+v.get(0)+"yen: "+v.get(1)));
        });
        return String.join("\n", result);
    }

    public VendingMachine addDrink(String name, String price) {
        drinks.add(Arrays.asList(name, price));
        return this;
    }

    public boolean canBy(String name) {
        boolean existInventory = false;
        for (List<String> drink : drinks) {
            if (name == drink.get(0)) {
                existInventory = true;
            }
        }
        if (existInventory == false) {
            return false;
        }
        for (List<String> drink : drinks) {
            if(name == drink.get(0)) {
                if (currentCharge() < Integer.valueOf(drink.get(1))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void buy(String name) {
        int price = 0;
        Iterator<List<String>> drinkIterator = drinks.iterator();
        while(drinkIterator.hasNext()){
            List<String> drink = drinkIterator.next();
            if (drink.get(0) == name){
                price = Integer.parseInt(drink.get(1));
                drinkIterator.remove();
                break;
            }
        }

        charge = currentCharge() - price;
    }
}
