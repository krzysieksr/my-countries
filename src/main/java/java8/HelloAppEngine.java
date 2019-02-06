/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java8;

// [START example]

import com.google.appengine.api.utils.SystemProperty;
import java8.AddressBookProtos.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

// With @WebServlet annotation the webapp/WEB-INF/web.xml is no longer required.
@WebServlet(name = "HelloAppEngine", value = "/hello")
public class HelloAppEngine extends HttpServlet {

    Connection conn;

    List<Country> countriesResult = new LinkedList<>();


    final String getStatement = "SELECT country.name, country.capital FROM country";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("złapano get\n");


        Properties properties = System.getProperties();
//
//    //TODO
//    Person.Builder person=Person.newBuilder();
//    person.setEmail("somemail@gmail.com");

        String url = System.getProperty("cloudsql");

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/example", "postgres", "postgres");
//            ResultSet resultSet = conn.prepareStatement(getStatement).executeQuery();

//            String string = resultSet.getString("country.name");
//            System.out.println(string);
        } catch (SQLException e) {
            System.out.println("connectionf ailure");
        }
//
//
//        try (ResultSet rs = conn.prepareStatement(getStatement).executeQuery()) {
//            while (rs.next()) {
//                String string = rs.getString("country.name");
//                System.out.println(string);
//            }
//
//        } catch (SQLException e) {
//            throw new ServletException("SQL error", e);
//        }

//
//        response.setContentType("text/plain");
//        response.getWriter().println("test");
//        response.getWriter().println("Hello App Engine - Standard using "
//                + SystemProperty.version.get() + " Java "
//                + properties.get("java.specification.version"));
    }

    //
    public static String getInfo() {
        return "Version: " + System.getProperty("java.version")
                + " O S: " + System.getProperty("os.name")
                + " User: " + System.getProperty("user.name") + "TESTTTT";
    }

}
// [END example]
