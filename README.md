# Saap_api
problem statement
Design a SAAS product for Accounts Payable 
Accounts payable is the amount owed by an entity to its vendors/suppliers for the goods and services received. To elaborate, once an entity orders goods and receives before making the payment for it, it should record a liability in its books of accounts(Typically ERP) based on the invoice amount. This short-term liability due to the suppliers, vendors, and others is called accounts payable. Once the payment is made to the vendor for the unpaid purchases, the corresponding amount is reduced from the accounts payable balance.
We are going to build a project for typical use cases involved in accounts payable 
The system should have following functions :
1)	The invoice details comes in email as parsable pdf document(not image pdf).We will use java email api to get the attachments from emails and store it in our local machine.
2)	We need to parse the pdf and get the data . Let’s assume we get only one format of invoice pdf. We will use Apache pdfbox to grab some fields from the invoice pdf and put those information in our DB
3)	We need a interface to list all the invoice data from DB
4)	We need a interface to approve the invoice data by an approver.(status change of invoice data)
5)	We need a interface to send email once the invoice data is approved .
Evaluation Criteria :
1)	How the classes are designed
2)	How the DB tables are designed
3)	How the business and data layer is design 
4)	How exception handling is done 
5)	Some clean code parameters has to be evaluated


We will give some basic info about apache pdfbox and java email api to send and receive emails, not full working code . They will use java..io,javaemail,jdbc,apachepdfboxapi’s to build this system. Lets use Maven based project structure.



