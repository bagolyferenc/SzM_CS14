package hu.unideb.dao;

import hu.unideb.config.TajConfiguration;
import hu.unideb.inf.TAJ;

import javax.print.attribute.standard.MediaSize;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

 public class TAJDAOImplement implements TajDAO {

     private static final String SELECT_ALL_TAJ = "SELECT * FROM TAJ";
     private static final String INSERT_TAJ = "INSERT INTO TAJ(tajszam, name, vercsoport, lakcim, szhely, anev, sznap) VALUES(?,?,?,?,?,?,?) ";
     private static final String UPDATE_TAJ = "UPDATE TAJ SET tajszam=?, name=?, vercsoport=?, lakcim=?, szhely=?, anev=?, sznap=? WHERE id=?";
     private static final String DELETE_TAJ = "DELETE FROM TAJ WHERE id=?";
     public Properties properties = new Properties();
     private String connectionURL;

    public TAJDAOImplement(){
        connectionURL = TajConfiguration.getValues("db.url");
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
        try(Connection c = DriverManager.getConnection(properties.getProperty(connectionURL));
            PreparedStatement stmt = taj.getId() <= 0 ? c.prepareStatement(INSERT_TAJ, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_TAJ);
        ){

            if(taj.getId() < 0) { //UPDATE
                stmt.setInt(7, taj.getId());
            }
            //tajszam=?, name=?, vercsoport=?, lakcim=?, szhely=?, anev=?, sznap=? WHERE id=?

            stmt.setString(1, taj.getTajszam());
            stmt.setString(2, taj.getName());
            stmt.setString(3, taj.getVercsoport());
            stmt.setString(4, taj.getLakcim());
            stmt.setString(5, taj.getSzhely());
            stmt.setString(6, taj.getAnev());
            stmt.setString(7, taj.getSznap().toString());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                return null;
            }

            if(taj.getId() <= 0){ //INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if (genKeys.next()){
                    taj.setId(genKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return taj;
    }

    @Override
    public void delete(hu.unideb.inf.TAJ taj) {

        try (Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(DELETE_TAJ);
        ){

            stmt.setInt(1,taj.getId());
            stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
