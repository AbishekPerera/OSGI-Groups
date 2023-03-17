package employeemanagement;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	ServiceRegistration empServiceRegistration;

	public void start(BundleContext context) throws Exception {
		
		System.out.println("Employee Publisher Started");
		
		employeePublisher emp = new employeeServiceImpl();
		
		empServiceRegistration = context.registerService(employeePublisher.class.getName(),emp, null);
	}

	public void stop(BundleContext context) throws Exception {
		
		System.out.println("Employee Publisher Stoped");
		empServiceRegistration.unregister();
		
	}

}




