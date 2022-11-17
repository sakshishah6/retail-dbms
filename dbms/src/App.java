import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;

public class App {

	static Connection connection = null;

	public static void main(String[] args) {

		/*
		 * "jdbc:oracle:thin:username/password@oracle12c.scs.ryerson.ca:1521:orcl";
		 */

		// replace user name and password below
		String dbURL = "jdbc:oracle:thin:username/password@oracle.scs.ryerson.ca:1521:orcl";

		try {
			connection = DriverManager.getConnection(dbURL);
			System.out.println("Connected to Oracle Database Server!");
			menu();

		} catch (SQLException e) {
			System.out.println("Database Connection Error!");
			e.printStackTrace();
		}
		;

	};

	public static void menu() throws SQLException {
		int choice;
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\n\nWelcome to the Online Store Database!\n");
			System.out.println("Enter a number to make your selection:");
			System.out.println("1: Create Tables");
			System.out.println("2: Populate Tables");
			System.out.println("3: View All Tables");
			System.out.println("4: View Queries");
			System.out.println("5: Drop Tables");
			System.out.println("6: Quit\n \n ");

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
				viewTables();
				break;
				
			case 4:
				viewQueries();
				break;

			case 5:
				dropTables();
				break;

			case 6:
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
				System.out.println("Invalid choice! Please make a valid choice. \\n\\n");
			}
		}
	}

	public static void createTables() throws SQLException {

		String createStores = "CREATE TABLE st7894 ( " + "store_id INT NOT NULL, " + "phone_number INT NOT NULL, "
				+ "employee_count INT NOT NULL, " + "address_street_number INT NOT NULL, "
				+ "address_street VARCHAR2(100) NOT NULL, " + "address_city VARCHAR2(100) NOT NULL, "
				+ "address_province VARCHAR2(100) NOT NULL, " + "address_postal_code VARCHAR2(6) NOT NULL, "
				+ "PRIMARY KEY (store_id))";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(createStores);
			statement.close();
			System.out.println("All tables successfully created.\n\n");
		} catch (SQLException e) {
			System.out.println("There was a problem creating the table.");
			e.printStackTrace();
		}
	}

	public static void populateTables() {
		String insertToStores = "INSERT INTO st7894 VALUES (2, 789465132, 50, 1200, 'dundas', 'toronto', 'on', 'l6p3l8')";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(insertToStores);
			int rows = statement.executeUpdate(insertToStores);
			if (rows > 0) {
				System.out.println("a row has been inserted");
			}
			statement.close();
			System.out.println("All tables successfully populated.\n\n");
		} catch (SQLException e) {
			System.out.println("There was a problem inserting these values into the table.");
			e.printStackTrace();
		}
	}

	public static void viewTables() {
		String viewStores = "select * from st7894";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(viewStores);
			statement.close();
		} catch (SQLException e) {
			System.out.println("There was a problem.");
			e.printStackTrace();
		}
	}
	
	public static void viewQueries() {

		// give a list of queries that the user can select from (queries described using
		// words)
		// user selects a number and then the query will run
		// query in sql as well as the results will be displayed
	}

	public static void dropTables() throws SQLException {
		String droptables = "DROP TABLE ST7894";
		
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(droptables);
			statement.close();
			System.out.println("All tables successfully dropped.\n\n");
		} catch (SQLException e) {
			System.out.println("There was a problem dropping the tables.");
			e.printStackTrace();
		}
	}
}
