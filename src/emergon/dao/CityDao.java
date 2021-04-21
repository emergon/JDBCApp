package emergon.dao;

import emergon.entity.City;
import java.util.List;

public class CityDao extends GenericDao implements CrudInterface<City>{

    private static final String FINDBYID = "SELECT * FROM city WHERE city_id = ?";
    private static final String FINDALL = "SELECT * FROM city";
    private static final String INSERT = "INSERT INTO city (city, country_id, last_update) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE city SET city = ?, country_id = ?, last_update = ? WHERE city_id = ?";
    private static final String DELETE = "DELETE FROM city WHERE city_id = ?";
    //private static final String FINDBYMAXID = "SELECT * FROM city WHERE city_id = (SELECT max(city_id) FROM city)";

    @Override
    public List<City> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public City findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(City t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(City t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
