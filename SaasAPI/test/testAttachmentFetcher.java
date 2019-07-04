

import com.altimetrik.saas.businessLayer.AttachmentFetcher;

import javax.mail.MessagingException;

import org.junit.Test;

public class testAttachmentFetcher {

	@Test(expected = MessagingException.class) 
	public void  testReceiveEmail() throws MessagingException, Exception{
		AttachmentFetcher.receiveEmail("ab","ab");
	}
	
}

