package hr.fer.zemris.ooup.lab4.guiElements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

import hr.fer.zemris.linearna.Point2D;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.Renderer;

public class G2DRendererImpl implements Renderer {

	
	private Graphics2D g2d;
	private Polygon poly=new Polygon();
	public G2DRendererImpl(Graphics2D g2d) {
		this.g2d=g2d;
	}
	
	@Override
	public void drawLine(Point2D s, Point2D e) {
		g2d.setColor(Color.BLUE);
		g2d.drawLine(s.getX(), s.getY(), e.getX(), e.getY());
	}

	@Override
	public void fillPolygon(Point2D[] points) {
		//decide if new poly is better than reset
		poly.reset();
		for(Point2D p:points)
			poly.addPoint(p.getX(), p.getY());
		g2d.setColor(Color.BLUE);
		g2d.fillPolygon(poly);
		g2d.setColor(Color.RED);
		g2d.drawPolygon(poly);
		
	}

}
