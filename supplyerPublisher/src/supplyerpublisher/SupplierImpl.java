package supplyerpublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javadatabasecon.JDBCDemo;
import javadatabasecon.JDBCDemoInterface;

public class SupplierImpl implements ISupplier {

	Scanner sc = new Scanner(System.in);

	private ArrayList<Supplier> supplierList = new ArrayList<Supplier>();

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet;
	private JDBCDemoInterface database;

	public SupplierImpl() {

		database = (JDBCDemoInterface) new JDBCDemo();
		connection = database.connection();
	}

	@Override
	public void addSupplier() {
		// TODO Auto-generated method stub
		Supplier supplier = new Supplier();

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Insert Supplier");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

		System.out.print("Enter supplier ID: ");
		supplier.setId(sc.nextInt());
		System.out.println();

		System.out.print("Enter Supplier Name: ");
		sc.nextLine();
		supplier.setSupName(sc.nextLine());
		System.out.println();

		System.out.print("Enter Mobile Number: ");
		supplier.setTelephone(sc.next());
		System.out.println();

		System.out.print("Enter Company Name: ");
		sc.nextLine();
		supplier.setCompanyName(sc.nextLine());
		System.out.println();

		System.out.print("Enter Address: ");
		supplier.setAddress(sc.nextLine());
		System.out.println();

		String sql = "INSERT INTO supplier (supName, telephone, companyName, address) VALUES ('" + supplier.getSupName()
				+ "', '" + supplier.getTelephone() + "', '" + supplier.getCompanyName() + "', '" + supplier.getAddress()
				+ "')";

		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);

			System.out.println("supplier Added Successfully");

		} catch (SQLException exc) {
			System.out.println("Error with Interting supplier");
			System.out.println(exc.getMessage());

		}

	}

	@Override
	public void getSupplierByID() {
		int supplierId;

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Get Supplier by Id ");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

		System.out.print("Enter Supplier ID : ");
		supplierId = sc.nextInt();

		System.out.println();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM supplier WHERE id = " + supplierId);

			if (resultSet.next()) {

				Supplier supplier = new Supplier();

				supplier.setId(resultSet.getInt("id"));
				supplier.setSupName(resultSet.getString("supName"));
				supplier.setTelephone(resultSet.getString("telephone"));
				supplier.setCompanyName(resultSet.getString("companyName"));
				supplier.setAddress(resultSet.getString("address"));

				System.out.printf("%-5s %-20s %-15s %-15s %-15s\n", "ID", "Supplier Name", "Mobile Number",
						"Company Name", "Address");
				System.out.printf("%-5d %-20s %-15s %-15s %-15s\n", supplier.getId(), supplier.getSupName(),
						supplier.getTelephone(), supplier.getCompanyName(), supplier.getAddress());

			} else {
				System.out.println("No supplier found with the given ID");

			}

		} catch (SQLException e) {
			System.out.println("Error with Interting supplier");
			e.printStackTrace();
		}

	}

	@Override
	public void updateSupplier() {
		int supplierId;
		String name, number, company, address;
		String sql;

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Update Supplier");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

		System.out.print("Enter Supplier ID : ");
		supplierId = sc.nextInt();
		System.out.println();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM supplier WHERE id = " + supplierId);

			if (resultSet.next()) {

				Supplier supplier = new Supplier();

				supplier.setId(resultSet.getInt("id"));
				supplier.setSupName(resultSet.getString("supName"));
				supplier.setTelephone(resultSet.getString("telephone"));
				supplier.setCompanyName(resultSet.getString("companyName"));
				supplier.setAddress(resultSet.getString("address"));

				System.out.printf("%-5s %-20s %-15s %-15s %-15s\n", "ID", "Supplier Name", "Mobile Number",
						"Company Name", "Address");
				System.out.printf("%-5d %-20s %-15s %-15s %-15s \n", supplier.getId(), supplier.getSupName(),
						supplier.getTelephone(), supplier.getCompanyName(), supplier.getAddress());
				System.out.println();

				System.out.println("Which field you want to update?");

				System.out.println("1. Supplier Name");
				System.out.println("2. Mobile Number");
				System.out.println("3. Company Name");
				System.out.println("4. Address");

				System.out.print("Enter your choice : ");
				int choice = sc.nextInt();

				switch (choice) {
				case 1:
					System.out.print("Enter new Supplier Name : ");
					sc.nextLine();
					name = sc.nextLine();
					sql = "UPDATE supplier SET supName = '" + name + "' WHERE id = " + supplierId;
					break;

				case 2:
					System.out.print("Enter new Mobile Number : ");
					number = sc.next();
					sql = "UPDATE supplier SET telephone = '" + number + "' WHERE id = " + supplierId;
					break;

				case 3:
					System.out.print("Enter new Company Name : ");
					sc.nextLine();
					company = sc.nextLine();
					sql = "UPDATE supplier SET companyName = '" + company + "' WHERE id = " + supplierId;
					break;

				case 4:
					System.out.print("Enter new Address : ");
					sc.nextLine();
					address = sc.nextLine();
					sql = "UPDATE supplier SET address = '" + address + "' WHERE id = " + supplierId;
					break;

				default:
					System.out.println("Invalid choice");
					return;
				}

				try {
					statement = connection.createStatement();
					statement.executeUpdate(sql);
					System.out.println("Supplier Updated Successfully");
				}catch(SQLException exc) {
					System.out.println("Error with Updating supplier");
					System.out.println(exc.getMessage());
				}
				

			} else {
				System.out.println("No supplier found with the given ID");

			}

		} catch (SQLException exc) {
			System.out.println("Error with Interting supplier");
			System.out.println(exc.getMessage());

		}

	}

	@Override
	public void deleteSupplier() {
		// TODO Auto-generated method stub
		int supplierId;

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Delete supplier");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

		System.out.print("Enter supplier ID : ");
		supplierId = sc.nextInt();

		System.out.println();

		try {
			statement = connection.createStatement();
			int result = statement.executeUpdate("DELETE FROM supplier WHERE id = " + supplierId);

			if (result > 0) {
				System.out.println("supplier Deleted Successfully");

			} else {
				System.out.println("No supplier found with the given ID");

			}

		} catch (SQLException exc) {
			System.out.println("Error with Deleting supplier");
			System.out.println(exc.getMessage());

		}

	}

	@Override
	public void getAllSuppliers() {

		ArrayList<Supplier> supplierList = new ArrayList<Supplier>();

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Loading All Suppliers from Database");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM supplier");

			while (resultSet.next()) {
				Supplier supplier = new Supplier();

				supplier.setId(resultSet.getInt("id"));
				supplier.setSupName(resultSet.getString("supName"));
				supplier.setTelephone(resultSet.getString("telephone"));
				supplier.setCompanyName(resultSet.getString("companyName"));
				supplier.setAddress(resultSet.getString("address"));

				supplierList.add(supplier);
			}

		} catch (SQLException e) {
			System.out.println("Error with Interting supplier");
			e.printStackTrace();
		}

		// Define the column headers
		System.out.printf("%-5s %-20s %-15s %-15s %-15s\n", "ID", "Supplier Name", "Mobile Number", "Company Name",
				"Address");

		// Loop through the supplierList and print each row
		for (Supplier supplier : supplierList) {
			System.out.printf("%-5d %-20s %-15s %-15s %-15s\n", supplier.getId(), supplier.getSupName(),
					supplier.getTelephone(), supplier.getCompanyName(), supplier.getAddress());
		}
	}

}
