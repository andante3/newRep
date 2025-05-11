package filters;
import message.Message;

public class SimpleSpamFilter implements SpamFilter{

    @Override
    public boolean isSpam(Message message) {
        if (message.getReceiver().getFilters("simple") == null)
            return false;
        String allText = message.getHeader() + " " + message.getText();
        String[] words = allText.split("[^A-Za-z0-9А-Яа-яЁё]");
        for (String word: words)
            if (word.equals("spam"))
                return true;
        return false;
    }
}
