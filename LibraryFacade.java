
public class LibraryFacade {

    private final StudentService studentService;
    private final BookCatalog bookCatalog;
    private final LoanService loanService;
    private final NotificationService notificationService;

    public LibraryFacade(
            StudentService studentService,
            BookCatalog bookCatalog,
            LoanService loanService,
            NotificationService notificationService) {

        if (studentService == null || bookCatalog == null
                || loanService == null || notificationService == null) {
            throw new IllegalArgumentException("Dependencies cannot be null");
        }

        this.studentService = studentService;
        this.bookCatalog = bookCatalog;
        this.loanService = loanService;
        this.notificationService = notificationService;
    }

    private String[] validate(String studentId, String bookId) {
        if (studentId == null || studentId.isBlank()
                || bookId == null || bookId.isBlank()) {
            throw new IllegalArgumentException("IDs cannot be empty");
        }

        studentId = studentId.trim();
        bookId = bookId.trim();

        if (!studentService.studentExists(studentId)) {
            throw new IllegalArgumentException("Unknown student");
        }

        if (!bookCatalog.bookExists(bookId)) {
            throw new IllegalArgumentException("Unknown book");
        }

        return new String[]{studentId, bookId};
    }

    public String borrowBook(String studentId, String bookId) {

        String[] ids = validate(studentId, bookId);
        studentId = ids[0];
        bookId = ids[1];

        if (!studentService.isActive(studentId)) {
            throw new IllegalStateException("Student is inactive");
        }

        if (loanService.hasLoan(studentId, bookId)) {
            throw new IllegalStateException("Book already borrowed");
        }

        if (!bookCatalog.isAvailable(bookId)) {
            throw new IllegalStateException("No copies available");
        }

        bookCatalog.decreaseCopies(bookId);
        loanService.addLoan(studentId, bookId);

        String message = "BORROWED " + studentId + " " + bookId;
        notificationService.sendNotification(message);

        return message;
    }

    public String returnBook(String studentId, String bookId) {

        String[] ids = validate(studentId, bookId);
        studentId = ids[0];
        bookId = ids[1];

        if (!loanService.hasLoan(studentId, bookId)) {
            throw new IllegalStateException("No existing loan");
        }

        loanService.removeLoan(studentId, bookId);
        bookCatalog.increaseCopies(bookId);

        String message = "RETURNED " + studentId + " " + bookId;
        notificationService.sendNotification(message);

        return message;
    }
}

