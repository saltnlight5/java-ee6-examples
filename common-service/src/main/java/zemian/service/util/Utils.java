/*
 *  Zemian Deng 2014
 */

package zemian.service.util;

/**
 *
 * @author zedeng
 */
public class Utils {
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
}
