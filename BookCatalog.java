
import java.util.HashMap;
import java.util.Map;

public class BookCatalog {
    private Map<String, Integer> books;

    public BookCatalog() {
        books = new HashMap<>();
        books.put("B1", 2);
        books.put("B2", 0);
    }

    public boolean bookExists(String bookId) {
        return books.containsKey(bookId);
    }

    public boolean isAvailable(String bookId) {
        return books.get(bookId) > 0;
    }

    public int getAvailableCopies(String bookId) {
        return books.get(bookId);
    }

    public void decreaseCopies(String bookId) {
        if (books.get(bookId) <= 0) {
            throw new IllegalStateException("No copies available");
        }
        books.put(bookId, books.get(bookId) - 1);
    }

    public void increaseCopies(String bookId) {
        books.put(bookId, books.get(bookId) + 1);
    }
}
