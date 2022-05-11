package hu.unideb.inf;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class TAJ {
    private final IntegerProperty id = new SimpleIntegerProperty(this,"id");
    private final StringProperty tajszam = new SimpleStringProperty(this, "tajszam");
    private  final StringProperty name = new SimpleStringProperty(this, "name");
    private  final StringProperty lakcim = new SimpleStringProperty(this, "lakcim");
    private final StringProperty szhely = new SimpleStringProperty(this, "szhely");
    private final StringProperty anev = new SimpleStringProperty(this, "anev");
    private final ObjectProperty<LocalDate> sznap = new SimpleObjectProperty<>(this, "sznap");
    private final ObjectProperty<ObservableList<Oltas>> oltasok = new SimpleObjectProperty<>(this, "oltasok");


    public ObservableList<Oltas> getOltasok() {
        return oltasok.get();
    }

    public ObjectProperty<ObservableList<Oltas>> oltasokProperty() {
        return oltasok;
    }

    public void setOltasok(ObservableList<Oltas> oltasok) {
        this.oltasok.set(oltasok);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTajszam() {
        return tajszam.get();
    }

    public StringProperty tajszamProperty() {
        return tajszam;
    }

    public void setTajszam(String tajszam) {
        this.tajszam.set(tajszam);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLakcim() {
        return lakcim.get();
    }

    public StringProperty lakcimProperty() {
        return lakcim;
    }

    public void setLakcim(String lakcim) {
        this.lakcim.set(lakcim);
    }

    public String getSzhely() {
        return szhely.get();
    }

    public StringProperty szhelyProperty() {
        return szhely;
    }

    public void setSzhely(String szhely) {
        this.szhely.set(szhely);
    }

    public String getAnev() {
        return anev.get();
    }

    public StringProperty anevProperty() {
        return anev;
    }

    public void setAnev(String anev) {
        this.anev.set(anev);
    }

    public LocalDate getSznap() {
        return sznap.get();
    }

    public ObjectProperty<LocalDate> sznapProperty() {
        return sznap;
    }

    public void setSznap(LocalDate sznap) {
        this.sznap.set(sznap);
    }
}
