package jsfdemo.service;

/**
 * Application startup and shutdown from standalone SE, command line environment.
 * 
 * <p>After application has started, the process will continue to run on foreground. User can hit CTRL+C to shutdown.
 * 
 * @author zedeng
 *
 */
public class ServiceApplicationMain {
	private static final ServiceApplication serviceApplication = ServiceApplication.getInstance();
	
	public static void main(String[] args) throws Exception {
		new ServiceApplicationMain().run();
	}

	public void run() {
		registerShutdownHook();
		startApplication();
		goToAwaitMode();		
	}

	private void goToAwaitMode() {
		// Put current thread into await mode. User can hit CTRL+C to shutdown.
		synchronized(serviceApplication) {
			try {
				serviceApplication.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void startApplication() {
		serviceApplication.quickStart();
	}

	private void registerShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				serviceApplication.quickStop();
			}
		});
	}
}
