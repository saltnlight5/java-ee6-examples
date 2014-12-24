/*
 *  Zemian Deng 2014
 */

package zemian.mvcexample.web.controller;

import zemian.service.util.Utils;

/**
 *
 * @author zedeng
 */
public class SysPropsController implements Controller {

    @Override
    public Object handle(WebRequest request) {
        return Utils.map("sysProps", System.getProperties());
    }

}
