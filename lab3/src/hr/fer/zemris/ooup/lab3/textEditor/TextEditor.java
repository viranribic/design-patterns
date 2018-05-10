package hr.fer.zemris.ooup.lab3.textEditor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import hr.fer.zemris.ooup.lab3.textEditor.plugins.Plugin;
import hr.fer.zemris.ooup.lab3.textEditor.undoRedoOperatoins.UndoManager;

public class TextEditor extends JFrame {

	/**
	 * Auto generated serial version UID.
	 */
	private static final long serialVersionUID = -5124547682466594903L;
	private TextEditorView textEditorView;
	private TextEditorModel textEditorModel;
	private ClipboardStack clipboard;

	public TextEditor() {
		JFrame thisFrame=this;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(640, 480);
		this.setTitle("Text editor");
		this.setLocationRelativeTo(null);
		this.setFocusTraversalKeysEnabled(false);

		this.clipboard = new ClipboardStack();
		this.textEditorModel = new TextEditorModel("Prvi\nDrugi\nTreci red\nCetiri", this.clipboard);
		this.textEditorView = new TextEditorView(this.textEditorModel, this.clipboard);

		JPanel mainPanel=new JPanel(new BorderLayout());
		mainPanel.setVisible(true);
		
		JMenuBar menuBar = new JMenuBar();
		//----------------------------------------------------------
		JMenu fileMenu = new JMenu("File");
		//----------------------------------------------------------
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener((e) -> {
			JFileChooser c = new JFileChooser();
			// Demonstrate "Open" dialog:
			int rVal = c.showOpenDialog(this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				File inputFile = c.getSelectedFile();
				String text = fileToText(inputFile);
				textEditorModel.setNewText(text);
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				// nothing chosen
			}
		});
		//----------------------------------------------------------
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener((e) -> {
			JFileChooser c = new JFileChooser();
			// Demonstrate "Save" dialog:
			int rVal = c.showSaveDialog(this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				File outputFile = c.getSelectedFile();
				String[] text=this.textEditorModel.getTextModel();
				textToFile(outputFile, text);
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				// nothing chosen
			}
		});
		//----------------------------------------------------------
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener((e)->{
			thisFrame.dispose();
		});
		//----------------------------------------------------------
		JMenu editMenu = new JMenu("Edit");
		//----------------------------------------------------------
		JMenuItem undo = new JMenuItem("Undo");
		UndoManager manager=UndoManager.getManager();
		manager.subscribeUndo(()->{
			undo.setEnabled(!manager.isUndoEmpty());
		});
		undo.addActionListener((e)->{
			textEditorModel.undo();
		});
		undo.setEnabled(false);
		//----------------------------------------------------------
		JMenuItem redo = new JMenuItem("Redo");
		manager.subscribeRedo(()->{
			redo.setEnabled(!manager.isRedoEmpty());
		});
		redo.addActionListener((e)->{
			textEditorModel.redo();
		});
		redo.setEnabled(false);
		//----------------------------------------------------------
		JMenuItem cut = new JMenuItem("Cut");
		cut.addActionListener((e)->{
			textEditorModel.cutSelectionToClipboard();
		});
		textEditorModel.subscibeSelectionObserver(()->{
			cut.setEnabled(textEditorModel.hasSelectedRange());
		});
		cut.setEnabled(false);
		//----------------------------------------------------------
		JMenuItem copy = new JMenuItem("Copy");
		copy.addActionListener((e)->{
			textEditorModel.copySelectionToClipboard();
		});
		textEditorModel.subscibeSelectionObserver(()->{
			copy.setEnabled(textEditorModel.hasSelectedRange());
		});
		copy.setEnabled(false);
		//----------------------------------------------------------
		JMenuItem paste = new JMenuItem("Paste");
		this.clipboard.subscribeClipboardObserver(()->{
			paste.setEnabled(!clipboard.isEmpty());
		});
		paste.setEnabled(false);
		paste.addActionListener((e)->{
			textEditorModel.paste();
		});
		//----------------------------------------------------------
		JMenuItem pasteAndTake = new JMenuItem("Paste and Take");
		this.clipboard.subscribeClipboardObserver(()->{
			pasteAndTake.setEnabled(!clipboard.isEmpty());
		});
		pasteAndTake.setEnabled(false);
		pasteAndTake.addActionListener((e)->{
			textEditorModel.pasteAndTake();
		});
		//----------------------------------------------------------
		JMenuItem delete = new JMenuItem("Delete selection");
		delete.addActionListener((e)->{
			textEditorModel.deleteRange();
		});
		textEditorModel.subscibeSelectionObserver(()->{
			delete.setEnabled(textEditorModel.hasSelectedRange());
		});
		delete.setEnabled(false);
		//----------------------------------------------------------
		JMenuItem clearDoc = new JMenuItem("Clear document");
		clearDoc.addActionListener((e)->{
			textEditorModel.clearDocument();
		});
		//----------------------------------------------------------

		JMenu moveMenu = new JMenu("Move");
		JMenuItem cursor2Start = new JMenuItem("Cursor to document start");
		cursor2Start.addActionListener((e)->{
			textEditorModel.moveCursorToStart();
		});
		JMenuItem cursor2End = new JMenuItem("Cursor to document end");
		cursor2End.addActionListener((e)->{
			textEditorModel.moveCursorToEnd();
		});
		
		
		menuBar.setVisible(true);
		menuBar.add(fileMenu);
		fileMenu.setVisible(true);
		menuBar.add(editMenu);
		editMenu.setVisible(true);
		menuBar.add(moveMenu);
		moveMenu.setVisible(true);
		LinkedList<Plugin> plugins=new LinkedList<>();
		try {
			moveMenu.add(generatePlugInMenu(plugins, textEditorModel, manager, clipboard));
		} catch (NullPointerException|ClassNotFoundException | InvocationTargetException |NoSuchMethodException| SecurityException | IllegalArgumentException |InstantiationException |IllegalAccessException e1) {
			System.out.println("Error. Plugin loading failed.");
		} 
		
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(exit);

		editMenu.add(undo);
		editMenu.add(redo);
		editMenu.add(cut);
		editMenu.add(copy);
		editMenu.add(paste);
		editMenu.add(pasteAndTake);
		editMenu.add(delete);
		editMenu.add(clearDoc);

		moveMenu.add(cursor2Start);
		moveMenu.add(cursor2End);
		//-----------------------------------------------------------------------
		JToolBar toolbar=new JToolBar();
		JButton undoButton=new JButton("Undo");
		manager.subscribeUndo(()->{
			undoButton.setEnabled(!manager.isUndoEmpty());
		});
		undoButton.addActionListener((e)->{
			textEditorModel.undo();
		});
		undoButton.setEnabled(false);
		//-----------------------------------------------------------------------
		
		JButton redoButton=new JButton("Redo");
		manager.subscribeRedo(()->{
			redoButton.setEnabled(!manager.isRedoEmpty());
		});
		redoButton.addActionListener((e)->{
			textEditorModel.redo();
		});
		redoButton.setEnabled(false);
		//-----------------------------------------------------------------------
		
		JButton cutButton=new JButton("Cut");
		cutButton.addActionListener((e)->{
			textEditorModel.cutSelectionToClipboard();
		});
		textEditorModel.subscibeSelectionObserver(()->{
			cutButton.setEnabled(textEditorModel.hasSelectedRange());
		});
		cutButton.setEnabled(false);
		//-----------------------------------------------------------------------
		
		JButton copyButton=new JButton("Copy");
		copyButton.addActionListener((e)->{
			textEditorModel.copySelectionToClipboard();
		});
		textEditorModel.subscibeSelectionObserver(()->{
			copyButton.setEnabled(textEditorModel.hasSelectedRange());
		});
		copyButton.setEnabled(false);
		//-----------------------------------------------------------------------
		
		JButton pasteButton=new JButton("Paste");
		this.clipboard.subscribeClipboardObserver(()->{
			pasteButton.setEnabled(!clipboard.isEmpty());
		});
		pasteButton.addActionListener((e)->{
			textEditorModel.paste();
		});
		pasteButton.setEnabled(false);
		//-----------------------------------------------------------------------
		
		toolbar.add(undoButton);
		toolbar.add(redoButton);
		toolbar.add(cutButton);
		toolbar.add(copyButton);
		toolbar.add(pasteButton);
		
		
		JPanel controlPanel=new JPanel(new BorderLayout());
		controlPanel.add(menuBar, BorderLayout.NORTH);
		controlPanel.add(toolbar, BorderLayout.SOUTH);
		
		JStatusBar statusBar=new JStatusBar(textEditorModel.getCursorLocation().getRow(), textEditorModel.getCursorLocation().getColumn(), textEditorModel.getLines().size());
		this.textEditorModel.subscribeTextObserver(()->{
			statusBar.updateLineNumber(textEditorModel.getLines().size());
		});
		this.textEditorModel.subscribeCursorObserver((loc)->{
			statusBar.updateCursor(loc);
		});
		mainPanel.add(controlPanel, BorderLayout.NORTH);
		mainPanel.add(this.textEditorView, BorderLayout.CENTER);
		mainPanel.add(statusBar, BorderLayout.SOUTH);
		
		
		this.add(mainPanel);
		this.textEditorView.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (e.isShiftDown()) {
						textEditorModel.expandRangeLeft();
					} else {
						textEditorModel.moveCursorLeft();
						textEditorModel.restartRange();
					}
					break;

				case KeyEvent.VK_RIGHT:
					if (e.isShiftDown()) {
						textEditorModel.expandRangeRight();
					} else {
						textEditorModel.moveCursorRight();
						textEditorModel.restartRange();
					}
					break;

				case KeyEvent.VK_UP:
					if (e.isShiftDown()) {
						textEditorModel.expandRangeUp();
					} else {
						textEditorModel.moveCursorUp();
						textEditorModel.restartRange();
					}
					break;

				case KeyEvent.VK_DOWN:
					if (e.isShiftDown()) {
						textEditorModel.expandRangeDown();
					} else {
						textEditorModel.moveCursorDown();
						textEditorModel.restartRange();
					}
					break;
				case KeyEvent.VK_BACK_SPACE:
					textEditorModel.deleteBefore();
					break;
				case KeyEvent.VK_DELETE:
					textEditorModel.deleteAfter();
					break;
				case KeyEvent.VK_C:
					if (e.isControlDown()) {
						textEditorModel.copySelectionToClipboard();
						break;
					}
				case KeyEvent.VK_X:
					if (e.isControlDown()) {
						textEditorModel.cutSelectionToClipboard();
						break;
					}
				case KeyEvent.VK_V:
					if (e.isControlDown()) {
						if (e.isShiftDown()) {
							textEditorModel.pasteAndTake();
						} else {
							textEditorModel.paste();
						}
						break;
					}
				case KeyEvent.VK_Z:
					if (e.isControlDown()) {
						textEditorModel.undo();
						break;
					}
				case KeyEvent.VK_Y:
					if (e.isControlDown()) {
						textEditorModel.redo();
						break;
					}
				default:
					if (isPrintableChar(e.getKeyChar()) || e.getKeyCode() == KeyEvent.VK_ENTER) {
						textEditorModel.insert(e.getKeyChar());
					}
				}

			}
		});

	}

	public static String fileToText(File file) {
		StringBuilder sb = new StringBuilder();

		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			while(true){
				String line=in.readLine();
				if(line==null)
					break;
				sb.append(line).append("\n");
			}
		} catch (IOException e) {

		}
		return sb.toString();
	}
	
	public static void textToFile(File file, String[] text) {
		try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
			for(int i=0;i<text.length;i++){
				out.write(text[i]);	
				out.newLine();
			}
		} catch (IOException e) {

		}
		return;
	}
	
	public JMenu  generatePlugInMenu(LinkedList<Plugin> plugins, TextEditorModel model, UndoManager undoManager, ClipboardStack clipboardStack) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		JMenu pluginMenu=new JMenu("Plugins");
		File folder=new File("hr.fer.zemris.ooup.lab3.textEditor.plugins");
		System.out.println(folder.exists());
		File[] lisOfFiles= folder.listFiles();
		for(File f:lisOfFiles){
			System.out.println(f.getName());
			JMenuItem pluginItem = new JMenuItem(f.getName());
			Plugin plugin = PluginFactory.newInstance(f.getName());
			pluginItem.addActionListener((e)->{
				plugin.execute(textEditorModel, undoManager, clipboardStack);
			});
			plugins.add(plugin);
			pluginMenu.add(pluginItem);
		}
		
		
		return pluginMenu;
	}

	public static boolean isPrintableChar(char c) {
		Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
		return (!Character.isISOControl(c)) && c != KeyEvent.CHAR_UNDEFINED && block != null
				&& block != Character.UnicodeBlock.SPECIALS;
	}

	public static void main(String[] args) {
		new TextEditor().setVisible(true);
	}
}
