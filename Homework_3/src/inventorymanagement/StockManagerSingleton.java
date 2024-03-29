package inventorymanagement;

import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
	// Gets current list of products
	public ArrayList<MediaProduct> getProducts() {
	    return products;
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
			
			ArrayList<MediaProduct> p = new ArrayList<MediaProduct>();
			
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split(","); //Split by comma
				if(parts.length != 5) { //Faulty line
					System.out.println("An invalid line has been found. Please check to see if the file has been formatted correctly.");
					sc.close();
					input.close();
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
					CDRecordProduct cd = new CDRecordProduct(title,price,year,genre);
					p.add(cd);
					break;
				case "Vinyl":
					VinylRecordProduct vinyl = new VinylRecordProduct(title,price,year,genre);
					p.add(vinyl);
					break;
				case "Tape":
					TapeRecordProduct tape = new TapeRecordProduct(title,price,year,genre);
					p.add(tape);
					break;
				}
			}
			if(p.size() == 0) { //Check for if something went wrong//
				System.out.println("File is either empty or has been formatted incorrectly.");
				System.out.println("Make sure the file is in 'Type','Title','Price','Year','Genre' format.");
				sc.close();
				input.close();
				return false;
			}
			
			this.products = p;
			sc.close();
			input.close();
			return true;
		}
		catch(Exception e) { //Excepts if it doesn't work
			e.printStackTrace();
			System.out.println("Cannot read file or it does not exist.");
			return false;
		}
		
		
	}
	
	//required//
	public boolean updateItemPrice(MediaProduct product, double newPrice) {		product.setPrice(newPrice);
		return true;
	}
	
	/**
	 * Adds a new media product to the inventory. Fails if product is already in inventory.
	 * @param product needed to be added.
	 * @return boolean
	 */
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
	
	/**
	 * Iterates through current inventory and removes item that is equal to the product parameter.
	 * @param product needed to be removed.
	 * @return boolean
	 */
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
	
	 // Saves the updated inventory back to the inventory.csv
	public boolean saveStock() {
		try {
	        BufferedWriter bw = new BufferedWriter(new FileWriter(inventoryFilePath));
	        
	        // Header row
	        bw.write("Type,Title,Price,Year,Genre");
	        bw.newLine();
	        
	        // The products information
	        for (MediaProduct product : products) {
	            String type = "";
	            if (product instanceof CDRecordProduct) {
	                type = "CD";
	            } else if (product instanceof VinylRecordProduct) {
	                type = "Vinyl";
	            } else if (product instanceof TapeRecordProduct) {
	                type = "Tape";
	            }
	            // Using get to get the products' information.
	            String title = product.getTitle();
	            double price = product.getPrice();
	            int year = product.getYear();
	            Genre genre = product.getGenre();

	            // Write the product information to the file
	            bw.write(type + "," + title + "," + price + "," + year + "," + genre);
	            bw.newLine();
	        }
	        // Close Bw
	        bw.close();
	        // True if success
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	        // False if failed
	        return false;
	    }
	}
	
	/**
	 * Goes through current inventory to find products below a given price.
	 * @param maxPrice The maximum price; nothing can be higher than this price.
	 * @return ArrayList of Media Products below the max price.
	 */
	public ArrayList<MediaProduct> getMediaProductBelowPrice(int maxPrice){
		if(this.products == null) {
			System.out.println("Products not initialized. Please do so first.");
			return null;
		}
		ArrayList<MediaProduct> lowPriceProducts = new ArrayList<MediaProduct>();
		for(MediaProduct product : this.products) {
			if(product.getPrice() < maxPrice) {
				lowPriceProducts.add(product);
			}
		}
		return lowPriceProducts;
	}
	
	/**
	 * Prints out the MediaProducts within the given list.
	 * @param productList list that will have contents printed out.
	 */
	public void printListOfMediaProduct(ArrayList<MediaProduct>productList) {
		if(this.products == null) {
			System.out.println("Products not initialized. Please do so first.");
			
		}else {
			System.out.println("Inventory of given product list:");
			for(MediaProduct product: productList) {
				System.out.println(product);
			}	
		}
		
	}
	
	/*
	 * Takes in MediaProduct list and finds the vinyls in the list and adds them to a separate list.
	 * @param productList list that will be scanned for the making of the viynls array.
	 * @return gives an array populated with vinyls from the MediaProduct array.
	 */
	public ArrayList<VinylRecordProduct>getVinylRecordList(ArrayList<MediaProduct> productList){
		ArrayList<VinylRecordProduct> vinylArray = new ArrayList<VinylRecordProduct>();
		//Use iterator to iterate and add matching product to list.
		if(productList.isEmpty()) {
			return null;
		}
		Iterator<MediaProduct> iterator = productList.iterator();
			while (iterator.hasNext()) {
				MediaProduct existingProduct = iterator.next();
				
				if (existingProduct instanceof VinylRecordProduct) {
					vinylArray.add((VinylRecordProduct) existingProduct);
				}
			}
		return vinylArray;
		}
	/*
	 * Takes in MediaProduct list and finds the CDs in the list and adds them to a separate list.
	 * @param productList list that will be scanned for the making of the CD array.
	 * @return gives an array populated with CDs from the MediaProduct array.
	 */
	public ArrayList<CDRecordProduct>getCDRecordsList(ArrayList<MediaProduct> productList){
		ArrayList<CDRecordProduct> cdArray = new ArrayList<CDRecordProduct>();
		//Use iterator to iterate and add matching product to list.
		if(productList.isEmpty()) {
			return null;
		}
		Iterator<MediaProduct> iterator = productList.iterator();
			while (iterator.hasNext()) {
				MediaProduct existingProduct = iterator.next();
				
				if (existingProduct instanceof CDRecordProduct) {
					cdArray.add((CDRecordProduct) existingProduct);
				}
			}
		return cdArray;
	}
	
	/*
	 * Takes in MediaProduct list and finds the tape in the list and adds them to a separate list.
	 * @param productList list that will be scanned for the making of the tape array.
	 * @return gives an array populated with tape from the MediaProduct array.
	 */
	public ArrayList<TapeRecordProduct>getTapeRecordList(ArrayList<MediaProduct> productList){
		ArrayList<TapeRecordProduct> tapeArray = new ArrayList<TapeRecordProduct>();
		//Use iterator to iterate and add matching product to list.
		if(productList.isEmpty()) {
			return null;
		}
		Iterator<MediaProduct> iterator = productList.iterator();
			while (iterator.hasNext()) {
				MediaProduct existingProduct = iterator.next();
				
				if (existingProduct instanceof TapeRecordProduct) {
					tapeArray.add((TapeRecordProduct) existingProduct);
				}
			}
		return tapeArray;
	}

}
