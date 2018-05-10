package hr.fer.zemris.ooup.lab3.textEditor;

public class TextRecord {

	private String text;
	private boolean isSelected;
	private int row;

	public TextRecord(String text, boolean isSelected, int row) {
		this.text = text;
		this.isSelected = isSelected;
		this.row=row;
	}

	public String text() {
		return this.text;
	}

	public boolean isSelected() {
		return isSelected;
	}
	
	public int getRow(){
		return row;
	}

}
