/*
 *  Zemian Deng 2014
 */
package zemian.service.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * A utility class that provide general programming helpers.
 *
 * @author zedeng
 */
public class Utils {
    
    // We do not intend to instanciate this class.

    private Utils() {
    }

    /**
     * A safe and string converter that guarantee to return a string regardless
     * of input.
     */
    public static String toString(Object object) {
        if (object == null) {
            return "null";
        }

        String result = null;
        // Just in case the implemenation of Object.toString is faulty, we
        // do not want exception thrown to caller.
        try {
            result = "" + object.toString();
        } catch (RuntimeException e) {
            result = System.identityHashCode(object) + "_BAD_TO_STRING";
        }
        return result;
    }

    public static String join(String sep, String... texts) {
        if (texts.length == 0) {
            return "";
        } else if (texts.length == 1) {
            return texts[0];
        } else {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < texts.length - 1; i++) {
                String text = texts[i];
                result.append(text);
                result.append(sep);
            }
            result.append(texts[texts.length - 1]);
            return result.toString();
        }
    }

    public static Map<String, Object> map(Object... pairs) {
        Map<String, Object> result = new HashMap();
        fillMap(result, pairs);
        return result;
    }

    public static Map<String, Object> sortedMap(Object... pairs) {
        Map<String, Object> result = new TreeMap();
        fillMap(result, pairs);
        return result;
    }

    public static void fillMap(Map<String, Object> map, Object... pairs) {
        if (pairs.length % 2 != 0) {
            throw new IllegalArgumentException("Arguments count is not even pairs");
        }

        for (int i = 0; i < pairs.length; i += 2) {
            Object key = pairs[i];
            Object value = pairs[i + 1];
            map.put(key.toString(), value);
        }
    }

    public static <T> List<T> list(Object... elements) {
        List<T> result = new ArrayList<>();
        for (Object e : elements) {
            result.add((T) e);
        }
        return result;
    }

    public static Properties readProps(File file) {
        Properties result = new Properties();
        try (FileReader reader = new FileReader(file)) {
            result.load(reader);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    /**
     * Read a UTF-8 text file into a list of String.
     */
    public static List<String> readLines(File file) {
        try {
            return Files.readAllLines(Paths.get(file.toURI()), Charset.forName("UTF-8"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Write all lines into file. I will not insert new line characters!
     */
    public static void writeLines(File file, String... lines) {
        try (FileWriter writer = new FileWriter(file)) {
            for (String line : lines) {
                writer.write(line);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Get a resource file/stream from current classpath and return its text
     * lines. NOTE: You should not prefix '/' in front of resource name!
     */
    public static List<String> readResourceLines(String resourceName) {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
        if (stream == null) {
            throw new RuntimeException("Resource not found: " + resourceName);
        }
        List<String> result = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
            return result;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Get a Properties file/stream from current classpath and return its text
     * lines. NOTE: You should not prefix '/' in front of resource name!
     */
    public static Properties readResourceProps(String resourceName) {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
        if (stream == null) {
            throw new RuntimeException("Resource not found: " + resourceName);
        }
        Properties props = new Properties();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            props.load(reader);
            return props;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static boolean isEmpty(String text) {
        boolean result = false;
        if (text == null || text.trim().equals("")) {
            result = true;
        }
        return result;
    }

    /**
     * A trick to unwrap a JavaScript (Rhino's Java class) to Java Class so it
     * can be used to pass to other Java methods!
     *
     * @param clz
     * @return same Java Class object.
     */
    public static Class<?> javaClass(Class<?> clz) {
        return clz;
    }

    public static void inspect(Object object) {
        System.out.println("object: " + object);
        System.out.println("object class: " + object.getClass());
        System.out.println("object identityHashCode: " + System.identityHashCode(object));
    }
    
    public static void runScript(String fileName) {
        runScript(fileName, map());
    }
    
    public static void runScript(String fileName, Map<String, Object> bindingsMap) {
        String ext = "js"; // Default to JavaScript.
        String[] names = fileName.split("\\.");
        if (names.length >= 2) {
            ext = names[names.length - 1];
        }
        try {
            ScriptEngine se = new ScriptEngineManager().getEngineByExtension(ext);
            try (FileReader reader = new FileReader(fileName)) {
                Bindings bindings = se.createBindings();
                bindings.putAll(bindingsMap);
                se.eval(reader, bindings);
            }
        } catch (IOException | ScriptException e) {
            throw new RuntimeException("Failed to evaluate script by engine: " + ext, e);
        }
    }
    
    public static <T> T evalScript(String engineName, String script, Map<String, Object> bindingsMap) {
        try {
            ScriptEngine se = new ScriptEngineManager().getEngineByName(engineName);
            try (StringReader reader = new StringReader(script)) {
                Bindings bindings = se.createBindings();
                bindings.putAll(bindingsMap);
                Object result = se.eval(reader, bindings);
                return (T)result;
            }
        } catch (ScriptException e) {
            throw new RuntimeException("Failed to evaluate script by engine: " + engineName, e);
        }
    }

    public static Class<?> loadClass(String className) {
        try {
            Class<?> clz = Class.forName(className);
            return clz;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printClassLoader(String msg) {
        System.out.println(msg);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        StringBuilder result = new StringBuilder();
        getClassLoaderTreeInfo(classLoader, result, "  ");
        System.out.println(result.toString());
    }

    public static void getClassLoaderTreeInfo(ClassLoader classLoader, StringBuilder sb, String indent) {
        sb.append(indent);
        sb.append(String.format("ClassLoader: %s", classLoader));
        sb.append("\n");
        ClassLoader parent = classLoader.getParent();
        if (parent != null) {
            getClassLoaderTreeInfo(parent, sb, indent + "  ");
        }
    }

    /** A temporary checkpoint in code to inspect stacktrace by throwing a harmless exception on purpose. */
    public static void printCheckpoint() {
        new Exception("Checkpoint").printStackTrace();
    }
}
