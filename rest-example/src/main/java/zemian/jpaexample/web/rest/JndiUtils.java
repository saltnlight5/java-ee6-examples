/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zemian.jpaexample.web.rest;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import zemian.service.logging.Logger;

/**
 *
 * @author zedeng
 */
public class JndiUtils {
    private static final Logger LOGGER = new Logger(JndiUtils.class);
    
    public static <T> T lookup(String name) {
        T result = null;
        try {
            InitialContext ic = new InitialContext();
            result = (T) ic.lookup(name);
            return result;
        } catch (NamingException e) {
            throw new RuntimeException("Failed to lookup JNDI name: " + name, e);
        }
    }
    
    public static void printJndiTree() {
        try {
            InitialContext ic = new InitialContext();
            printJndiContext(ic, "");
        } catch (NamingException e) {
            throw new RuntimeException("Failed to lookup JNDI.", e);
        }
    }
    
    public static void printJndiContext(Context context, String pacer) {
        try {
            System.out.println(pacer + "Context=" + context);
            NamingEnumeration<Binding> bindings = context.listBindings("");
            while (bindings.hasMoreElements()) {
                Binding binding = bindings.nextElement();
                String name = binding.getName();
                Object object = binding.getObject();
                System.out.println(name + ": " + object);
                if (object instanceof Context && object != context) {
                    printJndiContext((Context)object, pacer + "  ");
                }
            }
        } catch (NamingException e) {
            throw new RuntimeException("Failed to lookup JNDI.", e);
        }
    }
}
