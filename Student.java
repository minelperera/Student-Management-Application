public class Student {
    private static int idCounter = 1;
     // Instance variables for the Student class
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String major;
    private boolean deleted;

     // Constructor to initialize a new student with provided details
     public Student(String firstName, String lastName, int age, String major) {
        this.id = idCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.major = major;
        this.deleted = false;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    // Overriding toString method to provide a readable representation of the student
    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Age: " + age + "\n" +
                "Major: " + major + "\n";
    }
}
