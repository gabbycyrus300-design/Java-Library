/*
 * Student Record Management System
 * 
 * This comprehensive system provides administrators with efficient tools for managing student records.
 * The system includes functionalities for adding, updating, and viewing student information with
 * robust error handling and input validation.
 * 
 * Features:
 * - Add new students with unique ID validation
 * - Update existing student information
 * - View individual student details
 * - Display all students
 * - Search students by ID or name
 * - Comprehensive error handling
 * - User-friendly menu interface
 * 
 * @author Student Management System
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Student class represents individual student records with private instance variables
 * and appropriate getter/setter methods following encapsulation principles.
 */
class Student {
    // Private instance variables for student data
    private String name;
    private String studentId;
    private int age;
    private String grade;
    
    /**
     * Constructor to create a new Student object
     * @param name Student's full name
     * @param studentId Unique student identifier
     * @param age Student's age
     * @param grade Student's current grade/class
     */
    public Student(String name, String studentId, int age, String grade) {
        this.name = name;
        this.studentId = studentId;
        this.age = age;
        this.grade = grade;
    }
    
    // Getter methods
    public String getName() { return name; }
    public String getStudentId() { return studentId; }
    public int getAge() { return age; }
    public String getGrade() { return grade; }
    
    // Setter methods for updating student information
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGrade(String grade) { this.grade = grade; }
    
    /**
     * Returns a formatted string representation of the student
     * @return Formatted student information
     */
    @Override
    public String toString() {
        return String.format("ID: %-10s | Name: %-20s | Age: %-3d | Grade: %s", 
                           studentId, name, age, grade);
    }
}

/**
 * StudentManagement class handles all student record operations using static methods
 * and variables. This class serves as the core business logic layer.
 */
class StudentManagement {
    // Private static variables for data storage
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static int totalStudents = 0;
    
    /**
     * Adds a new student to the system with validation
     * @param name Student's name
     * @param studentId Unique student ID
     * @param age Student's age
     * @param grade Student's grade
     * @return true if student added successfully, false if ID already exists
     */
    public static boolean addStudent(String name, String studentId, int age, String grade) {
        // Check if student ID already exists
        if (findStudentById(studentId) != null) {
            return false; // Student ID already exists
        }
        
        // Validate input parameters
        if (name == null || name.trim().isEmpty() || 
            studentId == null || studentId.trim().isEmpty() || 
            grade == null || grade.trim().isEmpty()) {
            return false; // Invalid input
        }
        
        if (age < 5 || age > 100) {
            return false; // Invalid age range
        }
        
        // Create and add new student
        Student newStudent = new Student(name.trim(), studentId.trim(), age, grade.trim());
        studentList.add(newStudent);
        totalStudents++;
        return true;
    }
    
    /**
     * Updates existing student information
     * @param studentId ID of student to update
     * @param name New name (null to keep current)
     * @param age New age (-1 to keep current)
     * @param grade New grade (null to keep current)
     * @return true if updated successfully, false if student not found
     */
    public static boolean updateStudent(String studentId, String name, int age, String grade) {
        Student student = findStudentById(studentId);
        if (student == null) {
            return false; // Student not found
        }
        
        // Update only non-null/valid values
        if (name != null && !name.trim().isEmpty()) {
            student.setName(name.trim());
        }
        if (age > 0 && age >= 5 && age <= 100) {
            student.setAge(age);
        }
        if (grade != null && !grade.trim().isEmpty()) {
            student.setGrade(grade.trim());
        }
        
        return true;
    }
    
    /**
     * Retrieves student by ID
     * @param studentId Student ID to search for
     * @return Student object if found, null otherwise
     */
    public static Student getStudentById(String studentId) {
        return findStudentById(studentId);
    }
    
    /**
     * Private helper method to find student by ID
     * @param studentId Student ID to search for
     * @return Student object if found, null otherwise
     */
    private static Student findStudentById(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return null;
        }
        
        for (Student student : studentList) {
            if (student.getStudentId().equalsIgnoreCase(studentId.trim())) {
                return student;
            }
        }
        return null;
    }
    
    /**
     * Searches for students by name (partial match)
     * @param name Name to search for
     * @return ArrayList of matching students
     */
    public static ArrayList<Student> searchStudentsByName(String name) {
        ArrayList<Student> results = new ArrayList<>();
        if (name == null || name.trim().isEmpty()) {
            return results;
        }
        
        String searchName = name.trim().toLowerCase();
        for (Student student : studentList) {
            if (student.getName().toLowerCase().contains(searchName)) {
                results.add(student);
            }
        }
        return results;
    }
    
    /**
     * Gets all students in the system
     * @return ArrayList of all students
     */
    public static ArrayList<Student> getAllStudents() {
        return new ArrayList<>(studentList); // Return copy to maintain encapsulation
    }
    
    /**
     * Gets total number of students
     * @return Total student count
     */
    public static int getTotalStudents() {
        return totalStudents;
    }
    
    /**
     * Removes a student from the system
     * @param studentId ID of student to remove
     * @return true if removed successfully, false if not found
     */
    public static boolean removeStudent(String studentId) {
        Student student = findStudentById(studentId);
        if (student != null) {
            studentList.remove(student);
            totalStudents--;
            return true;
        }
        return false;
    }
}

/**
 * Main application class that provides the administrator interface
 * This class handles user interaction and menu operations
 */
public class StudentRecordManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Main method - entry point of the application
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("    STUDENT RECORD MANAGEMENT SYSTEM v1.0");
        System.out.println("=================================================");
        System.out.println("Welcome, Administrator!");
        System.out.println();
        
        // Initialize with sample data for demonstration
        initializeSampleData();
        
        // Main application loop
        boolean running = true;
        while (running) {
            try {
                displayMenu();
                int choice = getIntInput("Enter your choice (1-7): ");
                
                switch (choice) {
                    case 1:
                        addNewStudent();
                        break;
                    case 2:
                        updateStudentInfo();
                        break;
                    case 3:
                        viewStudentDetails();
                        break;
                    case 4:
                        viewAllStudents();
                        break;
                    case 5:
                        searchStudents();
                        break;
                    case 6:
                        removeStudentRecord();
                        break;
                    case 7:
                        running = false;
                        System.out.println("\nThank you for using Student Record Management System!");
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("❌ Invalid choice. Please select a number between 1-7.");
                }
                
                if (running) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
                
            } catch (Exception e) {
                System.out.println("❌ An unexpected error occurred: " + e.getMessage());
                System.out.println("Please try again.");
                scanner.nextLine(); // Clear the scanner buffer
            }
        }
        
        scanner.close();
    }
    
    /**
     * Displays the main menu options
     */
    private static void displayMenu() {
        System.out.println("\n" + repeatChar('=', 50));
        System.out.println("              ADMINISTRATOR MENU");
        System.out.println(repeatChar('=', 50));
        System.out.println("1. 📝 Add New Student");
        System.out.println("2. ✏️  Update Student Information");
        System.out.println("3. 👁️  View Student Details");
        System.out.println("4. 📋 View All Students");
        System.out.println("5. 🔍 Search Students");
        System.out.println("6. 🗑️  Remove Student Record");
        System.out.println("7. 🚪 Exit System");
        System.out.println("=".repeat(50));
        System.out.printf("Total Students in System: %d\n", StudentManagement.getTotalStudents());
        System.out.println();
    }
    
    /**
     * Handles adding a new student
     */
    private static void addNewStudent() {
        System.out.println("\n--- ADD NEW STUDENT ---");
        
        try {
            String name = getStringInput("Enter student name: ");
            String studentId = getStringInput("Enter student ID: ");
            int age = getIntInput("Enter student age: ");
            String grade = getStringInput("Enter student grade/class: ");
            
            if (StudentManagement.addStudent(name, studentId, age, grade)) {
                System.out.println("✅ Student added successfully!");
                System.out.println("Student Details:");
                Student addedStudent = StudentManagement.getStudentById(studentId);
                if (addedStudent != null) {
                    System.out.println("   " + addedStudent);
                }
            } else {
                System.out.println("❌ Failed to add student. Possible reasons:");
                System.out.println("   • Student ID already exists");
                System.out.println("   • Invalid input data (empty fields or age not between 5-100)");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error adding student: " + e.getMessage());
        }
    }
    
    /**
     * Handles updating student information
     */
    private static void updateStudentInfo() {
        System.out.println("\n--- UPDATE STUDENT INFORMATION ---");
        
        if (StudentManagement.getTotalStudents() == 0) {
            System.out.println("❌ No students in the system to update.");
            return;
        }
        
        try {
            String studentId = getStringInput("Enter student ID to update: ");
            Student existingStudent = StudentManagement.getStudentById(studentId);
            
            if (existingStudent == null) {
                System.out.println("❌ Student with ID '" + studentId + "' not found.");
                return;
            }
            
            System.out.println("Current student information:");
            System.out.println("   " + existingStudent);
            System.out.println("\nEnter new information (press Enter to keep current value):");
            
            // Get updated information
            String newName = getOptionalStringInput("New name [" + existingStudent.getName() + "]: ");
            String ageInput = getOptionalStringInput("New age [" + existingStudent.getAge() + "]: ");
            String newGrade = getOptionalStringInput("New grade [" + existingStudent.getGrade() + "]: ");
            
            int newAge = -1; // -1 indicates no change
            if (!ageInput.isEmpty()) {
                try {
                    newAge = Integer.parseInt(ageInput);
                    if (newAge < 5 || newAge > 100) {
                        System.out.println("⚠️ Age must be between 5-100. Keeping current age.");
                        newAge = -1;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Invalid age format. Keeping current age.");
                    newAge = -1;
                }
            }
            
            if (StudentManagement.updateStudent(studentId, newName, newAge, newGrade)) {
                System.out.println("✅ Student information updated successfully!");
                Student updatedStudent = StudentManagement.getStudentById(studentId);
                System.out.println("Updated information:");
                System.out.println("   " + updatedStudent);
            } else {
                System.out.println("❌ Failed to update student information.");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error updating student: " + e.getMessage());
        }
    }
    
    /**
     * Handles viewing individual student details
     */
    private static void viewStudentDetails() {
        System.out.println("\n--- VIEW STUDENT DETAILS ---");
        
        if (StudentManagement.getTotalStudents() == 0) {
            System.out.println("❌ No students in the system to view.");
            return;
        }
        
        try {
            String studentId = getStringInput("Enter student ID to view: ");
            Student student = StudentManagement.getStudentById(studentId);
            
            if (student != null) {
                System.out.println("\n✅ Student found:");
                System.out.println("=" .repeat(60));
                System.out.printf("Student ID    : %s%n", student.getStudentId());
                System.out.printf("Name          : %s%n", student.getName());
                System.out.printf("Age           : %d years%n", student.getAge());
                System.out.printf("Grade/Class   : %s%n", student.getGrade());
                System.out.println("=".repeat(60));
            } else {
                System.out.println("❌ Student with ID '" + studentId + "' not found.");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error viewing student details: " + e.getMessage());
        }
    }
    
    /**
     * Displays all students in the system
     */
    private static void viewAllStudents() {
        System.out.println("\n--- ALL STUDENTS IN SYSTEM ---");
        
        ArrayList<Student> allStudents = StudentManagement.getAllStudents();
        
        if (allStudents.isEmpty()) {
            System.out.println("❌ No students in the system.");
            return;
        }
        
        System.out.println("Total Students: " + StudentManagement.getTotalStudents());
        System.out.println("=".repeat(80));
        System.out.printf("%-4s %-10s %-20s %-5s %s%n", "#", "ID", "NAME", "AGE", "GRADE");
        System.out.println("-".repeat(80));
        
        for (int i = 0; i < allStudents.size(); i++) {
            Student student = allStudents.get(i);
            System.out.printf("%-4d %-10s %-20s %-5d %s%n", 
                            (i + 1), 
                            student.getStudentId(), 
                            student.getName(), 
                            student.getAge(), 
                            student.getGrade());
        }
        System.out.println("=".repeat(80));
    }
    
    /**
     * Handles searching for students
     */
    private static void searchStudents() {
        System.out.println("\n--- SEARCH STUDENTS ---");
        
        if (StudentManagement.getTotalStudents() == 0) {
            System.out.println("❌ No students in the system to search.");
            return;
        }
        
        try {
            System.out.println("Search options:");
            System.out.println("1. Search by ID");
            System.out.println("2. Search by Name");
            
            int searchChoice = getIntInput("Enter your choice (1-2): ");
            
            switch (searchChoice) {
                case 1:
                    String searchId = getStringInput("Enter student ID to search: ");
                    Student student = StudentManagement.getStudentById(searchId);
                    if (student != null) {
                        System.out.println("✅ Student found:");
                        System.out.println("   " + student);
                    } else {
                        System.out.println("❌ No student found with ID: " + searchId);
                    }
                    break;
                    
                case 2:
                    String searchName = getStringInput("Enter name to search (partial match supported): ");
                    ArrayList<Student> results = StudentManagement.searchStudentsByName(searchName);
                    
                    if (results.isEmpty()) {
                        System.out.println("❌ No students found with name containing: " + searchName);
                    } else {
                        System.out.println("✅ Found " + results.size() + " student(s):");
                        System.out.println("-".repeat(60));
                        for (Student s : results) {
                            System.out.println("   " + s);
                        }
                    }
                    break;
                    
                default:
                    System.out.println("❌ Invalid search option.");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error searching students: " + e.getMessage());
        }
    }
    
    /**
     * Handles removing a student record
     */
    private static void removeStudentRecord() {
        System.out.println("\n--- REMOVE STUDENT RECORD ---");
        
        if (StudentManagement.getTotalStudents() == 0) {
            System.out.println("❌ No students in the system to remove.");
            return;
        }
        
        try {
            String studentId = getStringInput("Enter student ID to remove: ");
            Student student = StudentManagement.getStudentById(studentId);
            
            if (student == null) {
                System.out.println("❌ Student with ID '" + studentId + "' not found.");
                return;
            }
            
            System.out.println("Student to be removed:");
            System.out.println("   " + student);
            
            String confirmation = getStringInput("Are you sure you want to remove this student? (yes/no): ");
            
            if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y")) {
                if (StudentManagement.removeStudent(studentId)) {
                    System.out.println("✅ Student record removed successfully!");
                } else {
                    System.out.println("❌ Failed to remove student record.");
                }
            } else {
                System.out.println("❌ Operation cancelled.");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error removing student: " + e.getMessage());
        }
    }
    
    /**
     * Gets string input from user with validation
     * @param prompt Prompt message
     * @return User input string
     */
    private static String getStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("❌ Input cannot be empty. Please try again.");
        }
    }
    
    /**
     * Gets optional string input (can be empty)
     * @param prompt Prompt message
     * @return User input string (can be empty)
     */
    private static String getOptionalStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    /**
     * Gets integer input from user with validation
     * @param prompt Prompt message
     * @return User input integer
     */
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return input;
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid number format. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
    
    /**
     * Initializes the system with sample data for demonstration purposes
     */
    private static void initializeSampleData() {
        StudentManagement.addStudent("Alice Johnson", "STU001", 16, "Grade 10");
        StudentManagement.addStudent("Bob Smith", "STU002", 15, "Grade 9");
        StudentManagement.addStudent("Carol Williams", "STU003", 17, "Grade 11");
        System.out.println("ℹ️  System initialized with sample data for demonstration.");
    }
    
    private static String repeatChar(char c, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}

/*
 * COMPREHENSIVE DOCUMENTATION
 * ===========================
 * 
 * OVERVIEW:
 * This Student Record Management System is a comprehensive Java application designed to help
 * administrators efficiently manage student records. The system follows object-oriented
 * programming principles with proper encapsulation, static methods, and error handling.
 * 
 * SYSTEM ARCHITECTURE:
 * 
 * 1. Student Class:
 *    - Contains private instance variables (name, studentId, age, grade)
 *    - Provides getter and setter methods for encapsulation
 *    - Implements toString() for formatted output
 * 
 * 2. StudentManagement Class:
 *    - Uses static variables for storing student list and total count
 *    - Implements static methods for all CRUD operations
 *    - Provides comprehensive validation and error handling
 *    - Methods include: addStudent(), updateStudent(), getStudentById(), etc.
 * 
 * 3. StudentRecordManagementSystem Class (Main):
 *    - Provides user interface and menu system
 *    - Handles user input and validation
 *    - Integrates with StudentManagement for business logic
 * 
 * KEY FEATURES:
 * 
 * 1. Add New Student:
 *    - Validates unique student ID
 *    - Checks for valid input data
 *    - Age validation (5-100 years)
 * 
 * 2. Update Student Information:
 *    - Allows selective updates (keep current values option)
 *    - Validates new data before updating
 *    - Shows before and after comparison
 * 
 * 3. View Student Details:
 *    - Individual student lookup by ID
 *    - Formatted display of student information
 * 
 * 4. View All Students:
 *    - Tabulated display of all students
 *    - Shows total count and numbered list
 * 
 * 5. Search Functionality:
 *    - Search by exact ID match
 *    - Search by partial name match (case-insensitive)
 * 
 * 6. Remove Student:
 *    - Confirmation dialog before deletion
 *    - Proper cleanup of data structures
 * 
 * ERROR HANDLING:
 * - Input validation for all user inputs
 * - Handles NumberFormatException for integer inputs
 * - Validates student ID uniqueness
 * - Checks for null and empty values
 * - Age range validation
 * - Graceful handling of edge cases
 * 
 * COMPILATION AND EXECUTION:
 * 
 * 1. Save the code in a file named "StudentRecordManagementSystem.java"
 * 
 * 2. Compile the program:
 *    javac StudentRecordManagementSystem.java
 * 
 * 3. Run the program:
 *    java StudentRecordManagementSystem
 * 
 * SYSTEM REQUIREMENTS:
 * - Java Development Kit (JDK) 8 or higher
 * - Command line interface or IDE with Java support
 * 
 * USER INTERACTION GUIDE:
 * 
 * 1. Upon starting, the system displays a welcome message and main menu
 * 2. Enter numbers 1-7 to select menu options
 * 3. Follow the prompts for each operation
 * 4. The system provides clear feedback for all operations
 * 5. Use option 7 to exit the system gracefully
 * 
 * SAMPLE USAGE:
 * - System starts with 3 sample students for demonstration
 * - Try adding a new student with ID "STU004"
 * - Update existing student information
 * - Search for students by name or ID
 * - View all students to see the complete list
 * 
 * TECHNICAL IMPLEMENTATION NOTES:
 * 
 * 1. Static Variables Usage:
 *    - studentList: ArrayList<Student> - stores all student records
 *    - totalStudents: int - maintains count of students
 * 
 * 2. Private Instance Variables:
 *    - All Student class variables are private with public getters/setters
 * 
 * 3. Static Methods:
 *    - All StudentManagement methods are static for classless access
 *    - Methods are logically separated by functionality
 * 
 * 4. Input Validation:
 *    - Comprehensive validation for all user inputs
 *    - Type checking and range validation
 *    - Empty string and null checks
 * 
 * 5. Code Best Practices:
 *    - Meaningful variable and method names
 *    - Comprehensive documentation and comments
 *    - Proper exception handling
 *    - Clean, readable code structure
 * 
 * FUTURE ENHANCEMENTS:
 * - File I/O for persistent storage
 * - Advanced search and filtering options
 * - Student grade calculations
 * - Export functionality (CSV, PDF)
 * - Batch operations for multiple students
 * 
 * This system demonstrates proper use of Java OOP concepts while providing a practical
 * and user-friendly interface for student record management.
 */