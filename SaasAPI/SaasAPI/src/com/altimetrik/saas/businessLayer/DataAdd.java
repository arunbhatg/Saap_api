	package com.altimetrik.saas.businessLayer;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;
import java.util.Date;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class DataAdd {

	private long invoiceNo;
	private java.sql.Date invoiceDate;
	private String customerPO;
	private String address;
	private double totalCost;
	private String senderEmail;
	private String status;
	
	protected DataAdd(String invoiceNo, String invoiceDate,
			String customerPO, String address,
			String totalCost,String senderEmail) throws CustomException {
		
		System.out.println("Entered data curator "); 
		
		this.invoiceNo = Long.parseLong(invoiceNo);
		try {
			Date date = new SimpleDateFormat("dd/mm/yy").parse(invoiceDate);
			this.invoiceDate = new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.customerPO = customerPO;
		this.address = address;
		this.totalCost = Double.parseDouble(totalCost);
		this.senderEmail = senderEmail;
		this.status = "pending";
		
		this.insertData();
	}
	
	private void insertData() throws CustomException {
		Connection conn = null;
		PreparedStatement pStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","1234");
			
			
			pStatement = (PreparedStatement) conn.prepareStatement("insert into saas_database.ap(invoiceNo,invoiceDate,customerPO,address,totalCost,senderEmail,status) values (?,?,?,?,?,?,?); ");
			pStatement.setLong(1, this.invoiceNo); 
 
			pStatement.setDate(2, this.invoiceDate); 
			pStatement.setString(3, this.customerPO); 
			pStatement.setString(4, this.address);
			pStatement.setDouble(5, this.totalCost); 
			pStatement.setString(6, this.senderEmail);
			pStatement.setString(7, this.status);
			pStatement.executeUpdate();
			System.out.println("Data added to DB"); 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch(MySQLIntegrityConstraintViolationException e){
			System.out.println("my sql integrity constraint violation exception ");
			CustomException obj = new CustomException("Invoice number is already present ");
			throw obj;
		}catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}

	
}
