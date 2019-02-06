package java8;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class CountryDAOImpl implements CountryDAO {
    Connection conn;

    List<Country> results = new LinkedList<>();

    @Override
    public List<Country> getCountries() {
        return null;
    }
}
