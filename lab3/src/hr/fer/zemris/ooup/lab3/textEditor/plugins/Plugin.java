package hr.fer.zemris.ooup.lab3.textEditor.plugins;

import hr.fer.zemris.ooup.lab3.textEditor.ClipboardStack;
import hr.fer.zemris.ooup.lab3.textEditor.TextEditorModel;
import hr.fer.zemris.ooup.lab3.textEditor.undoRedoOperatoins.UndoManager;

public interface Plugin {

	String getName();
	String getDescription();
	void execute(TextEditorModel model, UndoManager undoManager, ClipboardStack clipboardStack);

}
