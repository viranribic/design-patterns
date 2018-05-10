package hr.fer.zemris.ooup.lab3.textEditor.plugins;

import hr.fer.zemris.ooup.lab3.textEditor.ClipboardStack;
import hr.fer.zemris.ooup.lab3.textEditor.TextEditorModel;
import hr.fer.zemris.ooup.lab3.textEditor.undoRedoOperatoins.UndoManager;

public class Statistics implements Plugin {

	@Override
	public String getName() {
		return "Text editor statistics";
	}

	@Override
	public String getDescription() {
		return "This plugin scans the open document and outputs the number of rows, words and letters contained.";
	}

	@Override
	public void execute(TextEditorModel model, UndoManager undoManager, ClipboardStack clipboardStack) {
		int numberOfRows = 0;
		int numberOfWords = 0;
		int numberOfLetters = 0;

		for (String line : model.allLines()) {
			if (!line.equals("")) {
				numberOfRows++;
				numberOfLetters += line.length();
				numberOfWords += line.split(" ").length;
			}
		}

		System.out.println("The document conatins:\n\t" + numberOfRows + " rows.\n\t" + numberOfWords + " words.\n\t"
				+ numberOfLetters + " letters.\n");

	}

}
