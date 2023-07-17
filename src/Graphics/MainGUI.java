package Graphics;

import Control.StartseiteController;
import Model.DatabaseProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Main class is the initial starting point.
 *
 * @author Billy Dongmo
 * @version 1.0
 */
public class MainGUI extends Application {

    /**
     * Erzeugt das GUIfenster der Startseite.
     *
     * @throws IOException
     *             wird geworfen, falls startseite.fxml nicht gelesen werden
     *             konnte
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseProvider databaseProvider = new DatabaseProvider(false);

        FXMLLoader root = new FXMLLoader((getClass().getResource("startseite.fxml")));
        Scene scene = new Scene(root.load());

        /**
         * Hinweis über JavaFx:
         * Übergebe Parameter, die von StartseiteController benutzt werden, um die
         * Funtionen zu laufen (DatabaseProvider /Server ).
         * Alle andere Aufrufe von neu Seiten werden die gleiche logik folgen. Die
         * parameters werden durch set order get übergeben.
         */
        StartseiteController startseiteController = root.getController(); // get the controller of the running Gui page
        startseiteController.setParams(databaseProvider);// set(get) the params that are needed

        primaryStage.setTitle("Phase 10");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
