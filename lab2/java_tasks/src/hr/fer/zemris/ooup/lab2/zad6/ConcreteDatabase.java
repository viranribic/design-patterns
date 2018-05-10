package hr.fer.zemris.ooup.lab2.zad6;

import java.util.Map;


public class ConcreteDatabase implements MyDBase{

	private Map<Param, String> mapDB;
	
	public ConcreteDatabase(Map<Param, String> mapDB) {
		this.mapDB=mapDB;
	}
	

	@Override
	public int query(Param p) {
		String queryRes=mapDB.get(p);
		if(queryRes==null)
			return -1;
		System.out.println("Query result: "+queryRes);
		return 1;
	}


	@Override
	public void close() {
	}

}
