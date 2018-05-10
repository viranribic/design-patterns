package hr.fer.zemris.ooup.lab2.zad4.generator;

public class FibonacciDistGen implements IDistributionGenerator {

	private int Fn_1=1;
	private int Fn_2=1;
	private int numberOfElements;


	public FibonacciDistGen(int numberOfElements) {
		if(numberOfElements<=0)
			throw new IllegalArgumentException("The numer of elements must be greater than 0.");
		this.numberOfElements=numberOfElements;
	}
	
	
	@Override
	public Integer[] generateSequence() {
		Integer[] array=new Integer[numberOfElements];
		if(numberOfElements>=1)
			array[0]=1;
		if(numberOfElements>=2)
			array[1]=1;
		if(numberOfElements >=3)
			for(int i=2;i<this.numberOfElements;i++){
				array[i]=this.Fn_1+this.Fn_2;
				this.Fn_2=this.Fn_1;
				this.Fn_1=array[i];
			}
		this.Fn_1=this.Fn_2=1;
		return array;
	}

}
