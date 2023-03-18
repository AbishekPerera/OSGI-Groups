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
		Supplier supplier=new Supplier();
		
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

		String sql = "INSERT INTO supplier (supName, telephone, companyName, address) VALUES ('"+supplier.getSupName()+"', '"+supplier.getTelephone()+"', '"+supplier.getCompanyName()+"', '"+supplier.getAddress()+"')";
	
		try{
			statement = connection.createStatement();
			statement.executeUpdate(sql);

			System.out.println("supplier Added Successfully");

		}catch (SQLException exc) {
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

	    System.out.printf("%-5s %-20s %-15s %-15s %-15s\n", "ID", "Supplier Name", "Mobile Number", "Company Name", "Address");

	    for (Supplier supplier : supplierList) {
	        if (supplier.getId() == supplierId) {
	            System.out.printf("%-5d %-20s %-15s %-15s %-15s\n", supplier.getId(), supplier.getSupName(), supplier.getTelephone(), supplier.getCompanyName(), supplier.getAddress());
	            return;
	        }
	    }
	    System.out.println("No supplier found with the given ID");
	}

	@Override
	public void updateSupplier() {
	    int supplierId;
	    String name, number, company, address;

	    System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
	    System.out.println("Update Supplier");
	    System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

	    System.out.print("Enter Supplier ID : ");
	    supplierId = sc.nextInt();
	    System.out.println();

	    for (Supplier supplier : supplierList) {
	        if (supplier.getId() == supplierId) {
	            System.out.printf("%-5s %-20s %-15s %-15s %-15s\n", "ID", "Supplier Name", "Mobile Number", "Company Name", "Address");
	            System.out.printf("%-5d %-20s %-15s %-15s %-15s\n", supplier.getId(), supplier.getSupName(), supplier.getTelephone(), supplier.getCompanyName(), supplier.getAddress());
	            System.out.println();

	            System.out.println("Supplier ID: " + supplierId);
	            supplier.setId(supplierId);
	            
	            System.out.println("Supplier Name: " + supplier.getSupName());
	            
	            System.out.println("Do you need to edit Supplier Name (Y/N) : ");
	            name = sc.next();
	            
	            if(name.equals("Y")) {
	                System.out.println("Enter New Supplier Name: ");
	                supplier.setSupName(sc.next());
	            }
	            
	            System.out.println("Do you need to edit Mobile Number (Y/N) : ");
	            number = sc.next();
	            
	            if(number.equals("Y")) {
	                System.out.println("Enter New Mobile Number: ");
	                supplier.setTelephone(sc.next());
	            }
	            
	            System.out.println("Do you need to edit Company Name (Y/N): ");
	            company = sc.next();
	            
	            if(company.equals("Y")) {
	                System.out.println("Enter New Company Name: ");
	                supplier.setCompanyName(sc.next());
	            }

	            System.out.println("Do you need to edit Address (Y/N): ");
	            address = sc.next();
	            
	            if(address.equals("Y")) {
	                System.out.println("Enter New Address: ");
	                supplier.setAddress(sc.next());
	            }
	            
	            System.out.println("Supplier updated successfully");
	            return;
	        }
	    }
	    System.out.println("No supplier found with the given ID");
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

		for(Supplier supplier : supplierList){
			if(supplier.getId() == supplierId){
				supplierList.remove(supplier);
				System.out.println("supplier Deleted Successfully");
				return;
			}
		}
	}

	@Override
	public void getAllSuppliers() {
	    System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
	    System.out.println("Loading All Suppliers from Database");
	    System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

	    // Define the column headers
	    System.out.printf("%-5s %-20s %-15s %-15s %-15s\n", "ID", "Supplier Name", "Mobile Number", "Company Name", "Address");

	    // Loop through the supplierList and print each row
	    for (Supplier supplier : supplierList) {
	        System.out.printf("%-5d %-20s %-15s %-15s %-15s\n", supplier.getId(), supplier.getSupName(), supplier.getTelephone(), supplier.getCompanyName(), supplier.getAddress());
	    }
	}


}
