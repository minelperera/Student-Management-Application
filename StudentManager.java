import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    // Method to add a student to the list
    public String addStudent(Student student) {
        students.add(student);
        return "Student added successfully.";
    }

    // Method to retrieve all students
    public List<Student> getAllStudents() {
        return students;
    }

    // Method to retrieve a student by their ID
    public Optional<Student> getStudentById(int id) {
        return students.stream().filter(s -> s.getId() == id && !s.isDeleted()).findFirst();
    }

    // Method to update a student's details
    public String updateStudent(int id, String firstName, String lastName, int age, String major) {
        // Retrieve the student by ID
        Optional<Student> studentOpt = getStudentById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            // Update the student's details
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setAge(age);
            student.setMajor(major);
            return "Student updated successfully.";
        } else {
            
            return "Student not found.";// Return error message if student not found
        }
    }

    // Method to soft delete a student
    public boolean deleteStudent(int id) {
        Optional<Student> studentOpt = getStudentById(id);
        if (studentOpt.isPresent()) {
            studentOpt.get().setDeleted(true);// Set the deleted flag to true
            return true;// Return true if deletion was successful
        }
        return false;// Return false if student not found
    
    }

    // Method to search students by their name (either first or last name)
    public List<Student> searchStudentsByName(String name) {
        List<Student> results = new ArrayList<>();
        for (Student student : students) {
             // Check if the student is not deleted and if the name matches (case-insensitive)
             if (!student.isDeleted() && (student.getFirstName().equalsIgnoreCase(name) || student.getLastName().equalsIgnoreCase(name))) {
                results.add(student);
            }
        }
        return results;
    }
}
