package com.country.guice;

import com.country.converter.CountryDtoToCountryConverter;
import com.country.converter.CountryDtoToCountryConverterImpl;
import com.country.dao.CountryDAO;
import com.country.service.CountryService;
import com.country.dao.CountryDAOImpl;
import com.google.inject.AbstractModule;

public class BasicModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CountryDAO.class).to(CountryDAOImpl.class);
        bind(CountryService.class).toInstance(new CountryService());
        bind(CountryDtoToCountryConverter.class).to(CountryDtoToCountryConverterImpl.class);
    }
}
