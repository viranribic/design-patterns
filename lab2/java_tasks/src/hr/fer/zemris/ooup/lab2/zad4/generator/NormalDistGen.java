package hr.fer.zemris.ooup.lab2.zad4.generator;

import java.util.Random;

public class NormalDistGen implements IDistributionGenerator {

	private Random rand=new Random();
	private int numberOfElements;

	public NormalDistGen(int numberOfElements) {
		if(numberOfElements<=0)
			throw new IllegalArgumentException("The numer of elements must be greater than 0.");
		this.numberOfElements=numberOfElements;
	}
	
	@Override
	public Integer[] generateSequence() {
		Integer[] array=new Integer[numberOfElements];
		for(int i=0;i<this.numberOfElements;i++)
			array[i]=(int)(this.numberOfElements*this.rand.nextGaussian());
		return array;
	}

}
