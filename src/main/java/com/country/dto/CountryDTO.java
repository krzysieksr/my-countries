package com.country.dto;

import com.country.CountryProtos;

import java.util.List;

public class CountryDTO {
    private String name;
    private int population;
    private int area;
    private List<CountryProtos.Country.Language> language;
    private String capital;
    private int gdp;
    private String governmentType;
    private String currency;
    private String internetDomain;
    private List<CountryProtos.Country.Religion> religion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public List<CountryProtos.Country.Language> getLanguage() {
        return language;
    }

    public void setLanguage(List<CountryProtos.Country.Language> language) {
        this.language = language;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getGdp() {
        return gdp;
    }

    public void setGdp(int gdp) {
        this.gdp = gdp;
    }

    public String getGovernmentType() {
        return governmentType;
    }

    public void setGovernmentType(String governmentType) {
        this.governmentType = governmentType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getInternetDomain() {
        return internetDomain;
    }

    public void setInternetDomain(String internetDomain) {
        this.internetDomain = internetDomain;
    }

    public List<CountryProtos.Country.Religion> getReligion() {
        return religion;
    }

    public void setReligion(List<CountryProtos.Country.Religion> religion) {
        this.religion = religion;
    }
}
