package com.altimetrik.saas.businessLayer;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import com.altimetrik.saas.businessLayer.AttachmentFetcher;
public class TestDataExtractor {
//this program will check the functionality of our database
	DataExtractor obj = null;
	
	@Before
	public void set()throws CustomException{
		obj = new DataExtractor("D:\\Acushnet.pdf","df");
		obj.extractData();
	}
	
	@Test
	public void TestExtractData(){
		//checks  invoice no extracted is correct or not

		assertEquals("906262136",obj.getinvoice());

	}
	
	@Test
	public void testExtractData2() {
//		obj.extractData();
		//checks  date no extracted is correct or not
		assertEquals("08/01/18",obj.getdate());
	}
	
	@Test
	public void testExtractData3() {
		//checks  PO no extracted is correct or not
		assertEquals("414308118",obj.getpo());
	}
	
	@Test
	public void testExtractaddress() {
//		//checks Address extracted is correct or not
		
		assertEquals("AMERICAN GOLF CORPORATION \n PO BOX 188 \n PICO RIVERA CA 90660-0188",obj.getaddress());
	}
	
	@Test
	public void testExtractTotal() {

		assertEquals("$1,355.71",obj.gettotal());
	}
	
}
