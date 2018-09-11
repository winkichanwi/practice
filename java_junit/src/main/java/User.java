public class User {
    private String name;
    private String companyName;
    private String password;

    public User(String name, String companyName, String password) {
        this.name = name;
        this.companyName = companyName;
        this.password = password;
    }

    public boolean validate() {
        return password.length() >= 8;
    }

    public String signature() {
        String s = validate() ? "OK" : "NG";
        if (companyName == ""){
            return name + " " + s;
        }
        return name +": " + companyName + " " + s;
    }
}
