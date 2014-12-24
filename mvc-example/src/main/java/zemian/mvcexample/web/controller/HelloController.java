/*
 *  Zemian Deng 2014
 */

package zemian.mvcexample.web.controller;

import java.util.Date;
import zemian.service.util.Utils;

/**
 *
 * @author zedeng
 */
public class HelloController implements Controller {

    @Override
    public Object handle(WebRequest request) {
        return Utils.map("message", "Hello World.", "serverTime", new Date());
    }

}
