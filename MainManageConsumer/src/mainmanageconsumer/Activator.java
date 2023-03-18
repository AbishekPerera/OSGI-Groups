package mainmanageconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import authmanagepublisher.AuthManageServiceInterface;
import billmanagepublisher.BillManageServiceInterface;
import employeemanagement.employeePublisher;
import stockpublisher.IStockService;

public class Activator implements BundleActivator {

	ServiceReference AuthServiceReference;
	ServiceReference BillServiceReference;
	ServiceReference EmpServiceReference;
	ServiceReference StockServiceReference;


	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Start Subscriber Service");

		AuthServiceReference = bundleContext.getServiceReference(AuthManageServiceInterface.class.getName());
		@SuppressWarnings("unchecked")
		AuthManageServiceInterface user = (AuthManageServiceInterface) bundleContext.getService(AuthServiceReference);

		BillServiceReference = bundleContext.getServiceReference(BillManageServiceInterface.class.getName());
		@SuppressWarnings("unchecked")
		BillManageServiceInterface bill = (BillManageServiceInterface) bundleContext.getService(BillServiceReference);
		
		EmpServiceReference = bundleContext.getServiceReference(employeePublisher.class.getName());
		@SuppressWarnings("unchecked")
		employeePublisher emp = (employeePublisher) bundleContext.getService(EmpServiceReference);

		StockServiceReference = bundleContext.getServiceReference(IStockService.class.getName());
		@SuppressWarnings("unchecked")
		IStockService stock = (IStockService) bundleContext.getService(StockServiceReference);

		MainIn(user,bill,emp,stock);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Good Bye!");
		bundleContext.ungetService(AuthServiceReference);
	}

	private void MainIn(AuthManageServiceInterface user, BillManageServiceInterface bill, employeePublisher emp, IStockService stock) {

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
				System.out.println("----------Home Page---------- \n");
				System.out.println("1  - Bill Management System");
				System.out.println("2  - Stcock Management System");
				System.out.println("3  - Supplier Management System");
				System.out.println("4  - Employee Management System");
				System.out.println("5  - Admin Management System");
				System.out.println("\n --------------------------------------------------");

				System.out.println("Enter the number of the system you want to access: ");
				choice = sc.nextInt();

				switch (choice) {
					case 1:
						billManagement(bill);
						break;
					case 2:
						stockManagement(stock);
						break;
					case 3:
						supplierManagement();
						break;
					case 4:
						employeeManagement(emp);
						break;
					case 5:
						adminManagement(user);
						break;
					default:
						System.out.println("Invalid Input");
						break;
				}

				System.out.println("\n Do you want to access another system? (y/n)");
				subOption = sc.next().trim();
			}

		} else {
			System.out.println("Sorry you are not authorized to access this page");
		}

	}

	public void billManagement(BillManageServiceInterface bill) {

		int option;
		String subOption = "y";

		Scanner sc = new Scanner(System.in);

		System.out.println("\n");

		while (subOption.equalsIgnoreCase("y")) {
			System.out.println("----------Bill Management System----------\n");
			System.out.println("1  - Add Bill");
			System.out.println("2  - Update Bill");
			System.out.println("3  - View All Bills");
			System.out.println("4  - Search Bill by ID");
			System.out.println("5  - Delete Bill");
			System.out.println("\n--------------------------------------------------");

			System.out.println("Enter the number of the operation you want to perform: ");
			option = sc.nextInt();

			switch (option) {
				case 1:
					bill.addBill();
					break;
				case 2:
					bill.updateBillByID();
					break;
				case 3:
					bill.getAllBills();
					break;
				case 4:
					bill.getBillByID();
					break;
				case 5:
					bill.deleteBillByID();
					break;
				default:
					System.out.println("Invalid Input");
					break;
			}

			System.out.println("\n Do you want to perform another operation? (y/n)");
			subOption = sc.next().trim();
		}

	}

	public void stockManagement(IStockService stock) {

		int option;
		String subOption = "y";

		Scanner sc = new Scanner(System.in);

		System.out.println("\n");

		while (subOption.equalsIgnoreCase("y")) {
			System.out.println("----------Stock Management System----------\n");
			System.out.println("1  - Add Stock");
			System.out.println("2  - Update Stock");
			System.out.println("3  - View All Stocks");
			System.out.println("4  - Search Stock by ID");
			System.out.println("5  - Delete Stock");
			System.out.println("\n--------------------------------------------------");

			System.out.println("Enter the number of the operation you want to perform: ");
			option = sc.nextInt();

			switch (option) {
				case 1:
					stock.addStock();
					break;
				case 2:
					stock.updateStockById();
					break;
				case 3:
					stock.getAllStock();
					break;
				case 4:
					stock.getStockById();
					break;
				case 5:
					stock.deleteStockById();
					break;
				default:
					System.out.println("Invalid Input");
					break;
			}

			System.out.println("\n Do you want to perform another operation? (y/n)");
			subOption = sc.next().trim();
		}



	}

	public void supplierManagement() {

		System.out.println("Supplier Management System");

	}

	public void employeeManagement(employeePublisher emp) {

		int option;
		String subOption = "y";

		Scanner sc = new Scanner(System.in);

		System.out.println("\n");

		while (subOption.equalsIgnoreCase("y")) {
			System.out.println("----------Employee Management System----------\n");
			System.out.println("1  - Add Employee");
			System.out.println("2  - Update Employee");
			System.out.println("3  - View All Employees");
			System.out.println("4  - Search Employee by ID");
			System.out.println("5  - Delete Employee");
			System.out.println("\n--------------------------------------------------");

			System.out.println("Enter the number of the operation you want to perform: ");
			option = sc.nextInt();

			switch (option) {
				case 1:
					emp.addEmployee();
					break;
				case 2:
					emp.updateEmployee();
					break;
				case 3:
					emp.getAllEmployees();
					break;
				case 4:
					emp.getEmployeeById();
					break;
				case 5:
					emp.deleteEmployee();
					break;
				default:
					System.out.println("Invalid Input");
					break;
			}

			System.out.println("\n Do you want to perform another operation? (y/n)");
			subOption = sc.next().trim();
		}

	}

	public void adminManagement(AuthManageServiceInterface user) {

		int option;
		String subOption = "y";

		Scanner sc = new Scanner(System.in);

		System.out.println("\n");

		while (subOption.equalsIgnoreCase("y")) {
			System.out.println("----------Admin Management System----------\n");
			System.out.println("1  - Add Admin");
			System.out.println("2  - Update Admin");
			System.out.println("3  - View All Admins");
			System.out.println("4  - Search Admin by ID");
			System.out.println("5  - Delete Admin");
			System.out.println("\n--------------------------------------------------");

			System.out.println("Enter the number of the operation you want to perform: ");
			option = sc.nextInt();

			switch (option) {
				case 1:
					user.addUser();
					break;
				case 2:
					user.updateUserByID();
					break;
				case 3:
					user.getAllUsers();
					break;
				case 4:
					user.getUserByID();
					break;
				case 5:
					user.deleteUserByID();
					break;
				default:
					System.out.println("Invalid Input");
					break;
			}

			System.out.println("\n Do you want to perform another operation? (y/n)");
			subOption = sc.next().trim();
		}
		

	}

}
