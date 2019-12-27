package Project2;
import java.sql.*;
//********************************************************************
// DatabaseConnector.java Java Foundations
//
// Demonstrates the establishment of a JDBC connector.
//********************************************************************
public class DatabaseConnector
{
    //-----------------------------------------------------------------
// Establishes the connection to the database and prints an
// appropriate confirmation message.
//-----------------------------------------------------------------
    public static void main (String args[])
    {
        try
        {
            Connection conn = null;
// Loads the class object for the sqlite driver into the DriverManager.
            Class.forName("org.sqlite.JDBC");
// Attempt to establish a connection to the specified database via the
// DriverManager
            conn = DriverManager.getConnection("jdbc:sqlite://comtor.org/"+
                    "javafoundations?user=jf2e&password=hirsch");
            if (conn != null)
            {
                System.out.println("We have connected to our database!");
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}