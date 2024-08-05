import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String name;
    private String libraryCardNumber;
    private final List<Book> booksOnLoan;
    private final Map<Book, LocalDate> loanDates;

    public User(String name, String libraryCardNumber) {
        this.name = name;
        this.libraryCardNumber = libraryCardNumber;
        this.booksOnLoan = new ArrayList<>();
        this.loanDates = new HashMap<>();
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLibraryCardNumber() {
        return libraryCardNumber;
    }

    public void setLibraryCardNumber(String libraryCardNumber) {
        this.libraryCardNumber = libraryCardNumber;
    }

    public List<Book> getBooksOnLoan() {
        return booksOnLoan;
    }

    public Map<Book, LocalDate> getLoanDates() {
        return loanDates;
    }

    public void addBookOnLoan(Book book) {
        booksOnLoan.add(book);
        loanDates.put(book, LocalDate.now());
    }

    public void returnBook(Book book) {
        booksOnLoan.remove(book);
        loanDates.remove(book);
    }

    public double calculateLateFees() {
        double lateFee = 0.0;
        LocalDate now = LocalDate.now();
        for (Map.Entry<Book, LocalDate> entry : loanDates.entrySet()) {
            LocalDate dueDate = entry.getValue().plusWeeks(2);
            if (now.isAfter(dueDate)) {
                long daysLate = java.time.temporal.ChronoUnit.DAYS.between(dueDate, now);
                lateFee += daysLate * 0.5; // Assume 0.5 currency units per day late
            }
        }
        return lateFee;
    }
}
