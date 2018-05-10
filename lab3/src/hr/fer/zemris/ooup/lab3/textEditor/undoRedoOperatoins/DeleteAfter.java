package hr.fer.zemris.ooup.lab3.textEditor.undoRedoOperatoins;

import hr.fer.zemris.ooup.lab3.textEditor.EditActionView;
import hr.fer.zemris.ooup.lab3.textEditor.util.CursorManager;
import hr.fer.zemris.ooup.lab3.textEditor.util.TextManager;

public class DeleteAfter implements EditAction {

	private EditActionView modelView;
	private InsertText undoAction;
	private String symbol;

	public DeleteAfter(EditActionView modelView) {
		this(modelView, null);
	}

	public DeleteAfter(EditActionView modelView, InsertText undoAction) {
		this.modelView = modelView;
		this.undoAction = undoAction;
	}

	@Override
	public void exe_do() {
		this.symbol = TextManager.deleteAfter(modelView.getCursorLocation(), modelView.getLines());
	}

	@Override
	public void exe_undo() {
		if (this.undoAction == null)
			this.undoAction = new InsertText(modelView, symbol, this);
		this.undoAction.exe_do();
		CursorManager.moveCursorLeft(modelView.getCursorLocation(), modelView.getLines());
	}

	@Override
	public String toString() {
		return "DeleteAfter\nSymbol: " + symbol + "\n";
	}
}
