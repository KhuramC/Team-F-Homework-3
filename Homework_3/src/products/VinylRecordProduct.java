package products;

public class VinylRecordProduct extends MediaProduct {

	public VinylRecordProduct(String title, double price, int year, Genre genre) {
		super(title, price, year, genre);
		
	}
	
	@Override
	public String toString() {
		String s = "Title= " + title + ", price= " + price + ", year= " + year + ", genre= " + genre;
		return s;
	}

}
