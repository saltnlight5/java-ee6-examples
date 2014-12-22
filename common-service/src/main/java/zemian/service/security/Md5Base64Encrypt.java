/*
 *  Zemian Deng 2014
 */

package zemian.service.security;

import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author zedeng
 */
public class Md5Base64Encrypt implements Encrypt {    
    @Override
    public byte[] encrypt(byte[] plainData) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digestedData = md.digest(plainData);
            String result = DatatypeConverter.printBase64Binary(digestedData);
            return result.getBytes("UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Failed to hash data.", e);
        }
    }

    @Override
    public byte[] decrypt(byte[] securedData) {
        throw new UnsupportedOperationException("Not supported.");
    }

}
