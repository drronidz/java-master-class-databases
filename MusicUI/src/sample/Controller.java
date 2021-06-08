package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import sample.model.Album;
import sample.model.Artist;
import sample.model.DataSource;

import java.util.concurrent.ExecutionException;

public class Controller {

    @FXML
    public TableView artistTable;

    @FXML
    public ProgressBar progressBar;

    @FXML
    public void lisArtists() {
        Task<ObservableList<Artist>> task = new GetAllArtistsTask();
        artistTable.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());

        progressBar.setVisible(true);

        task.setOnSucceeded(event -> { progressBar.setVisible(false); });
        task.setOnFailed(event -> { progressBar.setVisible(false); });

        new Thread(task).start();
    }

    @FXML
    public void listAlbumsForArtist() {
        final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
        if(artist == null) {
            System.out.println("No ARTIST SELECTED !");
            return;
        }
        Task<ObservableList<Album>> task = new Task<>() {
            @Override
            protected ObservableList<Album> call() {
                return FXCollections.observableArrayList(
                        DataSource.getInstance().queryAlbumsForArtistID(artist.getId()));
            }
        };
        artistTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }
}

class GetAllArtistsTask extends Task {

    @Override
    public ObservableList<Artist> call() throws Exception {
        return FXCollections.observableArrayList(
                DataSource.getInstance().queryArtists(DataSource.ORDER_BY_ASC)
        );
    }
}
