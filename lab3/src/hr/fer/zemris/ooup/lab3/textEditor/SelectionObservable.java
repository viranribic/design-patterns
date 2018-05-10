package hr.fer.zemris.ooup.lab3.textEditor;

public interface SelectionObservable {

	void subscibeSelectionObserver(SelectionObserver observer);
	void unsubsscibeSelectionObserver(SelectionObserver observer);
	
	
}
