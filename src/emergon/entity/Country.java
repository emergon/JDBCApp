package emergon.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Country {
    private int countryId;
    private String country;
    private LocalDateTime lastUpdate;
    private List<City> cities;

    public Country(String country, LocalDateTime lastUpdate) {
        this.country = country;
        this.lastUpdate = lastUpdate;
    }

    public Country(int countryId, String country, LocalDateTime lastUpdate) {
        this.countryId = countryId;
        this.country = country;
        this.lastUpdate = lastUpdate;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.countryId;
        hash = 83 * hash + Objects.hashCode(this.country);
        hash = 83 * hash + Objects.hashCode(this.lastUpdate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Country other = (Country) obj;
        if (this.countryId != other.countryId) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.lastUpdate, other.lastUpdate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Country{" + "countryId=" + countryId + ", country=" + country + ", lastUpdate=" + lastUpdate + '}';
    }
    
}
