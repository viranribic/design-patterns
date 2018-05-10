package hr.fer.zemris.ooup.lab2.zad5.actions;

import java.util.Collection;

import hr.fer.zemris.ooup.lab2.zad5.Subject;

/**
 * Action which prints to the standard output the sum of all elements in the collection.
 * @author Viran
 *
 */
public class PrintSumAction implements IAction {

	public PrintSumAction(Subject subject) {
		subject.register(this);
	}
	
	@Override
	public void performAction(Collection<Integer> collection) {
		int sum=0;
		for(Integer i:collection)
			sum+=i;
		System.out.println("Sum of the collection elemnts is now: "+sum+" .");
	}

}
