package hu.unideb.inf.controller;

import hu.unideb.dao.TAJDAOImplement;
import hu.unideb.dao.TajDAO;
import hu.unideb.inf.App;
import hu.unideb.inf.TAJ;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainWindowController implements Initializable {

    private final TajDAO dao = new TAJDAOImplement();
    private List<TAJ> persons;

    @FXML
    private TableView<TAJ> tajTable;

    @FXML
    private TableColumn<TAJ, String> nameColumn;

    @FXML
    private TableColumn<TAJ, Void> opciokColumn;

    @FXML
    private TextField tajKereses;

    @FXML
    public void onTajKereses(){
        List<TAJ> szures = persons.stream().filter(taj -> taj.getTajszam().contains(tajKereses.getText())).collect(Collectors.toList());
        tajTable.getItems().setAll(szures);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        opciokColumn.setCellFactory(tajStringTableColumn -> new TableCell<>(){

            private final Button delBtn = new Button("Törlés");
            private final Button editBtn = new Button("Szerkesztés");

            {
                delBtn.setOnAction(actionEvent -> {
                    TAJ c = getTableRow().getItem();
                    deleteTAJ(c);
                    refreshTable();
                });

                editBtn.setOnAction(actionEvent -> {
                    TAJ c = getTableRow().getItem();
                    editTAJ(c);
                    refreshTable();
                });
            }

            @Override
            protected void updateItem(Void elem, boolean ures) {
                super.updateItem(elem, ures);
                if(ures){
                    setGraphic(null);
                }
                else{
                    HBox container = new HBox();
                    container.getChildren().addAll(delBtn, editBtn);
                    container.setSpacing(5.0);
                    setGraphic(container);
                }
            }
        });
    }

    private void editTAJ(TAJ t) {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/editTAJ.fxml");
        editTAJController controller = fxmlLoader.getController();
        controller.setTAJ(t);
    }

    private void deleteTAJ(TAJ t) {
        final ButtonType igenBtn = new ButtonType("Igen");
        final ButtonType nemBtn = new ButtonType("Nem");

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan törölni szeretné "+  t.getName() + "-t a(z) adatbázisból?", igenBtn, nemBtn);
        confirm.showAndWait().ifPresent(buttonType -> {
            if (buttonType.equals(igenBtn)) {
                dao.delete(t);
            }
        });


    }

    private void refreshTable() {
        persons = dao.findAll();
        tajTable.getItems().setAll(persons);
    }

    @FXML
    public void onExit(){
            Platform.exit();
    }

    @FXML
    public void onAdd(){
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/editTAJ.fxml");
        editTAJController controller = fxmlLoader.getController();
        controller.setTAJ(new TAJ());
    }
}
