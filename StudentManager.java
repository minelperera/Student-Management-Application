import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    public String addStudent(Student student) {
        students.add(student);
        return "Student added successfully.";
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Optional<Student> getStudentById(int id) {
        return students.stream().filter(s -> s.getId() == id && !s.isDeleted()).findFirst();
    }

    public String updateStudent(int id, String firstName, String lastName, int age, String major) {
        Optional<Student> studentOpt = getStudentById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setAge(age);
            student.setMajor(major);
            return "Student updated successfully.";
        } else {
            return "Student not found.";
        }
    }

    public boolean deleteStudent(int id) {
        Optional<Student> studentOpt = getStudentById(id);
        if (studentOpt.isPresent()) {
            studentOpt.get().setDeleted(true);
            return true;
        }
        return false;
    }

    public List<Student> searchStudentsByName(String name) {
        List<Student> results = new ArrayList<>();
        for (Student student : students) {
            if (!student.isDeleted() && (student.getFirstName().equalsIgnoreCase(name) || student.getLastName().equalsIgnoreCase(name))) {
                results.add(student);
            }
        }
        return results;
    }
}
