package emergon;

import emergon.dao.CountryDao;
import emergon.entity.Country;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author user
 */
public class MainClass {

    public static void main(String[] args) throws SQLException {
        testCountryFindAll();
        //testCountryFindById(1000);
//        testCountryCreate();
//        testCountryUpdate();
//        testCountryDelete(110);
    }
    
    public static void testCountryFindAll(){
        CountryDao cdao = new CountryDao();
        List<Country> list = cdao.findAll();
        for(Country country: list){
            System.out.println(country);
        }
    }
    
    public static void testCountryFindById(int id){
        CountryDao cdao = new CountryDao();
        Country country = cdao.findById(id);
        System.out.println("Country is : "+country);
    }
    
    public static void testCountryCreate(){
        CountryDao cdao = new CountryDao();
        Country country = new Country("Zimbambue", LocalDateTime.now());
        cdao.create(country);
        testCountryFindByMaxId();
    }

    public static void testCountryUpdate(){
        CountryDao cdao = new CountryDao();
        Country country = cdao.findById(110);
        country.setCountry("name2");
        country.setLastUpdate(LocalDateTime.now());
        cdao.update(country);
        country = cdao.findById(110);
        System.out.println("Updated country:"+country);
    }
    
    public static void testCountryDelete(int id){
        CountryDao cdao = new CountryDao();
        cdao.delete(id);
        Country country = cdao.findById(id);
        System.out.println("country:"+country);
    }
    
    public static void testCountryFindByMaxId(){
        CountryDao cdao = new CountryDao();
        Country country = cdao.findByMaxId();
        System.out.println("Country with max id is:"+country);
    }
}
