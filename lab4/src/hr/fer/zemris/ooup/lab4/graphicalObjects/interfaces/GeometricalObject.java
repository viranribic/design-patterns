package hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces;

import hr.fer.zemris.linearna.Point2D;
import hr.fer.zemris.ooup.lab4.graphicalObjects.Rectangle;

public interface GeometricalObject {
	void translate(Point2D delta);
	Rectangle getBoundingBox();
	double selectionDistance(Point2D mousePoint);
}
