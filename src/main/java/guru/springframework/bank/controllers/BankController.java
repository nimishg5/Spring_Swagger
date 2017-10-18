package guru.springframework.bank.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Bank1stController")
@Api(value="Bank1stController", description="Operations Defined on Bank 1st controller")
public class BankController {

	@RequestMapping(method=RequestMethod.GET)
	private ResponseEntity<String> dummy(){
		return new ResponseEntity<String>("Bank controller", HttpStatus.OK);
	}
}
