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
    public Label label_Gegenspieler1;
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
    public Button phase_bestaetigen;
    public Button Karte_spielen;
    public String lobby;
    public Identity identity;
    public String gameName;
    public Button bot_hinzufuegen;
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
    public Label opp1_cards2;
    public Label opp1_cards1;
    public Label opp2_cards2;
    public Label opp2_cards1;
    public Label opp3_cards2;
    public Label opp3_cards1;
    public Label opp4_cards2;
    public Label opp4_cards1;
    public Label opp5_cards2;
    public Label opp5_cards1;
    public Label client_cards1;
    public Label client_cards2;
    public Label label_SpielerName;
    private Card[] cards = new Card[] { new Card(0, CardColor.YELLOW, CardType.ONE),
            new Card(0, CardColor.GREEN, CardType.ONE), new Card(0, CardColor.RED, CardType.ONE) };
    private Game game;
    private Label[] playerName;
    private Label[] punkte;
    private Label[] handkarte_Nummer;
    private Label[][] stapelKarten;


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
        this.playerName = new Label[] { label_Client, label_Gegenspieler1, label_Gegenspieler2, label_Gegenspieler3,
                label_Gegenspieler4, label_Gegenspieler5 };
        this.punkte = new Label[] { label_ClientPunkte, label_Gegenspieler1Punkte, label_Gegenspieler2Punkte,
                label_Gegenspieler3Punkte, label_Gegenspieler4Punkte, label_Gegenspieler5Punkte };
        this.handkarte_Nummer = new Label[] { label_ClientHandkarten, label_Gegenspieler1Handkarten,
                label_Gegenspieler2Handkarten, label_Gegenspieler3Handkarten, label_Gegenspieler4Handkarten,
                label_Gegenspieler5Handkarten };
        this.stapelKarten = new Label[][] { {client_cards1, client_cards2}, { opp1_cards1, opp1_cards2 }, { opp2_cards1, opp2_cards2 },
                { opp3_cards1, opp3_cards2 }, { opp4_cards1, opp4_cards2 }, { opp5_cards1, opp5_cards2 },
                { client_cards1, client_cards2 } };

        //image not visible by initialization
        imagekarte_Uebersichtskarte.setVisible(false);
        for (int i = 0; i < handkarte_CurrentPlayer.length; i++) {
            handkarte_CurrentPlayer[i].setVisible(false);
        }
        // set the values of dropdown Zielstapel


        //set the labels as invisible by initialization
        for(int i = 0; i < punkte.length; i++){
            punkte[i].setVisible(false);
            handkarte_CurrentPlayer[i].setVisible(false);
        }

        for(int i = 0; i < playerBoxs.length; i++){
            playerBoxs[i].setVisible(false);
        }

        //set the array of selected cards
        selected_cards();


        System.out.println("Controller initialized!");

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
        } else if (dropdown_Zielstapel_StapelBewegen.getValue() == "Mein Stapel") {
            moveToCurrentPlayerBox();
        } else if (dropdown_Zielstapel_StapelBewegen.getValue() == "Spieler 1") {
            if(checkBox_Low.isSelected()){
                moveToOpponentBox( game.getAllPlayers()[1], true);
            } else {
                moveToOpponentBox(game.getAllPlayers()[1], false);
            }
        } else if (dropdown_Zielstapel_StapelBewegen.getValue() == "Spieler 2") {
            if(checkBox_Low.isSelected()){
                moveToOpponentBox(game.getAllPlayers()[2], true);
            } else {
                moveToOpponentBox(game.getAllPlayers()[2], false);
            }
        } else if (dropdown_Zielstapel_StapelBewegen.getValue() == "Spieler 3") {
            if(checkBox_Low.isSelected()){
                moveToOpponentBox(game.getAllPlayers()[3], true);
            } else {
                moveToOpponentBox( game.getAllPlayers()[3], false);
            }
        } else if (dropdown_Zielstapel_StapelBewegen.getValue() == "Spieler 4") {
            if(checkBox_Low.isSelected()){
                moveToOpponentBox( game.getAllPlayers()[4], true);
            } else {
                moveToOpponentBox( game.getAllPlayers()[4], false);
            }
        } else if (dropdown_Zielstapel_StapelBewegen.getValue() == "Spieler 5") {
            if(checkBox_Low.isSelected()){
                moveToOpponentBox(game.getAllPlayers()[5], true);
            } else {
                moveToOpponentBox(game.getAllPlayers()[5], false);
            }
        }

    }

    /**
     * Methode to move a card to the current player box
     *
     */
    private void moveToCurrentPlayerBox() throws RemoteException, InterruptedException {
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

    }

    public void moveToOpponentBox(Player moveToplayer, boolean low) throws RemoteException {
        System.out.println("move to opponent box");
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
                //renderHandCards();
                System.out.println("openStackCard new " + game.getOpenStack().peek());
                break;
            }
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
        //renderHandCards();
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
            //renderHandCards();
            if (game.getOpenStack().size() == 0) {
                imagekarte_Uebersichtskarte.setVisible(false);
            } else {
                Card firstCard = game.getOpenStack().peek();
                imagekarte_Uebersichtskarte.setImage(CardtoImage(firstCard));
                imagekarte_Uebersichtskarte.setVisible(true);
            }

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
        System.out.println("zug_beenden");
        System.out.println(game.getCurrentPlayer());
        game.goToNextPlayer();
        System.out.println("current player after" +  game.getCurrentPlayer() + " " + game.getAllPlayers()[game.getCurrentPlayer()].getId());
        // other Actions

        // if current player is bot, play bot turn
        if (game.getAllPlayers()[game.getCurrentPlayer()].getName().startsWith("Bot")) {
            System.out.println("BOT TURNNNNNNNN");
            game.playBotTurn();
        }
        // restructure to while-loop, end turn while bots are playing
    }

    public void addBot(ActionEvent event) throws RemoteException {
        get_lobby().addBot();
        //get_game().addBot();
    }


    /**
     * Method to ensure the validity of a phase
     * 
     * @param event
     */
    public void bestätigt_Phase(ActionEvent event) {
    }


    public void updateOpenStack() throws RemoteException {
        if (game.getOpenStack().size() == 0) {
            imagekarte_Uebersichtskarte.setVisible(false);
        } else {
            Card firstCard = game.getOpenStack().peek();
            imagekarte_Uebersichtskarte.setImage(CardtoImage(firstCard));
            imagekarte_Uebersichtskarte.setVisible(true);
        }
    }

    public void updatePunkte() throws RemoteException {
        for(int i = 0; i < game.getAllPlayers().length; i++){
            punkte[i].setText(Integer.toString(game.getAllPlayers()[i].getPoints()));
        }
    }

    public void updateHandKarteNummer() throws RemoteException {
        for(int i = 0; i < game.getAllPlayers().length; i++){
            handkarte_Nummer[i].setText(Integer.toString(game.getAllPlayers()[i].getHandCards().size()));
        }
    }

    public void updateSpieler() throws RemoteException {
        for(int i = 0; i < game.getAllPlayers().length; i++){
            playerName[i].setText(game.getAllPlayers()[i].getName());
            playerBoxs[i].setVisible(true);
            playerBoxs[i].setStroke(Paint.valueOf("black"));

            if(game.getAllPlayers()[i].getName().equals(identity.getUsername())){
                label_SpielerName.setText(game.getAllPlayers()[i].getName());
            }

        }
        playerBoxs[game.getCurrentPlayer()].setStroke(Paint.valueOf("#4fd423"));
    }

    public void updatePlayerCardNumber() throws RemoteException {
        for (int i = 0; i < game.getAllPlayers().length; i++) {
            System.out.println("1" + game.getAllPlayers()[i].getId());
            for (int j = 0; j < game.getFilings().size(); j++) {
                System.out.println("1" + game.getFilings().get(j).getPlayerId());
                if (game.getFilings().get(j).getPlayerId() == game.getAllPlayers()[i].getId()) {
                    System.out.println("if");
                    List<Filing> arr = new ArrayList<>();
                    arr.add(game.getFilings().get(j));
                    for(int k = 0; k < arr.size(); k++){
                        if(game.getFilings().get(j) instanceof Tuplet){
                            System.out.println("if2");
                            Tuplet t = (Tuplet) arr.get(k);
                            stapelKarten[i][k].setText(Integer.toString(t.getType().getNumber()));
                            System.out.println("number of cards in tuplet: " + t.getType());
                        }else {
                            System.out.println("else");
                            Street s = (Street) arr.get(k);
                            stapelKarten[i][k].setText(s.getStart().getNumber()  + "-" + s.getEnd().getNumber());
                            System.out.println("number of cards in Street: " + s.getStart().getNumber()  + "-" + s.getEnd().getNumber());
                        }
                    }
                }
            }
        }
    }




    // hilfe methode render the handCards images when changes occur

    private void updateRenderHandCards(Identity identity) throws RemoteException {
        System.out.println("renderHandCards");
        for(int i = 0; i < game.getAllPlayers().length; i++){
            if(game.getAllPlayers()[i].getName().equals(identity.getUsername())){
                List<Card> handCards = game.getAllPlayers()[i].getHandCards();
                System.out.println("handCards "+handCards.size() + "from" + game.getAllPlayers()[i].getName());
                for(int j = 0; j < handkarte_CurrentPlayer.length; j++){
                    handkarte_CurrentPlayer[j].setVisible(false);
                }
                for(int j = 0; j < handCards.size(); j++){
                    handkarte_CurrentPlayer[j].setImage(CardtoImage(handCards.get(j)));
                    handkarte_CurrentPlayer[j].setVisible(true);
                }
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
                @Override
                public void run() {

                    Platform.runLater(() -> {
                        try {
                            updateRenderHandCards(identity);
                            updateOpenStack();
                            updateSpieler();
                            updatePunkte();
                            updateHandKarteNummer();
                            updatePlayerCardNumber();
                            for(int i = 0; i < game.getAllPlayers().length; i++){
                                System.out.println(game.getAllPlayers().length);
                                System.out.println(game.getAllPlayers()[i].getId());
                                System.out.println(game.getAllPlayers()[i].getName());
                                identity.getUsername();
                            }

                            // if bot, make bot move
                            if (game.getAllPlayers()[game.getCurrentPlayer()].getName().startsWith("Bot")) {
                                System.out.println(game.getAllPlayers()[game.getCurrentPlayer()].getName());
                                System.out.println("BOT TURNNNNNNNN");
                                game.playBotTurn();
                            }

                            /**for (int j = 0; j < game.getFilings().size(); j++) {
                                if(game.getFilings().get(j) instanceof Tuplet){
                                    System.out.println("Tuplet");

                                    List<Filing> arr = new ArrayList<>();
                                    arr.add(game.getFilings().get(j));
                                    for(Filing f : arr){
                                        Tuplet t = (Tuplet) f;
                                        client_cards1.setText(Integer.toString(t.getType().getNumber()));
                                        System.out.println("number of cards in tuplet: " + t.getType());
                                    }
                                    
                                }else if(game.getFilings().get(j) instanceof Street){
                                    System.out.println("Sequence");



                                    List<Filing> arr = new ArrayList<>();
                                    arr.add(game.getFilings().get(j));
                                    for(Filing f : arr){
                                        Street s = (Street) f;
                                        client_cards1.setText(s.getStart().getNumber()  + "-" + s.getEnd().getNumber());
                                        System.out.println("number of cards in Street: " + s.getStart().getNumber()  + "-" + s.getEnd().getNumber());
                                    }
                                }else {
                                    System.out.println("on est mort");
                                    
                                }
                            }**/


                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    } );

                    System.out.println("Timer is running!");

                }
            }, 0, 2000);

        //update the GUI after the game has started

            Player[] players = game.getAllPlayers();
            ArrayList<String> playerNames = new ArrayList<String>();
            for (Player p : players) {
                playerNames.add(p.getName());
            }
            for (int i = playerNames.size(); i < 6; i++) {
                playerNames.add(" [LEER" + i + "]");
            }

            dropdown_Zielstapel_StapelBewegen.getItems().addAll("Uebersichtskarte", "Mein Stapel",
            "Spieler 1 " + playerNames.get(0),
            "Spieler 2 " + playerNames.get(1),
            "Spieler 3 " + playerNames.get(2),
            "Spieler 4 " + playerNames.get(3),
            "Spieler 5 " + playerNames.get(4));

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
