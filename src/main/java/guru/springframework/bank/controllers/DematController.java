package guru.springframework.bank.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/demat")
@Api(value="demat", description="This is a controller for demat accounts")
public class DematController {
	
	@RequestMapping(method=RequestMethod.GET)
	private ResponseEntity<String> dummyTransaction(){
		return new ResponseEntity<String>("This is a controller for demat accounts", HttpStatus.OK);
	}
}
