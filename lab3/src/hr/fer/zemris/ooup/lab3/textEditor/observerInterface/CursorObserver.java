package hr.fer.zemris.ooup.lab3.textEditor.observerInterface;

import hr.fer.zemris.ooup.lab3.textEditor.util.ImmutableLocation;

public interface CursorObserver {

	void updateCursorLocation(ImmutableLocation loc);
}
