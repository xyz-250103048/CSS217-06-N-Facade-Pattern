
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LoanService {
    private Map<String, Set<String>> loans;

    public LoanService() {
        loans = new HashMap<>();
    }

    public boolean hasLoan(String studentId, String bookId) {
        return loans.containsKey(studentId)
                && loans.get(studentId).contains(bookId);
    }

    public void addLoan(String studentId, String bookId) {
        loans.putIfAbsent(studentId, new HashSet<>());
        loans.get(studentId).add(bookId);
    }

    public void removeLoan(String studentId, String bookId) {
        loans.get(studentId).remove(bookId);

        if (loans.get(studentId).isEmpty()) {
            loans.remove(studentId);
        }
    }
}
