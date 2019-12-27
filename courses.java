package Project2;

public class courses {
    private int courseID;
    private String courseTitle;
    private String department;
    //constructors
    public courses(){

    }
    public courses(int courseID, String courseTitle, String department){
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.department = department;
    }
    //set methods
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    //get methods
    public int getCourseID() {
        return courseID;
    }
    public String getCourseTitle() {
        return courseTitle;
    }
    public String getDepartment() {
        return department;
    }
}
