package genericlibs;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileLibrary 
{
	public static String getValue(String filePath, String key) {
		try {
			FileInputStream propFile = new FileInputStream(filePath);
			Properties prop = new Properties();
			prop.load(propFile);
			
			return prop.getProperty(key);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}  
}
