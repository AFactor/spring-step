package com.rbs.sample.Controllers;

import com.rbs.sample.Models.Activity;
import com.rbs.sample.Models.SampleResponse;
import com.rbs.sample.Models.WeatherModel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;



@RestController
public class HelloController {

    @Value("${weather.path}")
    private String weatherPath;

    @Value("${lending.ceedata.path}")
    private String ceeDataPath;

    @Value("${cookie}")
    private String samlCookie;

    private RestTemplate restTemplate = new RestTemplate();
    
    @GetMapping("/hello")
    public SampleResponse index(@RequestParam(value="name") String name, 
                                @RequestParam(value="id") int id){
        return new SampleResponse(id,name);
    }

    @PostMapping(path = "/hello", consumes = "application/json", produces = "application/json")
    
    public SampleResponse index(@RequestBody SampleResponse response){
        response.Name = response.Name + ": as response";
        return response;
    }

  
    @GetMapping("/hello/area/{city}")
    public WeatherModel GetAreaDetails(@PathVariable String city)  {
        WeatherModel w =  restTemplate.getForObject(weatherPath + city,WeatherModel.class);
        return w;
    }

    @GetMapping("/hello/lending/ceedata/{id}")
    public Activity GetCeeDataDetails(@PathVariable String id)  {
        
        

        System.out.println("inside Cee");
        HttpHeaders requestHeaders = new HttpHeaders();
        // requestHeaders.add("Cookie", samlCookie);
        requestHeaders.add("Cookie", samlCookie);
        HttpEntity<?> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<Activity> act = restTemplate.exchange(ceeDataPath+ id, HttpMethod.GET, requestEntity, Activity.class);
        //changing some value to show basic transformation. Chnage CeeId.
        act.getBody().setCeeId( "Cee Id came from spring boot " + act.getBody().getCeeId());
        return act.getBody();
    }

    
}