package gradle.cucumber;

import java.util.HashMap;
import java.util.Map;

public class Menu {

    private Map<String, Integer> map;

    public Menu() {
        map = new HashMap<>();
        map.put("コーラ", 120);
        map.put("お茶", 150);
    }

    public Integer priceBy(String name) {
        return map.get(name);
    }
}