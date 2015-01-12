package zemian.jsfexample.web.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import zemian.jsfexample.data.User;
import zemian.jsfexample.service.UserService;

/**
 * @author zedeng
 *
 */
@ManagedBean
@SessionScoped
public class TableData {

    @Inject
    private UserService userService;
    
    public List<User> getUsers() {
        return userService.findAllUsers();
    }
}
