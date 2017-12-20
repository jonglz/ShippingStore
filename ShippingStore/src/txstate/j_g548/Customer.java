package txstate.j_g548;

/**
 * This class represents a Customer, a type of User that also has a phone and address
 * @author Jonathan Gonzalez
 *
 */
public class Customer extends User{
	
	private String Phone;
	private String Adress;
	
	/**
	 * This is a default constructor for Customers which sets
	 * the same fields as a User but also has a phone and address assigned
	 * @param FN is a <CODE>String</CODE> that represents the first name
	 * @param LN is a <CODE>String</CODE> that represents the last name
	 * @param I is a <CODE>int</CODE> that represents the ID
	 * @param Ph is a <CODE>String</CODE> that represents  the Phone Number
	 * @param Ad is a <CODE>String</CODE> that represents the address
	 */
	Customer(String FN, String LN, int I, String Ph, String Ad) {
		super(FN, LN, I);
		Phone = Ph;
		Adress = Ad;
	}

	/**
	 * This method gets the phone number
	 * @return Phone which is <CODE>String</CODE> that contains the phone number
	 */
	public String getPhone() {
		return Phone;
	}
	/**
	 * This method sets the phone number
	 * @param phone which is <CODE>String</CODE> that contains the phone number
	 */
	public void setPhone(String phone) {
		Phone = phone;
	}
	
	/**
	 * This method gets the address
	 * @return Address which is <CODE>String</CODE> that contains the address
	 */
	public String getAdress() {
		return Adress;
	}

	/**
	 * This method sets the address
	 * @param adress which is <CODE>String</CODE> that contains the address
	 */
	public void setAdress(String adress) {
		Adress = adress;
	}
	
}
