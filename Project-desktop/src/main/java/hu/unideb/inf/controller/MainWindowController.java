package hu.unideb.inf.controller;

import hu.unideb.dao.TAJDAOImplement;
import hu.unideb.dao.TajDAO;
import hu.unideb.inf.TAJ;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    TajDAO dao = new TAJDAOImplement();

    @FXML
    private TableView<TAJ> tajTable;

    @FXML
    private TableColumn<TAJ, String> nameColumn;

    @FXML
    private TableColumn<TAJ, String> tajColumn;

    @FXML
    private TableColumn<TAJ, String> vercsoportColumn;
    @FXML
    private TableColumn<TAJ, String> lakcimColumn;
    @FXML
    private TableColumn<TAJ, String> szulhelyColumn;
    @FXML
    private TableColumn<TAJ, String> szulnapColumn;
    @FXML
    private TableColumn<TAJ, String> anyjaneveColumn;
    @FXML
    private TableColumn<TAJ, Void> opciokColumn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tajColumn.setCellValueFactory(new PropertyValueFactory<>("tajszam"));
        vercsoportColumn.setCellValueFactory(new PropertyValueFactory<>("vercsoport"));
        lakcimColumn.setCellValueFactory(new PropertyValueFactory<>("lakcim"));
        szulhelyColumn.setCellValueFactory(new PropertyValueFactory<>("szhely"));
        szulnapColumn.setCellValueFactory(new PropertyValueFactory<>("sznap"));
        anyjaneveColumn.setCellValueFactory(new PropertyValueFactory<>("anev"));
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
                    //TODO
                    System.out.println("Adat szerkesztése");
                    refreshTable();
                });
            }

            @Override
            protected void updateItem(Void s, boolean b) {
                super.updateItem(s, b);
                if(b){
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

    private void deleteTAJ(TAJ c) {//TODO gombok cseréje yes -> igen, no -> nem
        final ButtonType igenBtn = new ButtonType("Igen");
        final ButtonType nemBtn = new ButtonType("Nem");

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan törölni szeretné "+  c.getName() + " az adatbázisból?", igenBtn, nemBtn);
        confirm.showAndWait().ifPresent(buttonType -> {
            if (buttonType.equals(igenBtn)) {
                dao.delete(c);
            }
        });


    }

    private void refreshTable() {
        tajTable.getItems().setAll(dao.findAll());
    }

    @FXML
        public void onExit(){
            Platform.exit();
    }
}
