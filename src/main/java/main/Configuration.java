package main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Properties;

public class Configuration {
	public static final String dFormatShort = "yyyy.MM.dd";
	public static final String dFormatLong = "yyyy.MM.dd HH:mm:ss";
	private static Properties settings;
	public static SimpleDateFormat dateLong = new SimpleDateFormat(Configuration.dFormatLong);
	public static SimpleDateFormat dateShort = new SimpleDateFormat(Configuration.dFormatShort);
	public static final String mainFXMLResource = "/FXML/MainScene.fxml";
	public static final String newEntryFXMLResource = "/FXML/NewEntryScene.fxml";
	public static int repoType = 2;
	
	static {
		try {
			settings = System.getProperties();
			settings.load(ClassLoader.getSystemResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String name) {
		return settings.getProperty(name);
	}
	
	public static void showProperties() {
		Iterator<Object> keys = settings.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next().toString();
			System.out.println(key + " = " + settings.getProperty(key));
		}
	}
	
	
}
