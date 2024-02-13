package inventorymanagement;

import java.util.ArrayList;
import products.*;

public class StockManagerSingleton {

	private static StockManagerSingleton instance = null;
	
	private static final String inventoryFilePath = "files/inventory.csv";
	
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
	
	//required//
	public boolean initializeStock() {
		return true;
		
	}
	
	//required//
	public boolean updateItemPrice(MediaProduct product, double newPrice) {
		return true;
	}
	
	//required//
	public boolean addItem(MediaProduct product) {
		return true;
	}
	
	//required//
	public boolean removeItem(MediaProduct product) {
		return true;
		
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
