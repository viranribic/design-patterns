package hr.fer.zemris.ooup.lab3.textEditor.observerInterface;

public interface CursorObservable {

	void subscribeCursorObserver(CursorObserver observer);
	void unsubscribeCursorObserver(CursorObserver observer);
	
}
