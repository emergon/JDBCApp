package emergon.dao;

import emergon.entity.Actor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActorDao {

    //crud operations (Insert, Select, Update, Delete)
    //{PROTOCOL}://{IP}:{PORT}/{DATABASE}
    private String url = "jdbc:mysql://localhost:3306/sakila";
    private String user = "root";
    private String password = "root";

//    2. DriverManager will give us the connection object.
//    3. Connection(url,port,username,password,schema/database)
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(ActorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public void closeConnections(ResultSet rs, Statement stmt, Connection conn) {
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ActorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //method that returns all actors from DB
    public List<Actor> findAll() {
        ArrayList<Actor> list = new ArrayList();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String query = "SELECT * FROM actor";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int actor_id = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                Actor actor = new Actor(actor_id, fname, lname);
                list.add(actor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt, conn);
        }
        return list;
    }
}
