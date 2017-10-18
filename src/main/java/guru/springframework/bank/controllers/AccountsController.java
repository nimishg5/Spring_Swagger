package guru.springframework.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.process.SeleniumLogin;
import io.swagger.annotations.Api;


@RestController
@RequestMapping(value="/accounts")
@Api(value="login", description="This is an Accounts Controller")
public class AccountsController {
	
	@Autowired
	SeleniumLogin seleniumLogin;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fetchaccounts", method= RequestMethod.GET)
	private ResponseEntity fetchAccounts() throws Exception{
		
		System.out.println("seleniumLogin=------------"+seleniumLogin);
		if(seleniumLogin.driver != null){
		//	boolean loginPage = seleniumLogin.testLogin();
			StringBuilder accountDetails = new StringBuilder();
			String message = null;
			/*if(loginPage){
				message = "Invalid Credentials";
				seleniumLogin.closeBrowser();
				return new ResponseEntity(message, HttpStatus.OK);
			}else{*/
				message = "Successfull Login";
				accountDetails = seleniumLogin.fetchAccounts();
				//seleniumLogin.closeBrowser();
				return new ResponseEntity(accountDetails, HttpStatus.OK);
			//}	
		}else{
			return new ResponseEntity("Please login to the controller first. Then try hitting the Accounts Controller.", HttpStatus.OK);
		}
	}
}
