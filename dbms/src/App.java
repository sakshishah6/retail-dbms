import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;

public class App {

	static Connection connection = null;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		String username = "";
		String password = "";
		System.out.println("Oracle Username: ");
		username = scanner.nextLine();
		System.out.println("Oracle Password: ");
		password = scanner.nextLine();
		
		System.out.println("\nConnecting...\n");
		
		String dbURL = "jdbc:oracle:thin:"+username+"/"+password+"@oracle.scs.ryerson.ca:1521:orcl";

		try {
			connection = DriverManager.getConnection(dbURL);
			System.out.println("Connected to Oracle Database Server!\n");
			menu();

		} catch (SQLException e) {
			System.out.println("Database Connection Error!\n");
			e.printStackTrace();
		}
		;

	};

	public static void menu() throws SQLException {
		int choice;

		while (true) {
			System.out.println("\n\nWelcome to the Online Store Database!\n");
			System.out.println("Enter a number to make your selection:");
			System.out.println("1: Create Tables");
			System.out.println("2: Populate Tables");
			System.out.println("3: View All Tables");
			System.out.println("4: View Queries");
			System.out.println("5: Drop Tables");
			System.out.println("6: Quit\n");
			System.out.print("Your selection: ");
			
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
				System.out.println("Invalid choice! Please make a valid choice.\n");
			}
		}
	}

	public static void createTables() throws SQLException {

		String create1 = "CREATE TABLE stores (store_id INT NOT NULL,phone_number INT NOT NULL,employee_count INT NOT NULL,address_street_number INT NOT NULL,address_street VARCHAR2(100) NOT NULL,address_city VARCHAR2(100) NOT NULL,address_province VARCHAR2(100) NOT NULL,address_postal_code VARCHAR2(6) NOT NULL,PRIMARY KEY (store_id))";
		String create2 = "CREATE TABLE product (product_id INT NOT NULL,product_name VARCHAR2(100) NOT NULL,clothing_type VARCHAR2(100) NOT NULL,price NUMBER(5, 2) NOT NULL,brand VARCHAR2(100) NOT NULL,material VARCHAR2(100) NOT NULL,product_size CHAR NOT NULL,colour VARCHAR2(100) NOT NULL,product_desc VARCHAR2(200),PRIMARY KEY (product_id))";
		String create3 = "CREATE TABLE products_in_store (product_id INT NOT NULL,store_id INT NOT NULL,quantity INT NOT NULL,PRIMARY KEY (product_id, store_id),FOREIGN KEY (product_id) REFERENCES product(product_id),FOREIGN KEY (store_id) REFERENCES stores(store_id))";
		String create4 = "CREATE TABLE employee (employee_id INT NOT NULL,first_name VARCHAR2(50) NOT NULL,middle_name VARCHAR2(50),last_name VARCHAR2(50) NOT NULL,department VARCHAR2(50) NOT NULL,phone_number INT NOT NULL,email_address VARCHAR2(50) NOT NULL,employee_role VARCHAR2(50) NOT NULL,salary FLOAT NOT NULL,bank_account_number NUMBER NOT NULL,birth_date DATE NOT NULL,date_hired DATE NOT NULL,PRIMARY KEY (employee_id))";
		String create5 = "CREATE TABLE employees_in_store (employee_id INT NOT NULL,store_id INT NOT NULL,PRIMARY KEY (employee_id),FOREIGN KEY (employee_id) REFERENCES employee(employee_id),FOREIGN KEY (store_id) REFERENCES stores(store_id))";
		String create6 = "CREATE TABLE address_of_employee (addressemp_id INT NOT NULL,address_street_number INT NOT NULL,address_street VARCHAR2(100) NOT NULL,address_city VARCHAR2(100) NOT NULL,address_province VARCHAR2(100) NOT NULL,address_postal_code VARCHAR2(6) NOT NULL,PRIMARY KEY (addressemp_id))";
		String create7 = "CREATE TABLE employee_address_ids (addressemp_id INT NOT NULL,employee_id INT NOT NULL,PRIMARY KEY (addressemp_id),FOREIGN KEY (employee_id) REFERENCES employee(employee_id),FOREIGN KEY (addressemp_id) REFERENCES address_of_employee(addressemp_id))";
		String create8 = "CREATE TABLE customer_loyalty_card (customer_id INT NOT NULL,first_name VARCHAR2(50) NOT NULL,middle_name VARCHAR2(50),last_name VARCHAR2(50) NOT NULL,phone_number INT NOT NULL,email_address VARCHAR2(100) NOT NULL,birth_date DATE NOT NULL,gender VARCHAR(10),account_points SMALLINT DEFAULT 0,date_acquired DATE NOT NULL,last_redeemed DATE,PRIMARY KEY(customer_id))";
		String create9 = "CREATE TABLE address_of_customer (addresscustomer_id INT NOT NULL,address_street_number INT NOT NULL,address_street VARCHAR2(100) NOT NULL,address_city VARCHAR2(100) NOT NULL,address_province VARCHAR2(100) NOT NULL,address_postal_code VARCHAR2(6) NOT NULL,PRIMARY KEY (addresscustomer_id))";
		String create10 = "CREATE TABLE customer_address_ids (customer_id INT NOT NULL,addresscustomer_id INT NOT NULL,PRIMARY KEY (addresscustomer_id),FOREIGN KEY (customer_id) REFERENCES customer_loyalty_card(customer_id),FOREIGN KEY (addresscustomer_id) REFERENCES address_of_customer(addresscustomer_id))";
		String create11 = "CREATE TABLE orders (receipt_number INT NOT NULL,total_amount NUMBER(10, 2) NOT NULL,promotions VARCHAR2(100),cash_payment CHAR NOT NULL,card_payment CHAR NOT NULL,order_time TIMESTAMP NOT NULL,PRIMARY KEY (receipt_number))";
		String create12 = "CREATE TABLE orders_in_store (receipt_number INT NOT NULL,store_id INT NOT NULL,PRIMARY KEY (receipt_number),FOREIGN KEY (receipt_number) REFERENCES orders(receipt_number),FOREIGN KEY (store_id) REFERENCES stores(store_id))";
		String create13 = "CREATE TABLE customer_order (receipt_number INT NOT NULL,customer_id INT NOT NULL,PRIMARY KEY (receipt_number),FOREIGN KEY (receipt_number) REFERENCES orders(receipt_number),FOREIGN KEY (customer_id) REFERENCES customer_loyalty_card(customer_id))";
		String create14 = "CREATE TABLE product_order (product_id INT NOT NULL,receipt_number INT NOT NULL,item_amount INT DEFAULT 0,PRIMARY KEY (product_id, receipt_number),FOREIGN KEY (product_id) REFERENCES product(product_id),FOREIGN KEY (receipt_number) REFERENCES orders(receipt_number))";
		String create15 = "CREATE TABLE card_payment (card_number NUMBER(16, 0) NOT NULL,payment_network VARCHAR2(20) NOT NULL,card_type CHAR NOT NULL,cvv_number NUMBER(3, 0) NOT NULL,expiry_date DATE NOT NULL,PRIMARY KEY (card_number))";
		String create16 = "CREATE TABLE card_payment_order (receipt_number INT NOT NULL,card_number NUMBER(16, 0) NOT NULL,PRIMARY KEY (receipt_number),FOREIGN KEY (receipt_number) REFERENCES orders(receipt_number),FOREIGN KEY (card_number) REFERENCES card_payment(card_number))";
		String create17 = "CREATE TABLE debit_card (card_number NUMBER(16, 0) NOT NULL,chequing_account_number NUMBER(11, 0) NOT NULL,bank_name VARCHAR2(50) NOT NULL,PRIMARY KEY (card_number, chequing_account_number),FOREIGN KEY (card_number) REFERENCES card_payment (card_number))";
		String create18 = "CREATE TABLE credit_card (card_number NUMBER(16, 0) NOT NULL,creditor_number NUMBER(20, 0) NOT NULL,creditor_name VARCHAR2(50) NOT NULL,PRIMARY KEY (card_number, creditor_number),FOREIGN KEY (card_number) REFERENCES card_payment(card_number))";

		String[] createStringsArr = {create1, create2, create3, create4, create5, create6,
									create7, create8, create9, create10, create11, create12,
									create13, create14, create15, create16, create17, create18};
		
		try {
			for (String item: createStringsArr) {
				Statement statement = connection.createStatement();
				statement.executeUpdate(item);
				statement.close();
			};
			System.out.println("All tables successfully created.\n\n");
		} catch (SQLException e) {
			System.out.println("There was a problem creating the table.");
			e.printStackTrace();
		}
	}

	public static void populateTables() {
		//String insertToStores = "INSERT INTO st7894 VALUES (2, 789465132, 50, 1200, 'dundas', 'toronto', 'on', 'l6p3l8')";
		String[] insertStringsArr = {};
		try {
			for (String item: insertStringsArr) {
				Statement statement = connection.createStatement();
				statement.executeUpdate(item);
				statement.close();
			};
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

		String drop1 = "DROP TABLE credit_card";
		String drop2 = "DROP TABLE debit_card";
		String drop3 = "DROP TABLE card_payment_order";
		String drop4 = "DROP TABLE card_payment";
		String drop5 = "DROP TABLE product_order";
		String drop6 = "DROP TABLE customer_order";
		String drop7 = "DROP TABLE orders_in_store";
		String drop8 = "DROP TABLE orders";
		String drop9 = "DROP TABLE customer_address_ids";
		String drop10 = "DROP TABLE address_of_customer";
		String drop11 = "DROP TABLE customer_loyalty_card";
		String drop12 = "DROP TABLE employee_address_ids";
		String drop13 = "DROP TABLE address_of_employee";
		String drop14 = "DROP TABLE employees_in_store";
		String drop15 = "DROP TABLE employee";
		String drop16 = "DROP TABLE products_in_store";
		String drop17 = "DROP TABLE product";
		String drop18 = "DROP TABLE stores";

		String[] dropStringsArr = {drop1, drop2, drop3, drop4, drop5, drop6,
				drop7, drop8, drop9, drop10, drop11, drop12,
				drop13, drop14, drop15, drop16, drop17, drop18};
		
		try {
			for (String item: dropStringsArr) {
				Statement statement = connection.createStatement();
				statement.executeUpdate(item);
				statement.close();
			};
			System.out.println("All tables successfully dropped.\n\n");
		} catch (SQLException e) {
			System.out.println("There was a problem dropping the tables.");
			e.printStackTrace();
		}
	}
}
