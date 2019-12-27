package Project2;

public class classes {
    private int classCode;
    private int courseID;
    private int studentID;
    private int year;
    private String semester;
    private String GPA;
    //constructors
    public  classes(){

    }
    public classes(int classCode, int courseID, int studentID, int year, String semester, String GPA){
        this.classCode = classCode;
        this.courseID = courseID;
        this.studentID = studentID;
        this.year = year;
        this.semester = semester;
        this.GPA = GPA;
    }
    //set methods
    public void setClassCode(int classCode) {
        this.classCode = classCode;
    }
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public void setGPA(String GPA) {
        this.GPA = GPA;
    }
    //get methods
    public int getClassCode() {
        return classCode;
    }
    public int getCourseID() {
        return courseID;
    }
    public int getStudentID() {
        return studentID;
    }
    public int getYear() {
        return year;
    }
    public String getSemester() {
        return semester;
    }
    public String getGPA() {
        return GPA;
    }
}
