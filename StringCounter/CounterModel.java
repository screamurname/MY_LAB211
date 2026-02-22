
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class CounterModel {

    private Map<Character, Integer> charCounter = new HashMap<>();
    private Map<String, Integer> wordCounter = new HashMap<>();

    public void analyze(String content) {
        // Reset lại map mỗi khi phân tích nội dung mới
        charCounter.clear();
        wordCounter.clear();

        // Phân tích ký tự
        for (char ch : content.toCharArray()) {
            if (Character.isSpaceChar(ch)) continue;
            charCounter.put(ch, charCounter.getOrDefault(ch, 0) + 1);
        }

        // Phân tích từ
        StringTokenizer tokenizer = new StringTokenizer(content);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            wordCounter.put(token, wordCounter.getOrDefault(token, 0) + 1);
        }
    }

    public Map<Character, Integer> getCharCounter() {
        return charCounter;
    }

    public Map<String, Integer> getWordCounter() {
        return wordCounter;
    }
}