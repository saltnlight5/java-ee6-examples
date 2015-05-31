package zemian.mvcexample.web;

/**
 * User: zedeng
 * Date Time: 5/21/15 8:47 PM
 */
public interface Controller {
    MView handle(WebRequest wreq) throws Exception;
}
