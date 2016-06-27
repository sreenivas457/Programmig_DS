package com.ds.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ds.commandresponsebuilder.CommandBuilder;
import com.ds.commandresponsebuilder.Response;

public class CommandBuilderTest {
	private Response response;
	private CommandBuilder cb;
	
	@Before
	public void setup() {
		response = new Response();
		cb = new CommandBuilder();
		response.buildResponse("HOT", "sandals");
		response.buildResponse("COLD", "boots");
		response.setDescription("Put on footwear");
		cb.addCommand(1, response);
	}
	
	@Test
	public void testAddCommand() {
		Response resultResponse = cb.retrieveResponse(1);
		assertEquals("Put on footwear", resultResponse.getDescription());
		assertEquals("sandals", resultResponse.getResponse("HOT"));
		assertEquals("boots", resultResponse.getResponse("COLD"));
	}
}
