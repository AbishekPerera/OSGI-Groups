package authmanagepublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javadatabasecon.JDBCDemo;
import javadatabasecon.JDBCDemoInterface;

public class AuthManageService implements AuthManageServiceInterface {

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet;
	private JDBCDemoInterface database;

	public AuthManageService() {
		database = (JDBCDemoInterface) new JDBCDemo();
		connection = database.connection();
	}

	@Override
	public void addUser() {

		Scanner scan = new Scanner(System.in);
		UserModel user = new UserModel();

		System.out.println("Enter User ID: ");
		user.setId(scan.nextInt());

		System.out.println("Enter Username: ");
		scan.nextLine();
		user.setUsername(scan.nextLine());

		System.out.println("Enter Email: ");
		user.setEmail(scan.next());

		System.out.println("Enter Password: ");
		user.setPassword(scan.next());

		System.out.println("Enter Phone: ");
		user.setPhone(scan.next());

		String insert = "INSERT INTO users (username, email,password,phone) VALUES('" + user.getUsername() + "', '"
				+ user.getEmail() + "', '" + user.getPassword() + "', '" + user.getPhone() + "')";
		try {

			statement = connection.createStatement();
			statement.executeUpdate(insert);
			System.out.println("User Inserted Successfully");


		} catch (SQLException exc) {
			System.out.println("Error with Interted User");
			System.out.println(exc.getMessage());

		}

	}

	@Override
	public boolean authUser() {

		System.out.println(
				"::::::::::::::::::::::::::::::::::::::: Login Page ::::::::::::::::::::::::::::::::::::::::::::");

		Scanner sc = new Scanner(System.in);

		boolean isPass = false;

		while (isPass == false) {

			System.out.println("Enter Username: ");
			String username = sc.nextLine();

			System.out.println("Enter Password: ");
			String password = sc.nextLine();

			try {
				statement = connection.createStatement();
				String SelectAll = "SELECT * FROM users";
				resultSet = statement.executeQuery(SelectAll);

				// checking user name and password

				while (resultSet.next()) {
					String name = resultSet.getString("username");
					String pass = resultSet.getString("password");

					if (name.equals(username) && pass.equals(password)) {
						System.out.println("Login Successfull");
						isPass = true;
						break;
					}

				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

			if (isPass == false) {
				System.out.println("Login Failed\n");
				System.out.println("Do you want to try again? (y/n)");
				String choice = sc.nextLine();
				if (choice.equalsIgnoreCase("n")) {
					break;
				}
			}

		}

		if (isPass == true) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void getAllUsers() {
		// TODO Auto-generated method stub

		ArrayList<UserModel> userList = new ArrayList<UserModel>();

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Loading All Users from Database");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

		try {

			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM users");

			while (resultSet.next()) {

				UserModel user = new UserModel();

				user.setId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setEmail(resultSet.getString("email"));
				// user.setPassword(resultSet.getString("password"));
				user.setPhone(resultSet.getString("phone"));

				userList.add(user);
			}

		} catch (SQLException exc) {

			System.out.println("Error with Interted Users");
			System.out.println(exc.getMessage());

		}

		System.out.printf("%-5s %-20s %-30s %-15s \n", "ID", "User Name", "Email",
				"Phone", "");

		for (UserModel user : userList) {
			System.out.printf("%-5d %-20s %-30s %-15s \n", user.getId(),
					user.getUsername(), user.getEmail(), user.getPhone());
			System.out.println("\n");
		}

	}

	@Override
	public void getUserByID() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getUserByID'");
	}

}
