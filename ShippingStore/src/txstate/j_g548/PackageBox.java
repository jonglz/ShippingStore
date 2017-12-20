package txstate.j_g548;

/**
 * Package Box Class used to create an extension of Package.
 * @author Jonathan Gonzalez
 *
 */
@SuppressWarnings("serial")
public class PackageBox extends Package {
	private int largestDimension;
	private int volume;

	/**
	 * Package Box constructor used to initialize the object. 
	 * @param trackingnumber
	 * @param specification
	 * @param mailingclass
	 * @param largestDimension
	 * @param volume
	 */
	public PackageBox(String trackingnumber, String specification, 
			String mailingclass, int largestDimension, int volume) {
		super(trackingnumber, "BOX", specification, mailingclass);
		this.largestDimension = largestDimension;
		this.volume = volume;
	}

	/**
	 * This method returns the largestDimension of Box.
	 * @return 
	 */
	public int getLargestDimension() {
		return largestDimension;
	}

	/**
	 * This method returns the volume of Box
	 * @return
	 */
	public int getVolume() {
		return volume;
	}

	/**
	 * This method returns the fields as a string representation.
	 */
	@Override
	public String toString() {
		return super.toString() + " " +  largestDimension + " " + volume + "\n";
	}

}
