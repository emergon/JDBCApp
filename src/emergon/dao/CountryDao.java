package emergon.dao;

import emergon.entity.Country;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class CountryDao extends GenericDao implements CrudInterface<Country>{

    private static final String FINDBYID = "SELECT * FROM country WHERE country_id = ?";
    private static final String FINDALL = "SELECT * FROM country";
    private static final String INSERT = "INSERT INTO country (country, last_update) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE country SET country = ?, last_update = ? WHERE country_id = ?";
    private static final String DELETE = "DELETE FROM country WHERE country_id = ?";
    private static final String FINDBYMAXID = "SELECT * FROM country WHERE country_id = (SELECT max(country_id) FROM country)";

    public Country findById(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Country country = null;
        try {
            pstm = conn.prepareStatement(FINDBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int countryId = rs.getInt(1);
            String name = rs.getString(2);
            Timestamp last_update = rs.getTimestamp("last_update");
            LocalDateTime lastUpdated = getLocalDateTime(last_update);
            country = new Country(countryId, name, lastUpdated);
        } catch (SQLException ex) {
            String message = "Country with id "+id+" could not be found!!!";
            Logger.getLogger(CountryDao.class.getName()).log(Level.SEVERE, message);
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return country;
    }

    //method that returns all countries from DB
    public List<Country> findAll() {
        ArrayList<Country> list = new ArrayList();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(FINDALL);
            while (rs.next()) {
                int country_id = rs.getInt(1);
                String name = rs.getString(2);
                Timestamp last_update = rs.getTimestamp("last_update");
                LocalDateTime lastUpdated = getLocalDateTime(last_update);
                Country country = new Country(country_id, name, lastUpdated);
                list.add(country);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CountryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt, conn);
        }
        return list;
    }

    public void create(Country country) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(INSERT);
            pstm.setString(1, country.getCountry());
            Timestamp last_update = Timestamp.valueOf(country.getLastUpdate());
            pstm.setTimestamp(2, last_update);
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Country successfully created!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CountryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    public void update(Country country) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(UPDATE);
            pstm.setInt(3, country.getCountryId());
            pstm.setString(1, country.getCountry());
            Timestamp last_update = Timestamp.valueOf(country.getLastUpdate());
            pstm.setTimestamp(2, last_update);
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Country successfully updated!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CountryDao.class.getName()).log(Level.SEVERE, null, ex);
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
                System.out.println("Country successfully deleted!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CountryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    public Country findByMaxId() {
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Country country = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(FINDBYMAXID);
            rs.next();
            int country_id = rs.getInt(1);
            String name = rs.getString(2);
            Timestamp last_update = rs.getTimestamp("last_update");
            LocalDateTime lastUpdated = getLocalDateTime(last_update);
            country = new Country(country_id, name, lastUpdated);
        } catch (SQLException ex) {
            Logger.getLogger(CountryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt, conn);
        }
        return country;
    }

}
