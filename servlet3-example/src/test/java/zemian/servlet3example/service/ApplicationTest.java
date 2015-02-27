package zemian.servlet3example.service;


import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/*
 *  Zemian Deng 2014
 */

/**
 *
 * @author zedeng
 */
public class ApplicationTest {
    @Test
    public void testConfigPackageName() {  
        String packagePath = "/" + Config.class.getPackage().getName().replaceAll("\\.", "/");
        Assert.assertThat(packagePath, CoreMatchers.is("/zemian/servlet3example/service"));
    }
}
