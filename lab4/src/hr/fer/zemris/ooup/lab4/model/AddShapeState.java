package hr.fer.zemris.ooup.lab4.model;

import hr.fer.zemris.linearna.Point2D;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.GraphicalObject;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.Renderer;

public class AddShapeState implements State {
	
	private GraphicalObject prototype;
	private DocumentModel model;
	
	public AddShapeState(DocumentModel model, GraphicalObject prototype) {
		this.model=model;
		this.prototype=prototype;
	}

	@Override
	public void mouseDown(Point2D mousePoint, boolean shiftDown, boolean ctrlDown) {
		GraphicalObject copy=this.prototype.duplicate();
		trans1(mousePoint, copy);
		model.addGraphicalObject(copy);
	}

	private void trans1(Point2D mousePoint, GraphicalObject copy) {
		double x=0,y=0;
		for(int i=0;i<copy.getNumberOfHotPoints();i++){
			x+=copy.getHotPoint(i).getX();
			y+=copy.getHotPoint(i).getY();
		}
		x/=copy.getNumberOfHotPoints();
		y/=copy.getNumberOfHotPoints();
		Point2D pt=new Point2D(x, y);
		mousePoint.difference(pt);
		copy.translate(mousePoint);
	}

	@Override
	public void mouseUp(Point2D mousePoint, boolean shiftDown, boolean ctrlDown) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(Point2D mousePoint) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLeaving() {
		// TODO Auto-generated method stub
		
	}
	
}
