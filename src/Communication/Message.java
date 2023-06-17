package Communication;

public class Message implements java.io.Serializable {
    public String sender;
    public String date;
    public String content;

    public Message(String sender, String date, String content) {
        this.sender = sender;
        this.date = date;
        this.content = content;
    }
}
