package guru.springframework.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/FranklinSecondPage")
@Api(value="FranklinSecondPageController", description="Operations pertaining to products Defined on Franklin 2nd page")
public class Franklin2ndController {
	
	@RequestMapping(value="/aboutus" ,method= RequestMethod.GET)
	@ApiOperation(value = "Get the details of about us page")
	private ResponseEntity<Map<String, String>> aboutUs() throws IOException{
		Document doc = Jsoup.connect("https://www.franklintempletonindia.com/investor/about-us#tab_tab0").get();
		Element division=doc.getElementsByClass("well fti-bg-primaryBlue").first();
		Element list = division.getElementsByClass("fti-numberGrid").first();
		Elements spans1 = list.getElementsByClass("fti-count");
		Elements spans2 = list.getElementsByClass("grid-text");
		Map<String, String> map = new LinkedHashMap<String, String>();  
		for(int i=0; i<spans1.size(); i++){
			map.put(spans1.get(i).ownText(), spans2.get(i).ownText());
		}
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/america", method= RequestMethod.GET)
	@ApiOperation(value = "Get the list of places in America")
	private ResponseEntity<List<String>> placesInAmerica() throws IOException{
		Document doc = Jsoup.connect("https://www.franklintempletonindia.com/investor/about-us#tab_tab0 ").get();
		Element blockQuote=doc.getElementsByClass(".fti-bg-skyBlue--2 fti-border-blue").first();
		Elements places = blockQuote.getAllElements();
		List<String> al = new ArrayList<String>();
		for(Element place : places){
			if(!place.ownText().equals(""))
			al.add(place.ownText());
		}
		return new ResponseEntity<List<String>>(al, HttpStatus.OK);
	}
	
	@RequestMapping(value="/europe",method= RequestMethod.GET)
	@ApiOperation(value = "Get the list of places in Europe")
	private ResponseEntity<List<String>> placesInEurope() throws IOException{
		Document doc = Jsoup.connect("https://www.franklintempletonindia.com/investor/about-us#tab_tab0 ").get();
		Element blockQuote=doc.getElementsByClass(".fti-bg-skyBlue--2 fti-border-green").first();
		Elements places = blockQuote.getAllElements();
		List<String> al = new ArrayList<String>();
		for(Element place : places){
			if(!place.ownText().equals(""))
			al.add(place.ownText());
		}
		return new ResponseEntity<List<String>>(al, HttpStatus.OK);
	}
	
	@RequestMapping(value="/asia", method= RequestMethod.GET)
	@ApiOperation(value = "Get the list of places in Asia Pacific")
	private ResponseEntity<List<String>> placesInAsia() throws IOException{
		Document doc = Jsoup.connect("https://www.franklintempletonindia.com/investor/about-us#tab_tab0 ").get();
		Element blockQuote=doc.getElementsByClass(".fti-bg-skyBlue--2 fti-border-orange").first();
		Elements places = blockQuote.getAllElements();
		List<String> al = new ArrayList<String>();
		for(Element place : places){
			if(!place.ownText().equals(""))
			al.add(place.ownText());
		}
		return new ResponseEntity<List<String>>(al, HttpStatus.OK);
	}

}
