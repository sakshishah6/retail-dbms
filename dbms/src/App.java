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
		};
	};

	public static void menu() throws SQLException {
		
		int choice;

		while (true) {
			System.out.println("\n\nWelcome to the Online Store Database!\n");
			System.out.println("Enter a number to make your selection:");
			System.out.println("1: Create Tables");
			System.out.println("2: Populate Tables");
			System.out.println("3: View All Tables");
			System.out.println("4: Run a Query");
			System.out.println("5: Insert/Update a Record");
			System.out.println("6: Drop Tables");
			System.out.println("7: Quit\n");
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
				runQuery();
				break;
				
			case 5:
				modifyRecord();
				break;

			case 6:
				dropTables();
				break;

			case 7:
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
				System.out.println("\nInvalid choice! Please make a valid choice.\n");
			}
		}
	}

	public static void createTables() {

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
			System.out.println("\nAll tables successfully created.\n\n");
		} catch (SQLException e) {
			System.out.println("\nThere was a problem creating the table.");
			e.printStackTrace();
		}
	}

	public static void populateTables() {

		String insert1 = "INSERT INTO Stores (store_id, phone_number, employee_count, address_street_number, address_street, address_city, address_province, address_postal_code) VALUES (1, 1234567890, 20, 1509, 'Queen St', 'Toronto', 'Ontario', 'M5B3N1')";
		String insert2 = "INSERT INTO Stores(store_id, phone_number, employee_count, address_street_number, address_street, address_city, address_province, address_postal_code) VALUES(2, 4165896325, 34, 4650, 'Eglington Ave W', 'Toronto', 'Ontario', 'M9A0E3')";
		String insert3 = "INSERT INTO Stores(store_id, phone_number, employee_count, address_street_number, address_street, address_city, address_province, address_postal_code) VALUES(3, 4378952688, 26, 2945, 'Abbot Dr', 'Halifax', 'Nova Scotia', 'S2F8T3')";
		String insert4 = "INSERT INTO Stores(store_id, phone_number, employee_count, address_street_number, address_street, address_city, address_province, address_postal_code) VALUES(4, 8657495102, 14, 879, 'St. John Street', 'Alida', 'Saskatchewan', 'S4P3Y2')";
		String insert5 = "INSERT INTO Product(product_id, product_name, clothing_type, price, brand, material, product_size, colour) VALUES(1, 'Nike Tech Fleece Joggers', 'Pants', 79.99, 'Nike', 'polyester', 'L', 'white')";
		String insert6 = "INSERT INTO Product(product_id, product_name, clothing_type, price, brand, material, product_size, colour) VALUES(2, 'Athletic Works Sweater', 'Sweaters', 45.85, 'Athletic Works', 'nylon', 'S', 'black')";
		String insert7 = "INSERT INTO Product(product_id, product_name, clothing_type, price, brand, material, product_size, colour) VALUES(3, 'T-shirt Dress', 'Dress', 15.89, 'George', 'cotton', 'M', 'red')";
		String insert8 = "INSERT INTO Product(product_id, product_name, clothing_type, price, brand, material, product_size, colour) VALUES(4, 'Parka Jacket', 'Coats and Jackets', 212.80, 'Point Zero', 'polyester, fur', 'L', 'black')";
		String insert9 = "INSERT INTO Product(product_id, product_name, clothing_type, price, brand, material, product_size, colour) VALUES(5, 'Plaid Shirt', 'Tops', 25.99, 'Banana Republic', 'flannel', 'M', 'blue')";
		String insert10 = "INSERT INTO Products_In_Store(product_id, store_id, quantity) VALUES(1, (SELECT store_id FROM Stores WHERE store_id = 1), 3)";
		String insert11 = "INSERT INTO Products_In_Store(product_id, store_id, quantity) VALUES(2, 2, 7)";
		String insert12 = "INSERT INTO Products_In_Store(product_id, store_id, quantity) VALUES(3, 4, 8)";
		String insert13 = "INSERT INTO Products_In_Store(product_id, store_id, quantity) VALUES(4, 4, 15)";
		String insert14 = "INSERT INTO Products_In_Store(product_id, store_id, quantity) VALUES(5, 3, 9)";
		String insert15 = "INSERT INTO Employee(employee_id, first_name, last_name, department, phone_number, email_address, employee_role, salary, bank_account_number, birth_date, date_hired) VALUES(12,'Ian', 'Thomas', 'Customer Service', '4165890000', 'ian.thomas@storename.ca', 'manager', 852.32, 833, TO_DATE('1991-03-01', 'YYYY-MM-DD'), TO_DATE('2010-12-01', 'YYYY-MM-DD'))";
		String insert16 = "INSERT INTO Employee(employee_id, first_name, last_name, department, phone_number, email_address, employee_role, salary, bank_account_number, birth_date, date_hired) VALUES(10,'Kevin', 'Liu', 'Custodial', '4168930100', 'kevin.liu@storename.ca', 'Custodian', 683.43, 090, TO_DATE('1973-04-01', 'YYYY-MM-DD'), TO_DATE('2011-08-11', 'YYYY-MM-DD'))";
		String insert17 = "INSERT INTO Employee(employee_id, first_name, last_name, department, phone_number, email_address, employee_role, salary, bank_account_number, birth_date, date_hired) VALUES(22, 'Bryan', 'Schumaker', 'Sales', '4168995123', 'bryan.schumaker@storename.ca', 'Sales Representative', 900.15, 832, TO_DATE('1988-01-01', 'YYYY-MM-DD'), TO_DATE('2014-06-13', 'YYYY-MM-DD'))";
		String insert18 = "INSERT INTO Employee(employee_id, first_name, last_name, department, phone_number, email_address, employee_role, salary, bank_account_number, birth_date, date_hired) VALUES(23,'Dylan', 'Graham', 'Customer Service', '6473990000', 'dylan.graham@storename.ca', 'Cashier', 750.12, 833, TO_DATE('1991-03-01', 'YYYY-MM-DD'), TO_DATE('2010-12-01', 'YYYY-MM-DD'))";
		String insert19 = "INSERT INTO Employees_In_Store(employee_id, store_id) VALUES(12, 1)";
		String insert20 = "INSERT INTO Employees_In_Store(employee_id, store_id) VALUES(10, (SELECT store_id FROM Stores WHERE store_id = 1))";
		String insert21 = "INSERT INTO Employees_In_Store(employee_id, store_id) VALUES(22, (SELECT store_id FROM Stores WHERE store_id = 2))";
		String insert22 = "INSERT INTO Employees_In_Store(employee_id, store_id) VALUES(23, (SELECT store_id FROM Stores WHERE store_id = 2))";
		String insert23 = "INSERT INTO Address_Of_Employee(addressEmp_id, address_street_number, address_street, address_city, address_province, address_postal_code) VALUES(13, 4650, 'Eglington Ave W', 'Toronto', 'Ontario', 'M9A0E3')";
		String insert24 = "INSERT INTO Address_Of_Employee(addressEmp_id, address_street_number, address_street, address_city, address_province, address_postal_code) VALUES(19, 2682, 'Bay St', 'Toronto', 'Ontario', 'M5J2R8')";
		String insert25 = "INSERT INTO Address_Of_Employee(addressEmp_id, address_street_number, address_street, address_city, address_province, address_postal_code) VALUES(23, 1174, 'Tycos Dr', 'Toronto', 'Ontario', 'M5T1T4')";
		String insert26 = "INSERT INTO Address_Of_Employee(addressEmp_id, address_street_number, address_street, address_city, address_province, address_postal_code) VALUES(29, 3121, 'Halsey Ave', 'Toronto', 'Ontario', 'M3B2W6')";
		String insert27 = "INSERT INTO Employee_Address_Ids(addressEmp_id, employee_id) VALUES (13, 12)";
		String insert28 = "INSERT INTO Employee_Address_Ids(addressEmp_id, employee_id) VALUES (19, 10)";
		String insert29 = "INSERT INTO Employee_Address_Ids(addressEmp_id, employee_id) VALUES (23, 22)";
		String insert30 = "INSERT INTO Employee_Address_Ids(addressEmp_id, employee_id) VALUES (29, 23)";
		String insert31 = "INSERT INTO Customer_Loyalty_Card (customer_id, first_name, last_name, phone_number, email_address, birth_date, gender, account_points, date_acquired, last_redeemed) VALUES (165, 'Violet', 'Green', 8768189937, 'vigreen@gmail.com', TO_DATE('2003-06-05', 'YYYY-MM-DD'), 'female', 55, TO_DATE('2021-11-09', 'YYYY-MM-DD'), TO_DATE('2021-12-10', 'YYYY-MM-DD'))";
		String insert32 = "INSERT INTO Customer_Loyalty_Card (customer_id, first_name, last_name, phone_number, email_address, birth_date, gender, account_points, date_acquired, last_redeemed) VALUES (210, 'Jayden', 'Brown', 9551230876, 'jjbrwn@outlook.com', TO_DATE('1993-09-15', 'YYYY-MM-DD'), 'male', 32, TO_DATE('2020-10-19', 'YYYY-MM-DD'), TO_DATE('2022-04-10', 'YYYY-MM-DD'))";
		String insert33 = "INSERT INTO Customer_Loyalty_Card (customer_id, first_name, middle_name, last_name, phone_number, email_address, birth_date, gender, account_points, date_acquired, last_redeemed) VALUES (47, 'Akina', 'Ladner', 'Rose', 4168596588, 'akina.lr@yahoo.ca', TO_DATE('1997-05-05', 'YYYY-MM-DD'), 'Female', 10, TO_DATE('2022-10-01', 'YYYY-MM-DD'), TO_DATE('2022-10-02', 'YYYY-MM-DD'))";
		String insert34 = "INSERT INTO Customer_Loyalty_Card (customer_id, first_name, middle_name, last_name, phone_number, email_address, birth_date, gender, account_points, date_acquired, last_redeemed) VALUES (19, 'Michael', 'A', 'Satyer', 4378881695, 'mike.sat@hotmail.com', TO_DATE('1963-04-19', 'YYYY-MM-DD'), 'Male', 26, TO_DATE('2016-08-11', 'YYYY-MM-DD'), TO_DATE('2020-10-21', 'YYYY-MM-DD'))";
		String insert35 = "INSERT INTO Address_Of_Customer (addressCustomer_id, address_street_number, address_street, address_city, address_province, address_postal_code) VALUES (12, 50, 'Dundas St', 'Toronto', 'Ontario', 'M5B2L1')";
		String insert36 = "INSERT INTO Address_Of_Customer (addressCustomer_id, address_street_number, address_street, address_city, address_province, address_postal_code) VALUES (10, 3, 'Moore Cir', 'Saskatoon', 'Saskatchewan', 'W9G1D4')";
		String insert37 = "INSERT INTO Address_Of_Customer (addressCustomer_id, address_street_number, address_street, address_city, address_province, address_postal_code) VALUES (11, 1685, 'King St', 'Whitby', 'Ontario', 'B9K6E2')";
		String insert38 = "INSERT INTO Address_Of_Customer (addressCustomer_id, address_street_number, address_street, address_city, address_province, address_postal_code) VALUES (9, 965, 'Front St', 'Halifax', 'Nova Scotia', 'S9G6E2')";
		String insert39 = "INSERT INTO Customer_Address_Ids (addressCustomer_id, customer_id) VALUES (12, 165)";
		String insert40 = "INSERT INTO Customer_Address_Ids (addressCustomer_id, customer_id) VALUES (11, 210)";
		String insert41 = "INSERT INTO Customer_Address_Ids (addressCustomer_id, customer_id) VALUES (10, 47)";
		String insert42 = "INSERT INTO Customer_Address_Ids (addressCustomer_id, customer_id) VALUES (9, 19)";
		String insert43 = "INSERT INTO Orders (receipt_number, total_amount, cash_payment, card_payment, order_time) VALUES (999, 79.99, 'N', 'Y', TO_TIMESTAMP('2022-09-25 7:10:01', 'YYYY-MM-DD HH:MI:SS'))";
		String insert44 = "INSERT INTO Orders (receipt_number, total_amount, cash_payment, card_payment, order_time) VALUES (1069, 16.36, 'N', 'Y', TO_TIMESTAMP('2021-10-05 5:51:39', 'YYYY-MM-DD HH:MI:SS'))";
		String insert45 = "INSERT INTO Orders (receipt_number, total_amount, cash_payment, card_payment, order_time) VALUES (56, 80.00, 'Y', 'N', TO_TIMESTAMP('2002-01-02 10:45:32', 'YYYY-MM-DD HH:MI:SS'))";
		String insert46 = "INSERT INTO Orders (receipt_number, total_amount, cash_payment, card_payment, order_time) VALUES (25, 159.80, 'Y', 'N', TO_TIMESTAMP('21-10-05 02:26:53', 'YYYY-MM-DD HH:MI:SS'))";
		String insert47 = "INSERT INTO Orders_In_Store (receipt_number, store_id) VALUES (999, 1)";
		String insert48 = "INSERT INTO Orders_In_Store (receipt_number, store_id) VALUES (1069, 2)";
		String insert49 = "INSERT INTO Orders_In_Store (receipt_number, store_id) VALUES (56, 2)";
		String insert50 = "INSERT INTO Orders_In_Store (receipt_number, store_id) VALUES (25, 3)";
		String insert51 = "INSERT INTO Customer_Order (receipt_number, customer_id) VALUES (999, 165)";
		String insert52 = "INSERT INTO Customer_Order (receipt_number, customer_id) VALUES (1069, 165)";
		String insert53 = "INSERT INTO Product_Order (product_id, receipt_number, item_amount) VALUES (1, 999, 1)";
		String insert54 = "INSERT INTO Product_Order (product_id, receipt_number, item_amount) VALUES (2, 1069, 1)";
		String insert55 = "INSERT INTO Product_Order (product_id, receipt_number, item_amount) VALUES (1, 56, 1)";
		String insert56 = "INSERT INTO Product_Order (product_id, receipt_number, item_amount) VALUES (5, 25, 1)";
		String insert57 = "INSERT INTO Card_Payment (card_number, payment_network, card_type, cvv_number, expiry_date) VALUES (5098123456789012, 'VISA', 'C', '010', TO_DATE('2024-10-10', 'YYYY-MM-DD'))";
		String insert58 = "INSERT INTO Card_Payment (card_number, payment_network, card_type, cvv_number, expiry_date) VALUES (9654826410230675, 'MASTERCARD', 'D', 365, TO_DATE('2027-08-13', 'YYYY-MM-DD'))";
		String insert59 = "INSERT INTO Card_Payment (card_number, payment_network, card_type, cvv_number, expiry_date) VALUES (6445164684656568, 'VISA', 'D', 365, TO_DATE('2027-08-13', 'YYYY-MM-DD'))";
		String insert60 = "INSERT INTO Card_Payment_Order (receipt_number, card_number) VALUES (999, 5098123456789012)";
		String insert61 = "INSERT INTO Card_Payment_Order (receipt_number, card_number) VALUES (1069, 9654826410230675)";
		String insert62 = "INSERT INTO Card_Payment_Order (receipt_number, card_number) VALUES (56, 6445164684656568)";
		String insert63 = "INSERT INTO Debit_Card (card_number, chequing_account_number, bank_name) VALUES (6445164684656568, 11111111111, 'CIBC')";
		String insert64 = "INSERT INTO Debit_Card (card_number, chequing_account_number, bank_name) VALUES (9654826410230675, 91111111111, 'CIBC')";
		String insert65 = "INSERT INTO Credit_Card (card_number, creditor_number, creditor_name) VALUES (5098123456789012, 98746123, 'TD Credit')";
		
		String[] insertStringsArr = {insert1, insert2, insert3, insert4, insert5, insert6, insert7, insert8, insert9, insert10, 
									insert11, insert12, insert13, insert14, insert15, insert16, insert17, insert18, insert19, insert20, 
									insert21, insert22, insert23, insert24, insert25, insert26, insert27, insert28, insert29, insert30, 
									insert31, insert32, insert33, insert34, insert35, insert36, insert37, insert38, insert39, insert40, 
									insert41, insert42, insert43, insert44, insert45, insert46, insert47, insert48, insert49, insert50, 
									insert51, insert52, insert53, insert54, insert55, insert56, insert57, insert58, insert59, insert60, 
									insert61, insert62, insert63, insert64, insert65};
		
		try {
			for (String item: insertStringsArr) {
				Statement statement = connection.createStatement();
				statement.executeUpdate(item);
				statement.close();
			};
			System.out.println("\nAll tables successfully populated.\n\n");
		} catch (SQLException e) {
			System.out.println("\nThere was a problem inserting these values into the table.");
			e.printStackTrace();
		}
	}

	public static void viewTables() {
		String view1 = "select * from stores";
		String view2 = "select * from product";
		String[] viewStringsArr = {view1, view2};
		try {
			for (String item: viewStringsArr) {
				Statement statement = connection.createStatement();
				statement.executeUpdate(item);
				//print table to console
				statement.close();
			};
		} catch (SQLException e) {
			System.out.println("\nThere was a problem.");
			e.printStackTrace();
		}
	}
	
	public static void runQuery() {

		// give a list of queries that the user can select from (queries described using
		// words)
		// user selects a number and then the query will run
		// query in sql as well as the results will be displayed
	}

	public static void modifyRecord() {
		System.out.println("\nSQL statement should be in one of the following forms:");
		System.out.println("\t\"INSERT INTO table (column1, column2, ...) VALUES (value1, value2, ...)\"  OR");
		System.out.println("\t\"UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition\"\n");
		System.out.println("\nEnter a statement to add/update a record:\n");
		String record = "";
		scanner.nextLine();
		record = scanner.nextLine();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(record);
			statement.close();
			System.out.println("\nRecord successfully updated in the database.\n\n");
		} catch (SQLException e) {
			System.out.println("\nThere was a problem with updating this record.");
			e.printStackTrace();
		}
	}
	
	public static void dropTables() {

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
