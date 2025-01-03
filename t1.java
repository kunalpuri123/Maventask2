import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private List<String> books;
    private List<String> borrowedBooks;

    public LibrarySystem() {
        books = new ArrayList<>();
        borrowedBooks = new ArrayList<>();
        initializeLibrary();
    }

    private void initializeLibrary() {
        books.add("Moby Dick");
        books.add("To Kill a Mockingbird");
        books.add("The Great Gatsby");
        books.add("1984");
        books.add("Pride and Prejudice");
    }

    public void displayAvailableBooks() {
        System.out.println("Available books:");
        for (String book : books) {
            System.out.println(book);
        }
    }

    public void borrowBook(String bookName) {
        if (!books.contains(bookName)) {
            System.out.println("Error: Book not found in the library.");
            return;
        }
        if (borrowedBooks.contains(bookName)) {
            System.out.println("Error: This book is already borrowed.");
            return;
        }
        books.remove(bookName);
        borrowedBooks.add(bookName);
        System.out.println("You have successfully borrowed: " + bookName);
    }

    public void returnBook(String bookName) {
        if (!borrowedBooks.contains(bookName)) {
            System.out.println("Error: This book was not borrowed.");
            return;
        }
        borrowedBooks.remove(bookName);
        books.add(bookName);
        System.out.println("You have successfully returned: " + bookName);
    }

    public void displayBorrowedBooks() {
        System.out.println("Borrowed books:");
        for (String book : borrowedBooks) {
            System.out.println(book);
        }
    }

    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();
        
        library.displayAvailableBooks();
        
        library.borrowBook("Moby Dick");
        library.borrowBook("The Great Gatsby");
        library.borrowBook("1984");
        
        library.displayAvailableBooks();
        library.displayBorrowedBooks();
        
        library.returnBook("Moby Dick");
        library.returnBook("To Kill a Mockingbird"); // Bug: This book was never borrowed
        
        library.displayAvailableBooks();
        library.displayBorrowedBooks();
    }
}

