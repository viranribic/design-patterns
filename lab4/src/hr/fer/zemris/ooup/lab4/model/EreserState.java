package hr.fer.zemris.ooup.lab4.model;

import java.util.LinkedList;

import hr.fer.zemris.linearna.Point2D;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.GraphicalObject;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.Renderer;

public class EreserState implements State {

	private DocumentModel model;
	private LinkedList<Point2D> markedPoints = new LinkedList<>();

	public EreserState(DocumentModel model) {
		this.model = model;
	}

	@Override
	public void mouseDown(Point2D mousePoint, boolean shiftDown, boolean ctrlDown) {
		this.markedPoints.add(mousePoint);
		this.model.notifyListeners();
	}

	@Override
	public void mouseUp(Point2D mousePoint, boolean shiftDown, boolean ctrlDown) {
		this.markedPoints.add(mousePoint);

		LinkedList<GraphicalObject> safe = new LinkedList<>();
		LinkedList<GraphicalObject> ereased = new LinkedList<>();

		for (GraphicalObject go : this.model.list()) {
			boolean isEreased = false;
			for (Point2D pt : this.markedPoints) {
				double dist=go.selectionDistance(pt);
				if (dist <= 5) {
					ereased.add(go);
					isEreased = true;
					break;
				}

			}
			if (isEreased) {
				continue;
			} else {
				safe.add(go);

			}

		}
		
		this.model.clear();
		for(GraphicalObject go:safe)
			this.model.addGraphicalObject(go);
		
		this.markedPoints.clear();
	}

	@Override
	public void mouseDragged(Point2D mousePoint) {
		this.markedPoints.add(mousePoint);
		this.model.notifyListeners();
	}

	@Override
	public void keyPressed(int keyCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterDraw(Renderer r, GraphicalObject go) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterDraw(Renderer r) {
		for (int i = 0; i < markedPoints.size() - 1; i++) {
			int nextI = i + 1;
			r.drawLine(this.markedPoints.get(i), this.markedPoints.get(nextI));
		}
	}

	@Override
	public void onLeaving() {
		// TODO Auto-generated method stub

	}

}
