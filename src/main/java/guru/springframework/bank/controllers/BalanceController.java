package guru.springframework.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.process.SeleniumLogin;
import io.swagger.annotations.Api;

@Controller
@RestController
@RequestMapping(value="/balance")
@Api(value="Login" , description="This is a controller for checking Balance of account numbers")
public class BalanceController {
	@Autowired
	SeleniumLogin seleniumLogin;
	@RequestMapping(value="/balance/{accountNumber}",method=RequestMethod.GET)
	private ResponseEntity findBalance(@PathVariable String accountNumber){
		if(seleniumLogin.driver != null){
			StringBuilder accountDetails = seleniumLogin.fetchAccounts();
			int startIndex = accountDetails.indexOf(accountNumber);
			if(startIndex==-1){
				return new ResponseEntity("Specified Account Number does not exist for the paricular user. Please provide correct account number.", HttpStatus.OK);
			}else{
				int SDGIndex = accountDetails.indexOf("SGD", startIndex);
				int startBalIndex = SDGIndex+4;
				int endBalIndex = accountDetails.indexOf("\n", startBalIndex);
				String balance = accountDetails.substring(startBalIndex, endBalIndex);
				return new ResponseEntity(balance, HttpStatus.OK);
			}
		}else{
			return new ResponseEntity("Please login to the controller first. Then try hitting the Accounts Controller.", HttpStatus.OK);
		}
		
	}
}
