package billmanagepublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Bill Manage Publisher service started");
		BillManageServiceInterface bill = new BillManageService();
		serviceRegistration = bundleContext.registerService(BillManageServiceInterface.class.getName(), bill, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Bill Manage Publisher service stopped");
		serviceRegistration.unregister();
	}

}
