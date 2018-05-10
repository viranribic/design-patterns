package hr.fer.zemris.ooup.lab3.textEditor.undoRedoOperatoins;

import hr.fer.zemris.ooup.lab3.textEditor.EditActionView;
import hr.fer.zemris.ooup.lab3.textEditor.util.TextManager;

public class DeleteBefore implements EditAction {

	private EditActionView modelView;
	private InsertText undoAction;
	private String symbol;
	
	public DeleteBefore(EditActionView modelView) {
		this(modelView,null);
	}

	public DeleteBefore(EditActionView modelView,InsertText undoAction) {
		this.modelView=modelView;
		this.undoAction=undoAction;
	}
	
	@Override
	public void exe_do() {
			this.symbol=TextManager.deleteBefore(modelView.getCursorLocation(), modelView.getLines());
	}

	@Override
	public void exe_undo() {
		if(this.undoAction==null)
			this.undoAction=new InsertText(modelView,symbol,this);
		this.undoAction.exe_do();
	}

	@Override
	public String toString() {
		return "DeleteBefore\nSymbol: "+symbol+"\n";
	}
}
