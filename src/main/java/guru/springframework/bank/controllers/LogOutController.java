package guru.springframework.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.process.SeleniumLogin;
import io.swagger.annotations.Api;

@Controller
@RestController
@RequestMapping(value="/logout")
@Api(value="logout", description="This is a LogOut Controller")
public class LogOutController {

	@Autowired
	SeleniumLogin seleniumLogin;
	
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	@RequestMapping(value="/out",method=RequestMethod.GET)
	private ResponseEntity logMeOut(){
		if (seleniumLogin.driver ==null){
			return new ResponseEntity("Please login from the controller first. Then try logout!", HttpStatus.OK);
		}else{
			seleniumLogin.closeBrowser();
			return new ResponseEntity("You have been logged out Successfully", HttpStatus.OK);
		}
	}
}
