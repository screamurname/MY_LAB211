
public class Model {
    private String content;

    public Model() {
        this.content = "";
    }

    /**
     * Thiết lập nội dung cần phân tích
     *
     * @param content Chuỗi đầu vào
     * @throws IllegalArgumentException nếu content null
     */
    public void setContent(String content) {
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        this.content = content;
    }

    /**
     * Lấy nội dung hiện tại
     *
     * @return Nội dung
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Phân tích và đếm số lần xuất hiện của từng từ
     *
     * @return Mảng kết quả đếm từ
     */
    public WordResult[] analyzeWords() {
        // Kiểm tra nội dung rỗng
        if (content == null || content.trim().isEmpty()) {
            return new WordResult[0];
        }

        // Tách từ sử dụng StringTokenizer (giống code gốc)
        java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(content);
        int tokenCount = tokenizer.countTokens();

        if (tokenCount == 0) {
            return new WordResult[0];
        }

        // Mảng lưu trữ các từ
        String[] words = new String[tokenCount];
        for (int i = 0; i < tokenCount; i++) {
            words[i] = tokenizer.nextToken();
        }

        return countElements(words);
    }

    /**
     * Phân tích và đếm số lần xuất hiện của từng ký tự
     *
     * @return Mảng kết quả đếm ký tự
     */
    public CharResult[] analyzeCharacters() {
        // Kiểm tra nội dung rỗng
        if (content == null || content.isEmpty()) {
            return new CharResult[0];
        }

        // Lấy mảng ký tự, loại bỏ khoảng trắng (giống code gốc)
        String charsWithoutSpaces = content.replaceAll("\\s+", "");
        if (charsWithoutSpaces.isEmpty()) {
            return new CharResult[0];
        }

        char[] chars = charsWithoutSpaces.toCharArray();
        return countElements(chars);
    }

    /**
     * Phương thức tổng quát đếm phần tử trong mảng String
     *
     * @param elements Mảng các từ
     * @return Mảng kết quả đếm từ
     */
    private WordResult[] countElements(String[] elements) {
        if (elements.length == 0) {
            return new WordResult[0];
        }

        // Mảng lưu trữ phần tử duy nhất
        String[] uniqueElements = new String[elements.length];
        int[] counts = new int[elements.length];
        int uniqueCount = 0;

        for (String element : elements) {
            boolean found = false;

            // Tìm phần tử trong mảng duy nhất
            for (int i = 0; i < uniqueCount; i++) {
                if (uniqueElements[i].equals(element)) {
                    counts[i]++;
                    found = true;
                    break;
                }
            }

            // Nếu không tìm thấy, thêm mới
            if (!found) {
                uniqueElements[uniqueCount] = element;
                counts[uniqueCount] = 1;
                uniqueCount++;
            }
        }

        // Tạo mảng kết quả
        WordResult[] results = new WordResult[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            results[i] = new WordResult(uniqueElements[i], counts[i]);
        }

        return results;
    }

    /**
     * Phương thức tổng quát đếm phần tử trong mảng char
     *
     * @param elements Mảng các ký tự
     * @return Mảng kết quả đếm ký tự
     */
    private CharResult[] countElements(char[] elements) {
        if (elements.length == 0) {
            return new CharResult[0];
        }

        // Mảng lưu trữ ký tự duy nhất
        char[] uniqueElements = new char[elements.length];
        int[] counts = new int[elements.length];
        int uniqueCount = 0;

        for (char element : elements) {
            boolean found = false;

            // Tìm ký tự trong mảng duy nhất
            for (int i = 0; i < uniqueCount; i++) {
                if (uniqueElements[i] == element) {
                    counts[i]++;
                    found = true;
                    break;
                }
            }

            // Nếu không tìm thấy, thêm mới
            if (!found) {
                uniqueElements[uniqueCount] = element;
                counts[uniqueCount] = 1;
                uniqueCount++;
            }
        }

        // Tạo mảng kết quả
        CharResult[] results = new CharResult[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            results[i] = new CharResult(uniqueElements[i], counts[i]);
        }

        return results;
    }

    /**
     * Lớp kết quả cho từ
     */
    public static class WordResult {

        private final String word;
        private final int count;

        public WordResult(String word, int count) {
            this.word = word;
            this.count = count;
        }

        public String getWord() {
            return word;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return word + "=" + count;
        }
    }

    /**
     * Lớp kết quả cho ký tự
     */
    public static class CharResult {

        private final char character;
        private final int count;

        public CharResult(char character, int count) {
            this.character = character;
            this.count = count;
        }

        public char getCharacter() {
            return character;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return character + "=" + count;
        }
    }
}
