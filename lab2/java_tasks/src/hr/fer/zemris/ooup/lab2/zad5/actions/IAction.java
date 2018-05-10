package hr.fer.zemris.ooup.lab2.zad5.actions;

import java.util.Collection;
/**
 * Interface for an action performing object.
 * @author Viran
 *
 */
public interface IAction {

	/**
	 * Perform an action using the given collection.
	 * @param collection Collection used in making the action.
	 */
	public void performAction(Collection<Integer> collection);
	
	
}
