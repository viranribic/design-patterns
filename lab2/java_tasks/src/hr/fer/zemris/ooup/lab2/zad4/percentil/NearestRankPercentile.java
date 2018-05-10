package hr.fer.zemris.ooup.lab2.zad4.percentil;

import java.util.Arrays;

/**
 * NearestRankPercentile class.
 * @author Viran
 *
 */
public class NearestRankPercentile implements PercentileDeterminer<Integer>{

	private Integer[] array;
	
	/**
	 * NearestRankPercentile constructor.
	 * @param array Array of integers which percentiles will be calculated. All modifications over the array are seen in this class.
	 */
	public NearestRankPercentile(Integer[] array) {
		if(array.length==0)
			throw new IllegalArgumentException("Array must contain more than one element.");
		this.array=array;
		Arrays.sort(this.array);
	}
	
	@Override
	public Integer calculatePercentile(int p) {
		int n_p=Math.round((float)(p*array.length/100 + 0.5));
		return array[n_p-1];
	}

}
