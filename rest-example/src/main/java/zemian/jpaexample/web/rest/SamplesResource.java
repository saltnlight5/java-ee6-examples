/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zemian.jpaexample.web.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zemian.jpaexample.dao.samples.UserSamples;
import zemian.jpaexample.data.User;

@Path("samples")
public class SamplesResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScriptResource.class);
   
    @Inject
    private UserSamples userSamples;
       
    @POST
    @Path("random-name-users")
    public List<User> createRandomNameUsers(@QueryParam("numOfUsers") @DefaultValue("10") int numOfUsers) {
        List<User> users = userSamples.createRandomNameUsers("tester", numOfUsers);
        LOGGER.info("Generated {} tester users.", users.size());
        return users;
    }
}