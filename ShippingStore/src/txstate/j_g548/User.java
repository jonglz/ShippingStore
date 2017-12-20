package txstate.j_g548;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * This class is a representation of a User, which contains a first name, a last name
 * and a unique ID. 
 * @author Kyle Greer
 *
 */

public class User implements Serializable {
	
	protected String firstName;
	protected String lastName;
	protected int ID;
	
	/**
	 * Default Constructor that sets FN, LN, and I to
	 * first name , last name, and ID
	 * @param FN is a <CODE>String</CODE> that should be the First Name
	 * @param LN is a <CODE>String</CODE> that should be the Last Name
	 * @param I is a <CODE>int</CODE> that should be the ID
	 */
	User(String FN, String LN, int I){
		firstName = FN;
		lastName = LN;
		ID = I;
	}
	
	/**
	 * this method returns the first name of the user
	 * @return a <CODE>String</CODE> that is the first name of the user
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * This method sets the first name of the user
	 * @param firstName is a <CODE>String</CODE> that contains the
	 *  first name of the user
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * this method returns the last name of the user
	 * @return a <CODE>String</CODE> that is the last name of the user
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * This method sets the last name of the user
	 * @param lastName is a <CODE>String</CODE> that contains the
	 *  last name of the user
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * This method returns the ID of the user
	 * @return a <CODE>int</CODE> that contains the ID of the user
	 */
	public int getID() {
		return ID;
	}
	
	/**This method sets the ID
	 * 
	 * @param iD is a <CODE>int</CODE> that needs to be the ID
	 */
	public void setID(int iD) {
		ID = iD;
	}
	
	/**
	 * converts the first name / last name / ID into a string to be written to output file
	 * @return s which is a <CODE>String</CODE>
	 */
	protected String convertToString() {
		String s = "";
		s.concat("User: ");
		s.concat("(" + firstName + ")" + " ");
		s.concat("(" + lastName + ")" + " ");
		s.concat("("+ ID + ")" + " ");
		s.concat("(X) / (X) / (X)/ (X) / (X)" );
		return s;
	}
	
	/**
	 * method to print to outputfile
	 * @param f is a <CODE>ObjectOutputStream</CODE> 
	 */
	public void fOut(ObjectOutputStream f) {
		try {
			f.writeObject(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is to print the User
	 */
	public void print() {
		System.out.print("User: ");
		System.out.format("%-12s", firstName);
		System.out.format("%-17s", lastName);
		System.out.format("%d", ID);
		System.out.format("%15s", "N/A");	
		System.out.format("%15s", "N/A");
		System.out.format("%15s", "N/A");
		System.out.format("%15s", "N/A");
		System.out.format("%15s", "N/A");
	}
}
