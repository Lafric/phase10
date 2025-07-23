# Eine Java implementierung der Spielkarte phase10
# Setup
Zum Ausführen der client.jar wird eine aktuelle java version und javafx benötigt.
Ist beides vorhanden kann sie wie folgt ausgeführt werden :
`java --enable-preview -jar --module-path "<absolute path to javafx sdk lib on local machine>" --add-modules javafx.controls,javafx.fxml client.jar`




# Registrieren & Anmelden
- Falls noch kein Account erstellt
	- Registieren mit mindestens 4 stelligem Passwort
- Falls Account vorhanden
	- mit Accountname und Passwort anmelden

# Main Menu
Alle Buttons erklärt :
* "Raum erstellen" : erstellt einen leere Raum dem Spieler beitreten können.
*  "Beitreten" : tritt dem, zuvor mit Linksklick aus der Liste gewählten, Raum bei.
* "⟳" : refreshed die Liste aller Räume.
*  "Abmelden" : meldet den User ab und schließt das Main Menu.
* " Bestenliste" : Öffnet ein neues Fenster mit einer Tabelle der aktuellen Bestenliste.
* " Absenden" : sendet den links daneben eingegebenen Text in den Globalen Chat.
* " Account löschen" wirft den User in ein Fenster in dem er nach erneuter Eingabe des Passworts, seinen Account löschen kann.

# Spielfeld
### Erklärung einer einzelnen Spieler-Übersicht :
* Anzahl Handkarten : die Anzahl der Karten die der entsprechende Spieler auf der Hand hat.
* Phase Rule 1 : Feld in dem ein Set of .. oder eine Straße zum vervollständigen der Phase gelegt werden kann. -> der Text untendrunter ist eine einzelne Zahl falls ein Set einer Zahl angelegt wurde und eine Range (z.B 4-6) für eine Straße. 
* Phase Rule 2 : analog zu Phase Rule 1.

### Erklärung der Interaktionsmöglichkeiten im Spiel
* "Zielstapel wählen ▽" :  Wählt aus, an welche Person eine oder mehrere Karten angelegt werden.
* "Auf Uebersichtskarte legen" : Legt eine ausgewählte Karte auf den offenen Stapel.
* "Neue Phase legen" : Legt ausgewählte Karten also ein Set oder eine Straße hin. Vorher muss man sich allerdings selbst in "Zielstapel wählen" auswählen.
* "Karte in Phase ergänzen" : Legt ausgewählte Karten an ein bereits existierendes Set oder eine bereits existierende Straße an.
* "Zug beenden" : Beendet den aktuellen Zug und übergibt an nächsten Spieler.
* "Uebersichtkarte ziehen" : Zieht die angezeigte Karte vom offenen Stapel auf die Hand.
* "Karte Ziehen" : Zieht eine Karte vom verdeckten Stapel auf die Hand.
* "Chat" : öffnet Raum-eigenes Chatfenster.
* "Spiel starten" : Startet das Spiel und muss von jedem zum ersten refresh ausgeführt werden.
* Tick-box mit "Karte oben anlegen" : entscheidet ob, falls man an eine Straße anlegen möchte, man oben oder unten anlegen möchte.
* "Bot hinzufügen" : füllt einen leeren Spieler Slot mit einem Bot auf.
* "Spiel Regeln" : öffnet ein Fenster mit einer kurzen Erklärung der Spielregeln und den einzelnen Phasen.

# Raum Chat
Raum eigenes Chatfenster, hier werden Nachrichten durch drücken von Enter abgesendet und sind für alle Menschen im Raum sichtbar.



