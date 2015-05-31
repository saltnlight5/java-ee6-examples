package zemian.mvcexample.service;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {	
	public static String FILE_ENCODING = "UTF-8";
	
	public static Properties loadProperties(String name) {
		try {
			try (InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(name)) {
				Properties result = new Properties();
				result.load(inStream);
				return result;
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to read claspath resource to Properties: " + name, e);
		}
	}
	
	public static String loadString(String name) {
		try {
			try (InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(name)) {
				return IOUtils.toString(inStream);
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to read classpath resource to String: " + name, e);
		}
	}
	
	/** Quickly create a map object from a list of String, Object pairs. */
	public static Map<String, Object> map(Object ... pairs) {
		Map<String, Object> result = new HashMap<String, Object>();
		for (int i = 0; i < pairs.length; i+=2) {
			result.put(pairs[i].toString(), pairs[i+1]);
		}
		return result;
	}
	
	public static String timestamp() {
		return new SimpleDateFormat("MMddyyyy-HHmm").format(new Date());
	}
	
	public static List<String> readLines(File file) throws Exception {
		try (FileReader reader = new FileReader(file)) {
			List<String> lines = IOUtils.readLines(reader);
			return lines;
		}
	}
}
