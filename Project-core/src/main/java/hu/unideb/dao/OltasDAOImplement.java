package hu.unideb.dao;

import hu.unideb.config.TajConfiguration;
import hu.unideb.inf.Oltas;
import hu.unideb.inf.TAJ;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OltasDAOImplement implements OltasDAO{

    private static final String SELECT_OLTAS_BY_TAJ_ID = "SELECT * FROM OLTAS WHERE taj_id=?";
    private static final String INSERT_OLTAS = "INSERT INTO OLTAS(oltas_neve, orvos_neve, oltas_idopontja, taj_id) VALUES (?,?,?,?)";
    private static final String UPDATE_OLTAS = "UPDATE OLTAS SET oltas_neve = ?, orvos_neve = ?, oltas_idopontja = ? WHERE id = ?";
    private static final String DELETE_OLTAS = "DELETE FROM OLTAS WHERE id = ?";
    private String connectionURL;

    public OltasDAOImplement() {
        this.connectionURL = TajConfiguration.getValues("db.url");
    }

    @Override
    public List<Oltas> findAll(TAJ taj) {
        return findAll(taj.getId());
    }

    @Override
    public List<Oltas> findAll(int tajId) {
        List<Oltas> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = c.prepareStatement(SELECT_OLTAS_BY_TAJ_ID)){

            statement.setInt(1, tajId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Oltas oltas = new Oltas();
                oltas.setId(rs.getInt("id"));
                oltas.setOltas_neve(rs.getString("oltas_neve"));
                oltas.setOrvos_neve(rs.getString("orvos_neve"));
                Date date = Date.valueOf(rs.getString("oltas_idopontja"));
                oltas.setOltas_idopontja(date == null ? LocalDate.now() : date.toLocalDate());

                result.add(oltas);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    @Override
    public Oltas save(Oltas oltas, int tajId) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = oltas.getId() <= 0 ? c.prepareStatement(INSERT_OLTAS, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_OLTAS);
        ){

            if(oltas.getId() > 0) { //UPDATE
                stmt.setInt(4, oltas.getId());
            }else{
                stmt.setInt(4, tajId);
            }

            stmt.setString(1, oltas.getOltas_neve());
            stmt.setString(2, oltas.getOrvos_neve());
            stmt.setString(3, oltas.getOltas_idopontja().toString());


            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                return null;
            }

            if(oltas.getId() <= 0){ //INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if (genKeys.next()){
                    oltas.setId(genKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return oltas;
    }

    @Override
    public void delete(Oltas o) {
        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement(DELETE_OLTAS );
        ){

            stmt.setInt(1, o.getId());
            stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll(int tajid) {
        findAll(tajid).forEach(this::delete);
    }
}
