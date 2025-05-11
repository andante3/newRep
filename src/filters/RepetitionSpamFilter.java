package filters;

import message.Message;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RepetitionSpamFilter implements SpamFilter {
    @Override
    public boolean isSpam(Message message) {
        if (message.getReceiver().getFilters("repetition") == null)
            return false;
        String allText = message.getText().toLowerCase();
        String[] words = allText.split("[^A-Za-z0-9А-Яа-яЁё]");
        if (words.length == 0 || allText.trim().isEmpty())
            return false;
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            if (wordsMap.containsKey(word))
                wordsMap.put(word, wordsMap.get(word) + 1);
            else
                wordsMap.put(word, 1);
        }
        int repetitionNumber = Integer.parseInt(message.getReceiver().getFilters("repetition"));
        return Collections.max(wordsMap.values()) > repetitionNumber;
    }
}
