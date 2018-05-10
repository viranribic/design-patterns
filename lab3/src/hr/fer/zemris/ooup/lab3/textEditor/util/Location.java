package hr.fer.zemris.ooup.lab3.textEditor.util;

public class Location implements ImmutableLocation , Comparable<Location>{

	private int row;
	private int column;
	
	public Location(int row, int column) {
		this.row=row;
		this.column=column;
	}
	
	@Override
	public int getRow() {
		return row;
	}
	
	public Location setRow(int row) {
		this.row = row;
		return this;
	}

	@Override
	public int getColumn() {
		return column;
	}

	public Location setColumn(int column) {
		this.column = column;
		return this;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
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
		Location other = (Location) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	@Override
	public int compareTo(Location o) {
		int compRows=Integer.compare(this.row, o.row);
		if(compRows==0){
			return Integer.compare(this.column, o.column);
		}else
			return compRows;
		
	}
	
	public Location copy(){
		return new Location(this.row, this.column);
	}
	
	@Override
	public String toString() {
		return "R: "+this.row+" C:"+this.column;
	}

}
