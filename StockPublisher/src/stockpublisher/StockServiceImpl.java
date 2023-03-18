package stockpublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javadatabasecon.JDBCDemo;
import javadatabasecon.JDBCDemoInterface;

public class StockServiceImpl implements IStockService {

	Scanner scanner = new Scanner(System.in);
	private ArrayList<StockModel> stockList = new ArrayList<StockModel>();

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet;
	private JDBCDemoInterface database;

	public StockServiceImpl() {

		database = (JDBCDemoInterface) new JDBCDemo();
		connection = database.connection();
	}

	@Override
	public void addStock() {

		StockModel stock = new StockModel();

		System.out.println("\nEnter stock ID: ");
		stock.setStockID(scanner.nextInt());

		System.out.println("Enter stock brand: ");
		stock.setStockName(scanner.next());

		System.out.println("Enter stock model: ");
		stock.setStockModel(scanner.next());

		System.out.println("Enter quantity: ");
		stock.setQuantity(scanner.nextInt());

		System.out.println("Enter Unit Price: ");
		stock.setUnitPrice(scanner.nextDouble());

		// stockList.add(stock);

		// Create a SQL statement
		String sql = "INSERT INTO stock (stockName, stockModel, quantity, unitPrice) VALUES ('"
				+ stock.getStockName() + "', '" + stock.getStockModel() + "', '"
				+ stock.getQuantity() + "', '" + stock.getUnitPrice() + "')";

		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);

		} catch (SQLException exc) {
			System.out.println("Error with Interting Stock");
			System.out.println(exc.getMessage());
		}

		System.out.println("\nStock added successfully.\n");

	}

	@Override
	public void getAllStock() {

		ArrayList<StockModel> stockList1 = new ArrayList<StockModel>();

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Retrieving All Stocks");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

		// Create a SQL statement
		String sql = "SELECT * FROM stock";

		try {
			// Get the result set
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			// Loop through the result set and add each row to the stockList1 array
			while (resultSet.next()) {
				StockModel stock = new StockModel();
				stock.setStockID(resultSet.getInt("stockID"));
				stock.setStockName(resultSet.getString("stockName"));
				stock.setStockModel(resultSet.getString("stockModel"));
				stock.setQuantity(resultSet.getInt("quantity"));
				stock.setUnitPrice(resultSet.getDouble("unitPrice"));
				stockList1.add(stock);
			}
		} catch (SQLException exc) {
			System.out.println("Error with Interted bILL");
			System.out.println(exc.getMessage());
		}

		// Define the column headers
		System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "Stock ID", "Stock Brand", "Stock Model", "Quantity",
				"Unit Price");

		// Loop through the stockList1 array and print each row
		for (StockModel stock : stockList1) {
			System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", stock.getStockID(), stock.getStockName(),
					stock.getStockModel(), stock.getQuantity(), stock.getUnitPrice());

		}

	}

	@Override
	public void getStockById() {

		int id;

		System.out.print("Enter Stock ID : ");
		id = scanner.nextInt();

		System.out.println();
		System.out.println(":::::::::::::::::::::::::::::::");
		System.out.println("..Searching through the Stock..");
		System.out.println(":::::::::::::::::::::::::::::::\n");

		System.out.println();

		System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "Stock ID", "Stock Brand", "Stock Model", "Quantity",
				"Unit Price");
		System.out.println();

		// Create a SQL statement
		String sql = "SELECT * FROM stock WHERE stockID = " + id;

		try {

			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
				StockModel stock = new StockModel();
				stock.setStockID(resultSet.getInt("stockID"));
				stock.setStockName(resultSet.getString("stockName"));
				stock.setStockModel(resultSet.getString("stockModel"));
				stock.setQuantity(resultSet.getInt("quantity"));
				stock.setUnitPrice(resultSet.getDouble("unitPrice"));
				System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", stock.getStockID(), stock.getStockName(),
						stock.getStockModel(), stock.getQuantity(), stock.getUnitPrice());
			} else {
				System.out.println("No stock found");
			}

		} catch (SQLException exc) {
			System.out.println("Error retrieving stock from database");
			System.out.println(exc.getMessage());
		}


	}

	@Override
	public void updateStockById() {

		int id;
		int choice;

		System.out.print("Enter ID of the stock needs updating : ");
		id = scanner.nextInt();

		System.out.println();
		System.out.println(":::::::::::::::::::::::::::::::");
		System.out.println("..Searching The Stock To Update..");
		System.out.println(":::::::::::::::::::::::::::::::\n");

		System.out.println();

		for (StockModel stock : stockList) {
			if (stock.getStockID() == id) {

				System.out.println("\n");

				System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "Stock ID", "Stock Brand", "Stock Model",
						"Quantity", "Unit Price");

				System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", stock.getStockID(), stock.getStockName(),
						stock.getStockModel(), stock.getQuantity(), stock.getUnitPrice());
				System.out.println("\n");

				System.out.println("Which data needs updating?");
				System.out.println("1  - Stock ID");
				System.out.println("2  - Stock Name");
				System.out.println("3  - Quantity ");
				System.out.println("4  - Unit Price");
				System.out.println("\n--------------------------");

				choice = scanner.nextInt();

				System.out.println(choice);

				switch (choice) {
					case 1:
						System.out.print("Enter new Stock ID : ");
						int newID = scanner.nextInt();
						stock.setStockID(newID);
						break;
					case 2:
						System.out.print("Enter new Stock Brand: ");
						String newName = scanner.next().trim();
						stock.setStockName(newName);
						break;
					case 3:
						System.out.print("Enter new Stock Model: ");
						String newModel = scanner.next().trim();
						stock.setStockModel(newModel);
						break;
					case 4:
						System.out.print("Enter new Quantity: ");
						int newQuantity = scanner.nextInt();
						stock.setQuantity(newQuantity);
						break;
					case 5:
						System.out.print("Enter new Unit Price: ");
						double newUnitPrice = scanner.nextDouble();
						stock.setUnitPrice(newUnitPrice);
						break;
					default:
						System.out.println("Invalid choice!");
				}

				System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "Stock ID", "Stock Brand", "Stock Model",
						"Quantity", "Unit Price");
				System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", stock.getStockID(), stock.getStockName(),
						stock.getStockModel(), stock.getQuantity(), stock.getUnitPrice());
				System.out.println("\n");
			} else {
				System.out.println("Invalid Stock ID!!!");
			}
		}

	}

	@Override
	public void deleteStockById() {

		int stockId;

		System.out.println("::::::::::::::::::::::::::::::: Delete Stock ::::::::::::::::::::::::::::::");

		System.out.println("Enter ID to delete stock : ");
		stockId = scanner.nextInt();

		// Create a SQL statement
		String sql = "DELETE FROM stock WHERE stockID = " + stockId;

		try{

			statement = connection.createStatement();
			int rowsDeleted = statement.executeUpdate(sql);

			if (rowsDeleted > 0) {
				System.out.println("Stock deleted successfully!");
			} else {
				System.out.println("No stock found to delete!");
			}

		}catch (SQLException exc) {

			System.out.println("Error deleting bill with ID " + stockId);
			System.out.println(exc.getMessage());
		}

		System.out.println("\n");

	}

	@Override
	public void exitMenu() {
		System.out.println("Succesfully Exitted From Stocks Menu!");
		System.exit(0);
	}

}
