package com.rbs.sample;

import com.rbs.sample.Controllers.HelloController;
import com.rbs.sample.Models.Activity;
import com.rbs.sample.Models.WeatherModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationContext.class)
@PropertySource("classpath:application.properties")
public class DemoApplicationTests {

	@Value("${weather.path}")
	private String weatherPath;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private HttpHeaders requestHeaders;

    @InjectMocks
	private HelloController helloTest;
	@Test
	public void Check_Cee_Id_Is_Transformed(){
		// WeatherModel w = new WeatherModel();
		// //WeatherModel w=restTemplate.getForObject(ArgumentMatchers.anyString(), ArgumentMatchers.any(WeatherModel.class));	
		// Mockito.when(restTemplate.getForObject(ArgumentMatchers.anyString(), ArgumentMatchers.<Class<Activity>>any())
		// .thenReturn(w));
		

		Activity myobjectA = new Activity();
		String expectedCeeId = "Cee Id came from spring boot 123";
        //define the entity you want the exchange to return
		Activity act = new Activity();
		act.setCeeId(123);
		// Instruct to ignore requestHeaders.add because we are not testing it.
		Mockito.doNothing().when(requestHeaders).add(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
		ResponseEntity<Activity> myEntity = new ResponseEntity<Activity>(act, HttpStatus.ACCEPTED);
		
        Mockito.when(restTemplate.exchange(
            ArgumentMatchers.anyString(),
            ArgumentMatchers.<HttpMethod>any(),
            ArgumentMatchers.<HttpEntity<?>>any(),
            ArgumentMatchers.<Class<Activity>>any())
		).thenReturn(myEntity);
		
		myobjectA = helloTest.GetCeeDataDetails("123");
		org.junit.Assert.assertEquals(expectedCeeId, myobjectA.getCeeId());
		
	}
	@Test
	public void Check_Correct_weather_Url_is_picked_up_from_config(){


		 WeatherModel w;
		 //WeatherModel w=restTemplate.getForObject(ArgumentMatchers.anyString(), ArgumentMatchers.any(WeatherModel.class));	
		//Mockito.when(restTemplate.getForObject(ArgumentMatchers.anyString(), ArgumentMatchers.<Class<WeatherModel>>any()))
		//.thenReturn(w);
		
		w = helloTest.GetAreaDetails("london");

		//org.junit.Assert.assertEquals(expectedCeeId, myobjectA.getCeeId());
		
	}

}
