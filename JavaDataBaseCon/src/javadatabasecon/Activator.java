package javadatabasecon;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	
	private ServiceRegistration DBserviceRegistration;

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Database Publisher service started");
		JDBCDemoInterface db = new JDBCDemo();
		DBserviceRegistration = bundleContext.registerService(JDBCDemoInterface.class.getName(), db, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("DB Publisher service stopped");
		DBserviceRegistration.unregister();
	}

}
