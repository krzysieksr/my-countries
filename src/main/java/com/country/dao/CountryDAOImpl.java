package com.country.dao;

import com.config.ConnectionPoolContextListener;
import com.country.CountryProtos;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAOImpl implements CountryDAO {

    private static final String SELECT_ALL_COUNTRIES = "SELECT * FROM country";
    private static final String GET_COUNTRIES_SORTED_BY_AREA = "SELECT * FROM country ORDER BY area DESC";
    private static final String GET_COUNTRIES_SORTED_BY_POPULATION = "SELECT * FROM country ORDER BY population DESC";

    @Override
    public List<CountryProtos.Country> getCountries() throws SQLException {
        return executeSelectQuery(SELECT_ALL_COUNTRIES);
    }

    @Override
    public List<CountryProtos.Country> getCountriesSortedByArea() throws SQLException {
        return executeSelectQuery(GET_COUNTRIES_SORTED_BY_AREA);
    }

    @Override
    public List<CountryProtos.Country> getCountriesSortedByPopulation() throws SQLException {
        return executeSelectQuery(GET_COUNTRIES_SORTED_BY_POPULATION);
    }

    @Override
    public void updateCountry(int id, String[] parameters) {

    }

    @Override
    public void createCountry(CountryProtos.Country country) {
//        String createCountrySql = "INSERT INTO country (name, population, area, capital, GDP, government, currency, internet_domain) VALUES" +
//                "(?, ?, ?, ?, ?, ?, ?, ?)";
//
//        String getCountryIdSql = "SELECT country_id FROM country WHERE name LIKE ?"; // otrzymamy COUNTRY_ID
//
//
//
//        // for each religia
//        String getReligionId = "SELECT religion_id FROM religion WHERE religionName LIKE ?"; //
//
//
//        for each religia_id in religions_id
//                INSERT INTO country_religion (country, religion) VALUES (country_id, religia_id);
//
//
//
//        //for each język
//        String getLangId = "SELECT lang_id FROM lang WHERE langName LIKE ?";

    }

    private List<CountryProtos.Country> executeSelectQuery(String query) throws SQLException {
        DataSource pool = ConnectionPoolContextListener.dataSource;
        List<CountryProtos.Country> countryList;
        try (Connection conn = pool.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            countryList = buildCountryFromResultSet(statement);
        }

        return countryList;
    }

    private List<CountryProtos.Country> buildCountryFromResultSet(PreparedStatement statement) throws SQLException {
        List<CountryProtos.Country> countryList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            CountryProtos.Country country = CountryProtos.Country.newBuilder()
                    .setName(resultSet.getString("name"))
                    .setPopulation(resultSet.getInt("population"))
                    .setArea(resultSet.getInt("area"))
                    .setCapital(resultSet.getString("capital"))
                    .setGdp(resultSet.getInt("GDP"))
                    .setGovernmentType(resultSet.getString("government_type"))
                    .setCurrency(resultSet.getString("currency"))
                    .setInternetDomain(resultSet.getString("internet_domain"))
                    .build();
            countryList.add(country);
        }
        return countryList;
    }

}
