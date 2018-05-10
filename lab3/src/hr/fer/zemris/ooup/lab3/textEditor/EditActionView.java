package hr.fer.zemris.ooup.lab3.textEditor;

import java.util.LinkedList;


import hr.fer.zemris.ooup.lab3.textEditor.util.Location;
import hr.fer.zemris.ooup.lab3.textEditor.util.LocationRange;

public interface EditActionView {

	Location getCursorLocation();
	LocationRange getSelectionRange();
	LinkedList<String> getLines();
	
	
}
