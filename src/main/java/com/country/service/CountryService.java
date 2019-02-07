package com.country.service;

import com.country.CountryProtos;
import com.google.inject.Inject;

import java.util.List;

public class CountryService {
    @Inject
    CountryDAO countryDAO;

    public List<CountryProtos.Country> getAll() {
        return countryDAO.getCountries();
    }

    public List<CountryProtos.Country> getAllSortedByArea() {
        return countryDAO.getCountriesSortedByArea();
    }

    public List<CountryProtos.Country> getAllSortedByPopulation() {
        return countryDAO.getCountriesSortedByPopulation();
    }
}
