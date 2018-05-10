package hr.fer.zemris.ooup.lab3.textEditor.undoRedoOperatoins;

import hr.fer.zemris.ooup.lab3.textEditor.EditActionView;
import hr.fer.zemris.ooup.lab3.textEditor.util.CursorManager;
import hr.fer.zemris.ooup.lab3.textEditor.util.Location;
import hr.fer.zemris.ooup.lab3.textEditor.util.LocationRange;
import hr.fer.zemris.ooup.lab3.textEditor.util.TextManager;

public class DeleteRange implements EditAction{

	private EditActionView modelView;
	private EditAction undoAction;
	private String text;

	public DeleteRange(EditActionView modelView) {
		this(modelView,null);
	}

	public DeleteRange(EditActionView modelView, EditAction undoAction) {
		this.modelView=modelView;
		this.undoAction=undoAction;
	}

	@Override
	public void exe_do() {		
		this.text=TextManager.deleteRange(modelView.getSelectionRange(), modelView.getCursorLocation(), modelView.getLines());
	}

	@Override
	public void exe_undo() {
		if(this.undoAction==null)
			this.undoAction=new InsertText(modelView,text,this);
		this.undoAction.exe_do();
	}
	
	@Override
	public String toString() {
		return "DeleteRange\nText:\n"+text+"\n";
	}
}
