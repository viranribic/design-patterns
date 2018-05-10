package hr.fer.zemris.ooup.lab3.textEditor;

import java.util.Iterator;
import java.util.LinkedList;

import hr.fer.zemris.ooup.lab3.textEditor.util.LocationRange;

public class TextIterable implements Iterable<TextRecord> {

	private LinkedList<TextRecord> records = new LinkedList<>();

	public TextIterable(LinkedList<String> lines, LocationRange range) {
		int selectionStartRow = range.getFrom().getRow();
		int selectionStartColumn = range.getFrom().getColumn();
		int selectionEndRow = range.getTo().getRow();
		int selectionEndColumn = range.getTo().getColumn();

		boolean isInRange = false;
		for (int row = 0; row < lines.size(); row++) {
			String lineText = lines.get(row);
			if (row == selectionStartRow) {
				if (selectionStartRow == selectionEndRow) {
					sameStartEndRow(lineText, selectionStartColumn, selectionEndColumn, isInRange, row);
					isInRange = false;
				} else {
					onlyStartRow(lineText, selectionStartColumn, isInRange, row);
					isInRange = true;
				}
			} else if (row == selectionEndRow) {
				onlyEndRow(lineText, selectionEndColumn, isInRange, row);
				isInRange = false;
			} else {
				this.records.add(new TextRecord(lineText, isInRange, row));
			}
		}
	}

	private void onlyEndRow(String lineText, int selectionEndColumn, boolean isInRange, int row) {
		if (0 < selectionEndColumn) {
			String beforeSelection = lineText.substring(0, selectionEndColumn);
			this.records.add(new TextRecord(beforeSelection, isInRange, row));

		} 
		
		isInRange = false;

		if (selectionEndColumn < lineText.length()) {
			String selection = lineText.substring(selectionEndColumn, lineText.length());
			this.records.add(new TextRecord(selection, isInRange, row));

		} else
			return;
		
	}

	private void onlyStartRow(String lineText, int selectionStartColumn, boolean isInRange, int row) {
		if (0 < selectionStartColumn) {
			String beforeSelection = lineText.substring(0, selectionStartColumn);
			this.records.add(new TextRecord(beforeSelection, isInRange, row));

		} 
		
		isInRange = true;

		if (selectionStartColumn < lineText.length()) {
			String selection = lineText.substring(selectionStartColumn, lineText.length());
			this.records.add(new TextRecord(selection, isInRange, row));

		} else
			return;
		
	}

	private void sameStartEndRow(String lineText, int selectionStartColumn, int selectionEndColumn, boolean isInRange,
			int row) {
		if(selectionEndColumn==selectionStartColumn){
			this.records.add(new TextRecord(lineText, isInRange, row));
			return;
		}
		if(0 == selectionStartColumn){
			isInRange = true;
			midString(lineText, selectionStartColumn, selectionEndColumn, isInRange, row);
			return;
		}else if (0 < selectionStartColumn&& selectionStartColumn<=lineText.length()) {
			String beforeSelection = lineText.substring(0, selectionStartColumn);
			this.records.add(new TextRecord(beforeSelection, isInRange, row));

			isInRange = true;

			midString(lineText, selectionStartColumn, selectionEndColumn, isInRange, row);
		} 
		
		
	}

	private void midString(String lineText, int selectionStartColumn, int selectionEndColumn, boolean isInRange,
			int row) {
		if (selectionStartColumn < selectionEndColumn && selectionEndColumn <= lineText.length()) {
			String selection = lineText.substring(selectionStartColumn, selectionEndColumn);
			this.records.add(new TextRecord(selection, isInRange, row));

		} else{
			System.out.println("Unexpected mid 1."); return;
		}
		
		isInRange = false;
		if (selectionEndColumn <= lineText.length()) {
			String afterSelection = lineText.substring(selectionEndColumn, lineText.length());
			this.records.add(new TextRecord(afterSelection, isInRange, row));

		} else{
			System.out.println("Unexpected mid 2."); return;
		}
	}

	@Override
	public Iterator<TextRecord> iterator() {
		return this.records.iterator();
	}

}
