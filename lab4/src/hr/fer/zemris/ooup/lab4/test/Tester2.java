package hr.fer.zemris.ooup.lab4.test;

import hr.fer.zemris.ooup.lab4.graphicalObjects.LineSegment;
import hr.fer.zemris.ooup.lab4.graphicalObjects.Rectangle;

public class Tester2 {

	
	public static void main(String[] args) {
		Rectangle rec= new Rectangle(0, 0, 4, 3);
		for(LineSegment ls:rec.borderIterator()){
			System.out.println(ls);
		}
	}
}
