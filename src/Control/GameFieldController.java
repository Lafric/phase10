package Control;

import Model.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;

public class GameFieldController {
    public ComboBox<String> dropdown_KarteWaehlen_karteSpielen;
    public ComboBox<String> dropdown_Zielstapel_StapelBewegen;
    public ImageView imagekarte_H1;
    public ImageView imagekarte_H2;
    public ImageView imagekarte_H3;
    public ImageView imagekarte_H4;
    public ImageView imagekarte_H5;
    public ImageView imagekarte_H6;
    public ImageView imagekarte_H7;
    public ImageView imagekarte_H8;
    public ImageView imagekarte_H10;
    public ImageView imagekarte_H9;
    public ImageView imgkarteSpieler1_1;
    public ImageView imgkarteSpieler1_2;
    public ImageView imgkarteSpieler1_3;
    public ImageView imgkarteSpieler1_4;
    public ImageView imgkarteSpieler1_5;
    public ImageView imgkarteSpieler1_6;
    public ImageView imgkarteSpieler1_7;
    public ImageView imgkarteSpieler1_8;
    public ImageView imgkarteSpieler1_9;
    public ImageView imgkarteSpieler1_10;
    public ImageView imgkarteSpieler2_1;
    public ImageView imgkarteSpieler2_2;
    public ImageView imgkarteSpieler2_3;
    public ImageView imgkarteSpieler2_4;
    public ImageView imgkarteSpieler2_5;
    public ImageView imgkarteSpieler2_6;
    public ImageView imgkarteSpieler2_7;
    public ImageView imgkarteSpieler2_8;
    public ImageView imgkarteSpieler2_9;
    public ImageView imgkarteSpieler2_10;
    public ImageView imgkarteSpieler3_1;
    public ImageView imgkarteSpieler3_2;
    public ImageView imgkarteSpieler3_3;
    public ImageView imgkarteSpieler3_4;
    public ImageView imgkarteSpieler3_5;
    public ImageView imgkarteSpieler3_6;
    public ImageView imgkarteSpieler3_7;
    public ImageView imgkarteSpieler3_8;
    public ImageView imgkarteSpieler3_9;
    public ImageView imgkarteSpieler3_10;
    public ImageView imgkarteSpieler4_1;
    public ImageView imgkarteSpieler4_2;
    public ImageView imgkarteSpieler4_3;
    public ImageView imgkarteSpieler4_4;
    public ImageView imgkarteSpieler4_5;
    public ImageView imgkarteSpieler4_6;
    public ImageView imgkarteSpieler4_7;
    public ImageView imgkarteSpieler4_8;
    public ImageView imgkarteSpieler4_9;
    public ImageView imgkarteSpieler4_10;
    public ImageView imgkarteSpieler5_1;
    public ImageView imgkarteSpieler5_2;
    public ImageView imgkarteSpieler5_3;
    public ImageView imgkarteSpieler5_4;
    public ImageView imgkarteSpieler5_5;
    public ImageView imgkarteSpieler5_6;
    public ImageView imgkarteSpieler5_7;
    public ImageView imgkarteSpieler5_8;
    public ImageView imgkarteSpieler5_9;
    public ImageView imgkarteSpieler5_10;
    public ImageView imagekarte_Uebersichtskarte;
    public Rectangle kasten_Gegenspieler1;
    public Label label_Gegenspieler1Name;
    public Label label_Gegenspieler1Handkarten;
    public Label label_Gegenspieler1Punkte;
    public Rectangle kasten_Gegenspieler2;
    public Label label_Gegenspieler2;
    public Label label_Gegenspieler2Handkarten;
    public Label label_Gegenspieler2Punkte;
    public Rectangle kasten_Gegenspieler3;
    public Label label_Gegenspieler3;
    public Label label_Gegenspieler3Handkarten;
    public Label label_Gegenspieler3Punkte;
    public Rectangle kasten_Gegenspieler4;
    public Label label_Gegenspieler4;
    public Label label_Gegenspieler4Handkarten;
    public Label label_Gegenspieler4Punkte;
    public Rectangle kasten_Gegenspieler5;
    public Label label_Gegenspieler5;
    public Label label_Gegenspieler5Handkarten;
    public Label label_Gegenspieler5Punkte;
    public Rectangle kasten_Client;
    public Label label_Client;
    public Label label_CLientHandkarten;
    public Label label_ClientPunkte;
    public Button launchchat;
    public Button karte_ziehen;
    public Button zug_beenden;
    public Button phase_bestätigen;
    public Button Karte_spielen;

    /**
     * Methode zum Beenden des Zuges
     * @param event
     */
    public void zug_beenden(ActionEvent event) {
    }

    /**
     * Methode zum Spielen einer Karte
     * Fälle :
     * Zum eigenen Ablagestapel für Phase Rule 1 oder 2
     * Zum Ablagestapel eines Gegners für Phase Rule 1 oder 2
     * Zum Uebersichtskartenstapel
     * @param event
     */
    public void karte_spielen(ActionEvent event) {
    }
    /**
     * Methode zum Ziehen einer Karte aus der AufnahmeStappel
     * @param event
     */
    public void karte_ziehen(ActionEvent event) {
    }
    /**
     * Methode zum Ziehen der Übersichtskarten des Clients
     * @param event
     */
    public void UebersichtKarte_Ziehen(ActionEvent event) {
    }
    /**
     * Methode zum Bestätigen der Gültigkeit der Phase
     * @param event
     */
    public void bestätigt_Phase(ActionEvent event) {
    }

    /**
     * Methode zum Bewegen der Karte zum Ablagestapel
     * @param event
     */
    public void bewegenZumUebersichtskartenstapel(ActionEvent event) {
    }

    /**
     * Methode zum Bewegen der Karte zum Ablagestapel
     * @param event
     */
    public void bewegenZumAblagestapel(ActionEvent event) {
    }

    /**
     * Methode zum Bewegen der Karte zum Ablagestapel des Gegners
     * @param event
     */
    public void bewegenZumAblagestapelGegner(ActionEvent event) {
    }

    /**
     * Methode zum Starten des Chats
     * @param event
     * @throws IOException
     */
    public void LaunchChat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/chatRaumInGameField.fxml"));
        Parent root = loader.load();


        // TODO next scene öffnen
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Spieldfeld");

        stage.setOnCloseRequest(e -> {
            //TODO: disconnect user
        });

        stage.show();


    }

    /**
     * Methode zur Umwandlung von String zu Karte wenn user auf ein Option Clickt
     * @param wert
     * @return
     */
    public Card StringtoKarte(String wert) {
        return null;
    };

    /**
     * String zum Anzeige der Karten anhand der Wert und Farbe
     * @param card
     * @return
     */
    public String KartetoString(Card card) {
        String wert;
        String farbe;

        wert = switch (card.getType().getNumber()) {
            case 1 -> "1";
            case 2 -> "2";
            case 3 -> "3";
            case 4 -> "4";
            case 5 -> "5";
            case 6 -> "6";
            case 7 -> "7";
            case 8 -> "8";
            case 9 -> "9";
            case 10 -> "10";
            case 11 -> "11";
            case 12 -> "12";
            case -1 -> "Skip";
            case -2 -> "Joker";
            default -> "Fehler";
        };

        farbe = switch (card.getColor()) {
            case YELLOW -> "Gelb";
            case GREEN -> "Grün";
            case BLUE -> "Blau";
            case RED -> "Rot";
            case PINK -> "Pink";
        };


        return farbe + " " + wert;
    }

    /**
     * Methode zum setzen der Karten auf der Hand
     * @throws RemoteException
     */

    public void setDropdown_KarteWaehlen_karteSpielen() throws RemoteException {
        dropdown_KarteWaehlen_karteSpielen.getItems().addAll("karte1","karte2");
       /** for (Karte karte : client.get_spielstatus().getEigeneHandkarten()) {
            dropdown_KarteWaehlen_karteSpielen.getItems().addAll(KartetoString(karte));
        }**/
    }

    /**
     * Methode zum setzen der Karten auf der Hand
     */
    public void setStapel() {
        dropdown_Zielstapel_StapelBewegen.getItems().addAll( "Mein Stapel Rule 1", "Mein Stapel Rule 2", "Uebersichtskarte", "Spieler 1 Rule 1","Spieler 1 Rule 2","Spieler 2 Rule 1", "Spieler 2 Rule 2", "Spieler 3 Rule 1","Spieler 3 Rule 2","Spieler 4 Rule 1","Spieler 4 Rule 2","Spieler 5 Rule 1","Spieler 5 Rule 2");
    }

}
