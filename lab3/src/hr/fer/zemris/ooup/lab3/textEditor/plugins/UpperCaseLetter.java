package hr.fer.zemris.ooup.lab3.textEditor.plugins;

import java.util.LinkedList;

import hr.fer.zemris.ooup.lab3.textEditor.ClipboardStack;
import hr.fer.zemris.ooup.lab3.textEditor.TextEditorModel;
import hr.fer.zemris.ooup.lab3.textEditor.undoRedoOperatoins.UndoManager;

public class UpperCaseLetter implements Plugin {

	@Override
	public String getName() {
		return "UpperCase transformer";
	}

	@Override
	public String getDescription() {
		return "This plugin scans the open document and changes every word's first letter into a uppercase one.";
	}

	@Override
	public void execute(TextEditorModel model, UndoManager undoManager, ClipboardStack clipboardStack) {
		LinkedList<String> lines = model.getLines();
		for (int row = 0; row < lines.size(); row++) {
			String line = lines.get(row);
			if (line.equals(""))
				continue;
			String[] words = line.split(" ");
			StringBuilder modifiedLine = new StringBuilder();
			
			for (int wordIndex = 0; wordIndex < words.length; wordIndex++) {
				char firstChar= Character.toUpperCase(words[wordIndex].charAt(0));
				words[wordIndex] = firstChar + words[wordIndex].substring(1);
				modifiedLine.append(words[wordIndex]).append(" ");
				
			}
			
			lines.set(row, modifiedLine.toString().trim());
		}
	}
}
