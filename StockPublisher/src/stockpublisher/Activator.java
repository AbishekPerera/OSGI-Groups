package stockpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	
	ServiceRegistration stockServiceRegistration;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Stock publisher started");
		IStockService iStockService = new StockServiceImpl();
		stockServiceRegistration = context.registerService(IStockService.class.getName(), iStockService, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stock Publisher stopped");
		stockServiceRegistration.unregister();
	}
}
