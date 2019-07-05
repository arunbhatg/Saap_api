package com.altimetrik.saas.businessLayer;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Flags;
//import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.FlagTerm;

public class AttachmentFetcher implements Runnable {

	protected static Properties userCredentials = new Properties();
	protected static Session session = null;
	protected static Store store = null;
	protected static Folder emailFolder = null;
	protected static String directoryPath = "";

	@Override
	public void run() {

		String pop3Host = "pop.gmail.com";
		String mailStoreType = "pop3";

		while (true) {

			try {

				userCredentials.load(new FileReader(
						"C:\\Users\\abhat\\workspace\\SaasAPI\\src\\com\\altimetrik\\saas\\user\\userCredentials.properties"));

				AttachmentFetcher.receiveEmail(pop3Host, mailStoreType);

			} catch (IOException e) {
				System.out.println("no file ");
			} catch (NoSuchProviderException e2) {
				System.out.println("No such provider");
				e2.printStackTrace();
			} catch (MessagingException e3) {
				System.out.println("Messaging is not working ");
				e3.printStackTrace();
			} catch (CustomException e4) {
				System.out.println(e4.getMessage());
			} finally {

				try {
					emailFolder.close(false);
					store.close();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}

			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	protected static void receiveEmail(String pop3Host, String mailStoreType)
			throws NoSuchProviderException, MessagingException, IOException, CustomException {

		Properties props = new Properties();
		props.put("mail.store.protocol", "pop3");
		props.put("mail.pop3.host", pop3Host);
		props.put("mail.pop3.port", "995");
		props.put("mail.pop3.starttls.enable", "true");

		// Get the Session object.
		Session session = Session.getInstance(props);

		// Session object is required because it enables access to protocol
		// providers
		// that implement the store and transport related classes.

		store = session.getStore("pop3s");
		store.connect(pop3Host, userCredentials.getProperty("userName"), userCredentials.getProperty("password"));

		// Create the folder object and open it in your mailbox.
		emailFolder = store.getFolder("INBOX");
		emailFolder.open(Folder.READ_ONLY);

		Flags seen = new Flags(Flags.Flag.SEEN);
		FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
		Message messages[] = emailFolder.search(unseenFlagTerm);

		// Message[] messages = emailFolder.getMessages();
		System.out.println("Total Message" + messages.length);

		// This gives total number of messages not read yet.(new messages)
		// Here I can create a thread pool based on the number of messages.
		// Currently I am handling them sequentially.

		// Iterate the messages
		for (int i = 0; i < messages.length; i++) {
			Message message = messages[i];

			// Address[] toAddress =
			// message.getRecipients(Message.RecipientType.TO);
			System.out.println("---------------------------------");
			System.out.println("Details of Email Message " + (i + 1) + " :");
			System.out.println("Subject: " + message.getSubject());
			System.out.println("From: " + message.getFrom()[0]);

			// System.out.println("email : " + message.getFrom()[0].toString());
			String[] arr = (message.getFrom()[0].toString()).split(" ");
			String senderEmail = arr[arr.length - 1];
			senderEmail = senderEmail.substring(1, senderEmail.length() - 1);

			System.out.println(message.getContentType());

			if (message.getContentType().contains("multipart")) {
				// System.out.println("Attachment Present ");
				Multipart multipart = (Multipart) message.getContent();
				for (int k = 0; k < multipart.getCount(); k++) {
					MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(k);
					if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
						// this part is attachment
						String fileName = part.getFileName();

						directoryPath = "D:\\" + fileName;
						part.saveFile(directoryPath);

						new DataExtractor(directoryPath, senderEmail);

					}
				}

			} else {

				continue;
			}
		}

	}

}
