package hr.fer.zemris.ooup.lab3.textEditor;

import java.awt.FontMetrics;
import java.util.Iterator;
import java.util.LinkedList;

import hr.fer.zemris.ooup.lab3.textEditor.observerInterface.ClipboardObserver;
import hr.fer.zemris.ooup.lab3.textEditor.observerInterface.CursorObservable;
import hr.fer.zemris.ooup.lab3.textEditor.observerInterface.CursorObserver;
import hr.fer.zemris.ooup.lab3.textEditor.observerInterface.TextObservable;
import hr.fer.zemris.ooup.lab3.textEditor.observerInterface.TextObserver;
import hr.fer.zemris.ooup.lab3.textEditor.undoRedoOperatoins.DeleteAfter;
import hr.fer.zemris.ooup.lab3.textEditor.undoRedoOperatoins.DeleteBefore;
import hr.fer.zemris.ooup.lab3.textEditor.undoRedoOperatoins.DeleteRange;
import hr.fer.zemris.ooup.lab3.textEditor.undoRedoOperatoins.EditAction;
import hr.fer.zemris.ooup.lab3.textEditor.undoRedoOperatoins.InsertText;
import hr.fer.zemris.ooup.lab3.textEditor.undoRedoOperatoins.UndoManager;
import hr.fer.zemris.ooup.lab3.textEditor.util.CursorManager;
import hr.fer.zemris.ooup.lab3.textEditor.util.Location;
import hr.fer.zemris.ooup.lab3.textEditor.util.LocationRange;
import hr.fer.zemris.ooup.lab3.textEditor.util.TextManager;

public class TextEditorModel implements CursorObservable, SelectionObservable,TextObservable, ClipboardObserver, EditActionView {

	// Data
	private LinkedList<String> lines = new LinkedList<>();
	private Location cursorLocation;
	private LocationRange selectionRange;
	private UndoManager undoManager;

	// Observers
	private LinkedList<CursorObserver> cursorObservers = new LinkedList<>();
	private LinkedList<TextObserver> textObservers = new LinkedList<>();
	private LinkedList<SelectionObserver> selectionObservers=new LinkedList<>();
	
	// Clipboard
	private ClipboardStack clipboard;
	

	public TextEditorModel(String text, ClipboardStack clipboard) {
		initNewText(text);
		
		if (clipboard == null)
			throw new NullPointerException("Clipboard object can't be null.");
		this.clipboard = clipboard;
		
		this.undoManager = UndoManager.getManager();
		
	}

	private void initNewText(String text) {
		String[] lines = text.split("\n");
		for (String l : lines)
			this.lines.add(l);
		this.cursorLocation = new Location(this.lines.size() - 1, this.lines.getLast().length()); // last

		this.selectionRange = new LocationRange();
		this.selectionRange.set(cursorLocation.copy(), cursorLocation.copy());
		this.selectionRange.setActive(false);
	}

	/*---------------------------Iterator methods----------------------------------------------*/
	/*-----------------------------------------------------------------------------------------*/
	public Iterable<String> allLines() {
		return new Iterable<String>() {

			@Override
			public Iterator<String> iterator() {
				return lines.iterator();
			}
		};
	}

	public Iterable<String> linesRange(int index1, int index2) {
		if (index1 < 0 || index1 >= index2 || index2 >= this.lines.size())
			throw new IllegalArgumentException("Given index values are not in the list range.");
		return new Iterable<String>() {

			@Override
			public Iterator<String> iterator() {
				return new Iterator<String>() {

					private int index = index1;

					@Override
					public String next() {
						return lines.get(index++);
					}

					@Override
					public boolean hasNext() {
						return index < index2;
					}
				};
			}
		};
	}

	public Iterable<TextRecord> extendedLines() {
		return new TextIterable(this.lines, selectionRange);
	}
	/*-----------------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------*/

	/*---------------------------Cursor movers-------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------*/
	public void moveCursorLeft() {
		CursorManager.moveCursorLeft(this.cursorLocation, this.lines);
		this.notifySelectionObservers();
		this.notifyCursorObservers();

	}

	public void moveCursorRight() {
		CursorManager.moveCursorRight(this.cursorLocation, this.lines);
		this.notifyCursorObservers();
		this.notifySelectionObservers();

	}

	public void moveCursorUp() {
		CursorManager.moveCursorUp(this.cursorLocation, this.lines);
		this.notifyCursorObservers();
		this.notifySelectionObservers();

	}

	public void moveCursorDown() {
		CursorManager.moveCursorDown(this.cursorLocation, this.lines);
		this.notifyCursorObservers();
		this.notifySelectionObservers();
	}

	public int cursorStartInPixelsY(FontMetrics fontMetrics) {
		return fontMetrics.getFont().getSize() * this.cursorLocation.getRow();
	}

	public int cursorStartInPixelsX(FontMetrics fontMetrics) {
		if (this.lines.size() == 0)
			return 0;
		int row, column;
		row = this.cursorLocation.getRow();
		column = this.cursorLocation.getColumn();
		String line = this.lines.get(row);
		if (column == line.length())
			return fontMetrics.stringWidth(line);

		String text = line.substring(0, column);
		return fontMetrics.stringWidth(text);
	}

	/*-----------------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------*/

	/*---------------------------Range management and character deletion-----------------------*/
	/*-----------------------------------------------------------------------------------------*/
	public void deleteBefore() {
		if (!selectionRange.getFrom().equals(selectionRange.getTo())) {
			this.deleteSelectedRange();
		} else {
			EditAction deleteBefore=new DeleteBefore(this);
			deleteBefore.exe_do();
			this.undoManager.actionPerformed(deleteBefore);
		}
		this.notifyTextObservers();
		this.notifyCursorObservers();
	}

	public void deleteAfter() {
		if (!selectionRange.getFrom().equals(selectionRange.getTo())) {
			this.deleteSelectedRange();
		} else {
			EditAction deleteAfter=new DeleteAfter(this);
			deleteAfter.exe_do();
			this.undoManager.actionPerformed(deleteAfter);
		}
		this.notifyTextObservers();
		this.notifyCursorObservers();
	}

	private void deleteSelectedRange() {
		if (!selectionRange.getFrom().equals(selectionRange.getTo())) {
			EditAction deleteRange = new DeleteRange(this);
			deleteRange.exe_do();
			this.undoManager.actionPerformed(deleteRange);
		}
		this.notifySelectionObservers();
	}

	public void setSelectionRange(LocationRange range) {
		this.selectionRange = range;
		this.notifyTextObservers();
		this.notifySelectionObservers();
	}

	/*-----------------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------*/

	/*---------------------------Observable methods--------------------------------------------*/
	/*-----------------------------------------------------------------------------------------*/
	@Override
	public void subscribeCursorObserver(CursorObserver observer) {
		if (!this.cursorObservers.contains(observer))
			this.cursorObservers.add(observer);
	}

	@Override
	public void unsubscribeCursorObserver(CursorObserver observer) {
		if (this.cursorObservers.contains(observer))
			this.cursorObservers.remove(observer);
	}

	private void notifyCursorObservers() {
		for (CursorObserver o : cursorObservers)
			o.updateCursorLocation(this.cursorLocation);
	}

	@Override
	public void subscribeTextObserver(TextObserver observer) {
		if (!this.textObservers.contains(observer))
			this.textObservers.add(observer);
	}

	@Override
	public void unsubscribeTextObserver(TextObserver observer) {
		if (this.textObservers.contains(observer))
			this.cursorObservers.remove(observer);
	}

	private void notifyTextObservers() {
		for (TextObserver o : textObservers)
			o.updateText();
	}
	/*-----------------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------*/

	public void expandRangeLeft() {
		if (selectionRange.getFrom().equals(selectionRange.getTo())) {
			Location to = this.cursorLocation.copy();
			CursorManager.moveCursorLeft(this.cursorLocation, lines);
			Location from = this.cursorLocation.copy();
			this.selectionRange.set(from, to);
		}else{
			CursorManager.moveCursorLeft(this.cursorLocation, lines);
			Location from = this.cursorLocation.copy();
			this.selectionRange.setFrom(from);
		}

		this.notifySelectionObservers();
		this.notifyTextObservers();
		
		// System.out.println("Cursor moved: " + selectionRange);
	}

	public void expandRangeRight() {
		if (selectionRange.getFrom().equals(selectionRange.getTo())) {
			Location from = this.cursorLocation.copy();
			CursorManager.moveCursorRight(this.cursorLocation, lines);
			Location to = this.cursorLocation.copy();
			this.selectionRange.set(from, to);
		}else{
			CursorManager.moveCursorRight(this.cursorLocation, lines);
			Location to = this.cursorLocation.copy();
			this.selectionRange.setTo(to);
		}


		this.notifyTextObservers();
		this.notifySelectionObservers();
		// System.out.println("Cursor moved: " + selectionRange);
	}

	public void expandRangeUp() {
		if (selectionRange.getFrom().equals(selectionRange.getTo())) {
			Location to = this.cursorLocation.copy();
			CursorManager.moveCursorUp(this.cursorLocation, lines);
			Location from = this.cursorLocation.copy();
			this.selectionRange.set(from, to);
		}else{
			CursorManager.moveCursorUp(this.cursorLocation, lines);
			Location from = this.cursorLocation.copy();
			this.selectionRange.setFrom(from);
		}
		

		this.notifyTextObservers();
		this.notifySelectionObservers();
		// System.out.println("Cursor moved: " + selectionRange);
	}

	public void expandRangeDown() {
		if (selectionRange.getFrom().equals(selectionRange.getTo())) {
			Location from = this.cursorLocation.copy();
			CursorManager.moveCursorDown(this.cursorLocation, lines);
			Location to = this.cursorLocation.copy();
			this.selectionRange.set(from, to);
		}else{
			CursorManager.moveCursorDown(this.cursorLocation, lines);
			Location to = this.cursorLocation.copy();
			this.selectionRange.setTo(to);
		}

		this.notifyTextObservers();
		this.notifySelectionObservers();
		// System.out.println("Cursor moved: " + selectionRange);
	}

	public void restartRange() {
		this.selectionRange.set(cursorLocation.copy(), cursorLocation.copy());
		this.notifySelectionObservers();
		
	}

	public void insert(char character) {
		this.insert(character + "");
	}

	public void insert(String text) {
		EditAction insertAction = new InsertText(this, text);
		insertAction.exe_do();
		this.undoManager.actionPerformed(insertAction);
		this.notifyTextObservers();
		this.notifySelectionObservers();
	}

	@Override
	public void updateClipboard() {
		this.notifyTextObservers();
	}

	public void copySelectionToClipboard() {
		String selection = TextManager.getRangeContent(selectionRange, cursorLocation, lines);
		this.clipboard.push(selection);
		this.clipboard.notifyObservers();
	}

	public void cutSelectionToClipboard() {
		String selection = TextManager.getRangeContent(selectionRange, cursorLocation, lines);
		if(!selection.equals("")){
			this.clipboard.push(selection);
			this.deleteSelectedRange();
			this.clipboard.notifyObservers();
			this.notifyCursorObservers();
			this.notifyTextObservers();		
			this.notifySelectionObservers();
		}
	}

	public void pasteAndTake() {
		String selection = this.clipboard.pop();
		this.insert(selection);
		this.clipboard.notifyObservers();
	}

	public void paste() {
		String selection = this.clipboard.peek();
		this.insert(selection);
		this.clipboard.notifyObservers();
	}

	@Override
	public Location getCursorLocation() {
		return this.cursorLocation;
	}

	@Override
	public LocationRange getSelectionRange() {
		return this.selectionRange;
	}

	@Override
	public LinkedList<String> getLines() {
		return this.lines;
	}

	public void undo() {
		this.undoManager.undo();
		this.notifyTextObservers();
		this.notifyCursorObservers();
		this.notifySelectionObservers();
	}

	public void redo() {
		this.undoManager.redo();
		this.notifyTextObservers();
		this.notifyCursorObservers();
		this.notifySelectionObservers();
	}

	public String[] getTextModel() {
		String[] lines = new String[this.lines.size()];
		int i=0;
		for(String line:this.lines)
			lines[i++]=line+"\n";
		return lines;
	}

	public void setNewText(String text) {
		this.lines.clear();
		initNewText(text);
		this.undoManager.clearAllStacks();
		this.notifyCursorObservers();
		this.notifyTextObservers();
		this.notifySelectionObservers();
	}

	public void deleteRange() {
		this.deleteSelectedRange();
		this.notifyCursorObservers();
		this.notifyTextObservers();
	}

	public void clearDocument() {
		Location beginning=new Location(0, 0);
		Location end=new Location(this.lines.size()-1, this.lines.getLast().length());
		this.selectionRange.set(beginning, end);
		this.deleteSelectedRange();
		this.notifyCursorObservers();
		this.notifyTextObservers();
	}

	public void moveCursorToStart() {
		this.cursorLocation.setRow(0).setColumn(0);
		this.notifyCursorObservers();
		this.notifySelectionObservers();
	}

	public void moveCursorToEnd() {
		this.cursorLocation.setRow(this.lines.size()-1).setColumn(this.lines.getLast().length());
		this.notifyCursorObservers();
		this.notifySelectionObservers();
	}

	@Override
	public void subscibeSelectionObserver(SelectionObserver observer) {
		if (!this.selectionObservers.contains(observer))
			this.selectionObservers.add(observer);
		
	}

	@Override
	public void unsubsscibeSelectionObserver(SelectionObserver observer) {
		if (this.selectionObservers.contains(observer))
			this.selectionObservers.remove(observer);
	}
	
	private void notifySelectionObservers(){
		for(SelectionObserver o:selectionObservers)
			o.updateSelectionObserver();
	}

	public boolean hasSelectedRange() {
		return !this.selectionRange.getFrom().equals(this.selectionRange.getTo());
	}

}
