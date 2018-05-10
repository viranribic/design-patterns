package hr.fer.zemris.ooup.lab4.guiElements;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import hr.fer.zemris.linearna.Point2D;
import hr.fer.zemris.ooup.lab4.graphicalObjects.LineSegment;
import hr.fer.zemris.ooup.lab4.graphicalObjects.Oval;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.GraphicalObject;
import hr.fer.zemris.ooup.lab4.model.AddShapeState;
import hr.fer.zemris.ooup.lab4.model.DocumentModel;
import hr.fer.zemris.ooup.lab4.model.EreserState;
import hr.fer.zemris.ooup.lab4.model.IdleState;
import hr.fer.zemris.ooup.lab4.model.SelectShapeState;
import hr.fer.zemris.ooup.lab4.model.State;

public class GUI extends JFrame {

	/**
	 * Auto generated serial version UID.
	 */
	private static final long serialVersionUID = -1412067556549875778L;
	private List<GraphicalObject> objects;
	private DocumentModel document;
	private State currentState;
	private HashMap<String,GraphicalObject> objectsMap;

	public GUI(List<GraphicalObject> objects) {
		this.objects = objects;
		this.document = new DocumentModel();
		this.currentState = IdleState.getState();
		this.objectsMap=new HashMap<>();
		
		for(GraphicalObject obj:objects){
			String id=obj.getShapeID().split(":")[0];
			this.objectsMap.put(id, obj);
		}
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(640, 480);
		this.setTitle("Paint");
		this.setLocationRelativeTo(null);
		this.setFocusTraversalKeysEnabled(false);

		configView(objects);
		// configActions();
	}

	private void configView(List<GraphicalObject> objects) {
		// add toolbar
		JToolBar toolbar = new JToolBar();
		JButton load = new JButton("Load");
		JButton save = new JButton("Save");
		JButton export = new JButton("Export SVG");

		JButton[] shapeButtons = new JButton[objects.size()];

		JButton select = new JButton("Select");
		JButton clear = new JButton("Clear");

		load.addActionListener((e) -> {
			JFileChooser c = new JFileChooser();
			// Demonstrate "Open" dialog:
			int rVal = c.showOpenDialog(this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				processLoad(c);
				
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				// nothing chosen
			}
		});

		save.addActionListener((e) -> {
			JFileChooser c = new JFileChooser();
			// Demonstrate "Open" dialog:
			int rVal = c.showSaveDialog(this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				File inputFile = c.getSelectedFile();

				try (BufferedWriter out = new BufferedWriter(new FileWriter(inputFile))) {
					
					LinkedList<String> rows=new LinkedList<>();
					for(GraphicalObject go: document.list()){
						go.save(rows);
					}
					
					for(String l:rows){
						out.write(l);
						out.newLine();
					}
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				return;
			}
		});

		export.addActionListener((e) -> {
			JFileChooser c = new JFileChooser();
			// Demonstrate "Open" dialog:
			int rVal = c.showSaveDialog(this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				File inputFile = c.getSelectedFile();
				SVGRendererImpl r = new SVGRendererImpl(inputFile);
				for (GraphicalObject obj : document.list()) {
					obj.render(r);
				}
				try {
					r.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				return;
			}

		});

		int i = 0;
		for (GraphicalObject obj : objects) {
			JButton shape = new JButton(obj.getShapeName());
			shape.addActionListener((e) -> {
				currentState.onLeaving();
				currentState = new AddShapeState(document, obj);
			});
			shapeButtons[i++] = shape;
		}

		select.addActionListener((e) -> {
			currentState.onLeaving();
			currentState = new SelectShapeState(document);
		});
		clear.addActionListener((e) -> {
			currentState.onLeaving();
			currentState = new EreserState(document);
		});

		toolbar.add(load);
		toolbar.add(save);
		toolbar.add(export);
		for (JButton shapeBtn : shapeButtons)
			toolbar.add(shapeBtn);
		toolbar.add(select);
		toolbar.add(clear);

		// add pane
		PaintCanvas canvas = new PaintCanvas(this.document, this);

		canvas.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.equals(KeyEvent.VK_ESCAPE)) {
					currentState.onLeaving();
					currentState = IdleState.getState();
				} else
					currentState.keyPressed(e.getKeyCode());
			}
		});

		canvas.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				canvas.requestFocus();
				if (SwingUtilities.isLeftMouseButton(e)) {
					Point2D mousePoint = pointToPoint2D(e.getPoint());
					currentState.mouseDown(mousePoint, e.isShiftDown(), e.isControlDown());
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				canvas.requestFocus();
				if (SwingUtilities.isLeftMouseButton(e)) {
					Point2D mousePoint = pointToPoint2D(e.getPoint());
					currentState.mouseUp(mousePoint, e.isShiftDown(), e.isControlDown());
				}
			}

		});

		canvas.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				canvas.requestFocus();
				if (SwingUtilities.isLeftMouseButton(e)) {
					currentState.mouseDragged(pointToPoint2D(e.getPoint()));
				}
			}

		});

		canvas.requestFocus();
		// canvas.requestFocusInWindow();

		JPanel mainPanel = new JPanel(new BorderLayout());

		mainPanel.add(toolbar, BorderLayout.NORTH);
		mainPanel.add(canvas, BorderLayout.CENTER);
		this.add(mainPanel);
	}

	private void processLoad(JFileChooser c) {
		File inputFile = c.getSelectedFile();
		List<String> lines=fileToLines(inputFile);
		Stack<GraphicalObject> objStac=new Stack<>();
		
		for(String line:lines){
			if(line.length()==0) continue;
			int splitIndex=line.indexOf(':');
			
			String id=line.substring(0, splitIndex);
			String params=line.substring(splitIndex+1);
			
			GraphicalObject prototype=this.objectsMap.get(id);
			prototype.load(objStac, params);
			
		}
		
		//TODO !!
		
		for(GraphicalObject go:objStac){
			this.document.addGraphicalObject(go);
		}
	}

	private List<String> fileToLines(File inputFile) {
		LinkedList<String> list=new LinkedList<>();
		
		try (BufferedReader in = new BufferedReader(new FileReader(inputFile))) {
			while(true){
				String line=in.readLine();
				if(line==null)
					break;
				list.add(line);
			}
		} catch (IOException e) {

		}
		return list;
	}

	public State getCurrentState() {
		return this.currentState;
	}

	private static Point2D pointToPoint2D(Point p) {
		return new Point2D(p.getX(), p.getY());
	}
}
