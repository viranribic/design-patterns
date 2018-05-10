package hr.fer.zemris.ooup.lab3.textEditor.undoRedoOperatoins;

import java.util.LinkedList;
import java.util.Stack;

public class UndoManager implements UndoStackObservable, RedoStackObservervable{

	private Stack<EditAction> undoStack;
	private Stack<EditAction> redoStack;
	private static UndoManager manager;
	private LinkedList<StackObserver> undoObservers;
	private LinkedList<StackObserver> redoObservers;
	
	private UndoManager() {
		this.undoStack=new Stack<>();
		this.redoStack=new Stack<>();
		this.undoObservers=new LinkedList<StackObserver>();
		this.redoObservers=new LinkedList<StackObserver>();
	}
	
	public static UndoManager getManager(){
		if(manager==null)
			manager=new UndoManager();
		return manager;
	}
	
	public void undo(){
		if(!this.undoStack.isEmpty()){
			EditAction action=this.undoStack.pop();
			action.exe_undo();
			this.redoStack.push(action);
			this.notiyUndoObservers();
			this.notiyRedoObservers();
		}
	}
	
	public void redo(){
		if(!this.redoStack.isEmpty()){
			EditAction action=this.redoStack.pop();
			action.exe_do();
			this.undoStack.push(action);
			this.notiyUndoObservers();
			this.notiyRedoObservers();
		}
	}
	
	public void actionPerformed(EditAction action){
		if(!this.redoStack.isEmpty())
			this.redoStack.clear();
		this.undoStack.push(action);
		this.notiyUndoObservers();
		this.notiyRedoObservers();
		
	}

	private void notiyRedoObservers() {
		for(StackObserver o:redoObservers)
			o.update();
	}

	private void notiyUndoObservers() {
		for(StackObserver o:undoObservers)
			o.update();
	}

	@Override
	public void subscribeRedo(StackObserver observer) {
		if(!this.redoObservers.contains(observer))
			this.redoObservers.add(observer);
	}

	@Override
	public void unsubscribeRedo(StackObserver observer) {
		this.redoObservers.remove(observer);
	}

	@Override
	public boolean isRedoEmpty() {
		return this.redoStack.isEmpty();
	}

	@Override
	public void subscribeUndo(StackObserver observer) {
		if(!this.undoObservers.contains(observer))
			this.undoObservers.add(observer);
	}

	@Override
	public void unsubscribeUndo(StackObserver observer) {
		this.undoObservers.remove(observer);
	}

	@Override
	public boolean isUndoEmpty() {
		return this.undoStack.isEmpty();
	}

	@Override
	public Stack<EditAction> getRedoStack() {
		return this.redoStack;
	}

	@Override
	public Stack<EditAction> getUndoStack() {
		return this.undoStack;
	}

	public void clearAllStacks() {
		this.redoStack.clear();
		this.undoStack.clear();
	}
	
}
