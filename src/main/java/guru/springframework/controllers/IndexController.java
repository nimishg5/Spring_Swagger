package guru.springframework.controllers;


import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.operations.JsonResponse;
import io.swagger.annotations.Api;
import springfox.documentation.spring.web.json.Json;

//@RestController
@RequestMapping("/index")
@Api(value="indexStore", description="Operations pertaining to products in Index Store")
public class IndexController {
    @RequestMapping(method= RequestMethod.GET)
    Json index() throws IOException{
        Json json = new Json(new JsonResponse().getResponse());
        return json;
    }
}
