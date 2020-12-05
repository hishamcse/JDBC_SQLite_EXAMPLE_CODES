package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import sample.model.Album;
import sample.model.Artist;
import sample.model.Datasource;

import java.util.AbstractList;

public class Controller {

    @FXML
    private TableView artistTable;
    @FXML
    private ProgressBar progressBar;

    @FXML
    public void showartists(){
        Task<ObservableList<Artist>> task=new GetexTask();
        artistTable.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());

        progressBar.setVisible(true);

        task.setOnSucceeded(event -> progressBar.setVisible(false));
        task.setOnFailed(event -> progressBar.setVisible(false));

        new Thread(task).start();
    }


    @FXML
    public void showalbumforartist(){
        Artist artist=(Artist) artistTable.getSelectionModel().getSelectedItem();
        if(artist==null){
            System.out.println("no artist selected");
            return;
        }
        Task<ObservableList<Album>> task=new Task<ObservableList<Album>>() {
            @Override
            protected ObservableList<Album> call() throws Exception {
                return FXCollections.observableArrayList(
                        Datasource.getInstance().queryalbumbyartist(artist.getId()));
            }
        };
        artistTable.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());

        progressBar.setVisible(true);

        task.setOnSucceeded(event -> progressBar.setVisible(false));
        task.setOnFailed(event -> progressBar.setVisible(false));

        new Thread(task).start();
    }

    @FXML
    public void update(){            //manual way.not recommended.it should be done through dialog box
        final Artist artist=(Artist) artistTable.getItems().get(2);

        Task<Boolean> task=new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return Datasource.getInstance().updateartist(artist.getId(),"AC/DC");
            }
        };
        task.setOnSucceeded(e->{
            if(task.valueProperty().get()){
                artist.setName("AC/DC");
                artistTable.refresh();
            }
        });

        new Thread(task).start();
    }
}

class GetexTask extends Task{
    @Override
    public ObservableList<Artist> call(){
        return FXCollections.observableArrayList
                (Datasource.getInstance().queryartists(Datasource.ORDER_BY_ASC));
    }
}