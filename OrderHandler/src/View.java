import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class View extends JFrame implements ActionListener {
	private QueryListener queryListener;
	
	StringBuffer bufOrder = new StringBuffer();
	
	LinkedList<OrderData> orderItems = new LinkedList();
	
	protected JTextField customerID = new JTextField(8);
	protected JTextField products = new JTextField(8);
	protected JTextField quantityField = new JTextField(8);
	
	private JLabel enter = new JLabel ("Enter new order: ", JLabel.RIGHT);
	private JLabel custID = new JLabel ("Customer ID: ", JLabel.RIGHT);
	private JLabel prod = new JLabel ("Product ID: ", JLabel.RIGHT);
	private JLabel quantity = new JLabel ("Quantity: ", JLabel.RIGHT);
	
	private JLabel newCust = new JLabel ("Enter new customer: ", JLabel.RIGHT);
	private JLabel firstName = new JLabel ("First name: ", JLabel.RIGHT);
	private JLabel lastName = new JLabel ("Last name: ", JLabel.RIGHT);
	private JLabel address = new JLabel ("Address: ", JLabel.RIGHT);
	private JLabel phone = new JLabel ("Phone: ", JLabel.RIGHT);
	
	private JLabel newProd = new JLabel ("Enter new product: ", JLabel.RIGHT);
	private JLabel prodName = new JLabel ("Product name: ", JLabel.RIGHT);
	private JLabel prodPrice = new JLabel ("Product price: ", JLabel.RIGHT);
	
	private JLabel fillerLabel = new JLabel ("             ", JLabel.RIGHT);
	private JLabel fillerLabel2 = new JLabel ("             ", JLabel.RIGHT);
	
	protected JTextField first = new JTextField(8);
	protected JTextField last = new JTextField(8);
	protected JTextField add = new JTextField(8);
	protected JTextField ph = new JTextField(8);
	
	protected JTextField prodP = new JTextField(8);
	protected JTextField prodN = new JTextField(8);
	
	private JLabel queryLabel = new JLabel ("Query (* for all): ", JLabel.RIGHT);
	protected JTextField query = new JTextField(8);
	
	protected JTextArea queryField = new JTextArea();
	protected JScrollPane scrollPane = new JScrollPane(queryField);
	
	protected JTextArea orderField = new JTextArea();
	protected JScrollPane scrollPane2 = new JScrollPane(orderField);
	
	private JPanel p = new JPanel(new GridBagLayout());
	
	private JButton btn = new JButton("Add product to order");
	private JButton btnPlaceOrder = new JButton("Place order");
	private JButton btn2 = new JButton("Enter");
	private JButton btn3 = new JButton("Enter");
	
	private JButton btnOrder = new JButton		("View Order");	
	private JButton btnCustomer = new JButton	("View Customer");
	private JButton btnProduct = new JButton	("View Product");
	
	
	public void actionPerformed (ActionEvent e) {
		
		if (e.getSource()==btn){
			
			String customerIDString = customerID.getText();
			String productIDString = products.getText();
			String quantityString = quantityField.getText();
			
			int custID = Integer.parseInt(customerIDString);
			int prodID = Integer.parseInt(productIDString);
			int quant = Integer.parseInt(quantityString);
			
			orderItems.add(new OrderData(custID,prodID,quant));
			bufOrder.append(prodID + " x "+quant+"\n");
			orderField.setText(bufOrder.toString());
			
						
		}
		
		if (e.getSource()==btnPlaceOrder){
			if (queryListener != null) {						//calls queryperformed when button is pressed
				queryListener.orderAdded(orderItems);
			}
			orderItems.clear(); //empty order
			bufOrder.setLength(0);
			orderField.setText(bufOrder.toString());
		}
	
		if (e.getSource()==btn2){
			
			String customerFirst = first.getText();
			String customerLast = last.getText();
			String customerAddress = add.getText();
			String customerPhone = ph.getText();
			
			if (queryListener != null) {						//calls queryperformed when button is pressed
				queryListener.customerAdded(new CustomerData(customerFirst,customerLast,customerAddress,customerPhone));
			}
		
		}
		
		if (e.getSource()==btn3){
			try{
				String productName = prodN.getText();
				String productPrice = prodP.getText();
				double price=Double.parseDouble(productPrice);
				
				if (queryListener != null) {						//calls queryperformed when button is pressed
					queryListener.productAdded(new ProductData(productName,price));
				}
				}
			catch (Exception err){
				JOptionPane.showMessageDialog(null, "Check inputs! Price format: xx.xx");
			}
		
		}
		
		if (e.getSource()==btnCustomer){
			try{
				int id;
				String idString= query.getText();
				if (idString.equals("*")){
					id = -99;
				}
				
				else {
					id=Integer.parseInt(idString);
				}
				
				if (queryListener != null) {						//calls queryperformed when button is pressed
					queryListener.queryPerformed(new QueryData(id));
				}
			}
			catch(Exception err){
				JOptionPane.showMessageDialog(null, "Please enter valid id!");
			}
			
		}
		
		if (e.getSource()==btnProduct){
			try{
				int id;
				String idString= query.getText();
				if (idString.equals("*")){
					id = -99;
				}
				
				else {
					id=Integer.parseInt(idString);
				}
				
				if (queryListener != null) {						//calls queryperformed when button is pressed
					queryListener.productQueryPerformed(new QueryData(id));
				}
			}
			catch(Exception err){
				JOptionPane.showMessageDialog(null, "Please enter valid id!");
			}
			
		}
	
	
	}
	
	public View(Model model){
		
		setSize(900, 450);
		
		setLayout(new GridBagLayout());
		setLocationRelativeTo(null);
		GridBagConstraints c = new GridBagConstraints();
		
		c.weighty = 1;
		c.weightx = 1;
		c.gridx=1;
		c.gridy=1;
		c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(p,c);
		p.add(customerID,c);
		
		
		c.gridx=0;
		c.gridy=0;
		p.add(enter, c);
		
		c.gridx=1;
		c.gridy=4;
		p.add(btn, c);
		
		c.gridx=1;
		c.gridy=2;
		p.add(products,c);
		
		c.gridx=1;
		c.gridy=3;
		p.add(quantityField,c);		
		
		c.gridx=1;
		c.gridy=6;
		p.add(btnPlaceOrder,c);
				
		c.gridx=1;
		c.gridy=5;
		c.insets= new Insets(2,0,2,0);
		scrollPane2.setPreferredSize(new Dimension(160,50));
		p.add(scrollPane2, c);
		
		c.gridx=0;
		c.gridy=1;
		p.add(custID,c);
		
		c.gridx=0;
		c.gridy=2;
		p.add(prod,c);
		
		c.gridx=0;
		c.gridy=3;
		p.add(quantity,c);
		
		c.gridx=0;
		c.gridy=8;
		c.insets = new Insets(80,0,0,0);
		p.add(queryLabel,c);
		
		c.insets = new Insets(2,2,2,2);
		c.gridx=1;
		c.gridy=9;
		btnOrder.setPreferredSize(new Dimension(130,20));
		p.add(btnOrder,c);
		
		c.gridx=1;
		c.gridy=10;
		btnCustomer.setPreferredSize(new Dimension(130,20));
		p.add(btnCustomer,c);
		
		c.gridx=1;
		c.gridy=11;
		btnProduct.setPreferredSize(new Dimension(130,20));
		p.add(btnProduct,c);
				
		c.insets = new Insets(2,2,2,2);
		c.gridx=0;
		c.gridy=9;
		p.add(query,c);
		
		
		c.gridx=2;
		c.gridy=0;
		p.add(fillerLabel, c);
		
		c.gridx=3;
		c.gridy=0;
		p.add(newCust, c);
		
		c.gridx=3;
		c.gridy=1;
		p.add(firstName, c);
		
		c.gridx=3;
		c.gridy=2;
		p.add(lastName,c);
		
		c.gridx=3;
		c.gridy=3;
		p.add(address,c);
		
		c.gridx=3;
		c.gridy=4;
		p.add(phone,c);
		
		c.gridx=4;
		c.gridy=1;
		p.add(first, c);
		
		c.gridx=4;
		c.gridy=2;
		p.add(last,c);
		
		c.gridx=4;
		c.gridy=3;
		p.add(add,c);
		
		c.gridx=4;
		c.gridy=4;
		p.add(ph,c);
		
		c.gridx=4;
		c.gridy=5;
		p.add(btn2,c);
		
		c.gridx=5;
		c.gridy=0;
		p.add(fillerLabel2, c);
		
		c.gridx=6;
		c.gridy=0;
		p.add(newProd, c);
		
		c.gridx=6;
		c.gridy=1;
		p.add(prodName,c);
		
		c.gridx=6;
		c.gridy=2;
		p.add(prodPrice,c);
		
		c.gridx=7;
		c.gridy=1;
		p.add(prodN,c);
		
		c.gridx=7;
		c.gridy=2;
		p.add(prodP,c);
		
		c.gridx=7;
		c.gridy=3;
		p.add(btn3,c);
		
		c.gridx=4;
		c.gridy=9;
		c.gridwidth=4;
		c.gridheight=12;
		c.insets = new Insets(2,2,2,2);
		scrollPane.setPreferredSize(new Dimension(400,100));
		p.add(scrollPane, c);
		
		btn.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btnPlaceOrder.addActionListener(this);
		btnProduct.addActionListener(this);
		btnCustomer.addActionListener(this);
		btnOrder.addActionListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setQueryListener(QueryListener queryListener) {
		this.queryListener = queryListener;
		
	}

	public void setQueryField(StringBuffer buf) {
		queryField.setText(buf.toString());
		
	}
	
}
