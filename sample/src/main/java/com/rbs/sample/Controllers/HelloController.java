package com.rbs.sample.Controllers;

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

@RestController
public class HelloController {

    @Value("${weather.path}")
    private String weatherPath;

    @Value("${lending.ceedata.path}")
    private String ceeDataPath;

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
        
        RestTemplate restTemplate = new RestTemplate();
       
        WeatherModel weather = restTemplate.getForObject(weatherPath+ city, WeatherModel.class);
        return weather;
    }

    @GetMapping("/hello/lenidng/ceedata/{id}")
    public Activity GetAreaDetails(@PathVariable String id)  {
        
        RestTemplate restTemplate = new RestTemplate();
       
        Activity activity = restTemplate.getForObject(ceeDataPath+ id, Activity.class);
        return activity;
    }
}