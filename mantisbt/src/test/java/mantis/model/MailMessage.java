package mantis.model;

/**
 * Created by User on 21.02.2018.
 */
public class MailMessage {
    public   String text;
    public   String to;

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}
