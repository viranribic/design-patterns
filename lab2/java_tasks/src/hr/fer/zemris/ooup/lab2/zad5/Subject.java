package hr.fer.zemris.ooup.lab2.zad5;

import hr.fer.zemris.ooup.lab2.zad5.actions.IAction;

public interface Subject {

	public void register(IAction observer);
	public void unregister(IAction observer);
	public void notifyObservers();
	
}
