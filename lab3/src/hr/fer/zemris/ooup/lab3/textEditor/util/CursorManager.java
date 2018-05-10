package hr.fer.zemris.ooup.lab3.textEditor.util;

import java.util.LinkedList;

public class CursorManager {

	public static void moveCursorLeft(Location cursorLocation, LinkedList<String> lines) {
		
		int column = cursorLocation.getColumn();
		if (column == 0) {
			int row = cursorLocation.getRow();
			if (row == 0)
				return; // the cursor is already at the beginning
			else {
				cursorLocation.setRow(row - 1); // move "up" one row...
				cursorLocation.setColumn(lines.get(row - 1).length());// ...and
																			// position
																			// to
																			// the
																			// end
			}
		} else
			cursorLocation.setColumn(column - 1);

	}
	
	public static void moveCursorRight(Location cursorLocation, LinkedList<String> lines)  {
	
		int column = cursorLocation.getColumn();
		int row = cursorLocation.getRow();

		if (column == lines.get(row).length()) {
			if (row == lines.size() - 1)
				return; // the cursor is now at the end
			else {
				cursorLocation.setRow(row + 1); // move "down" one row...
				cursorLocation.setColumn(0);// ...and position at the
													// beginning
			}
		} else
			cursorLocation.setColumn(column + 1);

	}

	public static void moveCursorUp(Location cursorLocation, LinkedList<String> lines)  {
		
		int row = cursorLocation.getRow();
		if (row == 0){
			cursorLocation.setColumn(0);
		}else {
			int column = cursorLocation.getColumn();
			if (column <= lines.get(row - 1).length()) {
				cursorLocation.setRow(row - 1); // move "up" one row...
			} else {
				cursorLocation.setRow(row - 1); // move "up" one row...
				cursorLocation.setColumn(lines.get(row - 1).length());// ...and
																				// position
																				// to
																				// the
																				// end
			}
		}
	}

	public static void moveCursorDown(Location cursorLocation, LinkedList<String> lines)  {
		
		int row = cursorLocation.getRow();
		if (row == lines.size() - 1)
			cursorLocation.setColumn(lines.get(row).length());
		else {
			int column = cursorLocation.getColumn();
			if (column <= lines.get(row + 1).length()) {
				cursorLocation.setRow(row + 1); // move "up" one row...
			} else {
				cursorLocation.setRow(row + 1); // move "up" one row...
				cursorLocation.setColumn(lines.get(row + 1).length());// ...and
																				// position
																				// to
																				// the
																				// end
			}
		}
	}

}
