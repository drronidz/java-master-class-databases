package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import sample.model.Artist;
import sample.model.DataSource;

public class Controller {

    @FXML
    public TableView artistTable;

    public void lisArtists() {
        Task<ObservableList<Artist>> task = new GetAllArtistsTask();
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
