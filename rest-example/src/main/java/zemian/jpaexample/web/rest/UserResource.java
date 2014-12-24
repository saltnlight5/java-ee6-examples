/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zemian.jpaexample.web.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import zemian.jpaexample.dao.UserDao;
import zemian.jpaexample.data.User;
import zemian.service.logging.Logger;

@Path("user")
public class UserResource {
    private static final Logger LOGGER = new Logger(UserResource.class);
   
    @Inject
    private UserDao userDao;
       
    @POST
    public User create(User user) {
        userDao.save(user);
        return user;
    }
    
    @PUT
    public User update(User user) {
        return userDao.update(user);
    }
    
    @GET
    @Path("{username}")
    public User find(@PathParam("username") String username) {
        return userDao.find(username);
    }
    
    @DELETE
    @Path("{username}")
    public void delete(@PathParam("username") String username) {
        userDao.delete(username);
    }
}