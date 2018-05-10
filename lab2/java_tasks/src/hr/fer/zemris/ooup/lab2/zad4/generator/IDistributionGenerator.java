package hr.fer.zemris.ooup.lab2.zad4.generator;

/**
 * Interface for classes capable of generating a sequences.
 * @author Viran
 *
 */
public interface IDistributionGenerator {

	/**
	 * Generates a sequence of integers.
	 * @return Sequence of integers.
	 */
	public abstract Integer[] generateSequence();
}
