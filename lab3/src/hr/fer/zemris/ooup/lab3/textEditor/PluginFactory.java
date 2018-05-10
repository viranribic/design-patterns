package hr.fer.zemris.ooup.lab3.textEditor;

import java.lang.reflect.InvocationTargetException;

import hr.fer.zemris.ooup.lab3.textEditor.plugins.Plugin;

public class PluginFactory {

	@SuppressWarnings("unchecked")
	public static Plugin newInstance(String pluginName) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Class<Plugin> classVar=null;
		classVar=(Class<Plugin>)Class.forName("hr.fer.zemris.ooup.lab3.textEditor.plugins."+pluginName);
		return (Plugin) classVar.newInstance();
	}
}
