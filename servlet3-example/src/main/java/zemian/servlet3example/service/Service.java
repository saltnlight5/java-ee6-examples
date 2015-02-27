package zemian.servlet3example.service;

/**
 * A component that provide init and destroy lifecycle in an application.
 * 
 * @author zedeng
 *
 */
public interface Service {
	void init();
	void destroy();
}
