package hr.fer.zemris.linearna;

public class Point2D extends Vector {

	public Point2D(double x, double y) {
		super(x,y);
	}
	
	public Point2D(Point2D hotPoint) {
		super(hotPoint.getX(),hotPoint.getY());
	}

	public int getX(){
		return (int)this.elements[0];
	}
	
	public int getY(){
		return (int)this.elements[1];
	}
	
	public void translate(Point2D dp){
		this.elements[0]+=dp.getX();
		this.elements[1]+=dp.getY();	
	}
	
	public void difference(Point2D dp){
		this.elements[0]-=dp.getX();
		this.elements[1]-=dp.getY();	
	}
	
	public Point2D nTranslate(Point2D dp){
		return new Point2D(this.getX()+dp.getX(), this.getY()+dp.getY());
	}
	
	public Point2D nDifference(Point2D dp){
		return new Point2D(this.getX()-dp.getX(), this.getY()-dp.getY());
	}
	
	@Override
	public String toString() {
		return this.getX()+","+this.getY();
	}
	
}
