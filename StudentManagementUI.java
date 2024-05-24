import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentManagementUI extends JFrame {
    private StudentManager studentManager = new StudentManager();
    private JTextArea textArea;// Text area to display student information


    // Constructor to initialize the UI
    public StudentManagementUI() {
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    // Method to initialize UI components
    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1));

        // Create buttons for different operations and add action listeners
        JButton addButton = new JButton("Create a new student");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createStudent();
            }
        });
        buttonPanel.add(addButton);

        JButton viewAllButton = new JButton("View all students");
        viewAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAllStudents();
            }
        });
        buttonPanel.add(viewAllButton);

        JButton viewButton = new JButton("View a specific student’s information");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewStudentById();
            }
        });
        buttonPanel.add(viewButton);

        JButton modifyButton = new JButton("Modify the details of an existing student");
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });
        buttonPanel.add(modifyButton);

        JButton removeButton = new JButton("Remove a student from the system");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
        buttonPanel.add(removeButton);

        JButton searchButton = new JButton("Search students by name");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudentsByName();
            }
        });
        buttonPanel.add(searchButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.EAST);

        add(panel);
    }

    // Method to create a new student
    private void createStudent() {
        while (true) {
            try {
                // Prompt user to enter student details using JOptionPane dialogs
                String firstName = JOptionPane.showInputDialog("Enter first name:");
                if (!firstName.matches("[a-zA-Z]+")) {
                    throw new IllegalArgumentException("First name must contain only letters.");
                }

                String lastName = JOptionPane.showInputDialog("Enter last name:");
                if (!lastName.matches("[a-zA-Z]+")) {
                    throw new IllegalArgumentException("Last name must contain only letters.");
                }

                String ageInput = JOptionPane.showInputDialog("Enter age:");
                int age = Integer.parseInt(ageInput);
                if (age <= 0) {
                    throw new IllegalArgumentException("Age must be a positive integer.");
                }

                String major = JOptionPane.showInputDialog("Enter major:");

                // Create a new Student object and add it to the StudentManager
                Student student = new Student(firstName, lastName, age, major);
                String message = studentManager.addStudent(student);
                textArea.append(message + "\n");
                break;// Exit loop if student creation is successful
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Age must be an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to view all students
    private void viewAllStudents() {
        textArea.setText("");
        for (Student student : studentManager.getAllStudents()) {
            if (!student.isDeleted()) {
                textArea.append(student.toString() + "\n");
            }
        }
    }

    // Method to view a specific student by ID
    private void viewStudentById() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter student ID:"));
            studentManager.getStudentById(id).ifPresentOrElse(
                student -> textArea.setText(student.toString()),
                () -> textArea.setText("Student not found.\n")
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID must be an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent() {
        while (true) {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter student ID:"));
                String firstName = JOptionPane.showInputDialog("Enter new first name:");
                 // Validate first name (should contain only letters)
                if (!firstName.matches("[a-zA-Z]+")) {
                    throw new IllegalArgumentException("First name must contain only letters.");
                }

                String lastName = JOptionPane.showInputDialog("Enter new last name:");
                // Validate last name (should contain only letters)
                if (!lastName.matches("[a-zA-Z]+")) {
                    throw new IllegalArgumentException("Last name must contain only letters.");
                }

                String ageInput = JOptionPane.showInputDialog("Enter new age:");
                int age = Integer.parseInt(ageInput);
                 // Validate age (should be a positive integer)
                if (age <= 0) {
                    throw new IllegalArgumentException("Age must be a positive integer.");
                }

                String major = JOptionPane.showInputDialog("Enter new major:");

                // Update student details using StudentManager
                String message = studentManager.updateStudent(id, firstName, lastName, age, major);
                textArea.append(message + "\n");
                break;// Exit loop if update is successful
            } catch (NumberFormatException e) {
                // Handle NumberFormatException (occurs if age is not an integer)
                JOptionPane.showMessageDialog(this, "Age must be an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                // Handle IllegalArgumentException (occurs if input validation fails)
                JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

     // Method to delete a student
     private void deleteStudent() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter student ID:"));
            if (studentManager.deleteStudent(id)) {
                textArea.append("Student deleted successfully.\n");
            } else {
                textArea.append("Student not found.\n");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID must be an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to search students by name
    private void searchStudentsByName() {
        String name = JOptionPane.showInputDialog("Enter name to search:");
        List<Student> results = studentManager.searchStudentsByName(name);
        textArea.setText("");
        if (results.isEmpty()) {
            textArea.append("No students found.\n");
        } else {
            for (Student student : results) {
                textArea.append(student.toString() + "\n");
            }
        }
    }

    // Main method to launch the GUI
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            StudentManagementUI ui = new StudentManagementUI();
            ui.setVisible(true);
        });
    }
}
