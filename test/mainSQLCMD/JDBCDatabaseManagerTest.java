package mainSQLCMD;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by admin on 09.05.2016.
 */
public class JDBCDatabaseManagerTest {

    private DatabaseManager manager;

    @Before
    public void setup() {
        manager = new JDBCDatabaseManager();
        manager.connect("sqlcmd", "postgres", "9369");
    }

    @Test
    public void testGetAllTableNames() {
        String[] tableNames = manager.getTableNames();
        assertEquals("[user, test]", Arrays.toString(tableNames));
    }

    @Test
    public void testTableData() {
        //given
        manager.clear("user");

        //when
        DataSet input = new DataSet();
        input.put("id", 13);
        input.put("name", "Stiven");
        input.put("password", "pass");
        manager.create(input);

        //then
        DataSet[] users = manager.getTableData("user");
        assertEquals(1, users.length);

        DataSet user = users[0];
        assertEquals("[name, password, id]", Arrays.toString(user.getNames()));
        assertEquals("[Stiven, pass, 13]", Arrays.toString(user.getValue()));
    }

    @Test
    public void testUpdateTableData() {
            //given
        manager.clear("user");

            //when
        DataSet input = new DataSet();
        input.put("id", 13);
        input.put("name", "Stiven");
        input.put("password", "pass");
        manager.create(input);
        DataSet newValue = new DataSet();
        newValue.put("password", "pass2");
        manager.update("user", 13, newValue);

        //then
        DataSet[] users = manager.getTableData("user");
        assertEquals(1, users.length);

        DataSet user = users[0];
        assertEquals("[name, password, id]", Arrays.toString(user.getNames()));
        assertEquals("[Stiven, pass2, 13]", Arrays.toString(user.getValue()));


    }
}
