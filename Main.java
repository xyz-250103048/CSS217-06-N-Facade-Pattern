
public class Main {
    public static void main(String[] args) {

        StudentService students = new StudentService();
        BookCatalog books = new BookCatalog();
        LoanService loans = new LoanService();
        NotificationService notifications = new NotificationService();

        LibraryFacade library = new LibraryFacade(
                students, books, loans, notifications
        );

        run("S1 borrows B1",
                () -> System.out.println(library.borrowBook("S1", "B1")),
                books, notifications);

        run("S1 borrows B1 again",
                () -> System.out.println(library.borrowBook("S1", "B1")),
                books, notifications);

        run("S3 borrows B1",
                () -> System.out.println(library.borrowBook("S3", "B1")),
                books, notifications);

        run("S1 returns B1",
                () -> System.out.println(library.returnBook("S1", "B1")),
                books, notifications);

        run("S1 returns B1 again",
                () -> System.out.println(library.returnBook("S1", "B1")),
                books, notifications);
    }

    private static void run(
            String description,
            Runnable action,
            BookCatalog books,
            NotificationService notifications) {

        System.out.println("\nRequest: " + description);

        try {
            action.run();
        } catch (IllegalStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        System.out.println("B1 copies: "
                + books.getAvailableCopies("B1"));

        System.out.println("Notifications: "
                + notifications.getNotificationCount());
    }
}
