package hr.fer.zemris.ooup.lab2.zad6;

/**
 * Database query parameters.
 * @author Viran
 *
 */
public class Param {
	public String table;
	public String column;
	public String key;
	
	/**
	 * Param constructor.
	 * @param table Table name.
	 * @param column Column name.
	 * @param key  Key name.
	 */
	public Param(String table, String column, String key) {
		this.table=table;
		this.column=column;
		this.key=key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((column == null) ? 0 : column.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((table == null) ? 0 : table.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Param other = (Param) obj;
		if (column == null) {
			if (other.column != null)
				return false;
		} else if (!column.equals(other.column))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (table == null) {
			if (other.table != null)
				return false;
		} else if (!table.equals(other.table))
			return false;
		return true;
	}
	
	
	
}
