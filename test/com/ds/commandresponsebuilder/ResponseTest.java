package com.ds.commandresponsebuilder;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ds.commandresponsebuilder.Response;
/**
 * This ResponseTest class tests Response object
 * @author sreenivas
 *
 */
public class ResponseTest {
	/**
	 *ReponseTest Class
	 */
	Response response;
	
	/**
	 * Sets up the basic response object to test
	 */
	@Before
	public void setup(){
		response = new Response();
		response.buildResponse("HOT", "sandals");
		response.buildResponse("COLD", "boots");
		response.setDescription("Put on footwear");
	}

	/**
	 * tests response class for type<->Response mapping
	 */
	@Test
	public void testBuildAndGetResponse() {	
		assertEquals("sandals", response.getResponse("HOT"));
		assertEquals("boots", response.getResponse("COLD"));
	}

}
