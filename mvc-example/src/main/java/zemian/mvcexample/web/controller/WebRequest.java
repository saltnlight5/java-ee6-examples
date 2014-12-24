/*
 *  Zemian Deng 2014
 */
package zemian.mvcexample.web.controller;

import javax.servlet.http.HttpServletRequest;
import zemian.mvcexample.web.MvcMainServlet;

/**
 *
 * @author zedeng
 */
public class WebRequest {
    private HttpServletRequest httpServletRequest;
    private MvcMainServlet mvcMainServlet;

    public void setMvcMainServlet(MvcMainServlet mvcMainServlet) {
        this.mvcMainServlet = mvcMainServlet;
    }

    public MvcMainServlet getMvcMainServlet() {
        return mvcMainServlet;
    }
    
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

}
