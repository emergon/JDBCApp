package emergon.dao;

import emergon.entity.Actor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
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
    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(ActorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    private void closeConnections(ResultSet rs, Statement stmt, Connection conn) {
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ActorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void closeConnections(PreparedStatement pstm, Connection conn) {
        try {
            pstm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ActorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Actor findById(int id) {
        Connection conn = getConnection();
        String query = "SELECT * FROM actor WHERE actor_id = ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Actor actor = null;
        try {
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int actor_id = rs.getInt(1);
            String fname = rs.getString(2);
            String lname = rs.getString(3);
            Timestamp last_update = rs.getTimestamp("last_update");
            LocalDateTime lastUpdated = getLocalDateTime(last_update);
            actor = new Actor(actor_id, fname, lname, lastUpdated);
        } catch (SQLException ex) {
            Logger.getLogger(ActorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return actor;
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
                Timestamp last_update = rs.getTimestamp("last_update");
                LocalDateTime lastUpdated = getLocalDateTime(last_update);
                Actor actor = new Actor(actor_id, fname, lname, lastUpdated);
                list.add(actor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt, conn);
        }
        return list;
    }

    public void create(Actor actor) {
        Connection conn = getConnection();
        String query = "INSERT INTO actor (first_name, last_name, last_update) VALUES (?, ?, ?)";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(query);
            pstm.setString(1, actor.getFirstName());
            pstm.setString(2, actor.getLastName());
            Timestamp last_update = Timestamp.valueOf(actor.getLastUpdate());
            pstm.setTimestamp(3, last_update);
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Actor successfully created!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    public void update(Actor actor) {

    }

    public void delete(int id) {

    }

    private LocalDateTime getLocalDateTime(Timestamp ts) {
        if (ts != null) {
            return ts.toLocalDateTime();
        }
        return null;
    }
}
