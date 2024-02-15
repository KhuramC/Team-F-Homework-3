package products;

public class CDRecordProduct extends MediaProduct {

	public CDRecordProduct(String title, double price, int year, Genre genre) {
		super(title, price, year, genre);
	}

	@Override
	public String toString() {
		return "Title= " + title + ", price= " + price + ", year= " + year + ", genre= " + genre;
	}

}
