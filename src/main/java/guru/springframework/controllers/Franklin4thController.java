package guru.springframework.controllers;

import java.io.IOException;
import java.util.LinkedHashMap;
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

@RestController
@RequestMapping("/FranklinForthPage")
@Api(value="FranklinForthPageController", description="Operations pertaining to products Defined on Franklin 4th page")
public class Franklin4thController {
	
	@RequestMapping(method= RequestMethod.GET)
	private ResponseEntity franklinDetails() throws IOException{
		Document doc = Jsoup.connect("https://www.franklintempleton.com/investor/products/mutual-funds/overview?FundID=609").get();
		Elements headers=doc.getElementsByClass("sections-title");
		String dollarValues = doc.select("td[class=net-asset-value]").text();
		String offeringPrice = doc.select("td[class=public-offering-price col-md-4]").text(); 
		String yearValues =  doc.select("td[class=year-to-date]").text();
		String finalValues=dollarValues + " "+offeringPrice+ " " +yearValues;
		String[] elements = finalValues.split(" ");
		Map<String, String> map = new LinkedHashMap<String, String>();
		int i=0;
		for(Element header: headers){
			map.put(header.ownText(), elements[i]);
			i++;
			}
	return new ResponseEntity(map, HttpStatus.OK);
	}

}
