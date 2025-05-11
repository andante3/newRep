package filters;
import message.Message;

public class KeywordsSpamFilter implements SpamFilter {

    @Override
    public boolean isSpam(Message message) {
        if (message.getReceiver().getFilters("keywords") == null)
            return false;
        String[] keys = message.getReceiver().getFilters("keywords").split(" ");
        String allText = (message.getHeader() + " " + message.getText()).toLowerCase();
        String[] tokens = allText.split("[^A-Za-z0-9А-Яа-яЁё]");
        for (String key : keys)
            for (String token : tokens)
                if (token.equals(key))
                    return true;
        return false;
    }
}
