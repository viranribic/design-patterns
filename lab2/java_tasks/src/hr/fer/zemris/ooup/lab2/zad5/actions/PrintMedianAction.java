package hr.fer.zemris.ooup.lab2.zad5.actions;

import java.util.Collection;

import hr.fer.zemris.ooup.lab2.zad4.percentil.LIBCRPercentile;
import hr.fer.zemris.ooup.lab2.zad4.percentil.PercentileDeterminer;
import hr.fer.zemris.ooup.lab2.zad5.Subject;

/**
 * Action which prints to the standard output the median of all elements in the collection.
 * @author Viran
 *
 */
public class PrintMedianAction implements IAction {

	public PrintMedianAction(Subject subject) {
		subject.register(this);
	}
	
	@Override
	public void performAction(Collection<Integer> collection) {
		PercentileDeterminer<Integer> percentil=new LIBCRPercentile(collection.toArray(new Integer[collection.size()]));
		System.out.println("Median of the collection elemnts is now: "+percentil.calculatePercentile(50)+" .");
	}

}
