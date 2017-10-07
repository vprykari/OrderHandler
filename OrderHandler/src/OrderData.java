
public class OrderData {
	int customerID;
	int productID;
	int quantity;
	
	public OrderData(int customerID, int productID, int quantity) {
		super();
		this.customerID = customerID;
		this.productID = productID;
		this.quantity = quantity;
	}

	public int getCustomerID() {
		return customerID;
	}

	public int getProductID() {
		return productID;
	}

	public int getQuantity() {
		return quantity;
	}
	
	
}
