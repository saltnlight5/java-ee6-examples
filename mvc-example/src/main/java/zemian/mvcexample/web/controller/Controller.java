/*
 *  Zemian Deng 2014
 */
package zemian.mvcexample.web.controller;

/**
 * A controller to handle web request. Implementation should return an object
 * as model to be display by a view (such as JSP). If the model instance returned 
 * is an java.util.Map, then all the names and values will be available to the
 * view as request attributes, else it will be stored with single "model"
 * name.
 * 
 * <p>Each controller implementation should also provide a corresponding view
 * that will use the model for display.
 * 
 * @author zedeng
 */
public interface Controller {
    Object handle(WebRequest request);
}
