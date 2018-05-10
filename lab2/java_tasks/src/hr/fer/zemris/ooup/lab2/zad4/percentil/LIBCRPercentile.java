package hr.fer.zemris.ooup.lab2.zad4.percentil;

import java.util.Arrays;

/**
 * The Linear Interpolation Between Closest Ranks method.
 * 
 * @author Viran
 *
 */
public class LIBCRPercentile implements PercentileDeterminer<Integer> {

	private Integer[] v;

	/**
	 * LIBCRPercentile constructor.
	 * @param array Array of integers this class operates on.
	 */
	public LIBCRPercentile(Integer[] array) {
		if(array.length==0)
			throw new IllegalArgumentException("Array must contain more than one element.");
		this.v=array;
		Arrays.sort(this.v);
	}

	@Override
	public Integer calculatePercentile(int p) {
		if(p< 100*(1-0.5)/v.length)
			return v[0];
		if(p> 100*(v.length-0.5)/v.length)
			return v[0];
		for(int i=0;i<v.length-1;i++){
			double v_i= 100*((double)i+1-0.5)/v.length;
			double v_ip1= 100*((double)i+1+1.-0.5)/v.length;
			if(v_i<=p && p<=v_ip1){
				double v_p=v[i]+(double)v.length*(p-v_i)*(v[i+1]-v[i])/100;
				return (int)v_p;
			}
		}
		return -1;
	}

}
