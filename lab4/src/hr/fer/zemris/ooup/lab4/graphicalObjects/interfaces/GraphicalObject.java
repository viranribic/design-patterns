package hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces;

import hr.fer.zemris.linearna.Point2D;

public interface GraphicalObject extends GeometricalObject,DrawableObject,ObservableObject,PrototipeObject, LoadSaveObject {

	boolean isSelected();
	void setSelected(boolean selected);
	int getNumberOfHotPoints();
	Point2D getHotPoint(int index);
	void setHotPoint(int index, Point2D point);
	boolean isHotPointSelected(int index);
	void setHotPointSelected(int index, boolean selected);
	double getHotPointDistance(int index, Point2D mousePoint);
	void incZValue(int offset);
	void decZValue(int offset);
	double getZValue();
}
