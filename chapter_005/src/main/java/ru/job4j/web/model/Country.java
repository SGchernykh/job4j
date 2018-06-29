package ru.job4j.web.model;

public class Country {
    private final int id;
    private final String country;

    /**
     * Constructor.
     * @param id      id.
     * @param country country.
     */
    public Country(final int id, final String country) {
        this.id = id;
        this.country = country;
    }

    public int getId() {
        return this.id;
    }

    public String getCountry() {
        return this.country;
    }

    @Override
    public String toString() {
        return String.format("Country: %s", this.country);
    }
}
