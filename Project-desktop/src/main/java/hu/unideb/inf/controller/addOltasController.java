package hu.unideb.inf.controller;

import hu.unideb.inf.Oltas;
import hu.unideb.inf.TAJ;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class addOltasController implements Initializable {

    private Stage stage;
    private TAJ taj;
    private Oltas oltas;


    public void init(Oltas oltas, TAJ taj, Stage stage) {
        this.stage = stage;
        this.taj = taj;
        this.oltas = oltas;

        oltas_nev.textProperty().bindBidirectional(this.oltas.oltas_neveProperty());
        orvos_nev.textProperty().bindBidirectional(this.oltas.orvos_neveProperty());
        oltas_ido.valueProperty().bindBidirectional(this.oltas.oltas_idopontjaProperty());
    }

    @FXML
    private Button mentesBtn;

    @FXML
    private TextField oltas_nev;

    @FXML
    private TextField orvos_nev;

    @FXML
    private DatePicker oltas_ido;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mentesBtn .disableProperty().bind(oltas_nev.textProperty().isEmpty().or(orvos_nev.textProperty().isEmpty()).or(oltas_ido.valueProperty().isNull()));
    }

    @FXML
    public void onCancel(){
        stage.close();
    }

    @FXML
    public void onSave(){
        taj.getOltasok().remove(oltas);
        taj.getOltasok().add(oltas);
        stage.close();
    }
}
