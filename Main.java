import java.util.Scanner;

public class Main {
    private static StudentManager studentManager = new StudentManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Choose mode:");
        System.out.println("1. Console");
        System.out.println("2. GUI");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            runConsole();
        } else if (choice == 2) {
            StudentManagementUI.runUI();
        } else {
            System.out.println("Invalid choice. Exiting.");
        }
    }

    private static void runConsole() {
        while (true) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> createStudent();
                case 2 -> viewAllStudents();
                case 3 -> viewStudentById();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("1. Create a new student");
        System.out.println("2. View all students");
        System.out.println("3. View a specific student’s information");
        System.out.println("4. Modify the details of an existing student");
        System.out.println("5. Remove a student from the system");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createStudent() {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter major: ");
        String major = scanner.nextLine();

        Student student = new Student(firstName, lastName, age, major);
        studentManager.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private static void viewAllStudents() {
        for (Student student : studentManager.getAllStudents()) {
            if (!student.isDeleted()) {
                System.out.println(student);
            }
        }
    }

    private static void viewStudentById() {
        System.out.print("Enter student ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        studentManager.getStudentById(id).ifPresentOrElse(
            student -> System.out.println(student),
            () -> System.out.println("Student not found.")
        );
    }

    private static void updateStudent() {
        System.out.print("Enter student ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter new last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter new age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new major: ");
        String major = scanner.nextLine();

        if (studentManager.updateStudent(id, firstName, lastName, age, major)) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (studentManager.deleteStudent(id)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }
}
