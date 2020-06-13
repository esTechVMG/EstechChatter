package estech.vmg.chatter.Bubbles;

public class Message {
    public String text,date;

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }
    public Message(){//Dummy constructor for testing
        text="Unknown Message";
        date="Unknown Date";
    }
    public Message(String text,String date){
        this.text=text;
        this.date=date;
    }
}
