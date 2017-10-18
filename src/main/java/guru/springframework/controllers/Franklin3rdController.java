package guru.springframework.controllers;

import org.aspectj.internal.lang.annotation.ajcPrivileged;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="FranklinLoginPage")
@Api(value="FranklinSecondPageController", description="Franklin Login Controller")
public class Franklin3rdController {

	@RequestMapping(value="/login/{id}/{password}", method=RequestMethod.GET)
	private ResponseEntity login(@PathVariable String id, @PathVariable String password, Model model){
		
		return new ResponseEntity("WIP", HttpStatus.OK);
	}
}
