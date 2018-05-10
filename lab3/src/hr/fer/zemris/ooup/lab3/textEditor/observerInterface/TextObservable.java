package hr.fer.zemris.ooup.lab3.textEditor.observerInterface;

public interface TextObservable {

	void subscribeTextObserver(TextObserver observer);
	void unsubscribeTextObserver(TextObserver observer);
	
}
