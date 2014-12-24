/*
 *  Zemian Deng 2014
 */

package zemian.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A utility class that provide general programming helpers.
 * 
 * @author zedeng
 */
public class Utils {
    // We do not intend to instanciate this class.
    private Utils() {
    }
    
    /** A safe and string converter that guarantee to return a string regardless
     of input. */
    public static String toString(Object object) {
        if (object == null)
            return "null";
        
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
    
    public static String join(String sep, String ... texts) {
        StringBuilder result = new StringBuilder();
        for (String text : texts)
            result.append(text);
        return result.toString();
    }
    
    public static Map<String, Object> map(Object ... pairs) {
        if (pairs.length % 2 != 0)
            throw new IllegalArgumentException("Arguments count is not even pairs");
        
        Map<String, Object> result = new HashMap<>();
        for (int i = 0; i < pairs.length; i += 2) {
            Object key = pairs[i];
            Object value = pairs[i + 1];
            result.put(key.toString(), value);
        }
        return result;
    }
    
    public static <T> List<T> list(T ... elements) {
        List<T> result = new ArrayList<>();
        for (T e : elements) {
            result.add(e);
        }
        return result;
    }
}
