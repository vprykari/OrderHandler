import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;



public class Model {
		StringBuffer buf = new StringBuffer();
	
	
		public void newCustomer(CustomerData data) {
			insertCustomer(data.getFirst(),data.getLast(), data.getAddress(), data.getPhone());
			
		}

		public void getCustomer(QueryData data){
			buf.setLength(0);
			
			if (data.getId() == -99){
				selectAllCustomers();
			}
			else{
			selectCustomers(data.getId());
			}
		}
		
		public void getProduct(QueryData data){
			buf.setLength(0);
			
			if (data.getId() == -99){
				selectAllProducts();
			}
			else{
				selectProducts(data.getId());
			}
		}
		
		public StringBuffer getBuf() {
			return buf;
		}
		
		public void newProduct(ProductData data){
			insertProduct(data.getName(),data.getPrice());
		}
		
		public void newOrder(LinkedList<OrderData> orderItems){
			OrderData idData = orderItems.get(0);
			int id = idData.getCustomerID();
			
			int orderID = insertOrder(id);					//insert order into Orders
			System.out.println("last order id is: " + orderID);
			
			Iterator<OrderData> Iterator = orderItems.iterator();	//next, insert products into OrderItems
	        while (Iterator.hasNext()) {
	        	
	        	OrderData data = Iterator.next();
	        	insertOrderItems(orderID,data.getProductID(),data.getQuantity());
	            //System.out.println(data.getProductID()+" : "+ data.getQuantity()); 
	            }
			
			
		}
		
		private int insertOrder(int customerID){
			String sql = "INSERT INTO Orders(CustomerID) VALUES(?)";
			try (Connection conn = this.connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, customerID);
	            pstmt.executeUpdate();
	            
	            ResultSet rs = pstmt.getGeneratedKeys();
	            return rs.getInt(1);
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            return 0;
	        }
		}
		
		private void insertOrderItems(int orderID, int productID, int quantity){
			String sql = "INSERT INTO OrderItems(OrderID,ProductID,Quantity) VALUES(?,?,?)";
			try (Connection conn = this.connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, orderID);
	            pstmt.setInt(2, productID);
	            pstmt.setInt(3, quantity);

	            pstmt.executeUpdate();
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            JOptionPane.showMessageDialog(null, "Customer or product does not exist!");
	        }
		}
		
		private void insertProduct(String name, double price) {
			String sql = "INSERT INTO Products(ProductName,Price) VALUES(?,?)";
			 try (Connection conn = this.connect();
		                PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, name);
		            pstmt.setDouble(2, price);

		            pstmt.executeUpdate();
		            
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
			
		}
			
		private void selectAllCustomers() {
			String sql = "SELECT * FROM Customers";
			try (Connection conn = this.connect();
		            Statement stmt  = conn.createStatement();		        		
		            ResultSet rs    = stmt.executeQuery(sql)){
		            
		            // loop through the result set
		            while (rs.next()) {		            			                
		            	buf.append(rs.getInt("CustomerID"));
		            	buf.append(" | "+rs.getString("FirstName"));
		            	buf.append(" | "+rs.getString("LastName"));
		            	buf.append(" | "+rs.getString("Address"));
		            	buf.append(" | "+rs.getString("Phone")+"\n");
		            }
		            
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
		}

		private void selectCustomers(int id) {
			String sql = "SELECT * FROM Customers WHERE CustomerID = "+id;
			try (Connection conn = this.connect();
		            Statement stmt  = conn.createStatement();		        		
		            ResultSet rs    = stmt.executeQuery(sql)){
		            
		            // loop through the result set
		            while (rs.next()) {		            			                
		            	buf.append(rs.getInt("CustomerID"));
		            	buf.append(" | "+rs.getString("FirstName"));
		            	buf.append(" | "+rs.getString("LastName"));
		            	buf.append(" | "+rs.getString("Address"));
		            	buf.append(" | "+rs.getString("Phone"));
		            }
		            
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
		}
		
		private void selectProducts(int id) {
			String sql = "SELECT * FROM Products WHERE ProductID = "+id;
			try (Connection conn = this.connect();
		            Statement stmt  = conn.createStatement();		        		
		            ResultSet rs    = stmt.executeQuery(sql)){
		            
		            // loop through the result set
		            while (rs.next()) {		            			                
		            	buf.append(rs.getInt("ProductID"));
		            	buf.append(" | "+rs.getString("ProductName"));
		            	buf.append(" | "+rs.getDouble("Price"));
		            }
		            
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
		}
		
		private void selectAllProducts() {
			String sql = "SELECT * FROM Products";
			try (Connection conn = this.connect();
		            Statement stmt  = conn.createStatement();		        		
		            ResultSet rs    = stmt.executeQuery(sql)){
		            
		            // loop through the result set
		            while (rs.next()) {		            			                
		            	buf.append(rs.getInt("ProductID"));
		            	buf.append(" | "+rs.getString("ProductName"));
		            	buf.append(" | "+rs.getDouble("Price")+"\n");
		            }
		            
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
		}

		private Connection connect() { //Connect to database
	        // SQLite connection string
			
	        String url = "jdbc:sqlite:C:/Users/valte/Documents/OrderDB.db";
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	            conn.createStatement().execute("PRAGMA foreign_keys = ON");
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }
		
		public void insertCustomer(String first, String last, String address, String phone){
			String sql = "INSERT INTO Customers(FirstName,LastName,Address,Phone) VALUES(?,?,?,?)";
			 try (Connection conn = this.connect();
		                PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, first);
		            pstmt.setString(2, last);
		            pstmt.setString(3, address);
		            pstmt.setString(4, phone);
		            
		            
		            pstmt.executeUpdate();
		            
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
			
		}

}

