/*
 *  Zemian Deng 2014
 */

package zemian.mvcexample.web.controller;

import zemian.mvcexample.web.Controller;
import zemian.mvcexample.web.MView;
import zemian.mvcexample.web.WebRequest;

/**
 * A controller that return "sysProps" of the Java System Properties.
 * 
 * @author zedeng
 */
public class SysPropsController implements Controller {

    @Override
    public MView handle(WebRequest wreq) {
        MView mview = new MView(wreq.getControllerName());
        mview.put("sysProps", System.getProperties());
        return mview;
    }

}
