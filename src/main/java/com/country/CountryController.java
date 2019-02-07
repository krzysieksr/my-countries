package com.country;

import com.country.guice.BasicModule;
import com.country.service.CountryService;
import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "countryController", value = "/countries")
public class CountryController extends HttpServlet {
    @Inject
    CountryService countryService;

    @Override
    public void init() {
        Injector injector = Guice.createInjector(new BasicModule());
        countryService = injector.getInstance(CountryService.class);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("zlapano GET");

        List<CountryProtos.Country> countryList = countryService.getAll(); // na tym etapie gotowa lista

        Gson gson = new Gson();
        String countriesJson = gson.toJson(countryList);

        request.setAttribute("mojeKraje", countryList);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        out.print(countriesJson);
        out.flush();
    }


}
