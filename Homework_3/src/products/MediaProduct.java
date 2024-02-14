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
    
    //Copy constructor
    public MediaProduct(MediaProduct product) {
    	this.title = product.getTitle();
    	this.price = product.getPrice();
    	this.year = product.getYear();
    	this.genre = product.getGenre();
    }

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public String toString() {
		String s = title + " is worth $" + price + ", was released in the year " + year + ", and is from the " + genre +" genre.";
		return s;
	}
	
	/*
	 * Overridden equals method to compare two MediaProduct objects.
	 */
	@Override
	public boolean equals(Object obj) {
		MediaProduct p = ((MediaProduct)obj);
		if(this.title.equals(p.getTitle()) && 
				this.price == p.getPrice() && 
				this.year == p.getYear() && 
				this.genre.equals(p.getGenre())) {
			return true;
		}
		return false;
	}
}