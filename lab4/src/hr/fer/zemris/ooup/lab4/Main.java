package hr.fer.zemris.ooup.lab4;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.ooup.lab4.graphicalObjects.LineSegment;
import hr.fer.zemris.ooup.lab4.graphicalObjects.Oval;
import hr.fer.zemris.ooup.lab4.graphicalObjects.Triangle;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.GraphicalObject;
import hr.fer.zemris.ooup.lab4.guiElements.GUI;

public class Main {

	public static void main(String[] args) {
		List<GraphicalObject> objects=new ArrayList<>();
		objects.add(new LineSegment());
		objects.add(new Oval());
		objects.add(new Triangle());
		
		new GUI(objects).setVisible(true);
	}
}
