package guru.springframework.bank.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/Loans")
@Api(value="Loans", description="This is a controller for Loans")
public class LoanController {

	@RequestMapping(method=RequestMethod.GET)
	private ResponseEntity<String> dummyLoan(){
		return new ResponseEntity<String>("This is a controller for Loans", HttpStatus.OK);
	}
}