package Control;

import Model.Card;
import Model.CardColor;
import Model.CardType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Stack;

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
    public ImageView imagekarte_H11;
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

    //Für Dummy Logik
    private int[] player;
    private Rectangle[] rechtecke = new Rectangle[]{kasten_Client, kasten_Gegenspieler1, kasten_Gegenspieler2, kasten_Gegenspieler3, kasten_Gegenspieler4, kasten_Gegenspieler5};
    private ImageView[] handkarte_CurrentPlayer  = new ImageView[]{imagekarte_H1, imagekarte_H2, imagekarte_H3, imagekarte_H4, imagekarte_H5, imagekarte_H6, imagekarte_H7, imagekarte_H8, imagekarte_H9, imagekarte_H10, imagekarte_H11};
    private Card[] cards = new Card[]{ new Card(0, CardColor.YELLOW, CardType.ONE),new Card(0, CardColor.GREEN, CardType.ONE), new Card(0, CardColor.RED, CardType.ONE)};
    public Stack<Card> uebersichtsCards = new Stack<>(); // Array des Stapel Uebesichtskarte... Nur die erste Karte wird angezeigt

    private int currentPlayer = 6; //javafx ordnet die rectangle in order an, deswegen ist der erste spieler 6

    // set the current player or client
    public void setPlayer(int[] player) {
        this.player = player;
    }

    /**
     * Methode zum Beenden des Zuges
     * @param event
     */
    public void zug_beenden(ActionEvent event) {


        if(currentPlayer== 0){
            currentPlayer = 5;
        } else{

            currentPlayer--;
            System.out.println(rechtecke[currentPlayer]);

        }
       for(int i = 0; i < this.player.length; i++){
           if(i == currentPlayer){
               rechtecke[i].setStroke(Paint.valueOf("#4fd423"));
           } else {
               rechtecke[i].setStroke(Paint.valueOf("black"));
           }
        }
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

    /**
     * Methode zum setzen der Karten auf der Uebersichtskarte
     */
    public void setImagekarte_Uebersichtskarte(){
        Card firstCard = uebersichtsCards.peek();
        imagekarte_Uebersichtskarte.setImage(KartetoImage(firstCard));
    }


    /**
     * Ablegen zur ÜbersichtsKarte
     * @param card
     */
    public void ablegenZuUebersichtsKarteStapel(Card card){
        uebersichtsCards.push(card);
    }

    /**
     * Methode zur Aufruf der erste Übersichtskarte
     * @return
     */
    public Card getUebersichtskarte(){
        return uebersichtsCards.pop();
    }

    /**
     * Methode zum setzen der Karten auf der Hand
     * @param cards
     */
    public void setImageHandkarte_CurrentPlayer(Card[] cards){
        /**for(int i = 0; i < handkarte_CurrentPlayer.length; i++){
            System.out.println(cards[i]);
            System.out.println(handkarte_CurrentPlayer.length);
            handkarte_CurrentPlayer[i].setImage(KartetoImage(cards[i])); // es wirft handkarte_currentPlayer[i] is null
        }**/
        // just the 3 first Cards
        imagekarte_H1.setImage(KartetoImage(cards[0]));
        imagekarte_H2.setImage(KartetoImage(cards[1]));
        imagekarte_H3.setImage(KartetoImage(cards[2]));
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
        if(dropdown_Zielstapel_StapelBewegen.getValue() == "Uebersichtskarte"){
            bewegenZumAblagestapel(event);
        }
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
        /**int index = handkarte_CurrentPlayer.length ;
        handkarte_CurrentPlayer[index].setImage(KartetoImage(uebersichtsKarte));**/
        if(uebersichtsCards.empty()){
            imagekarte_Uebersichtskarte.setVisible(false);
        }else{
            imagekarte_H4.setImage(KartetoImage(getUebersichtskarte()));
            if(uebersichtsCards.empty()){
                imagekarte_Uebersichtskarte.setVisible(false);
            }else{
                setImagekarte_Uebersichtskarte();
            }
        }
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
        System.out.println(dropdown_KarteWaehlen_karteSpielen.getValue());
        Card uebersichtscard = StringtoKarte(dropdown_KarteWaehlen_karteSpielen.getValue());
        ablegenZuUebersichtsKarteStapel(uebersichtscard);
        System.out.println("stack uebersichtskarte size" +" "+uebersichtsCards.size());
        setImagekarte_Uebersichtskarte();
    }

    /**
     * Methode zum Bewegen der Karte zum Ablagestapel des Gegners
     * @param event
     */
    public void bewegenZumAblagestapelGegner(ActionEvent event) {
    }



    /**
     * Methode zur Umwandlung von String zu Karte wenn user auf ein Option Clickt
     * @param wert
     * @return
     */
    public Card StringtoKarte(String wert) {
        Card card = switch (wert){
            case "karte1" -> new Card(0, CardColor.YELLOW, CardType.ONE);
            default -> new Card(0, CardColor.GREEN, CardType.ONE);
        };
        return card;
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
     * give the image Object of a given Card
     * @param card
     * @return
     */
    public Image KartetoImage(Card card) {
        String wert;
        String farbeNum;

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

        farbeNum = switch (card.getColor()) {
            case YELLOW -> "1";
            case RED -> "2";
            case GREEN -> "3";
            case BLUE -> "5";
            case PINK -> "4";
        };
        String ergebnis = "file:./src/Graphics/playingCards/" + "card" + wert + "Color" + farbeNum +"Repeat0.png"; // Umgang mit Repeat 1 and 2
        return new Image(ergebnis);
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

}
