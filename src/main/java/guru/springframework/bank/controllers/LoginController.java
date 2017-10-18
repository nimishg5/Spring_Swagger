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
@RequestMapping(value="/login")
@Api(value="Login" , description="This is a controller for checking login of users")
public class LoginController {
	
	@Autowired
	SeleniumLogin seleniumLogin;
	
	@RequestMapping(value="/login/{id}/{password}",method=RequestMethod.GET)
	private ResponseEntity<String> checklogin(@PathVariable String id, 
											  @PathVariable String password) throws Exception{
		System.out.println("seleniumLogin=------------"+seleniumLogin);
		seleniumLogin.setCredentials(id, password);
		boolean loginPage = seleniumLogin.testLogin();
		String message = null;
		if(loginPage){
			message = "Invalid Credentials";
		}else{
			message = "Successfull Login";
		}
	//	seleniumLogin.closeBrowser();
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
