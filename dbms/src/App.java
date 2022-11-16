import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        String dbURL = "jdbc:oracle:thin:[s86shah/02060327]@oracle12c.scs.ryerson.ca:1521:orcl12c";
        String username = "s86shah";
        String password = "02060327";
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            System.out.println("Connected to Oracle Database Server!");
            
        } catch (SQLException e) {
            System.out.println("Database Connection Error!");
            e.printStackTrace();
        };
        
        menu();
        
        try {
			connection.close();
			System.out.println("Database connection closed.");
		} catch (SQLException e) {
			System.out.println("Error in closing the database connection.");
			e.printStackTrace();
		}
        System.exit(0);
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
                    return;
 
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
