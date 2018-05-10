package hr.fer.zemris.ooup.lab3.textEditor.undoRedoOperatoins;

import java.util.LinkedList;
import java.util.Stack;

public interface UndoStackObservable {
	void subscribeUndo(StackObserver observer);
	void unsubscribeUndo(StackObserver observer);
	boolean isUndoEmpty();
	Stack<EditAction> getUndoStack();
}
