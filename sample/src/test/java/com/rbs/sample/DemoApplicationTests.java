package com.rbs.sample;

import com.rbs.sample.Controllers.HelloController;
import com.rbs.sample.Models.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringRunner.class)
public class DemoApplicationTests {
	@Mock
	private RestTemplate restTemplate;
	@Mock
	private HttpHeaders requestHeaders;
    @InjectMocks
	private HelloController helloTest;
	@Test
	public void Check_Cee_Id_Is_Transformed(){
		Activity myobjectA = new Activity();
		String expectedCeeId = "Cee Id came from spring boot 123";
        //define the entity you want the exchange to return
		Activity act = new Activity();
		act.setCeeId(123);
		// Instruct to ignore requestHeaders.add because we are not testing it.
		Mockito.doNothing().when(requestHeaders).add(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
		ResponseEntity<Activity> myEntity = new ResponseEntity<Activity>(act, HttpStatus.ACCEPTED);
		//Mock the upstream Avaloq call.
        Mockito.when(restTemplate.exchange(
            ArgumentMatchers.anyString(),
            ArgumentMatchers.<HttpMethod>any(),
            ArgumentMatchers.<HttpEntity<?>>any(),
            ArgumentMatchers.<Class<Activity>>any())
		).thenReturn(myEntity);
		
		myobjectA = helloTest.GetCeeDataDetails("123","CoId-1");
		org.junit.Assert.assertEquals(expectedCeeId, myobjectA.getCeeId());
		org.junit.Assert.assertEquals("CoId-1", myobjectA.getCorelationId());
	}
}
