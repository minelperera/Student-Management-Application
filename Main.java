import java.util.List;
import java.util.Scanner;

public class Main {
    // Instance of StudentManager to manage students
    private static StudentManager studentManager = new StudentManager();
    // Scanner instance to read user input
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
             // Display menu options for console or GUI
            System.out.println("1. Console");
            System.out.println("2. GUI");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
             // Switch based on user input
             switch (choice) {
                case "1":
                    runConsoleInterface();
                    break;
                case "2":
                    runGuiInterface();
                    break;
                default:
                    System.out.println("Invalid input. Please enter 1 for Console or 2 for GUI.");
            }
        }
    }

    // Method to run console interface
    private static void runConsoleInterface() {
        while (true) {
            // Display menu options for console operations
            System.out.println("1. Create a new student");
            System.out.println("2. View all students");
            System.out.println("3. View a specific student’s information");
            System.out.println("4. Modify the details of an existing student");
            System.out.println("5. Remove a student from the system");
            System.out.println("6. Search students by name");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
              // Switch based on user input
              switch (choice) {
                case "1":
                    createStudent();
                    break;
                case "2":
                    viewAllStudents();
                    break;
                case "3":
                    viewStudentById();
                    break;
                case "4":
                    updateStudent();
                    break;
                case "5":
                    deleteStudent();
                    break;
                case "6":
                    searchStudentsByName();
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");// Handle invalid input
            }
        }
    }

    // Method to run GUI interface
    private static void runGuiInterface() {
        StudentManagementUI ui = new StudentManagementUI();
        ui.setVisible(true);
    }

    private static void createStudent() {
        while (true) {
            try {
                // Read and validate first name
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();
                if (!firstName.matches("[a-zA-Z]+")) {
                    throw new IllegalArgumentException("First name must contain only letters.");
                }

                // Read and validate last name
                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();
                if (!lastName.matches("[a-zA-Z]+")) {
                    throw new IllegalArgumentException("Last name must contain only letters.");
                }

                 // Read and validate age
                 System.out.print("Enter age: ");
                int age = Integer.parseInt(scanner.nextLine());
                if (age <= 0) {
                    throw new IllegalArgumentException("Age must be a positive integer.");
                }

                // Read major
                System.out.print("Enter major: ");
                String major = scanner.nextLine();
            

                // Create a new student and add to the manager
                Student student = new Student(firstName, lastName, age, major);
                String message = studentManager.addStudent(student);
                System.out.println(message);
                break;// Exit loop if student creation is successful
            } catch (NumberFormatException e) {
                System.out.println("Age must be an integer.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

     // Method to view all students
     private static void viewAllStudents() {
        for (Student student : studentManager.getAllStudents()) {
            if (!student.isDeleted()) {
                System.out.println(student);
            }
        }
    }

     // Method to view a specific student by ID
     private static void viewStudentById() {
        try {
            System.out.print("Enter student ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            studentManager.getStudentById(id).ifPresentOrElse(
                student -> System.out.println(student),
                () -> System.out.println("Student not found.")
            );
        } catch (NumberFormatException e) {
            System.out.println("ID must be an integer.");
        }
    }

    // Method to update student details
    private static void updateStudent() {
        while (true) {
            try {
                System.out.print("Enter student ID: ");
                int id = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter new first name: ");
                String firstName = scanner.nextLine();
                if (!firstName.matches("[a-zA-Z]+")) {
                    throw new IllegalArgumentException("First name must contain only letters.");
                }

                System.out.print("Enter new last name: ");
                String lastName = scanner.nextLine();
                if (!lastName.matches("[a-zA-Z]+")) {
                    throw new IllegalArgumentException("Last name must contain only letters.");
                }

                System.out.print("Enter new age: ");
                int age = Integer.parseInt(scanner.nextLine());
                if (age <= 0) {
                    throw new IllegalArgumentException("Age must be a positive integer.");
                }

                System.out.print("Enter new major: ");
                String major = scanner.nextLine();

                String message = studentManager.updateStudent(id, firstName, lastName, age, major);
                System.out.println(message);
                break;// Exit loop if update is successful
            } catch (NumberFormatException e) {
                System.out.println("Age must be an integer.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Method to delete a student
    private static void deleteStudent() {
        try {
            System.out.print("Enter student ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (studentManager.deleteStudent(id)) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID must be an integer.");
        }
    }

    // Method to search students by name
    private static void searchStudentsByName() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        List<Student> results = studentManager.searchStudentsByName(name);
        if (results.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : results) {
                System.out.println(student);
            }
        }
    }
}
