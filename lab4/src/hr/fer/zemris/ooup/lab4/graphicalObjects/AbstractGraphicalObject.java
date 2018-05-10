package hr.fer.zemris.ooup.lab4.graphicalObjects;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.linearna.IncompatibleOperandException;
import hr.fer.zemris.linearna.Point2D;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.GraphicalObject;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.GraphicalObjectListener;

public abstract class AbstractGraphicalObject implements GraphicalObject {

	protected Point2D[] hotPoints;
	protected boolean[] hotPointSelected;
	protected boolean selected;
	List<GraphicalObjectListener> listeners = new ArrayList<>();
	private int zValue=0;

	protected AbstractGraphicalObject(Point2D... points) {
		this.hotPoints = points;
		this.hotPointSelected = new boolean[points.length];

	}

	@Override
	public Point2D getHotPoint(int index) {
		return this.hotPoints[index];
	}

	@Override
	public void setHotPoint(int index, Point2D point) {
		this.hotPoints[index] = point;
		this.notifyListeners();
	}

	@Override
	public int getNumberOfHotPoints() {
		return this.hotPoints.length;
	}

	@Override
	public double getHotPointDistance(int index, Point2D mousePoint) {
		return GeometryUtil.distanceFromPoint(this.getHotPoint(index), mousePoint);
	}

	@Override
	public boolean isHotPointSelected(int index) {
		return this.hotPointSelected[index];
	}

	@Override
	public void setHotPointSelected(int index, boolean selected) {
		this.hotPointSelected[index] = selected;
		this.notifyListeners();
	}

	@Override
	public boolean isSelected() {
		return this.selected;
	}

	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
		this.notifySelectionListeners();
	}

	@Override
	public void translate(Point2D delta) {
		try {
			for (Point2D p : this.hotPoints)
				p.add(delta);
			this.notifyListeners();
		} catch (IncompatibleOperandException e) {
			System.out.println("Error while subtracting.");
		}
	}

	@Override
	public void addGraphicalObjectListener(GraphicalObjectListener l) {
		if (!this.listeners.contains(l))
			this.listeners.add(l);

		
	}

	@Override
	public void removeGraphicalObjectListener(GraphicalObjectListener l) {
		if (this.listeners.contains(l))
			this.listeners.remove(l);

	}

	protected void notifyListeners() {
		for (GraphicalObjectListener l : this.listeners)
			l.graphicalObjectChanged(this);
	}

	protected void notifySelectionListeners() {
		for (GraphicalObjectListener l : this.listeners)
			l.graphicalObjectSelectionChanged(this);
	}

	@Override
	public void incZValue(int offset) {
		this.zValue+=offset;
		notifyListeners();
		
	}
	
	@Override
	public void decZValue(int offset) {
		this.zValue-=offset;
		this.zValue=(this.zValue<0)?0:this.zValue;
		notifyListeners();
	}
	
	@Override
	public double getZValue(){
		return this.zValue;
	}
	
	@Override
	public void save(List<String> rows) {
		rows.add(this.getShapeID()+"\n");
	}
}
