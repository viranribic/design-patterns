package hr.fer.zemris.ooup.lab4.graphicalObjects;

import java.util.List;
import java.util.Stack;

import hr.fer.zemris.linearna.Point2D;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.GraphicalObject;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.GraphicalObjectListener;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.Renderer;

public class Triangle extends AbstractGraphicalObject {

	public Triangle() {
		super(
				new Point2D[]{
						new Point2D(0, 0),
						new Point2D(0, 30),
						new Point2D(15, 15)
						} 
		);
	}
	
	public Triangle(Point2D... points){
		super(points);
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(100, 100, 100, 100);
	}

	@Override
	public double selectionDistance(Point2D mousePoint) {
		double d1 = GeometryUtil.distanceFromLineSegment(getHotPoint(0), getHotPoint(1), mousePoint);
		double d2 = GeometryUtil.distanceFromLineSegment(getHotPoint(1), getHotPoint(2), mousePoint);
		double d3 = GeometryUtil.distanceFromLineSegment(getHotPoint(2), getHotPoint(0), mousePoint);

		return Math.min(Math.min(d1, d2), d3);
	}

	@Override
	public void render(Renderer r) {
		r.fillPolygon(new Point2D[] { getHotPoint(0), getHotPoint(1), getHotPoint(2) });
	}

	@Override
	public String getShapeName() {
		return "Triangle";
	}

	@Override
	public GraphicalObject duplicate() {
		
		return new Triangle(new Point2D(getHotPoint(0)), new Point2D(getHotPoint(1)), new Point2D(getHotPoint(2)));
	}

	@Override
	public String getShapeID() {
		return "@TRIANGLE";
	}

	@Override
	public void load(Stack<GraphicalObject> stack, String data) {
		// TODO Auto-generated method stub

	}

}
