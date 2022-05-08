package hu.unideb.inf;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Oltas {
    private IntegerProperty id = new SimpleIntegerProperty("this", "id");
    private StringProperty oltas_neve = new SimpleStringProperty("this", "oltas_neve");
    private StringProperty orvos_neve = new SimpleStringProperty("this", "orvos_neve");
    private ObjectProperty<LocalDate> oltas_idopontja = new SimpleObjectProperty<>(this, "oltas_idopontja");
    private IntegerProperty tajId = new SimpleIntegerProperty(this, "taj_id");

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getOltas_neve() {
        return oltas_neve.get();
    }

    public StringProperty oltas_neveProperty() {
        return oltas_neve;
    }

    public void setOltas_neve(String oltas_neve) {
        this.oltas_neve.set(oltas_neve);
    }

    public String getOrvos_neve() {
        return orvos_neve.get();
    }

    public StringProperty orvos_neveProperty() {
        return orvos_neve;
    }

    public void setOrvos_neve(String orvos_neve) {
        this.orvos_neve.set(orvos_neve);
    }

    public LocalDate getOltas_idopontja() {
        return oltas_idopontja.get();
    }

    public ObjectProperty<LocalDate> oltas_idopontjaProperty() {
        return oltas_idopontja;
    }

    public void setOltas_idopontja(LocalDate oltas_idopontja) {
        this.oltas_idopontja.set(oltas_idopontja);
    }
}
