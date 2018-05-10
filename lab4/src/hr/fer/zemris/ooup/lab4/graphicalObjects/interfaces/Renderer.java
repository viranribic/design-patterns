package hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces;

import hr.fer.zemris.linearna.Point2D;

public interface Renderer {
	void drawLine(Point2D s, Point2D e);
	void fillPolygon(Point2D[] points);
}