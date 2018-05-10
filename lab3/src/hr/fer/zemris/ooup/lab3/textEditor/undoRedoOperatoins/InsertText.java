package hr.fer.zemris.ooup.lab3.textEditor.undoRedoOperatoins;

import hr.fer.zemris.ooup.lab3.textEditor.EditActionView;
import hr.fer.zemris.ooup.lab3.textEditor.util.Location;
import hr.fer.zemris.ooup.lab3.textEditor.util.LocationRange;
import hr.fer.zemris.ooup.lab3.textEditor.util.TextManager;

public class InsertText implements EditAction {

	private EditActionView modelView;
	private String text;
	private LocationRange range;
	private EditAction undoAction;
	
	public InsertText(EditActionView modelView, char symobl) {
		this(modelView, symobl+"",null);
	}
	
	public InsertText(EditActionView modelView, char symobl ,EditAction undoAction) {
		this(modelView, symobl+"",undoAction);
	}
	
	public InsertText(EditActionView modelView, String text) {
		this(modelView,text,null);
	}
	
	public InsertText(EditActionView modelView, String text ,EditAction undoAction) {
		this.modelView=modelView;
		this.text=text;
		this.undoAction=undoAction;
	}
	


	@Override
	public void exe_do() {
			Location from=this.modelView.getCursorLocation().copy();
			TextManager.insert(text, this.modelView.getCursorLocation(), this.modelView.getLines());
			Location to=this.modelView.getCursorLocation().copy();
			this.range=new LocationRange(from, to, false);
			this.modelView.getSelectionRange().set(range.getFrom(), range.getTo());
	}

	@Override
	public void exe_undo() {
		if(this.undoAction==null)
			this.undoAction=new DeleteRange(modelView,this);
		this.modelView.getSelectionRange().set(range.getFrom(), range.getTo());
		this.undoAction.exe_do();
	}
	
	@Override
	public String toString() {
		return "Insert\nText:\n"+text+"\n";
	}

}
