package hr.fer.zemris.ooup.lab3.textEditor;

import java.util.LinkedList;
import java.util.Stack;

import hr.fer.zemris.ooup.lab3.textEditor.observerInterface.ClipboardObservable;
import hr.fer.zemris.ooup.lab3.textEditor.observerInterface.ClipboardObserver;

public class ClipboardStack implements ClipboardObservable {

	private Stack<String> textStack = new Stack<>();
	private LinkedList<ClipboardObserver> observers = new LinkedList<>();

	public String pop() {
		if (!this.textStack.isEmpty())
			return this.textStack.pop();
		else
			return "";
	}

	public String push(String text) {
		return this.textStack.push(text);
	}

	public String peek() {
		if (!this.textStack.isEmpty())
			return this.textStack.peek();
		else
			return "";
	}

	public boolean isEmpty() {
		return this.textStack.isEmpty();
	}

	public void clear() {
		this.textStack.clear();
		this.notifyObservers();
	}

	public void notifyObservers() {
		for (ClipboardObserver o : observers)
			o.updateClipboard();
	}

	@Override
	public void subscribeClipboardObserver(ClipboardObserver observer) {
		if (!this.observers.contains(observer))
			this.observers.add(observer);

	}

	@Override
	public void unsubscribeClipboardObserver(ClipboardObserver observer) {
		if (this.observers.contains(observer))
			this.observers.remove(observer);

	}

}
