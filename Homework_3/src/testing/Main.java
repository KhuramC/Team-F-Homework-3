package testing;

import inventorymanagement.StockManagerSingleton;
import products.*;

public class Main {



	public static void main(String[] args) {
		
		//testing MediaProduct toString//
		MediaProduct p = new MediaProduct("fgr", 22.1, 1989, Genre.ELECTRONIC);
		System.out.println(p);
		
		StockManagerSingleton insta = StockManagerSingleton.getInstance();

	}

}
