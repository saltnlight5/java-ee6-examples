/*
 *  Zemian Deng 2014
 */
package zemian.service.security;

/**
 * A service to secure data by encryption and/or decryption of a given input
 * data. Note that not all implementations need to provide decryption, such as 
 * MD5 which is only a one way hash algorithm.
 * 
 * @author zedeng
 */
public interface Encrypt {
    byte[] encrypt(byte[] plainData);
    byte[] decrypt(byte[] securedData);
}
