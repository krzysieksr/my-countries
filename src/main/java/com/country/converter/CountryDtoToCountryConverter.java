package com.country.converter;

import com.country.CountryProtos;
import com.country.dto.CountryDTO;

public interface CountryDtoToCountryConverter {
    CountryProtos.Country convert(CountryDTO countryDTO);
}
