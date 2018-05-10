package hr.fer.zemris.ooup.lab3.textEditor;

import javax.swing.JLabel;
import javax.swing.JPanel;

import hr.fer.zemris.ooup.lab3.textEditor.util.ImmutableLocation;


public class JStatusBar extends JPanel {

	/**
	 * Auto generatoed serial version UID.
	 */
	private static final long serialVersionUID = -8514858153058974098L;
	private JLabel statusLabel;
	
	public JStatusBar(int cursorRow, int cursorColumn, int numberOfLines) {
		String statusBarText="Cursor: "+cursorRow+","+cursorColumn+" | Number of rows: "+numberOfLines;
		this.statusLabel=new JLabel(statusBarText);
		this.add(statusLabel);
	}
	
	public void updateCursor(ImmutableLocation cursor){
		String[] statusBarComponents=this.statusLabel.getText().split("\\|");
		this.statusLabel.setText("Cursor: "+cursor.getRow()+","+cursor.getColumn()+" | "+statusBarComponents[1]);
	}
	
	public void updateLineNumber(int numberOfRows){
		String[] statusBarComponents=this.statusLabel.getText().split("\\|");
		this.statusLabel.setText(statusBarComponents[0]+" | Number of rows: "+numberOfRows);
	
	}
}
