package hr.fer.zemris.ooup.lab3.textEditor.util;

import java.util.LinkedList;

public class TextManager {

	// ------------------------------------------------------------------
	public static String deleteBefore(Location cursorLocation, LinkedList<String> lines) {
		int column = cursorLocation.getColumn();
		
		int row = cursorLocation.getRow();

		if (column == 0 && row == 0)
			return "";
		if (column == 0) {
			// Concatenate this row with the previous one and delete the current
			// from list
			String oldLine = lines.get(row);
			int nextColumn = lines.get(row - 1).length();
			String newLine = lines.get(row - 1).concat(oldLine);
			lines.set(row - 1, newLine);
			lines.remove(oldLine);
			cursorLocation.setRow(row - 1);
			cursorLocation.setColumn(nextColumn);
			return "\n";
		} else {
			// just remove that character and and change the cursor location
			String oldLine = lines.get(row);
			column--; // column will always be ahead of a character
			StringBuilder sbLine=new StringBuilder(oldLine);
			String deletedCharacter= sbLine.charAt(column)+"";
			String newLine = sbLine.deleteCharAt(column).toString();
			lines.remove(oldLine);
			lines.add(row, newLine);
			cursorLocation.setColumn(column);
			return deletedCharacter;
		}

	}

	public static String deleteAfter(Location location, LinkedList<String> lines) {
		int column = location.getColumn();
		int row = location.getRow();
		String currentLine = lines.get(row);
		if (column == currentLine.length() && row == lines.size() - 1)
			return "";
		if (column == currentLine.length()) {
			// Concatenate this row with the previous one and delete the current
			// from list
			String nextLine = lines.get(row + 1);
			String newLine = currentLine.concat(nextLine);
			lines.set(row, newLine);
			lines.remove(nextLine);
			return "\n";
		} else {
			// just remove that character and and change the cursor location
			String oldLine = lines.get(row);
			StringBuilder sbLine=new StringBuilder(oldLine);
			String deletedCharacter= sbLine.charAt(column)+"";
			String newLine = sbLine.deleteCharAt(column).toString();
			lines.remove(oldLine);
			lines.add(row, newLine);
			return deletedCharacter;
		}

	}
	// ------------------------------------------------------------------

	// ------------------------------------------------------------------
	public static String deleteRange(LocationRange selectionRange, Location cursorLocation, LinkedList<String> lines) {
		Location from = selectionRange.getFrom();
		Location to = selectionRange.getTo();
		int compRes = from.compareTo(to);
		if (compRes == 0)
			return "";
		else if (compRes > 0) {
			// to is before from
			Location tmp = from;
			from = to;
			to = tmp;
		}
		// update the selection and cursor data
		selectionRange.set(from.copy(), from.copy());
		cursorLocation.setRow(from.getRow()).setColumn(from.getColumn());
		//
		int columnFrom = from.getColumn();
		int rowFrom = from.getRow();
		int columnTo = to.getColumn();
		int rowTo = to.getRow();

		// delete the section of the selected
		if (rowFrom == rowTo) {
			return deleteSameSelectionRow(selectionRange, cursorLocation, lines, columnFrom, columnTo, rowFrom);
		} else {
			return deleteDifferentSelectionRows(selectionRange, cursorLocation, lines, columnFrom, columnTo, rowFrom,
					rowTo);
		}

	}

	private static String deleteSameSelectionRow(LocationRange selectionRange, Location cursorLocation,
			LinkedList<String> lines, int columnFrom, int columnTo, int row) {
		if (columnFrom == columnTo)
			return "";

		String line = lines.get(row);
		String textBefore = line.substring(0, columnFrom);
		String textSelected = line.substring(columnFrom, columnTo);
		String textAfter = line.substring(columnTo);
		lines.remove(row);
		lines.add(row, textBefore + textAfter);
		return textSelected;
	}

	private static String deleteDifferentSelectionRows(LocationRange selectionRange, Location cursorLocation,
			LinkedList<String> lines, int columnFrom, int columnTo, int rowFrom, int rowTo) {
		String stringFrom = null;
		String stringFromUnselected = null;
		String stringFromSelected = null;
		String stringTo = null;
		String stringToUnselected = null;
		String stringToSelected = null;
		LinkedList<String> linesToBeRemoved = new LinkedList<>();

		// Part 1: Extraction
		int rowCursor = rowFrom;
		// process beginning row
		if (columnFrom > 0) {
			stringFrom = lines.get(rowCursor);
			stringFromUnselected = stringFrom.substring(0, columnFrom);
			stringFromSelected = stringFrom.replace(stringFromUnselected, "");
			rowCursor++;
		}

		// process other rows
		while (rowCursor < rowTo) {
			// delete all of these lines
			linesToBeRemoved.add(lines.get(rowCursor));
			rowCursor++;
		}

		// process last row
		if (columnTo <= lines.get(rowCursor).length()) {
			// delete the section of the selected
			stringTo = lines.get(rowCursor);
			stringToSelected = stringTo.substring(0, columnTo);
			stringToUnselected = stringTo.replace(stringToSelected, "");
		}

		// Part 2: Deletion
		for (int delCursor = rowTo; delCursor >= rowFrom; delCursor--)
			lines.remove(delCursor);

		// Part 3: Re-insertion
		int insertCursor = rowFrom;
		if (stringFromUnselected != null) {
			// If this if statement evaluates true-> the from cursor location
			// becomes out of range!
			// if (stringFromUnselected.length() != 0) {
			lines.add(insertCursor, stringFromUnselected);
			insertCursor++;

			// }
		}

		if (stringToUnselected != null) {
			if (stringToUnselected.length() != 0) {
				lines.add(insertCursor, stringToUnselected);
				insertCursor++;

			}
		}
		// if the from row was simply deleted without reinsertions add a simple
		// "" to the beggining
		if (insertCursor == rowFrom)
			lines.add(insertCursor, "");

		// TODO-------------------------------------------------------------------------------

		// int row = rowFrom;
		// System.out.println((row) + " Unselected before: " +
		// stringFromUnselected);
		// System.out.println((row++) + " Selected before: " +
		// stringFromSelected);
		// for (String selected : linesToBeRemoved)
		// System.out.println((row++) + " Selected: " + selected);
		// System.out.println((row++) + " Selected after: " + stringToSelected);
		// System.out.println((row) + " Unselected after: " +
		// stringToUnselected);
		// System.out.println();
		// TODO-------------------------------------------------------------------------------

		StringBuilder sb = new StringBuilder();
		if (stringFromSelected != null)
			sb.append(stringFromSelected).append("\n");
		for (String selected : linesToBeRemoved)
			sb.append(selected).append("\n");
		if (stringToSelected != null)
			sb.append(stringToSelected).append("\n");

		return sb.toString();

	}
	// ------------------------------------------------------------------

	// ------------------------------------------------------------------
	public static String getRangeContent(LocationRange selectionRange, Location cursorLocation,
			LinkedList<String> lines) {
		int columnFrom = selectionRange.getFrom().getColumn();
		int rowFrom = selectionRange.getFrom().getRow();
		int columnTo = selectionRange.getTo().getColumn();
		int rowTo = selectionRange.getTo().getRow();

		// delete the section of the selected
		if (rowFrom == rowTo) {
			return getSameSelectionRow(selectionRange, cursorLocation, lines, columnFrom, columnTo, rowFrom);
		} else {
			return getDiferentSelectionRows(selectionRange, cursorLocation, lines, columnFrom, columnTo, rowFrom,
					rowTo);
		}

	}

	private static String getDiferentSelectionRows(LocationRange selectionRange, Location cursorLocation,
			LinkedList<String> lines, int columnFrom, int columnTo, int rowFrom, int rowTo) {

		String stringFromSelected = null;
		String stringToSelected = null;

		int rowFormCounter = rowFrom;
		LinkedList<String> linesToConcatinate = new LinkedList<>();
		// all this if the selection is spaced by one or more rows

		if (columnFrom > 0) {
			String stringFrom = lines.get(rowFrom);
			String stringFromUnselected = stringFrom.substring(0, columnFrom);
			stringFromSelected = stringFrom.replace(stringFromUnselected, "");
			rowFormCounter++;

		}

		while (rowFormCounter < rowTo) {
			// delete all of these lines
			linesToConcatinate.add(lines.get(rowFormCounter));
			rowFormCounter++;
		}
		if (columnTo > 0) {
			// delete the section of the selected
			String stringTo = lines.get(rowTo);
			stringToSelected = stringTo.substring(0, columnTo);

		}

		StringBuilder selection = new StringBuilder();
		selection.append(stringFromSelected).append("\n");
		for (String s : linesToConcatinate)
			selection.append(s).append("\n");
		selection.append(stringToSelected).append("\n");
		return selection.toString();

	}

	private static String getSameSelectionRow(LocationRange selectionRange, Location cursorLocation,
			LinkedList<String> lines, int columnFrom, int columnTo, int row) {
		if (columnFrom == columnTo)
			return "";

		String line = null;
		String textSelected = null;

		// all this if the selection is spaced by one or more rows
		line = lines.get(row);

		if (columnFrom == 0) {
			textSelected = line.substring(0, columnTo);

		} else if (0 < columnFrom && columnFrom < line.length()) {
			textSelected = line.substring(columnFrom, columnTo);

		} else {
			// simply remove the whole line
		}
		textSelected = (textSelected == null) ? "" : textSelected;
		return textSelected;
	}
	// ------------------------------------------------------------------

	// ------------------------------------------------------------------
	public static void insert(String text, Location cursorLocation, LinkedList<String> lines) {
		int row = cursorLocation.getRow();

		if (text.contains("\n")) {

			insertWithNewLiners(text, row, lines, cursorLocation);

		} else {
			int lineSize=text.length();
			String line = TextManager.insertNewString(cursorLocation, lines, text);
			lines.set(row, line);
			int column=cursorLocation.getColumn();
			cursorLocation.setRow(row).setColumn(column+lineSize);
			
		}
	}

	private static String insertNewString(Location cursorLocation, LinkedList<String> lines, String text) {

		String line = lines.get(cursorLocation.getRow());
		if (line.length() == 0) {
			return text;
		} else {

			int column = cursorLocation.getColumn();

			String beforeString = null;
			if (column > 0) {
				beforeString = line.substring(0, column);
			}

			String afterString = null;
			if (column <= line.length())
				afterString = line.substring(column, line.length());
			line = null;

			if (beforeString != null) {
				if (afterString != null) {
					line = beforeString.concat(text.concat(afterString));
				} else {
					line = beforeString.concat(text);
				}
			} else {
				if (afterString != null) {
					line = text.concat(afterString);
				} else {
					line = text;
				}
			}
			return line;

		}
	}

	private static void insertWithNewLiners(String text, int row, LinkedList<String> lines, Location cursorLocation) {
		String line = TextManager.insertNewString(cursorLocation, lines, text);
		lines.remove(row); // the row is already contained in line

		String[] splittedLines = line.split("\n");
		int cursor = 0;
		for (; cursor < splittedLines.length; cursor++) {
			if (splittedLines[cursor].length() == 0) {
				lines.add(row + cursor, "");
			} else {
				lines.add(row + cursor, splittedLines[cursor]);
			}
		}

		if (line.endsWith("\n")) {
			lines.add(row + cursor, "");
			cursor++;
		}

		row += cursor - 1;
		int column = lines.get(row).length();
		cursorLocation.setRow(row).setColumn(column);
	}
	// ------------------------------------------------------------------

}
