package hr.fer.zemris.ooup.lab3.textEditor.undoRedoOperatoins;

import java.util.Stack;

public interface RedoStackObservervable {

	void subscribeRedo(StackObserver observer);
	void unsubscribeRedo(StackObserver observer);
	boolean isRedoEmpty();
	Stack<EditAction> getRedoStack();
}
