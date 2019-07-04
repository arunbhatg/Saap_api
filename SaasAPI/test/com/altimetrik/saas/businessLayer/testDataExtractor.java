package com.altimetrik.saas.businessLayer;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import com.altimetrik.saas.businessLayer.AttachmentFetcher;
public class testDataExtractor {

	DataExtractor obj = null;
	
	@Before
	public void set()throws CustomException{
		obj = new DataExtractor("D:\\Acushnet.pdf","df");
		obj.extractData();
	}
	
	@Test
	public void testExtractData(){

		assertEquals("906262136",obj.getText1());

	}
	
	@Test
	public void testExtractData2() {
//		obj.extractData();
		assertEquals("08/01/18",obj.getText2());
	}
	
	@Test
	public void testExtractData3() {
//		obj.extractData();
		assertEquals("414308118",obj.getText3());
	}
	
	@Test
	public void testExtractData4() {
//		obj.extractData();
		System.out.println("AMERICAN GOLF CORPORATION\nPO BOX 188\nPICO RIVERA CA 90660-0188"); 
		System.out.println("**" + obj.getText4() + "**"); 
		assertEquals("AMERICAN GOLF CORPORATION \n PO BOX 188 \n PICO RIVERA CA 90660-0188",obj.getText4());
	}
	
	@Test
	public void testExtractData5() {

		assertEquals("$1,355.71",obj.getText5());
	}
	
}
