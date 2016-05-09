package mainSQLCMD;

import java.sql.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by admin on 08.05.2016.
 */
public class DatabaseManager {
    private Connection connection;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String database = "sqlcmd";
        String user = "postgres";
        String password = "9369";

        DatabaseManager manager = new DatabaseManager();

        manager.connect(database, user, password);

        Connection connection = manager.getConnection();

        //insert
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO public.user (name, password)" +
                "VALUES ('Stiven', 'Pupkin')");
        stmt.close();

        //select
        String[] tables = manager.getTableNames();

        System.out.println(Arrays.toString(tables));

        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.user WHERE id > 5");
        while (rs.next())
        {
            System.out.println("id: " + rs.getString("id"));
            System.out.println("name: " + rs.getString("name"));
            System.out.println("password: " + rs.getString("password"));
            System.out.println("-------");
        }
        rs.close();
        stmt.close();

        //delete
        stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM public.user " +
                "WHERE id > 10 AND id < 100");
        stmt.close();

        //update
        PreparedStatement ps = connection.prepareStatement(
                "UPDATE public.user SET password = ? WHERE id > 3");
        String pass = "password_" + new Random().nextInt();
        ps.setString(1, pass);
        ps.executeUpdate();
        ps.close();

        connection.close();
    }

    public String[] getTableNames() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE';");
            String[] tables = new String[100];
            int index = 0;
            while (rs.next())
            {
                tables[index++] = rs.getString("table_name");
            }
            tables = Arrays.copyOf(tables, index, String[].class);
            rs.close();
            stmt.close();
            return tables;
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public void connect(String database, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Please add jdbc jar to project.");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/" + database, user,
                    password);
        } catch (SQLException e) {
            System.out.println(String.format("Cant get connection for database: %s user: %s", database, user));
            e.printStackTrace();
            connection = null;
        }
    }

    private Connection getConnection() {
        return connection;
    }
}
