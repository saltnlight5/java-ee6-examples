package zemian.jpaexample.web.controller;

import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Welcome {
    
    private Date currentTime = new Date();

    public Date getCurrentTime() {
        return currentTime;
    }
    
}
