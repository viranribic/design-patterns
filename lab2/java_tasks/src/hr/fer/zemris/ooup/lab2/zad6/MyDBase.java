package hr.fer.zemris.ooup.lab2.zad6;

/**
 * My database interface..
 * @author Viran
 *
 */
public interface MyDBase {
	
	/**
	 * Make a query on this database.
	 * @param p User query.
	 * @return Operation status.
	 */
	public int query(Param p);

	/**
	 * Make the necessary operations for closing this database.
	 */
	public void close();
}
