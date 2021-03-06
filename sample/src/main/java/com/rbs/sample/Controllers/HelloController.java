package com.rbs.sample.Controllers;

import java.util.List;

import com.rbs.sample.Models.Activity;
import com.rbs.sample.Models.SampleResponse;
import com.rbs.sample.Models.WeatherModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;




@RestController
public class HelloController {

    @Value("${weather.path}")
    private String weatherPath;

    @Value("${lending.ceedata.path}")
    private String ceeDataPath;

    @Value("${cookie}")
    private String samlCookie;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private RestTemplate restTemplate = new RestTemplate();
    
    @GetMapping("/hello")
    public SampleResponse index(@RequestParam(value="name") String name, 
                                @RequestParam(value="id") int id){
        return new SampleResponse(id,name);
    }

    @PostMapping(path = "/hello", consumes = "application/json", produces = "application/json")
    
    public SampleResponse index(@RequestBody SampleResponse response){
        response.setName(response.getName() + ": as response");
        return response;
    }
    @PostMapping(path = "/hello/data", consumes = "application/json", produces = "application/text")
    public String data(@RequestBody SampleResponse response){
        String sql = "INSERT INTO Sample_Response VALUES(" + response.getId() + ",'" + response.getName() + "')"; 
        int i = jdbcTemplate.update(sql);
        return i + " record added";
    }

    @GetMapping(path = "/hello/data/all",produces = "application/json")
    public List<SampleResponse> data(){
        String sql = "SELECT * FROM Sample_Response"; 
        List<SampleResponse> r = jdbcTemplate.query(sql, new BeanPropertyRowMapper<SampleResponse>(SampleResponse.class));
        return r;
    }

    @GetMapping(path = "/hello/data/{id}",produces = "application/json")
    public SampleResponse databyId(@PathVariable int id){
        String sql = "SELECT * FROM Sample_Response where id=" + id; 
        SampleResponse r = (SampleResponse)jdbcTemplate.queryForObject(
            sql, new BeanPropertyRowMapper<SampleResponse>(SampleResponse.class));
        return r;
    }
  
    @GetMapping("/hello/area/{city}")
    public WeatherModel GetAreaDetails(@PathVariable String city)  {
        WeatherModel w =  restTemplate.getForObject(weatherPath + city,WeatherModel.class);
        return w;
    }

    @GetMapping("/hello/lending/ceedata/{id}")
    public Activity GetCeeDataDetails(@PathVariable String id, @RequestHeader(value = "corelationId") String corelationId)  {
        
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", samlCookie);
        HttpEntity<?> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<Activity> act = restTemplate.exchange(ceeDataPath+ id, HttpMethod.GET, requestEntity, Activity.class);
        //changing some value to show basic transformation. Chnage CeeId.
        act.getBody().setCeeId( "Cee Id came from spring boot " + act.getBody().getCeeId());
        act.getBody().setCorelationId(corelationId);
        return act.getBody();
    }

    
}