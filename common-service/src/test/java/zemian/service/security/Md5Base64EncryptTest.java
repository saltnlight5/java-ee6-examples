/*
 *  Zemian Deng 2014
 */

package zemian.service.security;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author zedeng
 */
public class Md5Base64EncryptTest {
    
    private Encrypt encrypt = new Md5Base64Encrypt();
    
    @Test
    public void testEncrypt() {
        byte[] hashed = encrypt.encrypt("Zemian".getBytes());
        Assert.assertThat(new String(hashed), CoreMatchers.is("6GhpmX+6PjN0K3oQZEOaBSn06zJqkMGs395uqI8P4GI="));
        
        hashed = encrypt.encrypt("Zemian Deng".getBytes());
        Assert.assertThat(new String(hashed), CoreMatchers.is("yek+nr13av13NkYinByRyufYNrs++117qZpKgsRsmTM="));
        
        hashed = encrypt.encrypt("S3cret123&^%".getBytes());
        System.out.println(new String(hashed));
        Assert.assertThat(new String(hashed), CoreMatchers.is("jro0If66LNlKtR919shouOJw10KSGyN2/y5HtBX/4NM="));        
    }
}
