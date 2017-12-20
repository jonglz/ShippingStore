package txstate.j_g548;

import java.io.*;
import java.util.*;

/**
 * This class is a very simple representation of a transaction. There are only getter
 * methods and no setter methods 
 * @author Jonathan Gonzalez
 *
 */
public class Transaction implements Serializable {

    private Integer customerID;
    private String trackingNumber;
    private Date shippingDate;
    private Date deliveryDate;
    private Integer employeeID;
    private float shippingCost;
    
    /**
     * This constructor initializes the Transaction object. The constructor provides no
	 * user input validation. That should be handled by the class that creates a
	 * transaction object.
     * @param customerID
     * @param trackingNumber
     * @param shippingDate
     * @param deliveryDate
     * @param shippingCost
     * @param employeeID
     */
    public Transaction(Integer customerID, String trackingNumber, Date shippingDate, 
    		Date deliveryDate, float shippingCost, Integer employeeID) {
    	
        this.customerID=customerID;
        this.trackingNumber=trackingNumber;
        this.shippingDate=shippingDate;
        this.deliveryDate=deliveryDate;
        this.shippingCost=shippingCost;
        this.employeeID=employeeID;
    }

    /**
     * This method returns the tracking number.
     * @return
     */
	public String getTrackingNumber() {
		return trackingNumber;
	}

	/**
	 * This method returns the customerID.
	 * @return
	 */
	public Integer getCustomerID() {
		return customerID;
	}

	/**
	 * This method returns the shippingDate 
	 * @return
	 */
	public Date getShippingDate() {
		return shippingDate;
	}

	/**
	 * This method returns the deliveryDate 
	 * @return
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * This method returns the employeeID
	 * @return
	 */
	public Integer getEmployeeID() {
		return employeeID;
	}

	/**
	 * This method returns the shippingCost
	 * @return
	 */
	public float getShippingCost() {
		return shippingCost;
	}

}
