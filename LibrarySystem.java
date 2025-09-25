import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

/**
 * A basic library management system that allows users to add, borrow, and return books.
 * This program demonstrates the use of control structures including loops, conditional
 * statements, and exception handling as outlined in Eck (2022), Chapter 3.
 * 
 * @author Student
 * @version 1.0
 */
public class LibrarySystem {
    
    // HashMap to store book information: Key = Book Title, Value = Book object
    private static Map<String, Book> library = new HashMap<>();
    private static Scanner input = new Scanner(System.in);
    
    /**
     * Inner class to represent a Book with title, author, and quantity
     */
    static class Book {
        private String title;
        private String author;
        private int quantity;
        
        public Book(String title, String author, int quantity) {
            this.title = title;
            this.author = author;
            this.quantity = quantity;
        }
        
        // Getters and setters
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
        
        @Override
        public String toString() {
            return String.format("Title: %s, Author: %s, Quantity: %d", 
                               title, author, quantity);
        }
    }
    
    /**
     * Main method that controls the program flow using a while loop
     * as described in Eck (2022), Section 3.3
     */
    public static void main(String[] args) {
        System.out.println("=== Welcome to the Library Management System ===");
        
        boolean continueProgram = true;
        
        // Main program loop using while statement (Eck, 2022, Section 3.3)
        while (continueProgram) {
            displayMenu();
            
            try {
                int choice = getMenuChoice();
                
                // Using nested control structures (if-else statements)
                // as recommended in Eck (2022), Chapter 3
                switch (choice) {
                    case 1:
                        addBooks();
                        break;
                    case 2:
                        borrowBooks();
                        break;
                    case 3:
                        returnBooks();
                        break;
                    case 4:
                        continueProgram = false;
                        System.out.println("Thank you for using the Library System. Goodbye!");
                        break;
                    default:
                        System.out.println("Error: Invalid option. Please select 1-4.");
                }
                
            } catch (Exception e) {
                // Exception handling as covered in Eck (2022), Section 3.7
                System.out.println("Error: Invalid input. Please enter a valid number.");
                input.nextLine(); // Clear the invalid input
            }
            
            if (continueProgram) {
                System.out.println(); // Add spacing between operations
            }
        }
        
        input.close();
    }
    
    /**
     * Displays the main menu options to the user
     */
    private static void displayMenu() {
        System.out.println("\n--- Library Management System ---");
        System.out.println("1. Add Books");
        System.out.println("2. Borrow Books");
        System.out.println("3. Return Books");
        System.out.println("4. Exit");
        System.out.print("Please select an option (1-4): ");
    }
    
    /**
     * Gets and validates menu choice from user input
     * Uses exception handling as described in Eck (2022), Section 3.7
     */
    private static int getMenuChoice() throws Exception {
        try {
            return input.nextInt();
        } catch (Exception e) {
            input.nextLine(); // Clear invalid input
            throw new Exception("Invalid input format");
        }
    }
    
    /**
     * Handles adding books to the library system
     * Implements nested control structures and input validation
     */
    private static void addBooks() {
        System.out.println("\n=== Add Books ===");
        
        try {
            input.nextLine(); // Clear the buffer
            
            // Get book information from user
            System.out.print("Enter book title: ");
            String title = input.nextLine().trim();
            
            // Input validation using if statement (Eck, 2022, Chapter 3)
            if (title.isEmpty()) {
                System.out.println("Error: Book title cannot be empty.");
                return;
            }
            
            System.out.print("Enter author name: ");
            String author = input.nextLine().trim();
            
            if (author.isEmpty()) {
                System.out.println("Error: Author name cannot be empty.");
                return;
            }
            
            System.out.print("Enter quantity to add: ");
            int quantity = input.nextInt();
            
            // Validate quantity using nested if statements
            if (quantity <= 0) {
                System.out.println("Error: Quantity must be a positive number.");
                return;
            }
            
            // Check if book already exists using if-else control structure
            if (library.containsKey(title.toLowerCase())) {
                // Book exists - update quantity
                Book existingBook = library.get(title.toLowerCase());
                int newQuantity = existingBook.getQuantity() + quantity;
                existingBook.setQuantity(newQuantity);
                
                System.out.printf("Success: Updated '%s' quantity. New total: %d books%n",
                                title, newQuantity);
            } else {
                // New book - add to library
                Book newBook = new Book(title, author, quantity);
                library.put(title.toLowerCase(), newBook);
                
                System.out.printf("Success: Added new book '%s' by %s (%d copies)%n",
                                title, author, quantity);
            }
            
        } catch (Exception e) {
            // Exception handling as per Eck (2022), Section 3.7
            System.out.println("Error: Invalid input. Please enter valid information.");
            input.nextLine(); // Clear invalid input
        }
    }
    
    /**
     * Handles borrowing books from the library
     * Uses control structures for validation and error handling
     */
    private static void borrowBooks() {
        System.out.println("\n=== Borrow Books ===");
        
        // Check if library is empty using if statement
        if (library.isEmpty()) {
            System.out.println("Error: No books available in the library.");
            return;
        }
        
        try {
            input.nextLine(); // Clear the buffer
            
            System.out.print("Enter book title to borrow: ");
            String title = input.nextLine().trim();
            
            if (title.isEmpty()) {
                System.out.println("Error: Book title cannot be empty.");
                return;
            }
            
            System.out.print("Enter number of books to borrow: ");
            int borrowQuantity = input.nextInt();
            
            if (borrowQuantity <= 0) {
                System.out.println("Error: Number of books must be positive.");
                return;
            }
            
            // Check if book exists using nested if-else structures
            if (library.containsKey(title.toLowerCase())) {
                Book book = library.get(title.toLowerCase());
                
                // Check availability using nested if statement
                if (book.getQuantity() >= borrowQuantity) {
                    // Books available - process borrowing
                    int newQuantity = book.getQuantity() - borrowQuantity;
                    book.setQuantity(newQuantity);
                    
                    System.out.printf("Success: You borrowed %d copy/copies of '%s'%n",
                                    borrowQuantity, book.getTitle());
                    System.out.printf("Remaining copies in library: %d%n", newQuantity);
                    
                    // Remove book if quantity becomes 0
                    if (newQuantity == 0) {
                        library.remove(title.toLowerCase());
                        System.out.println("Note: This book is now out of stock.");
                    }
                    
                } else {
                    // Insufficient quantity - display error
                    System.out.printf("Error: Only %d copy/copies of '%s' available. " +
                                    "Cannot borrow %d copies.%n",
                                    book.getQuantity(), book.getTitle(), borrowQuantity);
                }
                
            } else {
                System.out.printf("Error: Book '%s' not found in library.%n", title);
            }
            
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter valid information.");
            input.nextLine(); // Clear invalid input
        }
    }
    
    /**
     * Handles returning books to the library
     * Implements control structures for validation and book management
     */
    private static void returnBooks() {
        System.out.println("\n=== Return Books ===");
        
        try {
            input.nextLine(); // Clear the buffer
            
            System.out.print("Enter book title to return: ");
            String title = input.nextLine().trim();
            
            if (title.isEmpty()) {
                System.out.println("Error: Book title cannot be empty.");
                return;
            }
            
            System.out.print("Enter number of books to return: ");
            int returnQuantity = input.nextInt();
            
            if (returnQuantity <= 0) {
                System.out.println("Error: Number of books must be positive.");
                return;
            }
            
            // For this implementation, we'll assume any book can be returned
            // In a real system, you'd track borrowed books per user
            
            // Check if book exists in library using if-else structure
            if (library.containsKey(title.toLowerCase())) {
                // Book exists - add to existing quantity
                Book book = library.get(title.toLowerCase());
                int newQuantity = book.getQuantity() + returnQuantity;
                book.setQuantity(newQuantity);
                
                System.out.printf("Success: Returned %d copy/copies of '%s'%n",
                                returnQuantity, book.getTitle());
                System.out.printf("Total copies now in library: %d%n", newQuantity);
                
            } else {
                // Book doesn't exist - ask for author to add as new entry
                System.out.printf("Book '%s' not found in library system.%n", title);
                System.out.print("Enter author name to add this book to the system: ");
                input.nextLine(); // Clear buffer
                String author = input.nextLine().trim();
                
                if (!author.isEmpty()) {
                    Book newBook = new Book(title, author, returnQuantity);
                    library.put(title.toLowerCase(), newBook);
                    
                    System.out.printf("Success: Added and returned '%s' by %s (%d copies)%n",
                                    title, author, returnQuantity);
                } else {
                    System.out.println("Error: Author name cannot be empty. Return cancelled.");
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter valid information.");
            input.nextLine(); // Clear invalid input
        }
    }
}