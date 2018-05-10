package hr.fer.zemris.ooup.lab2.zad4.percentil;

/**
 * PercentileDeterminer interface.
 * @author Viran
 *
 * @param <T>
 */
public interface PercentileDeterminer<T extends Number> {

	
	/**
	 * Calculate the percentile over the contained collection.
	 * @param p Percentile.
	 * @return Calculated value.
	 */
	public abstract T calculatePercentile(int p);
}
