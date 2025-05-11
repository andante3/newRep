package filters;
import message.Message;

public class SenderSpamFilter implements SpamFilter{
    @Override
    public boolean isSpam(Message message) {
        if (message.getReceiver().getFilters("sender") == null)
            return false;
        return message.getSender().getUser().equals(message.getReceiver().getFilters("sender"));
    }
}
