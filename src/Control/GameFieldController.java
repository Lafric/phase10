package Control;

import Model.*;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

import Communication.Lobby;
import Communication.LobbyChatRefresh;

public class GameFieldController implements Initializable {
    //public Game game;

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
    public String lobby; 
    public Identity identity;
    public String gameName;
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
    public ImageView imgkarteSpielerClient_1;
    public ImageView imgkarteSpielerClient_2;
    public ImageView imgkarteSpielerClient_3;
    public ImageView imgkarteSpielerClient_4;
    public ImageView imgkarteSpielerClient_5;
    public ImageView imgkarteSpielerClient_6;
    public ImageView imgkarteSpielerClient_7;
    public ImageView imgkarteSpielerClient_8;
    public ImageView imgkarteSpielerClient_9;
    public ImageView imgkarteSpielerClient_10;

    //Für Dummy Logik
    private int[] player;
    public Rectangle[] rechtecke;
    public ImageView[] handkarte_CurrentPlayer;
    public ImageView[] stapelCards_CurrentPlayer;
    public CheckBox[] checkBoxes;
    public ImageView[] cards_opp1;
    public ImageView[] cards_opp2;
    public ImageView[] cards_opp3;
    public ImageView[] cards_opp4;
    public ImageView[] cards_opp5;
    public boolean[] selectedCards = new boolean[11];
    private Card[] cards = new Card[]{ new Card(0, CardColor.YELLOW, CardType.ONE),new Card(0, CardColor.GREEN, CardType.ONE), new Card(0, CardColor.RED, CardType.ONE)};
    public Stack<Card> uebersichtsCards = new Stack<>(); // Array des Stapel Uebesichtskarte... Nur die erste Karte wird angezeigt

    private int currentPlayer = 5; //javafx ordnet die rectangle in order an, deswegen ist der erste spieler 6
    private Game game;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.handkarte_CurrentPlayer = new ImageView[]{imagekarte_H1, imagekarte_H2, imagekarte_H3, imagekarte_H4, imagekarte_H5, imagekarte_H6, imagekarte_H7, imagekarte_H8, imagekarte_H9, imagekarte_H10, imagekarte_H11};
        this.rechtecke = new Rectangle[]{kasten_Gegenspieler1, kasten_Gegenspieler2, kasten_Gegenspieler3, kasten_Gegenspieler4, kasten_Gegenspieler5, kasten_Client};
        this.checkBoxes = new CheckBox[]{checkBox_1, checkBox_2, checkBox_3, checkBox_4, checkBox_5, checkBox_6, checkBox_7, checkBox_8, checkBox_9, checkBox_10, checkBox_11};
        this.stapelCards_CurrentPlayer = new ImageView[]{imgkarteSpielerClient_1, imgkarteSpielerClient_2, imgkarteSpielerClient_3, imgkarteSpielerClient_4, imgkarteSpielerClient_5, imgkarteSpielerClient_6, imgkarteSpielerClient_7, imgkarteSpielerClient_8, imgkarteSpielerClient_9, imgkarteSpielerClient_10};
        this.cards_opp1 = new ImageView[]{imgkarteSpieler1_1, imgkarteSpieler1_2, imgkarteSpieler1_3, imgkarteSpieler1_4, imgkarteSpieler1_5, imgkarteSpieler1_6, imgkarteSpieler1_7, imgkarteSpieler1_8, imgkarteSpieler1_9, imgkarteSpieler1_10};
        this.cards_opp2 = new ImageView[]{imgkarteSpieler2_1, imgkarteSpieler2_2, imgkarteSpieler2_3, imgkarteSpieler2_4, imgkarteSpieler2_5, imgkarteSpieler2_6, imgkarteSpieler2_7, imgkarteSpieler2_8, imgkarteSpieler2_9, imgkarteSpieler2_10};
        this.cards_opp3 = new ImageView[]{imgkarteSpieler3_1, imgkarteSpieler3_2, imgkarteSpieler3_3, imgkarteSpieler3_4, imgkarteSpieler3_5, imgkarteSpieler3_6, imgkarteSpieler3_7, imgkarteSpieler3_8, imgkarteSpieler3_9, imgkarteSpieler3_10};
        this.cards_opp4 = new ImageView[]{imgkarteSpieler4_1, imgkarteSpieler4_2, imgkarteSpieler4_3, imgkarteSpieler4_4, imgkarteSpieler4_5, imgkarteSpieler4_6, imgkarteSpieler4_7, imgkarteSpieler4_8, imgkarteSpieler4_9, imgkarteSpieler4_10};
        this.cards_opp5 = new ImageView[]{imgkarteSpieler5_1, imgkarteSpieler5_2, imgkarteSpieler5_3, imgkarteSpieler5_4, imgkarteSpieler5_5, imgkarteSpieler5_6, imgkarteSpieler5_7, imgkarteSpieler5_8, imgkarteSpieler5_9, imgkarteSpieler5_10};

        /**try {
            movetoOpenStack(new Card(0, CardColor.RED, CardType.TWO));
        } catch (RemoteException e) {
            e.printStackTrace();
        } // to delete**/
        System.out.println("Controller initialized!");

        //set the images to invisible by initialization
        for(int i = 0; i < stapelCards_CurrentPlayer.length; i++){
            stapelCards_CurrentPlayer[i].setVisible(false);
            cards_opp1[i].setVisible(false);
            cards_opp2[i].setVisible(false);
            cards_opp3[i].setVisible(false);
            cards_opp3[i].setVisible(false);
            cards_opp4[i].setVisible(false);
            cards_opp5[i].setVisible(false);
        }
        imagekarte_Uebersichtskarte.setVisible(false);
        for(int i = 0; i < handkarte_CurrentPlayer.length; i++){
            handkarte_CurrentPlayer[i].setVisible(false);
        }
        // set the values of dropdown Zielstapel
        dropdown_Zielstapel_StapelBewegen.getItems().addAll( "Mein Stapel","Mein Stapel Rule 1", "Mein Stapel Rule 2", "Uebersichtskarte", "Spieler 1 Rule 1","Spieler 1 Rule 2","Spieler 2 Rule 1", "Spieler 2 Rule 2", "Spieler 3 Rule 1","Spieler 3 Rule 2","Spieler 4 Rule 1","Spieler 4 Rule 2","Spieler 5 Rule 1","Spieler 5 Rule 2");

        //set the array of selected cards
        selected_cards();

    }

    public void selected_cards(){
        for( int i = 0; i < handkarte_CurrentPlayer.length; i++){
            int finalI = i;
            checkBoxes[i].setOnMouseClicked(event -> {
                if(selectedCards[finalI] == true){
                    selectedCards[finalI] = false;
                } else {
                    selectedCards[finalI] = true;
                }
                System.out.println(selectedCards[finalI]);
            });
        }
    }


    //Method to get LobbyName from main menue, needed for lobby chat
    public void give_lobby(String lobby){
        System.out.println("received " + lobby);
        this.lobby = lobby;
    }
    public void get_identity(Identity identity){
        this.identity = identity;
    }

    /**
     * Methode zum Beenden des Zuges
     * @param event
     */
    public void zug_beenden(ActionEvent event) {
       try{
           if(currentPlayer== 0){
               currentPlayer = 5;
           } else{

               currentPlayer--;
               System.out.println(rechtecke[currentPlayer]);

           }
           for(int i = 0; i < player.length; i++){
               if(i == currentPlayer){
                   rechtecke[i].setStroke(Paint.valueOf("#4fd423"));
               } else {
                   rechtecke[i].setStroke(Paint.valueOf("black"));
               }
           }
       } catch ( Exception e) {
           e.printStackTrace();
       }
    }




    /**
     * Methodt to set the image of the oppenStack

    public void setImagekarte_OpenStack() throws RemoteException {
        Card firstCard =cards[0]; //  game.getOpenStack().peek();
        imagekarte_Uebersichtskarte.setImage(CardtoImage(firstCard));
        imagekarte_Uebersichtskarte.setVisible(true);
    }*/

    /**
     * Method to play
     * Fälle :
     * Zum eigenen Ablagestapel für Phase Rule 1 oder 2
     * Zum Ablagestapel eines Gegners für Phase Rule 1 oder 2
     * Zum Uebersichtskartenstapel
     * @param event
     */
    public void playCard(ActionEvent event) throws RemoteException {
        if(dropdown_Zielstapel_StapelBewegen.getValue() == "Uebersichtskarte"){
            moveToOpenStack(event);
        } else if(dropdown_Zielstapel_StapelBewegen.getValue() == "Mein Stapel Rule 1"){
            for(int i = 0; i < selectedCards.length; i++){
                if(selectedCards[i] == true){
                    moveToCurrentPlayerBox(1);
                }
            }
        }else if (dropdown_Zielstapel_StapelBewegen.getValue() == "Mein Stapel Rule 2"){
            for(int i = 0; i < selectedCards.length; i++){
                if(selectedCards[i] == true){
                    moveToCurrentPlayerBox(2);
                }
            }
        }else if (dropdown_Zielstapel_StapelBewegen.getValue() == "Mein Stapel") {
            for (int i = 0; i < selectedCards.length; i++) {
                if (selectedCards[i] == true) {
                    moveToCurrentPlayerBox(0);
                }
            }
        }

    }

    /**
     * Methode to move a card to the current player box
     * @param Rulenum
     */
    private void moveToCurrentPlayerBox( int Rulenum) throws RemoteException {
        Player player = game.getAllPlayers()[game.getCurrentPlayer()];
        List<Card> handCards = player.getHandCards();
        if (Rulenum == 1) {
           for(int i = 0; i < selectedCards.length; i++){
               if(selectedCards[i] == true){

                   if(i>4){
                      stapelCards_CurrentPlayer[i-5].setImage(CardtoImage(cards[0]));
                      stapelCards_CurrentPlayer[i-5].setVisible(true);
                   }else {
                       stapelCards_CurrentPlayer[i].setImage(CardtoImage(cards[0]));
                       stapelCards_CurrentPlayer[i].setVisible(true);
                   }

               }
           }

        } else if (Rulenum == 2) {
            for(int i = 0; i < selectedCards.length; i++){
                if(selectedCards[i] == true){
                    if(i < 5) {
                        stapelCards_CurrentPlayer[i + 5].setImage(CardtoImage(cards[0]));
                        stapelCards_CurrentPlayer[i + 5].setVisible(true);
                    }else {
                        stapelCards_CurrentPlayer[i].setImage(CardtoImage(cards[0]));
                        stapelCards_CurrentPlayer[i].setVisible(true);
                    }

                    //d'enlever du handkarte,
                    //supprimer ton image
                    //set les gens a false
                }
            }

        } else if (Rulenum == 0) { // for phase mit keine Rules
            for(int i = 0; i < selectedCards.length; i++){
                if(selectedCards[i] == true){
                    stapelCards_CurrentPlayer[i].setImage(CardtoImage(cards[0]));
                    stapelCards_CurrentPlayer[i].setVisible(true);
                    //d'enlever du handkarte,
                    //supprimer ton image
                    //set les gens a false
                }
            }
        }
    }


    public Game get_game(){
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry("185.162.248.237", 1099);
            Game game = (Game) registry.lookup(this.gameName);
            return game;    
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Methode to draw a card from the stack
     */
     public void drawCard(ActionEvent event) throws RemoteException {
         System.out.println("drawCard");
         Player player = game.getAllPlayers()[game.getCurrentPlayer()];
         System.out.println(player.getHandCards());
         this.game.drawCard(player, true);
         //update Gui
         List<Card> handCards = player.getHandCards();
         int index = handCards.size() - 1;
         handkarte_CurrentPlayer[index].setImage(CardtoImage(handCards.get(index)));
         handkarte_CurrentPlayer[index].setVisible(true);
         System.out.println("HandsCards "+player.getHandCards());
    }

    /**
     * Method to draw a card from the open stack
     * @param event
     */
    public void drawOpenStackCard(ActionEvent event) throws RemoteException {
        try{
            Player player = game.getAllPlayers()[game.getCurrentPlayer()];
            this.game.drawCard(game.getAllPlayers()[game.getCurrentPlayer()], false);
            //update Gui
            List<Card> handCards = player.getHandCards();
            int index = handCards.size() - 1;
            //setImagekarte_OpenStack();

            Card firstCard =  game.getOpenStack().peek();
            imagekarte_Uebersichtskarte.setImage(CardtoImage(firstCard));
            imagekarte_Uebersichtskarte.setVisible(true);

            handkarte_CurrentPlayer[index].setImage(CardtoImage(handCards.get(index)));
            handkarte_CurrentPlayer[index].setVisible(true);
            System.out.println("HandsCards "+player.getHandCards());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method to ensure the validity of a phase
     * @param event
     */
    public void bestätigt_Phase(ActionEvent event) {
    }

    /**
     * Method to move a card to the overview stack
     * @param event
     */
    public void moveToOpenStack(ActionEvent event) throws RemoteException {
        System.out.println(game.getOpenStack().peek());
        Player player = game.getAllPlayers()[game.getCurrentPlayer()];
        List<Card> handCards = player.getHandCards();
        for(int i = 0; i < selectedCards.length; i++){
            if(selectedCards[i] == true){
                Card selectedCard = handCards.get(i);

                System.out.println("openStackCard "+ game.getOpenStack().peek());

                game.throwCard(player, selectedCard.getId(),player.getId());
                //update Gui
                Card firstCard =  game.getOpenStack().peek();
                imagekarte_Uebersichtskarte.setImage(CardtoImage(firstCard));
                imagekarte_Uebersichtskarte.setVisible(true);

                System.out.println("openStackCard new " + game.getOpenStack().peek());
                break;
            }
        }
    }



    /**
     * Method to move a card to the opponent stack
     * @param event
     */
    public void moveToOpponent(ActionEvent event) {
    }


    /**
     * give the image Object of a given Card
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
        String ergebnis = "file:./src/Graphics/playingCards/" + "card" + wert + "Color" + farbeNum +"Repeat0.png";
        System.out.println(ergebnis);
        return new Image(ergebnis);
    }


    public void start_Game(ActionEvent event) throws IOException,RemoteException{

        Registry registry = LocateRegistry.getRegistry("185.162.248.237", 1099);
        System.out.println("Connected to Server");
        try {
            gameName = "Game" + this.lobby.substring(5);
            Lobby lobby = (Lobby) registry.lookup(this.lobby);
            System.out.println("Lobby found");
            lobby.startGame();
            game = get_game();

            //update the GUI after the game has started

            // set the cards of the player
            for(int i = 0; i < game.getAllPlayers()[game.getCurrentPlayer()].getHandCards().size() ; i++){
                List<Card> handCards = game.getAllPlayers()[game.getCurrentPlayer()].getHandCards();
                System.out.println(handCards);
                handkarte_CurrentPlayer[i].setImage(CardtoImage(handCards.get(i)));
                handkarte_CurrentPlayer[i].setVisible(true);
            }

            //set the cards of the opponent and current player to invisible
            for(int i = 0; i < stapelCards_CurrentPlayer.length; i++){
                stapelCards_CurrentPlayer[i].setVisible(false);
                cards_opp1[i].setVisible(false);
                cards_opp2[i].setVisible(false);
                cards_opp3[i].setVisible(false);
                cards_opp3[i].setVisible(false);
                cards_opp4[i].setVisible(false);
                cards_opp5[i].setVisible(false);
            }
            //set the Openstack card
            imagekarte_Uebersichtskarte.setImage(CardtoImage(game.getOpenStack().get(0)));
            imagekarte_Uebersichtskarte.setVisible(true);
            System.out.println("Game started");
            System.out.println("openStackCard " + game.getOpenStack());
            System.out.println("hiidenstackfirstCard " + game.getHiddenStack().peek());
            System.out.println("playerName " + game.getAllPlayers()[game.getCurrentPlayer()].getName()+ " playerID " + game.getAllPlayers()[game.getCurrentPlayer()].getId());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        

    }




    /**
     * Methode zum Starten des Chats
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
        Runnable refresh = new LobbyChatRefresh(chatField,lobby);
        Thread chatrefresh = new Thread(refresh);
        chatrefresh.start();

        // TODO next scene öffnen
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Chatroom");

        stage.setOnCloseRequest(e -> {
            //TODO: disconnect user
        });

        stage.show();


    }


}
