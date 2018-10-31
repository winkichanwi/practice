import java.util.List;
import java.util.Map;


public class UserController {
    private DataBase con = new DataBase();

    public void create(Map<String, String> params) {
        con.execute("insert into user (name, age) values ('" + params.get("name") + "', " + params.get("age") + ")");
    }

    public List search(int age) {
        return con.find("select * from user where age = " + age +  ";");
    }

    public List<Map> searchName(String name) {
        return con.find("select * from user where name = '" + name + "';");
    }

    public List<Map> searchNameAndAge(String name, int age) {
        return con.find("select * from user where name = '" + name + "' and age = " + age + ";");
    }
}
