package hr.fer.zemris.ooup.lab4.graphicalObjects;

import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.IncompatibleOperandException;
import hr.fer.zemris.linearna.Point2D;
import hr.fer.zemris.linearna.Vector;

public class GeometryUtil {

	public static double distanceFromPoint(Point2D p1, Point2D p2) {
		try {
			return p2.nSub(p1).norm();
		} catch (IncompatibleOperandException e) {
			return -1;
		}
	}

	public static double distanceFromLineSegment(Point2D s, Point2D e, Point2D p) {
		int sX=s.getX();
		int sY=s.getY();
		
		int eX=e.getX();
		int eY=e.getY();
		
		int pX=p.getX();
		int pY=p.getY();
		
		int minX=(sX<eX)?sX:eX;
		int minY=(sY<eY)?sY:eY;
		int maxX=(sX>eX)?sX:eX;
		int maxY=(sY>eY)?sY:eY;

		if(minX==maxX){
			if(pY<minY){
				return Math.sqrt( (minY-pY)*(minY-pY)+(minX-pX)*(minX-pX) );
			}else if(minY<=pY && pY<=maxY){
				return Math.abs( minX-pX);
			}else{ //maxY<pY
				return Math.sqrt( (maxY-pY)*(maxY-pY)+(maxX-pX)*(maxX-pX) );
			}
		}else{
			if(pX<minX){
				return Math.sqrt( (minY-pY)*(minY-pY)+(minX-pX)*(minX-pX) );
			}else if(minX<=pX && pX<=maxX){
				return Math.abs( minY-pY);
			}else{ //maxY<pY
				return Math.sqrt( (maxY-pY)*(maxY-pY)+(maxX-pX)*(maxX-pX) );
			}		}
		
		
		//return -1;
//		try {
//			IVector pH=p.copyPart(3).set(2, 1);
//			
//			IVector e_Perp_s=perpen(s, e);
//			
//			IVector perpS=generateDirV(e_Perp_s,s);
//			IVector perpE=generateDirV(e_Perp_s,e);
//			
//			double pStart=perpS.dotProduct(pH);
//			double pEnd=perpE.dotProduct(pH);
//			
//			if( pStart < 0 ){
//				return s.nSub(p).norm();
//			}else if( pEnd <=0 ){
//				return workingDistance(s, e, p);
//
//			}else{
//				return e.nSub(p).norm();
//
//			}
//			//return useful(s, e, p);
//			//return workingDistance(s, e, p);
//		} catch (IncompatibleOperandException e1) {
//			return -1;
//		}
//		
	}

	private static IVector generateDirV(IVector dir, Point2D s) {
		double dx=dir.get(0);
		double dy=dir.get(1);
		double sx=s.get(0);
		double sy=s.get(1);
		// x = dx * t + sx  -> t = 1/dx*( x-sx)
		// y = dy * t + sy -> y= dy / dx * (x-sx) + sy
		// y*dx - sy*dx = dy * (x-sx)
		// dy*x + dx * y + ( sx*dy - sy*dx ) = 0
		double a= dy;
		double b= dx;
		double c= -(sx*dy - sy*dx);
		return new Vector(a,b,c);
		
	}

	private static double useful(Point2D s, Point2D e, Point2D p) throws IncompatibleOperandException {
		IVector sH=s.copyPart(3).set(2, 1);
		IVector eH=e.copyPart(3).set(2, 1);
		IVector pH=p.copyPart(3).set(2, 1);
		IVector es=eH.nVectorProduct(sH);
		
		
		double res1=pH.dotProduct(es);
		double res2=workingDistance(s, e, p);
		if(res1==res2){
			System.out.println(res1==res2);	
		}else{
			System.out.println(res1 +" "+res2);
		}
		return 0;
	}

	private static double workingDistance(Point2D s, Point2D e, Point2D p) throws IncompatibleOperandException {
		IVector seV=e.nSub(s);
		IVector spV=e.nSub(p);
		double projection=seV.dotProduct(spV);
		IVector sppV=e.nNormalise().scalarMultiply(projection);
		return spV.sub(sppV).norm();
	}
	
	private static IVector perpen(IVector s, IVector e) throws IncompatibleOperandException{
		IVector diff=e.nSub(s);
		double tmp=  diff.get(0);
		diff.set(0, -diff.get(1)).set(1, tmp);
		return diff;
	}

}
