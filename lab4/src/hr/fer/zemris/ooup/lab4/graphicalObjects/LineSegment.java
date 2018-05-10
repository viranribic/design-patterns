package hr.fer.zemris.ooup.lab4.graphicalObjects;

import java.util.List;
import java.util.Stack;

import hr.fer.zemris.linearna.Point2D;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.GraphicalObject;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.Renderer;

public class LineSegment extends AbstractGraphicalObject{

	public LineSegment(Point2D start, Point2D end) {
		super(start,end);
	}
	
	public LineSegment() {
		super(new Point2D(0, 0), new Point2D(25, 25));
	}
	
	public Point2D getStart(){
		return this.hotPoints[0];
	}
	
	public Point2D getEnd(){
		return this.hotPoints[1];
	}
	
	@Override
	public Rectangle getBoundingBox() {
		Point2D start=this.getStart();
		Point2D end=this.getEnd();
		int sX=start.getX();
		int sY=start.getY();
		int eX=end.getX();
		int eY=end.getY();
		int upperLeftX=(sX<eX)?sX:eX;
		int upperLeftY=(sY<eY)?sY:eY;
		int width=sX-eX;
		int height=sY-eY;
		width=(width<0)?-width:width;
		height=(height<0)?-height:height;
		return new Rectangle(upperLeftX, upperLeftY, width, height);
	}

	@Override
	public double selectionDistance(Point2D mousePoint) {
		Rectangle boundingBox=this.getBoundingBox();
		double minDist=Double.MAX_VALUE;
		for(LineSegment ls:boundingBox.borderIterator()){
			double dist=GeometryUtil.distanceFromLineSegment(ls.getStart(), ls.getEnd(), mousePoint);
			if(dist<minDist)
				minDist=dist;
		}
		return minDist;
	}

	@Override
	public String getShapeName() {
		return "Line segment";
	}

	@Override
	public GraphicalObject duplicate() {
		Point2D start=this.getStart();
		Point2D end=this.getEnd();
		
		LineSegment copy=new LineSegment( new Point2D(start.getX(), start.getY()),new Point2D(end.getX(), end.getY()) );
		int i=0;
		for(Boolean b:this.hotPointSelected)
			copy.hotPointSelected[i++]=b;
		copy.selected=this.selected;
		return copy;
	}

	@Override
	public String toString() {
		return this.getShapeName()+" -> "+this.getHotPoint(0)+" "+this.getHotPoint(1);
	}

	@Override
	public void render(Renderer r) {
		r.drawLine(this.getStart(), this.getEnd());
	}
	
	@Override
	public String getShapeID() {
		return "@LINE:"+getStart()+";"+getEnd();
	}
	
	@Override
	public void load(Stack<GraphicalObject> stack, String data) {
		String[] params=data.split(";");
		String[] startS=params[0].split(",");
		String[] endS=params[1].split(",");
		
		Point2D start= new Point2D(Double.parseDouble(startS[0]), Double.parseDouble(startS[1]));
		Point2D end=new Point2D(Double.parseDouble(endS[0]), Double.parseDouble(endS[1]));
		
		LineSegment copy=new LineSegment( new Point2D(start.getX(), start.getY()),new Point2D(end.getX(), end.getY()) );
		
		stack.push(copy);
	}
	
}
