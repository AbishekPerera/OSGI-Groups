package mainmanageconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import authmanagepublisher.AuthManageServiceInterface;

public class Activator implements BundleActivator {

	ServiceReference AuthServiceReference;

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Start Subscriber Service");

		AuthServiceReference = bundleContext.getServiceReference(AuthManageServiceInterface.class.getName());
		@SuppressWarnings("unchecked")
		AuthManageServiceInterface user = (AuthManageServiceInterface) bundleContext.getService(AuthServiceReference);

		MainIn(user);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Good Bye!");
		bundleContext.ungetService(AuthServiceReference);
	}

	private void MainIn(AuthManageServiceInterface user) {

		boolean IsLogedIn = user.authUser();
		// System.out.println(IsLogedIn);

		// user.getAllUsers();

		if (IsLogedIn) {
			System.out.println("Welcome to the Wireles waves Admin Panel");

			// user.getAllUsers();

			int choice = 0;
			String subOption = "y";

			Scanner sc = new Scanner(System.in);

			System.out.println("\n");

			while (subOption.equalsIgnoreCase("y")) {
				System.out.println("----------Home Page----------\n");
				System.out.println("1  - Bill Management System");
				System.out.println("2  - Stcock Management System");
				System.out.println("3  - Supplier Management System");
				System.out.println("4  - Employee Management System");
				System.out.println("5  - Admin Management System");
				System.out.println("\n--------------------------------------------------");

				System.out.println("Enter the number of the system you want to access: ");
				choice = sc.nextInt();

				switch (choice) {
					case 1:
						billManagement();
						break;
					case 2:
						stockManagement();
						break;
					case 3:
						supplierManagement();
						break;
					case 4:
						employeeManagement();
						break;
					case 5:
						adminManagement();
						break;
					default:
						System.out.println("Invalid Input");
						break;
				}

				System.out.println("Do you want to access another system? (y/n)");
				subOption = sc.next().trim();
			}

		} else {
			System.out.println("Sorry you are not authorized to access this page");
		}

	}

	public void billManagement() {

		System.out.println("Bill Management System");

	}

	public void stockManagement() {

		System.out.println("Stock Management System");

	}

	public void supplierManagement() {

		System.out.println("Supplier Management System");

	}

	public void employeeManagement() {

		System.out.println("Employee Management System");

	}

	public void adminManagement() {

		System.out.println("Admin Management System");

	}

}
