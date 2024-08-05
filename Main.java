public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        User user = new User("John Doe", "12345");

        Book book1 = new Book("Effective Java", "Joshua Bloch", 2018, 416, "Programming");
        Book book2 = new Book("The Catcher in the Rye", "J.D. Salinger", 1951, 277, "Fiction");
        Book book3 = new Book("Sapiens", "Yuval Noah Harari", 2015, 464, "History");
        Book book4 = new Book("Clean Code", "Robert C. Martin", 2008, 464, "Programming");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);

        System.out.println("Books by Yuval Noah Harari: " + library.findBooksByAuthor("Yuval Noah Harari"));
        System.out.println("Books with more than 300 pages: " + library.findBooksWithMoreThanNPages(300));
        System.out.println("Book with most pages: " + library.findBookWithMostPages().get());

        library.loanBook("Effective Java", user);
        System.out.println("Books on loan to John Doe: " + user.getBooksOnLoan());

        library.returnBook("Effective Java", user);
        System.out.println("Books on loan to John Doe after returning: " + user.getBooksOnLoan());

        library.printBookTitlesSorted();
    }
}
