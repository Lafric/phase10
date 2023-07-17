package Control;

import Model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

import Communication.Lobby;
import Communication.LobbyChatRefresh;

public class GameFieldController implements Initializable {
    // public Game game;

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
    public Label label_ClientHandkarten;
    public Label label_ClientPunkte;
    public Button launchchat;
    public Button karte_ziehen;
    public Button zug_beenden;
    public Button phase_bestätigen;
    public Button Karte_spielen;
    public String lobby;
    public Identity identity;
    public String gameName;
    public Button bot_hinzufügen;
    public Pane pane_kartenSpieler;
    public CheckBox checkBox_1;
    public CheckBox checkBox_2;
    public CheckBox checkBox_3;
    public CheckBox checkBox_4;
    public CheckBox checkBox_5;
    public CheckBox checkBox_6;
    public CheckBox checkBox_7;
    public CheckBox checkBox_8;
    public CheckBox checkBox_9;
    public CheckBox checkBox_10;
    public CheckBox checkBox_11;


    // Für Dummy Logik
    public Rectangle[] playerBoxs;
    public ImageView[] handkarte_CurrentPlayer;
    public CheckBox[] checkBoxes;
    public boolean[] selectedCards = new boolean[11];
    public Button draw_openStack;
    public CheckBox checkBox_Low;
    private Card[] cards = new Card[] { new Card(0, CardColor.YELLOW, CardType.ONE),
            new Card(0, CardColor.GREEN, CardType.ONE), new Card(0, CardColor.RED, CardType.ONE) };
    private Game game;

    
    /** 
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.handkarte_CurrentPlayer = new ImageView[] { imagekarte_H1, imagekarte_H2, imagekarte_H3, imagekarte_H4,
                imagekarte_H5, imagekarte_H6, imagekarte_H7, imagekarte_H8, imagekarte_H9, imagekarte_H10,
                imagekarte_H11 };
        this.playerBoxs = new Rectangle[] { kasten_Client, kasten_Gegenspieler1, kasten_Gegenspieler2,
                kasten_Gegenspieler3, kasten_Gegenspieler4, kasten_Gegenspieler5 };
        this.checkBoxes = new CheckBox[] { checkBox_1, checkBox_2, checkBox_3, checkBox_4, checkBox_5, checkBox_6,
                checkBox_7, checkBox_8, checkBox_9, checkBox_10, checkBox_11 };

        /**
         * try {
         * movetoOpenStack(new Card(0, CardColor.RED, CardType.TWO));
         * } catch (RemoteException e) {
         * e.printStackTrace();
         * } // to delete
         **/
        System.out.println("Controller initialized!");


        imagekarte_Uebersichtskarte.setVisible(false);
        for (int i = 0; i < handkarte_CurrentPlayer.length; i++) {
            handkarte_CurrentPlayer[i].setVisible(false);
        }
        // set the values of dropdown Zielstapel
        dropdown_Zielstapel_StapelBewegen.getItems().addAll("Mein Stapel", "Mein Stapel Rule 1", "Mein Stapel Rule 2",
                "Uebersichtskarte", "Spieler 1 Rule 1", "Spieler 1 Rule 2", "Spieler 2 Rule 1", "Spieler 2 Rule 2",
                "Spieler 3 Rule 1", "Spieler 3 Rule 2", "Spieler 4 Rule 1", "Spieler 4 Rule 2", "Spieler 5 Rule 1",
                "Spieler 5 Rule 2");

        //set the labels as invisible by initialization
        // label_Gegenspieler1Name.setVisible(false);
        label_Gegenspieler1Handkarten.setVisible(false);
        label_Gegenspieler1Punkte.setVisible(false);
        // label_Gegenspieler2.setVisible(false);
        label_Gegenspieler2Handkarten.setVisible(false);
        label_Gegenspieler2Punkte.setVisible(false);
        // label_Gegenspieler3.setVisible(false);
        label_Gegenspieler3Handkarten.setVisible(false);
        label_Gegenspieler3Punkte.setVisible(false);
        // label_Gegenspieler4.setVisible(false);
        label_Gegenspieler4Handkarten.setVisible(false);
        label_Gegenspieler4Punkte.setVisible(false);
        // label_Gegenspieler5.setVisible(false);
        label_Gegenspieler5Handkarten.setVisible(false);
        label_Gegenspieler5Punkte.setVisible(false);
        // label_Client.setVisible(false);
        label_ClientHandkarten.setVisible(false);
        label_ClientPunkte.setVisible(false);

        for(int i = 0; i < playerBoxs.length; i++){
            playerBoxs[i].setVisible(false);
        }

        //set the array of selected cards
        selected_cards();




        System.out.println("Controller initialized!");

    }

    public void playerBox(int number_of_players){
        for(int i = 0; i < number_of_players; i++){
            playerBoxs[i].setVisible(true);
        }

        if(number_of_players == 1){
            label_Client.setVisible(true);
            label_ClientHandkarten.setVisible(true);
            label_ClientPunkte.setVisible(true);
        }else if(number_of_players == 2){
            label_Client.setVisible(true);
            label_ClientHandkarten.setVisible(true);
            label_ClientPunkte.setVisible(true);
            label_Gegenspieler1Name.setVisible(true);
            label_Gegenspieler1Handkarten.setVisible(true);
            label_Gegenspieler1Punkte.setVisible(true);
        }else if(number_of_players == 3){
            label_Client.setVisible(true);
            label_ClientHandkarten.setVisible(true);
            label_ClientPunkte.setVisible(true);
            label_Gegenspieler1Name.setVisible(true);
            label_Gegenspieler1Handkarten.setVisible(true);
            label_Gegenspieler1Punkte.setVisible(true);
            label_Gegenspieler2.setVisible(true);
            label_Gegenspieler2Handkarten.setVisible(true);
            label_Gegenspieler2Punkte.setVisible(true);
        }else if(number_of_players == 4){
            label_Client.setVisible(true);
            label_ClientHandkarten.setVisible(true);
            label_ClientPunkte.setVisible(true);
            label_Gegenspieler1Name.setVisible(true);
            label_Gegenspieler1Handkarten.setVisible(true);
            label_Gegenspieler1Punkte.setVisible(true);
            label_Gegenspieler2.setVisible(true);
            label_Gegenspieler2Handkarten.setVisible(true);
            label_Gegenspieler2Punkte.setVisible(true);
            label_Gegenspieler3.setVisible(true);
            label_Gegenspieler3Handkarten.setVisible(true);
            label_Gegenspieler3Punkte.setVisible(true);
        }else if(number_of_players == 5){
            label_Client.setVisible(true);
            label_ClientHandkarten.setVisible(true);
            label_ClientPunkte.setVisible(true);
            label_Gegenspieler1Name.setVisible(true);
            label_Gegenspieler1Handkarten.setVisible(true);
            label_Gegenspieler1Punkte.setVisible(true);
            label_Gegenspieler2.setVisible(true);
            label_Gegenspieler2Handkarten.setVisible(true);
            label_Gegenspieler2Punkte.setVisible(true);
            label_Gegenspieler3.setVisible(true);
            label_Gegenspieler3Handkarten.setVisible(true);
            label_Gegenspieler3Punkte.setVisible(true);
            label_Gegenspieler4.setVisible(true);
            label_Gegenspieler4Handkarten.setVisible(true);
            label_Gegenspieler4Punkte.setVisible(true);
        }else if (number_of_players == 6 ){
            label_Client.setVisible(true);
            label_ClientHandkarten.setVisible(true);
            label_ClientPunkte.setVisible(true);
            label_Gegenspieler1Name.setVisible(true);
            label_Gegenspieler1Handkarten.setVisible(true);
            label_Gegenspieler1Punkte.setVisible(true);
            label_Gegenspieler2.setVisible(true);
            label_Gegenspieler2Handkarten.setVisible(true);
            label_Gegenspieler2Punkte.setVisible(true);
            label_Gegenspieler3.setVisible(true);
            label_Gegenspieler3Handkarten.setVisible(true);
            label_Gegenspieler3Punkte.setVisible(true);
            label_Gegenspieler4.setVisible(true);
            label_Gegenspieler4Handkarten.setVisible(true);
            label_Gegenspieler4Punkte.setVisible(true);
            label_Gegenspieler5.setVisible(true);
            label_Gegenspieler5Handkarten.setVisible(true);
            label_Gegenspieler5Punkte.setVisible(true);
        }
    }

    public void selected_cards() {
        for (int i = 0; i < handkarte_CurrentPlayer.length; i++) {
            int finalI = i;
            checkBoxes[i].setOnMouseClicked(event -> {
                if (selectedCards[finalI] == true) {
                    selectedCards[finalI] = false;
                } else {
                    selectedCards[finalI] = true;
                }
                System.out.println(selectedCards[finalI]);
            });
        }
    }

    // Method to get LobbyName from main menue, needed for lobby chat
    public void give_lobby(String lobby) {
        System.out.println("received " + lobby);
        this.lobby = lobby;
    }

    public void get_identity(Identity identity) {
        this.identity = identity;
    }

    public Game get_game() {
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry("185.162.248.237", 1099);
            Game game = (Game) registry.lookup(this.gameName);
            System.out.println("game updated");
            return game;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    public Lobby get_lobby() {
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry("185.162.248.237", 1099);
            Lobby lobby = (Lobby) registry.lookup(this.lobby);
            return lobby;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to play
     * Fälle :
     * Zum eigenen Ablagestapel für Phase Rule 1 oder 2
     * Zum Ablagestapel eines Gegners für Phase Rule 1 oder 2
     * Zum Uebersichtskartenstapel
     * 
     * @param event
     */
    public void playCard(ActionEvent event) throws RemoteException, InterruptedException {
        if(dropdown_Zielstapel_StapelBewegen.getValue() == "Uebersichtskarte"){
            moveToOpenStack(event);
        } else if (dropdown_Zielstapel_StapelBewegen.getValue() == "Mein Stapel Rule 1") {
            moveToCurrentPlayerBox(1);
        } else if (dropdown_Zielstapel_StapelBewegen.getValue() == "Mein Stapel Rule 2") {
            moveToCurrentPlayerBox(2);
        } else if (dropdown_Zielstapel_StapelBewegen.getValue() == "Mein Stapel") {
            moveToCurrentPlayerBox(0);
        } else if (dropdown_Zielstapel_StapelBewegen.getValue() == "Spieler 1 Rule 1" || dropdown_Zielstapel_StapelBewegen.getValue() == "Spieler 1 Rule 2") {
            if(checkBox_Low.isSelected()){
                moveToOpponentBox(1, game.getAllPlayers()[1], true);
            } else {
                moveToOpponentBox(2, game.getAllPlayers()[1], false);
            }
        } else if (dropdown_Zielstapel_StapelBewegen.getValue() == "Spieler 2 Rule 1" || dropdown_Zielstapel_StapelBewegen.getValue() == "Spieler 2 Rule 2") {
            if(checkBox_Low.isSelected()){
                moveToOpponentBox(1, game.getAllPlayers()[2], true);
            } else {
                moveToOpponentBox(2, game.getAllPlayers()[2], false);
            }
        } else if (dropdown_Zielstapel_StapelBewegen.getValue() == "Spieler 3 Rule 1" || dropdown_Zielstapel_StapelBewegen.getValue() == "Spieler 3 Rule 2") {
            if(checkBox_Low.isSelected()){
                moveToOpponentBox(1,  game.getAllPlayers()[3], true);
            } else {
                moveToOpponentBox(2, game.getAllPlayers()[3], false);
            }
        } else if (dropdown_Zielstapel_StapelBewegen.getValue() == "Spieler 4 Rule 1" || dropdown_Zielstapel_StapelBewegen.getValue() == "Spieler 4 Rule 2") {
            if(checkBox_Low.isSelected()){
                moveToOpponentBox(1, game.getAllPlayers()[4], true);
            } else {
                moveToOpponentBox(2,  game.getAllPlayers()[4], false);
            }
        } else if (dropdown_Zielstapel_StapelBewegen.getValue() == "Spieler 5 Rule 1" || dropdown_Zielstapel_StapelBewegen.getValue() == "Spieler 5 Rule 2") {
            if(checkBox_Low.isSelected()){
                moveToOpponentBox(1, game.getAllPlayers()[5], true);
            } else {
                moveToOpponentBox(2,  game.getAllPlayers()[5], false);
            }
        }

    }

    /**
     * Methode to move a card to the current player box
     * 
     * @param Rulenum
     *            represents the phaserule number
     */
    private void moveToCurrentPlayerBox(int Rulenum) throws RemoteException, InterruptedException {
        Player player = game.getAllPlayers()[game.getCurrentPlayer()];
        List<Card> handCards = player.getHandCards();
        List<Integer> cardIDs = new ArrayList<>();
        for (int i = 0; i < selectedCards.length; i++) {
            if (selectedCards[i] == true) {
                cardIDs.add(handCards.get(i).getId());
            }
        }
        for (int i = 0; i < cardIDs.size(); i++) {
            System.out.println("arrayId" + cardIDs.get(i));
        }

        int[] arrayCardIDs = cardIDs.stream().mapToInt(i -> i).toArray(); // convert list to array
        System.out.println("arrayId" + arrayCardIDs.length);
        System.out.println("before laycard " + game.getAllPlayers()[game.getCurrentPlayer()].getHandCards());
        System.out.println("current player before" +  player.getName() + " " + player.getId());

        game.layCards(game.getAllPlayers()[game.getCurrentPlayer()],arrayCardIDs);
        System.out.println("after laycard " + get_game().getAllPlayers()[get_game().getCurrentPlayer()].getHandCards());
        System.out.println("current player before" +  game.getAllPlayers()[game.getCurrentPlayer()].getName() + " " + game.getAllPlayers()[game.getCurrentPlayer()].getId());
        System.out.println("fillngs" +  game.getFilings().size());

        for (int j = 0; j < game.getFilings().size(); j++) {

                System.out.println("Filing " + game.getFilings().get(j));
                System.out.println("Filing " + game.getFilings().get(j).getPlayerId());
                System.out.println("Filing " + game.getAllPlayers()[game.getCurrentPlayer()].getId());
                System.out.println("check" + checkBox_Low.isSelected());

        }



        if (Rulenum == 1) {
            for (int i = 0; i < selectedCards.length; i++) {
                if (selectedCards[i] == true) {

                    if (i > 4) {

                    } else {

                    }
                    renderHandCards();
                }
            }

        } else if (Rulenum == 2) {
            for (int i = 0; i < selectedCards.length; i++) {
                if (selectedCards[i] == true) {
                    if (i < 5) {

                    } else {

                    }
                    renderHandCards();
                }
            }

        } else if (Rulenum == 0) { // for phase mit keine Rules
            for (int i = 0; i < selectedCards.length; i++) {
                if (selectedCards[i] == true) {

                    renderHandCards();
                }
            }
        }
    }

    public void moveToOpponentBox(int Rulenum, Player moveToplayer, boolean low) throws RemoteException {

        Player player = game.getAllPlayers()[game.getCurrentPlayer()];
        List<Card> handCards = player.getHandCards();

        for (int i = 0; i < selectedCards.length; i++) {
            if (selectedCards[i] == true) {
                for (int j = 0; j < game.getFilings().size(); j++) {
                    if (game.getFilings().get(j).getPlayerId() == moveToplayer.getId()) {
                        game.playCard(player, handCards.get(i).getId(), game.getFilings().get(j).getId(), low);
                    }
                }
            }
            break;
        }
    }

    /**
     * Methode to draw a card from the stack
     */
    public void drawCard(ActionEvent event) throws RemoteException {

        System.out.println("drawCard");

        Player player = game.getAllPlayers()[game.getCurrentPlayer()];
        System.out.println(player.getHandCards());
        this.game.drawCard(player, true);
        // update Gui
        renderHandCards();
        System.out.println("HandsCards " + player.getHandCards());
    }

    /**
     * Method to draw a card from the open stack
     * 
     * @param event
     */
    public void drawOpenStackCard(ActionEvent event) throws RemoteException {
        try {
            Player player = game.getAllPlayers()[game.getCurrentPlayer()];
            this.game.drawCard(game.getAllPlayers()[game.getCurrentPlayer()], false);
            // update GUI
            renderHandCards();
            if (game.getOpenStack().size() == 0) {
                imagekarte_Uebersichtskarte.setVisible(false);
            } else {
                Card firstCard = game.getOpenStack().peek();
                imagekarte_Uebersichtskarte.setImage(CardtoImage(firstCard));
                imagekarte_Uebersichtskarte.setVisible(true);
            }
            ;

            System.out.println("HandsCards " + player.getHandCards());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to end the turn of the current player with all actions that results
     * from it
     * 
     * @param event
     */
    public void zug_beenden(ActionEvent event) throws RemoteException {
        // game.goToNextPlayer();
        playerBoxs[game.getCurrentPlayer()].setStroke(Paint.valueOf("#4fd42"));
        // other Actions

        // if current player is bot, play bot turn
        if (game.getAllPlayers()[game.getCurrentPlayer()].isBot()) {
            game.playBotTurn();
        }
        // restructure to while-loop, end turn while bots are playing
    }

    public void addBot(ActionEvent event) throws RemoteException {
        get_lobby().addBot();
        get_game().addBot();
    }

    /**
     * Method to ensure the validity of a phase
     * 
     * @param event
     */
    public void bestätigt_Phase(ActionEvent event) {
    }

    /**
     * Method to move a card to the overview stack
     * 
     * @param event
     */
    public void moveToOpenStack(ActionEvent event) throws RemoteException {
        Player player = game.getAllPlayers()[game.getCurrentPlayer()];
        List<Card> handCards = player.getHandCards();
        for (int i = 0; i < selectedCards.length; i++) {
            if (selectedCards[i] == true) {
                Card selectedCard = handCards.get(i);
                game.throwCard(player, selectedCard.getId(), player.getId());
                // update Gui
                Card firstCard = game.getOpenStack().peek();
                imagekarte_Uebersichtskarte.setImage(CardtoImage(firstCard));
                imagekarte_Uebersichtskarte.setVisible(true);
                renderHandCards();
                System.out.println("openStackCard new " + game.getOpenStack().peek());
                break;
            }
        }
    }


    // hilfe methode render the handCards images when changes occur

    private void renderHandCards() throws RemoteException {
        List<Card> handCards = game.getAllPlayers()[game.getCurrentPlayer()].getHandCards();
        System.out.println("handCards "+handCards.size());
        for(int i = 0; i < handkarte_CurrentPlayer.length; i++){
            handkarte_CurrentPlayer[i].setVisible(false);
        }
        for(int i = 0; i < handCards.size(); i++){
            handkarte_CurrentPlayer[i].setImage(CardtoImage(handCards.get(i)));
            handkarte_CurrentPlayer[i].setVisible(true);
        }
        for (int j = 0; j < game.getFilings().size(); j++) {
            if (game.getFilings().get(j).getPlayerId() == game.getAllPlayers()[game.getCurrentPlayer()].getId()) {
                System.out.println("Filing " + game.getFilings().get(j));
            }
        }
    }

    /**
     * give the image Object of a given Card
     * 
     * @param card
     * @return
     */
    public Image CardtoImage(Card card) {
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
            case -1 -> "SKIP";
            case -2 -> "JOKER";
            default -> "Fehler";
        };

        farbeNum = switch (card.getColor()) {
            case YELLOW -> "1";
            case RED -> "2";
            case GREEN -> "3";
            case PINK -> "4";
            case BLUE -> "5";
        };
        String ergebnis = "file:./src/Graphics/playingCards/" + "card" + wert + "Color" + farbeNum + "Repeat0.png";
        return new Image(ergebnis);
    }

    public void start_Game(ActionEvent event) throws IOException, RemoteException {

        Registry registry = LocateRegistry.getRegistry("185.162.248.237", 1099);
        System.out.println("Connected to Server");
        try {
            gameName = "Game" + this.lobby.substring(5);
            Lobby lobby = (Lobby) registry.lookup(this.lobby);
            System.out.println("Lobby found");
            lobby.startGame();
            game = get_game();

            //to update automatically
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                int a = 1;
                @Override
                public void run() {





                    Platform.runLater(() -> {
                        try {
                            int number_of_players = game.getAllPlayers().length;
                            if(number_of_players == 1){
                                label_ClientHandkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()].getHandCards().size()));
                                label_ClientPunkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()].getPoints()));
                            }else if(number_of_players == 2){
                                label_ClientHandkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()].getHandCards().size()));
                                label_ClientPunkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()].getPoints()));
                                label_Gegenspieler1Handkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+1].getHandCards().size()));
                                label_Gegenspieler1Punkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+1].getPoints()));
                            }else if(number_of_players == 3){
                                label_ClientHandkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()].getHandCards().size()));
                                label_ClientPunkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()].getPoints()));
                                label_Gegenspieler1Handkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+1].getHandCards().size()));
                                label_Gegenspieler1Punkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+1].getPoints()));
                                label_Gegenspieler2Handkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+2].getHandCards().size()));
                                label_Gegenspieler2Punkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+2].getPoints()));
                            }else if(number_of_players == 4){
                                label_ClientHandkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()].getHandCards().size()));
                                label_ClientPunkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()].getPoints()));
                                label_Gegenspieler1Handkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+1].getHandCards().size()));
                                label_Gegenspieler1Punkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+1].getPoints()));
                                label_Gegenspieler2Handkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+2].getHandCards().size()));
                                label_Gegenspieler2Punkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+2].getPoints()));
                                label_Gegenspieler3Handkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+3].getHandCards().size()));
                                label_Gegenspieler3Punkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+3].getPoints()));
                            }else if(number_of_players == 5){
                                label_ClientHandkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()].getHandCards().size()));
                                label_ClientPunkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()].getPoints()));
                                label_Gegenspieler1Handkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+1].getHandCards().size()));
                                label_Gegenspieler1Punkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+1].getPoints()));
                                label_Gegenspieler2Handkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+2].getHandCards().size()));
                                label_Gegenspieler2Punkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+2].getPoints()));
                                label_Gegenspieler3Handkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+3].getHandCards().size()));
                                label_Gegenspieler3Punkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+3].getPoints()));
                                label_Gegenspieler4Handkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+4].getPoints()));
                                label_Gegenspieler4Punkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+4].getPoints()));
                            }else if(number_of_players == 6){
                                label_ClientHandkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()].getHandCards().size()));
                                label_ClientPunkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()].getPoints()));
                                label_Gegenspieler1Handkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+1].getHandCards().size()));
                                label_Gegenspieler1Punkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+1].getPoints()));
                                label_Gegenspieler2Handkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+2].getHandCards().size()));
                                label_Gegenspieler2Punkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+2].getPoints()));
                                label_Gegenspieler3Handkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+3].getHandCards().size()));
                                label_Gegenspieler3Punkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+3].getPoints()));
                                label_Gegenspieler4Handkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+4].getPoints()));
                                label_Gegenspieler4Punkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+4].getPoints()));
                                label_Gegenspieler5Handkarten.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+5].getPoints()));
                                label_Gegenspieler5Punkte.setText(Integer.toString(game.getAllPlayers()[game.getCurrentPlayer()+5].getPoints()));
                            }
                            renderHandCards();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    } );

                    System.out.println("Timer is running!");
                }
            }, 0, 1000);

        //update the GUI after the game has started

            // set the cards of the player
            for (int i = 0; i < game.getAllPlayers()[game.getCurrentPlayer()].getHandCards().size(); i++) {
                List<Card> handCards = game.getAllPlayers()[game.getCurrentPlayer()].getHandCards();
                System.out.println(handCards);
                handkarte_CurrentPlayer[i].setImage(CardtoImage(handCards.get(i)));
                handkarte_CurrentPlayer[i].setVisible(true);
            }

            // set the Openstack card
            imagekarte_Uebersichtskarte.setImage(CardtoImage(game.getOpenStack().get(0)));
            imagekarte_Uebersichtskarte.setVisible(true);
            // set the number of Box for player
            /**
             * for(int i = 0; i < playerBoxs.length; i++){
             * playerBoxs[i].setVisible(false);
             * }
             * for(int i = 0; i < game.getAllPlayers().length ; i++){
             * playerBoxs[i].setVisible(true);
             * }
             **/
            // set the first (current) player Box to blue
            playerBoxs[game.getCurrentPlayer()].setStroke(Paint.valueOf("#4fd423"));

            //set the name of the player
            label_Client.setText(game.getAllPlayers()[game.getCurrentPlayer()].getName());
            label_Gegenspieler1Name.setText(game.getAllPlayers()[game.getCurrentPlayer()+1].getName());
            label_Gegenspieler2.setText(game.getAllPlayers()[game.getCurrentPlayer()+2].getName());
            label_Gegenspieler3.setText(game.getAllPlayers()[game.getCurrentPlayer()+3].getName());
            label_Gegenspieler4.setText(game.getAllPlayers()[game.getCurrentPlayer()+4].getName());
            label_Gegenspieler5.setText(game.getAllPlayers()[game.getCurrentPlayer()+5].getName());
            //set the Openstack card
            imagekarte_Uebersichtskarte.setImage(CardtoImage(game.getOpenStack().get(0)));
            imagekarte_Uebersichtskarte.setVisible(true);
            //set the number of Box for player
            playerBox(game.getAllPlayers().length);



            System.out.println("Game started");
            System.out.println("openStackCard " + game.getOpenStack());
            System.out.println("hiidenstackfirstCard " + game.getHiddenStack().peek());
            System.out.println("playerName " + game.getAllPlayers()[game.getCurrentPlayer()].getName() + " playerID "
                    + game.getAllPlayers()[game.getCurrentPlayer()].getId());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Methode zum Starten des Chats
     * 
     * @param event
     * @throws IOException
     */
    public void LaunchChat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/chatRaumInGameField.fxml"));
        Parent root = loader.load();
        ChatRaumController controller = loader.getController();
        controller.get_lobby(lobby);
        controller.get_identity(identity);
        TextArea chatField = controller.getChat_ausgabe();
        Runnable refresh = new LobbyChatRefresh(chatField, lobby);
        Thread chatrefresh = new Thread(refresh);
        chatrefresh.start();

        // TODO next scene öffnen
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Chatroom");

        stage.setOnCloseRequest(e -> {
            // TODO: disconnect user
        });

        stage.show();

    }

}
