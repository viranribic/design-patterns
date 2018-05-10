package hr.fer.zemris.ooup.lab3.model.Animal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AnimalFactory {

	@SuppressWarnings("unchecked")
	public static Animal newInstance(String animalKind,String name) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Class<Animal> classVar=null;
		classVar=(Class<Animal>)Class.forName("hr.fer.zemris.ooup.lab3.model.plugins."+animalKind);
		Constructor<?> cctr= classVar.getConstructor(String.class);
		
		return (Animal) cctr.newInstance(name);
	}
}
