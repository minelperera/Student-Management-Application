import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagementUI extends JFrame {
    private StudentManager studentManager = new StudentManager();
    private JTextArea textArea;

    public StudentManagementUI() {
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1));

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

    private void createStudent() {
        String firstName = JOptionPane.showInputDialog("Enter first name:");
        String lastName = JOptionPane.showInputDialog("Enter last name:");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter age:"));
        String major = JOptionPane.showInputDialog("Enter major:");

        Student student = new Student(firstName, lastName, age, major);
        studentManager.addStudent(student);
        textArea.append("Student added successfully.\n");
    }

    private void viewAllStudents() {
        textArea.setText("");
        for (Student student : studentManager.getAllStudents()) {
            if (!student.isDeleted()) {
                textArea.append(student.toString() + "\n");
            }
        }
    }

    private void viewStudentById() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter student ID:"));
        studentManager.getStudentById(id).ifPresentOrElse(
            student -> textArea.setText(student.toString()),
            () -> textArea.setText("Student not found.\n")
        );
    }

    private void updateStudent() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter student ID:"));
        String firstName = JOptionPane.showInputDialog("Enter new first name:");
        String lastName = JOptionPane.showInputDialog("Enter new last name:");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter new age:"));
        String major = JOptionPane.showInputDialog("Enter new major:");

        if (studentManager.updateStudent(id, firstName, lastName, age, major)) {
            textArea.append("Student updated successfully.\n");
        } else {
            textArea.append("Student not found.\n");
        }
    }

    private void deleteStudent() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter student ID:"));
        if (studentManager.deleteStudent(id)) {
            textArea.append("Student deleted successfully.\n");
        } else {
            textArea.append("Student not found.\n");
        }
    }

    public static void runUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementUI().setVisible(true);
            }
        });
    }
}
