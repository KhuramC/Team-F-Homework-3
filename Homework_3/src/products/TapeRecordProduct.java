package products;

public class TapeRecordProduct extends MediaProduct {

	public TapeRecordProduct(String title, double price, int year, Genre genre) {
		super(title, price, year, genre);
	}

	@Override
	public String toString() {
		String s = "Title= " + title + ", price= " + price + ", year= " + year + ", genre= " + genre;
		return s;
	}

}
