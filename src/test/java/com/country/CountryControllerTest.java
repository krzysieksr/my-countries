package com.country;

import com.country.service.CountryService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertTrue;

public class CountryControllerTest {

    @Mock
    private CountryService countryService;

    private CountryController countryController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        countryController = new CountryController();
        countryController.ownInit(countryService);
    }


    @Test
    public void testDoGet() throws IOException, SQLException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponseWrapper.class);

        CountryProtos.Country countryA = CountryProtos.Country.newBuilder()
                .setName("Polska")
                .setArea(1000)
                .build();

        List<CountryProtos.Country> countryList = Arrays.asList(countryA);

        StringWriter stringWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));

        when(countryService.getAll()).thenReturn(countryList);

        countryController
                .doGet(request, response);

        assertTrue(stringWriter.toString().contains("Polska"));

    }
}