package com.altimetrik.saas.businessLayer;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;



public class DataExtractor {

	protected String path;
	protected String [] arr = null;
	protected String invoice,po,date,address,total;
	
	public DataExtractor(String path,String senderEmail) throws CustomException{
		

		
		this.path = path;
		this.extractData();
		new DataAdd(invoice,po,date,address,total,senderEmail);
	}
	
	public String getinvoice(){
		return this.invoice;	
	}
	
	public String getpo(){
		return this.po;
	}
	
	public String getdate(){
		return this.date;
	}
	
	public String getaddress(){
		return this.address;
	}
	
	public String gettotal(){
		return this.total;
	}
	
	protected void extractData(){
		File file = new File(this.path);

		PDDocument doc = null;
		try{
			doc = PDDocument.load(file);
			PDPage page = doc.getPage(0);
			
			PDFTextStripperByArea pdfStripper = new PDFTextStripperByArea();
			Rectangle2D.Float Rect = new Rectangle2D.Float(51,129,40,15);  
			Rectangle2D.Float Rect2 = new Rectangle2D.Float(180,129,45,15);
			Rectangle2D.Float Rect3 = new Rectangle2D.Float(290,147,55,15);
			Rectangle2D.Float Rect4 = new Rectangle2D.Float(51,169,200,45);
			Rectangle2D.Float Rect5 = new Rectangle2D.Float(51,312,1000,400);
	        pdfStripper.addRegion( "invoice No", Rect );    
	        pdfStripper.addRegion( "invoice Date", Rect2 );
	        pdfStripper.addRegion( "Customer PO", Rect3 );
	        pdfStripper.addRegion( "address", Rect4 );
	        pdfStripper.addRegion( "total invoice", Rect5 );
	        pdfStripper.extractRegions(page); 
			
	        this.invoice = pdfStripper.getTextForRegion("invoice No").trim();
	        System.out.println(invoice); 
	        
	        this.date = pdfStripper.getTextForRegion("invoice Date").trim();
	        System.out.println(date);
	        
	        this.po = pdfStripper.getTextForRegion("Customer PO").trim();
	        System.out.println(po);
	  
	        this.address = pdfStripper.getTextForRegion("address").trim();
	        System.out.println(address);
	        

	        
	        this.total = pdfStripper.getTextForRegion("total invoice").trim();
	        
	        arr = this.total.split("\n");
	        String temp = arr[arr.length-1];
	        this.total = "";
	        

	        
	        for(int i=0;i<temp.length();i++){
	        	if(temp.charAt(i) == ',' || temp.charAt(i) == '$')	
	        		continue;
	        	this.total += temp.charAt(i);
	        }
	        
	        System.out.println(this.total);
	        

        
		}catch(IOException e){
			e.printStackTrace();
		} finally {
				try {
					if(doc != null)
						doc.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		}
		
	}
	

}
