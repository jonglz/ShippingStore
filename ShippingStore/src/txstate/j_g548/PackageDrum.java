package txstate.j_g548;

/**
 * Package Drum Class used to create an extension of Package.
 * @author Jonathan Gonzalez
 *
 */
@SuppressWarnings("serial")
public class PackageDrum extends Package{

	private String material;
	private int diameter;

	/**
	 * Constructor initialize PackageDrum object
	 * @param trackingnumber
	 * @param specification
	 * @param mailingclass
	 * @param material
	 * @param diameter
	 */
	public PackageDrum(String trackingnumber, String specification, 
			String mailingclass, String material, int diameter) {
		super(trackingnumber, "DRUM", specification, mailingclass);
		this.diameter = diameter; 
		this.material = material;
	}

	/**
	 * This method returns the material of Drum.
	 * @return 
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * This method returns the diameter of Drum.
	 * @return 
	 */
	public int getDiameter() {
		return diameter;
	}

	/**
	 * This method returns the fields as a string representation.
	 */
	@Override
	public String toString() {
		return super.toString() + " " + material + " " + diameter + "\n";
	}
}
