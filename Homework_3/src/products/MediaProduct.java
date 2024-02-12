package products;

public class MediaProduct {
    protected String title;
    protected double price;
    protected int year;
    protected Genre genre;

    public MediaProduct(String title, double price, int year, Genre genre) {
        this.title = title;
        this.price = price;
        this.year = year;
        this.genre = genre;
    }
}
