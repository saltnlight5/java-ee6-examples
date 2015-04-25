/*
 *  Zemian Deng 2014
 */
package zemian.mvcexample.web.controller;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

/**
 * A class to encapsulate all the data needed to process a request by a Controller.
 * 
 * @author zedeng
 */
public class WebRequest {
    private HttpServletRequest httpServletRequest;
    private Servlet mainServlet;

    public void setMainServlet(Servlet mainServlet) {
        this.mainServlet = mainServlet;
    }

    public Servlet getMainServlet() {
        return mainServlet;
    }
    
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

}
