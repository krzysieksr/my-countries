package com.country.service;

import com.country.CountryProtos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DefaultCountryDAO implements CountryDAO {

    final String getCountries = "SELECT * FROM countries";

    final String getCountriesSortedByArea = "SELECT * FROM countries ORDER BY area DESC";
    final String getCountriesSortedByPopulation = "SELECT * FROM countries ORDER BY population DESC";

    @Override
    public List<CountryProtos.Country> getCountries() {
        System.out.println("DAO: get countries\n");
        List<CountryProtos.Country> countryList = executeQuery(getCountries);
        return countryList;
    }

    @Override
    public List<CountryProtos.Country> getCountriesSortedByPopulation() {
        return executeQuery(getCountriesSortedByPopulation);
    }

    @Override
    public List<CountryProtos.Country> getCountriesSortedByArea() {
        return executeQuery(getCountriesSortedByArea);
    }

    @Override
    public void updateCountry(int id, String[] parameters) {

    }

    @Override
    public void createCountry(CountryProtos.Country country) {

    }

    private List<CountryProtos.Country> executeQuery(String query) {
        List<CountryProtos.Country> countryList = new LinkedList<>();

        try {
            String url = System.getProperty("cloudsql");
            Connection connection = DriverManager.getConnection(url);
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            countryList = parseQueryOutput(resultSet);
        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("SQL exception: " +  e.getMessage());
        }
        return countryList;
    }


    private List<CountryProtos.Country> parseQueryOutput(ResultSet resultSet) throws SQLException {
        List<CountryProtos.Country> countryList = new LinkedList<>();
        while (resultSet.next()) {
            CountryProtos.Country country = CountryProtos.Country.newBuilder()
                    .setName(resultSet.getString("name"))
                    .setArea(resultSet.getInt("area"))
                    .build();

            countryList.add(country);
        }
        return countryList;
    }
}
