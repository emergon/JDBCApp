package emergon.dao;

import emergon.entity.Actor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActorDao extends GenericDao implements CrudInterface<Actor>{

    private static final String FINDBYID = "SELECT * FROM actor WHERE actor_id = ?";
    private static final String FINDALL = "SELECT * FROM actor";
    private static final String INSERT = "INSERT INTO actor (first_name, last_name, last_update) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE actor SET first_name = ?, last_name = ?, last_update = ? WHERE actor_id = ?";
    private static final String DELETE = "DELETE FROM actor WHERE actor_id = ?";

    public Actor findById(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Actor actor = null;
        try {
            pstm = conn.prepareStatement(FINDBYID);
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
            rs = stmt.executeQuery(FINDALL);
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
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(INSERT);
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
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(UPDATE);
            pstm.setInt(4, actor.getActorId());
            pstm.setString(1, actor.getFirstName());
            pstm.setString(2, actor.getLastName());
            Timestamp last_update = Timestamp.valueOf(actor.getLastUpdate());
            pstm.setTimestamp(3, last_update);
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Actor successfully updated!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    public void delete(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETE);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Actor successfully deleted!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }
}
