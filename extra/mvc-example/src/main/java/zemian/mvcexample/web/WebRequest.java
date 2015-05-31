/*
 *  Zemian Deng 2014
 */
package zemian.mvcexample.web;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

/**
 * A class to encapsulate all the data needed to process a request by a Controller.
 * 
 * @author zedeng
 */
public class WebRequest {
    private HttpServletRequest httpServletRequest;
    private String controllerName;

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

}
