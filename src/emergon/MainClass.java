package emergon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author user
 */
public class MainClass {
//crud operations (Insert, Select, Update, Delete)
    //{PROTOCOL}://{IP}:{PORT}/{DATABASE}
    private static String url = "jdbc:mysql://localhost:3306/sakila";
    private static String user = "root";
    private static String password = "root";

    public static void main(String[] args) throws SQLException {
//    1. import Driver jar.
//    2. DriverManager will give us the connection object.
//    3. Connection(url,port,username,password,schema/database)
        Connection connection = DriverManager.getConnection(url, user, password);
//    Create Statement = queries (select, insert, update, delete)
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM actor";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        int actor_id = resultSet.getInt(1);
        String fname = resultSet.getString(2);
        String lname = resultSet.getString(3);
        System.out.println("actorId="+actor_id+",fname="+fname+",lname="+lname);
        resultSet.next();
        actor_id = resultSet.getInt(1);
        fname = resultSet.getString(2);
        lname = resultSet.getString(3);
        System.out.println("actorId="+actor_id+",fname="+fname+",lname="+lname);
        resultSet.close();
        statement.close();
        connection.close();
//    Execute Statement
//    ResultSet = store results (data) of the statement
//    transform the ResultSet into Java objects (List, ArrayList, Student)

    }

}
