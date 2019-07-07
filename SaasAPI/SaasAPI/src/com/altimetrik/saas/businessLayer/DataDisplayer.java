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
			
		
			
			while(rs.next()){
				
				obj = new DatabaseObject(rs.getLong(1),rs.getDate(2),rs.getString(3),
						rs.getString(4),rs.getDouble(5),rs.getString(6),rs.getString(7));

				
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}
		return obj;
	}
	
	public static String approve(long invoiceNumber){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","1234");
			pStatement = conn.prepareStatement("select senderEmail from saas_database.ap where invoiceNo = ?");
			pStatement.setLong(1, invoiceNumber); 
			rs = pStatement.executeQuery();
			
			
			String senderEmailId = ""; 
			while(rs.next()){
				senderEmailId = rs.getString(1);
			}
			
			if(senderEmailId == ""){

				return new String("no such Id present ");
			}
			
			System.out.println("Sending Email ... "); 
			new EmailSender(senderEmailId);
			System.out.println("Email Sent "); 
			pStatement = conn.prepareStatement("update saas_database.ap set status = 'approved' where invoiceNo = ? ");
			pStatement.setLong(1, invoiceNumber); 
			pStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace(); 
		}
		
		return new String("invoice approved and email sent ");
	}
	
	public static void main(String []args){

		showData();
	}
	
}
