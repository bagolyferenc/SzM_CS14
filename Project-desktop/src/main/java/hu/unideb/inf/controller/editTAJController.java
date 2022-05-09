package hu.unideb.inf.controller;

import hu.unideb.dao.OltasDAO;
import hu.unideb.dao.OltasDAOImplement;
import hu.unideb.dao.TAJDAOImplement;
import hu.unideb.dao.TajDAO;
import hu.unideb.inf.App;
import hu.unideb.inf.Oltas;
import hu.unideb.inf.TAJ;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;


public class editTAJController {

    private TAJ taj;
    //private Oltas oltas;
    private OltasDAO oltasDAO = new OltasDAOImplement();
    private TajDAO tajDAO = new TAJDAOImplement();


    @FXML
    private TextField name;

    @FXML
    private TextField szul_hely;

    @FXML
    private DatePicker szuli;

    @FXML
    private TextField anya_neve;

    @FXML
    private TextField lakcim;

    @FXML
    private TextField tajsz;

    @FXML
    ListView<Oltas> oltasok;

    public void setTAJ(TAJ t) {
        this.taj = t;

        List<Oltas> olist = oltasDAO.findAll(t.getId());
        taj.setOltasok(FXCollections.observableArrayList(olist));

        name.textProperty().bindBidirectional(taj.nameProperty());
        szul_hely.textProperty().bindBidirectional(taj.szhelyProperty());
        anya_neve.textProperty().bindBidirectional(taj.anevProperty());
        lakcim.textProperty().bindBidirectional(taj.lakcimProperty());
        tajsz.textProperty().bindBidirectional(taj.tajszamProperty());
        oltasok.itemsProperty().bindBidirectional(taj.oltasokProperty());
        szuli.valueProperty().bindBidirectional(taj.sznapProperty());
    }

    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/main_window.fxml");
    }

    @FXML
    public void onSave(){
        taj = tajDAO.save(taj);
        oltasDAO.deleteAll(taj.getId());
        taj.getOltasok().forEach(oltas -> oltasDAO.save(oltas,taj.getId()));
        App.loadFXML("/fxml/main_window.fxml");
    }
}
