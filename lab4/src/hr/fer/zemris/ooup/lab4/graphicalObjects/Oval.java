package hr.fer.zemris.ooup.lab4.graphicalObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.IncompatibleOperandException;
import hr.fer.zemris.linearna.Point2D;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.GraphicalObject;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.Renderer;

public class Oval extends AbstractGraphicalObject{

	private static final int RECURSION_DEPTH = 4;
	private Point2D center;
	
	public Oval(Point2D right, Point2D down) {
		super(right,down);
		refreshCenter(right, down);
	}

	private void refreshCenter(Point2D right, Point2D down) {
		int centerX=(right.getX()<down.getX())?right.getX():down.getX();
		int centerY=(right.getY()<down.getY())?right.getY():down.getY();
		this.center=new Point2D(centerX, centerY);
	}
	
	public Oval() {
		this(new Point2D(25, 0), new Point2D(0, 25));
	}
	
	public Point2D getRight(){
		return this.hotPoints[0];
	}
	
	public Point2D getDown(){
		return this.hotPoints[1];
	}
	
	@Override
	public Rectangle getBoundingBox() {
		refreshCenter(this.getRight(), this.getDown());
		
		Point2D left=this.getRight().nDifference(this.center);
		Point2D up=this.getDown().nDifference(this.center);
		int width=left.getX()*2;
		int height=up.getY()*2;
		int upperLeftX=this.getRight().getX()-width;
		int upperLeftY=this.getDown().getY()-height;
		return new Rectangle(upperLeftX, upperLeftY, width, height);
	}

	@Override
	public double selectionDistance(Point2D mousePoint) {
		Rectangle boundingBox=this.getBoundingBox();
		double minDist=Double.MAX_VALUE;
		for(LineSegment ls:boundingBox.borderIterator()){
			double dist=GeometryUtil.distanceFromLineSegment(ls.getStart(), ls.getEnd(), mousePoint);
			//System.out.println("pt: "+mousePoint+"  s: "+ls.getStart()+"  e: "+ls.getEnd()+"  d: "+dist);
			
			if(dist<minDist)
				minDist=dist;
		}
		return minDist;
	}

	@Override
	public String getShapeName() {
		return "Oval";
	}

	@Override
	public GraphicalObject duplicate() {
		refreshCenter(this.getRight(), this.getDown());
		Point2D start=this.getRight();
		Point2D end=this.getDown();
		
		Oval copy=new Oval( new Point2D(start.getX(), start.getY()),new Point2D(end.getX(), end.getY()) );
		int i=0;
		for(Boolean b:this.hotPointSelected)
			copy.hotPointSelected[i++]=b;
		copy.selected=this.selected;
		copy.center=new Point2D(center.getX(), center.getY());
		return copy;
	}

	@Override
	public String toString() {
		return this.getShapeName()+" -> "+this.getHotPoint(0)+" "+this.getHotPoint(1);
	}

	@Override
	public void render(Renderer r) {
		refreshCenter(this.getRight(), this.getDown());
		IVector diffW,diffH;
		try {
			diffW = this.getRight().nSub(this.center);
			diffH = this.getDown().nSub(this.center);
			
		} catch (IncompatibleOperandException e) {
			System.out.println(this.toString()+" failed to draw.");
			return;
		}
		double a=diffW.get(0); //y component of center to x 
		double b=diffH.get(1); //y component of center to y
		
		//Generate all x points
		LinkedList<Double> X=new LinkedList<Double>(sampleXValues(new HashSet<Double>(), -a, a, RECURSION_DEPTH));
		X.add(-a);	//add lower bound
		X.add(a);	//add upper bound
		Collections.sort(X);	//make sure the points are sorted
		
		//for every x calculate y=f(x)
		LinkedList<Double> Y=getPosY(X,a,b);
		
		Point2D[] polyPoints=genPoints(X,Y);
		translateToPoint(polyPoints,center);
		r.fillPolygon(polyPoints);
	}

	private void translateToPoint(Point2D[] polyPoints, Point2D center) {
		for(Point2D pt:polyPoints)
			pt.translate(center);
		
	}

	private Point2D[] genPoints(LinkedList<Double> X, LinkedList<Double> Y) {
		Point2D[] points;
		if(X.size()<1){
			throw new IllegalArgumentException("Insufficient points in list : "+X.size());
		}
		points=new Point2D[X.size()*2-2];
		int ptIndex=0;
		// go from second element till the end
		for(int i=1;i<X.size();i++){
			points[ptIndex++]=new Point2D(X.get(i), Y.get(i));
		}
		//return from the last component to the first
		for(int i=X.size()-2;i>=0;i--){
			points[ptIndex++]=new Point2D(X.get(i), -Y.get(i));
		}
		
		return points;
	}

	private LinkedList<Double> getPosY(LinkedList<Double> X, double a, double b) {
		LinkedList<Double> Y= new LinkedList<>();
		double y=0;
		for(Double x:X){
			y=b*Math.sqrt(1-(x*x)/(a*a));
			Y.add(y);
		}
		return Y;
	}

	private HashSet<Double> sampleXValues(HashSet<Double> X, double from, double to, int targetDepth) {
		//x.add(from);
		//x.add(to);
		double half=(from+to)/2;
		X.add(half);
		if(targetDepth==0)
			return X;
		sampleXValues(X, from, half,targetDepth-1);
		sampleXValues(X, half, to,targetDepth-1);
		return X;
	}

	@Override
	public String getShapeID() {
		return "@OVAL:"+getRight()+";"+getDown();
	}
	
	@Override
	public void load(Stack<GraphicalObject> stack, String data) {
		
		
		String[] params=data.split(";");
		String[] startS=params[0].split(",");
		String[] endS=params[1].split(",");
		
		Point2D start= new Point2D(Double.parseDouble(startS[0]), Double.parseDouble(startS[1]));
		Point2D end=new Point2D(Double.parseDouble(endS[0]), Double.parseDouble(endS[1]));
		
		Oval copy=new Oval( new Point2D(start.getX(), start.getY()),new Point2D(end.getX(), end.getY()) );

		stack.push(copy);
	}
}
