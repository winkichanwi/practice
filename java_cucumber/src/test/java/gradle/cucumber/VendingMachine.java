package gradle.cucumber;

public class VendingMachine {
    private int charge;

    public VendingMachine charge(Integer money) {
        if (money == 10 || money == 50 || money == 100 || money == 500 || money == 1000) {
            this.charge += money;
        }
        return this;
    }

    public Integer currentCharge() {
        return charge;
    }
}
