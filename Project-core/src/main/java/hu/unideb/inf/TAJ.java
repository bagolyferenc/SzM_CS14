package hu.unideb.inf;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class TAJ {
    private IntegerProperty id = new SimpleIntegerProperty(this,"id");
    private StringProperty tajszam = new SimpleStringProperty(this, "tajszam");
    private StringProperty name = new SimpleStringProperty(this, "name");
    //private GenderType gender;
    private StringProperty lakcim = new SimpleStringProperty(this, "lakcim");
    private StringProperty szhely = new SimpleStringProperty(this, "szhely");
    private StringProperty anev = new SimpleStringProperty(this, "anev");
    private ObjectProperty<LocalDate> sznap = new SimpleObjectProperty<>(this, "sznap");
    private ObjectProperty<ObservableList<Oltas>> oltasok = new SimpleObjectProperty<>(this, "oltasok");


    public ObservableList<Oltas> getOltasok() {
        return oltasok.get();
    }

    public ObjectProperty<ObservableList<Oltas>> oltasokProperty() {
        return oltasok;
    }

    public void setOltasok(ObservableList<Oltas> oltasok) {
        this.oltasok.set(oltasok);
    }

    /*public enum GenderType{
        MALE,FEMALE,NA
    }*/

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
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

    /*public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }*/
}
