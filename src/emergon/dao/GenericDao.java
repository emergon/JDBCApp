package emergon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenericDao {
    private final String url = "jdbc:mysql://localhost:3306/sakila";
    private final String user = "root";
    private final String password = "root";
    
    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    protected void closeConnections(ResultSet rs, Statement stmt, Connection conn) {
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void closeConnections(PreparedStatement pstm, Connection conn) {
        try {
            pstm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected LocalDateTime getLocalDateTime(Timestamp ts) {
        if (ts != null) {
            return ts.toLocalDateTime();
        }
        return null;
    }
}
