package testing;

import inventorymanagement.StockManagerSingleton;
import products.CDRecordProduct;
import products.Genre;
import products.MediaProduct;
import products.VinylRecordProduct;
import products.TapeRecordProduct;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create an instance of StockManagerSingleton
        StockManagerSingleton stockManager = StockManagerSingleton.getInstance();

        // Initialize stock from the CSV file
        if (stockManager.initializeStock()) {
            System.out.println("\nStock initialized successfully!\n");
        } else {
            System.out.println("Failed to initialize stock.");
            return;
        }

        // Print the current inventory
        System.out.println("Current Inventory:");
        stockManager.printListOfMediaProduct(stockManager.getProducts());

//        // Test updateItemPrice method. Uncomment when needed for testing.
//        CDRecordProduct cdToUpdate = new CDRecordProduct("Thriller", 19.99, 1982, Genre.POP);
//        double newPrice = 22.99;
//        stockManager.updateItemPrice(cdToUpdate, newPrice);
//        System.out.println("\nUpdated price of " + cdToUpdate.getTitle() + " to $" + newPrice);
//
//        // Test addItem method. Uncomment when needed for testing.
//        VinylRecordProduct newVinyl = new VinylRecordProduct("EXAMPLEVinyl", 19.99, 2022, Genre.ROCK);
//        if (stockManager.addItem(newVinyl)) {
//            System.out.println("Added " + newVinyl.getTitle() + " to the inventory.");
//        } else {
//            System.out.println("Failed to add " + newVinyl.getTitle() + ". Could already be in inventory.");
//        }
//
//        // Test removeItem method. Uncomment when needed for testing.
//        VinylRecordProduct vinylToRemove = new VinylRecordProduct("EXAMPLEVinyl", 19.99, 2022, Genre.ROCK);
//        if (stockManager.removeItem(vinylToRemove)) {
//            System.out.println("Removed " + vinylToRemove.getTitle() + " from the inventory.");
//        } else {
//            System.out.println(vinylToRemove.getTitle() + "NOT FOUND");
//        }

        // Save the updated inventory back to the CSV file
        if (stockManager.saveStock()) {
            System.out.println("\nInventory saved successfully!");
        } else {
            System.out.println("\nFailed to save inventory.");
        }

        // Printing current inventory after using updateItemPrice, addItem, or removeItem.
        System.out.println("\nCurrent Inventory:");
        stockManager.printListOfMediaProduct(stockManager.getProducts());

//     // Test getMediaProductBelowPrice method. Uncomment when needed for testing. Can change maxPrice as needed.
//        int maxPrice = 25;
//        System.out.println("Products below $" + maxPrice + ":");
//        stockManager.printListOfMediaProduct(stockManager.getMediaProductBelowPrice(maxPrice));
        
        
        // Test getVinylRecordList
        ArrayList<VinylRecordProduct> vinylList = stockManager.getVinylRecordList(stockManager.getProducts());
        System.out.println("\nVinyl Records:");
        for (VinylRecordProduct vinyl : vinylList) {
            System.out.println(vinyl);
        }
        // Test getCDRecordsList
        ArrayList<CDRecordProduct> cdList = stockManager.getCDRecordsList(stockManager.getProducts());
        System.out.println("\nCD Records:");
        for (CDRecordProduct cd : cdList) {
            System.out.println(cd);
        }

        // Test getTapeRecordList
        System.out.println("\nTape Records:");
        ArrayList<TapeRecordProduct> tapeList = stockManager.getTapeRecordList(stockManager.getProducts());
        for (TapeRecordProduct tape : tapeList) {
            System.out.println("Tape Product: " + tape.getTitle() + ", Price: $" + tape.getPrice() + ", Year: " + tape.getYear() + ", Genre: " + tape.getGenre());
        }

    }
}


