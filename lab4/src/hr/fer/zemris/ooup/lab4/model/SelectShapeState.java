package hr.fer.zemris.ooup.lab4.model;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import hr.fer.zemris.linearna.Point2D;
import hr.fer.zemris.ooup.lab4.graphicalObjects.CompositeShape;
import hr.fer.zemris.ooup.lab4.graphicalObjects.Rectangle;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.GraphicalObject;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.Renderer;

public class SelectShapeState implements State {

	private DocumentModel model;

	public SelectShapeState(DocumentModel model) {
		this.model = model;
	}

	@Override
	public void mouseDown(Point2D mousePoint, boolean shiftDown, boolean ctrlDown) {
		GraphicalObject obj = this.model.findSelectedGraphicalObject(mousePoint);
		if (obj == null) {
			if (!ctrlDown)
				unselectAll();
			return;
		} else {
			if (!ctrlDown)
				unselectAll();
			obj.setSelected(true);
		}

	}

	private void unselectAll() {
		for (GraphicalObject selectedObj : this.model.list())
			if (selectedObj.isSelected())
				selectedObj.setSelected(false);
	}

	@Override
	public void mouseUp(Point2D mousePoint, boolean shiftDown, boolean ctrlDown) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(Point2D mousePoint) {
		GraphicalObject selected = getOnlySeleced();
		if (selected != null) {
			int index = getSelectedHotpointIndex(mousePoint, selected);
			selected.setHotPoint(index, mousePoint);
		}

	}

	private int getSelectedHotpointIndex(Point2D mousePoint, GraphicalObject selected) {
		double minSel = Double.MAX_VALUE;
		int index = -1;
		if (selected.isSelected())
			for (int i = 0; i < selected.getNumberOfHotPoints(); i++) {
				double dist = selected.getHotPointDistance(i, mousePoint);
				if (dist < minSel) {
					minSel = dist;
					index = i;
				}
			}
		return index;
	}

	@Override
	public void keyPressed(int keyCode) {
		GraphicalObject selected = getOnlySeleced();
		if (selected != null) {
			switch (keyCode) {
			case KeyEvent.VK_PLUS:
			case 107:
				bringInFront(selected);
				break;
			case KeyEvent.VK_MINUS:
			case 109:
				sendToBack(selected);
				break;
			case KeyEvent.VK_UP:
				upSelected(selected);
				break;
			case KeyEvent.VK_DOWN:
				downSelected(selected);
				break;
			case KeyEvent.VK_LEFT:
				leftSelected(selected);
				break;
			case KeyEvent.VK_RIGHT:
				rightSelected(selected);
				break;
			default:
				break;
			}
		}
		
		switch (keyCode) {
		case KeyEvent.VK_G:
			formGroup();
			break;
		case KeyEvent.VK_U:
			unseletGroup();
			break;
		default:
			break;
		}
	}

	private void unseletGroup( ) {
		boolean isGroup=false;
		
		CompositeShape cs=null;
		LinkedList<GraphicalObject> otherShapes=new LinkedList<>();
		
		for(GraphicalObject first:this.model.list()){
			if(first instanceof CompositeShape){
				isGroup=true;
				cs=(CompositeShape) first;
				continue;
			}
			if(isGroup){
				otherShapes.add(first);
			}else{
				break;	
			}
		}
		
		if(isGroup){
			List<GraphicalObject> selected=cs.getList();
			
//			for(GraphicalObject go:selected)
//				System.out.println("S: "+go);
//			for(GraphicalObject go:otherShapes)
//				System.out.println("U: "+go);
			
			
			this.model.clear();
			for(GraphicalObject go:selected){
				go.setSelected(true);
				this.model.addGraphicalObject(go);
			}
			for(GraphicalObject go:otherShapes){
				this.model.addGraphicalObject(go);
			}
		}
	}


	private void formGroup() {
		for(GraphicalObject go:this.model.list())
			if(go instanceof CompositeShape)
				return;
			else
				break;
		
		LinkedList<GraphicalObject> selectedObj=new LinkedList<>();
		LinkedList<GraphicalObject> unselectedObj=new LinkedList<>();
		
		for(GraphicalObject go:this.model.list())
			if(go.isSelected())
				selectedObj.add(go);
			else
				unselectedObj.add(go);
		
//		for(GraphicalObject go:selectedObj)
//			System.out.println("S: "+go);
//		for(GraphicalObject go:unselectedObj)
//			System.out.println("U: "+go);
		
		this.model.clear();
		CompositeShape cs=new CompositeShape(selectedObj);
		this.model.addGraphicalObject(cs);
		for(GraphicalObject go:unselectedObj)
			this.model.addGraphicalObject(go);
	}

	private void rightSelected(GraphicalObject selected) {
		selected.translate(new Point2D(1, 0));
	}

	private void leftSelected(GraphicalObject selected) {
		selected.translate(new Point2D(-1, 0));
	}

	private void downSelected(GraphicalObject selected) {
		selected.translate(new Point2D(0, +1));
	}

	private void upSelected(GraphicalObject selected) {
		selected.translate(new Point2D(0, -1));
	}

	private void bringInFront(GraphicalObject selected) {
		selected.decZValue(1);

	}

	private void sendToBack(GraphicalObject selected) {
		selected.incZValue(1);

	}

	@Override
	public void afterDraw(Renderer r, GraphicalObject go) {
		if (go.isSelected()) {
			Rectangle rct = go.getBoundingBox();
			rct.render(r);
		}
	}

	@Override
	public void afterDraw(Renderer r) {
		GraphicalObject selected = getOnlySeleced();
		if (selected != null)
			for (int hpIndex = 0; hpIndex < selected.getNumberOfHotPoints(); hpIndex++)
				ptToRect(selected.getHotPoint(hpIndex)).render(r);

	}

	private Rectangle ptToRect(Point2D hotPoint) {
		return new Rectangle(hotPoint.getX() - 3, hotPoint.getY() - 3, 6, 6);
	}

	private GraphicalObject getOnlySeleced() {
		int selected = 0;
		GraphicalObject oneSelected = null;
		for (GraphicalObject o : this.model.list()) {
			if (o.isSelected()) {
				selected++;
				oneSelected = o;
			}
		}
		if (selected == 1)
			return oneSelected;
		else
			return null;
	}

	@Override
	public void onLeaving() {
		for (GraphicalObject go : this.model.list())
			go.setSelected(false);

	}

}
