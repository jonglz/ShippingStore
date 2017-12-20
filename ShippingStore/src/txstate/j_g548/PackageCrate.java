package txstate.j_g548;
/**
 * Package Crate Class used to create an extension of Package.
 * @author Jonathan Gonzalez
 *
 */
@SuppressWarnings("serial")
public class PackageCrate extends Package {

	private float maximumLoadWeight;
	private String content;

	/**
	 * Constructor initialize PackageCrate object
	 * @param trackingnumber
	 * @param specification
	 * @param mailingclass
	 * @param maximumLoadWeight
	 * @param content
	 */
	public PackageCrate(String trackingnumber, String specification, 
			String mailingclass, float maximumLoadWeight, String content) {
		super(trackingnumber, "CRATE", specification, mailingclass);
		this.content = content;
		this.maximumLoadWeight = maximumLoadWeight;
	}

	/**
	 * This method returns the maximumLoadWeight of Crate.
	 * @return 
	 */
	public float getMaxWeight() {
		return maximumLoadWeight;
	}

	/**
	 * This method returns the content of Crate.
	 * @return 
	 */
	public String getContent() {
		return content;
	}

	/**
	 * This method returns the fields as a string representation.
	 */
	@Override
	public String toString() {
		return super.toString() + " " + maximumLoadWeight + " " + content + "\n";
	}
}
