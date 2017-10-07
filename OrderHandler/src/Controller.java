import java.util.LinkedList;

public class Controller implements QueryListener {
	Model model;
	View view;
	StringBuffer buf = new StringBuffer();
	
	public Controller(Model model, View view) {
		super();
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void customerAdded(CustomerData data) {
		model.newCustomer(data);
	}

	@Override
	public void queryPerformed(QueryData data) {
		model.getCustomer(data);
		buf=model.getBuf();
		view.setQueryField(buf);
	}

	@Override
	public void productAdded(ProductData data) {
		model.newProduct(data);
		
	}

	@Override
	public void orderAdded(LinkedList orderItems) {
		model.newOrder(orderItems);
		
	}

	@Override
	public void productQueryPerformed(QueryData data) {
		model.getProduct(data);
		buf=model.getBuf();
		view.setQueryField(buf);
	}

}
