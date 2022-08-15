package utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {

	private static boolean init=false;
	
	public static Logger getLogger(Class<?> c){
		if(init){
			return Logger.getLogger(c);
		}
		PropertyConfigurator.configure("log4j.properties");
		init = true;
		return Logger.getLogger(c);
	}
	
}