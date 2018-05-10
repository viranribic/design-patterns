package hr.fer.zemris.ooup.lab4.guiElements;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.linearna.Point2D;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.Renderer;

public class SVGRendererImpl implements Renderer {

	private List<String> lines = new ArrayList<>();
	private File inputFile;

	// public SVGRendererImpl(String fileName) {
	// // zapamti fileName; u lines dodaj zaglavlje SVG dokumenta:
	// // <svg xmlns=... >
	// // ...
	//
	//
	//
	// }

	public SVGRendererImpl(File inputFile) {
		this.inputFile = inputFile;
		this.lines.add("<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">");
	}

	public void close() throws IOException {
		// u lines još dodaj završni tag SVG dokumenta: </svg>
		// sve retke u listi lines zapiši na disk u datoteku
		// ...
		lines.add("</svg>");

		try (BufferedWriter out = new BufferedWriter(new FileWriter(this.inputFile))) {
			for(String l:this.lines){
				out.write(l);
				out.newLine();
			}

		} catch (IOException e) {

		}
	}

	@Override
	public void drawLine(Point2D s, Point2D e) {
		// Dodaj u lines redak koji definira linijski segment:
		// <line ... />
		this.lines.add("<line x1=\" " + s.getX() + " \" y1=\" " + s.getY() + " \" x2=\" " + e.getX() + " \" y2=\" "
				+ e.getY() + "\" style=\" stroke:rgb(0,0,255) \" />");
	}

	@Override
	public void fillPolygon(Point2D[] points) {
		// Dodaj u lines redak koji definira popunjeni poligon:
		// <polygon points="..." style="stroke: ...; fill: ...;" />
		String prefix = "<polygon points= \" ";
		StringBuilder sb = new StringBuilder();
		for (Point2D pt : points)
			sb.append(pt.getX() + "," + pt.getY() + " ");
		String sufix = " \" style= \" fill:rgb(0,0,255);stroke:rgb(255,0,0) \" />";
		this.lines.add(prefix + sb.toString() + sufix);
	}

}
