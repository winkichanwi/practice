import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import java.sql.SQLException;
import java.util.*;
import static org.junit.Assert.assertEquals;

public class UserControllerTest {

        // user table: id, name, age

        @Test
        public void create() {
            //given
            DataBase con = new DataBase();
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", "tanaka");
            params.put("age", "20");
            //when
            new UserController().create(params);
            //then
            List<Map> list = con.find("select * from user;");
            assertEquals(1, list.size());
        }

}

