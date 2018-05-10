package hr.fer.zemris.ooup.lab2.zad4;

import hr.fer.zemris.ooup.lab2.zad4.generator.ConsecutiveDistGen;
import hr.fer.zemris.ooup.lab2.zad4.generator.IDistributionGenerator;
import hr.fer.zemris.ooup.lab2.zad4.percentil.LIBCRPercentile;
import hr.fer.zemris.ooup.lab2.zad4.percentil.PercentileDeterminer;

public class DistributionTester {

	private static IDistributionGenerator generator;
	private static PercentileDeterminer<Integer> percentil;
	
	
	public static void main(String[] args) {
		generator=new ConsecutiveDistGen(0,100,1);
		Integer[] array=generator.generateSequence();
		array=new Integer[]{1,10,50};
		percentil=new LIBCRPercentile(array);
		System.out.println(percentil.calculatePercentile(80));
		
	}
}
