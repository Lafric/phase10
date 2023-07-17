package Control;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import Model.DatabaseProvider;
import Model.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BestenlisteController {
    @FXML
    public TableView<UserData> tabelle_bestenliste;

    @FXML
    public TableColumn<UserData, String> spalte_nutzername;

    @FXML
    public TableColumn<UserData, Integer> spalte_gespielteSpiele;

    @FXML
    public TableColumn<UserData, Integer> spalte_siege;

    @FXML
    public TableColumn<UserData, Integer> spalte_siegesrate;

    public DatabaseProvider dbProvider;
    public ListView<UserData> scoreList = new ListView<UserData>();

    public void initialize() {
        spalte_nutzername.setCellValueFactory(new PropertyValueFactory<>("nutzername"));
        spalte_gespielteSpiele.setCellValueFactory(new PropertyValueFactory<>("gespielteSpiele"));
        spalte_siege.setCellValueFactory(new PropertyValueFactory<>("siege"));
        spalte_siegesrate.setCellValueFactory(new PropertyValueFactory<>("siegesrate"));

        this.dbProvider = new DatabaseProvider(false);
        tabelle_bestenliste.setItems(getPlayerData());
    }

    
    /** 
     * @return ObservableList<UserData>
     */
    private ObservableList<UserData> getPlayerData() {
        ObservableList<UserData> data = FXCollections.observableArrayList();

        try {
            UserData[] userData = this.dbProvider.getUserData();
            System.out.println("Got UserData from DBProvider");
            for (UserData user : userData) {
                data.add(user);
            }
            tabelle_bestenliste.getItems().addAll(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setParams(DatabaseProvider databaseProvider) {
        this.dbProvider = databaseProvider;
    }
}
