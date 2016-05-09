package mainSQLCMD;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by admin on 09.05.2016.
 */
public class DatabaseManagerTest {

    private DatabaseManager manager;

    @Before
    public void setup() {
        manager = new DatabaseManager();
        manager.connect("sqlcmd", "postgres", "9369");
    }

    @Test
    public void testGetAllTableNames() {
        String[] tableNames = manager.getTableNames();
        assertEquals("[user, test]", Arrays.toString(tableNames));
    }

}
