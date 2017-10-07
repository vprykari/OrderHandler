
public class CustomerData {
	String first;
	String last;
	String address;
	String phone;
	
	public CustomerData(String first, String last, String address, String phone) {
		super();
		this.first = first;
		this.last = last;
		this.address = address;
		this.phone = phone;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
