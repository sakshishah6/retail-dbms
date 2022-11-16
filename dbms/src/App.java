import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;

public class App {
	
	static Connection connection = null;
	
    public static void main(String[] args) {
    	
        /* "jdbc:oracle:thin:[username/password]@oracle12c.scs.ryerson.ca:1521:orcl12c"; */
    	
    	//fill in username and password below (according to string above)
    	String dbURL = "jdbc:oracle:thin:[/]@oracle12c.scs.ryerson.ca:1521:orcl12c";
                
        try {
            connection = DriverManager.getConnection(dbURL);
            System.out.println("Connected to Oracle Database Server!");
            menu();
            
        } catch (SQLException e) {
            System.out.println("Database Connection Error!");
            e.printStackTrace();
        };
        
    };
    
    public static void menu() {
    	int choice;
    	Scanner scanner = new Scanner(System.in);

        while(true) {
        	System.out.println("\n\nWelcome to the Online Store Database!\n");
        	System.out.println("Enter a number to make your selection:");
            System.out.println("1: Create Tables");
            System.out.println("2: Populate Tables");
            System.out.println("3: View Queries");
            System.out.println("4: Drop Tables");
            System.out.println("5: Quit\n \n ");
 
            System.out.println("Your selection: ");
            choice = scanner.nextInt();
 
            switch (choice) {
 
                case 1:
                	createTables();
                    break;
 
                case 2:
                	populateTables();
                    break;
 
                case 3:
                    viewQueries();
                    break;
 
                case 4:
                	dropTables();
                    break;
 
                case 5:
                	scanner.close();
                    try {
            			connection.close();
            			System.out.println("Database connection closed.");
            		} catch (SQLException e) {
            			System.out.println("Error in closing the database connection.");
            			e.printStackTrace();
            		}
                    System.exit(0);
 
                default:
                    System.out.println("Invalid choice!!! Please make a valid choice. \\n\\n");
            }
        }
    }
    
    public static void createTables() {
    	
    	System.out.println("All tables successfully created.\n\n");
    }
    
    public static void populateTables() {
    	
    	System.out.println("All tables successfully populated.\n\n");
    }
    
    public static void viewQueries() {
    	//give a list of queries that the user can select from (queries described using words)
    	//user selects a number and then the query will run
    	//query in sql as well as the results will be displayed
    }
    
    public static void dropTables() {
    	
    	System.out.println("All tables successfully dropped.\n\n");
    }
}
