package estech.vmg.chatter.Bubbles;

public class Message {
    public String text,date,user;

    public String getUser() {
        return user;
    }
    public String getDate() {
        return date;
    }
    public String getText() {
        return text;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Message(){//Dummy constructor for testing
        user="Unknown User";
        text="Unknown Message";
        date="Unknown Date";
    }
    public Message(String user,String text,String date){
        this.user=user;
        this.text=text;
        this.date=date;
    }
}
