package hu.unideb.inf.controller;

import hu.unideb.inf.Oltas;
import hu.unideb.inf.TAJ;
import javafx.stage.Stage;

public class addOltasController {

    private Stage stage;
    private TAJ taj;
    private Oltas oltas;

    public void init(Oltas oltas, TAJ taj, Stage stage) {
        this.stage = stage;
        this.taj = taj;
        this.oltas = oltas;
    }
}
