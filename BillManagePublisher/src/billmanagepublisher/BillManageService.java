package billmanagepublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javadatabasecon.JDBCDemo;
import javadatabasecon.JDBCDemoInterface;

public class BillManageService implements BillManageServiceInterface {

	Scanner scan = new Scanner(System.in);

	private ArrayList<BillModel> billList = new ArrayList<BillModel>();

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet;
	private JDBCDemoInterface database;

	public BillManageService() {

		database = (JDBCDemoInterface) new JDBCDemo();
		connection = database.connection();
	}

	@Override
	public void addBill() {

		BillModel bill = new BillModel();

		System.out.println("Enter Bill ID: ");
		bill.setId(scan.nextInt());
		
		System.out.println("Enter Customer Name: ");
		scan.nextLine(); 
		bill.setCusName(scan.nextLine());

		System.out.println("Enter Phone Model: ");
		bill.setPhoneModel(scan.next());

		System.out.println("Enter Phone Brand: ");
		bill.setPhoneBrand(scan.next());

		System.out.println("Enter Phone IMEI: ");
		bill.setPhoneImei(scan.next());

		System.out.println("Enter Phone Price: ");
		bill.setPrice(scan.nextDouble());

		System.out.println("Enter Discount: ");
		bill.setDiscount(scan.nextDouble());

		double total = bill.getPrice() - bill.getDiscount();
		bill.setTotal(total);
		
		System.out.println(".....................................\n");
		System.out.println("Total: " + total);
		System.out.println(".....................................\n");
		
//		billList.add(bill);
		
		String insertBill = "INSERT INTO bills (CusName, PhoneModel, PhoneBrand, PhoneImei, price, discount, total) VALUES ('" + bill.getCusName() + "', '" + bill.getPhoneModel() + "', '" + bill.getPhoneBrand() + "', '" + bill.getPhoneImei() + "', " + bill.getPrice() + ", " + bill.getDiscount() + ", " + bill.getTotal() + ")";

		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertBill);
			System.out.println("Customer Inserted");
		}catch (SQLException exc) {
			System.out.println("Error with Interted bILL");
			System.out.println(exc.getMessage());
			
		}
		
		System.out.println("Bill Added Successfully");

	}

	@Override
	public void getAllBills() {
		// TODO Auto-generated method stub
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Loading All Bills from Database");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

		// Define the column headers
		System.out.printf("%-5s %-20s %-15s %-15s %-15s %-10s %-10s %-10s %-10s\n", "ID", "Customer Name",
				"Phone Model", "Phone Brand", "Phone IMEI", "Phone Price", "Discount", "Total", "");

		// Loop through the billList and print each row
		for (BillModel bill : billList) {
			System.out.printf("%-5d %-20s %-15s %-15s %-15s %-10.2f %-10.2f %-10.2f\n", bill.getId(), bill.getCusName(),
					bill.getPhoneModel(), bill.getPhoneBrand(), bill.getPhoneImei(), bill.getPrice(),
					bill.getDiscount(), bill.getTotal());
		}

	}

	@Override
	public void getBillByID() {
		// TODO Auto-generated method stub

		int billId;

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Gey Bill by Id from Database");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

		System.out.print("Enter Bill ID : ");
		billId = Integer.parseInt(scan.next().trim());

		System.out.printf("%-5s %-20s %-15s %-15s %-15s %-10s %-10s %-10s %-10s\n", "ID", "Customer Name",
				"Phone Model", "Phone Brand", "Phone IMEI", "Phone Price", "Discount", "Total", "");
		for (BillModel bill : billList) {
			if (bill.getId() == billId) {
				System.out.printf("%-5d %-20s %-15s %-15s %-15s %-10.2f %-10.2f %-10.2f\n", bill.getId(),
						bill.getCusName(), bill.getPhoneModel(), bill.getPhoneBrand(), bill.getPhoneImei(),
						bill.getPrice(), bill.getDiscount(), bill.getTotal());
				return;
			}
		}
		System.out.println("No customer found with the given ID");
	}

	@Override
	public void updateBillByID() {
		// TODO Auto-generated method stub

		int billId;

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Update Bill by Id from Database");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

		System.out.print("Enter Bill ID : ");
		billId = Integer.parseInt(scan.next().trim());

		for (BillModel bill : billList) {
			if (bill.getId() == billId) {
				System.out.printf("%-5s %-20s %-15s %-15s %-15s %-10s %-10s %-10s %-10s\n", "ID", "Customer Name",
						"Phone Model", "Phone Brand", "Phone IMEI", "Phone Price", "Discount", "Total", "");
				System.out.printf("%-5d %-20s %-15s %-15s %-15s %-10.2f %-10.2f %-10.2f\n", bill.getId(),
						bill.getCusName(), bill.getPhoneModel(), bill.getPhoneBrand(), bill.getPhoneImei(),
						bill.getPrice(), bill.getDiscount(), bill.getTotal());
				System.out.println("\n");
//				
//				System.out.println("Enter New Bill ID: ");
//				bill.setId(scan.nextInt());

				System.out.println("Enter New Customer Name: ");
				scan.nextLine();
				bill.setCusName(scan.nextLine());

				System.out.println("Enter New Phone Model: ");
				bill.setPhoneModel(scan.next());

				System.out.println("Enter New Phone Brand: ");
				bill.setPhoneBrand(scan.next());

				System.out.println("Enter New Phone IMEI: ");
				bill.setPhoneImei(scan.next());

				System.out.println("Enter New Phone Price: ");
				bill.setPrice(scan.nextDouble());

				System.out.println("Enter New Discount: ");
				bill.setDiscount(scan.nextDouble());

				double total = bill.getPrice() - bill.getDiscount();
				bill.setTotal(total);

				System.out.println(".....................................\n");
				System.out.println("New Total: " + total);
				System.out.println(".....................................\n");

				System.out.println("::::::::::::::::::: Updated Details :::::::::::::::\n");
				System.out.printf("%-5s %-20s %-15s %-15s %-15s %-10s %-10s %-10s %-10s\n", "ID", "Customer Name",
						"Phone Model", "Phone Brand", "Phone IMEI", "Phone Price", "Discount", "Total", "");
				System.out.printf("%-5d %-20s %-15s %-15s %-15s %-10.2f %-10.2f %-10.2f\n", bill.getId(),
						bill.getCusName(), bill.getPhoneModel(), bill.getPhoneBrand(), bill.getPhoneImei(),
						bill.getPrice(), bill.getDiscount(), bill.getTotal());
				System.out.println("\n");

				return;
			}
		}
		System.out.println("No Bill found with the given ID");

	}

	@Override
	public void deleteBillByID() {
		// TODO Auto-generated method stub

		int billId;

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Delete Bill by Id from Database");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

		System.out.print("Enter Bill ID : ");
		billId = Integer.parseInt(scan.next().trim());

		for (BillModel bill : billList) {
			if (bill.getId() == billId) {
				billList.remove(bill);
				System.out.println("Bill Deleted Successfully");
				return;
			}
		}
	}

}
