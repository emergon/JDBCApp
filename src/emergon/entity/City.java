package emergon.entity;

import java.time.LocalDate;
import java.util.Objects;

public class City {
    private int cityId;
    private String name;
    private Country country;
    private LocalDate lastUpdate;

    public City(int cityId, String name, Country country, LocalDate lastUpdate) {
        this.cityId = cityId;
        this.name = name;
        this.country = country;
        this.lastUpdate = lastUpdate;
    }

    public City(String name, Country country, LocalDate lastUpdate) {
        this.name = name;
        this.country = country;
        this.lastUpdate = lastUpdate;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.cityId;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.country);
        hash = 47 * hash + Objects.hashCode(this.lastUpdate);
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
        final City other = (City) obj;
        if (this.cityId != other.cityId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
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
        StringBuilder builder = new StringBuilder();
        builder.append("City{")
                .append("cityId=").append(cityId)
                .append(",name=").append(name)
                .append(",country=").append(country.getCountry())
                .append(",lastUpdate=").append(lastUpdate)
                .append('}');
        return builder.toString();
    }
    
}
