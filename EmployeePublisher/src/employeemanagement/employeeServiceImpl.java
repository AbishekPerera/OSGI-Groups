package employeemanagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javadatabasecon.JDBCDemo;
import javadatabasecon.JDBCDemoInterface;

public class employeeServiceImpl implements employeePublisher {

	Scanner sc = new Scanner(System.in);
	private ArrayList<Employee> employeeList = new ArrayList<Employee>();

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet;
	private JDBCDemoInterface database;

	public employeeServiceImpl() {

		database = (JDBCDemoInterface) new JDBCDemo();
		connection = database.connection();
	}

	@Override
	public void addEmployee() {
		System.out.println("----------------------------------------------------------");
		System.out.println("                    Add Employee info ");
		System.out.println("----------------------------------------------------------");

		Employee employee = new Employee();

		System.out.print("Enter Employee ID      : ");
		employee.setEmpId(sc.nextInt());

		System.out.print("Enter Employee Name    : ");
		sc.nextLine();
		employee.setEmpName(sc.nextLine());

		System.out.print("Enter Employee Email   : ");
		employee.setEmail(sc.next());

		System.out.print("Enter Employee Address : ");
		sc.nextLine();
		employee.setAddress(sc.nextLine());

		System.out.print("Enter Employee Telephone: ");
		employee.setTelephone(sc.next());

		System.out.print("Enter Employee Type    : ");
		employee.setEmpType(sc.next());

		String insertQuery = "INSERT INTO employee (empName, email, address, telephone, empType) VALUES ('"
				+ employee.getEmpName() + "', '" + employee.getEmail() + "', '" + employee.getAddress() + "', '"
				+ employee.getTelephone() + "', '" + employee.getEmpType() + "')";

		try {

			statement = connection.createStatement();
			statement.executeUpdate(insertQuery);

		} catch (SQLException exc) {
			System.out.println("Error with Interted Employee");
			System.out.println(exc.getMessage());

		}

		System.out.println("\n");
		System.out.println("----------------------------------------------------------");
		System.out.println("             Employee Added Successfully");
		System.out.println("----------------------------------------------------------");

	}

	@Override
	public void getAllEmployees() {

		ArrayList<Employee> employeeList = new ArrayList<Employee>();

		System.out.println("                                            All Employee Info\n");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-20s%-20s%-20s%-40s%-20s%-20s\n", "Employee ID", "Employee Name", "Employee Email",
				"Employee Address", "Employee Telephone", "Employee Type");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");

		try {

			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM employee");

			while (resultSet.next()) {

				Employee employee = new Employee();

				employee.setEmpId(resultSet.getInt("empId"));
				employee.setEmpName(resultSet.getString("empName"));
				employee.setEmail(resultSet.getString("email"));
				employee.setAddress(resultSet.getString("address"));
				employee.setTelephone(resultSet.getString("telephone"));
				employee.setEmpType(resultSet.getString("empType"));

				employeeList.add(employee);
			}

		} catch (SQLException exc) {
			System.out.println("Error with Interted Employees");
			System.out.println(exc.getMessage());

		}

		for (Employee employee : employeeList) {

			System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", employee.getEmpId(),
					employee.getEmpName(),
					employee.getEmail(), employee.getAddress(), employee.getTelephone(),
					employee.getEmpType());

		}

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
	}

	@Override
	public void updateEmployee() {
		System.out.println("----------------------------------------------------------");
		System.out.println("                 Update Employee Info");
		System.out.println("----------------------------------------------------------");

		int empId;
		System.out.print("Enter Employee ID : ");
		empId = sc.nextInt();

		boolean found = false;
		for (Employee employee : employeeList) {
			if (employee.getEmpId() == empId) {
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("                                      Existing Employee Details");
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("Employee ID       : " + employee.getEmpId());
				System.out.println("Employee Name     : " + employee.getEmpName());
				System.out.println("Employee Email    : " + employee.getEmail());
				System.out.println("Employee Address  : " + employee.getAddress());
				System.out.println("Employee telNumber: " + employee.getTelephone());
				System.out.println("Employee Type     : " + employee.getEmpType());
				found = true;
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------");

				System.out.println("\n");
				System.out.println("Which field do you want to update?");
				System.out.println("1. Employee Name");
				System.out.println("2. Employee Email");
				System.out.println("3. Employee Address");
				System.out.println("4. Employee Telephone");
				System.out.println("5. Employee Type");
				int choice = sc.nextInt();

				switch (choice) {
					case 1:
						System.out.print("Enter Employee Name : ");
						String empName = sc.next();
						employee.setEmpName(empName);
						break;
					case 2:
						System.out.print("Enter Employee Email : ");
						String email = sc.next();
						employee.setEmail(email);
						break;
					case 3:
						System.out.print("Enter Employee Address : ");
						String address = sc.next();
						employee.setAddress(address);
						break;
					case 4:
						System.out.print("Enter Employee Telephone : ");
						String telephone = sc.next();
						employee.setTelephone(telephone);
						break;
					case 5:
						System.out.print("Enter Employee Type : ");
						String empType = sc.next();
						employee.setEmpType(empType);
						break;
					default:
						System.out.print("Invalid choice");
				}
				System.out.println("\n");
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("                                         Updated Employee Details");
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("Employee ID       : " + employee.getEmpId());
				System.out.println("Employee Name     : " + employee.getEmpName());
				System.out.println("Employee Email    : " + employee.getEmail());
				System.out.println("Employee Address  : " + employee.getAddress());
				System.out.println("Employee Telephone: " + employee.getTelephone());
				System.out.println("Employee Type     : " + employee.getEmpType());
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------");
				break;
			}
		}

		if (!found) {
			System.out.println("----------------------------------------------------------");
			System.out.println("There exist NO employee with the Employee ID: " + empId);
			System.out.println("----------------------------------------------------------");
		}

	}

	@Override
	public void deleteEmployee() {
		System.out.println("----------------------------------------------------------");
		System.out.println("                  Delete an Employee");
		System.out.println("----------------------------------------------------------");

		int empId;
		System.out.print("Enter Employee ID : ");
		empId = sc.nextInt();

		String deleteQuery = "DELETE FROM employee WHERE empId = '" + empId + "'";

		try {

			statement = connection.createStatement();
			int rowsDeleted = statement.executeUpdate(deleteQuery);

			if (rowsDeleted > 0) {
				System.out.println("----------------------------------------------------------------");
				System.out.println("  Employee with the Employee ID: " + empId + " Deleted Successfully");
				System.out.println("----------------------------------------------------------------");
				System.out.println("");
			} else {
				System.out.println("----------------------------------------------------------");
				System.out.println("There exist NO employee with the Employee ID: " + empId);
				System.out.println("----------------------------------------------------------");
			}

		} catch (SQLException exc) {

			System.out.println("Error deleting Employee with ID " + empId);
			System.out.println(exc.getMessage());
		}

		System.out.println("\n");

	}

	@Override

	public void getEmployeeById() {
		System.out.println("----------------------------------------------------------");
		System.out.println("                    Employee Info");
		System.out.println("----------------------------------------------------------");

		int empId;
		System.out.print("Enter Employee ID : ");
		empId = sc.nextInt();

		String query = "SELECT * FROM employee WHERE empId = '" + empId + "'";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

			if (resultSet.next()) {

				Employee employee = new Employee();
				employee.setEmpId(resultSet.getInt("empId"));
				employee.setEmpName(resultSet.getString("empName"));
				employee.setEmail(resultSet.getString("email"));
				employee.setAddress(resultSet.getString("address"));
				employee.setTelephone(resultSet.getString("telephone"));
				employee.setEmpType(resultSet.getString("empType"));

				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("                                      Existing Employee Details");
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("Employee ID       : " + employee.getEmpId());
				System.out.println("Employee Name     : " + employee.getEmpName());
				System.out.println("Employee Email    : " + employee.getEmail());
				System.out.println("Employee Address  : " + employee.getAddress());
				System.out.println("Employee telNumber: " + employee.getTelephone());
				System.out.println("Employee Type     : " + employee.getEmpType());
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------");

				System.out.println("Do you want the tabular view? (y/n)");
				String choice = sc.next();

				if (choice.equalsIgnoreCase("y")) {
					System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Employee Name", "Employee	Email",
							"Employee Address", "Employee Telephone", "Employee Type");

					System.out.println(
							"------------------------------------------------------------------------------------------------------------------------------");

					System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", employee.getEmpName(), employee.getEmail(),
							employee.getAddress(), employee.getTelephone(), employee.getEmpType());

				}

			} else {
				System.out.println("----------------------------------------------------------");
				System.out.println("There exist NO employee with the Employee ID: " + empId);
				System.out.println("----------------------------------------------------------");
			}
		} catch (SQLException exc) {
			System.out.println("Error retrieving Employee from database");
			System.out.println(exc.getMessage());
		}

	}

}
