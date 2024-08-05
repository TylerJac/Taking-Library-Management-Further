import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {
    private final List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
        List<User> users = new ArrayList<>();
    }

    // Add and remove books

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }

    // Find books

    public List<Book> findBooksByYear(int year) {
        return books.stream()
                .filter(book -> book.getPublicationYear() == year)
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public Optional<Book> findBookWithMostPages() {
        return books.stream()
                .max(Comparator.comparingInt(Book::getPages));
    }

    public List<Book> findBooksWithMoreThanNPages(int n) {
        return books.stream()
                .filter(book -> book.getPages() > n)
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByCategory(String category) {
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Loan and return books

    public void loanBook(String title, User user) {
        Optional<Book> bookToLoan = books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title) && !book.isOnLoan())
                .findFirst();

        if (bookToLoan.isPresent()) {
            Book book = bookToLoan.get();
            book.setOnLoan(true);
            user.addBookOnLoan(book);
        }
    }

    public void returnBook(String title, User user) {
        Optional<Book> bookToReturn = books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title) && book.isOnLoan())
                .findFirst();

        if (bookToReturn.isPresent()) {
            Book book = bookToReturn.get();
            book.setOnLoan(false);
            user.returnBook(book);
        }
    }

    // Print book titles sorted alphabetically

    public void printBookTitlesSorted() {
        books.stream()
                .map(Book::getTitle)
                .sorted()
                .forEach(System.out::println);
    }
}
