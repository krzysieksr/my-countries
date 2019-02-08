package com.country.converter;

import com.country.CountryProtos;
import com.country.dto.CountryDTO;

public class CountryDtoToCountryConverterImpl implements CountryDtoToCountryConverter {
    @Override
    public CountryProtos.Country convert(CountryDTO countryDTO) {
        CountryProtos.Country country = CountryProtos.Country.newBuilder()
                .setName(countryDTO.getName())
                .setPopulation(countryDTO.getPopulation())
                .setArea(countryDTO.getArea())
                .addAllLanguages(countryDTO.getLanguage())
                .addAllReligions(countryDTO.getReligion())
                .setCapital(countryDTO.getCapital())
                .setGdp(countryDTO.getGdp())
                .setGovernmentType(countryDTO.getGovernmentType())
                .setCurrency(countryDTO.getCurrency())
                .setInternetDomain(countryDTO.getInternetDomain())
                .build();

        return country;
    }
}
