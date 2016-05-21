package mainSQLCMD;

/**
 * Created by admin on 21.05.2016.
 */
public interface DatabaseManager {
    DataSet[] getTableData(String tableName);

    String[] getTableNames();

    void connect(String database, String user, String password);

    void clear(String tableName);

    void create(DataSet input);

    void update(String tableName, int id, DataSet newValue);
}
