package emergon.dao;

import emergon.entity.City;
import emergon.entity.Country;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CityDao extends GenericDao implements CrudInterface<City> {

    private static final String FINDBYID = "SELECT * FROM city WHERE city_id = ?";
    private static final String FINDALL = "SELECT * FROM city";
    private static final String INSERT = "INSERT INTO city (city, country_id, last_update) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE city SET city = ?, country_id = ?, last_update = ? WHERE city_id = ?";
    private static final String DELETE = "DELETE FROM city WHERE city_id = ?";
    //private static final String FINDBYMAXID = "SELECT * FROM city WHERE city_id = (SELECT max(city_id) FROM city)";

    @Override
    public List<City> findAll() {
        ArrayList<City> list = new ArrayList();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(FINDALL);
            rs.absolute(37);
            while (rs.next()) {
                int cityId = rs.getInt("city_id");
                String name = rs.getString("city");
                if(name.equals("Athenai")){
                    rs.updateDate("last_update", Date.valueOf(LocalDate.now()));
                    rs.updateRow();
                }
                int countryId = rs.getInt("country_id");
                Country country = getCountryById(countryId);
                Date last_update = rs.getDate("last_update");
                LocalDate lastUpdated = last_update.toLocalDate();
                // = getLocalDate(last_update);
                City city = new City(cityId, name, country, lastUpdated);
                list.add(city);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CityDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt, conn);
        }
        return list;
    }

    @Override
    public City findById(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        City city = null;
        try {//FINDBYID
            pstm = conn.prepareStatement(FINDBYID, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int cityId = rs.getInt("city_id");
            String name = rs.getString("city");
            int countryId = rs.getInt("country_id");
            Country country = getCountryById(countryId);//Fill country...
            Date last_update = rs.getDate("last_update");
            LocalDate lastUpdated = getLocalDate(last_update);
            city = new City(cityId, name, country, lastUpdated);
        } catch (SQLException ex) {
            String message = "City with id " + id + " could not be found!!!";
            Logger.getLogger(CityDao.class.getName()).log(Level.SEVERE, message);
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return city;
    }

    @Override
    public void create(City city) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(INSERT);
            pstm.setString(1, city.getName());
            pstm.setInt(2, city.getCountry().getCountryId());
            Date last_update = Date.valueOf(city.getLastUpdate());
            pstm.setDate(3, last_update);
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("City successfully created!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CountryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    @Override
    public void update(City t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Method that transforms a java.sql.Date object to java.time.LocalDate
     * object
     *
     * @param last_update The java.sql.Date object
     * @return The transformed LocalDate object
     */
    private LocalDate getLocalDate(Date last_update) {
        return last_update.toLocalDate();
    }

    /**
     *
     * @param id The country_id column from the ResultSet object
     * @return The object Country that has the id of the parameter.
     */
    private Country getCountryById(int id) {
        CountryDao countryDao = new CountryDao();//Use CountryDao object to find the appropriate country
        Country country = countryDao.findById(id);
        return country;
    }

}
