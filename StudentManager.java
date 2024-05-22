import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Optional<Student> getStudentById(int id) {
        return students.stream().filter(s -> s.getId() == id && !s.isDeleted()).findFirst();
    }

    public boolean updateStudent(int id, String firstName, String lastName, int age, String major) {
        Optional<Student> studentOptional = getStudentById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setAge(age);
            student.setMajor(major);
            return true;
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        Optional<Student> studentOptional = getStudentById(id);
        if (studentOptional.isPresent()) {
            studentOptional.get().setDeleted(true);
            return true;
        }
        return false;
    }
}
