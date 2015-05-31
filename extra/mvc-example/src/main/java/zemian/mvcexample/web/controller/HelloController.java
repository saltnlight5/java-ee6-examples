/*
 *  Zemian Deng 2014
 */

package zemian.mvcexample.web.controller;

import zemian.mvcexample.web.Controller;
import zemian.mvcexample.web.MView;
import zemian.mvcexample.web.WebRequest;

import java.util.Date;

/**
 * Just a test controller that return "message" and "serverTime" as model map. 
 * This is mainly for testing purpose.
 * 
 * @author zedeng
 */
public class HelloController implements Controller {

    @Override
    public MView handle(WebRequest wreq) {
        MView mview = new MView(wreq.getControllerName());
        mview.put("message", "Hello World.");
        mview.put("serverTime", new Date());
        return mview;
    }

}
