package filters;

import message.Message;

public class CompositeSpamFilter implements SpamFilter{
    @Override
    public boolean isSpam(Message message) {

        return  new SimpleSpamFilter().isSpam(message) ||
                new KeywordsSpamFilter().isSpam(message) ||
                new RepetitionSpamFilter().isSpam(message) ||
                new SenderSpamFilter().isSpam(message);
    }
}
