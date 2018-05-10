package hr.fer.zemris.ooup.lab3.textEditor.util;

public class LocationRange {

	private Location from;
	private Location to;
	private boolean active;

	public LocationRange() {
		this(new Location(0, 0), new Location(0, 0), false);
	}

	public LocationRange(Location from, Location to, boolean active) {
		this.from = from;
		this.to = to;
		this.active = active;
	}

	public Location getFrom() {
		return from;
	}

	public void setFrom(Location from) {
		this.from = from;
	}

	public Location getTo() {
		return to;
	}

	public void setTo(Location to) {
		this.to = to;
	}


//	public boolean isActive() {
//		return this.active;
//	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void set(Location from, Location to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		LocationRange other = (LocationRange) obj;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "From: " + this.from + " To: " + this.to;
	}

	public boolean isInRange(Location location) {
		int locRow = location.getRow();
		if (this.from.getRow() <= locRow && locRow <= this.to.getRow()) {

			int locCol = location.getColumn();
			if (this.from.getColumn() <= locCol && locCol <= this.to.getColumn())
				return true;
			else
				return false;

		} else
			return false;
	}
	
	public LocationRange copy(){
		return new LocationRange(from.copy(), to.copy(), false);
	}
}
