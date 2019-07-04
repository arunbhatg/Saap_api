package com.altimetrik.saas.businessLayer;

import java.sql.*;


public class DataDisplayer {

	private static Connection conn = null;
	private static PreparedStatement pStatement = null;
	private static ResultSet rs = null;
	private static final int NTHREADS = 1;
	
	public static DatabaseObject showData(){
		
			DatabaseObject obj = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","1234");
			pStatement = conn.prepareStatement("select * from saas_database.ap");
			rs = pStatement.executeQuery();
			
			// cannot display data here. this is business layer
			// create a new object and pass to presentation layer
			
			
			while(rs.next()){
				
				obj = new DatabaseObject(rs.getLong(1),rs.getDate(2),rs.getLong(3),
						rs.getString(4),rs.getDouble(5),rs.getString(6),rs.getString(7));
//				System.out.println("Invoice No : " + rs.getLong(1));   
//				System.out.println("Invoice Date : " + rs.getDate(2));
//				System.out.println("Customer PO : " + rs.getLong(3)); 
//				System.out.println("address : " + rs.getString(4)); 
//				System.out.println("total Cost : " + rs.getDouble(5)); 
//				System.out.println("sender Email : " + rs.getString(6));
//				System.out.println("status : " + rs.getString(7));
				
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}
		return obj;
	}
	
	public static String approve(long invoiceNumber){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kamal","root","");
			pStatement = conn.prepareStatement("select senderEmail from saas_database.ap where invoiceNo = ?");
			pStatement.setLong(1, invoiceNumber); 
			rs = pStatement.executeQuery();
			
			
			String senderEmailId = ""; 
			while(rs.next()){
				senderEmailId = rs.getString(1);
			}
			
			if(senderEmailId == ""){
//				new EmailSender(senderEmailId);
				return new String("no such Id present ");
			}
			
			System.out.println("Sending Email ... "); 
			new EmailSender(senderEmailId);
			System.out.println("Email Sent "); 
			pStatement = conn.prepareStatement("update invoiceDetails set status = 'approved' where invoiceNo = ? ");
			pStatement.setLong(1, invoiceNumber); 
			pStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace(); 
		}
		
		return new String("invoice approved and email sent ");
	}
	
	public static void main(String []args){
//		long invoiceNo = 906262136;
//		approve(invoiceNo);
		showData();
	}
	
}
