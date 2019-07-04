package com.altimetrik.saas.user;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.altimetrik.saas.businessLayer.AttachmentFetcher;
import com.altimetrik.saas.businessLayer.CustomException;
import com.altimetrik.saas.businessLayer.CustomRuntimeException;
import com.altimetrik.saas.businessLayer.DataDisplayer;
import com.altimetrik.saas.businessLayer.DatabaseObject;

public class MyApplication2 {

	public static final int NTHREADS = 1;
	
	public static void runApplication() throws CustomRuntimeException,InterruptedException{
		
		try{
			ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
//			Callable<Integer> worker = new AttachmentFetcher2();
			
			executor.execute(new AttachmentFetcher());
//			Future<Integer> submit = executor.submit(worker);
//			System.out.println(submit.get());
			
			displayInterface();
		
		
		}catch(CustomRuntimeException e2){
			System.out.println("reached main execution exception"); 
			System.out.println(e2.getMessage()); 
		}
	}
	
	public static void displayInterface(){
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do{
			System.out.println("______________________________");
			System.out.println("i) Get all details "); 
			System.out.println("ii) Mark approved and send email"); 
//			System.out.println("iii) Send approved email ");
			System.out.println("iii) Exit ");
			System.out.println("______________________________"); 
			
			System.out.println("Enter your choice : "); 
			
			choice = sc.nextInt();
			
			switch(choice){
			case 1 : DatabaseObject obj = DataDisplayer.showData();
					 System.out.println(obj); 
				break;
			case 2 : System.out.println("Enter invoice number to approve "); 
					long invoiceNo = sc.nextLong();
					String s = DataDisplayer.approve(invoiceNo);
					System.out.println(s);
				break;
			case 3 : System.out.println("Closing application ... "); 
				sc.close();
				System.exit(0); 
				break;
				default :
					System.out.println("There's no such choice "); 
			}
		}while(true);
	}
	
	public static void main(String[] args) {
			try {
				runApplication();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
