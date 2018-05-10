package hr.fer.zemris.ooup.lab4.guiElements;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.GraphicalObject;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.Renderer;
import hr.fer.zemris.ooup.lab4.model.DocumentModel;
import hr.fer.zemris.ooup.lab4.model.State;

public class PaintCanvas extends JPanel {

	/**
	 * Auto generated serial version UID.
	 */
	private static final long serialVersionUID = 189013964232910667L;
	private DocumentModel model;
	private GUI gui;

	public PaintCanvas(DocumentModel model, GUI gui) {
		this.model = model;
		this.model.addDocumentModelListener(() -> {
			this.repaint();
		});
		this.gui=gui;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Renderer r = new G2DRendererImpl(g2d);
		State curState=this.gui.getCurrentState();
		for (GraphicalObject gobj : this.model.list()) {
			gobj.render(r);
			curState.afterDraw(r, gobj);
		}
		curState.afterDraw(r);
	}

}
