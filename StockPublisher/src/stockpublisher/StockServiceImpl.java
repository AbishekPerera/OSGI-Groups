package stockpublisher;

import java.util.ArrayList;
import java.util.Scanner;

public class StockServiceImpl implements IStockService {

	Scanner scanner = new Scanner(System.in);
	private ArrayList<StockModel> stockList = new ArrayList<StockModel>();
	 
	
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
		
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Retrieving All Stocks");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

		// Define the column headers
		System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "Stock ID", "Stock Brand", "Stock Model", "Quantity", "Unit Price");
		

		// Loop through the stockList array and print each row
		for (StockModel stock : stockList) {
			System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", stock.getStockID(), stock.getStockName(), stock.getStockModel(), stock.getQuantity(), stock.getUnitPrice());
			
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
        
		System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "Stock ID", "Stock Brand", "Stock Model", "Quantity", "Unit Price");
		System.out.println();
		
		for(StockModel stock : stockList) {
			 if(stock.getStockID() == id){
					System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", stock.getStockID(), stock.getStockName(), stock.getStockModel(), stock.getQuantity(), stock.getUnitPrice());
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
        
		for(StockModel stock : stockList) {
			 if(stock.getStockID() == id){
				 
				System.out.println("\n");
				 
				System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "Stock ID", "Stock Brand", "Stock Model", "Quantity", "Unit Price");
			    
				System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", stock.getStockID(), stock.getStockName(), stock.getStockModel(), stock.getQuantity(), stock.getUnitPrice());
				System.out.println("\n");
				
				System.out.println("Which data needs updating?");
				System.out.println("1  - Stock ID");
				System.out.println("2  - Stock Name");
				System.out.println("3  - Quantity ");
				System.out.println("4  - Unit Price");
				System.out.println("\n--------------------------");
				
				
				choice = scanner.nextInt();
				
				System.out.println(choice);
				
				switch(choice) {
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
				
				System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "Stock ID", "Stock Brand", "Stock Model", "Quantity", "Unit Price");
				System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", stock.getStockID(), stock.getStockName(), stock.getStockModel(), stock.getQuantity(), stock.getUnitPrice());
	            System.out.println("\n");
			 }
			 else {
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
