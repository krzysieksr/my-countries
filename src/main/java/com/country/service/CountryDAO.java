package com.country.service;

import com.country.CountryProtos;

import java.util.List;

public interface CountryDAO {
    List<CountryProtos.Country> getCountries();
    List<CountryProtos.Country> getCountriesSortedByPopulation();
    List<CountryProtos.Country> getCountriesSortedByArea();
    void updateCountry(int id, String[] parameters);
    void createCountry(CountryProtos.Country country);
}
