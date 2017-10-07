import java.util.LinkedList;

public interface QueryListener {
	public void customerAdded(CustomerData data);
	public void productAdded(ProductData data);
	

	public void queryPerformed(QueryData data);
	public void orderAdded(LinkedList orderItems);
	public void productQueryPerformed(QueryData queryData);	
}
