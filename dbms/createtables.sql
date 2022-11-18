CREATE TABLE Stores (  
    store_id				INT				        NOT NULL,  
    phone_number			INT				        NOT NULL,  
    employee_count			INT				        NOT NULL,  
    address_street_number	INT				        NOT NULL,  
    address_street			VARCHAR2(100)		    NOT NULL,  
    address_city			VARCHAR2(100)		    NOT NULL,  
    address_province		VARCHAR2(100)		    NOT NULL,  
    address_postal_code		VARCHAR2(6)				NOT NULL,  
    PRIMARY KEY (store_id)  
  
);

CREATE TABLE Product(  
    product_id	            INT			               NOT NULL,  
    product_name	        VARCHAR2(100)	           NOT NULL,  
    clothing_type           VARCHAR2(100)	           NOT NULL,  
    price                   NUMBER(5,2)	               NOT NULL,  
    brand                   VARCHAR2(100)	           NOT NULL,  
    material                VARCHAR2(100)	           NOT NULL,  
    product_size            CHAR			           NOT NULL,   
    colour                  VARCHAR2(100)	           NOT NULL,   
    product_desc            VARCHAR2(200),  
    PRIMARY KEY (product_id)  
);

CREATE TABLE Products_In_Store (  
    product_id              INT                     NOT NULL,  
    store_id                INT                     NOT NULL,  
    quantity		        INT			            NOT NULL,  
    PRIMARY KEY (product_id, store_id),  
    FOREIGN KEY (product_id) REFERENCES Product(product_id),  
    FOREIGN KEY (store_id) REFERENCES Stores(store_id)  
  
);

CREATE TABLE Employee (  
	employee_id			    INT				    NOT NULL,  
	first_name			    VARCHAR2(50)		NOT NULL,  
    middle_name			    VARCHAR2(50),  
    last_name			    VARCHAR2(50)		NOT NULL,  
    department			    VARCHAR2(50)		NOT NULL,  
	phone_number		    INT				    NOT NULL,  
    email_address		    VARCHAR2(50)		NOT NULL,  
    employee_role		    VARCHAR2(50)		NOT NULL,  
    salary				    FLOAT			    NOT NULL,  
    bank_account_number	    NUMBER			    NOT NULL,  
    birth_date			    DATE				NOT NULL,  
    date_hired			    DATE				NOT NULL,  
    PRIMARY KEY (employee_id)  
);

CREATE TABLE Employees_In_Store(  
    employee_id			    INT				    NOT NULL,  
    store_id				INT				    NOT NULL,  
    PRIMARY KEY (employee_id),  
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id),  
    FOREIGN KEY (store_id) REFERENCES Stores(store_id)  
);

CREATE TABLE Address_Of_Employee( 
    addressEmp_id            INT                 NOT NULL, 
    address_street_number	 INT				 NOT NULL, 
    address_street			VARCHAR2(100)		NOT NULL, 
    address_city			VARCHAR2(100)		NOT NULL, 
    address_province		VARCHAR2(100)		NOT NULL, 
    address_postal_code		VARCHAR2(6)			NOT NULL, 
    PRIMARY KEY (addressEmp_id) 
);

CREATE TABLE Employee_Address_Ids( 
    addressEmp_id            INT                 NOT NULL, 
    employee_id			     INT				 NOT NULL, 
    PRIMARY KEY (addressEmp_id), 
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id), 
    FOREIGN KEY (addressEmp_id) REFERENCES Address_Of_Employee(addressEmp_id) 
);

CREATE TABLE Customer_Loyalty_Card(  
    customer_id	            INT	                NOT NULL,  
    first_name	            VARCHAR2(50)        NOT NULL,  
    middle_name	            VARCHAR2(50),  
    last_name		        VARCHAR2(50)        NOT NULL,  
    phone_number	        INT		            NOT NULL,  
    email_address	        VARCHAR2(100)       NOT NULL,  
    birth_date	            DATE                NOT NULL,  
    gender		            VARCHAR(10),  
    account_points	        SMALLINT	        DEFAULT 0,  
    date_acquired           DATE	            NOT NULL,  
    last_redeemed	        DATE,  
    PRIMARY KEY(customer_id)  
      
);

CREATE TABLE Address_Of_Customer(  
    addressCustomer_id		INT				    NOT NULL,  
    address_street_number   INT				    NOT NULL,  
    address_street			VARCHAR2(100)		NOT NULL,  
    address_city			VARCHAR2(100)		NOT NULL,  
    address_province		VARCHAR2(100)		NOT NULL,  
    address_postal_code		VARCHAR2(6)			NOT NULL,  
    PRIMARY KEY (addressCustomer_id)  
      
);

CREATE TABLE Customer_Address_Ids(  
    customer_id			    INT				NOT NULL,  
    addressCustomer_id		INT				NOT NULL,  
    PRIMARY KEY (addressCustomer_id),  
    FOREIGN KEY (customer_id) REFERENCES Customer_Loyalty_Card(customer_id),  
    FOREIGN KEY (addressCustomer_id) REFERENCES Address_Of_Customer(addressCustomer_id)  
);

CREATE TABLE Orders(  
    receipt_number			INT				NOT NULL,  
    total_amount			NUMBER(10,2)	NOT NULL,  
    promotions			    VARCHAR2(100),  
    cash_payment			CHAR            NOT NULL,  
    card_payment            CHAR            NOT NULL,  
    order_time			TIMESTAMP			NOT NULL,  
    PRIMARY KEY (receipt_number)  
      
);

CREATE TABLE Orders_In_Store(  
    receipt_number			INT				NOT NULL,  
    store_id				INT				NOT NULL,  
    PRIMARY KEY (receipt_number),  
    FOREIGN KEY (receipt_number) REFERENCES Orders(receipt_number),  
    FOREIGN KEY (store_id) REFERENCES Stores(store_id)  
);

CREATE TABLE Customer_Order(  
    receipt_number			INT				NOT NULL,  
    customer_id	            INT	            NOT NULL,  
    PRIMARY KEY (receipt_number),  
    FOREIGN KEY (receipt_number) REFERENCES Orders(receipt_number),  
    FOREIGN KEY (customer_id) REFERENCES Customer_Loyalty_Card(customer_id)  
      
);

CREATE TABLE Product_Order(  
    product_id	            INT			NOT NULL,  
    receipt_number			INT			NOT NULL,  
    item_amount             INT         DEFAULT 0,  
    PRIMARY KEY (product_id, receipt_number),  
    FOREIGN KEY (product_id) REFERENCES Product(product_id),  
    FOREIGN KEY (receipt_number) REFERENCES Orders(receipt_number)  
      
);

CREATE TABLE Card_Payment(  
    card_number		    NUMBER(16,0)		NOT NULL,  
    payment_network	    VARCHAR2(20)		NOT NULL,  
    card_type   		CHAR 			    NOT NULL,  
    cvv_number		    NUMBER(3,0)		    NOT NULL,  
    expiry_date		    DATE				NOT NULL,  
    PRIMARY KEY (card_number)  
);

CREATE TABLE Card_Payment_Order(   
    receipt_number			INT			    NOT NULL,   
    card_number		    NUMBER(16,0)		NOT NULL,   
    PRIMARY KEY (receipt_number),   
    FOREIGN KEY (receipt_number) REFERENCES Orders(receipt_number),   
    FOREIGN KEY (card_number) REFERENCES Card_Payment(card_number)   
);

CREATE TABLE Debit_Card(  
    card_number			    NUMBER(16,0)		NOT NULL,  
    chequing_account_number	NUMBER(11,0)		NOT NULL,  
    bank_name				VARCHAR2(50)		NOT NULL,  
    PRIMARY KEY (card_number, chequing_account_number),  
    FOREIGN KEY (card_number) REFERENCES Card_Payment (card_number)  
      
);

CREATE TABLE Credit_Card(  
    card_number			NUMBER(16,0)		NOT NULL,  
    creditor_number		NUMBER(20,0)		NOT NULL,  
    creditor_name			VARCHAR2(50)		NOT NULL,  
    PRIMARY KEY (card_number, creditor_number),  
    FOREIGN KEY (card_number) REFERENCES Card_Payment(card_number)  
);

