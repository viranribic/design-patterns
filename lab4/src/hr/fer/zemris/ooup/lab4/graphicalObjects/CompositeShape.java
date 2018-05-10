package hr.fer.zemris.ooup.lab4.graphicalObjects;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.w3c.dom.views.AbstractView;

import hr.fer.zemris.linearna.Point2D;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.GraphicalObject;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.GraphicalObjectListener;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.Renderer;

public class CompositeShape extends AbstractGraphicalObject {

	private List<GraphicalObject> containedObjects=new LinkedList<>();
	
	public CompositeShape(List<GraphicalObject> list) {
		this.containedObjects=list;
	}
	
	@Override
	public void translate(Point2D delta) {
		for(GraphicalObject go:containedObjects)
			go.translate(delta);
		for(GraphicalObject go:containedObjects)
			go.setSelected(false);
	}

	@Override
	public Rectangle getBoundingBox() {
		int minX=Integer.MAX_VALUE,minY=Integer.MAX_VALUE,maxX=Integer.MIN_VALUE,maxY=Integer.MIN_VALUE;
		
		for(GraphicalObject go:this.containedObjects){
			Rectangle rec=go.getBoundingBox();
			int goX=rec.getX();
			int goY=rec.getY();
			int goWidth=rec.getWidth();
			int goHeight=rec.getHeight();
			minX=(minX<goX)?minX:goX;
			minY=(minY<goY)?minY:goY;
			
			maxX=(goX+goWidth<maxX)?maxX:goX+goWidth;
			maxY=(goY+goHeight<maxY)?maxY:goY+goHeight;
			
		}
		
		return new Rectangle(minX, minY, maxX-minX, maxY-minY);
	}

	@Override
	public double selectionDistance(Point2D mousePoint) {
		double min=Double.MIN_VALUE;
		
		for(GraphicalObject obj:this.containedObjects){
			double dist=obj.selectionDistance(mousePoint);
			min=(min<dist)?min:dist;
		}
		return min;
	}

	@Override
	public void render(Renderer r) {
		for(GraphicalObject go:this.containedObjects)
			go.render(r);
	}

	@Override
	public void addGraphicalObjectListener(GraphicalObjectListener l) {
		for(GraphicalObject go:this.containedObjects)
			go.addGraphicalObjectListener(l);
	}

	@Override
	public void removeGraphicalObjectListener(GraphicalObjectListener l) {
		for(GraphicalObject go:this.containedObjects)
			go.removeGraphicalObjectListener(l);
	}

	@Override
	public String getShapeName() {
		StringBuilder sb=new StringBuilder();
		
		for(GraphicalObject go:this.containedObjects)
			sb.append(go.getShapeName()).append("\n");
		
		return sb.toString();
	}

	@Override
	public GraphicalObject duplicate() {
		LinkedList<GraphicalObject> objects=new LinkedList<>();
		for(GraphicalObject go:this.containedObjects)
			objects.add(go.duplicate());
		return new CompositeShape(objects);
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setSelected(boolean selected) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNumberOfHotPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point2D getHotPoint(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHotPoint(int index, Point2D point) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isHotPointSelected(int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setHotPointSelected(int index, boolean selected) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getHotPointDistance(int index, Point2D mousePoint) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void incZValue(int offset) {
		for(GraphicalObject go:this.containedObjects)
			go.decZValue(offset);
		for(GraphicalObject go:containedObjects)
			go.setSelected(false);
	}

	@Override
	public void decZValue(int offset) {
		for(GraphicalObject go:this.containedObjects)
			go.decZValue(offset);
		for(GraphicalObject go:containedObjects)
			go.setSelected(false);
	}

	@Override
	public double getZValue() {
		double minZ=Double.MAX_VALUE;
		for(GraphicalObject go:this.containedObjects)
			minZ=(minZ<go.getZValue())?minZ:go.getZValue();
		return minZ;
	}

	public List<GraphicalObject>  getList(){
		return this.containedObjects;
	}
	
	@Override
	public String getShapeID() {
		StringBuilder sb=new StringBuilder();
		for(GraphicalObject go:this.containedObjects)
			sb.append(go.getShapeID()+"\n");
		sb.append("@COMP:"+this.containedObjects.size());
		return sb.toString();
	}
	
	@Override
	public void load(Stack<GraphicalObject> stack, String data) {
		int prevElements=Integer.parseInt(data);
		List<GraphicalObject> list=new LinkedList<>();
		
		for(int i=0;i<prevElements;i++)
			list.add(stack.pop());
		
		CompositeShape cs=new CompositeShape(list);
		
		stack.add(cs);
	}
	
}
