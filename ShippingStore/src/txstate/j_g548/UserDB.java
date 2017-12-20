package txstate.j_g548;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the User Database and contains methods that
 * add users, shows the users, and updates the user's info
 *
 * @author Jonathan Gonzalez
 */

public class UserDB implements Serializable{

	private static ArrayList<User> UserList;
	private static int idCounter = 1;
	private static final String FILENAME = "UserDB.txt";

	/**
	 * This constructor checks a file for a database of current users
	 * @throws Exception
	 */
	public UserDB() throws Exception {
		UserList = new ArrayList<User>(100000);
		try {
			FileInputStream fileInput = new FileInputStream(FILENAME);
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);

			fileInput.close();
		} catch (FileNotFoundException fnfe) {
		} catch (IOException ioe) {
			System.out.println("ERROR! Problem with file input");
		}

	}

	/**
	 * This method is to add Users to the UserList.
	 * Method contains a switch statement to check for type of User
	 */
	public void addUser() {
		User u;
		String firstN, lastN, phone, adress;
		int uID, ssn, Msal, bankDep;
		int c = 0; 
		Scanner s = new Scanner(System.in);

		System.out.print("Please enter the users type (1 customer, 2 employee): ");
		c = s.nextInt();
		System.out.print("Please enter the users first name: ");
		firstN = s.next();
		System.out.print("Please enter the users last name:  ");
		lastN = s.next();
		uID = generateUID();
		System.out.print("Your unique assigned UID is: ");
		System.out.println(uID);


		switch(c) {
		case 1:
			System.out.print("Please enter the users Phone: ");
			phone = s.next();
			System.out.print("Please enter the users Adress:  ");
			adress = s.next();
			u = new Customer(firstN, lastN, uID, phone, adress);
			UserList.add(u);
			break;
		case 2:
			System.out.println("Please enter the users SSN: ");
			ssn = s.nextInt();
			System.out.println("Please enter the users Monthly Salery: ");
			Msal = s.nextInt();
			System.out.println("Please enter the users Bank Deposit Number: ");
			bankDep = s.nextInt();
			u=new Employee(firstN, lastN, uID, ssn, Msal, bankDep );
			UserList.add(u);
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
	}

	/**
	 * This method is to update the user's info based on ID
	 * @param id - the <CODE>int</CODE> to search the user by
	 */
	public void updateUserInfo(int id) {
		int type = 0;
		User USER = null;
		Class typeC;
		for(User u : UserDB.UserList) {
			if(u.ID == id) 
			{
				typeC = u.getClass();
				if (typeC == User.class) type = 1;
				else if (typeC == Customer.class) type = 2; 
				else if (typeC == Employee.class) type = 3;
				USER = u;
			}
		}
		if (USER == null)
		{
			System.out.println("User Id not found");
			return;
		}

		Scanner s = new Scanner(System.in);
		int select = 1;
		switch(type) {
		case 1:
			while (select != 0)
			{
				System.out.print("Make a selection to edit (0 Quit, 1 for firstName, 2 lastName): ");
				select = s.nextInt();
				switch(select)
				{
				case 0:
					System.out.println("Quit");
					break;
				case 1:
					System.out.print("Enter new firstName: ");
					USER.setFirstName(s.next());
					System.out.println();
					break;
				case 2:
					System.out.print("Enter new lastName: ");
					USER.setLastName(s.next());
					System.out.println();
					break;
				default:
					System.out.println("invalid input");
				}
			}
			break;
		case 2:
			Customer c = (Customer) USER;
			while (select != 0)
			{
				System.out.print("Make a selection to edit (0 Quit, 1 for firstName, 2 lastName, 3 phone number, 4 Adress): ");
				select = s.nextInt();
				switch(select)
				{
				case 0:
					System.out.println("Quit");
					break;
				case 1:
					System.out.print("Enter new firstName: ");
					USER.setFirstName(s.next());
					System.out.println();
					break;
				case 2:
					System.out.print("Enter new lastName: ");
					USER.setLastName(s.next());
					System.out.println();
					break;
				case 3:
					System.out.print("Enter new phoneNumber: ");
					c.setPhone(s.next());
					System.out.println();
					break;
				case 4:
					System.out.print("Enter new Adress: ");
					c.setAdress(s.next());
					System.out.println();
					break;
				default:
					System.out.println("invalid input");
				}
			}
			break;
		case 3:
			Employee e = (Employee) USER;
			while (select != 0)
			{
				System.out.print("Make a selection to edit ( 0 Quit, 1 for firstName, 2 lastName, 3 Salary, 4 BankDeposite#, 5 SSN): ");
				select = s.nextInt();
				switch(select)
				{
				case 0:
					System.out.println("Quit");
					break;
				case 1:
					System.out.print("Enter new firstName: ");
					USER.setFirstName(s.next());
					System.out.println();
					break;
				case 2:
					System.out.print("Enter new lastName: ");
					USER.setLastName(s.next());
					System.out.println();
					break;
				case 3:
					System.out.print("Enter new Salary: ");
					e.setMsal(s.nextInt());
					System.out.println();
					break;
				case 4:
					System.out.print("Enter new Bank Deposite Number: ");
					e.setBankDep(s.nextInt());
					System.out.println();
					break;
				case 5:
					System.out.print("Enter new SSN: ");
					e.setSSN(s.nextInt());
					System.out.println();
				default:
					System.out.println("invalid input");
				}
			}
			break;
		}
	}

	/**
	 * This method is to print the users 
	 * stored in the UserList to the console
	 */
	public void printUsers() {	


		if (UserList.size() <= 0) {
			System.out.println("There's No Inventory To Display");
		}

		for (User u : UserList) {

			if (u instanceof Customer) {
				System.out.printf(String.format("| %-11s| %-12s| %-12s| %-8s| %-13s| %-15s|",
						"Customer", 
						"ID: " + ((Customer) u).getID(),
						"First Name: " + ((Customer) u).getFirstName(),
						"Last Name: " + ((Customer) u).getLastName(),
						"Phone: " + ((Customer) u).getPhone(),
						"Address: " + ((Customer) u).getAdress()));
			} 
			else if (u instanceof Employee) {
				System.out.printf(String.format("| %-11s| %-12s| %-12s| %-8s| %-13s| %-15s| %-18s", 
						"Customer", 
						"ID: " + ((Employee) u).getID(),
						"First Name: " + ((Employee) u).getFirstName(),
						"Last Name: " + ((Employee) u).getLastName(),
						"SSN #: " + Integer.toString(((Employee) u).getSSN()),
						"Salary: " + Float.toString(((Employee) u).getMsal()),
						"Salary: " + Integer.toString(((Employee) u).getBankDep())));
			}
		}
	}

	/**
	 * Method to find user
	 * @param userID
	 * @return
	 */
	public static String findUser(int userID){

		String userType = null;

		for(User i : UserList)
			if (i.getID() == userID)
				if (i instanceof Employee)
					userType = "Employee";
				else if (i instanceof Customer)
					userType = "Customer";

		return userType;
	}

	/**
	 * This method is to return the users in the UserList
	 * @return a <CODE>User</CODE> that is stored in the UserList
	 */
	public ArrayList<User> getUserList() {
		return UserList;
	}

	/**
	 * This method generates the unique ID for each user
	 * @return Returns the <CODE>int</CODE> that is needed to store each user (their ID)
	 */
	private int generateUID() {
		return UserDB.idCounter++;
	}

	/**
	 * This method is to print out the UserList (which stores Users) into the output file FILENAME
	 * @throws Exception
	 */
	public static  void flush() throws Exception {

		try {
			FileOutputStream fileOUT = new FileOutputStream(FILENAME);
			ObjectOutputStream objectOUT = new ObjectOutputStream(fileOUT);

			objectOUT.writeObject(UserList);
			objectOUT.close();
		}
		catch (IOException e) {
			System.out.println("Error~ There is a problem writing to file");

		}

	}

}
