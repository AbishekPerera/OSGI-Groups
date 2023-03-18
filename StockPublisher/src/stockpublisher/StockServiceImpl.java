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

		stockList.add(stock);

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

		try{
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
		}catch (SQLException exc) {
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

		for (StockModel stock : stockList) {
			if (stock.getStockID() == id) {
				System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", stock.getStockID(), stock.getStockName(),
						stock.getStockModel(), stock.getQuantity(), stock.getUnitPrice());
				return;
			}
		}
		System.out.println("No stock found");

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

		System.out.println("Enter ID to delete stock : ");
		stockId = scanner.nextInt();

		for (StockModel stock : stockList) {
			if (stock.getStockID() == stockId) {
				stockList.remove(stock);
				System.out.println("\nStock deleted successfully!!!");
				return;
			}
		}

		System.out.println("No stock found to delete!");

	}

	@Override
	public void exitMenu() {
		System.out.println("Succesfully Exitted From Stocks Menu!");
		System.exit(0);
	}

}
