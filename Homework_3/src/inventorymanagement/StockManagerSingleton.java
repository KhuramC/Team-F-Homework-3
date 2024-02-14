package inventorymanagement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import products.*;

public class StockManagerSingleton {

	private static StockManagerSingleton instance = null;
	
	private static final String inventoryFilePath = "files/inventory.csv";
	
	private ArrayList<MediaProduct> products;
	
	/**
	 * Private constructor to create single instance.
	 */
	private StockManagerSingleton() {
	}
	
	
	/**
	 * Creates a single instance if it has not been created yet.
	 * @return the instance of the StockManagerSingleton.
	 */
	public static StockManagerSingleton getInstance() {
		if (instance == null) {
			instance = new StockManagerSingleton();
		}
		return instance;	
	}
	
	/**
	 * Attempts to read into a csv file and create MediaProduct objects based on it to put into the ArrayList.
	 * @return boolean determining if initialization occurred correctly.
	 */
	public boolean initializeStock (){
		FileInputStream input;
		Scanner sc;
		
		try { //Tries to read into file//
			input = new FileInputStream(inventoryFilePath);
			sc = new Scanner(input);
		}
		catch(Exception e) { //Excepts if it doesn't work
			e.printStackTrace();
			System.out.println("Cannot read file or it does not exist.");
			return false;
		}
		
		ArrayList<MediaProduct> p = new ArrayList<MediaProduct>();
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] parts = line.split(","); //Split by comma
			if(parts.length != 5) { //Faulty line
				System.out.println("An invalid line has been found. Please check to see if the file has been formatted correctly.");
				return false;
			}
			if(parts[0].equals("Type")){ //First line should not create an object//
				continue;	
			}
			//based on ordering of csv file//
			String type = parts[0];
			String title = parts[1];
			double price = Double.parseDouble(parts[2]);
			int year = Integer.parseInt(parts[3]);
			
			
			
			
			//see if this errors out(put everything in try if so) if an incorrect enum is passed//
			Genre genre = Genre.valueOf(parts[4]);
			
			switch(type) { //creates product based on their type and adds to ArrayList//
			case "CD":
				CDRecordProduct product = new CDRecordProduct(title,price,year,genre);
				p.add(product);
				break;
			case "Vinyl":
				VinylRecordProduct product = new VinylRecordProduct(title,price,year,genre);
				p.add(product);
				break;
			case "Tape":
				TapeRecordProduct product = new TapeRecordProduct(title,price,year,genre);
				p.add(product);
				break;
			}
		}
		if(p.size() == 0) { //Check for if something went wrong//
			System.out.println("File is either empty or has been formatted incorrectly.");
			System.out.println("Make sure the file is in 'Type','Title','Price','Year','Genre' format.");
			return false;
		}
		
		this.products = p;
		sc.close();
		input.close();
		return true;
	}
	
	//required//
	public boolean updateItemPrice(MediaProduct product, double newPrice) {
		return true;
	}
	
	//required//
	public boolean addItem(MediaProduct product) {
		if (product != null) {
			//Assuming product is correctly made.
			if (!products.contains(product)) {
				products.add(product);
				return true;
			}
		}
		return false; //Failed to add
	}
	
	//required//
	public boolean removeItem(MediaProduct product) {
		//Use iterator to iterate and remove matching product.
		Iterator<MediaProduct> iterator = products.iterator();
		
		while (iterator.hasNext()) {
			MediaProduct existingProduct = iterator.next();
			
			if (existingProduct.equals(product)) {
				iterator.remove();
				return true;
			}
		}
		
		return false;
	}
	
	//required
	public boolean saveStock() {
		return true;
	}
	
	//required//
	public ArrayList<MediaProduct> getMediaProductBelowPrice(int maxPrice){
		return null;
	}
	
	//required//
	public void printListOfMediaProduct(ArrayList<MediaProduct>productList) {
		
	}
	
	//required//
	public ArrayList<VinylRecordProduct>getVinylRecordList(ArrayList<MediaProduct> productList){
		return null;
		
	}
	
	//required//
	public ArrayList<CDRecordProduct>getCDRecordsList(ArrayList<MediaProduct> productList){
		return null;
	}
	
	//required//
	public ArrayList<TapeRecordProduct>getTapeRecordList(ArrayList<MediaProduct> productList){
		return null;
	}

}
