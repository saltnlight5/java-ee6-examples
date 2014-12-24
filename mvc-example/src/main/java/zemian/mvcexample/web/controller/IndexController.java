/*
 *  Zemian Deng 2014
 */

package zemian.mvcexample.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import zemian.service.util.Utils;

/**
 *
 * @author zedeng
 */
public class IndexController implements Controller {

    @Override
    public Object handle(WebRequest request) {
        Map<String, Controller> controllers = request.getMvcMainServlet().getControllers();
        ArrayList<String> names = new ArrayList<>(controllers.keySet());
        Collections.sort(names);
        return Utils.map("controllerNames", names);
    }

}
