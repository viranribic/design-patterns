package hr.fer.zemris.ooup.lab2.zad5.dataSrcs;


/**
 * Interface for a class capable of generating a sequence of numbers.
 * @author Viran
 *
 */
public interface INumberSource{

	/**
	 * Generate the next number from this source.
	 * @return Next integer number.
	 */
	public int generateNext();
	
	/**
	 * Make the necessary closing operations.
	 */
	public void closeSource();
}
