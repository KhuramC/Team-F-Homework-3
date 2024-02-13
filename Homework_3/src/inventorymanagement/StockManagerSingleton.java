package inventorymanagement;

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
	public StockManagerSingleton getInstance() {
		if (instance == null) {
			instance = new StockManagerSingleton();
		}
		return instance;	
	}

}
