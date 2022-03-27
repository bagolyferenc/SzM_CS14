package hu.unideb.dao;

import javax.print.attribute.standard.MediaSize;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

 public class TAJDAOImplement implements TajDAO {

    private static final String SELECT_ALL_TAJ = "SELECT * FROM TAJ";
    public Properties properties = new Properties();
    private String connectionURL;

    public TAJDAOImplement(){
        try {
            properties.load(getClass().getResourceAsStream("/application.properties"));
            connectionURL = properties.getProperty("db.url");
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public List<hu.unideb.inf.TAJ> findAll() {

        List<hu.unideb.inf.TAJ> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_TAJ)

        ){
            while (rs.next()){
                hu.unideb.inf.TAJ taj = new hu.unideb.inf.TAJ();
                taj.setId(rs.getInt("id"));
                taj.setTajszam(rs.getString("tajszam"));
                taj.setName(rs.getString("name"));

                //TAJ.GenderType gender = TAJ.GenderType.valueOf(rs.getInt("gender"));
                //taj.setSznap(gender == null ? TAJ.GenderType.NA : TAJ.GenderType.valueOf(gender));
                //taj.setGender(rs.getInt("gender"));
                taj.setVercsoport(rs.getString("vercsoport"));
                taj.setLakcim(rs.getString("lakcim"));
                taj.setSzhely(rs.getString("szhely"));
                taj.setAnev(rs.getString("anev"));

                Date date = Date.valueOf(rs.getString("sznap"));
                taj.setSznap(date == null ? LocalDate.now() : date.toLocalDate());

                result.add(taj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public hu.unideb.inf.TAJ save(hu.unideb.inf.TAJ taj) {
        return null;
    }

    @Override
    public void delete(hu.unideb.inf.TAJ taj) {

    }
}
