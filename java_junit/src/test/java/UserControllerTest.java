import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class UserControllerTest {

    private DataBase con;

    @Before
    public void setup() {
        con = new DataBase();
        con.execute("delete from user;");
    }
    // user table: id, name, age

    @Test
    public void create() {
        //given
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "tanaka");
        params.put("age", "20");
        //when
        new UserController().create(params);
        //then
        List<Map> list = con.find("select * from user;");
        assertEquals(1, list.size());
    }

    @Test
    public void searchAge() {
        List<Object[]> list = Arrays.asList(new Object[][] {
                { 20, 1 },
                { 21, 0 }
        });
        list.forEach(r -> assertAge((Integer) r[0], (Integer) r[1]));
        // assertAge(20, 1);
        // assertAge(21, 0);
    }

    private void assertAge(int age, int expected) {
        //given
        con.execute("insert into user (name, age) values ( 'tanaka', 20 );");

        //when
        List<Map> list = new UserController().search(age);

        //then
        assertEquals(expected, list.size());
    }

}

