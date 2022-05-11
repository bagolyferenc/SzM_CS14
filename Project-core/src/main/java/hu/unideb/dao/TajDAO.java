package hu.unideb.dao;

import  java.util.List;

public interface TajDAO {

    List<hu.unideb.inf.TAJ> findAll();

    hu.unideb.inf.TAJ save(hu.unideb.inf.TAJ taj);

    void delete(hu.unideb.inf.TAJ taj);

}
