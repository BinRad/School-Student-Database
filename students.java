package Project2;

public class students {
    private int studentID;
    private String firstName;
    private String lastName;
    private String sex;
    //Constructors
    public students(){
    }
    public students(int studentID, String firstName, String lastName,  String sex){
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
    }
    //set methods
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    //get methods
    public int getStudentID() {
        return studentID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getSex() {
        return sex;
    }
}
