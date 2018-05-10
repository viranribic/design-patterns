package hr.fer.zemris.ooup.lab4.graphicalObjects;

import java.util.Iterator;

import hr.fer.zemris.linearna.Point2D;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.Renderer;

public class Rectangle {

	private int x;
	private int y;
	private int width;
	private int height;
	
	public Rectangle(int x, int y, int width, int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	};
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Point2D getUpperLeftPoint(){
		return new Point2D(this.x, this.y);
	}
	
	public Point2D getUpperRightPoint(){
		return new Point2D(this.x+this.width, this.y);
	}
	
	public Point2D getLowerLeftPoint(){
		return new Point2D(this.x, this.y+this.height);
	}
	
	public Point2D getLowerRightPoint(){
		return new Point2D(this.x+this.width, this.y+this.height);
	}
	
	public Iterable<LineSegment> borderIterator(){
		return ()->{
			return new Iterator<LineSegment>() {
				
				
				private int startIndex=-1;
				
				@Override
				public LineSegment next() {
					startIndex++;
					switch(startIndex){
					case 0: return new LineSegment(getUpperLeftPoint(),getUpperRightPoint()); 
					case 1: return new LineSegment(getUpperRightPoint(),getLowerRightPoint());
					case 2: return new LineSegment(getLowerRightPoint(),getLowerLeftPoint());
					case 3: return new LineSegment(getLowerLeftPoint(),getUpperLeftPoint());
					default: return null;
					}
				}
				
				@Override
				public boolean hasNext() {
					return startIndex<3; // elements: -1, 0, 1 ,2
				}
			};
		};
	}

	public void render(Renderer r) {
		r.drawLine(this.getUpperLeftPoint(), this.getUpperRightPoint());
		r.drawLine(this.getUpperRightPoint(), this.getLowerRightPoint());
		r.drawLine(this.getLowerRightPoint(), this.getLowerLeftPoint());
		r.drawLine(this.getLowerLeftPoint(), this.getUpperLeftPoint());
	}
	
}
