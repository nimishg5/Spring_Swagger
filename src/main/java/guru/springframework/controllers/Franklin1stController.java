package guru.springframework.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import io.swagger.annotations.Api;
import springfox.documentation.spring.web.json.Json;

@RestController
@RequestMapping("/FranklinHomePage")
@Api(value="FranklinHomePageController", description="Operations pertaining to products Defined on Franklin Home page")
public class Franklin1stController {
	
	@RequestMapping(method= RequestMethod.GET)
	private ResponseEntity franklinHome() throws IOException{
		Document doc = Jsoup.connect("https://www.franklintempletonindia.com/").get();
		String values=doc.select("span[class=fti-link-caption]").text();
		System.out.println(values);
		return new ResponseEntity(values, HttpStatus.OK);
	}
}
