package guru.springframework.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.process.SeleniumLogin;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/transactions")
@Api(value="transactions", description="This is a controller for transactions")
public class TransactionsController {
	
	@Autowired
	SeleniumLogin seleniumLogin;
	
	@RequestMapping(value="last/{accountNumber}/{otp}",method=RequestMethod.GET)
	private ResponseEntity<String> recentTransaction(@PathVariable String accountNumber,
													 @PathVariable String otp) throws Exception{
		if(seleniumLogin.driver==null){
			return new ResponseEntity("Please login from the controller first. Then try Transactions Controller!", HttpStatus.OK);
		}else{
		return new ResponseEntity(seleniumLogin.getLastTransaction(accountNumber, otp),HttpStatus.OK);
		}
	}
}
