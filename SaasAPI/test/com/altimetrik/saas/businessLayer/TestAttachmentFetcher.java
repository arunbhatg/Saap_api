package com.altimetrik.saas.businessLayer;

import com.altimetrik.saas.businessLayer.AttachmentFetcher;

import javax.mail.MessagingException;

import org.junit.Test;

public class TestAttachmentFetcher {

	@Test(expected = MessagingException.class)
	public void testReceiveEmail() throws MessagingException, Exception {
		// this will check same email is saving to database and fetching back
		AttachmentFetcher.receiveEmail("arunbhat65@gmail.com", "arunbhat65@gmail.com");

	}

}
