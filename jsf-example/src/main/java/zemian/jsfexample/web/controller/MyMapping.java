package zemian.jsfexample.web.controller;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class MyMapping {
    private String message = "Hello";

    public String getMessage() {
        return message;
    }
}
