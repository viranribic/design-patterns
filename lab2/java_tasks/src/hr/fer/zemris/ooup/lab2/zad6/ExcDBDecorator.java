package hr.fer.zemris.ooup.lab2.zad6;

/**
 * ExcDBDecorator
 * @author Viran
 *
 */
public class ExcDBDecorator implements MyDBase {

	private MyDBase mydbase;
	
	/**
	 * 
	 * @param mydbase
	 */
	public ExcDBDecorator(MyDBase mydbase) {
		this.mydbase=mydbase;
	}
	
	@Override
	public int query(Param p) {
		int res=this.mydbase.query(p);
		if(res<0)
			throw new DatabaseErrorException("There was an error while making a query to database.",res);
		return res;
	}

	@Override
	public void close() {
		mydbase.close();
	}

}
