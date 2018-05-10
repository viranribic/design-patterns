package hr.fer.zemris.ooup.lab3.textEditor.observerInterface;

public interface ClipboardObservable {
	
	void subscribeClipboardObserver(ClipboardObserver observer);
	void unsubscribeClipboardObserver(ClipboardObserver observer);

}
