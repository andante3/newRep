package filters;
import message.Message;

interface SpamFilter {
    boolean isSpam(Message message);
}
