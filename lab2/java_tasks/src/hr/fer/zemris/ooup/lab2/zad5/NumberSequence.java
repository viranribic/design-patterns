package hr.fer.zemris.ooup.lab2.zad5;

import java.util.LinkedList;

import hr.fer.zemris.ooup.lab2.zad5.actions.IAction;
import hr.fer.zemris.ooup.lab2.zad5.dataSrcs.INumberSource;

public class NumberSequence implements Runnable, Subject {

	private LinkedList<IAction> actions = new LinkedList<>();
	private LinkedList<Integer> collection = new LinkedList<>();
	private INumberSource source;

	public NumberSequence(INumberSource source) {
		this.source = source;
	}

	@Override
	public void run() {
		while (true) {
			//read next element
			Integer number = source.generateNext();
			//flow control
			if (number == -1)
				break;
			//collection update
			collection.add(number);
			
			notifyObservers();
			
			//one second wait
			synchronized (this) {
				try {
					this.wait(1000); // one second
				} catch (InterruptedException ignorable) {
					// no special handling for this exception
				}	
			}
			
		}
	}

	public void register(IAction action) {
		this.actions.add(action);
	}
	
	public void unregister(IAction action) {
		this.actions.remove(action);
	}
	
	
	public void notifyObservers(){
		//action performer
		for(IAction a:actions)
			a.performAction(collection);
		
	}

}
