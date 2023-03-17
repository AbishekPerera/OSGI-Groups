package billmanagepublisher;

public class BillModel {

	private int id;
	private String CusName;
	private String PhoneModel;
	private String PhoneBrand;
	private String PhoneImei;
	private double price;
	private double discount;
	private double total;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCusName() {
		return CusName;
	}
	public void setCusName(String cusName) {
		CusName = cusName;
	}
	public String getPhoneModel() {
		return PhoneModel;
	}
	public void setPhoneModel(String phoneModel) {
		PhoneModel = phoneModel;
	}
	public String getPhoneBrand() {
		return PhoneBrand;
	}
	public void setPhoneBrand(String phoneBrand) {
		PhoneBrand = phoneBrand;
	}
	public String getPhoneImei() {
		return PhoneImei;
	}
	public void setPhoneImei(String phoneImei) {
		PhoneImei = phoneImei;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}
