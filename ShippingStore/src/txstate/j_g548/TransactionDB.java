package txstate.j_g548;

import txstate.j_g548.*;
import txstate.j_g548.Package;

import java.text.*;
import java.io.*;
import java.util.*;

/**
 * 
 * This class is used to represent a database interface for a list of
 * <CODE>Transactions</CODE>'s. It using a file "TransactionDB.ser"
 * to store and write transacions in readable text form. It contains
 * an <CODE>ArrayList</CODE> called <CODE>transOrderList</CODE> to store the
 * database in a runtime friendly data structure. The <CODE>transOrderList</CODE>
 * is written to "TransactionDB.ser" at the end of the <CODE>MainApp</CODE> 
 * by calling <CODE>flush()</CODE>. This class also provides methods for
 * adding, remove, and searching  from the list.
 * 
 *
 * @author Jonathan Gonzalez
 *
 */
public class TransactionDB {

	private static final String DATA_FILE  = "TransactionDB.ser";
	private static ArrayList<Transaction> transOrderList;

	/**
	 * Method used to read in data from TransactionDB.ser and 
	 * store in ArrayList
	 * @throws Exception
	 */
	public TransactionDB() throws Exception {
		transOrderList = new ArrayList<>();


		try{
			FileInputStream fis = new FileInputStream(DATA_FILE);
			ObjectInputStream ois = new ObjectInputStream(fis);
			transOrderList = (ArrayList<Transaction>) ois.readObject();
			fis.close(); 
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("Creating File");
			FileOutputStream fos = new FileOutputStream(DATA_FILE);
			fos.close();
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
			System.out.println("ERROR! Problem with file input");
		}

	}

	/**
	 * method used to display a given ArrayList
	 * of package orders in a formatted manner.
	 */
	public void showTransactions() 
	{

		System.out.println(" -----------------------------------------------------"
				+ "------------------------------------------------------------------ ");
		System.out.println("| Customer ID | Tracking # | Ship Date                "
				+ "   | Deliver Date                |   Cost           | Employee ID |");
		System.out.println(" ------------------------------------------------------"
				+ "----------------------------------------------------------------- ");

		for (Transaction t : transOrderList){
			System.out.println(String.format("| %-12s| %-11s| %-16s| %-17s| %-17s| %-12s|", 
					t.getCustomerID(), 
					t.getTrackingNumber(), 
					t.getShippingDate(), 
					t.getDeliveryDate(), 
					t.getShippingCost(), 
					t.getEmployeeID()));
		}

		System.out.println(" ------------------------------------------------------"
				+ "-----------------------------------------------------------------\n");
	}


	/**
	 * Adds Transaction to ArrayList once user has submitted transaction information from MainApp
	 * Removes package once transaction has been completed.
	 * @param customerID
	 * @param trackingNumber
	 * @param shippingDate
	 * @param deliveryDate
	 * @param shippingCost
	 * @param employeeID
	 * @param packages
	 */
	public void completeTransaction(Integer customerID, String trackingNumber, Date shippingDate, 
			Date deliveryDate, float shippingCost, Integer employeeID, PackageDB packages)
	{
		transOrderList.add(new Transaction(customerID,trackingNumber,shippingDate,
				deliveryDate, shippingCost, employeeID));
		packages.removePackage(trackingNumber);

	}

	/**
	 * This method opens <CODE>"TransactionDB.ser"</CODE> and overwrites it with a serialization of
	 * all the package orders in the <CODE>transOrderList</CODE>.
	 * This should be the last method to be called before exiting the program.
	 * @throws IOException
	 */
	public static void flush() throws Exception {
		try {
			FileOutputStream fos = new FileOutputStream(DATA_FILE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(transOrderList);

			fos.close();

		} 
		catch(IOException e) {
			System.out.println("Problem Writing PackageOrderDB.ser");
		}

	}
}
