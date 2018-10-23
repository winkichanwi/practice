import java.util.List;
import java.util.Map;


public class UserController {
    public void create(Map<String, String> params) {
        DataBase con = new DataBase();
        con.execute("insert into user (name, age) values ('" + params.get("name") + "', " + params.get("age") + ")");
    }

    public List search(int age) {
        DataBase con = new DataBase();
        return con.find("select * from user where age = " + age +  ";");
    }
}
