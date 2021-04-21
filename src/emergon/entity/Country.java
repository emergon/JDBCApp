package emergon.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Country {
    private int countryId;
    private String country;
    private LocalDateTime lastUpdated;

    public Country(String country, LocalDateTime lastUpdated) {
        this.country = country;
        this.lastUpdated = lastUpdated;
    }

    public Country(int countryId, String country, LocalDateTime lastUpdated) {
        this.countryId = countryId;
        this.country = country;
        this.lastUpdated = lastUpdated;
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

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.countryId;
        hash = 83 * hash + Objects.hashCode(this.country);
        hash = 83 * hash + Objects.hashCode(this.lastUpdated);
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
        if (!Objects.equals(this.lastUpdated, other.lastUpdated)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Country{" + "countryId=" + countryId + ", country=" + country + ", lastUpdated=" + lastUpdated + '}';
    }
    
}
