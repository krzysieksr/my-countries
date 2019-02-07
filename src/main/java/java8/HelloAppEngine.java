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
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

// With @WebServlet annotation the webapp/WEB-INF/web.xml is no longer required.
@WebServlet(name = "HelloAppEngine", value = "/hello")
public class HelloAppEngine extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Properties properties = System.getProperties();

        System.out.println("TUTAJ");
//    //TODO
//    Person.Builder person=Person.newBuilder();
//    person.setEmail("somemail@gmail.com");


        /** LOCAL DB*/
//    try {
//      Connection conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/countries","krzysiek","dupa");
//      System.out.println(conn==null);
//      ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM country");
////        System.out.println("resultSet: "+resultSet==null);
//    resultSet.next();
//        System.out.println(resultSet);
//        String id = resultSet.getString("name");
//        System.out.println(id);
//    } catch (SQLExceptiocreateConnectionPooln e) {
//      e.printStackTrace();
//    }


        /**Connection to cloud DB from local*/
//    String countryName=null;
//    try {
//      Connection conn=DriverManager.getConnection("jdbc:postgresql://35.246.86.181:5432/postgres","postgres","dupa");
//      System.out.println(conn==null);
//      ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM country");
////        System.out.println("resultSet: "+resultSet==null);
//    resultSet.next();
//        System.out.println(resultSet);
//        countryName= resultSet.getString("name");
//        System.out.println(countryName);
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }


        HikariConfig config = new HikariConfig();

// Configure which instance and what database user to connect with.
        config.setJdbcUrl(String.format("jdbc:postgresql:///%s", "postgres"));
        config.setUsername("postgres"); // e.g. "root", "postgres"
        config.setPassword("dupa"); // e.g. "my-password"

// For Java users, the Cloud SQL JDBC Socket Factory can provide authenticated connections.
// See https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory for details.
        config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.postgres.SocketFactory");
        config.addDataSourceProperty("cloudSqlInstance", "country-230809:europe-west2:country-db");

        DataSource pool = new HikariDataSource(config);
        Connection conn = null;
        try {
            conn = pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ResultSet resultSet;
        String countryName = null;
        try {
            resultSet = conn.createStatement().executeQuery("SELECT * FROM country");
            resultSet.next();
            System.out.println(resultSet);
            countryName = resultSet.getString("name");
            System.out.println(countryName);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        response.setContentType("text/plain");
        response.getWriter().println("Hello App Engine - Standard using "
                + SystemProperty.version.get() + " Java "
                + properties.get("java.specification.version")
                + " " + (countryName));
    }

    public static String getInfo() {
        return "Version: " + System.getProperty("java.version")
                + " OS: " + System.getProperty("os.name")
                + " User: " + System.getProperty("user.name");
    }
}
// [END example]
