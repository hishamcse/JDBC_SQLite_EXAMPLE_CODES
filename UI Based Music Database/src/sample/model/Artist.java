package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Artist {//as we want data binding,we have to use SimpleProperty
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;

    public Artist(){
        id=new SimpleIntegerProperty();
        name=new SimpleStringProperty();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
