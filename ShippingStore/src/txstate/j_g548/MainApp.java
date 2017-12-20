package txstate.j_g548;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * This is the main class of the ShippingStore database manager. It provides a
 * console for a user to use the 5 main commands.
 *
 * @author Junye Wen
 * Edited By Jonathan Gonzalez and Kyle Greer
 */
public class MainApp {

	/**
	 * This method will begin the user interface console. Main uses a loop to
	 * continue doing commands until the user types '6'. A lot of user input
	 * validation is done in the loop. At least enough to allow the interface
	 * with ShippingStore to be safe.
	 *
	 * @param args this program expects no command line arguments
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);

		PackageDB PackageDB = new PackageDB();
		TransactionDB TransanctionDB = new TransactionDB();
		UserDB uList = new UserDB();

		int selection;
		do {
			String welcomeMessage = "\nWelcome to the Shipping Store database. Choose one of the following functions:\n\n"
					+ "\t1. Show all existing package orders in the database\n"
					+ "\t2. Add a new package order to the database.\n"
					+ "\t3. Delete a package order from a database.\n"
					+ "\t4. Search for a package order (given its Tracking #).\n"
					+ "\t5. Show a list of users in the database.\n"
					+ "\t6. Add new user to the database.\n"
					+ "\t7. Update user info (given their id).\n"
					+ "\t8. Complete a shipping transaction.\n"
					+ "\t9. Show completed shipping transactions.\n"
					+ "\t10. Exit program.\n";

			System.out.println(welcomeMessage);
			selection = in.nextInt();

			String type, inTemp, trackingNumber, specification, 
			mailingClass, customOne,customTwo;

			in.nextLine();

			switch (selection) {
			case 1:
				PackageDB.displayPackageOrders();
				break;
			case 2:
				System.out.println();
				System.out.print("Select Package Type: ");
				System.out.print("Envelope, Box, Crate, or Drum ");
				type = in.nextLine().toUpperCase();
				String temp[];
				switch (type) {
				case "ENVELOPE":
					System.out.println("\nENTER TRACKING#: ");
					trackingNumber = in.nextLine().toUpperCase();
					System.out.println("\nENTER SPECIFICATION: (Fragile, Books, Catalogs, Do-not-Bend, N/A) ");
					specification = in.nextLine().toUpperCase();
					System.out.println("\nENTER CLASS: (First-Class, Priority, Retail, Ground, Metro )");
					mailingClass = in.nextLine().toUpperCase();
					System.out.println("\nENTER HEIGHT: ");
					customOne = in.nextLine().toUpperCase();
					System.out.println("\nENTER WIDTH: ");
					customTwo = in.nextLine().toUpperCase();
					PackageDB.addOrder(trackingNumber, type, specification, mailingClass, customOne, customTwo);
					break;
				case "BOX":	
					System.out.println("\nENTER TRACKING#: ");
					trackingNumber = in.nextLine().toUpperCase();
					System.out.println("\nENTER SPECIFICATION: (Fragile, Books, Catalogs, Do-not-Bend, N/A) ");
					specification = in.nextLine().toUpperCase();
					System.out.println("\nENTER CLASS: (First-Class, Priority, Retail, Ground, Metro )");
					mailingClass = in.nextLine().toUpperCase();
					System.out.println("\nENTER LARGEST-DIMENSION: ");
					customOne = in.nextLine().toUpperCase();
					System.out.println("\nENTER VOLUME: ");
					customTwo = in.nextLine().toUpperCase();
					PackageDB.addOrder(trackingNumber, type, specification, mailingClass, customOne, customTwo);
					break;
				case "CRATE":
					System.out.println("\nENTER TRACKING#: ");
					trackingNumber = in.nextLine().toUpperCase();
					System.out.println("\nENTER SPECIFICATION: (Fragile, Books, Catalogs, Do-not-Bend, N/A) ");
					specification = in.nextLine().toUpperCase();
					System.out.println("\nENTER CLASS: (First-Class, Priority, Retail, Ground, Metro )");
					mailingClass = in.nextLine().toUpperCase();
					System.out.println("\nENTER MAX-LOAD-WEIGHT: ");
					customOne = in.nextLine().toUpperCase();
					System.out.println("\nENTER CONTENT: ");
					customTwo = in.nextLine().toUpperCase();
					PackageDB.addOrder(trackingNumber, type, specification, mailingClass, customOne, customTwo);
					break;
				case "DRUM":		
					System.out.println("\nENTER TRACKING#: ");
					trackingNumber = in.nextLine().toUpperCase();
					System.out.println("\nENTER SPECIFICATION: (Fragile, Books, Catalogs, Do-not-Bend, N/A) ");
					specification = in.nextLine().toUpperCase();
					System.out.println("\nENTER CLASS: (First-Class, Priority, Retail, Ground, Metro )");
					mailingClass = in.nextLine().toUpperCase();
					System.out.println("\nENTER MATERIAL: (Fiber or Plastic) ");
					customOne = in.nextLine().toUpperCase();
					System.out.println("\nENTER DIAMETER: ");
					customTwo = in.nextLine().toUpperCase();
					PackageDB.addOrder(trackingNumber, type, specification, mailingClass, customOne, customTwo);
					break;
				default:
					System.out.println("That is not a valid type. Returning to main menu.");
				}
				break;
			case 3:
				PackageDB.displayPackageOrders();

				System.out.println("\nPlease enter the tracking # to delete from database.\n");
				String orderToDelete = in.nextLine().toUpperCase();
				PackageDB.removePackage(orderToDelete);
				break;
			case 4:
				System.out.println("\nEnter the Tracking #\n");
				String trackingNum = in.nextLine().toUpperCase();

				PackageDB.searchPackageOrder(trackingNum);
				break;
			case 5:
				uList.printUsers();
				break;
			case 6:
				uList.addUser();
				break;
			case 7:
				int id; 
				System.out.print("enter Id: ");
				id = in.nextInt();
				uList.updateUserInfo(id);
				break;
			case 8:
				System.out.println();
				SimpleDateFormat date = new SimpleDateFormat("mm-dd-yyyy");
				System.out.print("\n Enter customer ID: ");
                Integer customerID=in.nextInt();
                if (UserDB.findUser(customerID) == null) {
                    System.out.print("\n Customer does not exists");
                    break;
                }
                else if (UserDB.findUser(customerID) == "Employee") {
                    System.out.print("\n Enter the customer ID.");
                    break;
                }
				in.nextLine();
				
				System.out.print("\n Enter tracking Number:");
				String trackingNumberInput = in.nextLine();
				if (PackageDB.findPackage(trackingNumberInput) == -1) {
					System.out.print("\n Tracking Number does not exists");
					break;
				}

				//Get Shipping Date
				Date shippingDate = new Date();
				while (true)
				{
					System.out.print("\n Enter Shipping Date (format mm-dd-yyyy): ");
					String str = in.nextLine();

					try {
						shippingDate = date.parse(str);
					} catch (ParseException e) {
						System.out.println("\n Not a valid date");
						continue;
					}
					break;
				}

				//Get Delivery Date
				Date deliveryDate = new Date();
				while (true)
				{
					System.out.print("\n Enter Delivery Date (format MM-dd-yyyy): ");
					String str = in.nextLine();

					try {
						deliveryDate = date.parse(str);
					} catch (ParseException e) {
						System.out.println("\n Not a valid date");
						continue;
					}
					break;
				}

				//Get shippingCost from user and validates input
				System.out.print("\n Enter Shipping Cost: ");
				while (!in.hasNextFloat()) {
					System.out.println("\n Error: Invalid Shipping Cost.");
					System.out.print("\n Enter Shipping Cost: ");
					in.next();
				}
				float cost=in.nextFloat();
				in.nextLine();

				System.out.print("\n Enter employee ID: ");
                Integer employeeID=in.nextInt();
                if (UserDB.findUser(employeeID) == null) {
                    System.out.print("\n Create the employee first.");
                    break;
                }
                else if (UserDB.findUser(employeeID) == "Customer") {
                    System.out.print("\n Please enter an employee ID or create a new user.");
                    break;
                }

				//add new package with the information given by the user
				TransanctionDB.completeTransaction(customerID, trackingNumberInput, shippingDate, deliveryDate, cost, employeeID, PackageDB);

				break;
			case 9:
				TransanctionDB.showTransactions();
				break;
			case 10:
				// Quits program
				System.out.println("Exit selected");
				
				break;
			default:
				System.out.println("Invalid selection");
			}
		} while (selection != 10);

		in.close();
		PackageDB.flush();
		TransactionDB.flush();
		UserDB.flush();
		System.out.println("Done!");

	}
	
}



