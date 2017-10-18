package guru.springframework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

//@RestController
@Api(value="onlinestore", description="Operations pertaining to fetching data from DB")
public class SpringBootJdbcController {

@Autowired	
JdbcTemplate jdbc;

@RequestMapping(value="/insert", method =RequestMethod.GET)
public String fetch(){
	jdbc.execute("insert into users(id,name)values(1,'nimish gupta')");  
	System.out.println("code reached JDBC");
    return "data inserted Successfully"; 
}

}
