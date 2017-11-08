package demo.com.roomdemo.database;

/**
 * Created by Amoeba on 11/3/2017.
 */

public class MessageInfo {
    private String text;
    private String time;
    public String toString(){
        return String.format("%s %s", this.text, this.time);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}