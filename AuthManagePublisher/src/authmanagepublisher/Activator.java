package authmanagepublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Auth Manage Publisher service started");
		AuthManageServiceInterface user = new AuthManageService();
		serviceRegistration = bundleContext.registerService(AuthManageServiceInterface.class.getName(), user, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Auth Manage Publisher service stopped");
		serviceRegistration.unregister();
	}

}
