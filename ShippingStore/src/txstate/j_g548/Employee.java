package txstate.j_g548;

/**
 * This class represents an Employee which is extended from the User class and contains
 * three more additional fields that are the Social Security Number,
 * monthly salary, and the bank account number
 * @author Jonathan Gonzalez
 *
 */
public class Employee extends User{
	
	private int SSN;
	private float Msal;
	private int bankDep;
	
	/**
	 * Default constructor for Employee that contains everything User had, but also 
	 * Social Security Number, monthly salary, and the bank account number
	 * @param FN is a <CODE>String</CODE>
	 * @param LN is a <CODE>String</CODE>
	 * @param I is a <CODE>int</CODE>
	 * @param s is a <CODE>String</CODE>
	 * @param m is a <CODE>float</CODE>
	 * @param bank is a <CODE>int</CODE>
	 */
	Employee(String FN, String LN, int I, int s, float m, int bank) {
		super(FN, LN, I);
		SSN= s;
		Msal=m;
		bankDep = bank;
	}

	/**
	 * This method gets the social security number
	 * @return SSN which is a <CODE>String</CODE> that contains the social security number
	 */
	public int getSSN() {
		return SSN;
	}

	/**
	 * This method sets the social security number
	 * @param sSN is a <CODE>String</CODE> that contains the social security number
	 */
	public void setSSN(int sSN) {
		SSN = sSN;
	}

	/**
	 * This method returns the monthly salary
	 * @return Msal which is a <CODE>float</CODE> that contains the monthly salary
	 */
	public float getMsal() {
		return Msal;
	}

	/**
	 * This method sets the monthly salary
	 * @param msal is a <CODE>float</CODE> used for setting the monthly salary
	 */
	public void setMsal(int msal) {
		Msal = msal;
	}

	/**
	 * This method returns the Bank Account number
	 * @return bankdep is a <CODE>int</CODE> that contains the bank account number
	 */
	public int getBankDep() {
		return bankDep;
	}

	/**
	 * This method sets the bank account number
	 * @param bankDep is a <CODE>int</CODE> that contains the bank account number
	 */
	public void setBankDep(int bankDep) {
		this.bankDep = bankDep;
	}
	
}
