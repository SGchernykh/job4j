package ru.job4j.web.model;

public class City {
    private final int id;
    private final String city;
    private final int countryID;

    /**
     * Constructor.
     * @param id        city id.
     * @param city      city name.
     * @param countryID country id.
     */
    public City(final int id, final String city, final int countryID) {
        this.id = id;
        this.city = city;
        this.countryID = countryID;
    }

    public int getId() {
        return this.id;
    }

    public String getCity() {
        return this.city;
    }

    public int getCountryID() {
        return this.countryID;
    }
    @Override
    public String toString() {
        return String.format("City: %s", this.city);
    }
}
