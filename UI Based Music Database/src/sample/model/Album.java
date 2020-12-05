package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Album {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty artist_id;

    public Album() {
        id=new SimpleIntegerProperty();
        name=new SimpleStringProperty();
        artist_id=new SimpleIntegerProperty();
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

    public int getArtist() {
        return artist_id.get();
    }

    public void setArtist(int artist) {
        this.artist_id.set(artist);
    }
}
