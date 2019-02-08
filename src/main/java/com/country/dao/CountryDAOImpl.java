package com.country.dao;

import com.config.ConnectionPoolContextListener;
import com.country.CountryProtos;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CountryDAOImpl implements CountryDAO {

    private static final String SELECT_ALL_COUNTRIES = "SELECT * FROM country";
    private static final String GET_COUNTRIES_SORTED_BY_AREA = "SELECT * FROM country ORDER BY area DESC";
    private static final String GET_COUNTRIES_SORTED_BY_POPULATION = "SELECT * FROM country ORDER BY population DESC";

    private static final String GET_LANGUAGES_FOR_COUNTRY =
            "SELECT l.* FROM lang l INNER JOIN country_lang cl ON cl.lang_id=l.lang_id WHERE cl.country_id=";

    private static final String GET_RELIGIONS_FOR_COUNTRY =
            "SELECT r.religionName FROM religion r INNER JOIN country_religion cr ON cr.religion_id=r.religion_id WHERE cr.country_id=";


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

            CountryProtos.Country.Builder builder = CountryProtos.Country.newBuilder()
                    .setName(resultSet.getString("name"))
                    .setPopulation(resultSet.getInt("population"))
                    .setArea(resultSet.getInt("area"))
                    .setCapital(resultSet.getString("capital"))
                    .setGdp(resultSet.getInt("GDP"))
                    .setGovernmentType(resultSet.getString("government_type"))
                    .setCurrency(resultSet.getString("currency"))
                    .setInternetDomain(resultSet.getString("internet_domain"));


            int country_id = resultSet.getInt("country_id");

            DataSource pool = ConnectionPoolContextListener.dataSource;
            try (Connection conn = pool.getConnection()) {
                PreparedStatement religionStatement = conn.prepareStatement(GET_RELIGIONS_FOR_COUNTRY + country_id);
                ResultSet religionSet = religionStatement.executeQuery();


                List<CountryProtos.Country.Religion> religions = new LinkedList<>();
                while (religionSet.next()) {
                    String string = religionSet.getString("religionName");
                    CountryProtos.Country.Religion religion = CountryProtos.Country.Religion.valueOf(string);
                    religions.add(religion);
                }

                builder.addAllReligions(religions);


                PreparedStatement languageStatement = conn.prepareStatement(GET_LANGUAGES_FOR_COUNTRY + country_id);
                ResultSet languageSet = languageStatement.executeQuery();

                List<CountryProtos.Country.Language> languages= new LinkedList<>();

                while (languageSet.next()) {
                    String string = languageSet.getString("langName");
                    CountryProtos.Country.Language language = CountryProtos.Country.Language.valueOf(string);
                    languages.add(language);
                }

                builder.addAllLanguages(languages);

            }
            CountryProtos.Country country = builder.build();
            countryList.add(country);
        }
        return countryList;
    }

}
