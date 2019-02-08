package com.country;

import com.country.converter.CountryDtoToCountryConverter;
import com.country.converter.CountryDtoToCountryConverterImpl;
import com.country.dto.CountryDTO;
import com.country.guice.BasicModule;
import com.country.service.CountryService;
import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "countryController", value = "/countries")
public class CountryController extends HttpServlet {
    private CountryService countryService;
    private CountryDtoToCountryConverter countryDtoToCountryConverter;

    @Override
    public void init() {
        Injector injector = Guice.createInjector(new BasicModule());
        countryService = injector.getInstance(CountryService.class);
        countryDtoToCountryConverter = injector.getInstance(CountryDtoToCountryConverterImpl.class);

        ownInit(countryService);
    }

    void ownInit(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        List<CountryProtos.Country> countryList = null;
        try {
            countryList = countryService.getAll();
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        Gson gson = new Gson();
        String countriesJson = gson.toJson(countryList);

        request.setAttribute("myCountries", countryList);

        response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(countriesJson);
        out.flush();

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } finally {
            reader.close();
        }

        Gson gson = new Gson();
        CountryDTO countryDTO = gson.fromJson(sb.toString(), CountryDTO.class);
        CountryProtos.Country country = countryDtoToCountryConverter.convert(countryDTO);

        countryService.createCountry(country);


    }
}
