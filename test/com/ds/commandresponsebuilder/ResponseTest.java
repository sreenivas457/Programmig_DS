package com.ds.commandresponsebuilder;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ds.commandresponsebuilder.Response;

public class ResponseTest {

	Response response;
	
	
	@Before
	public void setup(){
		response = new Response();
		response.buildResponse("HOT", "sandals");
		response.buildResponse("COLD", "boots");
		response.setDescription("Put on footwear");
	}
	
	@Test
	public void testBuildAndGetResponse() {	
		
		assertEquals("sandals", response.getResponse("HOT"));
		assertEquals("boots", response.getResponse("COLD"));
	}
	
	@Test
	public void testDescription(){
		assertEquals("Put on footwear", response.getDescription());
	}

}
