package authmanagepublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

		System.out.println("add User");

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
	
				resultSet.close();
				statement.close();
	
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

		try {
			statement = connection.createStatement();
			String SelectAll = "SELECT * FROM users";
			resultSet = statement.executeQuery(SelectAll);

			// Iterate through the ResultSet and print the values of each column
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("username");
				String email = resultSet.getString("email");

				System.out.println("User ID: " + id + ", Name: " + name + ", Email: " + email);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
