package hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces;

import java.util.List;
import java.util.Stack;

public interface LoadSaveObject {

	public String getShapeID();
	public void load(Stack<GraphicalObject> stack, String data);
	public void save(List<String> rows);
	
}
