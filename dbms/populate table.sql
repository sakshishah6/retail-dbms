INSERT INTO Stores(store_id, phone_number, employee_count, address_street_number, address_street, address_city, address_province, address_postal_code)
VALUES(1, 1234567890, 20, 1509, 'Queen St', 'Toronto', 'Ontario', 'M5B3N1');

INSERT INTO Stores(store_id, phone_number, employee_count, address_street_number, address_street, address_city, address_province, address_postal_code)
VALUES(2, 4165896325, 34, 4650, 'Eglington Ave W', 'Toronto', 'Ontario', 'M9A0E3');

INSERT INTO Stores(store_id, phone_number, employee_count, address_street_number, address_street, address_city, address_province, address_postal_code)
VALUES(3, 4378952688, 26, 2945, 'Abbot Dr', 'Halifax', 'Nova Scotia', 'S2F8T3');


INSERT INTO Stores(store_id, phone_number, employee_count, address_street_number, address_street, address_city, address_province, address_postal_code)
VALUES(4, 8657495102, 14, 879, 'St. John Street', 'Alida', 'Saskatchewan', 'S4P3Y2');

INSERT INTO Product(product_id, product_name, clothing_type, price, brand, material, product_size, colour)
VALUES(1, 'Nike Tech Fleece Joggers', 'Pants', 79.99, 'Nike', 'polyester', 'L', 'white');


INSERT INTO Product(product_id, product_name, clothing_type, price, brand, material, product_size, colour)
VALUES(2, 'Athletic Works Sweater', 'Sweaters', 45.85, 'Athletic Works', 'nylon', 'S', 'black');

INSERT INTO Product(product_id, product_name, clothing_type, price, brand, material, product_size, colour)
VALUES(3, 'T-shirt Dress', 'Dress', 15.89, 'George', 'cotton', 'M', 'red');

INSERT INTO Product(product_id, product_name, clothing_type, price, brand, material, product_size, colour)
VALUES(4, 'Parka Jacket', 'Coats and Jackets', 212.80, 'Point Zero', 'polyester, fur', 'L', 'black');

INSERT INTO Product(product_id, product_name, clothing_type, price, brand, material, product_size, colour)
VALUES(5, 'Plaid Shirt', 'Tops', 25.99, 'Banana Republic', 'flannel', 'M', 'blue');






INSERT INTO Products_In_Store(product_id, store_id, quantity)
VALUES(1, (SELECT store_id FROM Stores WHERE store_id = 1), 3);

INSERT INTO Products_In_Store(product_id, store_id, quantity)
VALUES(2, 2, 7);

INSERT INTO Products_In_Store(product_id, store_id, quantity)
VALUES(3, 4, 8);

INSERT INTO Products_In_Store(product_id, store_id, quantity)
VALUES(4, 4, 15);

INSERT INTO Products_In_Store(product_id, store_id, quantity)
VALUES(5, 3, 9);





INSERT INTO Employee(employee_id, first_name, last_name, department, phone_number, email_address, employee_role, salary, bank_account_number, birth_date, date_hired)
VALUES(12,'Ian', 'Thomas', 'Customer Service', '4165890000', 'ian.thomas@storename.ca', 'manager', 852.32, 833, TO_DATE('1991-03-01', 'YYYY-MM-DD'), 
TO_DATE('2010-12-01', 'YYYY-MM-DD'));


INSERT INTO Employee(employee_id, first_name, last_name, department, phone_number, email_address, employee_role, salary, bank_account_number, birth_date, date_hired)
VALUES(10,'Kevin', 'Liu', 'Custodial', '4168930100', 'kevin.liu@storename.ca', 'Custodian', 683.43, 090, TO_DATE('1973-04-01', 'YYYY-MM-DD'), 
TO_DATE('2011-08-11', 'YYYY-MM-DD'));


INSERT INTO Employee(employee_id, first_name, last_name, department, phone_number, email_address, employee_role, salary, bank_account_number, birth_date, date_hired)
VALUES(22, 'Bryan', 'Schumaker', 'Sales', '4168995123', 'bryan.schumaker@storename.ca', 'Sales Representative', 900.15, 832, TO_DATE('1988-01-01', 'YYYY-MM-DD'), 
TO_DATE('2014-06-13', 'YYYY-MM-DD'));

INSERT INTO Employee(employee_id, first_name, last_name, department, phone_number, email_address, employee_role, salary, bank_account_number, birth_date, date_hired)
VALUES(23,'Dylan', 'Graham', 'Customer Service', '6473990000', 'dylan.graham@storename.ca', 'Cashier', 750.12, 833, TO_DATE('1991-03-01', 'YYYY-MM-DD'), 
TO_DATE('2010-12-01', 'YYYY-MM-DD'));

INSERT INTO Employees_In_Store(employee_id, store_id)
VALUES(12, 1);

INSERT INTO Employees_In_Store(employee_id, store_id)
VALUES(10, (SELECT store_id FROM Stores WHERE store_id = 1));

INSERT INTO Employees_In_Store(employee_id, store_id)
VALUES(22, (SELECT store_id FROM Stores WHERE store_id = 2));


INSERT INTO Employees_In_Store(employee_id, store_id)
VALUES(23, (SELECT store_id FROM Stores WHERE store_id = 2));

INSERT INTO Address_Of_Employee(addressEmp_id, address_street_number, address_street, address_city, address_province, address_postal_code)
VALUES(13, 4650, 'Eglington Ave W', 'Toronto', 'Ontario', 'M9A0E3');

INSERT INTO Address_Of_Employee(addressEmp_id, address_street_number, address_street, address_city, address_province, address_postal_code)
VALUES(19, 2682, 'Bay St', 'Toronto', 'Ontario', 'M5J2R8');


INSERT INTO Address_Of_Employee(addressEmp_id, address_street_number, address_street, address_city, address_province, address_postal_code)
VALUES(23, 1174, 'Tycos Dr', 'Toronto', 'Ontario', 'M5T1T4');


INSERT INTO Address_Of_Employee(addressEmp_id, address_street_number, address_street, address_city, address_province, address_postal_code)
VALUES(29, 3121, 'Halsey Ave', 'Toronto', 'Ontario', 'M3B2W6');


INSERT INTO Employee_Address_Ids(addressEmp_id, employee_id)
VALUES (13, 12);

INSERT INTO Employee_Address_Ids(addressEmp_id, employee_id)
VALUES (19, 10);

INSERT INTO Employee_Address_Ids(addressEmp_id, employee_id)
VALUES (23, 22);

INSERT INTO Employee_Address_Ids(addressEmp_id, employee_id)
VALUES (29, 23);

INSERT INTO Customer_Loyalty_Card (customer_id, first_name, last_name, phone_number, email_address, birth_date, gender, account_points, date_acquired, last_redeemed)
VALUES (165, 'Violet', 'Green', 8768189937, 'vigreen@gmail.com', TO_DATE('2003-06-05', 'YYYY-MM-DD'), 'female', 55, TO_DATE('2021-11-09', 'YYYY-MM-DD'), TO_DATE('2021-12-10', 'YYYY-MM-DD'));

INSERT INTO Customer_Loyalty_Card (customer_id, first_name, last_name, phone_number, email_address, birth_date, gender, account_points, date_acquired, last_redeemed)
VALUES (210, 'Jayden', 'Brown', 9551230876, 'jjbrwn@outlook.com', TO_DATE('1993-09-15', 'YYYY-MM-DD'), 'male', 32, TO_DATE('2020-10-19', 'YYYY-MM-DD'), TO_DATE('2022-04-10', 'YYYY-MM-DD'));

INSERT INTO Customer_Loyalty_Card (customer_id, first_name, middle_name, last_name, phone_number, email_address, birth_date, gender, account_points, date_acquired, last_redeemed)
VALUES (47, 'Akina', 'Ladner', 'Rose', 4168596588, 'akina.lr@yahoo.ca', TO_DATE('1997-05-05', 'YYYY-MM-DD'), 'Female', 10, TO_DATE('2022-10-01', 'YYYY-MM-DD'), TO_DATE('2022-10-02', 'YYYY-MM-DD'));

INSERT INTO Customer_Loyalty_Card (customer_id, first_name, middle_name, last_name, phone_number, email_address, birth_date, gender, account_points, date_acquired, last_redeemed)
VALUES (19, 'Michael', 'A', 'Satyer', 4378881695, 'mike.sat@hotmail.com', TO_DATE('1963-04-19', 'YYYY-MM-DD'), 'Male', 26, TO_DATE('2016-08-11', 'YYYY-MM-DD'), TO_DATE('2020-10-21', 'YYYY-MM-DD'));


INSERT INTO Address_Of_Customer (addressCustomer_id, address_street_number, address_street, address_city, address_province, address_postal_code)
VALUES (12, 50, 'Dundas St', 'Toronto', 'Ontario', 'M5B2L1');


INSERT INTO Address_Of_Customer (addressCustomer_id, address_street_number, address_street, address_city, address_province, address_postal_code)
VALUES (10, 3, 'Moore Cir', 'Saskatoon', 'Saskatchewan', 'W9G1D4');


INSERT INTO Address_Of_Customer (addressCustomer_id, address_street_number, address_street, address_city, address_province, address_postal_code)
VALUES (11, 1685, 'King St', 'Whitby', 'Ontario', 'B9K6E2');

INSERT INTO Address_Of_Customer (addressCustomer_id, address_street_number, address_street, address_city, address_province, address_postal_code)
VALUES (9, 965, 'Front St', 'Halifax', 'Nova Scotia', 'S9G6E2');


INSERT INTO Customer_Address_Ids (addressCustomer_id, customer_id)
VALUES (12, 165);

INSERT INTO Customer_Address_Ids (addressCustomer_id, customer_id)
VALUES (11, 210);

INSERT INTO Customer_Address_Ids (addressCustomer_id, customer_id)
VALUES (10, 47);

INSERT INTO Customer_Address_Ids (addressCustomer_id, customer_id)
VALUES (9, 19);


INSERT INTO Orders (receipt_number, total_amount, cash_payment, card_payment, order_time)
VALUES (999, 79.99, 'N', 'Y', TO_TIMESTAMP('2022-09-25 7:10:01', 'YYYY-MM-DD HH:MI:SS'));

INSERT INTO Orders (receipt_number, total_amount, cash_payment, card_payment, order_time)
VALUES (1069, 16.36, 'N', 'Y', TO_TIMESTAMP('2021-10-05 5:51:39', 'YYYY-MM-DD HH:MI:SS'));

INSERT INTO Orders (receipt_number, total_amount, cash_payment, card_payment, order_time)
VALUES (56, 80.00, 'Y', 'N', TO_TIMESTAMP('2002-01-02 10:45:32', 'YYYY-MM-DD HH:MI:SS'));

INSERT INTO Orders (receipt_number, total_amount, cash_payment, card_payment, order_time)
VALUES (25, 159.80, 'Y', 'N', TO_TIMESTAMP('21-10-05 02:26:53', 'YYYY-MM-DD HH:MI:SS'));


INSERT INTO Orders_In_Store (receipt_number, store_id)
VALUES (999, 1);


INSERT INTO Orders_In_Store (receipt_number, store_id)
VALUES (1069, 2);


INSERT INTO Orders_In_Store (receipt_number, store_id)
VALUES (56, 2);


INSERT INTO Orders_In_Store (receipt_number, store_id)
VALUES (25, 3);


INSERT INTO Customer_Order (receipt_number, customer_id)
VALUES (999, 165);

INSERT INTO Customer_Order (receipt_number, customer_id)
VALUES (1069, 165);



INSERT INTO Product_Order (product_id, receipt_number, item_amount)
VALUES (1, 999, 1);



INSERT INTO Product_Order (product_id, receipt_number, item_amount)
VALUES (2, 1069, 1);


INSERT INTO Product_Order (product_id, receipt_number, item_amount)
VALUES (1, 56, 1);


INSERT INTO Product_Order (product_id, receipt_number, item_amount)
VALUES (5, 25, 1);


INSERT INTO Card_Payment (card_number, payment_network, card_type, cvv_number, expiry_date)
VALUES (5098123456789012, 'VISA', 'C', '010', TO_DATE('2024-10-10', 'YYYY-MM-DD'));

INSERT INTO Card_Payment (card_number, payment_network, card_type, cvv_number, expiry_date)
VALUES (9654826410230675, 'MASTERCARD', 'D', 365, TO_DATE('2027-08-13', 'YYYY-MM-DD'));

INSERT INTO Card_Payment (card_number, payment_network, card_type, cvv_number, expiry_date)
VALUES (6445164684656568, 'VISA', 'D', 365, TO_DATE('2027-08-13', 'YYYY-MM-DD'));


INSERT INTO Card_Payment_Order (receipt_number, card_number)
VALUES (999, 5098123456789012);



INSERT INTO Card_Payment_Order (receipt_number, card_number)
VALUES (1069, 9654826410230675);


INSERT INTO Card_Payment_Order (receipt_number, card_number)
VALUES (56, 6445164684656568);




INSERT INTO Debit_Card (card_number, chequing_account_number, bank_name)
VALUES (6445164684656568, 11111111111, 'CIBC');

INSERT INTO Debit_Card (card_number, chequing_account_number, bank_name)
VALUES (9654826410230675, 91111111111, 'CIBC');



INSERT INTO Credit_Card (card_number, creditor_number, creditor_name)
VALUES (5098123456789012, 98746123, 'TD Credit');


