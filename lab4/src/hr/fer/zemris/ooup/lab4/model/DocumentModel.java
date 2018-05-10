package hr.fer.zemris.ooup.lab4.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import hr.fer.zemris.linearna.Point2D;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.GraphicalObject;
import hr.fer.zemris.ooup.lab4.graphicalObjects.interfaces.GraphicalObjectListener;

public class DocumentModel {

	public final static double SELECTION_PROXIMITY = 10;

	// Kolekcija svih grafičkih objekata:
	private List<GraphicalObject> objects = new ArrayList<>();
	// Read-Only proxy oko kolekcije grafičkih objekata:
	private List<GraphicalObject> roObjects = Collections.unmodifiableList(objects);
	// Kolekcija prijavljenih promatrača:
	private List<DocumentModelListener> listeners = new ArrayList<>();
	// Kolekcija selektiranih objekata:
	private List<GraphicalObject> selectedObjects = new ArrayList<>();
	// Read-Only proxy oko kolekcije selektiranih objekata:
	private List<GraphicalObject> roSelectedObjects = Collections.unmodifiableList(selectedObjects);

	// Promatrač koji će biti registriran nad svim objektima crteža...
	private final GraphicalObjectListener goListener = new GraphicalObjectListener() {

		@Override
		public void graphicalObjectChanged(GraphicalObject go) {
			// TODO Auto-generated method stub
			// System.out.println("missing - graphicalObjectChanged");
			if (!objects.contains(go)) {
				objects.add(go);
			}
			objects.sort(new Comparator<GraphicalObject>() {
				@Override
				public int compare(GraphicalObject o1, GraphicalObject o2) {
					return -Double.compare(o1.getZValue(), o2.getZValue());
				}
			});
			notifyListeners();
		}

		@Override
		public void graphicalObjectSelectionChanged(GraphicalObject go) {
			// TODO Auto-generated method stub
			// System.out.println("missing - graphicalObjectSelectionChanged");
			if (!selectedObjects.contains(go))
				selectedObjects.add(go);
			notifyListeners();
		}

	};

	// Konstruktor...
	public DocumentModel() {

	}

	// Brisanje svih objekata iz modela (pazite da se sve potrebno odregistrira)
	// i potom obavijeste svi promatrači modela
	public void clear() {
		// TODO Auto-generated method stub
		for (GraphicalObject o : this.objects) {
			o.removeGraphicalObjectListener(goListener);
		}
		this.selectedObjects.clear();
		this.objects.clear();
		this.notifyListeners();
	}

	// Dodavanje objekta u dokument (pazite je li već selektiran; registrirajte
	// model kao promatrača)
	public void addGraphicalObject(GraphicalObject obj) {
		if (!this.objects.contains(obj)) {
			this.objects.add(obj);
			obj.addGraphicalObjectListener(this.goListener);
			this.notifyListeners();
		}

	}

	// Uklanjanje objekta iz dokumenta (pazite je li već selektiran;
	// odregistrirajte model kao promatrača)
	public void removeGraphicalObject(GraphicalObject obj) {
		if (this.selectedObjects.contains(obj)) {
			this.selectedObjects.remove(obj);
			obj.removeGraphicalObjectListener(this.goListener);
			this.notifyListeners();
		}

	}

	// Vrati nepromjenjivu listu postojećih objekata (izmjene smiju ići samo
	// kroz metode modela)
	public List<GraphicalObject> list() {
		return this.roObjects;
	}

	// Prijava...
	public void addDocumentModelListener(DocumentModelListener l) {
		if (!this.listeners.contains(l)) {
			this.listeners.add(l);
		}
	}

	// Odjava...
	public void removeDocumentModelListener(DocumentModelListener l) {
		if (this.listeners.contains(l)) {
			this.listeners.remove(l);
		}
	}

	// Obavještavanje...
	public void notifyListeners() {
		for (DocumentModelListener l : this.listeners)
			l.documentChange();
	}

	// Vrati nepromjenjivu listu selektiranih objekata
	public List<GraphicalObject> getSelectedObjects() {
		return this.roSelectedObjects;
	}

	// Pomakni predani objekt u listi objekata na jedno mjesto kasnije...
	// Time će se on iscrtati kasnije (pa će time možda veći dio biti vidljiv)
	public void increaseZ(GraphicalObject go) {
		if (this.objects.contains(go)) {
			int pos = this.objects.indexOf(go);
			if (pos != this.objects.size() - 1) {
				this.objects.remove(go);
				this.objects.add(pos + 1, go);
			} // else object is last
		} // else object does't exist
		this.notifyListeners();
	}

	// Pomakni predani objekt u listi objekata na jedno mjesto ranije...
	public void decreaseZ(GraphicalObject go) {
		if (this.objects.contains(go)) {
			int pos = this.objects.indexOf(go);
			if (pos != 0) {
				this.objects.remove(go);
				this.objects.add(pos - 1, go);
			} // else object is first
		} // else object does't exist
		this.notifyListeners();
	}

	// Pronađi postoji li u modelu neki objekt koji klik na točku koja je
	// predana kao argument selektira i vrati ga ili vrati null. Točka selektira
	// objekt kojemu je najbliža uz uvjet da ta udaljenost nije veća od
	// SELECTION_PROXIMITY. Status selektiranosti objekta ova metoda NE dira.
	public GraphicalObject findSelectedGraphicalObject(Point2D mousePoint) {
		for (GraphicalObject o : this.objects)
			for (int index = 0; index < o.getNumberOfHotPoints(); index++)
				if (o.getHotPointDistance(index, mousePoint) < SELECTION_PROXIMITY)
					return o;

		return null;

	}

	// Pronađi da li u predanom objektu predana točka miša selektira neki
	// hot-point.
	// Točka miša selektira onaj hot-point objekta kojemu je najbliža uz uvjet
	// da ta
	// udaljenost nije veća od SELECTION_PROXIMITY. Vraća se indeks hot-pointa
	// kojeg bi predana točka selektirala ili -1 ako takve nema. Status
	// selekcije
	// se pri tome NE dira.
	public int findSelectedHotPoint(GraphicalObject object, Point2D mousePoint) {
		for (int index = 0; index < object.getNumberOfHotPoints(); index++)
			if (object.getHotPointDistance(index, mousePoint) < SELECTION_PROXIMITY)
				return index;

		return -1;
	}

}
