package com.altimetrik.saas.businessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataApprover {

	String invoiceNumber;
	
	public DataApprover(String invoiceNumber){
		this.invoiceNumber = invoiceNumber;
		this.approveEmail();
	}
	
	private void approveEmail(){
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","1234");
			pStatement = (PreparedStatement) conn.prepareStatement("select customer_email from saas_database.ap where invoiceNo = ? ");
			pStatement.setString(1, this.invoiceNumber); 
//			pStatement.setDate(2, this.invoiceDate); 
			rs = pStatement.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(1)); 
				System.out.println(rs.getString(2)); 
				System.out.println(rs.getString(3)); 
				System.out.println(rs.getString(4)); 
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// Need to send h
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
