/*
 *  Zemian Deng 2014
 */

package zemian.mvcexample.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import zemian.mvcexample.service.Application;
import zemian.service.util.Utils;

/**
 * Return a list of controller names available in this application.
 * 
 * @author zedeng
 */
public class IndexController implements Controller {

    @Override
    public Object handle(WebRequest request) {
        Map<String, Controller> controllers = Application.getInstance().getControllers();
        ArrayList<String> names = new ArrayList<>(controllers.keySet());
        Collections.sort(names);
        return Utils.map("controllerNames", names);
    }

}
