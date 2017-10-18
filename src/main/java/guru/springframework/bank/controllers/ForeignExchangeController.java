package guru.springframework.bank.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/foreign")
@Api(value="foreign", description="This is a controller for foreign exchanges")
public class ForeignExchangeController {

	@RequestMapping(method=RequestMethod.GET)
	private ResponseEntity<String> dummyForeign(){
		return new ResponseEntity<String>("This is a controller for foreign exchanges", HttpStatus.OK);
	}
}
