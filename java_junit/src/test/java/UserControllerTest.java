import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class UserControllerTest {

    class TestData {
        String description;
        int input;
        int expected;

        public TestData(String description, int input, int expected) {
            this.description = description;
            this.input = input;
            this.expected = expected;
        }
    }

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

        List<TestData> list = Arrays.asList(new TestData[] {
                new TestData("1件を返す", 20, 1 ),
                new TestData("0件を返す", 21, 0 )
        });

        list.forEach(r -> assertAge(r.input, r.expected));
    }

    private void assertAge(int age, int expected) {
        //given
        con.execute("insert into user (name, age) values ( 'tanaka', 20 );");

        //when
        List<Map> list = new UserController().search(age);

        //then
        assertEquals(expected, list.size());
    }

    @Test
    public void searchName() {
        List<Object[]> list = Arrays.asList(new Object[][] {
                { "tanaka", 1 },
                { "tanaka2", 0 }
        });

        list.forEach(r -> assertName((String) r[0], (Integer) r[1]));
    }

    private void assertName(String name, int i) {
        con.execute("insert into user (name, age) values ( 'tanaka', 20 );");

        //when
        List<Map> list = new UserController().searchName(name);

        //then
        assertEquals(i, list.size());
    }

    @Test
    public void searchNameAndAge() {
        // given
        con.execute("insert into user (name, age) values ( 'tanaka', 20 );");

        // when
        assertNameAndAge("tanaka", 20, 1);
    }

    @Test
    public void notFoundSearchNameAndAge() {
        // given
        con.execute("insert into user (name, age) values ( 'tanaka', 20 );");
        assertNameAndAge("tanaka2", 21, 0);
    }

    private void assertNameAndAge(String name, int age, int expect) {
        List<Map> list = new UserController().searchNameAndAge(name, age);

        // then
        assertEquals(expect, list.size());
    }
}

