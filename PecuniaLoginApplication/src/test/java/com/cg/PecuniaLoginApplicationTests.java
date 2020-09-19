package com.cg;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.entity.User;
import com.cg.exceptions.LoginException;
import com.cg.service.LoginService;

@SpringBootTest
  public class PecuniaLoginApplicationTests {
	@Autowired
	private LoginService ser;
	 @Test
	public void ValidDetails() throws LoginException 
	{
	User result=new User();
	 User details=new User();
	result.setUserID("1");                                                                 
	result.setPassword("sahithi1899");
	details=ser.doLogin("1","sahithi1899");
     Assertions.assertEquals(result.toString(), details.toString());
}
	@Test
	public void InvalidDetails() throws LoginException 
	{
		
	Assertions.assertThrows(LoginException.class,()->ser.doLogin( "2","sahithi" ));
	}	
	
	
}
	















	
	
	
