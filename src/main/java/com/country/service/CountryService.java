package com.country.service;

import com.country.CountryProtos;
import com.country.dao.CountryDAO;
import com.google.inject.Inject;

import java.sql.SQLException;
import java.util.List;

public class CountryService {
    
    @Inject
    CountryDAO countryDAO;

    public List<CountryProtos.Country> getAll() throws SQLException {
        return countryDAO.getCountries();
    }

    public List<CountryProtos.Country> getAllSortedByArea() throws SQLException {
        return countryDAO.getCountriesSortedByArea();
    }

    public List<CountryProtos.Country> getAllSortedByPopulation() throws SQLException {
        return countryDAO.getCountriesSortedByPopulation();
    }

    public void createCountry(CountryProtos.Country country) {
        countryDAO.createCountry(country);
    }
}
