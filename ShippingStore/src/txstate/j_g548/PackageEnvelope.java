package txstate.j_g548;

/**
 * Package Envelope Class used to create an extension of Package.
 * @author Jonathan Gonzalez
 *
 */
@SuppressWarnings("serial")
public class PackageEnvelope extends Package {
	private int height;
	private int width;
	
	/**
	 * Constructor initialize PackageEnvelope object
	 * @param trackingnumber
	 * @param specification
	 * @param mailingclass
	 * @param height
	 * @param width
	 */
	public PackageEnvelope(String trackingnumber, String specification, 
			String mailingclass, int height, int width) {
		super(trackingnumber, "ENVELOPE", specification, mailingclass);
		this.height = height;
		this.width = width;
	}

	/**
	 * This method returns the height of Envelope.
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * This method returns the width of Envelope.
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * This method returns the fields as a string representation.
	 */
    @Override
    public String toString() {
        return super.toString() + " " +  height + " " + width + "\n";
    }
}
