package hu.unideb.inf.controller;

import hu.unideb.dao.OltasDAO;
import hu.unideb.dao.OltasDAOImplement;
import hu.unideb.dao.TAJDAOImplement;
import hu.unideb.dao.TajDAO;
import hu.unideb.inf.App;
import hu.unideb.inf.Oltas;
import hu.unideb.inf.TAJ;
import javafx.beans.binding.StringBinding;
import javafx.beans.binding.When;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class editTAJController implements Initializable {

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
        taj.getOltasok().forEach(oltas -> {
            oltas.setId(0);
            oltasDAO.save(oltas,taj.getId());
        });
        App.loadFXML("/fxml/main_window.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        oltasok.setCellFactory(param -> {
            ListCell<Oltas> cell = new ListCell<>();
            ContextMenu contextMenu = new ContextMenu();

            MenuItem editItem = new MenuItem("Szerkeztés");
            MenuItem deleteItem = new MenuItem("Törlés");

            contextMenu.getItems().addAll(editItem, deleteItem);

            editItem.setOnAction(event -> {
                Oltas item = cell.getItem();
                showOltasAblak(item);
            });

            deleteItem.setOnAction(event -> {
                taj.getOltasok().remove(cell.getItem());
            });

            StringBinding cellTextBinding = new When(cell.itemProperty().isNotNull()).then(cell.itemProperty().asString()).otherwise("");
            cell.textProperty().bind(cellTextBinding);

            cell.emptyProperty().addListener(((observableValue, aBoolean, t1) -> {
                if (t1){
                    cell.setContextMenu(null);
                }else{
                    cell.setContextMenu(contextMenu);
                }
            }));
            return cell;
        });
    }

    @FXML
    public void addUjOltas(){
        showOltasAblak();
    }

    private void showOltasAblak(Oltas oltas) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addOltas.fxml"));

        try {
            Parent root = loader.load();
            addOltasController controller = loader.getController();
            controller.init(oltas, taj, stage);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showOltasAblak(){
        showOltasAblak(new Oltas());
    }
}
