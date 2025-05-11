package filters;
import message.Message;

public class SenderSpamFilter implements SpamFilter{
    @Override
    public boolean isSpam(Message message) {
        if (message.getReceiver().getSpamFilter("sender") == null)
            return false;
        return message.getSender().getUser().equals(message.getReceiver().getSpamFilter("sender"));
    }
}
