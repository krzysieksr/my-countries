package com.country.guice;

import com.country.service.CountryDAO;
import com.country.service.CountryService;
import com.country.service.DefaultCountryDAO;
import com.google.inject.AbstractModule;

public class BasicModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CountryDAO.class).to(DefaultCountryDAO.class);
        bind(CountryService.class).toInstance(new CountryService());
    }
}
