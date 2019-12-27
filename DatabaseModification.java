package Project2;
import Project1.PieChart;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.SortedMap;
import java.util.TreeMap;
import javafx.stage.Stage;

public class DatabaseModification {
    private static PieChart P = new PieChart(300, 300);
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=abcdefg");
            if (conn != null) {
                System.out.println("We have connected to our database!");
                DatabaseModification.dropTables(conn);
                DatabaseModification.createTables(conn);
                //insert data in DB

                //students
                insertStudents(conn, new students(20, "Jessica", "person", "F"));
                insertStudents(conn, new students(21, "John", "person", "M"));
                insertStudents(conn, new students(22, "Jeremy", "person", "M"));
                insertStudents(conn, new students(23, "Johanna", "person", "F"));
                insertStudents(conn, new students(24, "Ben", "person", "M"));
                insertStudents(conn, new students(25, "Michael", "person", "M"));

                //courses
                DatabaseModification.insertCourses(conn, new courses(523, "Picasso", "ART"));
                DatabaseModification.insertCourses(conn, new courses(101, "Intro", "HIST"));
                DatabaseModification.insertCourses(conn, new courses(211, "JAVA", "CSC"));

                //classes
                insertClasses(conn, new classes(0, 211, 25, 2019, "Fall", "F"));
                DatabaseModification.insertClasses(conn, new classes(0, 211, 20, 2019, "Fall", "A" ));
                DatabaseModification.insertClasses(conn, new classes(0, 211, 21, 2019, "Fall", "F" ));
                DatabaseModification.insertClasses(conn, new classes(0, 211, 22, 2019, "Fall", "W" ));
                DatabaseModification.insertClasses(conn, new classes(0, 211, 23, 2019, "Fall", "A" ));
                DatabaseModification.insertClasses(conn, new classes(0, 211, 24, 2019, "Fall", "C" ));
                DatabaseModification.insertClasses(conn, new classes(0, 523, 24, 2019, "Fall", "C" ));
                DatabaseModification.insertClasses(conn, new classes(0, 101, 23, 2019, "Fall", "D" ));
                DatabaseModification.insertClasses(conn, new classes(0, 523, 23, 2019, "Fall", "F" ));
                DatabaseModification.insertClasses(conn, new classes(0, 101, 20, 2019, "Fall", "A" ));
                DatabaseModification.insertClasses(conn, new classes(0, 523, 21, 2019, "Fall", "W" ));
                for(int i = 0; i< 6;i++ ) {
                    String[] name = {"A", "B", "C", "D", "F", "W"};
                    DatabaseModification.insertStudents(conn, new students(i+1, "Fname" + name[i], "Lname" + name[i], "M"));
                    DatabaseModification.insertCourses(conn, new courses(212 + i, "Jav" + name[i], "C" + "S" + "C"));
                    DatabaseModification.insertClasses(conn, new classes(i + 400, 211, i+1, 2019, "Fall", name[i]));
                }
                DatabaseModification.mergeTables(conn);
                DatabaseModification.getGpaChart(conn);
                // show stuff
                DatabaseModification.showValues(conn, "Students");
                DatabaseModification.showValues(conn, "Courses");
                DatabaseModification.showValues(conn, "Classes");
                showColumns(conn, "classes");
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
//create tables
    public static void createTables(Connection conn) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "CREATE TABLE `test`.`Students` (" +
                            "`studentID` INT NOT NULL AUTO_INCREMENT, " +
                            "`firstName` VARCHAR(255) NULL, " +
                            "`lastName` VARCHAR(255) NULL, " +
                            "`sex` ENUM('M', 'F')," +
                            "PRIMARY KEY (`studentID`))");
            stmt.execute();
            PreparedStatement stmt2 = conn.prepareStatement(
                    "CREATE TABLE `test`.`courses` (" +
                            " `courseID` INT NOT NULL," +
                            "`courseTitle` VARCHAR(255) ," +
                            "`department` VARCHAR(255) ," +
                            "PRIMARY KEY (`courseID`))");
            stmt2.execute();
            PreparedStatement stmt3 = conn.prepareStatement(
                    "CREATE TABLE `test`.`classes` ( " +
                            "  `classCode` INT NOT NULL AUTO_INCREMENT, " +
                            "  `courseID` INT," +
                            "  `studentID` INT, " +
                            "  `year` INT, " +
                            "  `semester` VARCHAR(255), " +
                            "  `GPA` ENUM('A', 'B', 'C', 'D', 'F', 'W'), " +
                            "  PRIMARY KEY (`classCode`), " +
                            "  UNIQUE KEY `unique_ids` (`classCode`, `studentID`), " +
                            "  FOREIGN KEY(courseID) references courses(courseID), " +
                            "  FOREIGN KEY(studentID) references students(studentID)) "  );
                            stmt3.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //close tables
    public static void dropTables(Connection conn){
        try{
            PreparedStatement stmtClose1 = conn.prepareStatement(
                    "DROP TABLE `test`.`classes`;");

            stmtClose1.execute();
            PreparedStatement stmtClose2 = conn.prepareStatement(
                    "DROP TABLE `test`.`courses`");
            stmtClose2.execute();
            PreparedStatement stmtClose3 = conn.prepareStatement(
                    "DROP TABLE `test`.`students`");
            stmtClose3.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//insert methods
    public static void insertStudents(Connection conn, students S) {
        try {
            PreparedStatement stmtStudents = conn.prepareStatement("" +
                    "INSERT Students (studentID, firstName, lastName, sex) " +
                    "VALUES(?,?,?,?)");
            stmtStudents.setInt(1, S.getStudentID());
            stmtStudents.setString(2, S.getFirstName());
            stmtStudents.setString(3, S.getLastName());
            stmtStudents.setObject(4, S.getSex());
            stmtStudents.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertCourses(Connection conn, courses C){
        try {
            PreparedStatement stmtCourses = conn.prepareStatement(
                    "INSERT Courses (courseID, courseTitle, Department) " +
                            "VALUES(?,?,?)");
            stmtCourses.setInt(1, C.getCourseID());
            stmtCourses.setString(2, C.getCourseTitle());
            stmtCourses.setString(3, C.getDepartment());
            stmtCourses.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertClasses(Connection conn, classes C){
        try {
            PreparedStatement stmtClasses = conn.prepareStatement(
                    "INSERT Classes (classCode, courseID, studentID, year, semester, GPA) " +
                            "VALUES(?,?,?,?,?,?)");
            stmtClasses.setInt(1, C.getClassCode());
            stmtClasses.setInt(2, C.getCourseID());
            stmtClasses.setInt(3, C.getStudentID());
            stmtClasses.setInt(4, C.getYear());
            stmtClasses.setString(5, C.getSemester());
            stmtClasses.setObject(6, C.getGPA());
            stmtClasses.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// Obtains and displays a ResultSet from the Students table.
    public static void showValues(Connection conn, String table) {
        try {
            String selectorString = "SELECT * FROM "+ table;
            PreparedStatement stmt = conn.prepareStatement(selectorString);
            ResultSet rset = stmt.executeQuery();
            DatabaseModification.showResults(table, rset);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
// Displays the structure of the Student table.
    public static void showColumns(Connection conn, String table) {
        try
        {
            String selectorString = "SHOW COLUMNS FROM "+ table;
            PreparedStatement stmt = conn.prepareStatement(selectorString);
            ResultSet rset = stmt.executeQuery();
            DatabaseModification.showResults(table, rset);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
// Displays the contents of the specified ResultSet.
    public static void showResults(String tableName, ResultSet rSet) {
        int count = 0;
        try
        {
            ResultSetMetaData rsmd = rSet.getMetaData();
            int numColumns = rsmd.getColumnCount();
            String resultString = null;
            if (numColumns > 0)
            {
                resultString = "\nTable: " + tableName + "\n" +
                        "=======================================================\n";
                for (int colNum = 1; colNum <= numColumns; colNum++)
                    resultString += rsmd.getColumnLabel(colNum) + " ";
            }
            System.out.println(resultString);
            System.out.println(
                    "=======================================================");
            while (rSet.next())
            {
                resultString = "";
                for (int colNum = 1; colNum <= numColumns; colNum++)
                {
                    String column = rSet.getString(colNum);
                    if (column != null)
                        resultString += column + " ";
                }
                count++;
                System.out.println(resultString + '\n' +
                        "------------------------------------------------------------");
            }
            System.out.println(" Total # of entries is: " + count);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
// Merge tables
    public static void mergeTables(Connection conn){
        try {
            PreparedStatement stmtStudents = conn.prepareStatement("" +
                    "SELECT courses.department, classes.courseID, classes.semester, " +
                    "classes.year, students.firstName, students.lastName," +
                    "classes.studentID, classes.GPA " +
                    "FROM classes " +
                    "INNER JOIN courses ON " +
                    "courses.courseID = classes.courseID " +
                    "INNER JOIN students ON " +
                    "classes.studentID = students.studentID "  +
                    "WHERE department = 'CSC' AND classes.courseID = '211' " +
                    "AND semester = 'FALL' AND year = '2019' ");
            ResultSet rset = stmtStudents.executeQuery();
            DatabaseModification.showResults("JOIN", rset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getGpaChart(Connection conn){
        try {
            PreparedStatement stmtStudents = conn.prepareStatement("" +
                    "SELECT classes.GPA, COUNT(GPA) " +
                    "FROM classes " +
//                    "INNER JOIN students ON " +
//                    "students.studentID = classes.studentID " +
                    "GROUP BY GPA " +
                    "ORDER BY GPA");
            ResultSet rset = stmtStudents.executeQuery();
            DatabaseModification.showResults("GPAs for pie chart", rset);
            System.out.println("result should diplay here: \n");
            ResultSet rSet = stmtStudents.executeQuery();
            makePieChart.populate(rSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static class makePieChart extends Application {
        private static SortedMap<String,Integer> M = new TreeMap<String, Integer>();
        public static void populate(ResultSet rSet) {
            try {
                while (rSet.next()) {
                    String column = rSet.getString(1);
                    Integer number = rSet.getInt(2);
                    column += ": "+ number;
                    if(column != null) {
                        M.put(column, number);
                    }
                }
            } catch (
                    SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                ex.printStackTrace();
            }
            P.makeProbability(M);
            launch();
        }
        @Override
        public void start(Stage stage) {
            final int w = 600, h = 600;
            Group root = new Group();
            Canvas canvas = new Canvas(w, h);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            //PieChart P = new PieChart(w / 2, h / 2);
            P.setX(w / 2);
            P.setY(h / 2);
            P.setN(6);
            P.draw(gc);
            //display canvas / scene / stage
            root.getChildren().add(canvas); //adds the canvas to the root group
            stage.setScene(new Scene(root));//sets the scene on the stage that is using the root with the canvas elements
            stage.show();// tells java to show the stage in the application with all of the things that we put in it
        }
    }
}
