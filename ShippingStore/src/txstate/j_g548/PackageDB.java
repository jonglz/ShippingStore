package txstate.j_g548;

import java.io.*;
import java.util.*;

/**
 * This class is used to represent a database interface for a list of
 * <CODE>Package Order</CODE>'s. It using a plain-text file "PackageOrderDB.ser"
 * to store and write package order objects in readable text form. It contains
 * an <CODE>ArrayList</CODE> called <CODE>packageOrerList</CODE> to store the
 * database in a runtime friendly data structure. The <CODE>packageOrerList</CODE>
 * is written to "PackageOrderDB.txt" at the end of the <CODE>ShippingStore</CODE> object's
 * life by calling <CODE>flush()</CODE>. This class also provides methods for
 * adding, remove, and searching for shipping orders from the list.
 * 
 * @author Junye Wen 
 * Edited by Jonathan Gonzalez and Kyle Greer
 *
 */
public class PackageDB {

	private static final String DATA_FILE  = "PackageOrderDB.ser";
	private ArrayList<Package> packageOrderList;

	/**
	 * Private method used as an auxiliary method to display a given ArrayList
	 * of package orders in a formatted manner.
	 *
	 * @param orders the package order list to be displayed.
	 */
	public void displayPackageOrders(ArrayList<Package> packageOrderList) {

		if (packageOrderList.size() <= 0) {
			System.out.println("There's No Inventory To Display");
		}

		System.out.println(" -------------------------------------------------------------------------------------------------");
		System.out.println("| TRACKING # | TYPE    | SPECIFICATION    | CLASS         |                           |           ");
		System.out.println(" -------------------------------------------------------------------------------------------------");


		for (Package p : packageOrderList) {


			System.out.print(String.format("| %-11s| %-8s| %-17s| %-14s",
					p.getTrackingNumber(),
					p.getType(),
					p.getSpecification(),
					p.getMailingClass()));

			if (p instanceof PackageEnvelope) {
				System.out.println(String.format("| %-26s| %-30s|",
						"HEIGHT: " + Integer.toString(((PackageEnvelope) p).getHeight()),
						"WIDTH: " + Integer.toString(((PackageEnvelope) p).getWidth())));
			} 
			else if (p instanceof PackageBox) {
				System.out.println(String.format("| %-26s| %-30s|",
						"LARGEST DIMENSION: " + Integer.toString(((PackageBox) p).getLargestDimension()),
						"VOLUME: " + Integer.toString(((PackageBox) p).getVolume())));
			} 
			else if (p instanceof PackageCrate) {
				System.out.println(String.format("| %-26s| %-30s|",
						"MAX LOAD WEIGHT: " + String.format("%.2f", ((PackageCrate) p).getMaxWeight()),
						"CONTENT: " + ((PackageCrate) p).getContent()));
			} 
			else if (p instanceof PackageDrum) {
				System.out.println(String.format("| %-26s| %-30s|",
						"MATERIAL: " + ((PackageDrum) p).getMaterial(),
						"DIAMETER: " + Integer.toString(((PackageDrum) p).getDiameter())));
			}
		}

		System.out.println(" -------------------------------------------------------------------------------------------------");

	}

	/**
	 * Method used to read in data from PackageOrderDB.ser and 
	 * store in ArrayList
	 * @throws Exception
	 */
	public PackageDB() throws Exception {    
		packageOrderList = new ArrayList<>();


		try{
			FileInputStream fis = new FileInputStream(DATA_FILE);
			ObjectInputStream ois = new ObjectInputStream(fis);
			packageOrderList = (ArrayList<Package>) ois.readObject();
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
	 * Method showPackageOrders displays the current list of package orders in the Arraylist in no
	 * particular order.
	 *
	 */
	public void displayPackageOrders() {
		displayPackageOrders(packageOrderList);
	}

	/**
	 * This method can be used to find a package order in the Arraylist of orders.
	 *
	 * @param trackingNumber a <CODE>String</CODE> that represents the tracking number 
	 * of the order that to be searched for.
	 * @return the <CODE>int</CODE> index of the package orders in the Arraylist of orders,
	 * or -1 if the search failed.
	 */
	public int findPackage(String trackingNumber) {

		int index = -1;

		for (Package p : packageOrderList) {
			String temp = p.getTrackingNumber();

			if (trackingNumber.equalsIgnoreCase(temp)) {
				index = packageOrderList.indexOf(p);
				break;
			}
		}

		return index;
	}

	/**
	 * This method can be used to search for a package order in the Arraylist of orders.
	 *
	 * @param trackingNumber a <CODE>String</CODE> that represents the tracking number
	 * of the order that to be searched for.
	 */
	public void searchPackageOrder(String trackingNumber) {

		int index = findPackage(trackingNumber);

		if (index != -1) {
			ArrayList<Package> order = new ArrayList<>(1);
			order.add(getPackageOrder(index));
			System.out.println("\nHere is the order that matched:\n");
			displayPackageOrders(order);
		} else {
			System.out.println("\nSearch did not find a match.\n");
		}
	}

	/**
	 * This method is used to add a package order to the orderList ArrayList. In order for a
	 * package order to be added to the ArrayList it must comply with the following:
	 * <p>
	 * 1. The order is not already in the ArrayList according to the tracking number
	 * as the unique key.
	 * <p>
	 * 2. The TrackningNumber string matches the following regular expression:
	 * <CODE>"[A-Za-z0-9]{5}"</CODE> or in other words: it
	 * is 5 avinhanumeric characters.
	 * <p>
	 * 3. The Type of the order can be only one of the following:
	 *    Postcard, Letter, Envelope, Packet, Box, Crate, Drum, Roll, Tube.
	 * <p>
	 * 4. The Specification of the order can be only one of the following:
	 *    Fragile, Books, Catalogs, Do-not-Bend, N/A.
	 * <p>
	 * 5. The Mailing Class of the order can be only one of the following:
	 *    First-Class, Priority, Retail, Ground, Metro.
	 * <p>

	 * @param toAdd the <CODE>Package</CODE> object to add to the
	 * <CODE>packageOrderList</CODE>
	 */
	public void addOrder(String trackingNumber, String type, String specification, 
			String mailingClass, String customOne, String customTwo) {

		if (this.findPackage(trackingNumber) != -1) {
			System.out.println("Package Order already exists in database. \n");
			return;
		}

		if (!trackingNumber.matches("[A-Za-z0-9]{5}")) {
			System.out.println("Invalid Tracking Number: not proper format."
					+ "Tracking Number must be 5 alphanumeric characters.");
			return;
		}

		if (!(specification.equals("FRAGILE") || specification.equals("BOOKS") || specification.equals("CATALOGS")
				|| specification.equals("DO-NOT-BEND") || specification.toUpperCase().equals("N/A"))) {
			System.out.println("Invalid specification:\n"
					+ "Specification must be one of following: "
					+ "Fragile, Books, Catalogs, Do-not-Bend, N/A.");
			return;
		}

		if (!(mailingClass.equals("FIRST-CLASS") || mailingClass.equals("PRIORITY") || mailingClass.equals("RETAIL")
				|| mailingClass.equals("GROUND") || mailingClass.equals("METRO")) ) {
			System.out.println("Invalid Mailing Class:\n"
					+ "Mailing Class must be one of following: "
					+ "First-Class, Priority, Retail, Ground, Metro.");
			return;
		}


		if (type.equals("ENVELOPE")) {
			if(!customOne.matches("[0-9]{1,2}")) {
				System.out.println("Invalid height:\n");
				return;
			}
			if(!customTwo.matches("[0-9]{1,2}")) {
				System.out.println("Invalid width:\n");
				return;
			}

			// If it passed all the checks, add the order to the list
			packageOrderList.add(new PackageEnvelope(trackingNumber, specification, mailingClass,
					Integer.parseInt(customOne), Integer.parseInt(customTwo)));
		}
		else if (type.equals("BOX")) {
			if(!customOne.matches("[0-9]{1,3}")){
				System.out.println("Invalid dimension:\n");
				return;
			}
			if(!customTwo.matches("[0-9]{1,6}")){
				System.out.println("Invalid volume:\n");
				return;
			}

			// If it passed all the checks, add the order to the list
			packageOrderList.add(new PackageBox(trackingNumber, specification, mailingClass,
					Integer.parseInt(customOne), Integer.parseInt(customTwo)));
		}
		else if (type.equals("CRATE")) {
			if ((Float.parseFloat(customOne) < 0)) {
				System.out.println("Invalid load weight:\n");
				return;
			}

			// If it passed all the checks, add the order to the list
			packageOrderList.add(new PackageCrate(trackingNumber, specification, mailingClass,
					Float.parseFloat(customOne), customTwo));
		}
		else if (type.equals("DRUM")) {
			if (!(customOne.equals("PLASTIC") || customOne.equals("FIBER"))){
				System.out.println("Invalid material:\n");
				return;
			}
			if(!customTwo.matches("[0-9]{1,3}")){
				System.out.println("Invalid diameter:\n");
				return;
			}

			// If it passed all the checks, add the order to the list
			packageOrderList.add(new PackageDrum(trackingNumber, specification, mailingClass,
					customOne, Integer.parseInt(customTwo)));
		}

		else{
			System.out.println("Not a valid type.\n"
					+ "Please Enter: Envelope, Box, Crate, or Drum\n");
			return;
		}

		System.out.println("Package Order has been added.\n");
	}




	/**
	 * This method will remove an order from the <CODE>packageOrderList</CODE> ArrayList. It
	 * will remove the instance of an order that matches tracking number that was
	 * passed to this method. If no such order exists, it will produce an error message.
	 *
	 * @param toDelete the <CODE>PackageOrder</CODE> object to be removed.
	 */
	public void removePackage(String trackingNumber) {
		int orderID = findPackage(trackingNumber);
		if (orderID == -1) {
			System.out.println("\nAction failed. No package order with the given tracking # exist in database.\n");
		}
		else {
			packageOrderList.remove(orderID);
			System.out.println("\nAction successful. Package order has been removed from the database.\n");
		}
	}

	/**
	 * This method is used to retrieve the PackageOrder object from the
	 * <CODE>PackageOrderList</CODE> at a given index.
	 *
	 * @param i the index of the desired <CODE>PackageOrder</CODE> object.
	 * @return the <CODE>PackageOrder</CODE> object at the index or null if the index is
	 * invalid.
	 */
	public Package getPackageOrder(int i) 
	{
		if (i < packageOrderList.size() && i >= 0) {
			return packageOrderList.get(i);
		} else {
			System.out.println("Invalid Index");
			return null;
		}
	}

	/**
	 * This method opens <CODE>"PackageOrderDB.ser"</CODE> and overwrites it with a serialization of
	 * all the package orders in the <CODE>PackageOrderList</CODE>.
	 * This should be the last method to be called before exiting the program.
	 * @throws IOException
	 */
	public void flush() throws Exception {
		try {
			FileOutputStream fos = new FileOutputStream(DATA_FILE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(packageOrderList);

			fos.close();

		} 
		catch(IOException e) {
			System.out.println("Problem Writing PackageOrderDB.ser");
		}
	}

}
