package hr.fer.zemris.ooup.lab3.model.Animal;

import java.lang.reflect.InvocationTargetException;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Animal parrot= AnimalFactory.newInstance("Parrot", "Milo");
		parrot.animalPrintGreeting();
		parrot.animalPrintMenu();
		
		Animal tiger= AnimalFactory.newInstance("Tiger", "Shere Khan");
		tiger.animalPrintGreeting();
		tiger.animalPrintMenu();
		
		
	}
}
