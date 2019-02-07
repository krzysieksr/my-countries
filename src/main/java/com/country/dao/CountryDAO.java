package com.country.dao;

import com.country.CountryProtos;

import java.sql.SQLException;
import java.util.List;

public interface CountryDAO {
    List<CountryProtos.Country> getCountries() throws SQLException;

    List<CountryProtos.Country> getCountriesSortedByPopulation() throws SQLException;

    List<CountryProtos.Country> getCountriesSortedByArea() throws SQLException;

    void updateCountry(int id, String[] parameters);

    void createCountry(CountryProtos.Country country);
}
