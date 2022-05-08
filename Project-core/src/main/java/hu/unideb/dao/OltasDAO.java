package hu.unideb.dao;

import hu.unideb.inf.TAJ;
import hu.unideb.inf.Oltas;
import java.util.List;

public interface OltasDAO {

    List<Oltas> findAll(TAJ taj);
    List<Oltas> findAll(int tajId);
    Oltas save(Oltas o, int tajId);
    void delete(Oltas o);



}
