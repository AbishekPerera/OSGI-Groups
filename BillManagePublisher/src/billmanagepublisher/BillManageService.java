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

	// private ArrayList<BillModel> billList = new ArrayList<BillModel>();

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

		// System.out.println("Enter Bill ID: ");
		// bill.setId(scan.nextInt());

		System.out.println("Enter Customer Name: ");
		// scan.nextLine();
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

		// billList.add(bill);

		String insertBill = "INSERT INTO bills (CusName, PhoneModel, PhoneBrand, PhoneImei, price, discount, total) VALUES ('"
				+ bill.getCusName() + "', '" + bill.getPhoneModel() + "', '" + bill.getPhoneBrand() + "', '"
				+ bill.getPhoneImei() + "', " + bill.getPrice() + ", " + bill.getDiscount() + ", " + bill.getTotal()
				+ ")";

		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertBill);
			System.out.println("Bill Inserted");

		} catch (SQLException exc) {
			System.out.println("Error with Interted bILL");
			System.out.println(exc.getMessage());

		}

	}

	@Override
	public void getAllBills() {

		ArrayList<BillModel> billList = new ArrayList<BillModel>();

		// TODO Auto-generated method stub
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Loading All Bills from Database");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

		try {

			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM bills");

			while (resultSet.next()) {

				BillModel bill = new BillModel();

				bill.setId(resultSet.getInt("id"));
				bill.setCusName(resultSet.getString("CusName"));
				bill.setPhoneModel(resultSet.getString("PhoneModel"));
				bill.setPhoneBrand(resultSet.getString("PhoneBrand"));
				bill.setPhoneImei(resultSet.getString("PhoneImei"));
				bill.setPrice(resultSet.getDouble("price"));
				bill.setDiscount(resultSet.getDouble("discount"));
				bill.setTotal(resultSet.getDouble("total"));

				billList.add(bill);
			}

		} catch (SQLException exc) {
			System.out.println("Error with Interted bILL");
			System.out.println(exc.getMessage());

		}

		// Define the column headers
		System.out.printf("%-5s %-20s %-15s %-15s %-15s %-10s %-10s %-10s %-10s\n", "ID", "Customer Name",
				"Phone Model", "Phone Brand", "Phone IMEI", "Phone Price", "Discount", "Total", "");

		// Loop through the billList and print each row
		for (BillModel bill : billList) {
			System.out.printf("%-5d %-20s %-15s %-15s %-15s %-10.2f %-10.2f %-10.2f\n", bill.getId(), bill.getCusName(),
					bill.getPhoneModel(), bill.getPhoneBrand(), bill.getPhoneImei(), bill.getPrice(),
					bill.getDiscount(), bill.getTotal());
		}

		System.out.println("\n");

	}

	@Override
	public void getBillByID() {
		int billId;

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Get Bill by ID from Database");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

		System.out.print("Enter Bill ID: ");
		billId = Integer.parseInt(scan.next().trim());

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM bills WHERE id = " + billId);

			if (resultSet.next()) {
				BillModel bill = new BillModel();

				bill.setId(resultSet.getInt("id"));
				bill.setCusName(resultSet.getString("CusName"));
				bill.setPhoneModel(resultSet.getString("PhoneModel"));
				bill.setPhoneBrand(resultSet.getString("PhoneBrand"));
				bill.setPhoneImei(resultSet.getString("PhoneImei"));
				bill.setPrice(resultSet.getDouble("price"));
				bill.setDiscount(resultSet.getDouble("discount"));
				bill.setTotal(resultSet.getDouble("total"));

				System.out.printf("%-5s %-20s %-15s %-15s %-15s %-10s %-10s %-10s %-10s\n", "ID", "Customer Name",
						"Phone Model", "Phone Brand", "Phone IMEI", "Phone Price", "Discount", "Total", "");
				System.out.printf("%-5d %-20s %-15s %-15s %-15s %-10.2f %-10.2f %-10.2f\n", bill.getId(),
						bill.getCusName(), bill.getPhoneModel(), bill.getPhoneBrand(), bill.getPhoneImei(),
						bill.getPrice(), bill.getDiscount(), bill.getTotal());
			} else {
				System.out.println("No bill found with the given ID");
			}
		} catch (SQLException exc) {
			System.out.println("Error retrieving bill from database");
			System.out.println(exc.getMessage());
		}
	}

	@Override
	public void updateBillByID() {
		// TODO Auto-generated method stub

		int billId;
		String sql="";
		BillModel bill = new BillModel();

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Update Bill by Id from Database");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

		System.out.print("Enter Bill ID : ");
		billId = Integer.parseInt(scan.next().trim());

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM bills WHERE id = " + billId);

			if (resultSet.next()) {

				bill.setId(resultSet.getInt("id"));
				bill.setCusName(resultSet.getString("CusName"));
				bill.setPhoneModel(resultSet.getString("PhoneModel"));
				bill.setPhoneBrand(resultSet.getString("PhoneBrand"));
				bill.setPhoneImei(resultSet.getString("PhoneImei"));
				bill.setPrice(resultSet.getDouble("price"));
				bill.setDiscount(resultSet.getDouble("discount"));
				bill.setTotal(resultSet.getDouble("total"));

				System.out.printf("%-5s %-20s %-15s %-15s %-15s %-10s %-10s %-10s %-10s\n", "ID", "Customer Name",
						"Phone Model", "Phone Brand", "Phone IMEI", "Phone Price", "Discount", "Total", "");
				System.out.printf("%-5d %-20s %-15s %-15s %-15s %-10.2f %-10.2f %-10.2f\n", bill.getId(),
						bill.getCusName(), bill.getPhoneModel(), bill.getPhoneBrand(), bill.getPhoneImei(),
						bill.getPrice(), bill.getDiscount(), bill.getTotal());

				System.out.println("..........................\n");
				System.out.println("wich field you want to update ?\n");
				System.out.println("1. Customer Name");
				System.out.println("2. Phone Model");
				System.out.println("3. Phone Brand");
				System.out.println("4. Phone IMEI");
				System.out.println("5. Phone Price & Discount");
				System.out.println("\n.......................\n");

				System.out.print("Enter your choice : ");
				int choice = Integer.parseInt(scan.next().trim());

				switch (choice) {
				case 1:
					System.out.println("Enter New Customer Name: ");
					scan.nextLine();
					bill.setCusName(scan.nextLine());
					sql = "UPDATE bills SET CusName='" + bill.getCusName() + "' WHERE id = " + billId;
					break;
				case 2:
					System.out.println("Enter new Phone Model : ");
					bill.setPhoneModel(scan.next().trim());
					sql = "UPDATE bills SET PhoneModel='" + bill.getPhoneModel() + "' WHERE id = " + billId;
					break;
				case 3:
					System.out.println("Enter new Phone Brand : ");
					bill.setPhoneBrand(scan.next().trim());
					sql = "UPDATE bills SET PhoneBrand='" + bill.getPhoneBrand() + "' WHERE id = " + billId;
					break;
				case 4:
					System.out.println("Enter new Phone IMEI : ");
					bill.setPhoneImei(scan.next().trim());
					sql = "UPDATE bills SET PhoneImei='" + bill.getPhoneImei() + "' WHERE id = " + billId;
					break;
				case 5:
					System.out.println("Enter new Phone Price : ");
					bill.setPrice(Double.parseDouble(scan.next().trim()));
					
					System.out.println("Enter new Discount : ");
					bill.setDiscount(Double.parseDouble(scan.next().trim()));

					double total = bill.getPrice() - bill.getDiscount();

					sql = "UPDATE bills SET price=" + bill.getPrice() + ", discount=" + bill.getDiscount() + ", total="
							+ total + " WHERE id = " + billId;
					break;

				default:
					System.out.println("Invalid choice");

				}

				try{
					statement = connection.createStatement();
					statement.executeUpdate(sql);
					System.out.println("Bill updated successfully");

				}catch (SQLException exc) {
					System.out.println("Error updating bill from database");
					System.out.println(exc.getMessage());
				}

			} else {
				System.out.println("No bill found with the given ID");
				System.out.println("\n");
			}

		} catch (SQLException exc) {
			System.out.println("Error retrieving bill from database");
			System.out.println(exc.getMessage());
			System.out.println("\n");

		}

		System.out.println("\n");
	}

	@Override
	public void deleteBillByID() {

		int billId;

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Delete Bill by Id from Database");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

		System.out.print("Enter Bill ID : ");
		billId = Integer.parseInt(scan.next().trim());

		String deleteBill = "DELETE FROM bills WHERE id = " + billId;

		try {
			statement = connection.createStatement();
			int rowsDeleted = statement.executeUpdate(deleteBill);

			if (rowsDeleted == 0) {

				System.out.println("No bill found with ID " + billId);

			} else {

				System.out.println(rowsDeleted + " bill(s) deleted successfully");

			}
		} catch (SQLException exc) {

			System.out.println("Error deleting bill with ID " + billId);
			System.out.println(exc.getMessage());
		}
		System.out.println("\n");
	}

}
