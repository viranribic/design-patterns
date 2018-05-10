package hr.fer.zemris.ooup.lab4.test;

import hr.fer.zemris.linearna.IncompatibleOperandException;
import hr.fer.zemris.linearna.Point2D;
import hr.fer.zemris.ooup.lab4.graphicalObjects.GeometryUtil;

public class Tester {

	
	public static void main(String[] args) throws IncompatibleOperandException {
		Point2D s= new Point2D(0,0);
		Point2D e= new Point2D(1,0);
		Point2D p= new Point2D(2,-1);
		System.out.println("Point: "+p);
		System.out.println( "Dist: "+GeometryUtil.distanceFromLineSegment(s, e, p));
		
	}
	
	
	/* 					|					
	 * 					|					
	 * 					|					 
	 * 				x	|	x		x			
	 * -----------------0----0--------------
	 * 				x	|	x		x			
	 * 					|					
	 * 					|					 
	 * 					|					
	 */
	
	// -1  0
	// -1  1
	//  0  1
	//  1  1
	//  1  0
	//  1 -1 
	//  0 -1
	// -1 -1
	
	
}
