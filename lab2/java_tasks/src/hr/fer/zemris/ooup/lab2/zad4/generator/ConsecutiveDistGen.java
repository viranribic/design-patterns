package hr.fer.zemris.ooup.lab2.zad4.generator;

public class ConsecutiveDistGen implements IDistributionGenerator {

	private int lowerBound;
	private int upperBound;
	private int step;

	public ConsecutiveDistGen(int lowerBound, int upperBount, int step) {
		if(step==0)
			throw new IllegalArgumentException("Step must be an integer value different from zero.");
		else if(step>0 && lowerBound>upperBount)
			throw new IllegalArgumentException("For a positive step, the upper bound must be greater than or equal to the lower bound.");
		else if(step<0 && lowerBound<upperBount)
			throw new IllegalArgumentException("For a negative step, the upper bound must be less than or equal to the lower bound.");			
		
		this.lowerBound=lowerBound;
		this.upperBound=upperBount;
		this.step=step;
	}
	
	@Override
	public Integer[] generateSequence() {
		int size=(upperBound-lowerBound+1)/step;
		Integer[] array=new Integer[size];
		for(int i=1;i<array.length-1;i++)
			array[i]=lowerBound+i*step;
		array[array.length-1]=upperBound;
		array[0]=lowerBound;
		return array;
	}

}
