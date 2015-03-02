/*
 *  Zemian Deng 2014
 */

package zemian.service.util;

import org.hamcrest.CoreMatchers;
import org.junit.*;

/**
 *
 * @author zedeng
 */
public class UtilsTest {
    
    @Test
    public void demo() {
    }
    
    /** 
     * These are not really asserting test, but to run it manually to do
     * a quick eye inspection of the Utils does work.
     */
    @Test
    public void demoUtils() {
        System.out.println("Empty list: " + Utils.list());
        System.out.println("Full list: " + Utils.list("A", "B", "C"));
        System.out.println("Mixed list: " + Utils.list("A", 123));
        
        System.out.println("Empty map: " + Utils.map());
        System.out.println("Full map: " + Utils.map("A", "aaa", "B", "bbb", "C", "ccc"));
        
        System.out.println("String join: " + Utils.join("/", "A", "B", "C"));
        
        Utils.printClassLoader("JUnit ClassLoader.");
        
        Utils.printCheckpoint();
        
        System.out.println("Eval script: " + Utils.evalScript("js", "Math.pow(12, n);", "n", 2));
        
        System.out.println("Run script: ");
        Utils.runScript("src/main/resources/zemian/service/util/runscript-test.js", "n", 2);
        
        System.out.println("Run test scripts: ");
        Utils.runTestScript("n", 2);
    }
    
    @Test
    public void testJoin() {
        Assert.assertThat(Utils.join("/"), CoreMatchers.is(""));
        Assert.assertThat(Utils.join("/", "A"), CoreMatchers.is("A"));
        Assert.assertThat(Utils.join("/", "A", "B"), CoreMatchers.is("A/B"));
        Assert.assertThat(Utils.join("/", "A", "B", "C"), CoreMatchers.is("A/B/C"));
    }
}
