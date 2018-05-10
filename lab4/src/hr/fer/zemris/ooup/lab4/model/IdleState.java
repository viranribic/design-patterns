package hr.fer.zemris.ooup.lab4.model;

import hr.fer.zemris.linearna.Point2D;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.GraphicalObject;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.Renderer;

public class IdleState implements State {

	private static IdleState thisState;
	
	public static State getState() {
		if(thisState==null)
			thisState=new IdleState();
		return thisState;
	}
	
	@Override
	public void mouseDown(Point2D mousePoint, boolean shiftDown, boolean ctrlDown) {
		// TODO Auto-generated method stub

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
