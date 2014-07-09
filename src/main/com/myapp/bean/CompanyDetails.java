package main.com.myapp.bean;

/**
 * This CompanyDetails class as bean to show the data
 * 
 * @author Siddhartha
 */
public class CompanyDetails {

	private String name;
	private String year;
	private String month;
	private String price;

	public CompanyDetails() {
		super();
	}

	public CompanyDetails(String name, String year, String month, String price) {
		this.name = name;
		this.year = year;
		this.month = month;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}